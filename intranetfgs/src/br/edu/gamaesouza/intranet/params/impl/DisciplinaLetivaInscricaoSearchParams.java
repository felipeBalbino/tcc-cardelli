package br.edu.gamaesouza.intranet.params.impl;

import br.edu.gamaesouza.intranet.params.Params;



public class DisciplinaLetivaInscricaoSearchParams implements Params {
	
	private Integer ano;
	private Integer semestre;
	private String turno;
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



	public void setAno( Integer ano ) {
		this.ano = ano;
	}



	public Integer getAno() {
		return ano;
	}






	public void setSala(String sala) {
		this.sala = sala;
	}



	public String getSala() {
		return sala;
	}
	
	
	
}
