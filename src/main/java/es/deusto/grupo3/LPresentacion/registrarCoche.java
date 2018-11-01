package es.deusto.grupo3.LPresentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorUsuario;

public class registrarCoche extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtMarca;
	private JTextField textModelo;
	private JTextField textMatricula;
	private JTextField textPrecio;
	private JButton btnCancelar;
	private JButton btnAceptar;

	

	/**
	 * Create the frame.
	 */
	public registrarCoche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(21, 237, 89, 23);
		contentPane.add(btnCancelar);
		btnAceptar.addActionListener(this);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(331, 237, 89, 23);
		contentPane.add(btnAceptar);
		btnCancelar.addActionListener(this);
		
		JLabel lblRegistrarCocheNuevo = new JLabel("Registrar coche nuevo");
		lblRegistrarCocheNuevo.setFont(new Font("Source Sans Pro Black", Font.BOLD, 18));
		lblRegistrarCocheNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarCocheNuevo.setBounds(66, 11, 286, 36);
		contentPane.add(lblRegistrarCocheNuevo);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(198, 75, 154, 20);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarca.setBounds(122, 76, 46, 14);
		contentPane.add(lblMarca);
		
		textModelo = new JTextField();
		textModelo.setBounds(198, 115, 154, 20);
		contentPane.add(textModelo);
		textModelo.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModelo.setBounds(122, 116, 66, 14);
		contentPane.add(lblModelo);
		
		textMatricula = new JTextField();
		textMatricula.setBounds(198, 161, 154, 20);
		contentPane.add(textMatricula);
		textMatricula.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMatricula.setBounds(122, 162, 66, 14);
		contentPane.add(lblMatricula);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String marca = txtMarca.getText();
		String modelo = textModelo.getText();
		String matricula = textMatricula.getText();
		double precio = 700;
		
		if (e.getSource() == btnAceptar){
			Coche c = new Coche(marca, modelo, matricula, precio, false, false, false);
			GestorCoche coche = new GestorCoche();
		//Si no existe, añadir fila con el usuario nuevo y sus respectivos atributos
			coche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), c);	
			dispose();
		}
		
		if (e.getSource() == btnCancelar){
			dispose();
		}
	}
}
