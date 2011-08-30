package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;

@Embeddable
public class DisciplinaLetivaHorarioPK implements Serializable{

	@OneToOne
	private DisciplinaLetiva disciplinaLetiva;
	
	@OneToOne
	private Horario horario;
	
	@Enumerated(EnumType.STRING)
	private DiaSemanaEnum DiaSemana;
	
	public DisciplinaLetiva getDisciplinaLetiva() {
		return disciplinaLetiva;
	}

	public void setDisciplinaLetiva(DisciplinaLetiva disciplinaLetiva) {
		this.disciplinaLetiva = disciplinaLetiva;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public DiaSemanaEnum getDiaSemana() {
		return DiaSemana;
	}

	public void setDiaSemana(DiaSemanaEnum diaSemana) {
		DiaSemana = diaSemana;
	}
	
	
	
}
