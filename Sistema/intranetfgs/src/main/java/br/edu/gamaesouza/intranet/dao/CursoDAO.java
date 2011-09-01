package br.edu.gamaesouza.intranet.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.params.impl.CursoSearchParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
public class CursoDAO extends HibernateDaoSupport {
	
	@Autowired private DisciplinaDAO disciplinaDAO;

	public List<Curso> getAllCursos() throws IntranetException {
		Criteria allCursos = getSession().createCriteria(Curso.class);
		allCursos.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Curso> cursos = allCursos.list();
		return cursos;
	}

	public Curso getCursoById(Integer id)throws IntranetException{	
		Criteria cursoById = getSession().createCriteria(Curso.class);
		cursoById.add(Restrictions.eq("id", id));
		Curso curso = (Curso) cursoById.uniqueResult();
		return curso;
	}
	
	public Curso getCursoByNome(String id)throws IntranetException {	
		Criteria cursoByNome = getSession().createCriteria(Curso.class);			
		cursoByNome.add(Restrictions.eq("nome", id));
		Curso curso = (Curso) cursoByNome.uniqueResult();
		return curso;
	}

	public List<Curso> getAllCursosById(Integer[] cursosid) throws IntranetException{
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
	
	public void delete(final Curso curso) throws IntranetException{	
		HibernateCallback callback = new HibernateCallback() {
	        public Object doInHibernate(Session session) throws HibernateException, SQLException {
	            Object groupObj = session.load(Curso.class, curso.getId());
	            session.delete(groupObj);
	            return null;
	        }
	    };	
		getHibernateTemplate().execute(callback);
	}
	
	public void merge(Curso curso)  throws IntranetException{		
		getHibernateTemplate().merge(curso);
	}
	
	public void deletaDiciplinaCurso(Integer disciplinaId,Integer cursoId) throws IntranetException{
		Curso curso = getCursoById(cursoId);
		Disciplina disciplina = disciplinaDAO.getDisciplinaById(disciplinaId);
		disciplina.getCursos().remove(curso);
		curso.getDisciplinas().remove(disciplina);

		getHibernateTemplate().update(disciplina);
		getHibernateTemplate().update(curso);

	}

	public List<Disciplina> getDisciplinaListByStringList(List<String> paramRules) throws IntranetException{
		// Caso o usu�rio n�o selecione nenhuma disciplina/
		if(paramRules == null || paramRules.isEmpty()){
			return new ArrayList<Disciplina>();
		}
		
			List<Disciplina> disciplinas = new ArrayList<Disciplina>();
			
			for(String rule : paramRules){
				disciplinas.add(disciplinaDAO.getDisciplinaByNome(rule));
			}
			
			return disciplinas;
			
		}

	public List<Curso> getCursoListByStringList(List<String> cursosParam,Disciplina d) throws IntranetException {
		//TODO Refatorar M�todo
		// Esse m�todo precisa ser repensado, ele deleta tudo e adiciona dinovo
		
		List<Curso> cursos = new ArrayList<Curso>();	
		List<Curso> cursosDelete = d.getCursos();
		
		for(Curso curso : cursosDelete){
			curso.getDisciplinas().remove(d);
			merge(curso);
		}
		
		if(cursosParam != null){
			
			for(String curso : cursosParam){
				Criteria cursoByNome = getSession().createCriteria(Curso.class);	
				cursoByNome.add(Restrictions.eq("nome", curso));	
				Curso cursoTemporario = (Curso) cursoByNome.uniqueResult();			
				cursoTemporario.getDisciplinas().add(d);
				cursos.add(cursoTemporario);		
				merge(cursoTemporario);
			}
	
		}
		return cursos;
	}

	public List<Curso> getAllByParams(CursoSearchParams cursoSearchParams) {
	
		boolean operator = false;
		String query = "FROM Curso d left outer join fetch d.disciplinas disciplina ";
		
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
		
		Query cursosByParams = getSession().createQuery(query);
		cursosByParams.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Curso> cursos =  cursosByParams.list();
		
		return cursos;
	}

	public void update(Curso curso) throws IntranetException{
		getHibernateTemplate().update(curso);		
	}

	public void save(Curso curso) throws IntranetException{
		getHibernateTemplate().save(curso);			
	}

}
