package br.edu.gamaesouza.intranet.params.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.Params;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.StatusMatriculaEnum;

public class AlunoSearchParams implements Params {

	@Autowired PessoaDAO pessoaDAO;
	
	private String email;
	private String nome;
	private Integer matricula;
	private Integer cursoId;
	private StatusMatriculaEnum statusMatricula;
	private Integer periodo;
	

	
	
	@Override
	public boolean isEmpty() {
		if ((email == null || email.equals("")) &&
			(matricula == null || matricula.equals("")) &&
			(cursoId == null || cursoId.equals("") || cursoId == -1 ) &&
			(statusMatricula == null || statusMatricula.equals("") ) &&
			(periodo == null || periodo.equals("") ) &&
			(nome == null || nome.equals("")) ){
			return true;
		}
		return false;
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




	public Integer getMatricula() {
		return matricula;
	}


	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}




	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}




	public Integer getCursoId() {
		return cursoId;
	}




	public void setStatusMatricula(StatusMatriculaEnum statusMatricula) {
		this.statusMatricula = statusMatricula;
	}




	public StatusMatriculaEnum getStatusMatricula() {
		return statusMatricula;
	}




	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}




	public Integer getPeriodo() {
		return periodo;
	}

}
