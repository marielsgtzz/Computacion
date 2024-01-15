package librerias;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

import librerias.Escrito;
/**
 * @author Mariel Sofía Gutiérrez Zapién
 * @version 2.0 Métodos de funcionalidad básica y otros 9 métodos.
 * Diciembre 2020
 * Clase que tiene la matriz de los diferentes trabajos asi como de los métodos para modificar la matriz de Escritos y Escritos.
 * La matriz puede contener 300 escritos como máximo. 100 por cada tipo de Escrito. Hay tres tipos de escritos: amparos, ensayos, artículos. Cada uno representa un renglón.
 */
public class Trabajo {
	
	private String autor;
	private Escrito[][] datos;
	private int[] conteoArea; //cuantos escritos hay de cada area
	private int[] conteoTipo; //cuantos escritos hay de cada tipo (cantidad de escritos por renglón)
	private final int t = 3; 
	private final int c = 100;
	
	public Trabajo() {
		datos = new Escrito[t][c]; //cada fila es un tipo diferente de escrito
		conteoTipo = new int[3]; //para saber cuantos elementos hay en cada renglon
		conteoArea = new int[10]; //esta hecho para 10 areas diferentes (finanzas, electoral, salud, etc)
	}
	
	public Trabajo(String a) {
		this();
		autor = a;
	}
	
	public Escrito getElemDatos(int i, int j) {
		return datos[i][j];
	}
	
	public String getAutor() {
		return this.autor;
	}
	
	/**
	 * @return La cantidad de escritos que hay registrados en la matriz.
	 */
	public int totalEscritos() {
		int total=0;
		for(int i=0; i<3; i++) {
			total += conteoTipo[i];
		}
		return total;
	}
	
