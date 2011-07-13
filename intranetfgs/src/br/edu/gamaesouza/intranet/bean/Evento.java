package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="VaziaEvento",query="FROM Evento")
})
public @Data class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=1024,nullable = false)
	private String title;
	
	@Column(length=400)
	private String coordenacao;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataFim;
	
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	private Date horaFim;
	
	@Column(length=1024)
	private String local;
	
	@Column(length=6024)
	private String publicoalvo;
	
	@Column(length=8024)
	private String obs;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datahoraPublicacao;
	
	@OneToOne
	private Professor autor;
	
	@Transient
	private String obsComQuebra;
	
	@Transient
	private String publicoAlvoComQuebra;
	
	
	public String getObsComQuebra() {
		String obsComQuebra = obs.replaceAll("\n", "<br>");
		return obsComQuebra;
	}	
	
	public String getPublicoAlvoComQuebra() {
		String obsComQuebra = publicoalvo.replaceAll("\n", "<br>");
		return obsComQuebra;
	
	}


}
