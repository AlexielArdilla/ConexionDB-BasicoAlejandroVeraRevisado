package ar.com.unpaz.vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ar.com.unpaz.database.MateriaDAO;
import ar.com.unpaz.model.ConversorResultSetADefaultTableModel;

public class FormularioMateriasID extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JTable table = new JTable();
   

	public FormularioMateriasID() {
		
		
		createUserInterface();
		DefaultTableModel modelo = new DefaultTableModel();
		MateriaDAO materiasResultSet = new MateriaDAO();
		ConversorResultSetADefaultTableModel.rellena(materiasResultSet.getMateriasYSusIDs(), modelo);
		this.table.setModel(modelo);
		
	}

	
	private void createUserInterface() {
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(new JScrollPane(this.table));
		this.setTitle("Finales");
		this.setLocationRelativeTo(null);
		
		JPanel datos = new JPanel();
		datos.add(new JLabel("Consulta materias y sus IDs"));
		this.add(datos, BorderLayout.NORTH);
		
	    JPanel operaciones = new JPanel();
	    
    	operaciones.add(new JLabel("Operaciones:"));
    
		this.pack();
		this.setVisible(true);
	}
	
	
    public void actualizar() {

    	DefaultTableModel modelo = new DefaultTableModel();
		MateriaDAO materiasResultSet = new MateriaDAO();
		ConversorResultSetADefaultTableModel.rellena(materiasResultSet.getMateriasYSusIDs(), modelo);
		this.table.setModel(modelo);

	}
    
  

}