package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;

import javax.persistence.*;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="alunosByCurso",query="FROM Aluno a where a.curso.id = :curso")
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
