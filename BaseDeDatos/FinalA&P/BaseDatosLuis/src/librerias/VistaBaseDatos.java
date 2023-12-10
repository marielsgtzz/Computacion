package librerias;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

/**
 * 
 * @author Mariel Sofía Gutiérrez Zapién
 * @version 1.0 Primera versión de la interfaz gráfica tal y como estábamos trabajandola en clase. Sin implementación de la tabla, se usa una text area.
 * Noviembre 2020.
 */
public class VistaBaseDatos extends JFrame {
	
	protected JTextField txTipo, txArea, txDestinatario, txTitulo, txDireccion, 
	txTerminado, txFechaEntrega, txFechaPub,txRepresentando, txProcedencia, txApoyo, txContacto, txTerminal;
	private JLabel lbTipo, lbArea, lbDestinatario, lbTitulo, lbDireccion, 
	lbTerminado, lbFechaEntrega, lbFechaPub,lbRepresentando, lbProcedencia, lbApoyo, lbContacto;
	protected JButton btAlta, btEliminar, btBuscar, btEditar, btClear, btCargar, btDisplay, btEnter;
	protected JPanel p, p2;
	protected JTextArea info1;
	protected JScrollPane infos2;
	
		
	public VistaBaseDatos() {
		super();
		txTipo = new JTextField(16);
		txArea = new JTextField(16);
		txDestinatario = new JTextField(30);
		txTitulo = new JTextField(70);
		txDireccion = new JTextField(40);
		txTerminado = new JTextField(16); //posiblemente convertir a seleccion de opcion
		txFechaEntrega = new JTextField(10);
		txFechaPub = new JTextField(10);
		txRepresentando = new JTextField(16);
		txProcedencia = new JTextField(25);
		txApoyo = new JTextField(30);
		txContacto = new JTextField(30);
		txTerminal = new JTextField(30);
		
		lbTipo = new JLabel("Tipo: ");
		lbArea = new JLabel("Área: ");
		lbDestinatario = new JLabel("Presentado ante: ");
		lbTitulo = new JLabel("TÍtulo: ");
		lbDireccion = new JLabel("Guardado en: ");
		lbTerminado = new JLabel("Terminado: ");
		lbFechaEntrega = new JLabel("Fecha de entrega: ");
		lbFechaPub = new JLabel("Fecha de publicación: ");
		lbRepresentando = new JLabel("Representando a: ");
		lbProcedencia = new JLabel("Procedencia: ");
		lbApoyo = new JLabel("Persona que apoyó: ");
		lbContacto = new JLabel("Contacto de representado: ");
		
		btAlta = new JButton("Alta");
		btEditar = new JButton("Editar");
		btEliminar = new JButton("Eliminar");
		btBuscar = new JButton("Buscar");
		btClear = new JButton("Clear");
		btCargar = new JButton("Cargar");
		btDisplay = new JButton("Datos");
		btEnter = new JButton("Enter");
		info1 = new JTextArea(150, 50);
		infos2 = new JScrollPane(info1);
		p = new JPanel();
		p.setLayout(new GridLayout(5, 8)); 

		add(p);
		
		p.add(lbTitulo);
		p.add(txTitulo);
		p.add(lbTipo);
		p.add(txTipo);	
		p.add(lbArea);
		p.add(txArea);
		p.add(lbDestinatario);
		p.add(txDestinatario);
		p.add(lbFechaEntrega);
		p.add(txFechaEntrega);
		p.add(lbRepresentando);
		p.add(txRepresentando);
		p.add(lbContacto);
		p.add(txContacto);
		p.add(lbProcedencia);
		p.add(txProcedencia);
		p.add(lbApoyo);
		p.add(txApoyo);
		p.add(lbFechaPub);
		p.add(txFechaPub);
		p.add(lbDireccion);
		p.add(txDireccion);
		p.add(lbTerminado);
		p.add(txTerminado);
		
		
		p.add(btAlta);
		p.add(btEditar);
		p.add(btEliminar);
		p.add(btBuscar);
		p.add(btClear);
		p.add(btCargar);
		p.add(btDisplay);
		
		p.add(infos2);

		p.add(txTerminal);
		p.add(btEnter);
		this.setBounds(65,50, 1500,741);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public static void main (String []args) {
		VistaBaseDatos vc = new VistaBaseDatos();
		
	}
	
}