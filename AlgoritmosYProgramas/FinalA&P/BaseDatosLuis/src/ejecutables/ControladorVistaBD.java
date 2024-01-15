package ejecutables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import librerias.Trabajo;
import librerias.VistaBD;
/**
 * 
 * @author Mariel Sofía Gutiérrez Zapién
 * @version 2.0
 * Diciembre 2020
 */
public class ControladorVistaBD extends VistaBD{

	Trabajo t = new Trabajo("Base Datos");
	
	
	public ControladorVistaBD(String t) {
		super(t);
		this.btAlta.addActionListener(new Alta());
		this.btEditar.addActionListener(new Editar());
		this.btEliminar.addActionListener(new Eliminar());
		this.btBuscar.addActionListener(new Buscar());
		this.btClear.addActionListener(new Clear());
		this.btCargar.addActionListener(new CargarDatos());
		this.btDisplay.addActionListener(new DisplayDatos());
		this.btEnter.addActionListener(new EditarInfo());
		this.btDuda.addActionListener(new Informacion());
	}
	
	/**
	 * Obtiene la información de las casillas correspondientes para crear un objeto en la matriz y escribirlo en el archivo de texto.
	 * Aparecen mensajes correspondientes si no se ingresa texto apropiado.
	 */
	public class Alta implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if(btCargar.isEnabled()) {
				JOptionPane.showMessageDialog(null, "Primero se tienen que cargar los datos.");
			} else {
				boolean num , num2;
				int n, n2;
				if(txTipo.getText().equals("") || txTitulo.getText().equals("") ||txArea.getText().equals("") ||txDestinatario.getText().equals("") ||
				txFechaEntrega.getText().equals("") || txRepresentando.getText().equals("") || txContacto.getText().equals("") ||
				txProcedencia.getText().equals("") ||txApoyo.getText().equals("") ||txFechaPub.getText().equals("") ||txDireccion.getText().equals("") ||
				txTerminado.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Llene todas las casillas menos la de folio.");
				} else {
					try {  
					    Integer.parseInt(txTipo.getText()); 
					    Integer.parseInt(txArea.getText()); 
					    num = true;
					    num2 = true;
					  } catch(NumberFormatException e){  
					    num = false;  
					    num2 = false;
					  } 
					if(num && num2) {
						n = Integer.parseInt(txTipo.getText());
						if(n>=1 && n<=3) {
							n2 = Integer.parseInt(txArea.getText()); 
							if(n2>=1 &&n2<=8) {
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
								
								if(res)
									txTerminal.setText("Alta existosa.");
								else 
									txTerminal.setText("Alta falló.");
							}else {
								JOptionPane.showMessageDialog(null, "Área tiene que ser:\n1 - Corrupción \n2 - Constitucional\n3 - Electoral\n"+
										"4 - Medio Ambiente \n5 - Penal \n6 - Finanzas\n7 - Política Pública \n8 - Salud \n");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Tipo tiene que ser:\n1 - Amparo\n" + "2 - Ensayo\n"+"3 - Artículo para revista / periódico\n");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ingrese números en tipo y área.");
					}
				}
			}
			
			
		}
	}
	
	/**
	 * Se llenan los TextFields con la información correspondiente al escrito con el folio ingresado.
	 */
	public class EditarInfo implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if(btCargar.isEnabled()) {
				JOptionPane.showMessageDialog(null, "Primero se tienen que cargar los datos.");
			} else {
				boolean num;
				
				if(txFolio.getText() != "") {
					try {  
					    Integer.parseInt(txFolio.getText()); 
					    num = true;
					  } catch(NumberFormatException e){  
					    num = false;  
					  }  
					
					if(num){
						int folio = Integer.parseInt(txFolio.getText());
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
							JOptionPane.showMessageDialog(null, "No existe ese folio.");
						}
					}	else
						JOptionPane.showMessageDialog(null, "El folio debe de ser un número.");
					
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese un folio.");
				}
			}
			//por alguna extraña razón no se puede poner 2 en txTerminal porque se freezea el programa.
			
		}
	}
	
	/**
	 * Una vez que hay información en todas las casillas y se haya cambiado la información necesaria se cambia el objeto en la matriz 
	 * y archivo de texto usando el folio como puntero.
	 */
	public class Editar implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if(btCargar.isEnabled()) {
				JOptionPane.showMessageDialog(null, "Primero se tienen que cargar los datos.");
			} else {
				boolean num , num2, num3;
				int n, n2;
				if(txTipo.getText().equals("") || txTitulo.getText().equals("") ||txArea.getText().equals("") ||txDestinatario.getText().equals("") ||
				txFechaEntrega.getText().equals("") || txRepresentando.getText().equals("") || txContacto.getText().equals("") ||
				txProcedencia.getText().equals("") ||txApoyo.getText().equals("") ||txFechaPub.getText().equals("") ||txDireccion.getText().equals("") ||
				txTerminado.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Llene todas las casillas.");
				} else {
					try {  
					    Integer.parseInt(txTipo.getText()); 
					    Integer.parseInt(txArea.getText()); 
					    Integer.parseInt(txFolio.getText());
					    num = true;
					    num2 = true;
					    num3 = true;
					  } catch(NumberFormatException e){  
					    num = false;  
					    num2 = false;
					    num3 = false;
					  }  
					 
					if(num && num2 && num3) {
						n = Integer.parseInt(txTipo.getText()); 
						n2 =Integer.parseInt(txArea.getText()); 
						if(n>= 1 && n<=3) {
							if(n2 >= 1 && n2<=8) {
								String res = txFolio.getText();
								int folio = Integer.parseInt(res);
								String tipo, r2, a, r4, r5, r6, r7, r8, r9, r10, r11, r12;
								int r1, r3;
								boolean exito;
								tipo = txTipo.getText();
								r2 = txTitulo.getText();
								a = txArea.getText();
								r4 = txDestinatario.getText();
								r5 = txFechaEntrega.getText();
								r6 = txRepresentando.getText();
								r7 = txContacto.getText();
								r8 = txProcedencia.getText();
								r9 = txApoyo.getText();
								r10 = txFechaPub.getText();
								r11 = txDireccion.getText();
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
						} else {
							JOptionPane.showMessageDialog(null, "Área tiene que ser:\n1 - Corrupción \n2 - Constitucional\n3 - Electoral\n"+
									"4 - Medio Ambiente \n5 - Penal \n6 - Finanzas\n7 - Política Pública \n8 - Salud \n"); 
					} 
					} else {
					JOptionPane.showMessageDialog(null, "Tipo tiene que ser:\n1 - Amparo\n" + "2 - Ensayo\n"+"3 - Artículo para revista / periódico\n");
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Ingrese números en tipo, área y folio.");
		}
			}
			}}}
		
	/**
	 * Se ingresa el folio del escrito que se quiere eliminar y el objeto se elimina de la matriz y del archivo de texto.
	 */
	public class Eliminar implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if(btCargar.isEnabled()) {
				JOptionPane.showMessageDialog(null, "Primero se tienen que cargar los datos.");
			} else {
				int folio;
				boolean res;
				String f = txFolio.getText();
				folio = Integer.parseInt(f);
				try {
					res = t.eliminarEscrito(folio);
					if(res)
						txTerminal.setText("Baja exitosa.");
					else {
						txTerminal.setText("Falla en baja.");
					}
				} catch (IOException e) {
					System.out.println(e);
					e.printStackTrace();
					System.exit(-1);
				}
			}
			
		}
	}
	
	/**
	 * Se puede buscar un escritos según su tipo, área o procedencia.
	 * En el TexField a lado del de folio (txTerminal) se ingresa según qué se quiere buscar (tipo, área o procedencia)
	 * Y se pone el dato a buscar en el text field correspondiente
	 */
	public class Buscar implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if(btCargar.isEnabled()) {
				JOptionPane.showMessageDialog(null, "Primero se tienen que cargar los datos.");
			} else {
				String r,r1, r2, r3;
				String datos = ".";
				String [][] e = new String[100][13];
				int escritos = e.length;
				int  x;
				r = txTerminal.getText();
				if(r.equalsIgnoreCase("tipo")) {
					r1 = txTipo.getText();
					x = Integer.parseInt(r1);
					e = buscaEscritosTipo(x); 
				} else if (r.equalsIgnoreCase("area") || r.equalsIgnoreCase("área")) {
					r2 = txArea.getText();
					x = Integer.parseInt(r2);
					e = buscaEscritosArea(x);
				} else if (r.equalsIgnoreCase("procedencia")) {
					r3 = txProcedencia.getText();
					e = buscaEscritosProcedencia(r3);
				} else {
					JOptionPane.showMessageDialog(null, "En la caja arriba de ENTER ingresa según qué quieres buscar:\ntipo\nárea\nprocedencia");
				} 
				
				if(e==null || e[0][0].equals("no")) {
					JOptionPane.showMessageDialog(null, "No hay escritos que concuerden con la búsqueda.\nVerifique que haya ingresado la información correcta.");
				} else {
					DefaultTableModel model = (DefaultTableModel)table1.getModel();
					model.setRowCount(0);
					for(int i=0; i<escritos; i++) {
						model.addRow(new Object [] {e[i][0], e[i][1], e[i][2], e[i][3], e[i][4], e[i][5], e[i][6], e[i][7], e[i][8], 
								e[i][9], e[i][10], e[i][11], e[i][12]} );
					}
				}
			}
			
		}
		
		
		public String[][] buscaEscritosTipo(int tipo) {
			String res="-";
			StringBuilder sb = new StringBuilder();
			int[] conteoTipo = {t.totalXTipo(1), t.totalXTipo(2), t.totalXTipo(3)};
			int ct = 0; 
				if(tipo==1)
					ct = conteoTipo[0];
				else if(tipo==2)
					ct = conteoTipo[1];
				else if(tipo==3)
					ct = conteoTipo[2];
				else
					res="Ingresa un tipo de dato válido.";
				

				String[][] datos = new String[ct][13];
				
				for(int i=0; i<ct; i++) {
					datos[i][0]=Integer.toString(t.getElemDatos(tipo-1, i).getTipo());
					datos[i][1]=Integer.toString(t.getElemDatos(tipo-1, i).getArea());
					datos[i][2]=Integer.toString(t.getElemDatos(tipo-1, i).getFolio());
					datos[i][3]=t.getElemDatos(tipo-1, i).getTitulo();
					datos[i][4]=t.getElemDatos(tipo-1, i).getDestinatario();
					datos[i][5]=t.getElemDatos(tipo-1, i).getPersona(0);
					datos[i][6]=t.getElemDatos(tipo-1, i).getFecha(0);
					datos[i][7]=t.getElemDatos(tipo-1, i).getDireccion();
					datos[i][8]=t.getElemDatos(tipo-1, i).getPersona(1);
					datos[i][9]=t.getElemDatos(tipo-1, i).getPersona(2);
					datos[i][10]=t.getElemDatos(tipo-1, i).getPersona(3);
					datos[i][11]=t.getElemDatos(tipo-1, i).getFecha(1);
					if(t.getElemDatos(tipo-1, i).isTerminado())
						datos[i][12]="si";
					else
						datos[i][12]="no";
				}
				
					
				res = sb.toString();
			
			return datos;
		}
		
		public String[][] buscaEscritosArea(int area) {
			int conteoArea = t.totalXArea(area);
			int[][] indices = new int[conteoArea][2]; //guardar los indices en donde están lo Escritos de esa área
			int[] conteoTipo = {t.totalXTipo(1), t.totalXTipo(2), t.totalXTipo(3)};
			int cont=0;
	
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
			String[][] datos = new String[conteoArea][13];
			for(int k=0; k<conteoArea; k++) {
				datos[k][0]=Integer.toString(t.getElemDatos(indices[k][0], indices[k][1]).getTipo());
				datos[k][1]=Integer.toString(t.getElemDatos(indices[k][0], indices[k][1]).getArea());
				datos[k][2]=Integer.toString(t.getElemDatos(indices[k][0], indices[k][1]).getFolio());
				datos[k][3]=t.getElemDatos(indices[k][0], indices[k][1]).getTitulo();
				datos[k][4]=t.getElemDatos(indices[k][0], indices[k][1]).getDestinatario();
				datos[k][5]=t.getElemDatos(indices[k][0], indices[k][1]).getPersona(0);
				datos[k][6]=t.getElemDatos(indices[k][0], indices[k][1]).getFecha(0);
				datos[k][7]=t.getElemDatos(indices[k][0], indices[k][1]).getDireccion();
				datos[k][8]=t.getElemDatos(indices[k][0], indices[k][1]).getPersona(1);
				datos[k][9]=t.getElemDatos(indices[k][0], indices[k][1]).getPersona(2);
				datos[k][10]=t.getElemDatos(indices[k][0], indices[k][1]).getPersona(3);
				datos[k][11]=t.getElemDatos(indices[k][0], indices[k][1]).getFecha(1);
				if(t.getElemDatos(indices[k][0], indices[k][1]).isTerminado())
					datos[k][12]="si";
				else
					datos[k][12]="no";
			}
			
			return datos;
		}
		
		public String[][] buscaEscritosProcedencia(String procedencia) {
			int[] conteoTipo = {t.totalXTipo(1), t.totalXTipo(2), t.totalXTipo(3)};
			String[][] datos;
			ArrayList<Integer> l = new ArrayList<>();
			int cont=0;
			int conteo = 0;
			int i=0;
			while(i<3 && conteoTipo[i]!=0) {
				for(int j=0; j<conteoTipo[i]; j++) {
					if((t.getElemDatos(i, j).getPersona(0)).trim().equalsIgnoreCase(procedencia.trim())) {
						cont++;
						l.add(i);
						l.add(j);
					}
				}
				i++;
			}
			datos = new String[cont][13];
			if(cont!=0) {
				for(int k=0; k<cont; k++) {
					datos[k][0]=Integer.toString(t.getElemDatos(l.get(conteo), l.get(conteo+1)).getTipo());
					datos[k][1]=Integer.toString(t.getElemDatos(l.get(conteo), l.get(conteo+1)).getArea());
					datos[k][2]=Integer.toString(t.getElemDatos(l.get(conteo), l.get(conteo+1)).getFolio());
					datos[k][3]=t.getElemDatos(l.get(conteo), l.get(conteo+1)).getTitulo();
					datos[k][4]=t.getElemDatos(l.get(conteo), l.get(conteo+1)).getDestinatario();
					datos[k][5]=t.getElemDatos(l.get(conteo), l.get(conteo+1)).getPersona(0);
					datos[k][6]=t.getElemDatos(l.get(conteo), l.get(conteo+1)).getFecha(0);
					datos[k][7]=t.getElemDatos(l.get(conteo), l.get(conteo+1)).getDireccion();
					datos[k][8]=t.getElemDatos(l.get(conteo), l.get(conteo+1)).getPersona(1);
					datos[k][9]=t.getElemDatos(l.get(conteo), l.get(conteo+1)).getPersona(2);
					datos[k][10]=t.getElemDatos(l.get(conteo), l.get(conteo+1)).getPersona(3);
					datos[k][11]=t.getElemDatos(l.get(conteo), l.get(conteo+1)).getFecha(1);
					if(t.getElemDatos(l.get(conteo), l.get(conteo+1)).isTerminado())
						datos[k][12]="si";
					else
						datos[k][12]="no";
					conteo+=2;
				}	
			} else {
				datos = null;
			}
			return datos;
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
			txFolio.setText("");
		}
	}
	
	/**
	 * Muestra los datos de la matriz en la tabla. Sirve para actualizar la tabla.
	 */
	public class DisplayDatos implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(btCargar.isEnabled()) {
				JOptionPane.showMessageDialog(null, "Primero se tienen que cargar los datos.");
			} else {
				DefaultTableModel model = (DefaultTableModel)table1.getModel();
				model.setRowCount(0);
				int escritos = t.datosTabla().length;
				String[][] e = t.datosTabla(); 
				if(e == null) {
					lbEscritos.setText("No hay escritos registrados.");
				} else {
					for(int i=0; i<escritos; i++) {
						model.addRow(new Object [] {e[i][0], e[i][1], e[i][2], e[i][3], e[i][4], e[i][5], e[i][6], e[i][7], e[i][8], e[i][9], e[i][10], e[i][11], e[i][12]} );
					}
					
					lbEscritos.setText("Hay "+t.totalEscritos()+" escritos.");
				}
			}
			
		}
	}
	
	/**
	 *Agregar los elementos existentes en el archivo de texto a la matriz de Trabajo.
	 *Crea el archivo para guardar info de los escritos.
	 */
	public class CargarDatos implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			File e = new File("escritos.txt");
			  try {
				if (e.createNewFile()) {
				    //System.out.println("Se creo el archivo: " + e.getName());
					JOptionPane.showMessageDialog(null, "Nueva Base de Datos creada.");
				  } else {
					  JOptionPane.showMessageDialog(null, "Base de Datos abierta. Bienvenid@.");
				  }
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scanner archivo;
			String [] s = new String[12];//Lee el el archivo y llena un arreglo con la info de un escrito para instanciar usando la clase Trabajo
			int tipo, area;
			boolean res;
			boolean linea;
			try {
				archivo = new Scanner(new File("escritos.txt"));
				String line;
				if(e.length() == 0) {
					lbEscritos.setText("No hay escritos registrados.");
					btCargar.setEnabled(false);
				} else {
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
						
						
						lbEscritos.setText("Hay "+t.totalEscritos()+" escritos.");
					}
				}
				
			} catch(FileNotFoundException fnfe) {
				System.out.println(fnfe);
				System.exit(-1);
			}			
		}
	}
	
	/**
	 * Le da información al usuario sobre cómo usar el programa.
	 */
	public class Informacion implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			JOptionPane.showMessageDialog(null, "•Darle click al botón de cargar para iniciar.\n"+
	"•Para ver los escritos registrados darle click a ◎" +
	"•Tipo tiene que ser:\n1.- Amparo\n" + "2.- Ensayo\n"+"3.- Artículo para revista/periódico\n\n"+
	"•Área tiene que ser:\n1.- Corrupción\n2.- Constitucional\n3.- Electoral\n4.- Medio Ambiente\n5.- Penal\n6.- Finanzas\n7.- Política Pública"
	+ "\n8.- Salud\n\n"+
	"•Para editar un escrito colocar su folio y darle a enter, luego modificar los datos necesarios y darle a editar.\n"+
	"•Cuando un dato no sea relevante al escrito por registrar solo poner '.'");
		}
	}
	
	public static void main(String[] args) {
		ControladorVistaBD Final = new ControladorVistaBD("Base de Datos");
	}

}
