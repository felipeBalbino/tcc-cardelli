package br.edu.gamaesouza.intranet.params.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.Params;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class AlunoSearchParams implements Params {

	@Autowired PessoaDAO pessoaDAO;
	
	private String email;
	private String nome;
	private Integer matricula;
	

	
	
	@Override
	public boolean isEmpty() {
		if ((email == null || email.equals("")) &&
			(matricula == null || matricula.equals("")) &&
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

	public void setMatricula(String matricula) {
		this.matricula = Integer.parseInt(matricula);
	}

}
