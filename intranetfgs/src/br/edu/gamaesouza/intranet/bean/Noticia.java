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
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2010
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="",query="")
})
public class Noticia implements Serializable{
	
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Calendar getDatahoraPublicacao() {
		return datahoraPublicacao;
	}

	public void setDatahoraPublicacao(Calendar datahoraPublicacao) {
		this.datahoraPublicacao = datahoraPublicacao;
	}

	public Pessoa getAutor() {
		return autor;
	}

	public void setAutor(Pessoa pessoa) {
		this.autor = pessoa;
	}

	public void setMensagemComQuebra(String mensagemComQuebra) {
		this.mensagemComQuebra = mensagemComQuebra;
	}

	public String getMensagemComQuebra() {
		String mensagemComBr = mensagem.replaceAll("\n", "<br>");
		return mensagemComBr;
	}
	
	
	
	
	
}
