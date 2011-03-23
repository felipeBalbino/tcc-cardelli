package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="alunosByCurso",query="FROM Aluno a where a.curso.id = :curso"),
		@NamedQuery(name="alunoById",query="FROM Aluno WHERE id = :id")
})
public class Aluno extends Pessoa implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private Curso curso;

	@OneToMany
	private List<Hora> horas;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Hora> getHoras() {
		return horas;
	}

	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}

}
