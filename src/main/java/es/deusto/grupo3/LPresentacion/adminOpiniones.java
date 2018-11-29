package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorOpiniones;
import es.deusto.grupo3.LNegocio.Opinion;

import javax.swing.SwingConstants;

import java.awt.Toolkit;

public class adminOpiniones extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430978011913089956L;

	private PanelConImagen contentPane;
	private JButton btnAtras;
	private JList listAsig;
	private String usuario;
	private DefaultListModel modeloAsig;
	private JLabel lblabel;
	private GestorOpiniones objOpinion;
	
	/**
	 * Create the frame.
	 */
	public adminOpiniones() {
		
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		
		setTitle("HyraCar: Reseñas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 376);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblabel = new JLabel("RESEÑAS DE LOS USUARIOS");
		lblabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblabel.setBounds(30, 11, 330, 18);
		lblabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblabel);
		
		listAsig = new JList();
		listAsig.setFont(new Font("Candara", Font.BOLD, 14));
		listAsig.setBounds(30, 36, 330, 171);
		contentPane.add(listAsig);
	
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(280, 299, 100, 28);
		btnAtras.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(btnAtras);
		btnAtras.addActionListener(this);
		btnAtras.setActionCommand("Atras");
		
		//objOpinion=new GestorOpiniones();
		//this.CargarLista(objOpinion);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnAtras){
			this.dispose();
			menuAdmin menu = new menuAdmin();
			menu.setVisible(true);
		}
	}
	
	/*public void CargarLista(GestorOpiniones op){
		
		modeloAsig=new DefaultListModel();
		Statement st = BaseDeDatos.getStatement();
	
		for (Opinion o : objOpinion.getUsuarioHistorial(st)){
			modeloAsig.addElement( o.toString() );
		}
	
		listAsig.setModel( modeloAsig );
	}*/
}
