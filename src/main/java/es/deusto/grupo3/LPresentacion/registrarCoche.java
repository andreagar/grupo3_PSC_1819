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

public class registrarCoche extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtMarca;
	private JTextField textField;
	private JTextField textField_1;

	

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
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(21, 237, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(331, 237, 89, 23);
		contentPane.add(btnAceptar);
		
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
		
		textField = new JTextField();
		textField.setBounds(198, 115, 154, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModelo.setBounds(122, 116, 66, 14);
		contentPane.add(lblModelo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(198, 161, 154, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMatricula.setBounds(122, 162, 66, 14);
		contentPane.add(lblMatricula);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
