package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.GestorCoche;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class menuUsuario extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private PanelConImagen contentPane;
	private JButton btnCoche;
	private JButton btnMoto;
	private JButton btnModificar;
	private JButton btnHistorial;
	private JButton btnEliminar;
	private JButton btnOficinas;
	private JButton btnOpinion;
	private JButton btnHistoOp;
	private JButton btnSalir;
	private String nombre;
	private JButton btnAemet;
	private JLabel lblNewLabel;
	
	public menuUsuario (String nombre){
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setTitle("HyraCar");
		
		this.nombre = nombre;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 500);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCoche = new JButton("<html><body>Alquilar/Comprar<br><center>COCHE</center></body></html>");
		btnCoche.setToolTipText("");
		btnCoche.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnCoche.setBounds(52, 114, 157, 70);
		contentPane.add(btnCoche);
		btnCoche.addActionListener(this);
		
		btnMoto = new JButton("<html><body>Alquilar/Comprar<br><center>MOTO</center></body></html>");
		btnMoto.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnMoto.setBounds(241, 114, 157, 70);
		contentPane.add(btnMoto);
		btnMoto.addActionListener(this);
		
		btnOpinion = new JButton("Añadir Reseña");
		btnOpinion.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnOpinion.setBounds(430, 114, 157, 70);
		contentPane.add(btnOpinion);
		btnOpinion.addActionListener(this);
		
		btnHistoOp = new JButton("Ver reseñas");
		btnHistoOp.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnHistoOp.setBounds(430, 218, 157, 70);
		contentPane.add(btnHistoOp);
		btnHistoOp.addActionListener(this);
		
		btnModificar = new JButton("<html><body>Modificar mi<br><center>contraseña</center></body></html>");
		btnModificar.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnModificar.setBounds(52, 218, 157, 70);
		contentPane.add(btnModificar);
		btnModificar.addActionListener(this);
		
		btnEliminar = new JButton("Cancelar alquiler");
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnEliminar.setBounds(241, 218, 157, 70);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(this);
		
		btnAemet = new JButton("AEMET");
		btnAemet.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnAemet.setBounds(52, 319, 157, 70);
		contentPane.add(btnAemet);
		btnAemet.addActionListener(this);
		
		btnOficinas = new JButton("Ver oficinas");
		btnOficinas.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnOficinas.setBounds(241, 319, 157, 70);
		contentPane.add(btnOficinas);
		btnOficinas.addActionListener(this);
		
		btnHistorial = new JButton("Ver mi historial");
		btnHistorial.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnHistorial.setBounds(430, 319, 157, 70);
		contentPane.add(btnHistorial);
		btnHistorial.addActionListener(this);
		
		btnSalir = new JButton("Cerrar sesion");
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSalir.setBounds(24, 423, 120, 27);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(this);
		
		lblNewLabel = new JLabel("Alquiler/compra de coches HyraCar", SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		lblNewLabel.setBounds(52, 28, 540, 50);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
	
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
		
		if (e.getSource() == btnHistorial){
			historialAsignaciones asig = new historialAsignaciones(nombre);
			asig.setVisible(true);
		}
		
		if (e.getSource() == btnCoche){
			asignarCoche alquilar = new asignarCoche(nombre);
			alquilar.setVisible(true);			
		}
		
		if (e.getSource() == btnMoto){
			asignarMoto alquilarMoto = new asignarMoto(nombre);
			alquilarMoto.setVisible(true);			
		}
		
		if (e.getSource() == btnModificar){
			perfilUsuario framePerfil = new perfilUsuario(nombre);
			framePerfil.setVisible(true);
			
		}
		
		if (e.getSource() == btnEliminar){
			cancelarAlquiler alq = new cancelarAlquiler(nombre);
			alq.setVisible(true);
		}
		
		if (e.getSource() == btnOficinas){
			verOficinas of = new verOficinas(nombre);
			of.setVisible(true);
		}
		
		if (e.getSource() == btnAemet){
			dispose();
			climaMenu cl = new climaMenu(nombre);
			cl.setVisible(true);			
		}
		
		if (e.getSource() == btnOpinion){
			dispose();
			opinionMenu cl = new opinionMenu(nombre);
			cl.setVisible(true);			
		}
		
		if (e.getSource() == btnHistoOp){
			dispose();
			adminOpiniones op = new adminOpiniones(nombre);
			op.setVisible(true);
		}
		
		if (e.getSource() == btnSalir){
			dispose();
			nombre = null;
			vistaPrincipal frame = new vistaPrincipal();
			frame.setVisible(true);
		}
	}
}
