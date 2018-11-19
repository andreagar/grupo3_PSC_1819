package es.deusto.grupo3.LPresentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorUsuario;
import es.deusto.grupo3.LNegocio.Usuario;
import java.awt.Toolkit;

public class perfilUsuario extends JFrame implements ActionListener {

	private PanelConImagen contentPane;
	private JPasswordField passwordOld;
	private JPasswordField passwordField;
	private JLabel lblContraseaActual;
	private JLabel lblNewPassword;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JTextField textField;
	
	private String nombre;

	/**
	 * Create the frame.
	 */
	public perfilUsuario(String nombre) {
		setTitle("HyraCar: modificar contraseña");
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		this.nombre = nombre;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblContraseaActual = new JLabel("Contraseña actual");
		lblContraseaActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseaActual.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblContraseaActual.setBounds(124, 31, 164, 22);
		contentPane.add(lblContraseaActual);
		
		passwordOld = new JPasswordField();
		passwordOld.setBounds(140, 70, 131, 20);
		contentPane.add(passwordOld);
		
		lblNewPassword = new JLabel("Contraseña nueva");
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewPassword.setBounds(129, 112, 148, 22);
		contentPane.add(lblNewPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 145, 131, 20);
		contentPane.add(passwordField);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnCancelar.setBounds(10, 228, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(this);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnGuardar.setBounds(335, 228, 89, 23);
		contentPane.add(btnGuardar);
		btnGuardar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		char[] elChar = passwordField.getPassword();
		String nueva = String.valueOf(elChar);
		
		char[] elChar2 = passwordOld.getPassword();
		String antigua = String.valueOf(elChar2);
		
		GestorUsuario gestor = new GestorUsuario(nombre, antigua);
		
		System.out.println(nombre);
		System.out.println(antigua);
		System.out.println(nueva);
		
		//llanar al gestor para que me devuelva el nombre del usuario
		//crear dos variables para guardar las dos contraseñas
		
		if (e.getSource() == btnGuardar){
			Statement st = BaseDeDatos.getStatement();
			boolean correcto = gestor.chequearYaEnTablaLOGIN(st, nombre, antigua);
			
			if(correcto == true){
				dispose();
				boolean cambio = gestor.cambiarContrasenya(st, nombre, nueva);
				if (cambio == true){
					menuUsuario frameMenu = new menuUsuario(nombre);
					frameMenu.setVisible(true);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "La contrasenya antigua no es la correcta","Mensaje de error",JOptionPane.ERROR_MESSAGE);
			}

		}
		
		if (e.getSource() == btnCancelar){
			dispose();
			menuUsuario frameMenu = new menuUsuario(nombre);
			frameMenu.setVisible(true);
		}
		
		
		
		
	}
}
