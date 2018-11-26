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
 public class clima extends JFrame implements ActionListener{
 	private static final long serialVersionUID = 1L;
	private PanelConImagen contentPane;
	private JButton btnSalir;
	private String nombre;
	private JLabel lblNewLabel;
	
	public clima (String nombre){
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setTitle("HyraCar");
		
		this.nombre = nombre;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSalir = new JButton("Atras");
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSalir.setBounds(10, 430, 120, 27);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(this);
		
		lblNewLabel = new JLabel("El tiempo: HyraCar by AEMET", SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel.setBounds(52, 50, 383, 41);
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
		
		if (e.getSource() == btnSalir){
			dispose();
			menuUsuario frame = new menuUsuario(nombre);
			frame.setVisible(true);
		}
	}
}