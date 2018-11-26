package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;
import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorOficina;
import es.deusto.grupo3.LNegocio.Oficina;

public class climaPlantilla extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelConImagen contentPane;
	private JButton btnAtras;
	private DefaultListModel modeloOficinas;
	private GestorOficina objOficina;
	private JLabel lblOficinas;
	private String nombre, ciudad;
	private String tipoUsuario;
	private final static Logger log = Logger.getLogger(App.class.getName());
	
	/**
	 * Create the frame.
	 */
	public climaPlantilla(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setTitle("HyraCar: AEMET");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 361);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel aaa = new JLabel("El tiempo");
		aaa.setFont(new Font("Verdana", Font.BOLD, 18));
		aaa.setHorizontalAlignment(SwingConstants.CENTER);
		aaa.setBounds(66, 11, 286, 36);
		contentPane.add(aaa);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAtras.setBounds(10, 289, 89, 23);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(this);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		if (e.getSource() == btnAtras){
			dispose();
			menuUsuario frame = new menuUsuario(nombre);
			frame.setVisible(true);
		}
	}
	
}
