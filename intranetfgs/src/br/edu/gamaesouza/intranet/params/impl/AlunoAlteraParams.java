package br.edu.gamaesouza.intranet.params.impl;



import java.util.Calendar;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.params.AlunoParams;

import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;
import br.edu.gamaesouza.intranet.utils.StatusMatriculaEnum;

public @Data class AlunoAlteraParams implements AlunoParams {

	private Integer id;
	private String login;
	private String senha;
	private String email;
	private String nome;
	private Integer cursoId;
	private Integer matricula;
	private Integer periodo;
	private StatusMatriculaEnum statusMatricula;
	
	

	@Autowired private CursoDAO cursoDAO;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Aluno getAluno(){
		Aluno aluno = (Aluno) SpringUtil.getBean("aluno");
		aluno.setId(id);
		aluno.setLogin(login);
		aluno.setSenha(senha);
		aluno.setEmail(email);
		aluno.setNome(nome);
		aluno.setMatricula(matricula);
		aluno.setStatusMatricula(statusMatricula);
		aluno.setPeriodo(periodo);
		aluno.setDataUltimoAcesso(Calendar.getInstance());
		Curso curso = (Curso) SpringUtil.getBean("curso");
		try {
			curso = cursoDAO.getCursoById(cursoId);
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aluno.setCurso( curso );

		
		
		return aluno;
	}


	public void setSenha(String senha) {
		this.senha = FormUtil.encripta(senha);
	}


	public void setMatricula(String matricula) {
		this.matricula = Integer.parseInt(matricula);
	}





}
