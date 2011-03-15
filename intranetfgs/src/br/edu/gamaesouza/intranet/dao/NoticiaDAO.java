package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Noticia;
import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class NoticiaDAO extends HibernateDaoSupport{

	public void save(Noticia noticia) throws IntranetException{
		getHibernateTemplate().save(noticia);
	}
	
	public List<Noticia> getAllForIndex() throws IntranetException{		
		Criteria c = getSession().createCriteria(Noticia.class);
		c.setMaxResults(5);
		c.addOrder(Order.desc("id"));	
		List<Noticia> noticias = c.list();
		return noticias;
	}
	
	public List<Noticia> getAll() throws IntranetException{
		Criteria c = getSession().createCriteria(Noticia.class);
		c.addOrder(Order.desc("id"));	
		List<Noticia> noticias = c.list();
		return noticias;
		
	}
	
	
	public void update(Noticia noticia) throws IntranetException{
		getHibernateTemplate().merge(noticia);
	}
	
	
	public void delete(Noticia noticia) throws IntranetException{
		getHibernateTemplate().delete(noticia);
	}
	
	public Noticia getNoticiaById(Integer id) throws IntranetException{
		Criteria c = getSession().createCriteria(Noticia.class);
		c.add(Restrictions.eq("id", id));	
		Noticia noticia = (Noticia) c.uniqueResult();
		return noticia;
		
	}
	
}
