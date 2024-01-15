package p3;

public class Bici {
/*
 * Se quieren modelar las bicicletas personalizadas que una compañía hace y vende. 
 * Cada bici tiene un id único (generado automáticamente empezando en el número 200), 
 * un tipo ('U' para urbana, 'P' de pista y 'M' de montaña), 
 * número de accesorios extras y material de fabricación (aluminio o fibra de carbono).
 * 
Para registrar una bicicleta se necesitan todos los datos o sólo con el tipo y material.

 */

	private int numAcc;
	private int numSerie; 
	private String material;
	private char tipo;
	
	private static int id = 200; //Contador
	
	public Bici(int numA, String mat, char t ) {
		numAcc = numA;
		material=mat.toLowerCase();
		tipo=t;
		tipo=Character.toUpperCase(t);
		numSerie = id;
		id++;
	}
	
	public Bici(String mat, char t ) {
		material=mat.toLowerCase();
		tipo=Character.toUpperCase(t);
		numSerie = id;
		id++;
	}
/*
	 La bicicleta urbana tiene un costo base de $700, la de pista $12,000 y la de montaña $5,000.
	 El costo de una bicicleta depende de su tipo, del material de fabricación y 
	 el número de accesorios. 
	 Cuando es de aluminio aumenta $2,000 el costo y 
	 $4,000 cuando es de fibra de carbono. 
	 Si el número es menor a 3 no hay costo adicional, si son entre 
	 3 y 4 accesorios se cobran $500 adicionales y 
	 para más de cuatro se cobran $1,000 extras. 
	 Se necesita poder calcular el precio de cada bicicleta.
*/
	public double calculaCosto() {
		double costo=0;
		
		switch(this.tipo) {
			case 'U':
				//costo = costo + 700;
				costo += 700;
				break;
			case 'P':
				costo += 12000;
				break;
			case 'M':
				costo += 5000;
				break;
			default:
				break;
		}
		
		if(this.material.equals("aluminio")) {
			costo += 2000;
		} else if (this.material.equals("fibra de carbono")) {
			costo += 4000;
		} 
		
		if(this.numAcc == 3 || this.numAcc == 4) {
			costo += 500;
		} else if (this.numAcc > 4)
			costo += 1000;
		
		return costo;
	}
	
	/*También se necesita calcular el tiempo que se necesita para armar cada bicicleta. 
	 *Una de tipo urbana toma 1 día, de pista y montaña 2. Si es de fibra se necesita un día extra.
	 */
	
	public int calculaTiempo(){
		int tiempo=1;
		
		if(this.tipo=='P'||this.tipo=='M') {
			tiempo+=1;
		} 
		
		if(this.material.equals("fibra de carbono")) {
			tiempo+=1;
		}
		
		return tiempo;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("El tipo de bici es "+this.tipo);
		sb.append("El material de la bici es "+this.material);
		sb.append("La bici tiene "+this.numAcc+" accesorios");
		sb.append("El numero de serie de la bici es "+this.numSerie);
		return sb.toString();
	}
	
	public int compareTo(Bici otra) {
		int res=0;
		//res=Integer.compare(this.numSerie, otra.numSerie);
		if(this.numSerie<otra.numSerie) {
			res = -1;
		} else if (this.numSerie>otra.numSerie) {
			res = 1;
		}
		
		return res;
	}
	
	public boolean equals(Bici otra) {
		boolean res=false;
		if(numAcc == otra.numAcc && numSerie == otra.numSerie && material.equals(otra.material) && tipo == otra.tipo) {
			res = true;
		}
		return res;
	}	
}
