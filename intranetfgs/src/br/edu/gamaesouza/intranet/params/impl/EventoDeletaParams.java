package br.edu.gamaesouza.intranet.params.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.dao.EventoDAO;
import br.edu.gamaesouza.intranet.params.EventoParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class EventoDeletaParams implements EventoParams {

	@Autowired EventoDAO eventoDAO;
	
	private Integer id;
	
	
	@Override
	public Evento getEvento() {
		try {
			return eventoDAO.getEventoById(getId());
		} catch (IntranetException e) {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
