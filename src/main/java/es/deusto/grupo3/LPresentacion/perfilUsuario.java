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

public class perfilUsuario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPasswordField passwordOld;
	private JPasswordField passwordField;
	private JLabel lblContraseaActual;
	private JLabel lblNewPassword;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					perfilUsuario frame = new perfilUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public perfilUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblContraseaActual = new JLabel("Contraseña actual");
		lblContraseaActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseaActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContraseaActual.setBounds(129, 30, 148, 22);
		contentPane.add(lblContraseaActual);
		
		passwordOld = new JPasswordField();
		passwordOld.setBounds(140, 70, 131, 20);
		contentPane.add(passwordOld);
		
		JLabel lblNewPassword = new JLabel("Contraseña nueva");
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPassword.setBounds(129, 112, 148, 22);
		contentPane.add(lblNewPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 145, 131, 20);
		contentPane.add(passwordField);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 228, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(335, 228, 89, 23);
		contentPane.add(btnGuardar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String nombre = textField.getText();
		char[] elChar = passwordField.getPassword();
		String contrasenya = String.valueOf(elChar);
		GestorUsuario gestor = new GestorUsuario(nombre, contrasenya);
		
		if (e.getSource() == btnGuardar){
			Statement st = BaseDeDatos.getStatement();
			boolean correcto = gestor.chequearYaEnTablaLOGIN(st, nombre, contrasenya);
			
			if(correcto == true){
				dispose();
				menuUsuario frameMenu = new menuUsuario(nombre);
				frameMenu.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "La contrasenya antigua no es la correcta","Mensaje de error",JOptionPane.ERROR_MESSAGE);
			}

		}
		
		if (e.getSource() == btnCancelar){
			dispose();
			vistaPrincipal frame = new vistaPrincipal();
			frame.setVisible(true);
		}
		
		
		
		
	}
}
