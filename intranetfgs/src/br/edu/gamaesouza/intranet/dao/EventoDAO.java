package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.utils.IntranetException;

/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 16/03/2011
 */
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
		Criteria allEvents = getSession().createCriteria(Evento.class);	
		allEvents.addOrder(Order.desc("title"));
		List<Evento> eventsList = (List<Evento>)allEvents.list();
		return eventsList;
	}
	
	public List<Evento> getAllForIndex() throws IntranetException{
		Criteria allEventsForIndex = getSession().createCriteria(Evento.class);
		allEventsForIndex.setMaxResults(5);
		allEventsForIndex.addOrder(Order.desc("id"));	
		List<Evento> eventsList = allEventsForIndex.list();
		return eventsList;
	}
	
	public Evento getEventoById(Integer id) throws IntranetException{
		Criteria eventById = getSession().createCriteria(Evento.class);
		eventById.add(Restrictions.eq("id", id));
		Evento event = (Evento) eventById.uniqueResult();
		return event;
	}
	

}
