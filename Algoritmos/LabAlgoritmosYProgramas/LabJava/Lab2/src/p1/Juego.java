package p1;

public class Juego {

	/*
	 * Se necesita un programa de Java para llevar el control de un 
	 * juego de basketball. El contenido que la liga nos está 
	 * pidiendo es que el programa tenga los nombres de ambos 
	 * equipos, los puntajes de cada equipo y el estatus de la 
	 * partida (terminada o en curso). 
	 * 
	 * Aparte se necesita que se pueda registrar cuando un equipo 
	 * anota 1, 2 y 3 puntos, 
	 * 
	 * que se pueda cambiar el status del juego
	 * 
	 * que pueda saber los puntos de un equipo en específico 
	 * 
	 * y ver quién está ganando
	 * actualmente.
	 * */
	
	private String nombreEq1;
	private String nombreEq2;
	private int puntosE1;
	private int puntosE2;
	private String status;
	
	public Juego() {
		
	}
	
	public Juego(String e1, String e2, int p1, int p2, String s) {
		this.puntosE1 = p1;
		this.puntosE2 = p2;
		this.nombreEq1 = e1;
		this.nombreEq2 = e2;
		this.status = s;
	}
	
	//Prueba de que tambien funciona sin el this. (aunque es recomendable usarlo)
	public Juego(String e1, String e2) {
		puntosE1 = 0;
		puntosE2 = 0;
		nombreEq1 = e1;
		nombreEq2 = e2;
		status = "En curso";
	}
	
	public String getNombreEq1() {
		return nombreEq1;
	}

	public void setNombreEq1(String nombreEq1) {
		this.nombreEq1 = nombreEq1;
	}

	public String getNombreEq2() {
		return nombreEq2;
	}

	public void setNombreEq2(String nombreEq2) {
		this.nombreEq2 = nombreEq2;
	}

	public int getPuntosE1() {
		return puntosE1;
	}

	public void setPuntosE1(int puntosE1) {
		this.puntosE1 = puntosE1;
	}

	public int getPuntosE2() {
		return puntosE2;
	}

	public void setPuntosE2(int puntosE2) {
		this.puntosE2 = puntosE2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void anotacion(int puntos, String equipo) {
		if(equipo.equalsIgnoreCase(this.nombreEq1)) {
			this.puntosE1 += puntos;
		} else if (equipo.equalsIgnoreCase(this.nombreEq2)) {
			this.puntosE2 += puntos;
		}
	}
	
	public int puntaje(String equipo) {
		int res=0;
		
		if(equipo.equalsIgnoreCase(this.nombreEq1)) {
			res = this.puntosE1;
		} else if (equipo.equalsIgnoreCase(this.nombreEq2)) {
			res = this.puntosE2;
		}
	
		return res;
	}
	
	public String ganador() {
		String res="";
		
		if(this.puntosE1>this.puntosE2) {
//			res="Equipo 1";
//			res="Va ganando el equipo 1";
			res="Va ganando " + this.nombreEq1;
		} else if (this.puntosE1<this.puntosE2){
			res="Va ganando " + this.nombreEq2;
		} else {
			res="Estan empatados";
		}
			
		
		return res;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre equipo 1: " + this.nombreEq1 + "\n");
		sb.append("Nombre equipo 2: " + this.nombreEq2 + "\n");
		sb.append("Puntaje equipo 1: " + this.puntosE1 + "\n");
		sb.append("Puntaje equipo 2: " + this.puntosE2 + "\n"); 
		sb.append("Estatus juego: " + this.status + "\n");
		return sb.toString();
	}

	//Checa si en dos partidos juegan los mismos equipos
	public boolean equals(Juego j) {
		boolean res=false;
		if ((this.nombreEq1.equalsIgnoreCase(j.nombreEq1) || this.nombreEq1.equalsIgnoreCase(j.nombreEq2)) &&
			this.nombreEq2.equalsIgnoreCase(j.nombreEq1) || this.nombreEq1.equalsIgnoreCase(j.nombreEq2))
			res = true;
		return res;
	}
	
	// Puntaje total es mi criterio de comparacion
	public int compareTo(Juego j) {
		int res = 0;
		int puntajeTotal1 = this.puntosE1 + this.puntosE2;
		int puntajeTotal2 = j.puntosE1 + j.puntosE2;
		
		if(puntajeTotal1>puntajeTotal2) {
			res = 1;
		} else if (puntajeTotal1<puntajeTotal2) {
			res = -1;
		} else {
			res = 0;
		}
		
		return res;
	}

	
}
