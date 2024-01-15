package p2;

public class ValorMayorMatriz {

	public static void main(String[] args) {
		int[][] grid1 = {{9,9,8,1},{5,6,2,6},{8,2,6,4},{6,2,2,10}};
		int[][] grid2 = {{1,1,1,1,1},{1,1,1,1,1},{1,1,2,1,1},{1,1,1,1,1},{1,1,1,1,1}};
		int[][] grid = grid2;
		 //Row y col valen lo mismo pq es matriz cuadrada
        int rows = grid[0].length;
        int col = grid.length;
        
        
        int[][] res = new int[rows-2][col-2];
        
        //For matriz chica
        for(int k=0;k<rows-2;k++){
            for(int l=0; l<col-2;l++){
                int max = 0;
                //For matriz grande
                for(int i=k;i<=k+2;i++){
                    for(int j=l;j<=l+2;j++){
                        if(grid[i][j]>max){
                            max = grid[i][j];
                        }
                    }
                }
                
                res[k][l] = max;
                
                
            }
        }
        for(int i=0; i<res[0].length;i++) {
        	for(int j=0; j<res.length;j++) {
        		System.out.print(" "+res[i][j]+"|");
        	}
        	System.out.println("");
        }
	}

}

