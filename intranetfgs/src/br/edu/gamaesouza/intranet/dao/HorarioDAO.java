
package br.edu.gamaesouza.intranet.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetivaHorario;
import br.edu.gamaesouza.intranet.bean.Horario;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.utils.IntranetException;



/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
public class HorarioDAO extends HibernateDaoSupport {
	
	
	public void save(Horario horario){
		getHibernateTemplate().save(horario);
	}
	
	public void saveDisciplinaLetivaHorario(DisciplinaLetivaHorario disciplinaLetivaHorario){
		getHibernateTemplate().save(disciplinaLetivaHorario);
	}
	
	public List<Horario> getAllHorarios() throws IntranetException {
		Criteria getAllHorarioCriteria = getSession().createCriteria(Horario.class);
		getAllHorarioCriteria.addOrder(Order.asc("ano"));
		getAllHorarioCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Horario> horarios =  getAllHorarioCriteria.list();
		return horarios;
	}
	
	public List<Horario> getAllHorarios(Integer ano, Integer semestre) throws IntranetException {
		Criteria getAllHorarioCriteria = getSession().createCriteria(Horario.class);
		getAllHorarioCriteria.add(Restrictions.eq("ano", ano));
		getAllHorarioCriteria.add(Restrictions.eq("semestre", semestre));
		getAllHorarioCriteria.addOrder(Order.asc("id"));
		getAllHorarioCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Horario> horarios =  getAllHorarioCriteria.list();
		return horarios;
	}
	
	public List<DisciplinaLetivaHorario> getAllDisciplinaLetivaHorariosById(Integer id) throws IntranetException {
		String sql = "FROM DisciplinaLetivaHorario dlh WHERE dlh.disciplinaLetivaHorarioPK.disciplinaLetiva.id = " + id;
		Query getAllLetivaByHorario = getSession().createQuery(sql);
		getAllLetivaByHorario.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DisciplinaLetivaHorario> letivaHorario =  getAllLetivaByHorario.list();
		//Query c = getSession().createQuery( "Select d from DisciplinaLetivaHorario d where disciplinaLetiva_id="+id );
		return letivaHorario;
	}
	
	
	public DisciplinaLetivaHorario getDisciplinaLetivaHorarioById(Integer id) throws IntranetException {
		Query c = getSession().createQuery( "Select d from DisciplinaLetivaHorario d where id="+id );
		return (DisciplinaLetivaHorario) c.uniqueResult();
	}
	
	public DisciplinaLetivaHorario getDisciplinaLetivaHorarioByIds(Integer idHorario, Integer idLetiva) throws IntranetException {
		Query c = getSession().createQuery( "Select d from DisciplinaLetivaHorario d where disciplinaLetiva_id="+idLetiva+" and horario_id="+idHorario );
		return (DisciplinaLetivaHorario) c.uniqueResult();
	}
	
	public List<DisciplinaLetivaHorario> getDisciplinaLetivaHorarioByIds(Integer idHorario, Integer idLetiva, Integer ano, Integer semestre) throws IntranetException {
		
		
	String query =	"Select disciplinaLetivaHorario";
	query = query + " From DisciplinaLetivaHorario disciplinaLetivaHorario," ;
	query = query + "DisciplinaLetiva disciplinaLetiva ";
	query = query + "where disciplinaLetivaHorario.disciplinaLetivaHorarioPK.disciplinaLetiva.id="+idLetiva+" and disciplinaLetivaHorario.disciplinaLetivaHorarioPK.horario.id="+idHorario;
	query = query + " AND disciplinaLetivaHorario.disciplinaLetivaHorarioPK.disciplinaLetiva.disciplina.id = disciplinaLetiva.disciplina.id AND";
	query = query + " disciplinaLetiva.ano = 2011 and disciplinaLetiva.semestre = 1";
		
		
		
		
		
		
		Query c = getSession().createQuery( query );
		return c.list();
	}
	
	public Horario getHorarioById(Integer id) throws IntranetException {
		Criteria c = getSession().createCriteria(Horario.class);
		c.add(Restrictions.eq("id", id));
		Horario horario =(Horario) c.uniqueResult();
		return horario;
	}
	
	public void delete(Horario horario)throws IntranetException{	
		getHibernateTemplate().delete(horario);
	}
	
	public void deleteDisciplinaLetivaHorario(DisciplinaLetivaHorario disciplinaLetivaHorario)throws IntranetException{	
		getHibernateTemplate().delete(disciplinaLetivaHorario);
	}

	public boolean validationDisciplinaLetivaHorario(
			DisciplinaLetivaHorario disciplinaLetivaHorario) {
	
		Query c = getSession().createQuery( "Select d from DisciplinaLetivaHorario d where d.disciplinaLetivaHorarioPK.DiaSemana='"+disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().getDiaSemana().name()+
				"' and d.disciplinaLetivaHorarioPK.disciplinaLetiva.id="+disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().getDisciplinaLetiva().getId()+
				" and d.disciplinaLetivaHorarioPK.horario.id="+disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().getHorario().getId());
		List<DisciplinaLetivaHorario> validationDisciplinaLetHorario =(List<DisciplinaLetivaHorario>) c.list();
		
		if(validationDisciplinaLetHorario.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	public DisciplinaLetivaHorario getDisciplinaLetivaHorarioByHorarioAndDisciplinaLetivaId(
			Integer idDisciplinaLetiva, Integer idHorario) {
		String sql = "FROM DisciplinaLetivaHorario dlh WHERE dlh.disciplinaLetivaHorarioPK.horario.id = " + idHorario + " AND dlh.disciplinaLetivaHorarioPK.disciplinaLetiva.id = " + idDisciplinaLetiva ; 
		Query c = getSession().createQuery(sql);
		return (DisciplinaLetivaHorario) c.uniqueResult();
	}
	

}
