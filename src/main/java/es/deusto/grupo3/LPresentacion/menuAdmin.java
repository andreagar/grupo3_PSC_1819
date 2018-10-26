package es.deusto.grupo3.LPresentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class menuAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuAdmin frame = new menuAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public menuAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHyracarAdministracion = new JLabel("HyraCar: Administracion");
		lblHyracarAdministracion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHyracarAdministracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblHyracarAdministracion.setBounds(96, 25, 265, 40);
		contentPane.add(lblHyracarAdministracion);
		
		JButton btnRegistrarCoche = new JButton("Registrar coche");
		btnRegistrarCoche.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarCoche.setBounds(51, 97, 150, 51);
		contentPane.add(btnRegistrarCoche);
		
		JButton btnRegistrarMoto = new JButton("Registrar moto");
		btnRegistrarMoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarMoto.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarMoto.setBounds(256, 97, 150, 51);
		contentPane.add(btnRegistrarMoto);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(22, 328, 89, 23);
		contentPane.add(btnCerrar);
	}
}
