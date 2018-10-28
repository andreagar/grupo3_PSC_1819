package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.deusto.grupo3.LDatos.BaseDeDatos;

public class vistaPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLogIn;
	private JButton btnRegistrarse;
	private JButton btnSalir;
	private JButton btnAdministrador;
	
	public vistaPrincipal()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnLogIn = new JButton("LOG IN");
		btnLogIn.setFont(new Font("Candara", Font.BOLD, 18));
		btnLogIn.setBounds(140, 130, 200, 75);
		contentPane.add(btnLogIn);
		btnLogIn.addActionListener(this);
		
		btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setFont(new Font("Candara", Font.BOLD, 18));
		btnRegistrarse.setBounds(140, 227, 200, 75);
		contentPane.add(btnRegistrarse);
		btnRegistrarse.addActionListener(this);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Candara", Font.BOLD, 12));
		btnSalir.setBounds(10, 430, 120, 27);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Alquiler de coches HyraCar", SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 24));
		lblNewLabel.setBounds(80, 50, 300, 41);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		btnAdministrador = new JButton("Administrador");
		btnAdministrador.setBounds(300, 316, 120, 27);
		contentPane.add(btnAdministrador);
		btnAdministrador.addActionListener(this);
	
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				BaseDeDatos.close();
				
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnRegistrarse){
			registrarse registrarse = new registrarse();
			registrarse.setVisible(true);
			
		}
		
		if (e.getSource() == btnLogIn){
			login logIn = new login();
			logIn.setVisible(true);
			dispose();
			
		}
		
		if (e.getSource() == btnAdministrador){
			loginAdmin logInAdmin = new loginAdmin();
			logInAdmin.setVisible(true);
			dispose();
			
		}
		
		if (e.getSource() == btnSalir){
			try {
				BaseDeDatos.close();
				BaseDeDatos.getStatement().close();
				BaseDeDatos.getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
		}
	}
}
