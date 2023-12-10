package gtzzapienzapataverduzco;

/**
 * @author Mariel S. Gtz. Zapien, Mariana Zapata Covarrubias & Mauricio Verduzco Chavira
 */

public class ProyectoSegundoParcial_GtzZapienZapataVerduzco {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
    // TODO code application logic here}
        
    int res [][] = new int [9][9]; 
    
    Sudoku k = new Sudoku();

    int prueba [][] =   {{6,9,0,3,7,1,8,4,2},
                         {0,0,0,0,0,0,0,0,0},
                         {8,4,1,2,9,6,3,7,5},
                         {0,0,0,0,0,0,0,0,6},
                         {0,0,0,0,0,0,0,0,8},
                         {5,6,2,0,1,7,4,3,9},
                         {0,0,0,0,0,0,1,8,7},
                         {0,0,0,0,0,0,0,0,0},
                         {7,5,4,0,0,0,0,0,0}};
    
    System.out.println("La matriz de entrada es:");
    
    System.out.println(k.imprimeMatriz(prueba));

    res = k.funcionEjecutable(prueba);
    
    System.out.println("La matriz después de la función se ve así:");
    
    System.out.println(k.imprimeMatriz(res));
    
    }
 
}