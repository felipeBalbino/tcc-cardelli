package br.edu.gamaesouza.intranet.action;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
		
	@Getter @Setter @Autowired private EventoDAO eventoDAO;
	@Getter @Setter @Autowired private NoticiaDAO noticiaDAO;
	@Getter @Setter @Autowired private VagaDAO vagaDAO;
	
	@Getter @Setter private List<Evento> eventos;
	@Getter @Setter private List<Noticia> noticias;
	@Getter @Setter private List<Vaga> vagas;

	public String events() throws Exception {
		
		setEventos(eventoDAO.getAllForIndex());
		setNoticias(noticiaDAO.getAllForIndex());
		setVagas(vagaDAO.getAllValidos());
		
		return "paginaInicial";
		
	}

}
