package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Data;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */

@Entity
@NamedQueries(value={
		@NamedQuery(name="VaziaRule",query="FROM Rule")
})
public @Data class Rule implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false,unique=true)
	private String nome;	
	
}
