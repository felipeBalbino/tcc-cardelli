package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.params.impl.OuvidoriaEmailParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

import com.opensymphony.xwork2.ActionSupport;

public class SendMailAction extends ActionSupport {

	private static final long serialVersionUID = 3053722020213888951L;
	private List<Curso> cursos = new ArrayList<Curso>();
	
	@Autowired private OuvidoriaEmailParams ouvidoriaEmailParams;
	@Autowired private EnviarEmail enviarEmail;
	@Autowired private CursoDAO cursoDAO;
	
	public String prepare(){
		try {
			setCursos( cursoDAO.getAll() );
		} catch ( IntranetException e ) {
			e.printStackTrace();
		}
		return "refresh";	
	}

	public String execute() {		
		try{		
			enviarEmail.sendMailToOuvidoria( ouvidoriaEmailParams.getData() );
			addActionMessage("Mensagem enviada com sucesso!");
		}catch(IntranetException e){
			addActionError("Ocorreu um erro ao se conectar com o servidor de email.");
			return prepare();
		}		
		return prepare();	
	}
	
	public List<Curso> getCursos() {return cursos;}
	public void setCursos(List<Curso> cursos) {this.cursos = cursos;}
	
	public CursoDAO getCursoDAO() {return cursoDAO;}
	public void setCursoDAO(CursoDAO cursoDAO) {this.cursoDAO = cursoDAO;}
	
	public OuvidoriaEmailParams getOuvidoriaEmailParams() {return ouvidoriaEmailParams;}
	public void setOuvidoriaEmailParams(OuvidoriaEmailParams ouvidoriaEmailParams) {this.ouvidoriaEmailParams = ouvidoriaEmailParams;}
	
}
