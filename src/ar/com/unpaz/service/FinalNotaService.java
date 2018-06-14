package ar.com.unpaz.service;


import java.util.List;
import ar.com.unpaz.model.FinalNota;

public interface FinalNotaService {
	
	public List<FinalNota> getFinalesByIdAlumno(int id_alumno);
	public List<FinalNota> getFullQueryByNota(double nota);
}
