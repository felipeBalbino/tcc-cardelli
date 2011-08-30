package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.params.impl.OuvidoriaEmailParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

import com.opensymphony.xwork2.ActionSupport;

public class OuvidoriaAction extends ActionSupport {

	private static final long serialVersionUID = 3053722020213888951L;
	
	@Getter @Setter private List<Curso> cursos = new ArrayList<Curso>();
	
	@Getter @Setter @Autowired private OuvidoriaEmailParams ouvidoriaEmailParams;
	@Getter @Setter @Autowired private EnviarEmail enviarEmail;
	@Getter @Setter @Autowired private CursoDAO cursoDAO;
	
	public String prepare(){
		try {
			setCursos( cursoDAO.getAllCursos() );
		} catch ( IntranetException e ) {
			addActionMessage(e.getMessage());
		}
		return "refresh";	
	}

	public String execute() {		
		try{		
			enviarEmail.sendMailToOuvidoria( ouvidoriaEmailParams.getData() );
			ouvidoriaEmailParams = (OuvidoriaEmailParams) SpringUtil.getBean("ouvidoriaEmailParams");
			addActionMessage("Mensagem enviada com sucesso!");
		}catch(IntranetException e){
			addActionError("Ocorreu um erro ao se conectar com o servidor de email.");
			return prepare();
		}		
		return prepare();	
	}
	
	
}
