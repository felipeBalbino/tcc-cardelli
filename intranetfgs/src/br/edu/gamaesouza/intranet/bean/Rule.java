package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */

@Entity
@NamedQueries(value={
		@NamedQuery(name="VaziaRule",query="FROM Rule")
})
public class Rule implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false,unique=true)
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
