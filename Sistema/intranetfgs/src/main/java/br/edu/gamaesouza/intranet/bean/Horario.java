package br.edu.gamaesouza.intranet.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

@Entity @Audited
public class Horario {

	@Id
	@GeneratedValue
	@Getter @Setter
	private Integer id;
	
	@Temporal(TemporalType.TIME)
	@Getter @Setter
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	@Getter @Setter
	private Date horaFim;
	
	@Getter @Setter
	@NotEmpty
	private Integer ano;
	
	@Getter @Setter
	@NotEmpty
	private Integer semestre;
	
	
	public String toString(){
		Date horaInicio = this.horaInicio;
		Date horaFim = this.horaFim;
		String strDateFormat = "hh:mm:ss a";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return "Ano: "+ano+" Hora inicial: "+sdf.format(horaInicio)+" Hora Final: "+sdf.format(horaFim);
		
	}
}
