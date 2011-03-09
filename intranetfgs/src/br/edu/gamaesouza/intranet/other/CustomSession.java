package br.edu.gamaesouza.intranet.other;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CustomSession {

	public static Session getSession() {
		AnnotationConfiguration configuration  = new AnnotationConfiguration();
		configuration.configure();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();	
		return session;
	}

	
}
