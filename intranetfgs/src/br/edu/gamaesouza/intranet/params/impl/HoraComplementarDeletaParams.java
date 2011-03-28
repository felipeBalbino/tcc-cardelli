package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.bean.Hora;
import br.edu.gamaesouza.intranet.params.HoraComplementarParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class HoraComplementarDeletaParams implements HoraComplementarParams  {

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
	
	@Override
	public Hora getHora() throws IntranetException {
		// TODO Auto-generated method stub
		return null;
	}

}
