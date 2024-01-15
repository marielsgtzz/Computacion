package ejecutables;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

import librerias.Trabajo;
import librerias.VistaBD;
import librerias.VistaBaseDatos;
/**
 * 
 * @author Mariel Sofía Gutiérrez Zapién
 * @version 1.0 Primera versión del controlador que es compatible con el esqueleto de la interfaz gráfica.
 * Noviembre 2020.
 */
public class ControladorVistaBaseDatos extends VistaBaseDatos{

	Trabajo t = new Trabajo("Base Datos");
	
	
	public ControladorVistaBaseDatos() {
		super();
		this.btAlta.addActionListener(new Alta());
		this.btEditar.addActionListener(new Editar());
		this.btEliminar.addActionListener(new Eliminar());
		this.btBuscar.addActionListener(new Buscar());
		this.btClear.addActionListener(new Clear());
		this.btCargar.addActionListener(new CargarDatos());
		this.btDisplay.addActionListener(new DisplayDatos());
		this.btEnter.addActionListener(new EditarInfo());
	}
	
	public class Alta implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			StringBuilder sb = new StringBuilder(); //stringbuilder para la info del escrito
			String r1, r2, r3, r4, r5, r6, r7, r8, r9 ,r10, r11, r12; //los atributos del escrito
			int tipo, area;
			boolean res;
			
			r1 = txTipo.getText();
			r2 = txTitulo.getText();
			r3 = txArea.getText();
			r4 = txDestinatario.getText();
			r5 = txFechaEntrega.getText();
			r6 = txRepresentando.getText();
			r7 = txContacto.getText();
			r8 = txProcedencia.getText();
			r9 = txApoyo.getText();
			r10 = txFechaPub.getText();
			r11 = txDireccion.getText();
			r12 = txTerminado.getText();
			
			String [] atributos = {r1, r2, r3, r4, r5, r6, r7, r8, r9 ,r10, r11, r12};
			
			for(int i=0; i<12; i++) {
				sb.append(atributos[i]);
				sb.append("-");
			}
			
			//Escribir el escrito en el archivo de texto
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
			
			//instanciar un Escrito en la clase Trabajo
			tipo = Integer.parseInt(atributos[0]);
			area = Integer.parseInt(atributos[2]);
			res=t.agregarEscrito(tipo, atributos[1], area, atributos[3], atributos[4], atributos[5], atributos[6], atributos[7],
					atributos[8], atributos[9], atributos[10], atributos[11]);
			
