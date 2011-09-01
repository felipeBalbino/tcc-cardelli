package br.edu.gamaesouza.intranet.other;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class CustomSession {

	@Autowired private static HibernateTemplate hibernateTemplate;
	
	public static Session getSession() {	
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();	
		return session;
	}

	
}
