package ar.com.unpaz.vista;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ar.com.unpaz.database.AlumnoDAO;
import ar.com.unpaz.model.ConversorResultSetADefaultTableModel;




public class FormularioAlumnos  extends JPanel  implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private JTable tabla = new JTable();
  
    private JPanel operaciones = new JPanel();
    
    private JButton agregar = new JButton("Agregar");
    private JButton materiasPorNota = new JButton("Materias por nota");
    private JButton verFinales = new JButton("Ver Finales");

    /**
     * Crea la ventana con todos sus componentes dentro y la visualiza
     *
     */
    public FormularioAlumnos (){
    	super(new BorderLayout());
    	this.add(new JScrollPane(this.tabla));
    	
    	operaciones.add(new JLabel("Operaciones:"));
    	/* Agrego los botones al Listener */
    	agregar.addActionListener(this);
    	materiasPorNota.addActionListener(this);
    	verFinales.addActionListener(this);
    	/* Agrego los botones al Formulario */
    	operaciones.add(this.agregar);
    	operaciones.add(this.materiasPorNota);
    	operaciones.add(this.verFinales);
    	this.add(operaciones, BorderLayout.SOUTH);
    	
   	
    	this.tabla.setPreferredScrollableViewportSize(new Dimension(500, 175));
    }

    
   
    /**
     * Mete el modelo que recibe en la tabla.
     * @param modelo
     */
    public void tomaDatos(DefaultTableModel modelo)
    {
        this.tabla.setModel(modelo);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
    	   Object fuente = e.getSource();
    	   String nombre=null;
    	   
		if (fuente==agregar) {
			
			
    		   nombre = JOptionPane.showInputDialog("Ingrese un Apellido y nombre de Alumno.");
    	   	
			AlumnoDAO alumno = new AlumnoDAO();
			alumno.agregarAlumno(nombre);
			actualizar();
		}
    	   else if (fuente==materiasPorNota) {//*************************************************************************
    		   
    		   String nota = JOptionPane.showInputDialog("Ingrese la nota a buscar(entre 1 y 10 con o sin decimales)");
    		   
    		   try {
    			   
				double nota_double = Double.parseDouble(nota);
				
				if(nota_double>=1 && nota_double<=10){
					
					new FormularioMaterias(nota_double, this);

				}else{
					
					JOptionPane.showMessageDialog(null, "La nota debe estar entre 1 y 10 inclusive");
					
				}
				
			} catch (NumberFormatException e1) {

					JOptionPane.showMessageDialog(null, "No ingresó un valor correcto");
				e1.printStackTrace();
			}
    		   
    		   actualizar();
    	   }else if (fuente==verFinales){
    		  
    		  if (this.tabla.getSelectedRowCount() == 1) {
    			  DefaultTableModel tm = (DefaultTableModel) this.tabla.getModel();
    			  new FormularioFinales((int)tm.getValueAt(this.tabla.getSelectedRow(), 0),(String)tm.getValueAt(this.tabla.getSelectedRow(), 1), this);
    		  }
    	   }
    	
    }
    
	public void actualizar() {

		DefaultTableModel modelo = new DefaultTableModel();
		AlumnoDAO alumnosResultSet = new AlumnoDAO();
		ConversorResultSetADefaultTableModel.rellena(alumnosResultSet.getAllAlumnos(), modelo);
		this.tomaDatos(modelo);

	}

}
