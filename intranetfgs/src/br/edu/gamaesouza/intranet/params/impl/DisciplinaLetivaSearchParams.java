package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.params.Params;



public class DisciplinaLetivaSearchParams implements Params {
	
	private Integer ano;
	private Integer semestre;
	private String turno;
	private String disciplinaNome;
	private Integer professor;
	private String sala;



	public Integer getSemestre() {
		return semestre;
	}



	public void setSemestre( Integer semestre ) {
		this.semestre = semestre;
	}



	public String getTurno() {
		return turno;
	}



	public void setTurno( String turno ) {
		this.turno = turno;
	}



	public String getDisciplinaNome() {
		return disciplinaNome;
	}



	public void setDisciplinaNome( String disciplinaNome ) {
		this.disciplinaNome = disciplinaNome;
	}



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



	public void setAno( Integer ano ) {
		this.ano = ano;
	}



	public Integer getAno() {
		return ano;
	}



	public void setProfessor(Integer professor) {
		this.professor = professor;
	}



	public Integer getProfessor() {
		return professor;
	}



	public void setSala(String sala) {
		this.sala = sala;
	}



	public String getSala() {
		return sala;
	}
	
	
	
}
