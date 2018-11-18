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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class menuAdmin extends JFrame implements ActionListener{

	private PanelConImagen contentPane;
	private JButton btnCoche;
	private JButton btnRegistrarMoto;
	private JButton btnCerrar;
	private JLabel lblHyracarAdministracion;
	
	/**
	 * Create the frame.
	 */
	public menuAdmin() {
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setTitle("HyraCar: Administración");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 313);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHyracarAdministracion = new JLabel("HyraCar: Administración");
		lblHyracarAdministracion.setFont(new Font("Verdana", Font.BOLD, 20));
		lblHyracarAdministracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblHyracarAdministracion.setBounds(73, 25, 288, 40);
		contentPane.add(lblHyracarAdministracion);
		
		btnCoche = new JButton("Coche");
		btnCoche.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnCoche.setBounds(51, 97, 173, 51);
		contentPane.add(btnCoche);
		btnCoche.addActionListener(this);
		
		btnRegistrarMoto = new JButton("Moto");
		btnRegistrarMoto.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnRegistrarMoto.setBounds(256, 97, 150, 51);
		contentPane.add(btnRegistrarMoto);
		btnRegistrarMoto.addActionListener(this);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnCerrar.setBounds(21, 236, 89, 23);
		contentPane.add(btnCerrar);
		btnCerrar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnCoche){
			dispose();
			adminCoche menuCoche = new adminCoche();
			menuCoche.setVisible(true);
		}
		if (e.getSource() == btnRegistrarMoto){
			adminMoto vistaMoto = new adminMoto();
			vistaMoto.setVisible(true);
			dispose();
		}
		if (e.getSource() == btnCerrar){
			dispose();
			vistaPrincipal vista = new vistaPrincipal();
			vista.setVisible(true);
		}
		
	}
}
