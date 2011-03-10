package br.edu.gamaesouza.intranet.params.impl;



import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.params.AlunoParams;

import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public class AlunoNovoParams implements AlunoParams {

	
	private String login;
	private String senha;
	private String email;
	private String nome;
	private Integer cursoId;
	private Integer matricula;
	
	

	@Autowired private CursoDAO cursoDAO;
	
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
		aluno.setMatricula(matricula);
		
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

}
