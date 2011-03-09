package br.edu.gamaesouza.intranet.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class GenericDAO<T> {
	
	private CustomSession customSession;
	private Session session;
	private Transaction transaction;
	private T t;
	
	public GenericDAO() {
		
	}
	
	public void save(T t) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.save(t);
		transaction.commit();
		session.close();
	}
	
	public void update(T t) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.update(t);
		transaction.commit();
		session.close();
	}
	
	public void delete(T t) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.save(t);
		transaction.commit();
		session.close();
	}
	
	public List<T> getAll() throws IntranetException{
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(t.getClass());
		
		List<T> t = c.list();
		session.close();
		return t;
	}

}
