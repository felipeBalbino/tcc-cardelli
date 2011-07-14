package br.edu.gamaesouza.intranet.action;


import java.util.List;
import java.util.logging.Logger;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Evento;

import br.edu.gamaesouza.intranet.dao.EventoDAO;
import br.edu.gamaesouza.intranet.params.impl.EventoAlteraParams;
import br.edu.gamaesouza.intranet.params.impl.EventoDeletaParams;
import br.edu.gamaesouza.intranet.params.impl.EventoNovoParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

import com.opensymphony.xwork2.ActionSupport;

public class EventoAction extends ActionSupport {

	private final Logger logger = Logger.getLogger("br.edu.gamaesouza.intranet.action.EventoAction");
	
	private static final long serialVersionUID = 1L;
	
	private static final String MSG_EVENTO_NOVO_SUCESSO = "Evento adicionado com sucesso";
	private static final String MSG_EVENTO_NOVO_FAILURE = "N�o foi possivel adicionar o evento, ocorreu um erro interno no Servidor";
	private static final String MSG_EVENTO_ALTERA_SUCESSO = "Evento modificado com sucesso";
	private static final String MSG_EVENTO_ALTERA_FAILURE = "N�o foi possivel modificar o evento, ocorreu um erro interno no Servidor";
	private static final String MSG_EVENTO_DELETA_SUCESSO = "Evento deletado com sucesso";
	private static final String MSG_EVENTO_DELETA_INSUCESSO = "N�o foi possivel deletar o evento, ocorreu um erro interno no Servidor";

	private static final String RULE_EVENTO_NOVO = "RULE_EVENTO_NOVO";
	private static final String RULE_EVENTO_ALTERA = "RULE_EVENTO_ALTERA";
	private static final String RULE_EVENTO_DELETA = "RULE_EVENTO_DELETA";
	private static final String RULE_EVENTO_LISTA = "RULE_EVENTO_LISTA";

	private static final String RETURN_EVENTO_LISTA = "modificarEvento";

	@Getter @Setter private List<Evento> eventos;
	@Getter @Setter private String tempoDeResposta;
	
	@Getter @Setter @Autowired private EventoDAO eventoDAO;
	@Getter @Setter @Autowired private EventoAlteraParams eventoAlteraParams;
	@Getter @Setter @Autowired private EventoDeletaParams eventoDeletaParams;
	@Getter @Setter @Autowired private EventoNovoParams   eventoNovoParams;

	
	public String lista() {
		
		UserData.grantAccess(RULE_EVENTO_LISTA);
		try {			
			long inicio = System.currentTimeMillis();  
			eventos = eventoDAO.getAll();
			long  end = System.currentTimeMillis();  
			setTempoDeResposta(FormUtil.tempoResposta(eventos, inicio, end)); 
		} catch (IntranetException e) {
			logger.warning("Erro M�todo lista() em EventoAction.");
		}
		return RETURN_EVENTO_LISTA;
		
	}

	public String novo() {

		UserData.grantAccess(RULE_EVENTO_NOVO);
		
			try {
				eventoDAO.save(eventoNovoParams.getEvento());
				eventoNovoParams = (EventoNovoParams) SpringUtil.getBean("eventoNovoParams");
				addActionMessage(MSG_EVENTO_NOVO_SUCESSO);
			} catch (IntranetException e) {
				addActionError(MSG_EVENTO_NOVO_FAILURE);
			}

			return lista();
		
	}

	public String altera() {
		
		UserData.grantAccess(RULE_EVENTO_ALTERA);
		
			try {
				eventoDAO.merge(eventoAlteraParams.getEvento());
				eventoAlteraParams = (EventoAlteraParams) SpringUtil.getBean("eventoAlteraParams");
				addActionMessage(MSG_EVENTO_ALTERA_SUCESSO);
			} catch (IntranetException e) {
				addActionError(MSG_EVENTO_ALTERA_FAILURE);
			}

			return lista();
		
	}

	public String deleta() {	
		UserData.grantAccess(RULE_EVENTO_DELETA);
		
			try {			
				eventoDAO.delete(eventoDeletaParams.getEvento());
				eventoDeletaParams = (EventoDeletaParams) SpringUtil.getBean("eventoDeletaParams");
				addActionMessage(MSG_EVENTO_DELETA_SUCESSO);
			} catch (Exception e) {
				addActionError(MSG_EVENTO_DELETA_INSUCESSO);
			}
		
			return lista();

	}

}
