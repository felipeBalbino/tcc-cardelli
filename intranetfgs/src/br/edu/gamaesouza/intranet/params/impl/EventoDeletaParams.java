package br.edu.gamaesouza.intranet.params.impl;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.dao.EventoDAO;
import br.edu.gamaesouza.intranet.params.EventoParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public @Data class EventoDeletaParams implements EventoParams {

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
		return false;
	}

}
