package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorUsuario;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class registrarse extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelConImagen contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNombreDeUsuario;
	private JLabel lblContrasena;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public registrarse () {
		setTitle("HyraCar: Log In");
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 393);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(130, 90, 184, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(130, 185, 184, 32);
		contentPane.add(passwordField);
		
		lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblNombreDeUsuario.setBounds(97, 50, 264, 20);
		contentPane.add(lblNombreDeUsuario);
		
		lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblContrasena.setBounds(130, 142, 184, 32);
		contentPane.add(lblContrasena);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnAceptar.setBounds(50, 270, 150, 44);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnCancelar.setBounds(250, 270, 150, 44);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String nombre = textField.getText();
		char[] elChar = passwordField.getPassword();
		String contrasenya = String.valueOf(elChar);						
		
		if (e.getSource() == btnAceptar){
			GestorUsuario usuario = new GestorUsuario();
			BaseDeDatos.crearTablaBDUsuario();	
		//Si no existe, añadir fila con el usuario nuevo y sus respectivos atributos
			usuario.anyadirFilaATablauUsuario(BaseDeDatos.getStatement(), nombre, contrasenya);	
			dispose();
		}
		
		if (e.getSource() == btnCancelar){
			dispose();
		}
		
	}

}
