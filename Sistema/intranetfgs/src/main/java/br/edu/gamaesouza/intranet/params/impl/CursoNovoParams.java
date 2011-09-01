package br.edu.gamaesouza.intranet.params.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Noticia;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.params.CursoParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public @Data class CursoNovoParams implements CursoParams{

	private String nomeCurso;
	private Integer cargaHorariaComplementar;
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
			curso.setCargaHorariaComplementar(cargaHorariaComplementar);
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		curso.setNome(nomeCurso);
		
		return curso;
	}

}
