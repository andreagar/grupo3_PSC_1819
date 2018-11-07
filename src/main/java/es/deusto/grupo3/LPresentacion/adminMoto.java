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

public class adminMoto extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JButton btnRegistrarMoto;
	private JButton btnModificarMoto;
	private JButton btnAtras;

	public adminMoto() {
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
		
		btnRegistrarMoto = new JButton("Registrar moto");
		btnRegistrarMoto.setFont(new Font("Candara", Font.BOLD, 15));
		btnRegistrarMoto.setBounds(51, 97, 173, 51);
		contentPane.add(btnRegistrarMoto);
		btnRegistrarMoto.addActionListener(this);
		
		btnModificarMoto = new JButton("Modificar moto");
		btnModificarMoto.setFont(new Font("Candara", Font.BOLD, 15));
		btnModificarMoto.setBounds(256, 97, 150, 51);
		contentPane.add(btnModificarMoto);
		btnModificarMoto.addActionListener(this);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setBounds(22, 328, 89, 23);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnRegistrarMoto){
			
			registrarMoto vistaMoto = new registrarMoto();
			vistaMoto.setVisible(true);
			dispose();
		}
		if (e.getSource() == btnModificarMoto){
			
			modificarMoto motoMod = new modificarMoto();
			motoMod.setVisible(true);
			dispose();
		}
		if (e.getSource() == btnAtras){
			dispose();
			menuAdmin vista = new menuAdmin();
			vista.setVisible(true);
		}
		
	}
}
