package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.bean.Hora;
import br.edu.gamaesouza.intranet.params.HoraComplementarParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class HoraComplementarListaParams implements HoraComplementarParams {

	private Integer id;
	
	private String atividade;

	private Integer atividadeKey;
	
	public Integer getAtividadeKey() {
		return atividadeKey;
	}

	public void setAtividadeKey(Integer atividadeKey) {
		this.atividadeKey = atividadeKey;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	@Override
	public Hora getHora() throws IntranetException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	
	
	
}
