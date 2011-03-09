package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "ALUNO")
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
