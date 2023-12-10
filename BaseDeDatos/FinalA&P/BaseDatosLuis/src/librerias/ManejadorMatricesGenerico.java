package librerias;

/**
 * 
 * @author Mariel Sofía Gutiérrez Zapién
 *
 * @param <T>
 */
public class ManejadorMatricesGenerico <T> {
	
	public static <T extends Comparable<T>> int mayRen(T mat[][], int n, int ren){
		int may=0;
		
		for(int i=1; i<n; i++)
			may=ManejadorArreglosGenerico.posMaximo(mat[ren], n);
		return may;
	}
	
	public static <T extends Comparable<T>> int mayCol(T mat[][], int m, int col){
		int may=0;
		
		for(int j=1; j<m; j++)
			if(mat[may][col].compareTo(mat[j][col])<0)
				may=j;
		return may;
	}
	
	public static <T extends Comparable<T>> int minRen(T mat[][], int n, int ren){
		int min=0;
		
		for(int i=1; i<n; i++)
			min=ManejadorArreglosGenerico.posMinimo(mat[ren], n);
		return min;
	}
	
	public static <T extends Comparable<T>> int minCol(T mat[][], int m, int col){
		int min=0;
		
		for(int j=1; j<m; j++)
			if(mat[min][col].compareTo(mat[j][col])>0)
				min=j;
		return min;
	}
	
	public static <T extends Comparable<T>> int[] posMenor(T mat[][], int m, int n){
		int [] pos={0,0};
		T menor=mat[0][0];
		
		for(int j=0; j<m; j++)
			for(int i=0; i<n; i++)
				if(menor.compareTo(mat[j][i])>0){
					menor=mat[j][i];
					pos[0]=j;
					pos[1]=i;
				}
		return pos;
	}
	
	public static <T extends Comparable<T>> int[] posMayor(T mat[][], int m, int n){
		int [] pos={0,0};
		T mayor=mat[0][0];
	
		for(int j=0; j<m; j++)
			for(int i=0; i<n; i++)
				if(mayor.compareTo(mat[j][i])<0){
					mayor=mat[j][i];
					pos[0]=j;
					pos[1]=i;
				}
		return pos;
	}
	
	public static <T> T [][] traspuesta(T mat[][], int m, int n){
		T [][] res=(T [][]) (new Object [n][m]);
		
		for(int j=0; j<m; j++)
			for(int i=0; i<n; i++)
				res[i][j]=mat[j][i];
		return res;
	}
	
	
}