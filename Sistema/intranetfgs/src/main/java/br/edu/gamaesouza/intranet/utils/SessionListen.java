package br.edu.gamaesouza.intranet.utils;

import java.util.Calendar;
import java.util.Map;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;

import com.opensymphony.xwork2.ActionContext;
 
public class SessionListen implements HttpSessionListener {
	
    public void sessionDestroyed(HttpSessionEvent event) {
		try {
			Pessoa pessoa = null;
		
			if(ActionContext.getContext() != null && ActionContext.getContext().getSession() != null){
				Map<String, Object> sessao = ActionContext.getContext().getSession();
				pessoa = (Pessoa) sessao.get("pessoa");
			}
			
			if(pessoa != null){	
				pessoa.setDataUltimoAcesso(Calendar.getInstance());
				PessoaDAO pessoaDAO = new PessoaDAO();
				pessoaDAO.merge(pessoa);
			}
			
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}