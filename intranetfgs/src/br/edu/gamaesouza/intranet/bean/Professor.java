package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2010
 */

@Entity
@DiscriminatorValue(value="PROFESSOR")
@NamedQueries(value={
		@NamedQuery(name="professorById",query="FROM Professor WHERE id = :id")	
})
public class Professor extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	
}
