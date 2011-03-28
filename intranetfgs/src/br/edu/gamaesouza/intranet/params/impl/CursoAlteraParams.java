package br.edu.gamaesouza.intranet.params.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.params.CursoParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

/**
 * @author Gabriel Cardelli
 *
 */
public class CursoAlteraParams implements CursoParams {
	
	private Integer id;
	
	private String nomeCurso;
	
	private Integer cargaHorariaComplementar;
	
	private List<String> disciplinas;
	
	@Autowired private CursoDAO cursoDAO;
	
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Curso getCurso() {
		Curso curso = (Curso) SpringUtil.getBean("curso");
		curso.setId(id);
		curso.setNome(nomeCurso);
		curso.setCargaHorariaComplementar(cargaHorariaComplementar);
		try {
			curso.setDisciplinas(cursoDAO.getDisciplinaListByStringList(disciplinas));
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public List<String> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<String> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCargaHorariaComplementar(Integer cargaHorariaComplementar) {
		this.cargaHorariaComplementar = cargaHorariaComplementar;
	}

	public Integer getCargaHorariaComplementar() {
		return cargaHorariaComplementar;
	}

	
	
}
