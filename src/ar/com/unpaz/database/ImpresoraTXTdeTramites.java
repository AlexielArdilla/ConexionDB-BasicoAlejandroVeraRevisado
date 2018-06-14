package ar.com.unpaz.database;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ImpresoraTXTdeTramites {
	
	private int codigo;
	private String nombre;
	private String materia;
	private double nota;
	private String resultado;
	
	
	public LinkedList<String> resultSetALinkedListString(ResultSet rs)throws SQLException {

		LinkedList<String> mis_strings = new LinkedList<>();
		
		mis_strings.add("Materias con nota espefica:");
		mis_strings.add("");
		
		while(rs.next()){
			
			nombre = rs.getString(1);
			materia = rs.getString(2);
			nota = rs.getDouble(3);
			
			mis_strings.add("Nombre: "+nombre+" Materia: "+materia+" Nota: "+nota);
		}
		
		mis_strings.add("");
		mis_strings.add("Direccion General UNPAZ");
		
		return mis_strings;
	}
	
	public LinkedList<String> resultSetALinkedListStringParaAnalitico(int id_al, ResultSet rs)throws SQLException {

		LinkedList<String> mis_strings = new LinkedList<>();
		AlumnoDAO mi_alumno = new AlumnoDAO();
		nombre = mi_alumno.getNombreAlumnoByID(id_al);
		
		mis_strings.add("Analitico Carrera Analista de sistemas, Estudiante: "+ nombre);
		mis_strings.add("");
		
		while(rs.next()){
			
			codigo = rs.getInt(1);
			materia = rs.getString(2);
			nota = rs.getDouble(3);
			resultado = rs.getString(4);
			
			mis_strings.add(" Codigo Materia: "+codigo+" Nombre: "+materia+" Nota: "+nota+" Resultado: "+ resultado);
		}
		
		mis_strings.add("");
		mis_strings.add("Direccion General UNPAZ");
		
		return mis_strings;
	}
	
	
public void escribeLineas(String nombre_archivo,LinkedList<String> lineasAEscribir){//Anda perfecto!!!******************
		
		
		Path path_salida = Paths.get(nombre_archivo+".txt");
		try {
			
			BufferedWriter writer = Files.newBufferedWriter(path_salida, Charset.defaultCharset());
			
			for(String s: lineasAEscribir){
			
			String linea = s;
			
			writer.write(linea);
			
			writer.newLine();}
			
			writer.close();
			
			
		
		} catch (IOException x) {
		
			System.err.format("IOException:%s%n", x);
		
		}finally {
	   //**********************************************************************************************
		}
		
	}

}
