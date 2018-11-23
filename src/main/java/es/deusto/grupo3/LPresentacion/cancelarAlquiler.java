package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;
import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorAsignaciones;
import es.deusto.grupo3.LNegocio.GestorCoche;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class cancelarAlquiler extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430978011913089956L;
	
	private final static Logger log = Logger.getLogger(App.class.getName());

	private PanelConImagen contentPane;
	private JButton button;
	private JButton cancelarAlq;
	private JList listAsig;
	private String usuario;
	private GestorAsignaciones objAsig;
	private DefaultListModel modeloAsig;
	private JLabel lblabel;
	private int limAsig;
	private String asigSelected;
	
	/**
	 * Create the frame.
	 */
	public cancelarAlquiler(String nombre) {
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		
		
		this.usuario=nombre;
		
		setTitle("HyraCar: asignaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 376);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblabel = new JLabel("HISTORIAL DEL USUARIO "+ usuario);
		lblabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblabel.setBounds(30, 11, 330, 14);
		lblabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblabel);
		
		listAsig = new JList();
		listAsig.setFont(new Font("Candara", Font.BOLD, 14));
		listAsig.setBounds(30, 36, 330, 38);
		contentPane.add(listAsig);
	
		button = new JButton("Atras");
		button.setBounds(280, 299, 100, 28);
		button.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(button);
		button.addActionListener(this);
		button.setActionCommand("Atras");
		
		cancelarAlq = new JButton("Eliminar alquiler");
		cancelarAlq.setBounds(10, 280, 160, 50);
		cancelarAlq.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(cancelarAlq);
		cancelarAlq.addActionListener(this);
		cancelarAlq.setActionCommand("Atras");
		
		objAsig=new GestorAsignaciones();
		this.CargarLista(objAsig);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == cancelarAlq){
			this.eliminarAsignar();
		}
		
		if (e.getSource() == button){
			this.dispose();
		}
	}
	
	public void CargarLista(GestorAsignaciones vehiculo){
		
		modeloAsig=new DefaultListModel();
		
		Statement st = BaseDeDatos.getStatement();
	
		for (Asignaciones asig : vehiculo.GetArrayUsuarioAlquilados(st, usuario) ){
			modeloAsig.addElement( asig.toString() );
		}
	
		listAsig.setModel( modeloAsig );
	
	}
	
	public void eliminarAsignar(){
		limAsig=listAsig.getSelectedIndex();
		
		if(limAsig!=-1){
			modeloAsig=(DefaultListModel)listAsig.getModel();
			asigSelected = (String)listAsig.getSelectedValue();
			String[] parts = asigSelected.split("Matricula: ");
			String[] matricula = parts[1].split(",");
			log.info(matricula[0]);
			String[] vehiculo = matricula[1].split(" ");
			log.info(vehiculo[1]);
			
			boolean ok = false;
			ok = objAsig.CancelarAlquiler(BaseDeDatos.getStatement(), matricula[0], usuario, vehiculo[1]);
			
			if (ok == true){
				modeloAsig.remove(listAsig.getSelectedIndex());
			}
				
			
			
		}else{
			log.warn("Seleccion incorrecta (debes selccionar al menos un elemento).");
			JOptionPane.showMessageDialog(null, "Seleccion incorrecta (debes selccionar al menos un elemento).","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}