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

import lombok.Data;
import lombok.ToString;

import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;

@Entity
public @Data class Horario {

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
	
	
	public String toString(){
		Date horaInicio = this.horaInicio;
		Date horaFim = this.horaFim;
		String strDateFormat = "hh:mm:ss a";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return "Ano: "+ano+" Hora inicial: "+sdf.format(horaInicio)+" Hora Final: "+sdf.format(horaFim);
		
	}
	
	
	
}
