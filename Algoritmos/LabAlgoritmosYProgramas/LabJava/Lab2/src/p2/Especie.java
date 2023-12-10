package p2;

public class Especie {

	private String nombre;
	private int poblacion;
	private double velCrecimiento;
	
	public Especie(String nom, int pob, double vC) {
		this.nombre = nom;
		this.poblacion = pob;
		this.velCrecimiento = vC;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public double getVelCrecimiento() {
		return velCrecimiento;
	}

	public void setVelCrecimiento(double vC) {
		this.velCrecimiento = vC;
	}

	/*
	 *  El programa necesita poder predecir la población de la 
	 *  especie dentro de x años, este cálculo se puede lograr 
	 *  realizando la siguiente operación x veces 
	 *  (poblacion = poblacion + (velocidadCrecimiento / 100) * población), 
	 *  el resultado de este cálculo debe de ser un número entero. */
	public int calculaPob(int tiempo) {
		int res = 0;
		double pob = this.poblacion; //Casting implicito
		
		for(int i=0; i<tiempo; i++) {
			pob = pob + (this.velCrecimiento / 100) * pob;
		}
		
		res = (int)pob; //Casting explicito
		
		return res;
	}
	
	//Cambiar info
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre especie: " + this.nombre + "\n");
		sb.append("Poblacion especie: " + this.poblacion + "\n");
		sb.append("Velocidad Crecimiento especie: " + this.velCrecimiento + "\n");
		return sb.toString();
	}
	
	//Criterio de comparacion es poblacion actual
	public int compareTo(Especie e) {
		int res = 0;
		
		if(this.poblacion>e.poblacion) {
			res = 1;
		} else if (this.poblacion<e.poblacion) {
			res = -1;
		} else {
			res = 0;
		}
		
		return res;
	}
	
	public boolean equals(Especie e) {
		boolean res = false;
		if(this.nombre.equals(e.nombre) && this.poblacion==e.poblacion &&
				this.velCrecimiento == e.velCrecimiento) {
			res = true;
		}
		
		return res;
	}
	
	
	
	
	
	
	
	
}
