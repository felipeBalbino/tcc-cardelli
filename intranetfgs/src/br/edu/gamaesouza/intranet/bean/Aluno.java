package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;

import javax.persistence.*;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2010
 */
@Entity
@DiscriminatorValue(value = "ALUNO")
@NamedQueries(value={
		@NamedQuery(name="",query="")
})
public class Aluno extends Pessoa implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private Curso curso;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
