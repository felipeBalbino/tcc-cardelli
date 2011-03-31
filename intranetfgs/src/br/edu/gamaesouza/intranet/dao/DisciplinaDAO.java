
package br.edu.gamaesouza.intranet.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.Horario;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaInscricaoSearchParams;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaSearchParams;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaSearchParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
public class DisciplinaDAO extends HibernateDaoSupport {
	
	@Autowired private CursoDAO cursoDAO;
	
	public void save(DisciplinaLetiva disciplinaLetiva){
		getHibernateTemplate().save(disciplinaLetiva);
	}

	public void update(DisciplinaLetiva disciplinaLetiva) throws IntranetException{
		getHibernateTemplate().update(disciplinaLetiva);		
	}
	public List<Disciplina> getAllDisciplinas() throws IntranetException {
		Criteria getAllDisciplinasCriteria = getSession().createCriteria(Disciplina.class);
		getAllDisciplinasCriteria.addOrder(Order.asc("nome"));
		getAllDisciplinasCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Disciplina> listaTodasDisciplinas =  getAllDisciplinasCriteria.list();
		return listaTodasDisciplinas;
	}
	
	public List<DisciplinaLetiva> getAllDisciplinaLetivas() throws IntranetException {
		Criteria getAllDisciplinaLeticasCriteria = getSession().createCriteria(DisciplinaLetiva.class);
		getAllDisciplinaLeticasCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		getAllDisciplinaLeticasCriteria.addOrder(Order.desc("id"));
		List<DisciplinaLetiva> listaTodasDisciplinasLetivas =  getAllDisciplinaLeticasCriteria.list();	
		return listaTodasDisciplinasLetivas;
	}
	
	
	public void deleteDisciplina(Disciplina disciplina) throws IntranetException{
		String query = "SELECT c FROM Curso c left join c.disciplinas d WHERE d.id = " + disciplina.getId();
		Query queryCursoByDisciplinaId = getSession().createQuery(query);
		List<Curso> cursos = queryCursoByDisciplinaId.list();
		
		for(Curso curso : cursos){
			curso.getDisciplinas().remove(disciplina);
			cursoDAO.update(curso);
		}

		disciplina.setCursos(null);
		getHibernateTemplate().delete(disciplina);
	}
	
	public void save(Disciplina t) throws IntranetException{
		getHibernateTemplate().save(t);
		
		for(Curso curso : t.getCursos()){
			curso.getDisciplinas().add(t);
			getHibernateTemplate().merge(curso);
		}	
	}
	
	
	public Disciplina getDisciplinaByNome(String nome) throws IntranetException{	
		Criteria disciplinaByNome = getSession().createCriteria(Disciplina.class);	
		disciplinaByNome.add(Restrictions.eq("nome", nome));
		
		Disciplina disciplina = (Disciplina) disciplinaByNome.uniqueResult();
		return disciplina;	
	}
	
	
	public Disciplina getDisciplinaById(Integer id) throws IntranetException{
		Criteria disciplinaById = getSession().createCriteria(Disciplina.class);
		
		disciplinaById.add(Restrictions.eq("id", id));
		Disciplina disciplina = (Disciplina) disciplinaById.uniqueResult();
		
		return disciplina;
	}
	
	public DisciplinaLetiva getDisciplinaLetivaById(Integer id) throws IntranetException{
		Criteria disciplinaLetivaById = getSession().createCriteria(DisciplinaLetiva.class);
	
		disciplinaLetivaById.add(Restrictions.eq("id", id));
		DisciplinaLetiva disciplina = (DisciplinaLetiva) disciplinaLetivaById.uniqueResult();
		
		return disciplina;
	
	}
	
	public List<DisciplinaLetiva> getDisciplinaLetivaByDisciplinaId(Integer id) throws IntranetException{
		String query = "FROM DisciplinaLetiva dl WHERE disciplina_id = " + id;
		Query disciplinaLetivaByDisciplinaId = getSession().createQuery(query);
		List<DisciplinaLetiva> disciplinasLetivas = (List<DisciplinaLetiva>) disciplinaLetivaByDisciplinaId.list();
	
		return disciplinasLetivas;
	}
	
