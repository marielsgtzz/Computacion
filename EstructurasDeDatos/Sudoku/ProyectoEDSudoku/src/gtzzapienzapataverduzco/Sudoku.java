package gtzzapienzapataverduzco;

/**
 * @author Mariel S. Gtz. Zapien, Mariana Zapata Covarrubias & Mauricio Verduzco Chavira
 */

public class Sudoku {
    private static int [][] matrix;
    private static SetADT <Integer> arrayRows[] = new SetA[9]; 
    private static SetADT <Integer> arrayCols[] = new SetA[9]; 
    private static SetADT <Integer> arraySubMatrix[] = new SetA[9];

    // Llena los conjuntos con las datos que da el usuario
    public static int [][] funcionEjecutable(int [][] matrizEntrada){
        int matrizSalida[][]=new int[9][9];
        int numero;
        
        for(int i = 0; i < 9; i++) {
            arrayRows[i] = new SetA<>();
            arrayCols[i] = new SetA<>();
            arraySubMatrix[i] = new SetA<>();
        }
        
        for(int row = 0; row < 9; row++){
            for(int column = 0; column < 9; column++) {
                numero = matrizEntrada[row][column];
                
                if(numero != 0) {
                    if(!evaluaPosibilidad(row, column, numero)){ 
                        throw new ErroneousDataException("Los números ingresados no cumplen con las reglas de Sudoku");
                    
                    }else{
                    arrayRows[row].add(numero); 
                    arrayCols[column].add(numero);
                    arraySubMatrix[defineRegion(row, column)].add(numero);
                    }
                }
                
                matrizSalida[row][column]=numero;
            }  
        }
        
        if  (algortimoPrincipal(matrizSalida)){ //Llamada a Función Recursiva
            return matrizSalida; 
        }else{
            throw new ErroneousDataException("Sudoku sin solución");
        }
    }

    private static boolean algortimoPrincipal(int [][] matrixEntrada){
       
        for(int row=0;row <9;row++){
            for (int column=0;column<9;column++){
                
                if(matrixEntrada[row][column]==0){
                    
                    for (int numero=1;numero<=9;numero++){
                        
                        if(evaluaPosibilidad(row,column,numero)){
                            matrixEntrada[row][column]=numero;
                            arrayRows[row].add(numero);
                            arrayCols[column].add(numero);
                            arraySubMatrix[defineRegion(row, column)].add(numero);
                            
                            if(algortimoPrincipal(matrixEntrada)){//llamada recursiva
                                return true;
                                
                            }else{
                                matrixEntrada[row][column]=0; 
                                arrayRows[row].remove(numero);
                                arrayCols[column].remove(numero);
                                arraySubMatrix[defineRegion(row, column)].remove(numero);  
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean evaluaPosibilidad(int row,int column, int element){
        return !(arrayRows[row].contains(element) || 
                 arrayCols[column].contains(element) || 
                 arraySubMatrix[defineRegion(row, column)].contains(element));
    }
 
    public static int defineRegion(int row, int column) {
        return row/3 * 3 + column/3;
    }
    
    public static String imprimeMatriz(int [][] entrada){
        StringBuilder sb = new StringBuilder();
        sb.append(" - - - - - - - - - - - - - - - ");
        sb.append("\n");
        for(int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if(j==0)
                    sb.append("|");
                sb.append(" " + entrada[i][j] + " ");
                if( j==2 || j==5 || j==8)
                    sb.append("|");
            }
            if(i==2||i==5||i==8)
                sb.append("\n - - - - - - - - - - - - - - - ");
            sb.append("\n");
        }
        return sb.toString();
    }    
}