package br.edu.gamaesouza.intranet.params.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.params.DisciplinaParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class DisciplinaDeletaParams implements DisciplinaParams{

	private Integer id;
	@Autowired private DisciplinaDAO disciplinaDAO;
	
	public Disciplina getDisciplina() throws IntranetException {
		return disciplinaDAO.getDisciplinaById(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
