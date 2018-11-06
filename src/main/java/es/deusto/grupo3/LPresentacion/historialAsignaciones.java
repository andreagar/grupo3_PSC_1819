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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;

public class historialAsignaciones extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430978011913089956L;

	private JPanel contentPane;
	private JButton button;
	private JList listAsig;
	private String usuario;
	private GestorCoche objCoche;
	private DefaultListModel modeloAsig;
	
	/**
	 * Create the frame.
	 */
	public historialAsignaciones(String nombre) {
		
		this.usuario=nombre;
		
		setTitle("ASIGNACIONES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblabel = new JLabel("HISTORIAL DEL USUARIO "+ usuario);
		lblabel.setBounds(90, 11, 254, 14);
		lblabel.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(lblabel);
		
		listAsig = new JList();
		listAsig.setFont(new Font("Candara", Font.BOLD, 14));
		listAsig.setBounds(30, 36, 330, 38);
		contentPane.add(listAsig);
	
		button = new JButton("Atras");
		button.setBounds(280, 299, 100, 28);
		button.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(button);
		button.addActionListener(this);
		button.setActionCommand("Atras");
		
		objCoche=new GestorCoche();
		this.CargarLista(objCoche);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button){
			this.dispose();
			menuUsuario frame = new menuUsuario(usuario);
			frame.setVisible(true);
		}
	}
	
	public void CargarLista(GestorCoche vehiculo){
		
		modeloAsig=new DefaultListModel();
		
		Statement st = BaseDeDatos.getStatement();
	
		for (Asignaciones asig : vehiculo.getUsuarioHistorial(st, usuario) ){
			modeloAsig.addElement( asig.toString() );
		}
	
		listAsig.setModel( modeloAsig );
	
	}
}