			txTerminal.setText(""+res);
			
		}
	}
	
	public class EditarInfo implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			//por alguna extraña razón no se puede poner 2 en txTerminal porque se freezea el programa.
			boolean num;
			if(txTerminal.getText() != "") {
				try {  
				    Integer.parseInt(txTerminal.getText()); 
				    num = true;
				  } catch(NumberFormatException e){  
				    num = false;  
				  }  
				
				if(num && txTerminal.getText() != "2"){
					int folio = Integer.parseInt(txTerminal.getText());
					int [] indices = t.existeFolio(folio);
					
					if(indices[0] != -1 ) {
						txTitulo.setText(t.getElemDatos(indices[0], indices[1]).getTitulo());
						txTipo.setText(""+t.getElemDatos(indices[0], indices[1]).getTipo());
						txArea.setText(""+t.getElemDatos(indices[0], indices[1]).getArea());
						txDestinatario.setText(t.getElemDatos(indices[0], indices[1]).getDestinatario());
						txDireccion.setText(t.getElemDatos(indices[0], indices[1]).getDireccion());
						txFechaEntrega.setText(t.getElemDatos(indices[0], indices[1]).getFecha(0));
						txFechaPub.setText(t.getElemDatos(indices[0], indices[1]).getFecha(1));
						txProcedencia.setText(t.getElemDatos(indices[0], indices[1]).getPersona(0));
						txRepresentando.setText(t.getElemDatos(indices[0], indices[1]).getPersona(1));
						txContacto.setText(t.getElemDatos(indices[0], indices[1]).getPersona(2));
						txApoyo.setText(t.getElemDatos(indices[0], indices[1]).getPersona(3));
						if(t.getElemDatos(indices[0], indices[1]).isTerminado())
							txTerminado.setText("si");
						else
							txTerminado.setText("no");	
					} else {
						txTerminal.setText("No existe ese folio.");
					}
				}	else
					txTerminal.setText("El folio debe de ser un #.");
				
			}
			
			
		}
	}
	
	public class Editar implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String res = txTerminal.getText();
			int folio = Integer.parseInt(res);
			String tipo, r2, a, r4, r5, r6, r7, r8, r9, r10, r11, r12;
			int r1, r3;
			boolean exito;
			tipo = txTipo.getText();
			a = txArea.getText();
			r2 = txTitulo.getText();
			r4 = txDestinatario.getText();
			r5 = txDireccion.getText();
			r6 = txFechaEntrega.getText();
			r7 = txFechaPub.getText();
			r8 = txProcedencia.getText();
			r9 = txRepresentando.getText();
			r10 = txContacto.getText();
			r11 = txApoyo.getText();
			r12 = txTerminado.getText();
			
			r1 = Integer.parseInt(tipo);
			r3 = Integer.parseInt(a);
			
			try {
				exito = t.editarEscrito(folio, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
				if(exito)
					txTerminal.setText(""+exito);
				else
					txTerminal.setText(""+exito);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * Busca un escrito según la información que regrese el usuario.
	 * Tiene otros tres métodos para buscar Escritos según tipo, área y procedencia.
	 */
	public class Eliminar implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			int folio;
			boolean res;
			String f = txTerminal.getText();
			folio = Integer.parseInt(f);
			try {
				res = t.eliminarEscrito(folio);
				txTerminal.setText(""+res);
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
				System.exit(-1);
			}
			
			
		}
	}
	
	public class Buscar implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String r,r1, r2, r3;
			String datos = ".";
			int  i;
			r = txTerminal.getText();
			if(r.equalsIgnoreCase("tipo")) {
				r1 = txTipo.getText();
				i = Integer.parseInt(r1);
				datos = buscaEscritosTipo(i); //solo busca los Escrito de ese tipo	
			} else if (r.equalsIgnoreCase("area") || r.equalsIgnoreCase("área")) {
				r2 = txArea.getText();
				i = Integer.parseInt(r2);
				datos = buscaEscritosArea(i);
			} else if (r.equalsIgnoreCase("procedencia")) {
				r3 = txProcedencia.getText();
				datos = buscaEscritosProcedencia(r3);
			} else {
				datos = "Ingresa una de las siguientes opciones: \n tipo\n area \n procedencia";
			} 
			
			info1.setText(""+datos);
			
		}
		
		
		public String buscaEscritosTipo(int tipo) {
			String res="-";
			StringBuilder sb = new StringBuilder();
			int[] conteoTipo = {t.totalXTipo(1), t.totalXTipo(2), t.totalXTipo(3)};
			int ct = 0; 
			if(tipo>=1 && tipo<=3) {
				if(tipo==1)
					ct = conteoTipo[0];
				else if(tipo==2)
					ct = conteoTipo[1];
				else if(tipo==3)
					ct = conteoTipo[2];
				else
					res="Ingresa un tipo de dato válido.";
				
				for(int i=0; i<ct; i++) {
					sb.append(t.getElemDatos(tipo-1, i));
					sb.append("\n");
					sb.append("\n");
				}
				
					
				res = sb.toString();
			}
			return res;
		}
		
		public String buscaEscritosArea(int area) {
			int conteoArea = t.totalXArea(area);
			StringBuilder sb = new StringBuilder();
			String res="/";
			int[][] indices = new int[conteoArea][2]; //guardar los indices en donde están lo Escritos de esa área
			int[] conteoTipo = {t.totalXTipo(1), t.totalXTipo(2), t.totalXTipo(3)};
			int cont=0;
			if(area>=1 && area<=8) {
				int j=0;
				int i=0;
				while(i<3 && conteoTipo[i]!=0 && cont!=conteoArea) {
					while(j<conteoTipo[i] && cont!=conteoArea) {
						if(t.getElemDatos(i, j).getArea()==area) {
							indices[cont][0]=i;
							indices[cont][1]=j;
							cont++;
						}
						j++;
					}
					i++;
					j=0;
				}
			
				for(int k=0; k<conteoArea; k++) {
					sb.append(t.getElemDatos(indices[k][0], indices[k][1]));
					sb.append("\n");
					sb.append("\n");
				}
				res = sb.toString();
			
			
			} else
				res = "Ingresa una área válido.";
			
			return res;
		}
		
		public String buscaEscritosProcedencia(String procedencia) {
			int[] conteoTipo = {t.totalXTipo(1), t.totalXTipo(2), t.totalXTipo(3)};
			ArrayList<Integer> l = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			int cont=0;
			int i=0;
			while(i<3 && conteoTipo[i]!=0) {
				for(int j=0; j<conteoTipo[i]; j++) {
					if((t.getElemDatos(i, j).getPersona(0)).equalsIgnoreCase(procedencia)) {
						cont++;
						l.add(i);
						l.add(j);
					}
				}
				i++;
			}
			if(cont!=0) {
				for(int k=0; k-1<cont; k+=2) {
					sb.append(t.getElemDatos(l.get(k), l.get(k+1)));
					sb.append("\n");
					sb.append("\n");
				}	
			} else {
				sb.append("No hay escritos con esa procedencia.");
			}
			
			return sb.toString();
		}
	}
	
	/**
	 * Limpia el texto que pueda haber en los TextFields.
	 */
	public class Clear implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			txTitulo.setText("");
			txTipo.setText("");
			txArea.setText("");
			txDestinatario.setText("");
			txDireccion.setText("");
			txTerminado.setText("");
			txFechaEntrega.setText("");
			txFechaPub.setText("");
			txRepresentando.setText("");
			txProcedencia.setText("");
			txApoyo.setText("");
			txContacto.setText("");
			txTerminal.setText("");
		}
	}
	
	public class DisplayDatos implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			String s = t.imprimeDatos();
			info1.setText(s);
		}
	}
	
	/**
	 *Agregar los elementos existentes en el archivo de texto a la matriz de Trabajo.
	 */
	public class CargarDatos implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			Scanner archivo;
			String [] s = new String[12];//Lee el el archivo y llena un arreglo con la info de un escrito para instanciar usando la clase Trabajo
			int tipo, area;
			boolean res;
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
						
					txTerminal.setText(""+res);
					btCargar.setEnabled(false);
					
					
				}
				
			} catch(FileNotFoundException fnfe) {
				System.out.println(fnfe);
				System.exit(-1);
			}
			
			//DefaultTableModel model = (DefaultTableModel)table1.getModel();
			
			
		}
	}
	
	public static void main(String[] args) {
		ControladorVistaBaseDatos ejecuta = new ControladorVistaBaseDatos();
		
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaBD frame = new VistaBD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		/*
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
	
		
	}

}
