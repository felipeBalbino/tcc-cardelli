package br.edu.gamaesouza.intranet.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.security.UserData;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class ExceptionInterceptor extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private EnviarEmail enviarEmail = new EnviarEmail();

    public String execute() {
    		
    		 try {
    			 
    			 HttpServletRequest request = ServletActionContext.getRequest();
    			 Exception rex = (Exception) request.getAttribute("exception");		 
				 enviarEmail.sendExceptionToAdministrador(rex,UserData.getLoggedUser());
				 
			} catch (Throwable e) {
				
					 
        	}

        return "canNotLogDatabaseError";
    }

}
