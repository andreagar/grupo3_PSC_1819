package es.deusto.grupo3.LPresentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorUsuario;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class login extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelConImagen contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	private JLabel lblContrasena;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public login() {
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setResizable(false);
		setTitle("HyraCar: Log In");
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
		
		lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblNewLabel.setBounds(130, 59, 184, 20);
		contentPane.add(lblNewLabel);
		
		lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblContrasena.setBounds(130, 145, 184, 32);
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
		GestorUsuario gestor = new GestorUsuario();
			
		if (e.getSource() == btnAceptar){
			Statement st = BaseDeDatos.getStatement();
			boolean correcto = gestor.chequearYaEnTablaLOGIN(st, nombre, contrasenya);
			
			if(correcto == true){
				dispose();
				menuUsuario frameMenu = new menuUsuario(nombre);
				frameMenu.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "El nombre de usuario y la contrasenya no son correctas. Pruebe de nuevo.","Mensaje de error",JOptionPane.ERROR_MESSAGE);
			}

		}
		
		if (e.getSource() == btnCancelar){
			dispose();
			vistaPrincipal frame = new vistaPrincipal();
			frame.setVisible(true);
		}
			
		
	}

}
