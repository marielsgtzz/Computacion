package p1;

import java.util.ArrayList;

public class AnalizaArreglo {

	public static void main(String[] args) {
		int[] a = {2,8,15,18,26,41,50,66,73,82,90,95};
		ArrayList<Integer> lista = new ArrayList<>();
//		
		System.out.println("\nArreglo original");
		for(int i=0; i<a.length; i++) {
			System.out.println(a[i]);
		}
		
		System.out.println("Lista inicial: "+ lista);

		
		System.out.println("\nHay "+encuentraYEliminaMultiplos(a, 12, 3, lista)+" elementos en el arreglo");
		
		System.out.println("\nArreglo nuevo");
		for(int i=0; i<a.length; i++) {
			System.out.println(a[i]);
		}
		
		System.out.println("Lista: "+ lista);
		
		
	}
	
	public static int encuentraYEliminaMultiplos(int[] arreglo, int totalElem, int numero, ArrayList<Integer> result) {
		int res=totalElem;
		
		for(int i=0; i<totalElem; i++) {
			
			while(arreglo[i]!=0 && arreglo[i]%3==0) {
				//Agregamos a la lista
				result.add(arreglo[i]);
				
				System.out.println("Eliminar "+arreglo[i]);
				
				//Hay que borrar
				corrimientoAIzquierda(arreglo, res, i);
				res--;
			}
			
		}
		
		
		return res;
	}
	
	
	//Indice es el que queremos eliminar
	public static void corrimientoAIzquierda(int[] arreglo, int totalElem, int indice) {
		for (int i=indice; i<totalElem-1; i++)
			arreglo[i]=arreglo[i+1];
		arreglo[totalElem-1]=0;
	}
	
	

}
