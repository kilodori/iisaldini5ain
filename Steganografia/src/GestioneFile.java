
import java.io.*;
public class GestioneFile {
	//lettura file di testo. Valore restituito: stringa col contenuto del file
	public static String leggiFileTesto(String nomeFile) throws IOException{
	   	
    	FileReader fr=null;
    	FileWriter fw=null;
    	BufferedReader br=null;
    	File f=null;
    	fr=new FileReader(nomeFile);
    	br=new BufferedReader(fr);
    	String linea;
    	String testoLetto="";
    	String testoLetto2="";
    
      //leggi il testo dal file un carattere alla volta
        int c;
        do{
        	c=br.read();//leggi un carattere alla volta
			if(c!=-1)
        		testoLetto2=testoLetto2+(char)c;//concatena alla stringa
        }while(c!=-1);	
        //stampa il contenuto del file
        System.out.println("\n*************************************************************" +
        		"\n\nTesto 2\n"+testoLetto2);
        //chiude il file
    	fr.close();
		return null;
	}
	
	//Scrive nel file il contenuto della variabile testo. 
	//Il parametro append indica se aprire il file in append (se vera) 
	public static void scriviFileTesto(String nomeFile,String testo, boolean append) throws IOException{
		FileWriter fw=null;
		fw=new FileWriter(nomeFile,append);
    	// per aprire il file in append
    	//fw=new FileWriter(NOME_FILE_SCRITTURA,true);
    	fw.write(testo);
    	fw.close();
		
	}
	
	//restituisce la dimensione del file (in byte)
	public static long dimensioneFile(String nomeFile) throws IOException{
		//riapro il file che contiene la copia e stampo il contenuto
			int byteImg;
			long lunghezzaFile2=0;
			RandomAccessFile fin=null;
			fin = new RandomAccessFile(nomeFile, "r");
			//lunghezza in  byte del file di input
			lunghezzaFile2=fin.length();
			
			fin.close();
		return lunghezzaFile2;
	}
	
	//Legge il file binario e inserisce i suoi bytes in un vettore di int
	public static int [] leggiFileBinario(String nomeFile) throws IOException{
		RandomAccessFile finImg = new RandomAccessFile(nomeFile, "r");
		//lunghezza in  byte del file di input
		long lunghezzaFile1=finImg.length();
		// open output file 
		int byteImg;
		int vet[]= new int[(int)lunghezzaFile1];
		int i=0;
		do{
			
			byteImg=finImg.read();
			if(byteImg!=-1){
				vet[i]=byteImg;
				i++;
			}
		}while(byteImg!=-1);
		return vet;
	}
	
	
	//Scrive il contenuto del vettore di int[] in un file binario
	public static void scriviFileBinario(String nomeFile, int vet[]) throws IOException{
		RandomAccessFile foutImg = null;
		int byteImg, i=0;
		
		foutImg =new RandomAccessFile(nomeFile, "rw");//apre in lettura/scrittura
		
		do{
			byteImg=vet[i];
			if(byteImg!=-1)foutImg.write(byteImg);
			i++;
		}while(byteImg!=-1);
		
		foutImg.close();
	}
	
	//Scrive il contenuto del vettore di int[] sovrascrivendo  il file dalla  posizione inizio alla posizione fine.
	public static void scriviFileBinario(String nomeFile, int vet[],int inizio, int fine) throws IOException{
		RandomAccessFile foutImg = null;
		int byteImg, i=0;
		
		foutImg =new RandomAccessFile(nomeFile, "rw");
		
		foutImg.seek(inizio);
		
		do{
			byteImg=vet[i];
			if(i!=fine-inizio) foutImg.write(byteImg);
		}while(i!=fine-inizio);
	   
		foutImg.close();
	}
	
	//copia il contenuto del file origine nel file destinazione
	public static void copiaFileBinario(String nomeFileOrigine, String nomeFileDestinazione) throws IOException{
		RandomAccessFile finImg = null;
		RandomAccessFile foutImg = null;
		finImg = new RandomAccessFile(nomeFileOrigine, "r");
		//lunghezza in  byte del file di input
		// open output file 
		foutImg =new RandomAccessFile(nomeFileDestinazione, "rw");//apre in lettura/scrittura 
		
		int byteImg;
		do{	
			//copia del file
			byteImg=finImg.read();
			if(byteImg!=-1)foutImg.write(byteImg);
		}while(byteImg!=-1);
	    	  
	    	  
	}
}