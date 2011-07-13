package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
		@NamedQuery(name="VaziaNoticia",query="FROM Noticia")
})
public @Data class Noticia implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=1024,nullable = false)
	private String title;
	
	@Column(length=5000)
	private String mensagem;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datahoraPublicacao;
	
	@OneToOne
	private Pessoa autor;

	@Transient
	private String mensagemComQuebra;

	public String getMensagemComQuebra() {
		String mensagemComBr = mensagem.replaceAll("\n", "<br>");
		return mensagemComBr;
	}
	
	
	
	
	
}
