package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;

public class alquilarCoche extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430978011913089956L;

	private JPanel contentPane;
	private JButton button;
	private JButton detalles;
	private JButton btnComprar;
	private JButton btnAlquilar;
	private JList listCoche;
	private String usuario;
	private JLabel lblAviso;
	private JLabel lblApuntaElCdigo;
	private int limCoche;
	private String cocheSelected;
	private GestorCoche objCoche;
	private JTextArea textArea;
	private DefaultListModel modeloCoche;
	
	/**
	 * Create the frame.
	 */
	public alquilarCoche(String nombre) {
		
		this.usuario=nombre;
		
		setTitle("LOCALES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String sel = "<html><body>SELECCIONE UNA<br>MATRICULA:</body></html>";
		JLabel lblSeleccioneUnCdigo = new JLabel(sel);
		lblSeleccioneUnCdigo.setBounds(23, 11, 145, 40);
		lblSeleccioneUnCdigo.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(lblSeleccioneUnCdigo);
		
		JLabel lblDetallesDelLocal = new JLabel("DETALLES DEL LOCAL SELECCIONADO");
		lblDetallesDelLocal.setBounds(208, 11, 254, 14);
		lblDetallesDelLocal.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(lblDetallesDelLocal);
		
		listCoche = new JList();
		listCoche.setBounds(23, 60, 130, 200);
		contentPane.add(listCoche);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Candara", Font.BOLD, 14));
		textArea.setBounds(193, 36, 400, 38);
		contentPane.add(textArea);
		
		button = new JButton("Atras");
		button.addActionListener(this);
		button.setBounds(550, 299, 70, 28);
		button.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(button);
		button.addActionListener(this);
		button.setActionCommand("Atras");
		
		detalles = new JButton("Mostrar Detalles");
		detalles.addActionListener(this);
		detalles.setBounds(23, 298, 145, 29);
		detalles.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(detalles);
		detalles.addActionListener(this);
		detalles.setActionCommand("Detalles");
		
		btnComprar = new JButton("COMPRAR");
		btnComprar.addActionListener(this);
		btnComprar.setBounds(206, 298, 101, 29);
		btnComprar.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(btnComprar);
		btnComprar.addActionListener(this);
		btnComprar.setActionCommand("COMPRAR");
		
		lblAviso = new JLabel("Aviso:");
		lblAviso.setBounds(208, 85, 46, 14);
		lblAviso.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(lblAviso);
		
		String texto = "<html><body>Apunta el c\u00F3digo de la vivienda para que luego puedas<br> eliminar la operaci\u00F3n. </body></html>";
		lblApuntaElCdigo = new JLabel(texto);
		lblApuntaElCdigo.setBounds(208, 110, 435, 50);
		lblApuntaElCdigo.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(lblApuntaElCdigo);
		
		btnAlquilar = new JButton("ALQUILAR");
		btnAlquilar.addActionListener(this);
		btnAlquilar.setBounds(338, 299, 101, 28);
		btnAlquilar.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(btnAlquilar);
		btnAlquilar.addActionListener(this);
		btnAlquilar.setActionCommand("ALQUILAR");
		
		objCoche=new GestorCoche();
		this.CargarLista(objCoche);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			case "COMPRAR":
				//this.Asignar(0);
				break;
				
			case "ALQUILAR":
				//this.Asignar(1);
				break;
				
			case "Detalles":
				this.MostrarDetalles();
				break;
			
			case "Atras":
				dispose();
				menuUsuario frame = new menuUsuario(usuario);
				frame.setVisible(true);
				break;
		
		}

	}
	
	public void CargarLista(GestorCoche coche){
		
		modeloCoche=new DefaultListModel();
		
		Statement st = BaseDeDatos.getStatement();
	
		for (Coche s : coche.GetArrayCochesDisponibles(st) ){
			if(s.getAlquilado()==false && s.getComprado()==false && s.getAveriado()==false){
			
				modeloCoche.addElement( s.getMatricula() );
			}
		}
		
		
		listCoche.setModel( modeloCoche );
	
	}
	
	public void MostrarDetalles(){
		
		textArea.setText("");
		textArea.disable();
		
		limCoche=listCoche.getSelectedIndex();
		cocheSelected =(String)listCoche.getSelectedValue();
		Statement st = BaseDeDatos.getStatement();
		
		if(limCoche!=-1)
		{
			for (Coche c : objCoche.GetArrayCochesDisponibles(st))
			{
				if(cocheSelected.equals(c.getMatricula()))
				{
					textArea.append(" Matricula: ");
					textArea.append(c.getMatricula());
					textArea.append(", Marca: ");
					textArea.append(c.getMarca());
					textArea.append(", Modelo: ");
					textArea.append(c.getModelo());
					textArea.append("\n Precio: ");
					//textArea.append(c.getPrecio()+"€");
					break;
				}			
			}

		}
		else
		{
			JOptionPane.showMessageDialog(null, "Seleccion incorrecta (debes selccionar al menos un elemento).","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
