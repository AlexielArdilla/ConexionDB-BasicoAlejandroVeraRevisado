package ar.com.unpaz.service;

import java.util.List;

import ar.com.unpaz.database.MateriaDAO;
import ar.com.unpaz.model.Materia;

public class MateriaServiceImp implements MateriaService{

	@Override
	public List<Materia> getMaterias() {
		
		return new MateriaDAO().getMaterias();
	}

}
