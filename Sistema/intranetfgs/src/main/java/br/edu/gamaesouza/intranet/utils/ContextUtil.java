package br.edu.gamaesouza.intranet.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

public class ContextUtil {
	
	public static List<String> getAllDevelopers(){
		String admins = ServletActionContext.getServletContext().getInitParameter("developers");
		
		String[] emails = admins.split(",");
	
		List<String> listaEmails = Arrays.asList(emails);
		
		
		return listaEmails;
		
	}
	
}
