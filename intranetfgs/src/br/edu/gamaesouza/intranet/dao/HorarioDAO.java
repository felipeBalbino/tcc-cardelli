
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
	
	public List<DisciplinaLetivaHorario> getAllDisciplinaLetivaHorariosById(Integer id) throws IntranetException {
		Query c = getSession().createQuery( "Select d from DisciplinaLetivaHorario d where disciplinaLetiva_id="+id );
		return c.list();
	}
	
	
	public DisciplinaLetivaHorario getDisciplinaLetivaHorarioById(Integer id) throws IntranetException {
		Query c = getSession().createQuery( "Select d from DisciplinaLetivaHorario d where id="+id );
		return (DisciplinaLetivaHorario) c.uniqueResult();
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
	

}
