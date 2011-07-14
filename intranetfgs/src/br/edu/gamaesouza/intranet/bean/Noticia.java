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
		@NamedQuery(name="VaziaNoticia",query="FROM Noticia")
})
public class Noticia implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	@NotEmpty @Size(min=10, max=1024)
	private String title;
	
	@Getter @Setter
	@NotEmpty
	@Size(min=10, max=5000)
	private String mensagem;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter @Setter
	@NotEmpty
	private Calendar datahoraPublicacao;
	
	@OneToOne
	@Getter @Setter
	@NotEmpty
	private Pessoa autor;

	@Transient
	@Setter
	private String mensagemComQuebra;

	public String getMensagemComQuebra() {
		String mensagemComBr = mensagem.replaceAll("\n", "<br>");
		return mensagemComBr;
	}	
}
