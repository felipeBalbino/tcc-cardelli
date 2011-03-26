package br.edu.gamaesouza.intranet.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Noticia;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.bean.Rule;
import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.params.impl.AlunoSearchParams;
import br.edu.gamaesouza.intranet.params.impl.CursoSearchParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class PessoaDAO extends HibernateDaoSupport {
	


	
	public Boolean validarEmail(String email) throws IntranetException {
		Criteria c = getSession().createCriteria(Pessoa.class);
		c.add(Restrictions.eq("email", email));
		Pessoa pessoa =(Pessoa) c.uniqueResult();
		
		
		if(pessoa == null){
			return false;
		}else{
			return true;
		}
		
		
		
	}
	

	public Boolean validarLogin(String login) throws IntranetException {
		Criteria c = getSession().createCriteria(Pessoa.class);
		c.add(Restrictions.eq("login", login));
		
		Pessoa pessoa =(Pessoa) c.uniqueResult();
		
		if(pessoa == null){
			return false;
		}else{
			return true;
		}

	}
	
	public Boolean validarMatricula(Integer matricula) throws IntranetException {
		Criteria c = getSession().createCriteria(Pessoa.class);
		c.add(Restrictions.eq("matricula", matricula));
		
		Pessoa pessoa =(Pessoa) c.uniqueResult();
		
		if(pessoa == null){
			return false;
		}else{
			return true;
		}

	}
	
	public Aluno getAlunoByMatricula(Integer matricula) throws IntranetException {
		Criteria c = getSession().createCriteria(Aluno.class);
		c.add(Restrictions.eq("matricula", matricula));
		Aluno pessoa =(Aluno) c.uniqueResult();
		return pessoa;

	}
	
	public Pessoa getPessoaByMatricula(Integer matricula) throws IntranetException{
		Criteria c = getSession().createCriteria( Pessoa.class );
		c.add( Restrictions.eq( "matricula", matricula ) );
		Pessoa pessoa = (Pessoa) c.uniqueResult();
		return pessoa;
	}
	
	
	
	public Professor getProfessor(String login, String senha) throws IntranetException {
		Criteria c = getSession().createCriteria(Professor.class);
		c.add(Restrictions.eq("login", login));
		c.add(Restrictions.eq("senha", senha));
		Professor professor = (Professor) c.uniqueResult();
		
		return professor;
	}
	
	public Pessoa getPessoa(String login, String senha) throws IntranetException{
		
		
		Query c = getSession().getNamedQuery("pessoaByLoginSenha");
		c.setString("login", login);
		c.setString("senha", senha);
		
		Pessoa pessoa = (Pessoa) c.uniqueResult();
		
		return pessoa;
	}
	

	public void save(Professor professor) throws IntranetException{
		getHibernateTemplate().save(professor);
	}
	
	
	public void saveAluno(Aluno aluno) throws IntranetException{
		getHibernateTemplate().save(aluno);
	}
	
	public void update(Pessoa pessoa) throws IntranetException{
		getHibernateTemplate().update(pessoa);
	}
	
	public void merge(Pessoa pessoa)throws IntranetException {
		
		getHibernateTemplate().merge(pessoa);
		
	}
	
	public void update(Pessoa pessoa,List<Rule> rules)throws IntranetException {
		Pessoa altera = getPessoaById(pessoa.getId());
		altera.setEmail(pessoa.getEmail());
		altera.setLogin(pessoa.getLogin());
		altera.setNome(pessoa.getNome());
		altera.setRegras(rules);
		altera.setSenha(pessoa.getSenha());

		getHibernateTemplate().update(altera);
	}
	

	public List<Professor> getAll()throws IntranetException{
		Criteria c = getSession().createCriteria(Professor.class);
		List<Professor> professores = c.list();
		return professores;
	}
	
	public List<Aluno> getAllAlunos()throws IntranetException{
		Criteria c = getSession().createCriteria(Aluno.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Aluno> Alunos = c.list();
		return Alunos;
	}
	
	public List<Aluno> getAllAlunosByParams(AlunoSearchParams alunoSearchParams) {
		
		boolean operator = false;
		String query = "SELECT a FROM Aluno a ";
		
		if (!alunoSearchParams.isEmpty()){
			query =  query + "WHERE ";
			
			if(! ( alunoSearchParams.getMatricula() == null )){
				query = query + "matricula = '" + alunoSearchParams.getMatricula() + "'";
				operator = true;
			}
			
			if(! ( alunoSearchParams.getPeriodo() == null )){
				if(operator){
					query = query + " AND ";
				}
				query = query + "periodo = '" + alunoSearchParams.getPeriodo() + "'";
				operator = true;
			}
			
			if(! ( alunoSearchParams.getCursoId() == null )){
				if(operator){
					query = query + " AND ";
				}
				query = query + "curso_id = '" + alunoSearchParams.getCursoId() + "'";
				operator = true;
			}
			
			if(alunoSearchParams.getStatusMatricula()!= null && !alunoSearchParams.getStatusMatricula().equals("")){
				if(operator){
					query = query + " AND ";
				}	
				query = query + "statusMatricula LIKE '%" + alunoSearchParams.getStatusMatricula() + "%'";	
				operator = true;
			}

			if(alunoSearchParams.getNome()!= null && !alunoSearchParams.getNome().equals("")){
				if(operator){
					query = query + " AND ";
				}	
				query = query + "nome LIKE '%" + alunoSearchParams.getNome() + "%'";	
				operator = true;
			}
			
			if(alunoSearchParams.getEmail() != null && !alunoSearchParams.getEmail().equals("")){
				if(operator){
					query = query + " AND ";
				}	
				query = query + " email LIKE '%" + alunoSearchParams.getEmail() + "%'";			
			}
			
		}
		
		Query alunosByParams = getSession().createQuery(query);
		List<Aluno> alunos =  alunosByParams.list();
		return alunos;
	}
	
	public List<Rule> getAllRules()throws IntranetException{
		Criteria c = getSession().createCriteria(Rule.class);
		List<Rule> rules = c.list();
		return rules;
	}
	
	public Pessoa getPessoaById(Integer id)throws IntranetException{
		Query c = getSession().getNamedQuery("pessoaById");
		c.setInteger("id", id);
		Pessoa pessoa = (Pessoa) c.uniqueResult();
		return pessoa;	
	}
	
	public Aluno getAlunoById(Integer id)throws IntranetException{
		Query c = getSession().getNamedQuery("alunoById");
		c.setInteger("id", id);
		Aluno aluno = (Aluno) c.uniqueResult();
		return aluno;	
	}
	
	public Pessoa getPessoaByEmail(String id)throws IntranetException{
		Query c = getSession().getNamedQuery("pessoaById");
		c.setString("email", id);
		Pessoa pessoa = (Pessoa) c.uniqueResult();
		return pessoa;	
		
	}
	
	public Professor getProfessorById(Integer id)throws IntranetException{
		Query c = getSession().getNamedQuery("pessoaById");
		c.setInteger("id", id);
		Professor pessoa = (Professor) c.uniqueResult();
		return pessoa;	
		
	}
	
	public List<Aluno> getAlunosByCurso(Curso curso)throws IntranetException{
		Query c = getSession().getNamedQuery("alunosByCurso");
		c.setInteger("curso", curso.getId());
		List<Aluno> alunos = c.list();
		return alunos;
		
	}

	public List<Rule> getRuleListByStringList(List<String> paramRules) throws IntranetException{
		
		List<Rule> rules = new ArrayList<Rule>();
		
		for(String rule : paramRules){
			rules.add(getRuleByNome(rule));
		}
		
		return rules;
		
	}
	
	public Rule getRuleByNome(String ruleName)throws IntranetException{
		Criteria c = getSession().createCriteria(Rule.class);
		c.add(Restrictions.eq("nome", ruleName));
		Rule rule =(Rule) c.uniqueResult();
		return rule;
	}
	
	public void deleteProfessor(Professor professor)throws IntranetException{	
		getHibernateTemplate().delete(professor);
	}

}
