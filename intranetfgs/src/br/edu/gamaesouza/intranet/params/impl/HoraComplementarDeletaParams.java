package br.edu.gamaesouza.intranet.params.impl;

import lombok.Data;
import br.edu.gamaesouza.intranet.bean.Hora;
import br.edu.gamaesouza.intranet.params.HoraComplementarParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public @Data class HoraComplementarDeletaParams implements HoraComplementarParams  {

private String alunoId;
	
	private String horaId;
	
	@Override
	public Hora getHora() throws IntranetException {
		// TODO Auto-generated method stub
		return null;
	}

}
