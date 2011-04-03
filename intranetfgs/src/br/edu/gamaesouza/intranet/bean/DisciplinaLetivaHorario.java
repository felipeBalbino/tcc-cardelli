package br.edu.gamaesouza.intranet.bean;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;


@Entity
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
