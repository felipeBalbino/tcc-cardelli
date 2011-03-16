package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.params.Params;



public class AlunoSearchParams implements Params {


	private String email;
	private String nome;
	private Integer matricula;
	
	
	
	@Override
	public boolean isEmpty() {
		if ((email == null || email.equals("")) &&
			(matricula == null || matricula.equals("")) &&
			(nome == null || nome.equals(""))){
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
