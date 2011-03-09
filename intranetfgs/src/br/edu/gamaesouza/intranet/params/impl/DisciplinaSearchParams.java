package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.params.Params;

public class DisciplinaSearchParams implements Params {
	
	private String cursoNome;
	
	private String disciplinaNome;

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
		System.out.println("Disciplina NOME: " + disciplinaNome);
		this.disciplinaNome = disciplinaNome;
	}

	@Override
	public boolean isEmpty() {
		if ((cursoNome == null || cursoNome.equals("")) && (disciplinaNome == null || (disciplinaNome.equals("")))){
			return true;
		}
		return false;
	}
	
	
	
}
