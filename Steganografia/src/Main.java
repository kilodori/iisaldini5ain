
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/*
 * @author Ahmed Envar
 * @version 1.0
 * @version 1.1 corretto e commentato
 */
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader tastiera=new BufferedReader(new InputStreamReader(System.in));
		long dim_testo, dim_originale;
		int vet_lunghezza[]=new int [32], vet_immagine[]=new int [8], i;
		int j, lunghezzaMessaggio, vet_carattere[]=new int [8];
		String risposta=null;
		int vet_testo[] = null;
		int vet_contenitore[];
		
		do{
			System.out.println("Vuoi nascondere (n) o svelare (s) ? \nrisposta --> ");
			risposta=tastiera.readLine();
			risposta=risposta.toUpperCase();
		}while(!risposta.equals("S") && !risposta.equals("N"));
		
		if(risposta.equals("N")){
			//punto 1
			dim_testo=GestioneFile.dimensioneFile("testo.txt");
			vet_testo=new int[(int)dim_testo];
			vet_testo=GestioneFile.leggiFileBinario("testo.txt");
			System.out.println("Fine lettura file di testo.");
			//punto 2
			dim_originale=GestioneFile.dimensioneFile("immagineBN.bmp");//immagine originale
			vet_contenitore=new int[(int)dim_originale];
			vet_contenitore=GestioneFile.leggiFileBinario("immagineBN.bmp");//i byte del file immagine sono trasferiti nel vettore vet_contenitore
			System.out.println("Fine lettura file originale.");
			//punto 3
			RandomAccessFile file = null;
			file = new RandomAccessFile("contenitoreBN.bmp", "rw");//conterrà immagine+testo
			System.out.println("File contenitore creato.");
			//punto 4
			vet_lunghezza=BinarioDecimale.decbin((int) dim_testo, 32);//lunghezza del testo trasformata in binario a 32 bit
			//punto 5
			for(i=0; i<32; i++){//per ogni bit della lunghezza
				vet_immagine=BinarioDecimale.decbin(vet_contenitore[140+i], 8);//trasforma in binario a 8 bit il byte del file immagine
				vet_immagine[0]=vet_lunghezza[i]; //sostituisci il bit vet_immagine[0] (è il bit meno significativo del byte immagine) con il bit i-esimo della lunghezza testo
				vet_contenitore[140+i]=BinarioDecimale.bindec(vet_immagine);//ritrasforma in decimale gli 8 bit del byte immagine 
			}
			System.out.println("Ho nascosto la lunghezza del file di testo nel file contenitore (nei byte dal 140 al 171)...");
			
			for(i=0; i<dim_testo; i++){//per ogni carattere del testo
				vet_carattere=BinarioDecimale.decbin(vet_testo[i], 8);//il carattere i-esimo lo trasformo in binario a 8 bit
				for( j=0;j<8;j++){//ogni bit del carattere lo nascondo nel bit meno significativo di ogni byte del file immagine
					vet_immagine=BinarioDecimale.decbin(vet_contenitore[172+i*8+j], 8); //trasformo in binario a 8 bit il byte immagine
					vet_immagine[0]=vet_carattere[j]; ///vet_immagine[0] è il bit meno significativo del byte nel file immagine - lo sostituisco col j-esimo bit del carattere
					vet_contenitore[i*8+172+j]=BinarioDecimale.bindec(vet_immagine);//ritrasformo in decimale il binario a 8 bit del byte immagine
				}
				
			}
			System.out.println("Ho nascosto il messaggio del file di testo nel file contenitore (nei byte dal 172 in poi)...");
			//punto 7
			for(i=0; i<dim_originale; i++) file.write(vet_contenitore[i]);//scrivo immagine+testo nel file contenitore.bmp
			file.close();
			System.out.println("Ho scritto il file contenitore.bmp !");
			
			
		}
		else{
				//Estrazione da un file immagine/audio
				RandomAccessFile file2 = null;
				file2 = new RandomAccessFile("testoSvelato.txt", "rw");
				System.out.println("Nuovo file di testo creato.");
				
				vet_contenitore=GestioneFile.leggiFileBinario("contenitoreBN.bmp");
				System.out.println("Fine lettura file contenitore.");
				System.out.println("Sto estraendo la lunghezza del file di testo dal file contenitore...");
				for(i=140; i<172; i++){//recupero i 32 bit della lunghezza del testo
					vet_immagine=BinarioDecimale.decbin(vet_contenitore[i], 8); 
					vet_lunghezza[i-140]=vet_immagine[0]; //vet_immagine[0] è il bit meno significativo del byte nel file immagine
				}
				//trasformo in decimale i 32 bit della lunghezza (contenuti nel vettore vet_lunghezza)
				lunghezzaMessaggio=BinarioDecimale.bindec(vet_lunghezza);
				//System.out.println(lunghezzaMessaggio);
				vet_testo=new int[lunghezzaMessaggio];
				System.out.println("Sto estraendo il testo  dal file contenitore...");
				for(i=0; i<lunghezzaMessaggio; i++){//per ogni carattere
					for(j=0; j<8; j++){  //estraggo gli 8 bit del carattere i-esimo
						//System.out.println("i"+ i);
						//System.out.println("j"+j);
						vet_immagine=BinarioDecimale.decbin(vet_contenitore[i*8+172+j], 8); 
						vet_carattere[j]=vet_immagine[0]; //vet_immagine[0] è il bit meno significativo del byte nel file immagine
					}
					//trasformo in decimale gli 8 bit del carattere i-esimo
					vet_testo[i]=BinarioDecimale.bindec(vet_carattere); 
					//System.out.println(vet_testo[i]);
				}
				
				//punto 12
				for(i=0; i<lunghezzaMessaggio; i++) file2.write(vet_testo[i]);//scrivo sul file testoSvelato.txt il testo
				file2.close();
				System.out.println("File di testo estratto dal file contenitore e scritto nel nuovo file di testo (testoSvelato.txt)!");	
		}
	}
}
