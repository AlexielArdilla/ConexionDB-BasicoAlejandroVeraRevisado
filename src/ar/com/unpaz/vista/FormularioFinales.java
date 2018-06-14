package ar.com.unpaz.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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

import ar.com.unpaz.database.FinalNotaDAO;
import ar.com.unpaz.database.ImpresoraTXTdeTramites;
import ar.com.unpaz.database.ValidatorNombreFinales;
import ar.com.unpaz.model.ConversorResultSetADefaultTableModel;

public class FormularioFinales  extends JFrame  implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idAlumno;
	private String nombre;
	private JTable table = new JTable();
    private JButton agregar = new JButton("Agregar Final");
    private JButton borrar = new JButton("Borrar Final");
    private JButton help_id_Materias = new JButton("Listado IDs Materias");
    private JButton imprimir_analitico = new JButton("Imprimir Analitico");
    
    private FormularioAlumnos miFormAlumnos;

	public FormularioFinales(int idAlumno, String nombre, FormularioAlumnos form_alumnos) {
		
		this.idAlumno = idAlumno;
		this.setNombre(nombre);
		createUserInterface();
		DefaultTableModel modelo = new DefaultTableModel();
		FinalNotaDAO finalesResultSet = new FinalNotaDAO();
		ConversorResultSetADefaultTableModel.rellena(finalesResultSet.getAllFinlasByAlumno(this.idAlumno), modelo);
		this.table.setModel(modelo);
		this.miFormAlumnos = form_alumnos;
		
	}

	
	private void createUserInterface() {
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(new JScrollPane(this.table));
		this.setTitle("Finales");
		
		JPanel datos = new JPanel();
		datos.add(new JLabel("Legajo: " + this.getIdAlumno() + " - " + this.getNombre()));
		this.add(datos, BorderLayout.NORTH);
		
	    JPanel operaciones = new JPanel();
	    
    	operaciones.add(new JLabel("Operaciones:"));
    	agregar.addActionListener(this);
    	borrar.addActionListener(this);
    	help_id_Materias.addActionListener(this);
    	imprimir_analitico.addActionListener(this);
    	
    	/* Agrego los botones al Formulario */
    	operaciones.add(agregar);
    	operaciones.add(borrar);
    	operaciones.add(help_id_Materias);
    	operaciones.add(imprimir_analitico);
    	this.add(operaciones, BorderLayout.SOUTH);
	    
		this.pack();
		this.setVisible(true);
	}
	
	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object fuente = e.getSource();
		String final_string = null;

		if (fuente == agregar) {

			final_string = JOptionPane.showInputDialog(null, "ingrese: ID Materia, nota");// aca
			ValidatorNombreFinales miVali = new ValidatorNombreFinales(idAlumno);
			miVali.agregaFinal(final_string);
			miFormAlumnos.actualizar();
			this.actualizar();

		} else if (fuente == borrar) {

			if (this.table.getSelectedRowCount() == 1) {
				if (JOptionPane.showConfirmDialog(null, "Seguro desea borrar el final?", "WARNING",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					FinalNotaDAO finales = new FinalNotaDAO();
					DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
					int r = finales.borrarFinal(this.getIdAlumno(),
							(int) modelo.getValueAt(this.table.getSelectedRow(), 0),
							(BigDecimal) modelo.getValueAt(this.table.getSelectedRow(), 2));
					if (r == 1)
						modelo.removeRow(this.table.getSelectedRow());
				}
			}

			miFormAlumnos.actualizar();

		} else if (fuente == help_id_Materias) {

			new FormularioMateriasID();

		} else if (fuente == imprimir_analitico) {

			JFileChooser mi_chooser = new JFileChooser();

			mi_chooser.setAcceptAllFileFilterUsed(false);

			String ruta = "";

			try {

				if (mi_chooser.showSaveDialog(null) == mi_chooser.APPROVE_OPTION) {
					ruta = mi_chooser.getSelectedFile().getAbsolutePath();

					ImpresoraTXTdeTramites miImpresora = new ImpresoraTXTdeTramites();

					LinkedList<String> a_escribir = miImpresora
							.resultSetALinkedListStringParaAnalitico(idAlumno,new FinalNotaDAO().getAllFinlasByAlumno(this.idAlumno));

					miImpresora.escribeLineas(ruta, a_escribir);

					JOptionPane.showMessageDialog(null, "Guardado con éxito!!!");

				}
			} catch (Exception ex) {

				JOptionPane.showMessageDialog(null, "No se pudo guardar...");
			}

		}

	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	public void actualizar(){
		
		DefaultTableModel modelo = new DefaultTableModel();
		FinalNotaDAO finalesResultSet = new FinalNotaDAO();
		ConversorResultSetADefaultTableModel.rellena(finalesResultSet.getAllFinlasByAlumno(this.idAlumno), modelo);
		this.table.setModel(modelo);
		
	}
}
