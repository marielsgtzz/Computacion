package p1;

import java.util.Scanner;

public class PruebaJuegoBasketball {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		//Ej.1
		String e1, e2;
		System.out.println("Como se llama el primer equipo?");
		e1 = input.nextLine();
		System.out.println("Como se llama el segundo equipo?");
		e2 = input.nextLine();

		JuegoBasketball partido1 = new JuegoBasketball(e1, e2);
		
//		System.out.println(partido1.toString()+"\n");
//		
//		//Agrega puntos y checa ganador
//		System.out.println("Se logró anadir los puntos: " + partido1.anotacion(3, 2));
//		System.out.println(partido1.toString());
//		System.out.println(partido1.ganador()+"\n");
//		
//		System.out.println("Se logró anadir los puntos: " + partido1.anotacion(2, 1));
//		System.out.println("Se logró anadir los puntos: " + partido1.anotacion(2, 1));
//		System.out.println(partido1.toString());
//		System.out.println(partido1.ganador()+"\n");
		
		//Checa marcador de un equipo
		//sysout ctrl+space
		System.out.println("El equipo de los Warriors tiene "+ partido1.marcador("Warriors") +" puntos.");
	}

}
