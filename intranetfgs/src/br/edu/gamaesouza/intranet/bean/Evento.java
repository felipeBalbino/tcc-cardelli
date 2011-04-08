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
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(String coordenacao) {
		this.coordenacao = coordenacao;
	}



	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getPublicoalvo() {
		return publicoalvo;
	}

	public void setPublicoalvo(String publicoalvo) {
		this.publicoalvo = publicoalvo;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Calendar getDatahoraPublicacao() {
		return datahoraPublicacao;
	}

	public void setDatahoraPublicacao(Calendar datahoraPublicacao) {
		this.datahoraPublicacao = datahoraPublicacao;
	}

	public Professor getAutor() {
		return autor;
	}

	public void setAutor(Professor pessoa) {
		this.autor = pessoa;
	}

	public void setObsComQuebra( String obsComQuebra ) {
		this.obsComQuebra = obsComQuebra;
	}

	public String getObsComQuebra() {
		String obsComQuebra = obs.replaceAll("\n", "<br>");
		return obsComQuebra;
	}	
	
	public String getPublicoAlvoComQuebra() {
		String obsComQuebra = publicoalvo.replaceAll("\n", "<br>");
		return obsComQuebra;
	
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}


	
	

}
