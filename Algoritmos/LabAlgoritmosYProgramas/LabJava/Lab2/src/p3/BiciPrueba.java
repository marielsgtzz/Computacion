package p3;

import java.util.Scanner;

public class BiciPrueba {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int res;
		Bici[] bicis = new Bici[10];
		int cantBicis = 0;
		
		do {
			System.out.println("Escribe el numero correspondiente a la accion que quieras ejecutar\n");
			System.out.println(
					  "1. Crear una nueva bicicleta\n"
					+ "2. Ver cantidad de bicicletas\n"
					+ "3. Ver informacion de una bicicleta en especifico\n"
					+ "4. Calcular el costo de una bicicleta en especifico\n"
					+ "5. Calcular el tiempo de armado de una bicicleta en especifico\n"
					+ "6. Comparar dos bicicletas\n"
					+ "7. Mostrar la info de todas las bicis\n"
					+ "8. Para salir");
			
			res = input.nextInt();
			
			switch(res){
				case 1:
					if(cantBicis<bicis.length) {
						String mat;
						char t;
						int acc;
						
						System.out.println("De que material esta hecha la bicicleta? Alumninio o fibra de carbobo");
						input.nextLine();
						mat = input.nextLine();
						System.out.println("Cuantos accesorios tiene la bicicleta?");
						acc = input.nextInt();
						System.out.println("De que tipo es la bicicleta, ingresa la inicial correspondiente? Urbana (U), De pista (P), de Montana (M)");
						t = input.next().charAt(0);
						
						Bici a = new Bici(acc, mat, t);
						bicis[cantBicis] = a;
						
						cantBicis++;
					} else {
						System.out.println("Ya no se pueden ingresar más bicicletas");
					}
					break;
				case 2:
					System.out.println("Hay "+cantBicis+" bicicletas.");
					break;
				case 3:
					System.out.println("Que bicicleta quieres consultar?");
					res = input.nextInt();
					
					if(res<cantBicis) {
						System.out.println(bicis[res].toString());
					} else {
						System.out.println("No existe esa bicicleta");
					}
					
					break;
				case 4:
					System.out.println("Que bicicleta quieres consultar?");
					res = input.nextInt();
					if(res<cantBicis) {
						System.out.println(bicis[res].calculaCosto());
					} else {
						System.out.println("No existe esa bicicleta");
					}
					
					break;
				case 5:
					System.out.println("Que bicicleta quieres consultar?");
					res = input.nextInt();
					if(res<cantBicis) {
						System.out.println(bicis[res].calculaTiempo());
					} else {
						System.out.println("No existe esa bicicleta");
					}
					break;
				case 6:
					
					int res2;
					System.out.println("Que bicicleta quieres comparar?");
					res = input.nextInt();
					System.out.println("Que otra bicicleta quieres consultar?");
					res2 = input.nextInt();
					
					if(res<cantBicis && res2<cantBicis) {
						System.out.println(bicis[res].compareTo(bicis[res2]));
					} else {
						System.out.println("No existe esa bicicleta");
					}
					
					break;
				case 7:
					
					for(int i=0; i<cantBicis; i++) {
						System.out.println(bicis[i].toString());
					}
					
					break;
				case 8:
					System.out.println("Gracias");
					break;
				default:
					System.out.println("Esa no es una opcion");
					break;
			}
		}
		while(res!=8);
			
		}
		
	}
	

