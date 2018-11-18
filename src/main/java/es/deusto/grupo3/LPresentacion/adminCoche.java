package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class adminCoche extends JFrame implements ActionListener{
	
	private PanelConImagen contentPane;
	private JButton btnRegistrarCoche;
	private JButton btnModificarCoche;
	private JButton btnAtras;
	private JLabel lblHyracarAdministracion;

	public adminCoche() {
		
		setTitle("HyraCar: Administración");
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 314);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHyracarAdministracion = new JLabel("Administración: Coche");
		lblHyracarAdministracion.setFont(new Font("Verdana", Font.BOLD, 20));
		lblHyracarAdministracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblHyracarAdministracion.setBounds(51, 25, 355, 40);
		contentPane.add(lblHyracarAdministracion);
		
		btnRegistrarCoche = new JButton("Registrar coche");
		btnRegistrarCoche.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnRegistrarCoche.setBounds(51, 97, 173, 51);
		contentPane.add(btnRegistrarCoche);
		btnRegistrarCoche.addActionListener(this);
		
		btnModificarCoche = new JButton("Modificar Coche");
		btnModificarCoche.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnModificarCoche.setBounds(256, 97, 161, 51);
		contentPane.add(btnModificarCoche);
		btnModificarCoche.addActionListener(this);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAtras.setBounds(10, 242, 89, 23);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnRegistrarCoche){
			
			registrarCoche vistaCoche = new registrarCoche();
			vistaCoche.setVisible(true);
			dispose();
		}
		if (e.getSource() == btnModificarCoche){
			
			modificarCoche cocheMod = new modificarCoche();
			cocheMod.setVisible(true);
			dispose();
		}
		if (e.getSource() == btnAtras){
			dispose();
			menuAdmin vista = new menuAdmin();
			vista.setVisible(true);
		}
		
	}
}
