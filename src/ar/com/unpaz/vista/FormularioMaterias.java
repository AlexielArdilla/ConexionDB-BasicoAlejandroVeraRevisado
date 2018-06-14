package ar.com.unpaz.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ar.com.unpaz.database.ImpresoraTXTdeTramites;
import ar.com.unpaz.database.MateriaDAO;
import ar.com.unpaz.model.ConversorResultSetADefaultTableModel;

public class FormularioMaterias extends JFrame  implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double nota;
	private JTable table = new JTable();
    private JButton imprimir_txt = new JButton("Imprimir");
    private FormularioAlumnos miFormAlumnos;

	public FormularioMaterias(double nota, FormularioAlumnos form_alumnos) {
		
		this.nota = nota;
		createUserInterface();
		DefaultTableModel modelo = new DefaultTableModel();
		MateriaDAO materiasResultSet = new MateriaDAO();
		ConversorResultSetADefaultTableModel.rellena(materiasResultSet.getAllMateriasByNota(nota), modelo);
		this.table.setModel(modelo);
		this.miFormAlumnos = form_alumnos;
		
	}

	
	private void createUserInterface() {
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(new JScrollPane(this.table));
		this.setTitle("Finales");
		
		JPanel datos = new JPanel();
		datos.add(new JLabel("Materias con la nota: "+this.nota));
		this.add(datos, BorderLayout.NORTH);
		
	    JPanel operaciones = new JPanel();
	    
    	operaciones.add(new JLabel("Operaciones:"));
    	imprimir_txt.addActionListener(this);
    	
    	/* Agrego los botones al Formulario */
    	operaciones.add(imprimir_txt);
    	this.add(operaciones, BorderLayout.SOUTH);
	    
		this.pack();
		this.setVisible(true);
	}
	
	
	
    @SuppressWarnings("static-access")
	@Override
    public void actionPerformed(ActionEvent e) {
    	
    	   Object fuente = e.getSource();
    	   
    	  if (fuente==imprimir_txt) {
    		   
    	 
    		  JFileChooser mi_chooser= new JFileChooser(); 
  			
    		  mi_chooser.setAcceptAllFileFilterUsed(false);
  			
  			String ruta = ""; 
  			
  			try{ 
  			
  				if(mi_chooser.showSaveDialog(null)==mi_chooser.APPROVE_OPTION){ 
  			ruta = mi_chooser.getSelectedFile().getAbsolutePath();
  			
  			 ImpresoraTXTdeTramites miImpresora = new ImpresoraTXTdeTramites();
  			
  			 LinkedList<String> a_escribir= miImpresora.resultSetALinkedListString(new MateriaDAO().getAllMateriasByNota(nota));
			
  			 miImpresora.escribeLineas(ruta, a_escribir);
  			
  			JOptionPane.showMessageDialog(null, "Guardado con éxito!!!");
  			
  			} 
  			}catch (Exception ex){ 
  			
  				
  				JOptionPane.showMessageDialog(null, "No se pudo guardar...");
  			} 
    		  
     		  miFormAlumnos.actualizar();}
    
    	   }
    	
    
    
    public void actualizar() {

    	DefaultTableModel modelo = new DefaultTableModel();
		MateriaDAO materiasResultSet = new MateriaDAO();
		ConversorResultSetADefaultTableModel.rellena(materiasResultSet.getAllMateriasByNota(nota), modelo);
		this.table.setModel(modelo);

	}
    

}
