package es.deusto.grupo3.LPresentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorMoto;
import es.deusto.grupo3.LNegocio.GestorOficina;
import es.deusto.grupo3.LNegocio.GestorUsuario;
import es.deusto.grupo3.LNegocio.Moto;
import es.deusto.grupo3.LNegocio.Oficina;

import java.awt.Toolkit;

public class registrarOficina extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelConImagen contentPane;
	private JTextField txtId;
	private JTextField textNombre;
	private JTextField textCiudad;
	private JTextField textPais;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblRegistrarCocheNuevo;
	private JLabel lblId;
	private JLabel lblNombre;
	private JLabel lblCiudad;
	private JLabel lblPais;
	
	
	
	/**
	 * Create the frame.
	 */
	public registrarOficina() {
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setTitle("HyraCar: registrar oficina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 361);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnCancelar.setBounds(10, 289, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(this);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAceptar.setBounds(331, 289, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);
		
		lblRegistrarCocheNuevo = new JLabel("Registrar nueva oficina");
		lblRegistrarCocheNuevo.setFont(new Font("Verdana", Font.BOLD, 18));
		lblRegistrarCocheNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarCocheNuevo.setBounds(66, 11, 286, 36);
		contentPane.add(lblRegistrarCocheNuevo);
		
		txtId = new JTextField();
		txtId.setBounds(198, 75, 154, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		lblId = new JLabel("Identificador:");
		lblId.setFont(new Font("Candara", Font.PLAIN, 14));
		lblId.setBounds(97, 79, 100, 14);
		contentPane.add(lblId);
		
		textNombre = new JTextField();
		textNombre.setBounds(198, 115, 154, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Candara", Font.PLAIN, 14));
		lblNombre.setBounds(97, 119, 66, 14);
		contentPane.add(lblNombre);
		
		textCiudad = new JTextField();
		textCiudad.setBounds(198, 161, 154, 20);
		contentPane.add(textCiudad);
		textCiudad.setColumns(10);
		textCiudad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (textCiudad.getText().length()== 7) 
					e.consume();
		   }
		});
		
		lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setFont(new Font("Candara", Font.PLAIN, 14));
		lblCiudad.setBounds(97, 165, 66, 14);
		contentPane.add(lblCiudad);
		
		lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Candara", Font.PLAIN, 14));
		lblPais.setBounds(97, 209, 66, 14);
		contentPane.add(lblPais);
		
		textPais = new JTextField();
		textPais.setBounds(198, 205, 154, 21);
		contentPane.add(textPais);
		textPais.setColumns(10);

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnAceptar){
			this.anyadirOficina();
			dispose();
			menuAdmin vistaAdmin = new menuAdmin();
			vistaAdmin.setVisible(true);
		}
		
		if (e.getSource() == btnCancelar){
			dispose();
			menuAdmin vistaAdmin = new menuAdmin();
			vistaAdmin.setVisible(true);
		}
	}
	
	public void anyadirOficina(){
		int id = Integer.parseInt(txtId.getText());
		String nombre = textNombre.getText();
		String ciudad = textCiudad.getText();
		String pais = textPais.getText();
		
		Oficina oficina = new Oficina (id, nombre, ciudad, pais);

		GestorOficina gestorOf = new GestorOficina();
		//Si no existe, añadir fila con la oficina nueva y sus respectivos atributos
		gestorOf.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), oficina);
		
		gestorOf.GetArrayOficinas(BaseDeDatos.getStatement());
	}
}