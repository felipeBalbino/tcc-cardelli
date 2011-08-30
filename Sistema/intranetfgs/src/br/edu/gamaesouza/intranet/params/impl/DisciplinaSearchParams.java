package br.edu.gamaesouza.intranet.params.impl;

import lombok.Data;
import br.edu.gamaesouza.intranet.params.Params;

public @Data class DisciplinaSearchParams implements Params {
	
	private String cursoNome;
	
	private String disciplinaNome;

	@Override
	public boolean isEmpty() {
		if ((cursoNome == null || cursoNome.equals("")) && (disciplinaNome == null || (disciplinaNome.equals("")))){
			return true;
		}
		return false;
	}
	
	
	
}
