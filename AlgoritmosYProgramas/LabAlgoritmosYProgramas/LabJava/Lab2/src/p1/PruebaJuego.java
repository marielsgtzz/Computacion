package p1;

public class PruebaJuego {

	public static void main(String[] args) {
		//Juego p1 = new Juego("Warriors","Lakers");
		Juego partido1 = new Juego("Warriors", "Lakers", 3, 2, "En curso");
		Juego partido2 = new Juego("Celtics", "Bulls", 96, 100, "En curso");
		Juego partido3 = new Juego("Warriors", "Bulls");

		//Imprimir informacion de todos los partidos
		System.out.println(partido1.toString()); //sysout ctrl+espacio
		System.out.println(partido2.toString());
		System.out.println(partido3.toString());
		
		//Anotacion y comprobacion
		System.out.println("\nANOTA EQUIPO");
		partido1.anotacion(3, "celtics");
		System.out.println("Los warriors llevan "+partido1.puntaje("warriors")+" puntos");
		System.out.println("Los warriors llevan "+partido1.puntaje(partido1.getNombreEq1())+" puntos");
		
		//Cambio status juego
		System.out.println("\nCAMBIO STATUS JUEGO");
		partido1.setStatus("Terminado");
		System.out.println(partido1.toString());
		
		//Ganador actual
		System.out.println("\nQUIEN VA GANANDO");
		System.out.println("En el partido 3: " + partido3.ganador());
		
		
		//PRUEBAS EXTRAS
		//int masPuntos = partido1.compareTo(partido2);	
//		//
//		if (masPuntos == -1) {
//			System.out.println("El partido 2 hizo mas puntos");
//		} else if (masPuntos == 1)
//			System.out.println("El partido 1 hizo mas puntos");
//		else
//			System.out.println("Los dos partidos hicieron la misma cantidad de numeros");
//		
//		masPuntos = partido2.compareTo(partido3);
//		if (masPuntos == -1) {
//			System.out.println("El partido 3 hizo mas puntos");
//		} else if (masPuntos == 1)
//			System.out.println("El partido 2 hizo mas puntos");
//		else
//			System.out.println("Los dos partidos hicieron la misma cantidad de numeros");
		
		
		//System.out.println(partido3.ganador());
		//System.out.println(partido1.toString());
		
//		System.out.println(partido2.toString()); 
//		System.out.println(partido3.toString()); 

		
		
	}

}
