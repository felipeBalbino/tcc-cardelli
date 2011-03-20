package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.params.HoraComplementarParams;

public class HoraComplementarListaParams implements HoraComplementarParams {

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
