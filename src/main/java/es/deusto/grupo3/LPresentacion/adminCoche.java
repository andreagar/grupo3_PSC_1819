package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class adminCoche extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JButton btnRegistrarCoche;
	private JButton btnModificarCoche;
	private JButton btnAtras;

	public adminCoche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHyracarAdministracion = new JLabel("HyraCar: Administracion");
		lblHyracarAdministracion.setFont(new Font("Candara", Font.BOLD, 20));
		lblHyracarAdministracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblHyracarAdministracion.setBounds(96, 25, 265, 40);
		contentPane.add(lblHyracarAdministracion);
		
		btnRegistrarCoche = new JButton("Registrar coche");
		btnRegistrarCoche.setFont(new Font("Candara", Font.BOLD, 15));
		btnRegistrarCoche.setBounds(51, 97, 173, 51);
		contentPane.add(btnRegistrarCoche);
		btnRegistrarCoche.addActionListener(this);
		
		btnModificarCoche = new JButton("Modificar Coche");
		btnModificarCoche.setFont(new Font("Candara", Font.BOLD, 15));
		btnModificarCoche.setBounds(256, 97, 150, 51);
		contentPane.add(btnModificarCoche);
		btnModificarCoche.addActionListener(this);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setBounds(22, 328, 89, 23);
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
			
			registrarMoto vistaMoto = new registrarMoto();
			vistaMoto.setVisible(true);
			dispose();
		}
		if (e.getSource() == btnAtras){
			dispose();
			menuAdmin vista = new menuAdmin();
			vista.setVisible(true);
		}
		
	}
}