	public List<DisciplinaLetiva> getDisciplinaLetivaByProfessor(Integer id) throws IntranetException{
	
		String query = "FROM DisciplinaLetiva dl WHERE professor_id = " + id;
		Query disciplinaLetivaByProfessor = getSession().createQuery(query);
		List<DisciplinaLetiva> disciplinasLetivas = (List<DisciplinaLetiva>) disciplinaLetivaByProfessor.list();
	
		return disciplinasLetivas;
	
	}
	
	public void deleteDisciplinaLetiva(DisciplinaLetiva disciplinaLetiva) throws IntranetException{
		getHibernateTemplate().delete(disciplinaLetiva);
	}
	
	public void deleteDisciplinaLetivaOfAluno(final DisciplinaLetiva disciplinaLetiva, Integer idAluno) throws IntranetException{
		final List<Aluno> alunos = disciplinaLetiva.getAluno();
		for(Aluno aluno:alunos){
				if(aluno.getId().equals(idAluno)){
					alunos.remove( aluno );
					break;
				}
		}
		

	  DisciplinaLetiva dl = (DisciplinaLetiva) getDisciplinaLetivaById(disciplinaLetiva.getId());
	  dl.setAluno(alunos);
	  getHibernateTemplate().merge(dl);
		         

		
	}
	
	public void updateDisciplinaLetiva(final DisciplinaLetiva disciplinaLetiva) throws IntranetException{
		HibernateCallback callback = new HibernateCallback() {
	        public Object doInHibernate(Session session) throws HibernateException, SQLException {
	        	DisciplinaLetiva disciplinaLet = new DisciplinaLetiva();
	        	disciplinaLet = disciplinaLetiva;
	        	DisciplinaLetiva groupObj = (DisciplinaLetiva) session.load(DisciplinaLetiva.class, disciplinaLetiva.getId());
	            groupObj.setId(disciplinaLet.getId());
	            groupObj.setProfessor(disciplinaLet.getProfessor());
	            groupObj.setSala(disciplinaLet.getSala());
	            session.update(groupObj);
	            return null;
	        }
	    };	
		getHibernateTemplate().execute(callback);
	}

	
	public DisciplinaLetiva saveOrReturnDisciplinaLetiva(DisciplinaLetiva dl) throws IntranetException{		
		Query c = getSession().getNamedQuery("allDLByDisciplinaAnoSemestre");
		c.setInteger("disciplina", dl.getDisciplina().getId());
		c.setInteger("ano", dl.getAno());
		c.setInteger("semestre", dl.getSemestre());
		
		DisciplinaLetiva disciplinaLetiva = (DisciplinaLetiva) c.uniqueResult();
		
		if(disciplinaLetiva != null){
			return disciplinaLetiva;
		}else{
			getHibernateTemplate().save(dl);
			return dl;
		}
	}
	
	public List<DisciplinaLetiva> getDisciplinasLetivas(Integer ano, Integer semestre, String turno)throws IntranetException{	
		Query query = getSession().getNamedQuery("allDLByAnoSemestreTurno");
		
		query.setInteger("ano",ano);
		query.setInteger("semestre",semestre);
		query.setString("turno",turno);
		
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetiva> disciplinasLetivas = query.list();
	
		return disciplinasLetivas;	
	}
	
	public List<DisciplinaLetiva> getDisciplinasLetivas(Integer ano, Integer semestre, String turno,Pessoa pessoa)throws IntranetException{	
		Professor professor = (Professor) pessoa;
		
		Query c = getSession().getNamedQuery("allDLByAnoSemestreTurnoProfessor");
		c.setInteger("ano", ano);
		c.setInteger("semestre", semestre);
		c.setString("turno", turno);
		c.setString("professor", professor.getNome());	
	
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetiva> disciplinasLetivas = c.list();
	
		return disciplinasLetivas;	
	}
	
