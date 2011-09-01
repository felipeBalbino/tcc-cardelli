package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.params.HoraAEPParams;

public class HoraAEPDeletaParams implements HoraAEPParams {

	private String alunoId;
	
	private String horaId;
	
	

	public String getAlunoId() {
		return alunoId;
	}

	public String getHoraId() {
		return horaId;
	}

	public void setHoraId(String horaId) {
		this.horaId = horaId;
	}

	public void setAlunoId(String alunoId) {
		this.alunoId = alunoId;
	}

}
