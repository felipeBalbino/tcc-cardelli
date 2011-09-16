package br.edu.gamaesouza.intranet.bean;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.envers.Audited;


@Entity @Audited
public class DisciplinaLetivaHorario {
	
	@EmbeddedId
	private DisciplinaLetivaHorarioPK disciplinaLetivaHorarioPK;
	
	public DisciplinaLetivaHorario(){
		this.disciplinaLetivaHorarioPK = new DisciplinaLetivaHorarioPK();
	}
	
	public DisciplinaLetivaHorarioPK getDisciplinaLetivaHorarioPK() {
		return disciplinaLetivaHorarioPK;
	}

	public void setDisciplinaLetivaHorarioPK(
			DisciplinaLetivaHorarioPK disciplinaLetivaHoraPK) {
		this.disciplinaLetivaHorarioPK = disciplinaLetivaHoraPK;
	}

	

	


	
	
	
}
