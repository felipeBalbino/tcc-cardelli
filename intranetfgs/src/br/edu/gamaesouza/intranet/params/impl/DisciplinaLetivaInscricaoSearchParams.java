package br.edu.gamaesouza.intranet.params.impl;

import lombok.Data;
import br.edu.gamaesouza.intranet.params.Params;



public @Data class DisciplinaLetivaInscricaoSearchParams implements Params {
	
	private Integer ano;
	private Integer semestre;
	private String turno;
	private String sala;

	@Override
	public boolean isEmpty() {
		if ((turno == null || turno.equals("") || turno.equals("-1")) && 
			(semestre == null || semestre == -1) &&
			(ano == null || ano == -1) && 
			(sala == null || sala.equals("")|| sala.equals("-1"))
			){
			return true;
		}
		return false;
	}	
	
}
