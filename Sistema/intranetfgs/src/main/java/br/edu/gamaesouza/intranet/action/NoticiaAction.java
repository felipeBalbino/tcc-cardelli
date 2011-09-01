package br.edu.gamaesouza.intranet.action;

import java.util.Calendar;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import br.edu.gamaesouza.intranet.bean.Noticia;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.dao.NoticiaDAO;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

import com.opensymphony.xwork2.ActionSupport;

public class NoticiaAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private static final String RULE_NOTICIA_LISTA = "RULE_NOTICIA_LISTA";
	private static final String RULE_NOTICIA_SALVA = "RULE_NOTICIA_SALVA";
	private static final String RULE_NOTICIA_ALTERA = "RULE_NOTICIA_ALTERA";
	private static final String RULE_NOTICIA_DELETE = "RULE_NOTICIA_DELETE";

	@Setter @Getter private List<Noticia> noticias;
	@Setter @Getter private String tempoDeResposta;
	
	@Setter @Getter @Autowired private Noticia noticia;
	@Setter @Getter @Autowired private NoticiaDAO noticiaDAO;

	public String lista() {
		
		UserData.grantAccess(RULE_NOTICIA_LISTA);
		try {		
			long inicio = System.currentTimeMillis();  
				noticias = noticiaDAO.getAll();
			long  end = System.currentTimeMillis();  
			setTempoDeResposta(FormUtil.tempoResposta(noticias, inicio, end)); 
		} catch (IntranetException e) {
			addActionMessage(e.getMessage());
		}
		return "modificarNoticia";
		
	}

	public String save() {

		UserData.grantAccess(RULE_NOTICIA_SALVA);
		
			try {
				noticia.setAutor(UserData.getLoggedUser());
				noticia.setDatahoraPublicacao(Calendar.getInstance());
				noticiaDAO.save(noticia);
				noticia = (Noticia) SpringUtil.getBean("noticia");
				addActionMessage("Not�cia adicionado com sucesso");
			} catch (Exception e) {
				addActionError("N�o foi possivel adicionar a not�cia, ocorreu um erro interno no Servidor");
			}
			return lista();
	
	}
	
	public String altera() {

		UserData.grantAccess(RULE_NOTICIA_ALTERA);
		
			try {
				
				noticia.setAutor((Professor)UserData.getLoggedUser());
				noticia.setDatahoraPublicacao(Calendar.getInstance());
				noticiaDAO.update(noticia);
				noticia = (Noticia) SpringUtil.getBean("noticia");
				
				addActionMessage("Not�cia alterada com sucesso");
				
			} catch (Exception e) {
				
				addActionError("N�o foi possivel alterar a not�cia, ocorreu um erro interno no Servidor");

			}

			return lista();
	
	}
	
	public String delete() {

		UserData.grantAccess(RULE_NOTICIA_DELETE);
			try {
				
				Noticia noticia = noticiaDAO.getNoticiaById( this.noticia.getId() );
				System.out.println(noticia.getMensagem());
				noticiaDAO.delete(noticia);
				noticia = (Noticia) SpringUtil.getBean("noticia");
				addActionMessage("Not�cia deletada com sucesso");
				
			} catch (Exception e) {
				
				addActionError("N�o foi possivel deletar a not�cia, ocorreu um erro interno no Servidor");
				
			}
			return lista();
	
	}


}