	public boolean setAlunoFollowDisciplinhaLetiva(final Aluno aluno,
			final Integer disciplina, final Integer ano, final Integer semestre) throws IntranetException{
		Query queryVerify = getSession().getNamedQuery("allDLByAlunoDisciplinaAnoSemestre");
		queryVerify.setParameter("aluno", aluno.getId());
		queryVerify.setParameter("disciplina", disciplina);
		queryVerify.setParameter("ano", ano);
		queryVerify.setParameter("semestre", semestre);
		
		// Verifica se o Aluno j� est� inscrito na DisciplinaLetiva
		DisciplinaLetiva dlVerify = (DisciplinaLetiva) queryVerify.uniqueResult();
		
		if(dlVerify == null){			
			HibernateCallback callback = new HibernateCallback() {
		        public Object doInHibernate(Session session) throws HibernateException, SQLException {
		        	Query c = session.getNamedQuery("allDLByDisciplinaAnoSemestre");
					c.setParameter("disciplina", disciplina);
					c.setParameter("ano", ano);
					c.setParameter("semestre", semestre);
					
					DisciplinaLetiva dl = (DisciplinaLetiva) c.uniqueResult();
					
					dl.getAluno().add(aluno);
					getHibernateTemplate().merge(dl);
		            return null;
		        }
		    };	
			getHibernateTemplate().execute(callback);
			
			
			
			return false;
		}else{
			return true;
		}	
		
	}


	public List<DisciplinaLetiva> getDisciplinaLetivaByUser(Integer id) throws IntranetException{
		
		String sql = "FROM DisciplinaLetiva d left join fetch d.aluno aluno WHERE  aluno.id = " + id;
		
		Query query = getSession().createQuery(sql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetiva> disciplinasLetivas = (List<DisciplinaLetiva>)query.list();
		return disciplinasLetivas;
		
	}


	public DisciplinaLetiva getAlunosByDL( Integer id ) throws IntranetException{
	
		Criteria c = getSession().createCriteria(DisciplinaLetiva.class);
		c.add(Restrictions.eq("id", id));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		DisciplinaLetiva disciplinaLetiva = (DisciplinaLetiva) c.uniqueResult();
		
		return  disciplinaLetiva;

	
	}
	
	public void merge(Disciplina t) throws IntranetException{
		
	
		getHibernateTemplate().merge(t);
		
	}


	public List<Disciplina> getAllByParams(
			DisciplinaSearchParams disciplinaSearchParams) {
			
	
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
		
		query = query + " ORDER BY d.nome ASC";
		Query hibernateQuery = getSession().createQuery(query);
		hibernateQuery.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<Disciplina> ds =  hibernateQuery.list();
		
	
		
		return ds;
	}
	
	
	public List<DisciplinaLetiva> getAllByParamsDisciplinaLetiva(
			DisciplinaLetivaSearchParams disciplinaLetivaSearchParams) {
			
	
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
		
		Query hibernateQuery = getSession().createQuery(query);
		hibernateQuery.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetiva> ds =  hibernateQuery.list();
	
		
		return ds;
	}
	
	public List<DisciplinaLetiva> getAllByParamsDisciplinaLetivaInscricao(
			DisciplinaLetivaInscricaoSearchParams disciplinaLetivaInscricaoSearchParams) {
			
	
		boolean operator = false;
		String query = "FROM DisciplinaLetiva  d left outer join fetch d.disciplina disciplina ";
		
		if (!disciplinaLetivaInscricaoSearchParams.isEmpty()){
			query =  query + "WHERE ";
			
			if(disciplinaLetivaInscricaoSearchParams.getAno() != -1){
				query = query + "ano = '" + disciplinaLetivaInscricaoSearchParams.getAno() + "'";
				operator = true;
			}
			
			if(disciplinaLetivaInscricaoSearchParams.getSemestre() != -1){
				if(operator){
					query = query + " AND ";
				}
				query = query + "semestre = '" + disciplinaLetivaInscricaoSearchParams.getSemestre() + "'";
				operator = true;
			}
			
			if(!disciplinaLetivaInscricaoSearchParams.getTurno().equals("-1")){
				if(operator){
					query = query + " AND ";
				}
				query = query + "turno = '" + disciplinaLetivaInscricaoSearchParams.getTurno() + "'";
				operator = true;
			}
					
			
		}
		
		Query hibernateQuery = getSession().createQuery(query);
		hibernateQuery.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetiva> ds =  hibernateQuery.list();
	
		
		return ds;
	}


	

}
