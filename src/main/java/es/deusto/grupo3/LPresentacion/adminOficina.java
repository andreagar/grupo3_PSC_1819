package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class adminOficina extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelConImagen contentPane;
	private JButton btnRegistrarOficina;
	private JButton btnEliminarOficina;
	private JButton btnVerOficina;
	private JButton btnAtras;
	private JLabel lblHyracarAdministracion;

	public adminOficina() {
		setTitle("HyraCar: Administración");
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 314);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHyracarAdministracion = new JLabel("Administración: Oficina");
		lblHyracarAdministracion.setFont(new Font("Verdana", Font.BOLD, 20));
		lblHyracarAdministracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblHyracarAdministracion.setBounds(51, 25, 355, 40);
		contentPane.add(lblHyracarAdministracion);
		
		btnRegistrarOficina = new JButton("Registrar oficina");
		btnRegistrarOficina.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnRegistrarOficina.setBounds(51, 97, 156, 51);
		contentPane.add(btnRegistrarOficina);
		btnRegistrarOficina.addActionListener(this);
		
		btnEliminarOficina = new JButton("Eliminar oficina");
		btnEliminarOficina.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnEliminarOficina.setBounds(250, 97, 156, 51);
		contentPane.add(btnEliminarOficina);
		btnEliminarOficina.addActionListener(this);
		
		btnVerOficina = new JButton("Ver oficinas");
		btnVerOficina.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnVerOficina.setBounds(135, 175, 156, 51);
		contentPane.add(btnVerOficina);
		btnVerOficina.addActionListener(this);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAtras.setBounds(10, 242, 89, 23);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnRegistrarOficina){
			
			registrarOficina vistaOficina = new registrarOficina();
			vistaOficina.setVisible(true);
			dispose();
		}
		if (e.getSource() == btnEliminarOficina){
			
			eliminarOficina eliminarOf = new eliminarOficina();
			eliminarOf.setVisible(true);
			dispose();
		}
		if (e.getSource() == btnVerOficina){
			
			verOficinas verOf = new verOficinas("admin");
			verOf.setVisible(true);
			dispose();
		}		
		if (e.getSource() == btnAtras){
			dispose();
			menuAdmin vista = new menuAdmin();
			vista.setVisible(true);
		}
		
	}

}
