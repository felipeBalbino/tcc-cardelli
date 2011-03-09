package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value="PROFESSOR")
public class Professor extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	
}
