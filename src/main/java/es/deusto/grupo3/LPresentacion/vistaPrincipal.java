package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		btnLogIn.setFont(new Font("Stencil", Font.BOLD, 18));
		btnLogIn.setBounds(10, 141, 438, 75);
		contentPane.add(btnLogIn);
		//btnLogIn.addActionListener(this);
		
		btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setFont(new Font("Stencil", Font.BOLD, 18));
		btnRegistrarse.setBounds(10, 227, 438, 75);
		contentPane.add(btnRegistrarse);
		btnRegistrarse.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("HyraCar");
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 24));
		lblNewLabel.setBounds(153, 50, 212, 41);
		contentPane.add(lblNewLabel);
		//btnRegistrarse.addActionListener(this);
		
		
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
		
//		if (e.getSource() == btnLogIn){
//			login logIn = new login();
//			logIn.setVisible(true);
//			dispose();
//			
//		}
		
	}

}
