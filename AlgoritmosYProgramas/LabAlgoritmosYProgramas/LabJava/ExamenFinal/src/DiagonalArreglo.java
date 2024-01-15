/**
 * Dado un arreglo bidimensional de m x n elementos se quiere saber si
 * todos los elementos de su diagonal principal son iguales.
 * 
 * Escribe un metodo estatico generico que reciba un arreglo bidimensional generico 
 * y el total de renglones y columnas que tiene ocupados y que 
 * regrese un boolean indicando true si todos los elementos
 * de la diagonal son iguales o false si no lo son
 * */

public class DiagonalArreglo {

	public static <T> boolean diagonal(T[][] mat, int reg, int col) {
		boolean res = true;
		
		int menor;
		if(reg>col) {
			menor = col;
		} else {
			menor = reg;
		}
		
		int i=0;
		//System.out.println(mat.getClass());
		while (i!=menor-1 && mat[i][i].equals(mat[i+1][i+1])) {
			System.out.println("i: "+i+"  "+mat[i][i]+" vs. "+mat[i+1][i+1]);
			
			i++;
		}
		if(i!=menor-1 && !mat[i][i].equals(mat[i+1][i+1])) {
			System.out.println("Error");
			res = false;
		}
		return res;
	}
	
	public static void main(String[] args) {
		String[][] a = {{"Quito","Shangai","Hanoi"},{"Kiev","Quito","Nairobi"},{"Nairobi","Paris","Quito"},{"Quito","Roma","Madrid"}};
		System.out.println(diagonal(a,4,3));
		
		String[][] b = {{"Quito","Shangai","Hanoi"},{"Kiev","Quito","Nairobi"},{"Nairobi","Paris","Monterrey"},{"Quito","Roma","Madrid"}};
		System.out.println(diagonal(b,4,3));
		
		Integer[][] c = {{2,1,3},{3,2,1}};
		System.out.println(diagonal(c,2,3));
	}

}
