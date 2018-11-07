package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class modificarCoche extends JFrame implements ActionListener{

	private static final long serialVersionUID = 7430978011913089956L;

	private JPanel contentPane;
	
	private JTextField textMarca;
	private JTextField textModelo;
	private JTextField textMatricula;
	private JTextField textPrecio;
	private JTextField textImagen;
	
	private JButton detalles;
	private JButton button;
	private JButton modificar;
	
	private JList listCoche;
	
	private GestorCoche objCoche;
	
	private DefaultListModel modeloCoche;
	
	private int limCoche;
	private String cocheSelected;
	private String precioAux;

	public modificarCoche() {
		
		setTitle("MODIFICAR COCHE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String sel = "<html><body>SELECCIONE UNA<br>MATRICULA:</body></html>";
		JLabel lblSeleccioneUnCdigo = new JLabel(sel);
		lblSeleccioneUnCdigo.setBounds(23, 11, 145, 40);
		lblSeleccioneUnCdigo.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(lblSeleccioneUnCdigo);
		
		JLabel lblDetallesDelCoche = new JLabel("DETALLES DEL COCHE SELECCIONADO");
		lblDetallesDelCoche.setBounds(200, 29, 296, 14);
		lblDetallesDelCoche.setFont(new Font("Candara", Font.BOLD, 16));
		contentPane.add(lblDetallesDelCoche);
		
		listCoche = new JList();
		listCoche.setBounds(23, 60, 130, 200);
		contentPane.add(listCoche);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Candara", Font.PLAIN, 14));
		lblMarca.setBounds(200, 76, 46, 14);
		contentPane.add(lblMarca);
		
			textMarca = new JTextField();
			textMarca.setBounds(276, 72, 154, 20);
			contentPane.add(textMarca);
			textMarca.setColumns(10);
		
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Candara", Font.PLAIN, 14));
		lblModelo.setBounds(200, 116, 66, 14);
		contentPane.add(lblModelo);
		
			textModelo = new JTextField();
			textModelo.setBounds(276, 112, 154, 20);
			contentPane.add(textModelo);
			textModelo.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Candara", Font.PLAIN, 14));
		lblMatricula.setBounds(200, 162, 66, 14);
		contentPane.add(lblMatricula);
		
			textMatricula = new JTextField();
			textMatricula.setBounds(276, 158, 154, 20);
			contentPane.add(textMatricula);
			textMatricula.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Candara", Font.PLAIN, 14));
		lblPrecio.setBounds(200, 209, 66, 14);
		contentPane.add(lblPrecio);
		
			textPrecio = new JTextField();
			textPrecio.setBounds(250, 205, 180, 21);
			contentPane.add(textPrecio);
			textPrecio.setColumns(10);

		
		JLabel lblImagen = new JLabel("Dir. Imagen:");
		lblImagen.setFont(new Font("Candara", Font.PLAIN, 14));
		lblImagen.setBounds(200, 246, 81, 14);
		contentPane.add(lblImagen);
		
			textImagen = new JTextField();
			textImagen.setBounds(286, 240, 235, 20);
			contentPane.add(textImagen);
			textImagen.setColumns(10);
		
		button = new JButton("Atras");
		button.setBounds(442, 298, 70, 28);
		button.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(button);
		button.addActionListener(this);
		button.setActionCommand("Atras");
		
		detalles = new JButton("Seleccionar");
		detalles.setBounds(23, 298, 145, 29);
		detalles.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(detalles);
		detalles.addActionListener(this);
		detalles.setActionCommand("Detalles");
		
		modificar = new JButton("Modificar");
		modificar.setBounds(250, 299, 100, 28);
		modificar.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(modificar);
		modificar.addActionListener(this);
		modificar.setActionCommand("modificar");
		
		objCoche=new GestorCoche();
		this.CargarLista(objCoche);
		
		textMatricula.setEnabled(false);
		textModelo.setEnabled(false);
		textMarca.setEnabled(false);
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
				
				Statement st = BaseDeDatos.getStatement();
				GestorCoche gestor = new GestorCoche();
				double price = Double.parseDouble(textPrecio.getText());
				String id = textMatricula.getText();
				String img = textImagen.getText();
				
				boolean correcto = gestor.chequearYaEnTabla(st, id);
				
				if(correcto == true){
					boolean cambio = gestor.modificarDatos(st, id, price, img);
					
					if (cambio == true){
						System.out.println("Modificacion hecha");
						menuAdmin vista = new menuAdmin();
						vista.setVisible(true);
						dispose();
					}
				}
			}
		}
	}
	
	public void CargarLista(GestorCoche coche){
		
		modeloCoche=new DefaultListModel();
		Statement st = BaseDeDatos.getStatement();
	
		for (Coche s : coche.GetArrayCochesDisponibles(st) ){
			if(s.getAlquilado()==false && s.getComprado()==false && s.getAveriado()==false){
				modeloCoche.addElement(s.getMatricula());
			}
		}
		listCoche.setModel(modeloCoche);
	}
	
	public void MostrarDetalles(){
		
		limCoche=listCoche.getSelectedIndex();
		
		if(limCoche!=-1){
			cocheSelected =(String)listCoche.getSelectedValue();
			Statement st = BaseDeDatos.getStatement();
			
			for (Coche c : objCoche.GetArrayCochesDisponibles(st)){
				if(cocheSelected.equals(c.getMatricula())){
					textMarca.setText((c.getMarca()));
					textModelo.setText(c.getModelo());
					textMatricula.setText(c.getMatricula());
					precioAux = String.valueOf(c.getPrecio());
					textPrecio.setText(precioAux);
					textImagen.setText(c.getImagen());
					break;
				}			
			}

		}else{
			JOptionPane.showMessageDialog(null,"Seleccion incorrecta (debes selccionar al menos un elemento).",
										"Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
	}	
}


