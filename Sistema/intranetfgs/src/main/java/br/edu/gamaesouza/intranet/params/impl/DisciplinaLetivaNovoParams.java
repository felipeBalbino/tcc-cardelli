package br.edu.gamaesouza.intranet.params.impl;

import java.util.Calendar;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.DisciplinaLetivaParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public @Data class DisciplinaLetivaNovoParams implements DisciplinaLetivaParams {

	private Integer id;
	private Integer materia;
	private Integer professorid;
	private Integer semestre;
	private String turno;
	private Integer ano =  Calendar.getInstance().get(Calendar.YEAR);
	private String sala;
	
	@Autowired private PessoaDAO pessoaDao;
	@Autowired private DisciplinaDAO disciplinaDAO;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public DisciplinaLetiva getDisciplinaLetiva(){
		DisciplinaLetiva disciplinaLetiva = (DisciplinaLetiva) SpringUtil.getBean("disciplinaLetiva");
		try {
			Disciplina disc = disciplinaDAO.getDisciplinaById(materia);
			disciplinaLetiva.setDisciplina(disc);
			disciplinaLetiva.setSemestre(semestre);
			disciplinaLetiva.setTurno(turno);
			disciplinaLetiva.setAno(ano);
			disciplinaLetiva.setSala(sala);
			disciplinaLetiva.setProfessor(pessoaDao.getProfessorById(professorid));
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return disciplinaLetiva;
		
	}

}
