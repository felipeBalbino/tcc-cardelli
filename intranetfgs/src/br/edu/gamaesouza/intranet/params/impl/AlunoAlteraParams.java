package br.edu.gamaesouza.intranet.params.impl;



import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.params.AlunoParams;

import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;
import br.edu.gamaesouza.intranet.utils.StatusMatriculaEnum;

public class AlunoAlteraParams implements AlunoParams {

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

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	public void setMatricula(String matricula) {
		this.matricula = Integer.parseInt(matricula);
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setStatusMatricula(StatusMatriculaEnum statusMatricula) {
		this.statusMatricula = statusMatricula;
	}

	public StatusMatriculaEnum getStatusMatricula() {
		return statusMatricula;
	}

}
