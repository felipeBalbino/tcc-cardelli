package br.edu.gamaesouza.intranet.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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

	
	public String toString(){
		Date horaInicio = this.horaInicio;
		Date horaFim = this.horaFim;
		String strDateFormat = "hh:mm:ss a";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return "Ano: "+ano+" Hora inicial: "+sdf.format(horaInicio)+" Hora Final: "+sdf.format(horaFim);
		
	}
	
	
	
}
