package ar.com.unpaz.main;

import java.util.Scanner;

import ar.com.unpaz.database.AlumnoDAO;
import ar.com.unpaz.database.MateriaDAO;
import ar.com.unpaz.model.Alumno;
import ar.com.unpaz.model.FinalNota;
import ar.com.unpaz.model.Materia;
import ar.com.unpaz.service.AlumnoService;
import ar.com.unpaz.service.AlumnoServiceImp;
import ar.com.unpaz.service.FinalNotaImp;
import ar.com.unpaz.service.FinalNotaService;
import ar.com.unpaz.service.MateriaService;
import ar.com.unpaz.service.MateriaServiceImp;

public class PrincipalTest {

	public static void main(String[] args) {
		AlumnoService alumnoService = new AlumnoServiceImp();
		MateriaService materiaService = new MateriaServiceImp();//ahhh esto fabrica objetos!!
		FinalNotaService finalService = new FinalNotaImp();
		
		System.out.println("Query prueba Alumnos:\n");
		
		for (Alumno alumno: alumnoService.getAlumnos()){
			
			System.out.println("Id Alumno: " + alumno.getId() + " Nombre: " + alumno.getNombre());
			
		}
		
		System.out.println("\nQuery prueba Materias:\n");
		
		for(Materia m: materiaService.getMaterias()){
			
			System.out.println("Materia ID: "+ m.getId()+" Materia descripcion: "+m.getDescripcion());
			
		}
		
		System.out.println("\nQuery prueba Finales por ID de Alumno:\n");

		
		for (int i = 1; i <= 5; i++) {

			for (FinalNota f : finalService.getFinalesByIdAlumno(i)) {

				System.out.println("ID Alumno: "+f.getId_alumno()+" Nombre: "+ new AlumnoDAO().getNombreAlumnoByID(f.getId_alumno())+
						" Materia: "+new MateriaDAO().nombreMateriaPorID(f.getId_materia())+
						" Nota: "+ f.getNota());
			}//**for interno
		}//**for
	
		System.out.println("\n Prueba full query por Nota (Ingrese una Nota a buscar (Entre 1 y 10): ");
		
		Scanner miScanner = new Scanner(System.in);
		
		double nota_a_buscar = miScanner.nextDouble();
		
		for(FinalNota f:finalService.getFullQueryByNota(nota_a_buscar)){
			
			System.out.println("Final ID: "+f.getId()+" ID Alumno: "+f.getId_alumno()+" Id Materia: "+f.getId_materia()
			+" Nota: "+f.getNota());
			
		}
		miScanner.close();
	
	}

}
