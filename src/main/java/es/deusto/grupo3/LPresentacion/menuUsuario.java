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

public class menuUsuario extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAlquilar;
	private JButton btnModificar;
	private JButton btnHistorial;
	private JButton btnSalir;
	private String nombre;
	
	public menuUsuario (String nombre){
		
		this.nombre = nombre;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAlquilar = new JButton("Alquilar coche");
		btnAlquilar.setFont(new Font("Candara", Font.BOLD, 18));
		btnAlquilar.setBounds(140, 130, 200, 55);
		contentPane.add(btnAlquilar);
		btnAlquilar.addActionListener(this);
		
		btnHistorial = new JButton("Ver mi historial");
		btnHistorial.setFont(new Font("Candara", Font.BOLD, 18));
		btnHistorial.setBounds(140, 230, 200, 55);
		contentPane.add(btnHistorial);
		btnHistorial.addActionListener(this);
		
		btnModificar = new JButton("Modificar mi contraseña");
		btnModificar.setFont(new Font("Candara", Font.BOLD, 18));
		btnModificar.setBounds(115, 330, 250, 55);
		contentPane.add(btnModificar);
		btnModificar.addActionListener(this);
		
		btnSalir = new JButton("Cerrar sesion");
		btnSalir.setFont(new Font("Candara", Font.BOLD, 12));
		btnSalir.setBounds(10, 430, 120, 27);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Alquiler de coches HyraCar", SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 24));
		lblNewLabel.setBounds(80, 50, 300, 41);
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
//			registrarse registrarse = new registrarse();
//			registrarse.setVisible(true);
			GestorCoche g = new GestorCoche();
			ArrayList<Asignaciones> a = new ArrayList<Asignaciones>();
			a = g.getUsuarioHistorial(BaseDeDatos.getStatement(), nombre);
			for (int i = 0; i<a.size(); i++){
				a.get(i).toString();
			}
			
		}
		
		if (e.getSource() == btnAlquilar){
			dispose();
			alquilarCoche alquilar = new alquilarCoche(nombre);
			alquilar.setVisible(true);			
		}
		
		if (e.getSource() == btnModificar){
			
			dispose();
			perfilUsuario framePerfil = new perfilUsuario(nombre);
			framePerfil.setVisible(true);
			
		}
		
		if (e.getSource() == btnSalir){
			nombre = null;
			this.dispose();
			vistaPrincipal frame = new vistaPrincipal();
			frame.setVisible(true);
		}
	}

}
