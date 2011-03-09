package br.edu.gamaesouza.intranet.params.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Noticia;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.params.CursoParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public class CursoNovoParams implements CursoParams{

	private String nomeCurso;
	private List<String> disciplinasCurso;
	
	@Autowired private CursoDAO cursoDAO;
	
	public CursoNovoParams(){
		disciplinasCurso = new ArrayList<String>();
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Curso getCurso() {
		Curso curso = (Curso) SpringUtil.getBean("curso");
		try {
			curso.setDisciplinas(cursoDAO.getDisciplinaListByStringList(disciplinasCurso));
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		curso.setNome(nomeCurso);
		
		return curso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public List<String> getDisciplinasCurso() {
		return disciplinasCurso;
	}

	public void setDisciplinasCurso(List<String> disciplinasCurso) {
		this.disciplinasCurso = disciplinasCurso;
	}

}
