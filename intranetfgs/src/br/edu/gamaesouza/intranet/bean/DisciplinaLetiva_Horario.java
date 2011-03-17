package br.edu.gamaesouza.intranet.bean;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;


@Entity
public class DisciplinaLetiva_Horario {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private DiaSemanaEnum DiaSemana;
	
	@OneToOne
	private DisciplinaLetiva disciplinaLetiva;
	
	@OneToOne
	private Horario horario;

	public void setId( Integer id ) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setDisciplinaLetiva( DisciplinaLetiva disciplinaLetiva ) {
		this.disciplinaLetiva = disciplinaLetiva;
	}

	public DisciplinaLetiva getDisciplinaLetiva() {
		return disciplinaLetiva;
	}

	public void setHorario( Horario horario ) {
		this.horario = horario;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setDiaSemana( DiaSemanaEnum diaSemana ) {
		DiaSemana = diaSemana;
	}

	public DiaSemanaEnum getDiaSemana() {
		return DiaSemana;
	}


	


	
	
	
}
