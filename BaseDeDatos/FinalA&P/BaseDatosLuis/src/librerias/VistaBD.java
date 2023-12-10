package librerias;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * @author Mariel Sofía Gutiérrez Zapién
 * @version 2.0
 * Diciembre 2020
 */
public class VistaBD extends JFrame {

	protected JPanel contentPane;
	protected JTextField txTipo, txTitulo, txArea, txDestinatario, txProcedencia, txFechaEntrega,txFechaPub, txRepresentando,txContacto, 
	txApoyo, txDireccion, txTerminado, txFolio,txTerminal;
	protected JLabel lblFolio, lbEscritos, lbProcedencia, lbFechaEntrega, lbFechaPub, lbRepresentandoA, lbTipo, lbTitulo, lbArea, 
	lbDestinatario, lbContacto, lbApoyadoPor, lbDireccion, lbAsuntoconcluido;
	protected JButton btAlta, btEditar, btEliminar, btBuscar, btClear, btEnter, btDuda, btCargar, btDisplay;
	protected JScrollPane scrollPane;
	protected JTable table1;
	private JFrame frmBaseDeDatos;
	DefaultTableModel model;
	
	public VistaBD(String titulo) {
		super(titulo);
		
		frmBaseDeDatos = new JFrame();
		frmBaseDeDatos.setIconImage(Toolkit.getDefaultToolkit().getImage(VistaBD.class.getResource("/img/logoBD.png")));
		frmBaseDeDatos.setTitle("Base de Datos");
		
		//especificaciones de botones, text fields y labels.
		lbTipo = new JLabel("Tipo:");
		lbTipo.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbTipo.setBounds(100, 24, 65, 27);
		frmBaseDeDatos.getContentPane().add(lbTipo);
		
		txTipo = new JTextField();
		txTipo.setHorizontalAlignment(SwingConstants.CENTER);
		txTipo.setBounds(163, 25, 53, 27);
		frmBaseDeDatos.getContentPane().add(txTipo);
		txTipo.setColumns(10);
		
		lbTitulo = new JLabel("Título:");
		lbTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbTitulo.setBounds(262, 24, 82, 27);
		frmBaseDeDatos.getContentPane().add(lbTitulo);
		
		txTitulo = new JTextField();
		txTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txTitulo.setColumns(10);
		txTitulo.setBounds(336, 24, 321, 27);
		frmBaseDeDatos.getContentPane().add(txTitulo);
		
		lbArea = new JLabel("Área:");
		lbArea.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbArea.setBounds(703, 23, 65, 27);
		frmBaseDeDatos.getContentPane().add(lbArea);
		
		txArea = new JTextField();
		txArea.setHorizontalAlignment(SwingConstants.CENTER);
		txArea.setColumns(10);
		txArea.setBounds(766, 24, 53, 27);
		frmBaseDeDatos.getContentPane().add(txArea);
		
		lbDestinatario = new JLabel("Presentado ante:");
		lbDestinatario.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbDestinatario.setBounds(101, 76, 195, 27);
		frmBaseDeDatos.getContentPane().add(lbDestinatario);
		
		txDestinatario = new JTextField();
		txDestinatario.setHorizontalAlignment(SwingConstants.CENTER);
		txDestinatario.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txDestinatario.setColumns(10);
		txDestinatario.setBounds(280, 78, 183, 27);
		frmBaseDeDatos.getContentPane().add(txDestinatario);
		
		lbProcedencia = new JLabel("Procedencia: ");
		lbProcedencia.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbProcedencia.setBounds(498, 76, 148, 27);
		frmBaseDeDatos.getContentPane().add(lbProcedencia);
		
		txProcedencia = new JTextField();
		txProcedencia.setHorizontalAlignment(SwingConstants.CENTER);
		txProcedencia.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txProcedencia.setColumns(10);
		txProcedencia.setBounds(637, 78, 183, 27);
		frmBaseDeDatos.getContentPane().add(txProcedencia);
		
		txFechaEntrega = new JTextField();
		txFechaEntrega.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txFechaEntrega.setHorizontalAlignment(SwingConstants.CENTER);
		txFechaEntrega.setColumns(10);
		txFechaEntrega.setBounds(262, 126, 156, 31);
		frmBaseDeDatos.getContentPane().add(txFechaEntrega);
		
		lbFechaEntrega = new JLabel("Fecha Entrega:");
		lbFechaEntrega.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbFechaEntrega.setBounds(100, 126, 156, 27);
		frmBaseDeDatos.getContentPane().add(lbFechaEntrega);
		
		txFechaPub = new JTextField();
		txFechaPub.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txFechaPub.setHorizontalAlignment(SwingConstants.CENTER);
		txFechaPub.setColumns(10);
		txFechaPub.setBounds(664, 124, 156, 31);
		frmBaseDeDatos.getContentPane().add(txFechaPub);
		
		lbFechaPub = new JLabel("Fecha Publicación:");
		lbFechaPub.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbFechaPub.setBounds(464, 124, 202, 27);
		frmBaseDeDatos.getContentPane().add(lbFechaPub);
		
		lbRepresentandoA = new JLabel("Representando a:");
		lbRepresentandoA.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbRepresentandoA.setBounds(100, 179, 195, 27);
		frmBaseDeDatos.getContentPane().add(lbRepresentandoA);
		
		txRepresentando = new JTextField();
		txRepresentando.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txRepresentando.setHorizontalAlignment(SwingConstants.CENTER);
		txRepresentando.setColumns(10);
		txRepresentando.setBounds(291, 179, 188, 29);
		frmBaseDeDatos.getContentPane().add(txRepresentando);
		
		lbContacto = new JLabel("contacto de:");
		lbContacto.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbContacto.setBounds(511, 179, 195, 27);
		frmBaseDeDatos.getContentPane().add(lbContacto);
		
		txContacto = new JTextField();
		txContacto.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txContacto.setHorizontalAlignment(SwingConstants.CENTER);
		txContacto.setColumns(10);
		txContacto.setBounds(650, 180, 169, 29);
		frmBaseDeDatos.getContentPane().add(txContacto);
		
		lbApoyadoPor = new JLabel("Apoyado por:");
		lbApoyadoPor.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbApoyadoPor.setBounds(100, 231, 195, 27);
		frmBaseDeDatos.getContentPane().add(lbApoyadoPor);
		
		txApoyo = new JTextField();
		txApoyo.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txApoyo.setHorizontalAlignment(SwingConstants.CENTER);
		txApoyo.setColumns(10);
		txApoyo.setBounds(254, 231, 164, 32);
		frmBaseDeDatos.getContentPane().add(txApoyo);
		
		lbDireccion = new JLabel("Guardado en: ");
		lbDireccion.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbDireccion.setBounds(463, 231, 195, 27);
		frmBaseDeDatos.getContentPane().add(lbDireccion);
		
		txDireccion = new JTextField();
		txDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		txDireccion.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txDireccion.setColumns(10);
		txDireccion.setBounds(617, 231, 202, 32);
		frmBaseDeDatos.getContentPane().add(txDireccion);
		
		lbAsuntoconcluido = new JLabel("Asunto Concluido:");
		lbAsuntoconcluido.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lbAsuntoconcluido.setBounds(100, 283, 215, 27);
		frmBaseDeDatos.getContentPane().add(lbAsuntoconcluido);
		
		txTerminado = new JTextField();
		txTerminado.setForeground(Color.BLACK);
		txTerminado.setToolTipText("Ingresar si o no");
		txTerminado.setHorizontalAlignment(SwingConstants.CENTER);
		txTerminado.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txTerminado.setColumns(10);
		txTerminado.setBounds(302, 283, 164, 32);
		frmBaseDeDatos.getContentPane().add(txTerminado);
		
		lblFolio = new JLabel("Folio:");
		lblFolio.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblFolio.setBounds(467, 284, 65, 27);
		frmBaseDeDatos.getContentPane().add(lblFolio);
		
		txFolio = new JTextField();
		txFolio.setHorizontalAlignment(SwingConstants.CENTER);
		txFolio.setColumns(10);
		txFolio.setBounds(530, 285, 82, 31);
		frmBaseDeDatos.getContentPane().add(txFolio);
		
		
		lbEscritos = new JLabel("Escritos");
		lbEscritos.setForeground(Color.LIGHT_GRAY);
		lbEscritos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lbEscritos.setBounds(769, 353, 112, 51);
		frmBaseDeDatos.getContentPane().add(lbEscritos);
		
		btAlta = new JButton("Alta");
		btAlta.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btAlta.setBounds(16, 330, 128, 38);
		frmBaseDeDatos.getContentPane().add(btAlta);
		
		btEditar = new JButton("Editar");
		btEditar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btEditar.setBounds(156, 330, 128, 38);
		frmBaseDeDatos.getContentPane().add(btEditar);
		
		btEliminar = new JButton("Eliminar");
		btEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btEliminar.setBounds(300, 330, 128, 38);
		frmBaseDeDatos.getContentPane().add(btEliminar);
		
		btBuscar = new JButton("Buscar");
		btBuscar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btBuscar.setBounds(440, 330, 128, 38);
		frmBaseDeDatos.getContentPane().add(btBuscar);
		
		btClear = new JButton("Clear");
		btClear.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btClear.setBounds(580, 330, 128, 38);
		frmBaseDeDatos.getContentPane().add(btClear);
		
		btEnter = new JButton("Enter");
		btEnter.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btEnter.setBounds(720, 330, 174, 38);
		frmBaseDeDatos.getContentPane().add(btEnter);
		
		btDuda = new JButton("?");
		btDuda.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btDuda.setBounds(852, 6, 42, 38);
		frmBaseDeDatos.getContentPane().add(btDuda);
		
		btCargar = new JButton("Cargar");
		btCargar.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btCargar.setBounds(6, 14, 82, 27);
		frmBaseDeDatos.getContentPane().add(btCargar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 393, 860, 320);
		frmBaseDeDatos.getContentPane().add(scrollPane);
		
		//De la tabla
		table1 = new JTable();
		model = new DefaultTableModel();
		Object[] column = {"Tipo", "Área", "Folio", "Titulo","Destinatario",
				"Procedencia", "Entregado","Dirección", "Representando a", "Contacto","Apoyo", "Fecha publicación", "Concluido"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		table1.setModel(model);
		scrollPane.setViewportView(table1);
		
		txTerminal = new JTextField();
		txTerminal.setHorizontalAlignment(SwingConstants.CENTER);
		txTerminal.setColumns(10);
		txTerminal.setBounds(677, 287, 142, 31);
		frmBaseDeDatos.getContentPane().add(txTerminal);
			
		btDisplay = new JButton("◎");
		btDisplay.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btDisplay.setBounds(841, 176, 53, 38);
		frmBaseDeDatos.getContentPane().add(btDisplay);
	
		//Cuestiones del frame	
		frmBaseDeDatos.getContentPane().setForeground(Color.BLACK);
		frmBaseDeDatos.getContentPane().setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		frmBaseDeDatos.getContentPane().setBackground(Color.WHITE);
		frmBaseDeDatos.getContentPane().setLayout(null);
			
		frmBaseDeDatos.setBounds(65,50, 900,741);
		frmBaseDeDatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBaseDeDatos.setVisible(true);
		//frmBaseDeDatos.setIconImage(createImage("/BaseDatosLuis/src/img/logoBD.png").getImage());
	}
	/*
	public ImageIcon createImage(String path) {
		return new ImageIcon(java.awt.Toolkit.getDefaultToolkit().getClass().getResource(path));
	}
	*/
	public static void main(String[] args) {
		VistaBD prueba = new VistaBD("Base de Datos");
		
	}
}
