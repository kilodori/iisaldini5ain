import java.io.*;

public class FileBinari {
	public static void main(String args[])throws Exception{
		RandomAccessFile finImg = null;
		RandomAccessFile foutImg = null;
		
		String fileInImg="Immagine.jpg";
		String fileOutImg="Immagine2.jpg";
		long lunghezzaFile1=0;
		long lunghezzaFile2=0;
		int byteImg;
		
		File f=new File(fileInImg);
		if(f.exists()==false){
			System.out.println("Il file "+fileInImg+" non esiste!");
			System.exit(0);
		}
		// apertura file
		finImg = new RandomAccessFile(fileInImg, "r");
		//lunghezza in  byte del file di input
		lunghezzaFile1=finImg.length();
		// open output file 
		foutImg =new RandomAccessFile(fileOutImg, "rw");//apre in lettura/scrittura
		do{
			byteImg=finImg.read();
			if(byteImg!=-1)foutImg.write(byteImg);
		}while(byteImg!=-1);
	    	  
		//riposiziono a zero il puntatore e leggo il contenuto del file che ho scritto
		foutImg.seek(0);
		lunghezzaFile2=foutImg.length();
		do{
			byteImg=foutImg.read();
			if(byteImg!=-1) System.out.print(byteImg+" ");
		}while(byteImg!=-1);
	      
		System.out.println("\nLunghezza del file "+fileInImg+": "+lunghezzaFile1);
		System.out.println("\nLunghezza del file "+fileOutImg+": "+lunghezzaFile2);

	    	//chiudi i file
		finImg.close();
		foutImg.close();
	      
		//riapro il file che contiene la copia e stampo il contenuto
		RandomAccessFile fin=null;
		fin = new RandomAccessFile(fileOutImg, "r");
		//lunghezza in  byte del file di input
		lunghezzaFile2=fin.length();
		//ciclo per la lettura del file
		for (int i=0;i<lunghezzaFile2;i++){
			byteImg=fin.read();
			System.out.print(byteImg+" ");
		}
		//chiusura del file
		fin.close();
	}
}