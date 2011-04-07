package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;
import br.edu.gamaesouza.intranet.utils.StatusMatriculaEnum;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="alunosByCurso",query="FROM Aluno a where a.curso.id = :curso"),
		@NamedQuery(name="alunoById",query="FROM Aluno WHERE id = :id"),
		@NamedQuery(name="totalHorasCursoEAluno",query="SELECT DISTINCT new br.edu.gamaesouza.intranet.bean.result.HorasCursoResultBean(sum(comp.minutos), comp.aluno.curso.cargaHorariaComplementar) FROM HoraComplementar comp  where comp.aluno.id = :aluno GROUP BY comp.aluno.id")
})
public class Aluno extends Pessoa implements Serializable {
	
	@OneToOne
	private Curso curso;

	@OneToMany
	private List<Hora> horas;
	
	private Integer periodo;
	@Enumerated(EnumType.STRING)
	private StatusMatriculaEnum statusMatricula;
   
   

	public Curso getCurso() { return curso; }
	public void setCurso(Curso curso) { this.curso = curso; }   

	public StatusMatriculaEnum getStatusMatricula() { return statusMatricula; }
	public void setStatusMatricula(StatusMatriculaEnum statusMatricula) { this.statusMatricula = statusMatricula; }
   
	public Integer getPeriodo() { return periodo; }
	public void setPeriodo(Integer periodo) { this.periodo = periodo; }

	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}

	public List<Hora> getHoras() {
		return horas;
	}
	
	

}
