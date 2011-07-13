package br.edu.gamaesouza.intranet.action;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Noticia;
import br.edu.gamaesouza.intranet.bean.Vaga;
import br.edu.gamaesouza.intranet.dao.EventoDAO;
import br.edu.gamaesouza.intranet.dao.NoticiaDAO;
import br.edu.gamaesouza.intranet.dao.VagaDAO;

import com.opensymphony.xwork2.ActionSupport;

public @Data class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private static final String RETURN_INDEX = "paginaInicial";
	
	@Autowired private EventoDAO eventoDAO;
	@Autowired private NoticiaDAO noticiaDAO;
	@Autowired private VagaDAO vagaDAO;
	
	private List<Evento> eventos;
	private List<Noticia> noticias;
	private List<Vaga> vagas;

	public String events() throws Exception {
		
		eventos = eventoDAO.getAllForIndex();
		noticias = noticiaDAO.getAllForIndex();
		setVagas(vagaDAO.getAllValidos());
		
		
		return RETURN_INDEX;
		
	}

}
