package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;
import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.GestorAsignaciones;
import es.deusto.grupo3.LNegocio.GestorOficina;
import es.deusto.grupo3.LNegocio.Oficina;

public class eliminarOficina extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelConImagen contentPane;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JList listOficinas;
	private DefaultListModel modeloOficinas;
	private GestorOficina objOficina;
	private JLabel lblEliminar;
	private int limOficina;
	
	private final static Logger log = Logger.getLogger(App.class.getName());
	
	/**
	 * Create the frame.
	 */
	public eliminarOficina() {
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		setTitle("HyraCar: eliminar oficina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 361);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEliminar = new JLabel("Eliminar oficina");
		lblEliminar.setFont(new Font("Verdana", Font.BOLD, 18));
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setBounds(66, 11, 286, 36);
		contentPane.add(lblEliminar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnCancelar.setBounds(10, 289, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(this);
		
		btnEliminar = new JButton("Aceptar");
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnEliminar.setBounds(331, 289, 89, 23);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(this);
		
		listOficinas = new JList();
		listOficinas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listOficinas.setBounds(30, 50, 370, 195);
		contentPane.add(listOficinas);

		objOficina=new GestorOficina();
		this.CargarLista(objOficina);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnEliminar){
			this.Eliminar();;
			//dispose();
			//menuAdmin vistaAdmin = new menuAdmin();
			//vistaAdmin.setVisible(true);
		}
		
		if (e.getSource() == btnCancelar){
			dispose();
			menuAdmin vistaAdmin = new menuAdmin();
			vistaAdmin.setVisible(true);
		}
	}
	
	public void CargarLista(GestorOficina gestorOf){
		
		modeloOficinas=new DefaultListModel();
		
		Statement st = BaseDeDatos.getStatement();
	
		for (Oficina of : gestorOf.GetArrayOficinas(st) ){
			modeloOficinas.addElement( of.toString() );
		}
	
		listOficinas.setModel( modeloOficinas );
	
	}
	
	public void Eliminar(){
		limOficina=listOficinas.getSelectedIndex();
		
		if(limOficina!=-1){
			modeloOficinas=(DefaultListModel)listOficinas.getModel();
			Statement st = BaseDeDatos.getStatement();
			Oficina of = new Oficina (-1, null, null, null);
			of.setId(objOficina.GetArrayOficinas(st).get(limOficina).getId());
			of.setNombre(objOficina.GetArrayOficinas(st).get(limOficina).getNombre());
			of.setCiudad(objOficina.GetArrayOficinas(st).get(limOficina).getCiudad());
			of.setPais(objOficina.GetArrayOficinas(st).get(limOficina).getPais());
			
			boolean ok = false;
			ok = objOficina.eliminarOficina(st, of);
			if (ok == true){
				modeloOficinas.remove(listOficinas.getSelectedIndex());
			}
			
		}else{
			log.warn("Seleccion incorrecta (debes selccionar al menos un elemento).");
			JOptionPane.showMessageDialog(null, "Seleccion incorrecta (debes selccionar al menos un elemento).","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
