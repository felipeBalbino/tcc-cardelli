package br.edu.gamaesouza.intranet.params.impl;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.Params;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.StatusMatriculaEnum;

public @Data  class AlunoSearchParams implements Params {

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

}
