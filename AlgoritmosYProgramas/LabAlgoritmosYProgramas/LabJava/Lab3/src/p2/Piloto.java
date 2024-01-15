package p2;

public class Piloto {

	private String nombre;
	private int puntos;
	
	public Piloto() {
		
	}
	
	public Piloto(String n, int p) {
		nombre = n;
		puntos = p;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	//Funcionalidad mínima (toString, equals, compareTo)
	
}
