package br.edu.gamaesouza.intranet.params.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.ProfessorParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public class ProfessorAlteraParams implements ProfessorParams {

	@Autowired private PessoaDAO pessoaDAO;
	
	private Integer id;
	private String nome;
	private Integer matricula;
	private String login;
	private String senha;
	private String email;
	
	private List<String> rules;
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Professor getProfessor() throws IntranetException {
		Professor professor = (Professor) SpringUtil.getBean("professor");
		professor.setId(id);
		professor.setEmail(email);
		professor.setLogin(login);
		professor.setMatricula(matricula);
		professor.setNome(nome);
		professor.setSenha(senha);
		professor.setDataUltimoAcesso(Calendar.getInstance());
		return professor;
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

	public List<String> getRules() {
		return rules;
	}

	public void setRules(List<String> rules) {
		this.rules = rules;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	

}
