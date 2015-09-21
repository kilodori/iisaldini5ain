import java.io.*;

public class FileTesto {
    public static void main(String[] args) throws Exception{
    	final String NOME_FILE_LETTURA="testoRead.txt";//nome del file aperto in lettura
    	final String NOME_FILE_SCRITTURA="testoWrite.txt";//nome del file aperto in scrittura
    	
    	FileReader fr=null;
    	FileWriter fw=null;
    	BufferedReader br=null;
    	File f=null;
    	
    	f=new File(NOME_FILE_LETTURA);
    	if(f.exists()==false){
    		System.out.println("Il file "+NOME_FILE_LETTURA+" non esiste!");
    		System.exit(0);
    	}
    	
    	
    	String linea;
    	String testoLetto="";
    	String testoLetto2="";
    	
    	//apri il file in lettura
    	fr=new FileReader(NOME_FILE_LETTURA);
    	br=new BufferedReader(fr);
    	
        //leggi il testo dal file riga  per riga

        do{
        	linea=br.readLine();
        	if(linea!=null)
        		testoLetto=testoLetto+"\n"+linea;
        }while(linea!=null);
        fr.close();

      //stampa il contenuto del file
        System.out.println("Testo 1\n"+testoLetto);
        
      //apri il file in lettura
    	fr=new FileReader(NOME_FILE_LETTURA);
    	br=new BufferedReader(fr);
    
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
    	
        //Per scivere su file
        //apri il file in scrittura

    	fw=new FileWriter(NOME_FILE_SCRITTURA);
    	// per aprire il file in append
    	//fw=new FileWriter(NOME_FILE_SCRITTURA,true);
    	fw.write(testoLetto2);

        //chiude il file
    	fw.close();
	
    }
}