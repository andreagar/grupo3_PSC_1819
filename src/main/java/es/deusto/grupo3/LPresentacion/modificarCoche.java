package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;

public class modificarCoche extends JFrame implements ActionListener{

	private static final long serialVersionUID = 7430978011913089956L;

	private PanelConImagen contentPane;
	
	private JTextField textMarca;
	private JTextField textModelo;
	private JTextField textMatricula;
	private JTextField textPrecio;
	private JTextField textImagen;
	private JCheckBox chckbxAlquilada;
	private JCheckBox chckbxComprada;
	private JCheckBox chckbxAveriada;
	private JButton detalles;
	private JButton button;
	private JButton modificar;
	private JList listCoche;
	private GestorCoche objCoche;
	private DefaultListModel modeloCoche;
	private int limCoche;
	private String cocheSelected;
	private String precioAux;
	private String sel;
	private JLabel lblDetallesDelCoche;
	private JLabel lblMarca;
	private JLabel lblSeleccioneUnCdigo;
	private JLabel lblModelo;
	private JLabel lblMatricula;
	private JLabel lblPrecio;
	private JLabel lblImagen;


	public modificarCoche() {
		Toolkit toolkit = getToolkit();
		setIconImage(toolkit.getImage(adminMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		
		setTitle("HyraCar: modificar coche");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 376);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(toolkit.getImage(login.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sel = "<html><body>SELECCIONE UNA<br>MATRICULA:</body></html>";
		lblSeleccioneUnCdigo = new JLabel(sel);
		lblSeleccioneUnCdigo.setBounds(23, 11, 145, 40);
		lblSeleccioneUnCdigo.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblSeleccioneUnCdigo);
		
		lblDetallesDelCoche = new JLabel("DETALLES DEL COCHE SELECCIONADO");
		lblDetallesDelCoche.setBounds(200, 29, 350, 14);
		lblDetallesDelCoche.setFont(new Font("Verdana", Font.PLAIN, 16));
		contentPane.add(lblDetallesDelCoche);
		
		listCoche = new JList();
		listCoche.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listCoche.setBounds(23, 60, 130, 200);
		contentPane.add(listCoche);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Candara", Font.PLAIN, 14));
		lblMarca.setBounds(200, 76, 46, 14);
		contentPane.add(lblMarca);
		
		textMarca = new JTextField();
		textMarca.setBounds(276, 72, 154, 20);
		contentPane.add(textMarca);
		textMarca.setColumns(10);
		
		lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Candara", Font.PLAIN, 14));
		lblModelo.setBounds(200, 116, 66, 14);
		contentPane.add(lblModelo);
		
		textModelo = new JTextField();
		textModelo.setBounds(276, 112, 154, 20);
		contentPane.add(textModelo);
		textModelo.setColumns(10);
		
		lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Candara", Font.PLAIN, 14));
		lblMatricula.setBounds(200, 162, 66, 14);
		contentPane.add(lblMatricula);
		
		textMatricula = new JTextField();
		textMatricula.setBounds(276, 158, 154, 20);
		contentPane.add(textMatricula);
		textMatricula.setColumns(10);
		
		lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Candara", Font.PLAIN, 14));
		lblPrecio.setBounds(200, 209, 66, 14);
		contentPane.add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setBounds(250, 205, 180, 21);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);

		lblImagen = new JLabel("Dir. Imagen:");
		lblImagen.setFont(new Font("Candara", Font.PLAIN, 14));
		lblImagen.setBounds(200, 246, 81, 14);
		contentPane.add(lblImagen);
		
		textImagen = new JTextField();
		textImagen.setBounds(286, 240, 235, 20);
		contentPane.add(textImagen);
		textImagen.setColumns(10);
		
		button = new JButton("Atras");
		button.setBounds(511, 298, 89, 28);
		button.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(button);
		button.addActionListener(this);
		button.setActionCommand("Atras");
		
		detalles = new JButton("Seleccionar");
		detalles.setBounds(23, 271, 130, 28);
		detalles.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(detalles);
		detalles.addActionListener(this);
		detalles.setActionCommand("Detalles");
		
		modificar = new JButton("Modificar");
		modificar.setBounds(357, 298, 100, 28);
		modificar.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(modificar);
		modificar.addActionListener(this);
		modificar.setActionCommand("modificar");
		
		objCoche=new GestorCoche();
		this.CargarLista(objCoche);
		
		textMatricula.setEnabled(false);
		textModelo.setEnabled(false);
		textMarca.setEnabled(false);
		
		chckbxAlquilada = new JCheckBox("Alquilado");
		chckbxAlquilada.setBackground(SystemColor.text);
		chckbxAlquilada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxAlquilada.setBounds(482, 71, 97, 23);
		contentPane.add(chckbxAlquilada);
		
		chckbxComprada = new JCheckBox("Comprado");
		chckbxComprada.setBackground(SystemColor.text);
		chckbxComprada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxComprada.setBounds(482, 111, 97, 23);
		contentPane.add(chckbxComprada);
		
		chckbxAveriada = new JCheckBox("Averiado");
		chckbxAveriada.setBackground(SystemColor.text);
		chckbxAveriada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxAveriada.setBounds(482, 157, 97, 23);
		contentPane.add(chckbxAveriada);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == detalles){
			this.MostrarDetalles();
		}
		
		if (e.getSource() == button){
			dispose();
			menuAdmin vista = new menuAdmin();
			vista.setVisible(true);
		}
		
		if (e.getSource() == modificar){
			
			if ((textMatricula.getText()).equals("")){
				JOptionPane.showMessageDialog(null,"Seleccion incorrecta (debes selccionar al menos un elemento).",
						"Mensaje de error",JOptionPane.ERROR_MESSAGE);
			}else{
				
				this.cambios();
			}
		}
	}
	
