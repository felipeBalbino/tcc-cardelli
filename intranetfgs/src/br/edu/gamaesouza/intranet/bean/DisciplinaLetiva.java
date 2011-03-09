package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
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
	public List<Aluno> getAluno() {
		return aluno;
	}
	public void setAluno(List<Aluno> aluno) {
		this.aluno = aluno;
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

}
