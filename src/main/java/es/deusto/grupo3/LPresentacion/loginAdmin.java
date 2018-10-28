package es.deusto.grupo3.LPresentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class loginAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginAdmin frame = new loginAdmin();
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
	public loginAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginAdministrador = new JLabel("Login Administrador");
		lblLoginAdministrador.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoginAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginAdministrador.setBounds(132, 27, 183, 35);
		contentPane.add(lblLoginAdministrador);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(23, 216, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(293, 216, 89, 23);
		contentPane.add(btnEntrar);
		
		textField = new JTextField();
		textField.setBounds(168, 89, 122, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(88, 92, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(71, 131, 76, 14);
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 128, 122, 17);
		contentPane.add(passwordField);
	}
}
