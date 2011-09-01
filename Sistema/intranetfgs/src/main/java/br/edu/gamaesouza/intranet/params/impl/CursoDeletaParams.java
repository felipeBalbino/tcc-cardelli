package br.edu.gamaesouza.intranet.params.impl;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.params.CursoParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public @Data class CursoDeletaParams implements CursoParams{

	private Integer id;
	@Autowired CursoDAO cursoDAO;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Curso getCurso() {
		// TODO Auto-generated method stub
		try {
			return cursoDAO.getCursoById(id);
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
