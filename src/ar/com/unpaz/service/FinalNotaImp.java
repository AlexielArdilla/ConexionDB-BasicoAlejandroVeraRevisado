package ar.com.unpaz.service;

import java.util.List;

import ar.com.unpaz.database.FinalNotaDAO;
import ar.com.unpaz.model.FinalNota;

public class FinalNotaImp implements FinalNotaService{

	@Override
	public List<FinalNota> getFinalesByIdAlumno(int id_alumno) {
		
		return new FinalNotaDAO().getNotasByIDAlumno(id_alumno);
		
	}

	@Override
	public List<FinalNota> getFullQueryByNota(double nota) {
		
		return new FinalNotaDAO().getFullQueryByNota(nota);
	}

}
