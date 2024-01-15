package p1;

import java.util.Objects;

public class JuegoBasketball {

	/*Se necesita un programa de Java para llevar el control de un juego de basketball. 
	El contenido que la liga nos está pidiendo es que el programa tenga los nombres de ambos equipos, 
	los puntajes de cada equipo y el estatus de la partida (terminada o en curso). 
	Aparte se necesita que se pueda registrar cuando un equipo anota 1, 2 y 3 puntos, 
	que se pueda cambiar el status del juego, que pueda saber los puntos de un equipo en específico 
	y ver quién está ganando actualmente.*/
	
	private String equipo1;
	private String equipo2;
	private int puntosEq1;
	private int puntosEq2;
	private String status;
	
	public JuegoBasketball(String e1, String e2) {
		this.equipo1 = e1;
		this.equipo2 = e2;
		this.puntosEq1 = 0;
		this.puntosEq2 = 0;
		this.status = "En curso";
	}
	
	public String getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}

	public String getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}

	public int getPuntosEq1() {
		return puntosEq1;
	}

	public void setPuntosEq1(int puntosEq1) {
		this.puntosEq1 = puntosEq1;
	}

	public int getPuntosEq2() {
		return puntosEq2;
	}

	public void setPuntosEq2(int puntosEq2) {
		this.puntosEq2 = puntosEq2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param puntos Cuantos puntos se anotaron
	 * @param eq Que equipo anoto (1 o 2)
	 * */
	public boolean anotacion(int puntos, int eq) {
		boolean res=true;
		
		switch(eq) {
			case 1:
				this.puntosEq1 += puntos;
				break;
			case 2:
				this.puntosEq2 += puntos;
				break;
			default:
				res = false;
				break;
		}
		
		return res;
	}
	
	/**
	 * @return Regresa una string formateada diciendo quien va ganando.
	 * */
	public String ganador() {
		String res="";
		if(this.puntosEq1 > this.puntosEq2) {
			res = "Van ganando: " + this.equipo1;
		} else if (this.puntosEq1 < this.puntosEq2) {
			res = "Van ganando: " + this.equipo2;
		} else {
			res = "Van empatados.";
		}
		return res;
	}
	
	/**
	 * @return Regresa el marcador segun el equipo en parametro, si el equipo no esta jugando regresa -1
	 * */
	public int marcador(String equipo) {
		int res;
		if (equipo.equals(this.equipo1) || equipo.equals(this.equipo2)) {
			if(equipo.equals(equipo1))
				res = this.puntosEq1;
			else
				res = this.puntosEq2;
		} else
			res = -1;
		return res;
	}
	
	public String toString() {
		return "JuegoBasketball \n"+
	"Equipo 1 = " + equipo1 + " - Puntos = " + puntosEq1 + "\nEquipo 2 = " + equipo2 + 
	" - Puntos = " + puntosEq2 + "\nStatus juego = " + status + ".";
	}

	/**
	 * Checa si en dos partidas estan jugando los mismos equipos
	 * */
	public boolean equals(JuegoBasketball juego) {
		return ((this.equipo1.equalsIgnoreCase(juego.equipo1) || this.equipo1.equalsIgnoreCase(juego.equipo2)) &&
				(this.equipo2.equalsIgnoreCase(juego.equipo1) || this.equipo1.equalsIgnoreCase(juego.equipo2)));
	}

}
