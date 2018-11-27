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
import es.deusto.grupo3.LNegocio.Clima;
import es.deusto.grupo3.LNegocio.GestorClima;
import es.deusto.grupo3.LNegocio.GestorOficina;
import es.deusto.grupo3.LNegocio.Oficina;

import javax.swing.JTextArea;

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
	GestorClima gestorClima;
	private final static Logger log = Logger.getLogger(App.class.getName());
	private JLabel aaa;
	//Clima clima = new Clima("juan",2,"prueba",3);
	Clima clima = new Clima(null, 0, null, 0);
	/**
	 * Create the frame.
	 */
	public climaPlantilla(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		Statement st = BaseDeDatos.getStatement();
		double precip = gestorClima.getClima(st, ciudad).getTemperatura(); //esto es para ver si tambien falla esto o fallaba en la inicializacion del objeto Clima
		//Clima clima = new Clima (gestorClima.getClima(st, ciudad).getCiudad(), gestorClima.getClima(st, ciudad).getTemperatura(), gestorClima.getClima(st, ciudad).getEstado(), gestorClima.getClima(st, ciudad).getPrecipitacion());//AQUI ESTA EL ERROR
		clima.setCiudad(gestorClima.getClima(st, ciudad).getCiudad());
		clima.setTemperatura(gestorClima.getClima(st, ciudad).getTemperatura());
		clima.setEstado(gestorClima.getClima(st, ciudad).getEstado());
		clima.setPrecipitacion(gestorClima.getClima(st, ciudad).getPrecipitacion());
		
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
		
		aaa = new JLabel("El tiempo");
		aaa.setFont(new Font("Verdana", Font.BOLD, 18));
		aaa.setHorizontalAlignment(SwingConstants.CENTER);
		aaa.setBounds(66, 11, 286, 36);
		contentPane.add(aaa);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnAtras.setBounds(10, 289, 89, 23);
		contentPane.add(btnAtras);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCiudad.setBounds(66, 68, 120, 23);
		contentPane.add(lblCiudad);
		
		JLabel lblTemperatura = new JLabel("Temperatura:");
		lblTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTemperatura.setBounds(66, 102, 113, 23);
		contentPane.add(lblTemperatura);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstado.setBounds(66, 143, 89, 23);
		contentPane.add(lblEstado);
		
		JLabel lblPrecipitacion = new JLabel("Precipitacion %:");
		lblPrecipitacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecipitacion.setBounds(66, 189, 120, 18);
		contentPane.add(lblPrecipitacion);
		
		JTextArea textAreaCiudad = new JTextArea(clima.getCiudad());
		textAreaCiudad.setBounds(172, 68, 113, 18);
		contentPane.add(textAreaCiudad);
		
		JTextArea textAreaTemp = new JTextArea(Double.toString(clima.getTemperatura()));
		textAreaTemp.setBounds(172, 102, 113, 18);
		contentPane.add(textAreaTemp);
		
		JTextArea textAreaEstado = new JTextArea(clima.getEstado());
		textAreaEstado.setBounds(173, 143, 113, 18);
		contentPane.add(textAreaEstado);
		
		JTextArea textAreaPrecip = new JTextArea(Integer.toString(clima.getPrecipitacion()));
		textAreaPrecip.setBounds(172, 187, 113, 18);
		contentPane.add(textAreaPrecip);
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
