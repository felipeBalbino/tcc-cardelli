
package br.edu.gamaesouza.intranet.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaSearchParams;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaSearchParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class DisciplinaDAO extends GenericDAO<Disciplina> {

	
	private CustomSession customSession;
	private Session session;
	private Transaction transaction;
	
	public DisciplinaDAO() {
		
	}
	
	
	public List<Disciplina> getAll() throws IntranetException {
		
		this.session = CustomSession.getSession();
		
		Criteria c = session.createCriteria(Disciplina.class);
		c.addOrder(Order.asc("nome"));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Disciplina> ds =  c.list();
		
		session.close();
		
		return ds;
	}
	

	public List<DisciplinaLetiva> getAllDisciplinaLetivas() throws IntranetException {
		
		this.session = CustomSession.getSession();
		
		Criteria c = session.createCriteria(DisciplinaLetiva.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetiva> ds =  c.list();
		
		session.close();
		
		return ds;
	}
	
	
	public void deleteDisciplina(Disciplina disciplina) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.delete(disciplina);
		transaction.commit();	
		session.flush();
		this.session.close();
	}
	
	

	
	public void save(Disciplina t) throws IntranetException{
		this.session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.save(t);
		for(Curso curso : t.getCursos()){
			curso.getDisciplinas().add(t);
			session.merge(curso);
		}
		
		
		transaction.commit();
		session.close();
		
	}
	
	
	public Disciplina getDisciplinaByNome(String nome) throws IntranetException{
		
		this.session = CustomSession.getSession();
		Criteria c = session.createCriteria(Disciplina.class);
		
		c.add(Restrictions.eq("nome", nome));
		Disciplina disciplina = (Disciplina) c.uniqueResult();
		this.session.close();
		return disciplina;
		
	}
	
	public Disciplina getDisciplinaById(Integer id) throws IntranetException{
		
		this.session = CustomSession.getSession();
		Criteria c = session.createCriteria(Disciplina.class);
		
		c.add(Restrictions.eq("id", id));
		Disciplina disciplina = (Disciplina) c.uniqueResult();
		this.session.close();
		return disciplina;
		
	}
	
	public DisciplinaLetiva getDisciplinaLetivaById(Integer id) throws IntranetException{
		
		this.session = CustomSession.getSession();
		Criteria c = session.createCriteria(DisciplinaLetiva.class);
	
		c.add(Restrictions.eq("id", id));
		DisciplinaLetiva disciplina = (DisciplinaLetiva) c.uniqueResult();
		this.session.close();
		return disciplina;
	
	}
	
	public List<DisciplinaLetiva> getDisciplinaLetivaByIdTheDisciplina(Integer id) throws IntranetException{
		this.session = CustomSession.getSession();
		String query = "FROM DisciplinaLetiva dl WHERE disciplina_id = " + id;
		Query c = session.createQuery(query);
		List<DisciplinaLetiva> disciplinasLetivas = (List<DisciplinaLetiva>) c.list();
		this.session.close();
		return disciplinasLetivas;
	
	}
	
	public void deleteDisciplinaLetiva(DisciplinaLetiva disciplinaLetiva) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.delete(disciplinaLetiva);
		transaction.commit();	
		session.flush();
		this.session.close();
	}
	
	public void updateDisciplinaLetiva(DisciplinaLetiva disciplinaLetiva) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.update(disciplinaLetiva);
		transaction.commit();	
		session.flush();
		this.session.close();
	}

	
	public DisciplinaLetiva saveOrReturnDisciplinaLetiva(DisciplinaLetiva dl) throws IntranetException{		
		this.session = CustomSession.getSession();
		
		String query = "FROM DisciplinaLetiva dl WHERE dl.disciplina.id = " + dl.getDisciplina().getId();
		query = query + " AND ano = " +  dl.getAno();
		query = query + " AND semestre = " + dl.getSemestre();

		
		Query c = session.createQuery(query);
		
		DisciplinaLetiva disciplinaLetiva = (DisciplinaLetiva) c.uniqueResult();
		
		if(disciplinaLetiva != null){
			this.session.close();
			return disciplinaLetiva;
		}else{
			this.session = CustomSession.getSession();
			transaction = session.beginTransaction();
			session.save(dl);
			transaction.commit();
			session.close();
			return dl;
		}
	}
	
	public List<DisciplinaLetiva> getDisciplinaLetiva(Integer ano, Integer semestre, String turno)throws IntranetException{
		this.session = CustomSession.getSession();
		Criteria c = session.createCriteria(DisciplinaLetiva.class);
		
		c.add(Restrictions.eq("ano", ano));
		c.add(Restrictions.eq("semestre", semestre));
		c.add(Restrictions.eq("turno", turno));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetiva> disciplinasLetivas = c.list();
		this.session.close();
		return disciplinasLetivas;
		
		
	}
	
	public boolean setPessoaFollowDisciplinhaLetiva(Pessoa pessoa, Integer disciplina , Integer ano, Integer semestre) throws IntranetException{
		this.session = CustomSession.getSession();
		String queryVerifySql = "FROM DisciplinaLetiva dl left join fetch dl.aluno aluno WHERE aluno.id = " + pessoa.getId() + " AND dl.disciplina.id =  " + disciplina + " AND dl.ano = " + ano + " AND dl.semestre = " + semestre;
		Query queryVerify = session.createQuery(queryVerifySql);
		DisciplinaLetiva dlVerify = (DisciplinaLetiva) queryVerify.uniqueResult();
		
		if(dlVerify == null){
			
			
			String query = "FROM DisciplinaLetiva WHERE ano = " + ano + " AND semestre = " + semestre + " AND disciplina.id = " + disciplina;
			
			
			Query c = session.createQuery(query);
			

			DisciplinaLetiva dl = (DisciplinaLetiva) c.uniqueResult();
			
			System.out.println(dl);
			
			List<Aluno> alunos = dl.getAluno();
			
			if(alunos == null){
				alunos = new ArrayList<Aluno>();
				alunos.add((Aluno) pessoa);
				dl.setAluno( alunos );
			}else{
				
				dl.getAluno().add( (Aluno) pessoa );
				
			}
			
			this.session = CustomSession.getSession();
			transaction = session.beginTransaction();
			session.update(dl);
			transaction.commit();
			session.close();
			return false;
			
		}else{
			this.session.close();
			return true;
		}
		
		
		
		
		
		
	}


	public List<DisciplinaLetiva> getDisciplinaLetivaByUser(Pessoa loggedUser) throws IntranetException{
		System.out.println("ID DO CARA: " +  loggedUser.getId());
		String sql = "FROM DisciplinaLetiva d left join fetch d.aluno aluno WHERE  aluno.id = " + loggedUser.getId();
		this.session = CustomSession.getSession();
		Query query = session.createQuery(sql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetiva> disciplinasLetivas = (List<DisciplinaLetiva>)query.list();
		this.session.close();
		return disciplinasLetivas;
		
	}


	public DisciplinaLetiva getAlunosByDL( Integer id ) throws IntranetException{
		this.session = CustomSession.getSession();
		Criteria c = session.createCriteria(DisciplinaLetiva.class);
		c.add(Restrictions.eq("id", id));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	
		DisciplinaLetiva disciplinaLetiva = (DisciplinaLetiva) c.uniqueResult();
		this.session.close();
		return  disciplinaLetiva;

	
	}
	
	public void merge(Disciplina t) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.merge(t);
		transaction.commit();
		this.session.close();
	}


	public List<Disciplina> getAllByParams(
			DisciplinaSearchParams disciplinaSearchParams) {
			
		this.session = CustomSession.getSession();
		boolean operator = false;
		String query = "FROM Disciplina d left outer join fetch d.cursos curso ";
		
		if (!disciplinaSearchParams.isEmpty()){
			query =  query + "WHERE ";
			
			if(!disciplinaSearchParams.getCursoNome().equals("")){
				query = query + "curso.nome = '" + disciplinaSearchParams.getCursoNome() + "'";
				operator = true;
			}
			
			if(!disciplinaSearchParams.getDisciplinaNome().equals("")){
				if(operator){
					query = query + " AND ";
				}
				
				query = query + "d.nome LIKE '%" + disciplinaSearchParams.getDisciplinaNome() + "%'";
				
			}
			
		}
		
		Query hibernateQuery = session.createQuery(query);
		hibernateQuery.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Disciplina> ds =  hibernateQuery.list();
		
		session.close();
		
		return ds;
	}
	
	
	public List<DisciplinaLetiva> getAllByParamsDisciplinaLetiva(
			DisciplinaLetivaSearchParams disciplinaLetivaSearchParams) {
			
		
		this.session = CustomSession.getSession();
		boolean operator = false;
		String query = "FROM DisciplinaLetiva  d left outer join fetch d.disciplina disciplina ";
		
		if (!disciplinaLetivaSearchParams.isEmpty()){
			query =  query + "WHERE ";
			
			if(disciplinaLetivaSearchParams.getAno() != -1){
				query = query + "ano = '" + disciplinaLetivaSearchParams.getAno() + "'";
				operator = true;
			}
			
			if(disciplinaLetivaSearchParams.getSemestre() != -1){
				if(operator){
					query = query + " AND ";
				}
				query = query + "semestre = '" + disciplinaLetivaSearchParams.getSemestre() + "'";
				operator = true;
			}
			
			if(!disciplinaLetivaSearchParams.getTurno().equals("-1")){
				if(operator){
					query = query + " AND ";
				}
				query = query + "turno = '" + disciplinaLetivaSearchParams.getTurno() + "'";
				operator = true;
			}
					
			
			
			if(!disciplinaLetivaSearchParams.getDisciplinaNome().equals("")){
				if(operator){
					query = query + " AND ";
				}
				
				query = query + "disciplina.nome LIKE '%" + disciplinaLetivaSearchParams.getDisciplinaNome() + "%'";
				
			}
			
		}
		
		Query hibernateQuery = session.createQuery(query);
		hibernateQuery.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetiva> ds =  hibernateQuery.list();
		
		session.close();
		
		return ds;
	}
	
	

}