	public void cambios(){
		Statement st = BaseDeDatos.getStatement();
		GestorCoche gestor = new GestorCoche();
		double price = Double.parseDouble(textPrecio.getText());
		String id = textMatricula.getText();
		String img = textImagen.getText();
		boolean alquilada = chckbxAlquilada.isSelected();
		boolean comprada = chckbxComprada.isSelected();
		boolean averiada = chckbxAveriada.isSelected();
		
		boolean correcto = gestor.chequearYaEnTabla(st, id);
		
		if(correcto == true){
			boolean cambio = gestor.modificarDatos(st, id, price, img, alquilada, comprada, averiada);
			
			if (cambio == true){
				System.out.println("Modificacion hecha");
				menuAdmin vista = new menuAdmin();
				vista.setVisible(true);
				dispose();
			}
		}
	}
	public void CargarLista(GestorCoche coche){
		
		modeloCoche=new DefaultListModel();
		Statement st = BaseDeDatos.getStatement();
	
		for (Coche s : coche.GetArrayCocheGlobal(st) ){
			modeloCoche.addElement(s.getMatricula());
		}
		listCoche.setModel(modeloCoche);
	}
	
	public void MostrarDetalles(){
		
		limCoche=listCoche.getSelectedIndex();
		
		if(limCoche!=-1){
			cocheSelected =(String)listCoche.getSelectedValue();
			Statement st = BaseDeDatos.getStatement();
			
			for (Coche c : objCoche.GetArrayCocheGlobal(st)){
				if(cocheSelected.equals(c.getMatricula())){
					textMarca.setText((c.getMarca()));
					textModelo.setText(c.getModelo());
					textMatricula.setText(c.getMatricula());
					precioAux = String.valueOf(c.getPrecio());
					textPrecio.setText(precioAux);
					textImagen.setText(c.getImagen());
					chckbxAlquilada.setSelected(c.getAlquilado());
					chckbxComprada.setSelected(c.getComprado());
					chckbxAveriada.setSelected(c.getAveriado());
					break;
				}			
			}

		}else{
			JOptionPane.showMessageDialog(null,"Seleccion incorrecta (debes selccionar al menos un elemento).",
										"Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
	}	
}


