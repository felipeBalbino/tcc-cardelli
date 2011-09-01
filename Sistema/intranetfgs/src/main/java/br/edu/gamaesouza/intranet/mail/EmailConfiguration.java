package br.edu.gamaesouza.intranet.mail;



import java.util.Properties;

import javax.mail.Authenticator;

import org.apache.struts2.ServletActionContext;

public interface EmailConfiguration {
	
	public static final String EMAIL = ServletActionContext.getServletContext().getInitParameter("systemMail");	
		
	public static final String SENHA = ServletActionContext.getServletContext().getInitParameter("systemMailPassword");
	
	public Properties getConfiguration();
	
	public Authenticator getAuth();

}
