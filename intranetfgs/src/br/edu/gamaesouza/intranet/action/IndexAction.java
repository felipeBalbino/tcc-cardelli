package br.edu.gamaesouza.intranet.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Noticia;
import br.edu.gamaesouza.intranet.bean.Vaga;
import br.edu.gamaesouza.intranet.dao.EventoDAO;
import br.edu.gamaesouza.intranet.dao.NoticiaDAO;
import br.edu.gamaesouza.intranet.dao.VagaDAO;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {

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

	public EventoDAO getDao() {
		return eventoDAO;
	}

	public void setDao(EventoDAO dao) {
		this.eventoDAO = dao;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}
	
	
	
	
	
	
}
