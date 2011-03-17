package br.edu.gamaesouza.intranet.bean;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;

@Entity
public class Horario {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	private Date horaFim;
	
	@Column(nullable = false)
	private Integer ano;
	
	@Column(nullable = false)
	private Integer semestre;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}


	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setAno( Integer ano ) {
		this.ano = ano;
	}

	public Integer getAno() {
		return ano;
	}

	public void setSemestre( Integer semestre ) {
		this.semestre = semestre;
	}

	public Integer getSemestre() {
		return semestre;
	}

	
	
	
}
