package br.edu.gamaesouza.intranet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Rule;
import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.params.impl.CursoSearchParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class CursoDAO extends GenericDAO<Curso> {
	
	private final Logger logger = Logger.getAnonymousLogger();
	private Session session;
	private Transaction transaction;
	@Autowired private DisciplinaDAO disciplinaDAO;

	public CursoDAO() {

	}

	public List<Curso> getAll() throws IntranetException {
		this.session = CustomSession.getSession();
		Criteria c = session.createCriteria(Curso.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Curso> cursos = c.list();
		this.session.close();
		return cursos;
	}

	public Curso getCursoById(Integer id)throws IntranetException{

		this.session = CustomSession.getSession();
		Criteria c = session.createCriteria(Curso.class);
		c.add(Restrictions.eq("id", id));
		Curso curso = (Curso) c.uniqueResult();
		this.session.close();
		return curso;

	}
	
	public Curso getCursoByNome(String id)throws IntranetException {

		this.session = CustomSession.getSession();
		Criteria c = session.createCriteria(Curso.class);		
		
		c.add(Restrictions.eq("nome", id));
		Curso curso = (Curso) c.uniqueResult();
		this.session.close();
		return curso;

	}

	public List<Curso> getAllCursosByIds(Integer[] cursosid) throws IntranetException{

		List<Curso> cursos = new ArrayList<Curso>();

		for (int cont = 0; cont < cursosid.length; cont++) {
			cursos.add(getCursoById(cursosid[cont]));
		}
		
		
		return cursos;

	}
	
	public List<Curso> getAllCursosByNome(List<String> nomesCursos) throws IntranetException{

		List<Curso> cursos = new ArrayList<Curso>();

		for(String nome : nomesCursos){
			cursos.add(getCursoByNome(nome));
		}

		return cursos;

	}
	
	public void delete(Curso curso) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.delete(curso);
		transaction.commit();	
		session.flush();
		this.session.close();
	}
	
	public void merge(Curso curso)  throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.merge(curso);
		transaction.commit();
		session.flush();
		this.session.close();
	}
	
	public void deletaDiciplinaCurso(Integer disciplinaId,Integer cursoId) throws IntranetException{
		Curso curso = getCursoById(cursoId);
		Disciplina disciplina = disciplinaDAO.getDisciplinaById(disciplinaId);
		disciplina.getCursos().remove(curso);
		curso.getDisciplinas().remove(disciplina);
		this.session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.update(disciplina);
		session.update(curso);
		transaction.commit();
		this.session.close();
	}

	public List<Disciplina> getDisciplinaListByStringList(List<String> paramRules) throws IntranetException{
			
			List<Disciplina> disciplinas = new ArrayList<Disciplina>();
			
			for(String rule : paramRules){
				disciplinas.add(disciplinaDAO.getDisciplinaByNome(rule));
			}
			
			return disciplinas;
			
		}

	public List<Curso> getCursoListByStringList(List<String> cursosParam,Disciplina d) throws IntranetException {
		List<Curso> cursos = new ArrayList<Curso>();
		this.session = CustomSession.getSession();
		for(String curso : cursosParam){
			
			
			Criteria c = session.createCriteria(Curso.class);	
			c.add(Restrictions.eq("nome", curso));	
			Curso cursoTemp = (Curso) c.uniqueResult();
			cursoTemp.getDisciplinas().add(d);
			cursos.add(cursoTemp);	
		}
		this.session.close();
		return cursos;
	}

	public List<Curso> getAllByParams(CursoSearchParams cursoSearchParams) {
		this.session = CustomSession.getSession();
		boolean operator = false;
		String query = "FROM Curso d left outer join fetch d.disciplinas disciplina ";
		
		logger.info("Curso Search params is Empty? " + cursoSearchParams.isEmpty());
		
		if (!cursoSearchParams.isEmpty()){
			query =  query + "WHERE ";
			
			if(!cursoSearchParams.getDisciplinaNome().equals("")){
				query = query + "disciplina.nome = '" + cursoSearchParams.getDisciplinaNome() + "'";
				operator = true;
			}
			
			if(!cursoSearchParams.getCursoNome().equals("")){
				if(operator){
					query = query + " AND ";
				}
				
				query = query + "d.nome LIKE '%" + cursoSearchParams.getCursoNome() + "%'";
				
			}
			
		}
		
		Query hibernateQuery = session.createQuery(query);
		hibernateQuery.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Curso> ds =  hibernateQuery.list();
		
		session.close();
		
		return ds;
	}

}
