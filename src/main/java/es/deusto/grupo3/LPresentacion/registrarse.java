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

public class registrarse extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNombreDeUsuario;
	private JLabel lblContrasena;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public registrarse () {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 393);
		contentPane = new JPanel();
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
		lblNombreDeUsuario.setFont(new Font("Candara", Font.PLAIN, 25));
		lblNombreDeUsuario.setBounds(123, 50, 264, 20);
		contentPane.add(lblNombreDeUsuario);
		
		lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setFont(new Font("Candara", Font.PLAIN, 25));
		lblContrasena.setBounds(160, 145, 169, 32);
		contentPane.add(lblContrasena);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Candara", Font.BOLD, 18));
		btnAceptar.setBounds(50, 270, 150, 44);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Candara", Font.BOLD, 18));
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
			GestorUsuario usuario = new GestorUsuario(nombre, contrasenya);
			BaseDeDatos.crearTablaBDUsuario();	
		//Si no existe, añadir fila con el usuario nuevo y sus respectivos atributos
			usuario.anyadirFilaATablauUsuario(BaseDeDatos.getStatement(), nombre);	
			dispose();
		}
		
		if (e.getSource() == btnCancelar){
			dispose();
		}
		
	}

}
