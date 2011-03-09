package br.edu.gamaesouza.intranet.mail;



import java.util.Properties;

import javax.mail.Authenticator;

public interface EmailConfiguration {
	
	public static final String EMAIL = "intranetfgs@gmail.com";
	public static final String SENHA = "qwe123qwe123";
	
	public Properties getConfiguration();
	
	public Authenticator getAuth();

}
