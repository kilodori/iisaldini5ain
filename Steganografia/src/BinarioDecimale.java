

public class BinarioDecimale {
	//converte un numero decimale int in binario a nb bit. Restituisce un vettore di cifre binarie
	//la cifra pi� significativa del numero binario � in posizione zero
	public static int [] decbin(int num,int nb){
		int i;
		int vet[]=new int[nb];
		for(i=0;i<nb;i++){
			vet[i]=num%2;
			num=num/2;
		}
		return vet;
	}
	
	//converte un numero binario, le cui cifre sono contenute nel vettore vet passato come parametro, 
	//in un numero decimale int. La cifra pi� significativa del numero binario � in posizione zero
	public static int bindec(int vet[]){
		int app=1;
		int i;
		int tot=0;
		for(i=0;i<vet.length;i++){
			if(vet[i]==1){
				tot+=app;
			}
			app=app*2;
		}
		return tot;
	}
	/*
	public static void main(String[] args){
		int vet[]=new int [32];
		vet=decbin(65,32);
		for(int i=0;i<32;i++){
		System.out.println("val" + vet[i]);
		System.out.println(bindec(vet));
		}	
	}
	*/
}
