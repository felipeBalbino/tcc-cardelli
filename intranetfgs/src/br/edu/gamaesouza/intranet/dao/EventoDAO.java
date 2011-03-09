package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class EventoDAO {
	
	private Session session;
	private Transaction transaction;
	
	public void save(Evento evento) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.save(evento);
		transaction.commit();	
		session.flush();
		session.close();
	
	}
	
	public void update(Evento evento) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.update(evento);
		transaction.commit();
		session.flush();
		session.close();
	}
	
	public void merge(Evento evento) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.merge(evento);
		transaction.commit();
		session.flush();
		session.close();
	}
	
	
	public void delete(Evento evento) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.delete(evento);
		transaction.commit();	
		session.flush();
		session.close();
	}
	
	public List<Evento> getAll() throws IntranetException{
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Evento.class);	
		c.addOrder(Order.desc("title"));
		List<Evento> eventos = (List<Evento>)c.list();
		session.close();
		return eventos;
		
	}
	
	public List<Evento> getAllForIndex() throws IntranetException{
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Evento.class);
		c.setMaxResults(5);
		c.addOrder(Order.desc("id"));	
		List<Evento> eventos = (List<Evento>)c.list();
		session.close();
		return eventos;
		
	}
	
	public Evento getEventoById(Integer id) throws IntranetException{
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Evento.class);
		c.add(Restrictions.eq("id", id));
		Evento eventos = (Evento) c.uniqueResult();
		session.close();
		return eventos;
		
	}
	

}
