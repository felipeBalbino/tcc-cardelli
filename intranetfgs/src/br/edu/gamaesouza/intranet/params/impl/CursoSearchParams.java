package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.params.Params;

public class CursoSearchParams implements Params {

	private String cursoNome;
	
	private String disciplinaNome;
	
	
	@Override
	public boolean isEmpty() {
		if ((cursoNome == null || cursoNome.equals("")) && (disciplinaNome == null || disciplinaNome.equals(""))){
			return true;
		}
		return false;
	}


	public String getCursoNome() {
		return cursoNome;
	}


	public void setCursoNome(String cursoNome) {
		this.cursoNome = cursoNome;
	}


	public String getDisciplinaNome() {
		return disciplinaNome;
	}


	public void setDisciplinaNome(String disciplinaNome) {
		this.disciplinaNome = disciplinaNome;
	}
	
	

}
