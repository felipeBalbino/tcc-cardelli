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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="VaziaEvento",query="FROM Evento")
})
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	@Size(min=10,max=1024)
	@NotEmpty
	private String title;
	
	@Getter @Setter
	@Size(min=10,max=400)
	private String coordenacao;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Calendar dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Calendar dataFim;
	
	@Temporal(TemporalType.TIME)
	@Getter @Setter
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	@Getter @Setter
	private Date horaFim;
	
	@Column(length=1024)
	@Getter @Setter
	private String local;
	
	@Column(length=6024)
	@Getter @Setter
	private String publicoalvo;
	
	@Column(length=8024)
	@Getter @Setter
	private String obs;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter @Setter
	private Calendar datahoraPublicacao;
	
	@OneToOne
	@Getter @Setter
	private Professor autor;
	
	@Transient
	@Getter @Setter
	private String obsComQuebra;
	
	@Transient
	@Getter @Setter
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
