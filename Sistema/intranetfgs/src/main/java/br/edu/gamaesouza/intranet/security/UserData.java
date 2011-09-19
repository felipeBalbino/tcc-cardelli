package br.edu.gamaesouza.intranet.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.bean.Rule;

public class UserData {
	
	public static void grantAccess(String rule){
		
		boolean erro = true;
		System.out.println(1);
		Map sessao = ActionContext.getContext().getSession();
		Pessoa pessoa = (Pessoa) sessao.get("pessoa");
		System.out.println(pessoa);
		for (Rule ruleOfPerson : pessoa.getRegras()){
			if(ruleOfPerson.getNome().equals(rule)){
				erro = false;
			}
		}

		System.out.println(erro);
		if (erro){
			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				response.sendRedirect("../jsp/acessoNegado.jsp");
			} catch (IOException e) {
				System.out.println("[LOG] Erro ao redirecionar para página de acesso negado!");
			}
		}
	}
	
	
	public static Pessoa getLoggedUser(){
		Map sessao = ActionContext.getContext().getSession();
		Pessoa pessoa = (Pessoa) sessao.get("pessoa");
		return pessoa;
	}

}
