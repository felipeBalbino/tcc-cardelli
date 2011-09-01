package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.params.HoraAEPParams;

public class HoraAEPListaParams implements HoraAEPParams{

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	
}
