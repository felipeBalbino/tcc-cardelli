package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2010
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="allDLByDisciplinaAnoSemestre",query="SELECT dl FROM DisciplinaLetiva dl WHERE dl.disciplina.id = ?1 AND ano = ?2 AND semestre = ?3"),
		@NamedQuery(name="allDLByAnoSemestreTurno",query="SELECT dl FROM DisciplinaLetiva dl WHERE dl.ano = ?1 AND semestre = ?2 AND turno = ?3"),
		@NamedQuery(name="allDLByAnoSemestreTurnoProfessor",query="From DisciplinaLetiva where ano = ?1 AND semestre = ?2 AND turno = ?3 AND professor.nome = ?4"),
		@NamedQuery(name="allDLByAlunoDisciplinaAnoSemestre",query="SELECT dl FROM DisciplinaLetiva dl left join fetch dl.aluno aluno WHERE aluno.id = ?1 AND dl.disciplina.id =  ?2 AND dl.ano = ?3 AND dl.semestre = ?4")
})


public class DisciplinaLetiva implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private Integer ano;
	
	@Column(nullable = false)
	private Integer semestre;

	@Column(nullable = false)
	private String turno;
	
	@ManyToMany
	private List<Aluno> aluno;
	
	@OneToOne
	private Professor professor;
	
	@OneToOne
	private Disciplina disciplina;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getSemestre() {
		return semestre;
	}
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public void setTurno( String turno ) {
		this.turno = turno;
	}
	public String getTurno() {
		return turno;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setAluno(List<Aluno> aluno) {
		this.aluno = aluno;
	}
	public List<Aluno> getAluno() {
		return aluno;
	}


}
