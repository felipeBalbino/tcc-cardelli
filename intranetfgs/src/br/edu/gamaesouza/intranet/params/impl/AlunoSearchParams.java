package br.edu.gamaesouza.intranet.params.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.Params;
import br.edu.gamaesouza.intranet.utils.IntranetException;



public class AlunoSearchParams implements Params {


	private String email;
	private String nome;
	private Integer matricula;
	
	@Autowired PessoaDAO pessoaDAO;
	
	
	@Override
	public boolean isEmpty() {
		if ((email == null || email.equals("")) &&
			(matricula == null || matricula.equals("")) &&
			(nome == null || nome.equals(""))){
			return true;
		}
		return false;
	}

	
	public Aluno getAlunoByMatricula() throws IntranetException{
		return pessoaDAO.getAlunoByMatricula(matricula);
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


	public void setMatricula(String matricula) {
		if(!matricula.equals( "" )){
		this.matricula = Integer.parseInt(matricula);
		}else{
		this.matricula = null;
		}
	}

	public Integer getMatricula() {
		return matricula;
	}

}