	/**
	 * @param tipo
	 * @param titulo
	 * @param area
	 * @param destinatario
	 * @param fechaE
	 * @param representandoA
	 * @param contacto
	 * @param procedencia
	 * @param apoyadoPor
	 * @param fechaP
	 * @param direccion
	 * @param concluido
	 * Las 11 características de un escrito. (No se considera el folio porque ese lo asigna el programa)
	 * @return Si la alta fue exitosa o no.
	 * No permite que se regristre el mismo escrito dos veces.
	 */
	public boolean agregarEscrito(int tipo, String titulo, int area, String destinatario, String fechaE, String representandoA, String contacto,
			String procedencia, String apoyadoPor, String fechaP, String direccion, String concluido) {
		boolean res = false;
		Escrito e, ec;
		Escrito[] r = new Escrito[conteoTipo[tipo-1]];
		int pos;
		if(tipo>=1 && tipo<=3 && area>=1 && area<= 8) {//solo se agrega si los valores de tipo y area son válidos
			ec = new Escrito(tipo, titulo, area, destinatario, fechaE, representandoA, contacto, procedencia, apoyadoPor, fechaP, direccion, concluido);
			for(int i=0; i<conteoTipo[tipo-1]; i++) { //se copia el renglón de la matriz en donde se busca insertar el nuevo escrito
				r[i] = datos[tipo-1][i];
			}
			pos = ManejadorArreglosGenerico.buscaSecuencialDesordenada(r, conteoTipo[tipo-1], ec);
			if(pos==-1) { //solo si no se encuentra el escrito permite que se añada, esto para evitar escritos repetidos
				if(conteoTipo[tipo-1] < c) { //verifica que haya espacio en la matriz
					e = new Escrito(tipo, titulo, area, destinatario, fechaE, representandoA, contacto, procedencia, apoyadoPor, fechaP, direccion, concluido);
					datos[tipo-1][conteoTipo[tipo-1]] = e;
					conteoTipo[tipo-1]++;
					conteoArea[area-1]++;
					res=true;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ese escrito ya se registró previamente.");
			}
			
		}
		return res;
	}
	
	/**
	 * @return Toda la información de todos los escritos registrados. 
	 * Cada renglon es un escrito y hay un atributo en cada columna.
	 */
	public String[][] datosTabla() {
		int totalE = totalEscritos();
		int[] totalTipo = {totalXTipo(1), totalXTipo(2), totalXTipo(3)};
		String [][] res = new String[totalE][13];
		int cont = 0;
		int i=0;
		int renglon = 0;
		while(i<totalE && renglon<3 && conteoTipo[renglon]!=0) {
			while(cont<conteoTipo[renglon]) {
				res[i][0]=Integer.toString(datos[renglon][cont].getTipo());
				res[i][1]=Integer.toString(datos[renglon][cont].getArea());
				res[i][2]=Integer.toString(datos[renglon][cont].getFolio());
				res[i][3]=datos[renglon][cont].getTitulo();
				res[i][4]=datos[renglon][cont].getDestinatario();
				res[i][5]=datos[renglon][cont].getPersona(0);
				res[i][6]=datos[renglon][cont].getFecha(0);
				res[i][7]=datos[renglon][cont].getDireccion();
				res[i][8]=datos[renglon][cont].getPersona(1);
				res[i][9]=datos[renglon][cont].getPersona(2);
				res[i][10]=datos[renglon][cont].getPersona(3);
				res[i][11]=datos[renglon][cont].getFecha(1);
				if(datos[renglon][cont].isTerminado())
					res[i][12]="si";
				else
					res[i][12]="no";
				cont++;
				i++;
			}
			
			if(cont==totalTipo[renglon]) {
				cont=0;
				renglon++;
			}
		}
		
		
		return res;
	}
	
	/**
	 * @return Imprime los datos de los escritos con un buen formato.
	 */
	public String imprimeDatos() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<3; i++) {
			for(int j=0; j<conteoTipo[i]; j++) {
				sb.append("\nEscrito del tipo " + (i+1) + "\n");
				sb.append(datos[i][j].toString() + " ");
				sb.append("\n");
			}
				
			
		}
		return sb.toString();
	}
	
	/**
	 * @param area
	 * @return indica cuántos escritos del área indicada hay registrados
	 */
	public int totalXArea(int area) {
		return conteoArea[area-1];
	}
	
	/**
	 * @param tipo
	 * @return indica cuántos escritos del tipo indicada hay registrados
	 */
	public int totalXTipo(int tipo) {
		int res=-1;
		if(tipo>=1 && tipo<=3) {
			res = conteoTipo[tipo-1];
		} 
		return res;	
	}
	
	/**
	 * @param folio
	 * @return Los indices de dónde en la matriz se encuentra el escrito del folio que se manda. Regresa {-1,-1} si no se encontró el escrito.
	 */
	public int[] existeFolio(int folio) {
		int[] res = new int[2];
		
		int j=0;
		int i=0;
		while(i<3 && conteoTipo[i]!=0 && folio!=datos[i][j].getFolio()) {
			while(j<conteoTipo[i] && folio>datos[i][j].getFolio())
				j++;
			if(j==conteoTipo[i]) {
				i++;
				j=0;
			}
			
		}
		if(conteoTipo[i]==0) {
			res[0] = -1;
			res[1] = -1;
		} else if(folio==datos[i][j].getFolio()) {
			res[0] = i;
			res[1] = j;
		}
		return res;
	}
	
	/**
	 * @param folio
	 * Dando el folio se elimina el escrito tanto de la matriz como del archivo de texto. 
	 * @throws IOException
	 */
	public boolean eliminarEscrito(int folio) throws IOException {
		boolean exito=false;
		Escrito[] es;
		File ogFile, cFile;
		String l, l2;
		int [] res;
		int k;
		
		//buscar que exista un Escrito con ese folio, si la primera casilla es -1 significa que no existe ese folio en la matriz.
		res = existeFolio(folio);
		
		//llena un arreglo con el renglón de la matriz datos donde se encuentra el escrito por eliminar
		if(res[0]!=-1) {
			es = new Escrito[conteoTipo[res[0]]];
			for(k=0; k<es.length;k++) {
				es[k] = datos[res[0]][k];
			}
			
			//elimina el escrito del archivo de texto
			ogFile = new File("escritos.txt");
			cFile = new File("cambios.txt");
			
			BufferedReader r = new BufferedReader(new FileReader(ogFile));
			BufferedWriter w = new BufferedWriter(new FileWriter(cFile));
			
			l=datos[res[0]][res[1]].formatoArchivo();
			
			while((l2 = r.readLine()) != null) {
				if(l2.contains(l)) continue;
				w.write(l2 + "\n");
			}
			w.close();
			r.close();
			ogFile.delete();
			cFile.renameTo(ogFile);
			
			//quitar el escrito de la matriz
			ManejadorArreglosGenerico.unCorrimientoIzq(es, es.length, res[1]);
			es[es.length-1] = null; //como hubo corrimiento es necesario "eliminar" el último objeto
			conteoTipo[res[0]]--;
			conteoArea[datos[res[0]][res[1]].getArea()-1]--;
			exito=true;
			
			for(k=0; k<es.length;k++) {
				datos[res[0]][k] = es[k];
			}
			
			datos[res[0]][k] = null;
		} else {
			JOptionPane.showMessageDialog(null, "No existe ese folio.");
		}
		
		return exito;
	}

	/**
	 * 
	 * @param folio
	 * @param tipo
	 * @param titulo
	 * @param area
	 * @param destinatario
	 * @param fechaE
	 * @param representandoA
	 * @param contacto
	 * @param procedencia
	 * @param apoyadoPor
	 * @param fechaP
	 * @param direccion
	 * @param concluido
	 * @return si fue exitosa o no la edición
	 * @throws IOException
	 */
	public boolean editarEscrito(int folio, int tipo, String titulo, int area, String destinatario, String fechaE, String representandoA, String contacto,
			String procedencia, String apoyadoPor, String fechaP, String direccion, String concluido ) throws IOException {
		boolean exito=false;
		String lineaActual, l2, lineaCambio;
		File ogFile, cFile;
		
		//buscar que exista un Escrito con ese folio, si la primera casilla es -1 significa que no existe ese folio en la matriz.
		int[] res=existeFolio(folio);
		
		lineaActual = datos[res[0]][res[1]].formatoArchivo(); //Cómo está el escrito en el archivo (sin cambios)
		
		//cambia los valores en la matriz
		datos[res[0]][res[1]].setTipo(tipo);
		datos[res[0]][res[1]].setTitulo(titulo);
		datos[res[0]][res[1]].setArea(area);
		datos[res[0]][res[1]].setDestinatario(destinatario);
		datos[res[0]][res[1]].setFecha(fechaE, 0);
		datos[res[0]][res[1]].setPersona(representandoA, 1);
		datos[res[0]][res[1]].setPersona(contacto, 2);
		datos[res[0]][res[1]].setPersona(procedencia, 0);
		datos[res[0]][res[1]].setPersona(apoyadoPor, 3);
		datos[res[0]][res[1]].setFecha(fechaP, 1);
		datos[res[0]][res[1]].setDireccion(direccion);
		if(concluido.trim().equalsIgnoreCase("si")) {
			datos[res[0]][res[1]].setTerminado(true);
		} else
			datos[res[0]][res[1]].setTerminado(false);
		
		lineaCambio = datos[res[0]][res[1]].formatoArchivo(); //A lo que se cambió el escrito
		
		//cambia en el archivo de texto la linea correspondiente
		ogFile = new File("escritos.txt");
		cFile = new File("cambios.txt");
		
		BufferedReader r = new BufferedReader(new FileReader(ogFile));
		BufferedWriter w = new BufferedWriter(new FileWriter(cFile));
		
		//copia linea por linea el archivo y cambia la linea en donde se encuentra el escrito que se quería cambiar
		while((l2 = r.readLine()) != null) {
			if(l2.contains(lineaActual)){
				l2 = lineaCambio;
				w.write(l2 + "\n");
			} else {
				w.write(l2 + "\n");
			}	
		}
		w.close();
		r.close();
		//se borra el archivo viejo y el nuevo (ya con los cambios) toma el nombre del archivo viejo ("escritos.txt")
		ogFile.delete();
		cFile.renameTo(ogFile);
		exito=true;
		
		return exito;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + Arrays.deepHashCode(datos);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trabajo other = (Trabajo) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (!Arrays.deepEquals(datos, other.datos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Trabajo\n");
		sb.append("Autor: "+autor+"\n");
		sb.append("Datos: "+Arrays.toString(datos)+"\n");
		sb.append("Conteo área: "+Arrays.toString(conteoArea)+"\n");
		sb.append("Conteo tipo: "+Arrays.toString(conteoTipo));
		return sb.toString();
	}
	
	
	
}
