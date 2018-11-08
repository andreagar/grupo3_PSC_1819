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
import java.awt.Toolkit;

public class adminMoto extends JFrame implements ActionListener{
	
	private PanelConImagen contentPane;
	private JButton btnRegistrarMoto;
	private JButton btnModificarMoto;
	private JButton btnAtras;
	private JLabel lblHyracarAdministracion;

	public adminMoto() {
		setTitle("HyraCar: Administración");
		setIconImage(Toolkit.getDefaultToolkit().getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 314);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(Toolkit.getDefaultToolkit().getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHyracarAdministracion = new JLabel("Administración: Moto");
		lblHyracarAdministracion.setFont(new Font("Verdana", Font.BOLD, 20));
		lblHyracarAdministracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblHyracarAdministracion.setBounds(51, 25, 355, 40);
		contentPane.add(lblHyracarAdministracion);
		
		btnRegistrarMoto = new JButton("Registrar moto");
		btnRegistrarMoto.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnRegistrarMoto.setBounds(51, 97, 173, 51);
		contentPane.add(btnRegistrarMoto);
		btnRegistrarMoto.addActionListener(this);
		
		btnModificarMoto = new JButton("Modificar moto");
		btnModificarMoto.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnModificarMoto.setBounds(256, 97, 150, 51);
		contentPane.add(btnModificarMoto);
		btnModificarMoto.addActionListener(this);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAtras.setBounds(10, 242, 89, 23);
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
