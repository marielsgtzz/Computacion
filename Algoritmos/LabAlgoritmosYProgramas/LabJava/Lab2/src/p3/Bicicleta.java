package p3;

public class Bicicleta {

	/*
	 * Se quieren modelar las bicicletas personalizadas que una compañía hace y vende. 
	 * Cada bici tiene un id único (generado automáticamente empezando en el número 200)
	 * un tipo ('U' para urbana, 'P' de pista y 'M' de montaña)
	 * número de accesorios extras
	 * material de fabricación (aluminio o fibra de carbono).
	 * 
	 * Para registrar una bicicleta se necesitan todos los datos o sólo con el tipo y material.
	 * 
	 * La bicicleta urbana tiene un costo base de $700, la de pista $12,000 y la de montaña $5,000.
	 * El costo de una bicicleta depende de su tipo, del material de fabricación y el número de accesorios.
	 * Cuando es de aluminio aumenta $2,000 el costo y $4,000 cuando es de fibra de carbono. 
	 * Si el número es menor a 3 no hay costo adicional, 
	 * si son entre 3 y 4 accesorios se cobran $500 adicionales
	 * para más de cuatro se cobran $1,000 extras. Se necesita poder calcular el precio de cada bicicleta.
	 * 
	 * También se necesita calcular el tiempo que se necesita para armar cada bicicleta.
	 * Una de tipo urbana toma 1 día, de pista y montaña 2. Si es de fibra se necesita un día extra.
	 * 
	 * Las bicicletas se necesitan comparar entre ellas, su criterio de comparación es el id. 
	 * */
	
	private static int numSerie = 200;
	private int id;
	private char tipo;
	private int numAcc;
	private String material;
	
	public Bicicleta(char t, int numA, String mat) {
		this.tipo = Character.toUpperCase(t);
		this.numAcc = numA;
		this.material = mat.toLowerCase();
		this.id = numSerie;
		numSerie++;
	}
	
	public Bicicleta(char t, String mat) {
		this.tipo = Character.toUpperCase(t);
		this.material = mat.toLowerCase();
		this.id = numSerie;
		numSerie++;
	}
	
	/*
	 * La bicicleta urbana tiene un costo base de $700, la de pista $12,000 y la de montaña $5,000.
	 * El costo de una bicicleta depende de su tipo, del material de fabricación y el número de accesorios.
	 * Cuando es de aluminio aumenta $2,000 el costo y $4,000 cuando es de fibra de carbono. 
	 * Si el número es menor a 3 no hay costo adicional, 
	 * si son entre 3 y 4 accesorios se cobran $500 adicionales
	 * para más de cuatro se cobran $1,000 extras. Se necesita poder calcular el precio de cada bicicleta.
	 * 
	 */
	public double costoBici() {
		double costo = 0;
		
		//Tipo
		switch(this.tipo) {
			case 'U':
				costo += 700;
				break;
			case 'P':
				costo += 1200;
				break;
			case 'M':
				costo += 5000;
				break;
			default:
				break;
		}
		
		//Material
		if (this.material.equals("aluminio")) {
			costo += 2000;
		} else if (this.material.equals("fibra de carbono")) {
			costo += 4000;
		} 
		
		//Accesorios
		if(this.numAcc == 3 || this.numAcc == 4) {
			costo += 500;
		} else if (this.numAcc > 4) {
			costo += 1000;
		}
		
		return costo;
	}
	
	/*
	 * También se necesita calcular el tiempo que se necesita para armar cada bicicleta.
	 * Una de tipo urbana toma 1 día, de pista y montaña 2. Si es de fibra se necesita un día extra.
	 */
	public int tiempoArmado() {
		int tiempo = 0;
		
		switch(this.tipo) {
			case 'U':
				tiempo += 1;
				break;
			case 'P':
				tiempo += 2;
				break;
			case 'M':
				tiempo += 2;
				break;
			default:
				break;
		}
		
		if (this.tipo == 'U') {
			tiempo += 1;
		} else if (this.tipo == 'P' || this.tipo == 'M') {
			tiempo += 2;
		}
		
		if(this.material.equals("fibra de carbono")) {
			tiempo += 1;
		}
		
		return tiempo;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id bicicleta: " + this.id + "\n");
		sb.append("Tipo de bicicleta: " + this.tipo + "\n");
		sb.append("Numero de accesorios: " + this.numAcc + "\n");
		sb.append("Material: " + this.material + "\n\n");
		return sb.toString();
	}
	
	/*
	* Las bicicletas se necesitan comparar entre ellas, su criterio de comparación es el id. 
	* */
	public int compareTo(Bicicleta b) {
		int res = 0;

		if(this.id>b.id) {
			res = 1;
		} else if (this.id<b.id) {
			res = -1;
		} else {
			res = 0;
		}
		
		return res;
	}
	
	public boolean equals(Bicicleta b) {
		boolean res=false;
		
		if(this.id==b.id && this.material.equals(b.material) && this.numAcc==b.numAcc && this.numAcc==b.numAcc)
			res = true;
		return res;
	}
	
	
}