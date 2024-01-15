package librerias;
import java.util.ArrayList;

/**
 * 
 * @author Ana Lidia Franzoni Velazquez
 *
 */
public class ManejadorArreglosGenerico {
	
	public static <T extends Comparable<T>> int posMinimo(T [] arreglo, int n){
		int min=0;
		
		for(int i=1; i<n; i++)
			if(arreglo[min].compareTo(arreglo[i])>0)
				min=i;
		return min;
	}

	public static <T extends Comparable<T>>int posMinimo(T [] arreglo, int n, int pos){
		int min=pos;
		
		for(int i=pos+1; i<n; i++)
			if(arreglo[min].compareTo(arreglo[i])>0)
				min=i;
		return min;
	}
	
	public static <T extends Comparable<T>> void seleccionDirecta(T []arreglo, int n){
		int posMin;
		
		for(int i=0; i<n-1; i++){
			posMin=posMinimo(arreglo,n,i);
			swap(arreglo,i,posMin);
		}
	}
	
	public static <T extends Comparable<T>> int buscaSecuencialOrdenada(T [] a, int n, T x){
		int i=0;
		
		while (i<n && a[i].compareTo(x)<0)
			i++;
		if (i==n || !a[i].equals(x))
			i=-i-1;
		return i;
	}
	
	public static <T> int buscaSecuencialDesordenada(T [] a, int n, T x){
		int i=0;
		
		while (i<n && !a[i].equals(x))
			i++;
		if (i==n)
			i=-1;
		return i;
	}
			
	public static <T extends Comparable<T>> int buscaBinaria(T [] a, int n, T x){
		int pos;
		int inicio = 0;
		int fin = n-1;
		int mitad = (inicio+fin)/2; 
		
		while ( inicio <= fin && !a[mitad].equals(x) ) {
			if (x .compareTo(a[mitad])<0)
				fin = mitad-1; 
			else 
				inicio = mitad+1;
			mitad = (inicio+fin)/2;
		}
		if ( inicio > fin )		
			pos = -inicio-1;	
		else 					
			pos = mitad;
		return pos;
	}
	
	public static <T extends Comparable<T>> int posMaximo(T [] arreglo, int n){
		int max=0;
		
		for(int i=1; i<n; i++)
			if(arreglo[max].compareTo(arreglo[i])<0)
				max=i;
		return max;
	}
	
	public static <T extends Comparable<T>> int cuantosMayX(T [] a, int n, T x){
		int cont=0;
		
		for(int i=0; i<n; i++)
			if (a[i].compareTo(x)>0)
				cont++;
		return cont;
	}
	
	public static <T extends Comparable<T>> ArrayList<Integer> cualesMayX(T [] a, int n, T x){
		ArrayList<Integer> lista=new ArrayList<Integer>();
		
		for(int i=0; i<n; i++)
			if (a[i].compareTo(x)>0)
				lista.add(i);
		return lista;
	}

	public static <T extends Comparable<T>> int cuantosMenX(T [] a, int n, T x){
		int cont=0;
		
		for(int i=0; i<n; i++)
			if (a[i].compareTo(x)<0)
				cont++;
		return cont;
	}
	
	public static <T extends Comparable<T>> ArrayList<Integer> cualesMenX(T [] a, int n, T x){
		ArrayList<Integer> lista=new ArrayList<Integer>();
		
		for(int i=0; i<n; i++)
			if (a[i].compareTo(x)<0)
				lista.add(i);
		return lista;
	}
	
	public static <T> void swap(T []a, int posX, int posY){
		T aux;
		
		aux=a[posX];
		a[posX]=a[posY];
		a[posY]=aux;
	}
	
	public static <T> void invierte(T []a, int n){
		for(int i=0;i<n/2;i++)
			swap(a,i,n-1-i);
	}
	
	
	public static <T> void unCorrimientoDer(T [] a, int n, int pos){
        for (int i = n; i>pos; i--)
            a[i] = a[i-1];
	}
	
	public static <T> void unCorrimientoIzq(T [] a, int n, int pos){
		for (int i=pos; i<n-1; i++)
				a[i]=a[i+1];
	}
	
	private static <T> int inserta(T [] a, int n, int pos, T x){
		if(n<a.length){									
			unCorrimientoDer(a,n,pos);
			a[pos]=x;
			n++;
		}
		return n;
	}
	
	public static <T extends Comparable <T>> int insertaEnOrden(T []a, int n, T x){
		int pos;
		
		pos=buscaSecuencialOrdenada(a,n,x);
		if(pos<0){									
			n=inserta(a,n,-pos-1,x);
		}
		return n;
	}
	
	public static  <T> int insertaAlFinal(T []a, int n, T x){
		int nn;
		
		nn=inserta(a,n,n,x);
		return nn;
	}
	
	public static <T> int insertaAlInicio(T []a, int n, T x){
		return inserta(a,n,0,x);
	}
	
	static <T> int elimina(T [] a, int n, int pos){
		unCorrimientoIzq(a,n,pos);
		n--;
		a[n]=null;											
		return n;
	}
	
	public static <T extends Comparable<T>> int eliminaEnOrdenado(T[]a, int n, T x){
		int pos;
		
		pos=buscaSecuencialOrdenada(a,n,x);
		if (pos>=0)									
			n=elimina(a,n,pos);
		return n;
	}
	
	public static <T> int eliminaEnDesordenado(T []a, int n, T x){
		int pos;
		
		pos=buscaSecuencialDesordenada(a,n,x);
		if (pos>=0) {									
			a[pos]=a[n-1];
			n--;
			a[n]=null;
		}
		return n;
	}
	
	
	
	
	
}

	

	