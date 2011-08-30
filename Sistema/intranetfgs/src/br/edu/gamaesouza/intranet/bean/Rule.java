package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
		@NamedQuery(name="VaziaRule",query="FROM Rule")
})
public class Rule implements Serializable{
	
	@Id
	@GeneratedValue
	@Getter @Setter
	private Integer id;
	
	@Column(unique=true)
	@Getter @Setter
	@NotEmpty
	private String nome;	
	
}
