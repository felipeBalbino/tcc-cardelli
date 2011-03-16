package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class EventoDAO extends HibernateDaoSupport {
	
	
	public void save(Evento evento) throws IntranetException{
		
		getHibernateTemplate().save(evento);
	
	
	}
	
	public void update(Evento evento) throws IntranetException{
		
		getHibernateTemplate().update(evento);

	
	}
	
	public void merge(Evento evento) throws IntranetException{
		
		getHibernateTemplate().merge(evento);
	
	}
	
	
	public void delete(Evento evento) throws IntranetException{
	
		getHibernateTemplate().delete(evento);

		
	}
	
	public List<Evento> getAll() throws IntranetException{
	
		Criteria c = getSession().createCriteria(Evento.class);	
		c.addOrder(Order.desc("title"));
		List<Evento> eventos = (List<Evento>)c.list();

		return eventos;
		
	}
	
	public List<Evento> getAllForIndex() throws IntranetException{
		Criteria c = getSession().createCriteria(Evento.class);
		c.setMaxResults(5);
		c.addOrder(Order.desc("id"));	
		List<Evento> list = c.list();
		return list;
	}
	
	public Evento getEventoById(Integer id) throws IntranetException{
	
		Criteria c = getSession().createCriteria(Evento.class);
		c.add(Restrictions.eq("id", id));
		Evento eventos = (Evento) c.uniqueResult();

		return eventos;
		
	}
	

}
