package ejecutables;
import librerias.Trabajo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * 
 * @author Mariel Sofía Gutiérrez Zapién
 * @version 1.1 Para probar la fase inicial del código con la terminal.
 * 
 */
public class PruebaTrabajo {

	public static void main(String[] args) throws IOException {
		Trabajo t = new Trabajo("Luis");
		boolean res;
		Scanner lee, archivo;
		int n, tipo, area;
		int archivosRegistrados = 0;
		
		StringBuilder cadTipo = new StringBuilder(); 
		cadTipo.append("1 - Amparo \n");
		cadTipo.append("2 - Ensayo \n");
		cadTipo.append("3 - Artículo para revista / periódico \n");
		
		StringBuilder cadArea = new StringBuilder(); 
		cadArea.append("1 - Corrupción \n");
		cadArea.append("2 - Constitucionales \n");
		cadArea.append("3 - Electoral \n");
		cadArea.append("4 - Medio Ambiente \n");
		cadArea.append("5 - Penal \n");
		cadArea.append("6 - Tema financiero \n");
		cadArea.append("7 - Política Pública \n");
		cadArea.append("8 - Salud \n");
		
		String[] preguntas = {"Cuál es el tipo del escrito?", "Cuál es el título?", "Cuál es el área?", "Ante quién se presentó?", 
				"Cuándo fue la fecha de entrega?", "A quién se está representando?", "Contacto del representado", "Cuál es la procedencia?", 
				"Encaso de haber recibido apoyo de quién fue?","Cuándo se publicó?", 
				"En dónde está guardado?", "Ya está concluido el asunto?"};
		String r; //respuesta a la info necesaria para el escrito
		int r1;
		String[] s = new String[12];
		
		/**
		 * Crear archivo para guardar info de los escritos
		 */
		try {
		      File e = new File("escritos.txt");
		      if (e.createNewFile()) {
		        System.out.println("Se creo el archivo: " + e.getName());
		      } else {
		        System.out.println("Ya existe el archivo.\n");
		      }
		      
		 } catch (IOException e) {
		      System.out.println("Error: " + e);
		      e.printStackTrace();
		    }
		
		
		/**
		 * Obtener información de escrito del usuario (en consola) y la pone en el archivo de texto
		 */
		lee = new Scanner(System.in);
		System.out.println("Cuántos escritos quieres registrar?");
		n = lee.nextInt();
		System.out.println("Se quieren registrar "+n+" escritos.\n");
		
		StringBuilder sb = new StringBuilder(); //stringbuilder para la info del escrito
		for(int i=0; i<n; i++) {
			for(int j=0; j<12; j++) { //loop para hacer las 12 preguntas para obtener la información necesaria del escrito
				if(j==0) {
					System.out.println("\n" + preguntas[j] + "\n");
					System.out.println(cadTipo.toString());
					r1 = lee.nextInt();
					lee.nextLine();
					sb.append(r1);
					sb.append("-"); 
					
				} else if (j==2) {
					System.out.println("\n" + preguntas[j] + "\n");
					System.out.println(cadArea.toString());
					r1 = lee.nextInt();
					lee.nextLine();
					sb.append(r1);
					sb.append("-"); 
					
				} else {
					System.out.println("\n" + preguntas[j] + "\n");
					r = lee.nextLine(); 
					lee.nextLine();
					sb.append(r);
					sb.append("-");  
					
				}	
				
			}
			
			/**
			 * Escribir datos del escrito. (un escrito por línea)
			 */
			try {
			      FileWriter eW = new FileWriter("escritos.txt", true);
			      eW.write("\n");
			      eW.write(sb.toString());
			      eW.close();
			      System.out.println("Se escribio en el archivo con exito.");
			      
			} catch (IOException e) {
			      System.out.println("Error: " + e);
			      e.printStackTrace();
			    }
			
			sb.setLength(0); //vacía StingBuilder
		}
		
		/*
		 * Lee el el archivo y llena un arreglo con la info de un escrito 
		 * para instanciar usando la clase Trabajo
		 */
		try {
			archivo = new Scanner(new File("escritos.txt"));
			String line;
			archivo.nextLine();
			while(archivo.hasNextLine()) {
				line = archivo.nextLine();
				s = line.split("-"); //llena el arreglo s con los 12 atributos del escrito
				tipo = Integer.parseInt(s[0]);
				area = Integer.parseInt(s[2]);
				res=t.agregarEscrito(tipo, s[1], area, s[3], s[4], s[5], s[6], 
						s[7], s[8], s[9], s[10], s[11]);
				
					
				System.out.println(res);
			}
			
			System.out.println(t.imprimeDatos());
			System.out.println(t.totalXArea(6));
			System.out.println(t.totalXTipo(1));
			
			res = t.eliminarEscrito(2);
			System.out.println(res);

			System.out.println(t.imprimeDatos());
			System.out.println(t.totalXArea(6));
			System.out.println(t.totalXTipo(1));
			
			System.out.println(t.getElemDatos(0, 1));
			
		} catch(FileNotFoundException fnfe) {
			System.out.println(fnfe);
			System.exit(-1);
		}	
	}	
}
	
	
