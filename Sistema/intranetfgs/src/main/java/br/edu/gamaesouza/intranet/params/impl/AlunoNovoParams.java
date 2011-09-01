package br.edu.gamaesouza.intranet.params.impl;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.AreaProfissional;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.dao.AreaProfissionalDAO;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.params.AlunoParams;

import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;
import br.edu.gamaesouza.intranet.utils.StatusMatriculaEnum;

public @Data class AlunoNovoParams implements AlunoParams {

	
	private String login;
	private String senha;
	private String email;
	private String nome;
	private Integer cursoId;
	private Integer matricula;
	private Integer periodo;
	private StatusMatriculaEnum statusMatricula;
	private Long areaProfissionalId;
	

	@Autowired private CursoDAO cursoDAO;
	@Autowired private AreaProfissionalDAO areaProfissionalDAO;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Aluno getAluno(){
		Aluno aluno = (Aluno) SpringUtil.getBean("aluno");
		aluno.setLogin(login);
		aluno.setSenha(senha);
		aluno.setEmail(email);
		aluno.setNome(nome);
		aluno.setPeriodo(periodo);
		aluno.setMatricula(matricula);
		aluno.setStatusMatricula(statusMatricula);
		aluno.setDataUltimoAcesso(Calendar.getInstance());
		
		Curso curso = (Curso) SpringUtil.getBean("curso");
		AreaProfissional areaProfissional = (AreaProfissional) SpringUtil.getBean("areaProfissional");
		
	
		List<AreaProfissional> areas = new ArrayList<AreaProfissional>();
		try {
			curso = cursoDAO.getCursoById(cursoId);
			//Adicionar nova Area profissional.
			areaProfissional = areaProfissionalDAO.getAreaProfissionalById(areaProfissionalId);
			areas.add(areaProfissional);
			
		} catch (IntranetException e) {
			e.printStackTrace();
		}
		aluno.setCurso( curso );
		aluno.setAreasProfissionais(areas);
		
		
		return aluno;
	}


	public void setSenha(String senha) {
		this.senha = FormUtil.encripta(senha);
	}

	public void setMatricula(String matricula) {
		this.matricula = Integer.parseInt(matricula);
	}

}
