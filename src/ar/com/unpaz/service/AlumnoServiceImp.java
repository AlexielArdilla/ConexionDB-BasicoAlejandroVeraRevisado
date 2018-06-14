package ar.com.unpaz.service;

import java.util.List;

import ar.com.unpaz.database.AlumnoDAO;
import ar.com.unpaz.model.Alumno;

public class AlumnoServiceImp implements AlumnoService {
	public List<Alumno> getAlumnos(){
		return new AlumnoDAO().getAlumnos();
	}
}
