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
		Criteria allForIndex = getSession().createCriteria(Noticia.class);
		allForIndex.setMaxResults(5);
		allForIndex.addOrder(Order.desc("id"));	
		List<Noticia> notices = allForIndex.list();
		return notices;
	}
	
	public List<Noticia> getAll() throws IntranetException{
		Criteria allNotices = getSession().createCriteria(Noticia.class);
		allNotices.addOrder(Order.desc("id"));	
		List<Noticia> notices = allNotices.list();
		return notices;
	}
	
	public void update(Noticia noticia) throws IntranetException{
		getHibernateTemplate().merge(noticia);
	}
	
	
	public void delete(Noticia noticia) throws IntranetException{
		getHibernateTemplate().delete(noticia);
	}
	
	public Noticia getNoticiaById(Integer id) throws IntranetException{
		Criteria noticeById = getSession().createCriteria(Noticia.class);
		noticeById.add(Restrictions.eq("id", id));	
		Noticia notice = (Noticia) noticeById.uniqueResult();
		return notice;	
	}
	
}
