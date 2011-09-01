package br.edu.gamaesouza.intranet.params.impl;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.params.DisciplinaParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public @Data class DisciplinaAlteraParams implements DisciplinaParams{

	private Integer id;
	
	private String nome;
	
	private List<String> cursos;
	
	@Autowired CursoDAO cursoDAO;
	@Autowired DisciplinaDAO disciplinaDAO;
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Disciplina getDisciplina() throws IntranetException {
		Disciplina disciplina = disciplinaDAO.getDisciplinaById(id);
		disciplina.setNome(nome);
		disciplina.setCursos(cursoDAO.getCursoListByStringList(cursos,disciplina));
		return disciplina;
	}

}
