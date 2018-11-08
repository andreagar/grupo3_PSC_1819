package es.deusto.grupo3.LPresentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.text.DecimalFormat;
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
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.Moto;
import es.deusto.grupo3.LNegocio.GestorMoto;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;

public class alquilarMoto extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430978011913089956L;

	private PanelConImagen contentPane;
	private JButton button;
	private JButton detalles;
	private JButton btnComprar;
	private JButton btnAlquilar;
	private JList listMoto;
	private String usuario;
	private JLabel lblAviso;
	private int limMoto;
	private String motoSelected;
	private GestorMoto objMoto;
	private JTextArea textArea;
	private DefaultListModel modeloMoto;
	private JLabel imagen;
	private JLabel lblDetallesDelMoto;
	private String sel;
	private JLabel lblSeleccioneUnCdigo;
	
	/**
	 * Create the frame.
	 */
	public alquilarMoto(String nombre) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(alquilarMoto.class.getResource("/es/deusto/grupo3/img/icon.png")));
		
		this.usuario=nombre;
		
		setTitle("HyraCar: Motos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 376);
		contentPane = new PanelConImagen();
		contentPane.setBackgroundImage(Toolkit.getDefaultToolkit().getImage(alquilarMoto.class.getResource("/es/deusto/grupo3/img/fondo.jpg")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sel = "<html><body>SELECCIONE UNA<br>MATRICULA:</body></html>";
		lblSeleccioneUnCdigo = new JLabel(sel);
		lblSeleccioneUnCdigo.setBounds(23, 11, 145, 40);
		lblSeleccioneUnCdigo.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblSeleccioneUnCdigo);
		
		lblDetallesDelMoto = new JLabel("DETALLES DE LA MOTO SELECCIONADA");
		lblDetallesDelMoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetallesDelMoto.setBounds(193, 11, 400, 14);
		lblDetallesDelMoto.setFont(new Font("Verdana", Font.PLAIN, 14));
		contentPane.add(lblDetallesDelMoto);
		
		listMoto = new JList();
		listMoto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listMoto.setBounds(23, 60, 130, 200);
		contentPane.add(listMoto);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Candara", Font.BOLD, 14));
		textArea.setBounds(193, 36, 400, 38);
		contentPane.add(textArea);
		
		imagen = new JLabel();
		imagen.setBounds(200, 80, 300, 150);
		contentPane.add(imagen);
		
		button = new JButton("Atras");
		button.setBounds(550, 299, 70, 28);
		button.setFont(new Font("Verdana", Font.PLAIN, 13));
		contentPane.add(button);
		button.addActionListener(this);
		button.setActionCommand("Atras");
		
		detalles = new JButton("Mostrar Detalles");
		detalles.setBounds(23, 271, 130, 29);
		detalles.setFont(new Font("Verdana", Font.PLAIN, 11));
		contentPane.add(detalles);
		detalles.addActionListener(this);
		detalles.setActionCommand("Detalles");
		
		btnComprar = new JButton("COMPRAR");
		btnComprar.setBounds(206, 298, 101, 29);
		btnComprar.setFont(new Font("Verdana", Font.PLAIN, 13));
		contentPane.add(btnComprar);
		btnComprar.addActionListener(this);
		btnComprar.setActionCommand("COMPRAR");
		
		String texto = "<html><body>Aviso:<br>Apunta la matricula del vehiculo para que luego puedas eliminar<br> la operaci\u00F3n. </body></html>";
		lblAviso = new JLabel(texto);
		lblAviso.setBounds(200, 230, 435, 50);
		lblAviso.setFont(new Font("Candara", Font.BOLD, 14));
		contentPane.add(lblAviso);
		
		btnAlquilar = new JButton("ALQUILAR");
		btnAlquilar.setBounds(338, 299, 101, 28);
		btnAlquilar.setFont(new Font("Verdana", Font.PLAIN, 13));
		contentPane.add(btnAlquilar);
		btnAlquilar.addActionListener(this);
		btnAlquilar.setActionCommand("ALQUILAR");
		
		objMoto=new GestorMoto();
		this.CargarLista(objMoto);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			case "COMPRAR":
				//this.Asignar(0);
				break;
				
			case "ALQUILAR":
				this.Asignar(1);
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
	
	public void CargarLista(GestorMoto moto){
		
		modeloMoto=new DefaultListModel();
		
		Statement st = BaseDeDatos.getStatement();
	
		for (Moto s : moto.GetArrayMotosDisponibles(st) ){
			if(s.isAlquilado()==false && s.isComprado()==false && s.isAveriado()==false){
			
				modeloMoto.addElement( s.getMatricula() );
			}
		}
		
		
		listMoto.setModel( modeloMoto );
	
	}
	
	public void MostrarDetalles(){

		textArea.setText("");
		textArea.disable();
		limMoto=listMoto.getSelectedIndex();
		
		if(limMoto!=-1){
			motoSelected =(String)listMoto.getSelectedValue();
			Statement st = BaseDeDatos.getStatement();
			
			for (Moto m : objMoto.GetArrayMotosDisponibles(st))
			{
				if(motoSelected.equals(m.getMatricula()))
				{
					
					textArea.append(" Matricula: ");
					textArea.append(m.getMatricula());
					textArea.append(", Marca: ");
					textArea.append(m.getMarca());
					textArea.append(", Modelo: ");
					textArea.append(m.getModelo());
					textArea.append("\n Precio: ");
					DecimalFormat df = new DecimalFormat("#.00");
					textArea.append((df.format(m.getPrecio()/365)) + " €/dia"); //dividido entre 365 para sacar precio por día
					imagen.setIcon(new ImageIcon (alquilarMoto.class.getResource(m.getImagen())));
					break;
				}			
			}

		}else{
			JOptionPane.showMessageDialog(null, "Seleccion incorrecta (debes selccionar al menos un elemento).","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void Asignar(int opc){
		limMoto=listMoto.getSelectedIndex();
		
		if(limMoto!=-1){
			modeloMoto=(DefaultListModel)listMoto.getModel();
			motoSelected = (String)listMoto.getSelectedValue();
			
			if(opc == 1) //ALQUILAR
			{
				Asignaciones asig = new Asignaciones (usuario, motoSelected, true, false, false, 2);
				boolean ok = false;
				ok = objMoto.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig);
				if (ok == true){
					modeloMoto.remove(listMoto.getSelectedIndex());
				}
				
			}
			if(opc == 2) //COMPRAR
			{
//				Asignaciones asig = new Asignaciones (usuario, motoSelected, false, true, false);
//				objmoto.AsignarVehiculoUsuario(BaseDeDatos.getStatement(), asig);
//				modelomoto.remove(listMoto.getSelectedIndex());
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "Seleccion incorrecta (debes selccionar al menos un elemento).","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
