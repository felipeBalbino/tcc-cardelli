package br.edu.gamaesouza.intranet.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Noticia;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.bean.Rule;
import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class PessoaDAO {
	


	
	public Boolean validarEmail(String email) throws IntranetException {
		Session session = CustomSession.getSession();
		Criteria c = session.createCriteria(Pessoa.class);
		c.add(Restrictions.eq("email", email));
		
		Pessoa pessoa =(Pessoa) c.uniqueResult();
		session.close();
		if(pessoa == null){
			return false;
		}else{
			return true;
		}

	}
	

	public Boolean validarLogin(String login) throws IntranetException {
		Session session = CustomSession.getSession();
		Criteria c = session.createCriteria(Pessoa.class);
		c.add(Restrictions.eq("login", login));
		
		Pessoa pessoa =(Pessoa) c.uniqueResult();
		
		if(pessoa == null){
			return false;
		}else{
			return true;
		}

	}
	
	public Boolean validarMatricula(Integer matricula) throws IntranetException {
		Session session = CustomSession.getSession();
		Criteria c = session.createCriteria(Pessoa.class);
		c.add(Restrictions.eq("matricula", matricula));
		
		Pessoa pessoa =(Pessoa) c.uniqueResult();
		
		if(pessoa == null){
			return false;
		}else{
			return true;
		}

	}
	
	
	
	
	public Professor getProfessor(String login, String senha) throws IntranetException {
		Session session = CustomSession.getSession();
		Criteria c = session.createCriteria(Professor.class);
		c.add(Restrictions.eq("login", login));
		c.add(Restrictions.eq("senha", senha));
		Professor professor = (Professor) c.uniqueResult();
		
		return professor;
	}
	
	public Pessoa getPessoa(String login, String senha) throws IntranetException{
		Session session = CustomSession.getSession();
		Criteria c = session.createCriteria(Pessoa.class);
		c.add(Restrictions.eq("login", login));
		c.add(Restrictions.eq("senha", senha));
		
		Pessoa pessoa = (Pessoa) c.uniqueResult();
		
		return pessoa;
	}
	

	public void save(Professor professor) throws IntranetException{
		Session session = CustomSession.getSession();
		session.save(professor);
		
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		session.close();
	}
	
	
	public void saveAluno(Aluno aluno) throws IntranetException{
		Session session = CustomSession.getSession();
		session.save(aluno);
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		session.close();
	}
	
	public void update(Pessoa pessoa) throws IntranetException{
		Session session = CustomSession.getSession();	
		Transaction transaction = session.beginTransaction();
		session.update(pessoa);
		transaction.commit();
		session.close();
	}
	
	public void merge(Pessoa pessoa)throws IntranetException {
		Session session = CustomSession.getSession();
		
		Transaction transaction = session.beginTransaction();
		session.merge(pessoa);
		transaction.commit();
		session.close();
	}
	
	public void update(Pessoa pessoa,List<Rule> rules)throws IntranetException {
		Session session = CustomSession.getSession();
		Transaction transaction = session.beginTransaction();
		
		
		Pessoa altera = getPessoaById(pessoa.getId());
		altera.setEmail(pessoa.getEmail());
		altera.setLogin(pessoa.getLogin());
		altera.setNome(pessoa.getNome());
		altera.setRegras(rules);
		altera.setSenha(pessoa.getSenha());
	
		session.update(altera);
		
		transaction.commit();
		session.close();
	}
	

	public List<Professor> getAll()throws IntranetException{
		Session session = CustomSession.getSession();
		Criteria c = session.createCriteria(Professor.class);
		List<Professor> professores = c.list();
		return professores;
	}
	
	public List<Rule> getAllRules()throws IntranetException{
		Session session = CustomSession.getSession();
		Criteria c  = session.createCriteria(Rule.class);
		List<Rule> rules = c.list();
			session.close();
		return rules;
	}
	
	public Pessoa getPessoaById(Integer id)throws IntranetException{
		Session session = CustomSession.getSession();
		Criteria c = session.createCriteria(Pessoa.class);
		c.add(Restrictions.eq("id", id));
		Pessoa pessoa = (Pessoa) c.uniqueResult();
			session.close();
		return pessoa;
		
	}
	
	public Professor getProfessorById(Integer id)throws IntranetException{
		Session session = CustomSession.getSession();
		Criteria c = session.createCriteria(Professor.class);
		c.add(Restrictions.eq("id", id));
		Professor professor =(Professor) c.uniqueResult();
			session.close();
		return professor;
		
	}

	public List<Rule> getRuleListByStringList(List<String> paramRules) throws IntranetException{
		
		List<Rule> rules = new ArrayList<Rule>();
		
		for(String rule : paramRules){
			rules.add(getRuleByNome(rule));
		}
		
		return rules;
		
	}
	
	public Rule getRuleByNome(String ruleName)throws IntranetException{
		Session session = CustomSession.getSession();
		Criteria c = session.createCriteria(Rule.class);
		c.add(Restrictions.eq("nome", ruleName));
		Rule rule =(Rule) c.uniqueResult();
			session.close();
		return rule;
	}
	
	public void deleteProfessor(Professor professor)throws IntranetException{
		Session session = CustomSession.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(professor);
		transaction.commit();	
		session.flush();
		session.close();
	}

}
