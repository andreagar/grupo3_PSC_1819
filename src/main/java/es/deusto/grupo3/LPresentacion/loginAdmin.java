package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

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

public class loginAdmin extends JFrame implements ActionListener{
	
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

	public loginAdmin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(loginAdmin.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setResizable(false);
		setTitle("HyraCar: Log In Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 393);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(Toolkit.getDefaultToolkit().getImage(loginAdmin.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
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
		
		lblNewLabel = new JLabel("ID admin");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblNewLabel.setBounds(130, 48, 184, 20);
		contentPane.add(lblNewLabel);
		
		lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblContrasena.setBounds(130, 142, 184, 32);
		contentPane.add(lblContrasena);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnAceptar.setBounds(50, 270, 150, 44);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 15));
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
			
			if(nombre.equals("admin") && contrasenya.equals("admin")){
				dispose();
				menuAdmin frameMenu = new menuAdmin();
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