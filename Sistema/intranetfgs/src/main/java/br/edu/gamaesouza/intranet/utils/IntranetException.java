package br.edu.gamaesouza.intranet.utils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.security.UserData;

public class IntranetException extends Exception {
	
	private String msg;
	@Autowired EnviarEmail enviarEmail;
	
	public IntranetException(String msg){  
	     super(msg);  
	     this.msg = msg;  
	     try {
			enviarEmail.sendExceptionToAdministrador(this, UserData.getLoggedUser());
		 } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}  
	
	
	public String getMessage(){  
		return msg;  
    }  

	
}
