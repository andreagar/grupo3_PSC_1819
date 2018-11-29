package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import es.deusto.grupo3.App;
import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorOpiniones;
import es.deusto.grupo3.LNegocio.Moto;

import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import org.apache.log4j.Logger;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class opinionMenu extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430978011913089956L;
	
	private final static Logger log = Logger.getLogger(App.class.getName());

	private PanelConImagen contentPane;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton seleccion;
	private String usuario;
	private int limCoche;
	private String cocheSelected;
	private DefaultListModel modeloCoche;
	private JLabel lblVehiculo;
	private JLabel lblPuntos;
	private JLabel lblReseaDelServicio;
	private DefaultListModel modeloAsig;
	private JList listVehiculo;
	private JLabel lblMatrcula;
	private JLabel lblComentario;
	private GestorOpiniones objVehiculo;
	private JComboBox comboBox;
	private JTextField txtMatricula;
	private int lim;
	
	
	/**
	 * Create the frame.
	 */
	public opinionMenu(String nombre) {
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage(asignarCoche.class.getResource("/es/deusto/grupo3/img/icon.png")));
				
		this.usuario=nombre;
		
		setTitle("HyraCar: Reseñas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 376);
		contentPane = new PanelConImagen();
		Toolkit toolkit = getToolkit();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblVehiculo = new JLabel("VEHÍCULOS:");
		lblVehiculo.setBounds(23, 11, 145, 28);
		lblVehiculo.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblVehiculo);
		
		listVehiculo = new JList();
		listVehiculo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listVehiculo.setBounds(23, 41, 130, 230);
		contentPane.add(listVehiculo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(525, 299, 95, 28);
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 13));
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand("Atras");
		
		seleccion = new JButton("Seleccionar");
		seleccion.setBounds(23, 282, 130, 29);
		seleccion.setFont(new Font("Verdana", Font.PLAIN, 11));
		contentPane.add(seleccion);
		seleccion.addActionListener(this);
		seleccion.setActionCommand("Seleccionar");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(391, 299, 95, 29);
		btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 13));
		contentPane.add(btnGuardar);
		btnGuardar.addActionListener(this);
		btnGuardar.setActionCommand("COMPRAR");
		
		
		lblReseaDelServicio = new JLabel("Reseña del vehículo de HyraCar");
		lblReseaDelServicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblReseaDelServicio.setFont(new Font("Verdana", Font.BOLD, 20));
		lblReseaDelServicio.setBounds(178, 22, 442, 28);
		contentPane.add(lblReseaDelServicio);
		
		lblPuntos = new JLabel("Puntuación:");
		lblPuntos.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblPuntos.setBounds(284, 112, 100, 28);
		contentPane.add(lblPuntos);
		
		lblMatrcula = new JLabel("Matrícula:");
		lblMatrcula.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblMatrcula.setBounds(284, 73, 100, 28);
		contentPane.add(lblMatrcula);
		
		lblComentario = new JLabel("Comentario:");
		lblComentario.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblComentario.setBounds(284, 149, 100, 28);
		contentPane.add(lblComentario);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setSelectedIndex(4);
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 14));
		comboBox.setBounds(391, 112, 38, 26);
		contentPane.add(comboBox);
		
		txtMatricula = new JTextField();
		txtMatricula.setEnabled(false);
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(391, 79, 154, 20);
		contentPane.add(txtMatricula);
		
		txtMatricula.setEnabled(false);
		
		objVehiculo=new GestorOpiniones();
		this.CargarLista(objVehiculo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnCancelar){
			menuUsuario menu2 = new menuUsuario(this.usuario);
			menu2.setVisible(true);
			dispose();
		}
		
		if (e.getSource() == btnGuardar){
			//añadir lo de guardar
			menuUsuario menu1 = new menuUsuario(this.usuario);
			menu1.setVisible(true);
			dispose();
		}
		
		if (e.getSource() == seleccion){
			this.MostrarDetalles();
		}
	}
	
	
	public void CargarLista(GestorOpiniones vehiculo){
		
		modeloAsig = new DefaultListModel();
		
		Statement st = BaseDeDatos.getStatement();
	
		for (String asig : vehiculo.getUsuarioMatriculas(st, usuario) ){
			modeloAsig.addElement( asig.toString() );
		}
	
		listVehiculo.setModel( modeloAsig );
	
	}


	public void MostrarDetalles(){
		
		lim = listVehiculo.getSelectedIndex();
		
		if(lim!=-1){
			String mat = (String)listVehiculo.getSelectedValue();
			txtMatricula.setText(mat);
		}else{
			JOptionPane.showMessageDialog(null,"Seleccion incorrecta (debes selccionar al menos un elemento).", 
					"Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
	}	
}
