package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Noticia;
import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class NoticiaDAO {

	private CustomSession customSession;
	private Session session;
	private Transaction transaction;
	
	public void save(Noticia noticia) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.save(noticia);
		transaction.commit();
		session.close();
	}
	
	public List<Noticia> getAllForIndex() throws IntranetException{
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Noticia.class);
		
		c.setMaxResults(5);
		c.addOrder(Order.desc("id"));	
		List<Noticia> noticias = c.list();
		session.close();
		return noticias;
		
	}
	
	public List<Noticia> getAll() throws IntranetException{
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Noticia.class);	
		
		List<Noticia> noticias = c.list();
		
		session.close();
		return noticias; 
		
	}
	
	
	public void update(Noticia noticia) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.merge(noticia);
		transaction.commit();
		session.flush();
		session.close();
	}
	
	
	public void delete(Noticia noticia) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.delete(noticia);
		transaction.commit();	
		session.flush();
		session.close();
	}
	
	public Noticia getNoticiaById(Integer id) throws IntranetException{
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Noticia.class);
		c.add(Restrictions.eq("id", id));
		Noticia noticia = (Noticia) c.uniqueResult();
		session.close();
		return noticia;
		
	}
	
}
