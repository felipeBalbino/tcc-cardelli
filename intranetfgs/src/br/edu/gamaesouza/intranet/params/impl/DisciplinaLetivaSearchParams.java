package br.edu.gamaesouza.intranet.params.impl;

import lombok.Data;
import br.edu.gamaesouza.intranet.params.Params;



public @Data class DisciplinaLetivaSearchParams implements Params {
	
	private Integer ano;
	private Integer semestre;
	private String turno;
	private String disciplinaNome;
	private Integer professor;
	private String sala;

	@Override
	public boolean isEmpty() {
		if ((turno == null || turno.equals("") || turno.equals("-1")) && 
			(semestre == null || semestre == -1) &&
			(ano == null || ano == -1) && 
			(disciplinaNome == null || disciplinaNome.equals("")) && 
			(sala == null || sala.equals("")) &&
			(professor == null || professor == -1)
			
			){
			return true;
		}
		return false;
	}
	
}
