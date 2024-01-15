package p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistroCumples {

	private int[][] datosCumple;
	
	
	public RegistroCumples() {
		
		datosCumple = new int[2][12]; //renglones=sexo, columnas=meses;
		File file = new File("cumple.txt"); //En el txt viene el mes REAL
		try {
			Scanner archivo = new Scanner(file);
			int personas = archivo.nextInt();//Cuantos datos vamos a capturar
			archivo.nextLine();
						
			int mes;
			int sexo; //0-F, 1-M
			for(int i=0; i<personas; i++) {
				mes = archivo.nextInt();
				archivo.nextLine();
				archivo.nextLine();
				if(archivo.next().equals("F")) {
					sexo = 0;
				} else {
					sexo = 1;
				}
				this.datosCumple[sexo][mes-1] += 1; 
			}
			
			
			//System.out.println(personas);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	

	//Cuántas personas cumplen en un mes en específico
	//Asumimos que nos dan el mes real menos 1
	public String cumpleMes(int mes) {
		String res;
		res = "Cumplen "+(datosCumple[0][mes] + datosCumple[1][mes])+" personas en el mes "+(mes+1);
		return res;
	}
	
	//Cumplen más hombres o mujeres en un mes en concreto
	public String mOf(int mes) {
		String res;
		if(datosCumple[0][mes]>datosCumple[1][mes]) {
			res="Cumplen más mujeres que hombres en el mes "+(mes+1);
		} else if(datosCumple[0][mes]<datosCumple[1][mes]) {
			res="Cumplen más hombres que mujeres en el mes "+(mes+1);
		} else {
			res="Cumplen la misma cantidad de mujeres que hombres en el mes "+(mes+1);
		}
		return res;
	}
	
	//En qué meses nadie cumplió años (usando ArrayList)
	public String nadie() {
		ArrayList<Integer> list = new ArrayList();
		//datosCumple.length COLUMNAS
		//datosCumple[0].length RENGLONES 
		for(int i=0; i<datosCumple[0].length;i++) {
			if(datosCumple[0][i] == 0 && datosCumple[1][i] == 0) {
				list.add(i+1);
			}
		}
		String res="Nadie cumple en los meses: "+ list.toString();
		return res;
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.datosCumple[0].length; i++) {
			sb.append("Mes "+(i+1)+": F: "+this.datosCumple[0][i]+" M: "+this.datosCumple[1][i]+"\n");
		}
		return sb.toString();
	}

}
