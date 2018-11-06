package es.deusto.grupo3.LPresentacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;


public class listaCoches extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	//private PanelConImagen contentPane;
	private JButton btnAtrs;
	private JTable table;
	ArrayList<Coche> listaCoche;
	GestorCoche coches = new GestorCoche();
	private Coche seleccion;
	
	
	public listaCoches() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("HyraCar");
		setBounds(100, 100, 507, 403);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 478, 329);
		
		crearTabla();
		scrollPane.setViewportView(table);
		
		btnAtrs = new JButton();
		btnAtrs.setBounds(20, 351, 89, 23);
		btnAtrs.setActionCommand("atras");
		btnAtrs.addActionListener(this);
		
		btnAtrs = new JButton();
		btnAtrs.setBounds(20, 351, 89, 23);
		btnAtrs.setActionCommand("Modificar");
		btnAtrs.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
			case "atras": 
				adminCoche vista = new adminCoche();
				vista.setVisible(true);
				dispose();
				break;
			
			case "Modificar":
				modificarCoche modificar = new modificarCoche(seleccion);
				modificar.setVisible(true);
				dispose();
		}		
	}

	private void crearTabla(){
		

		table=null;
		
		/**
		 * Para ordenar la tabla de coches por matricula
		 */
		
		listaCoche = new ArrayList<Coche>();
		listaCoche = coches.ArrayCoche(); //DE ESTA FORMA NO ME DA NULL.POINTER.EXCEPTION, PERO LA LISTA ME SALE VACIA
		
		TablaCoche tcm = new TablaCoche(listaCoche);
//********************************************************************************************************************		
		table = new JTable(tcm);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		tcm.fireTableDataChanged();
	}
	
	class TablaCoche extends AbstractTableModel{
       
		private static final long serialVersionUID = 1L;
		private String[] columnNames = {"Marca", "Modelo", "Matricula", "Precio", /*"Alquilado", 
										"Comprado", "Averiado", */"Imagen"};
        Object[][] data;
        
        
        public TablaCoche(ArrayList<Coche> m) {
        	
        	super();
        	
    		int filas = m.size();
    		int cont;
    		data=new Object[filas][];
    		cont=0;
    		
    		for (Coche entry : listaCoche) {
    		    
    			Object[]a={new String(entry.getMarca()),
    					   new String(entry.getModelo()),
    					   new String(entry.getMatricula()),
    					   new Double(entry.getPrecio()),
    					   //new String(entry.getAlquilado()),
    					   //new String(entry.getComprado()),
    					   //new String(entry.getAveriado()),
    					   new String(entry.getImagen())};
    			data[cont]=a;
    			cont++;
    		}
        }
        
        public void setData(ArrayList<Coche> m) {
        	int filas = m.size();
    		int cont;
    		data=new Object[filas][];
    		cont=0;
    	
    		for (Coche entry : listaCoche){
    		    //System.out.println(entry.getKey() + "/" + entry.getValue());
    			Object[]a={new String(entry.getMarca()),
 					   new String(entry.getModelo()),
 					   new String(entry.getMatricula()),
 					   new Double(entry.getPrecio()),
 					   //new String(entry.getAlquilado()),
 					   //new String(entry.getComprado()),
 					   //new String(entry.getAveriado()),
 					   new String(entry.getImagen())};
    			data[cont]=a;
    			cont++;
    		}
        }

		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.length;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames.length;
		}
		
		public String getColumnName (int col){
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
            return data[row][col];
		}
    }
	
}
