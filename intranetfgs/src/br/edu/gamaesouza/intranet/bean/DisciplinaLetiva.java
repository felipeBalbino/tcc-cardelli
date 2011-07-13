package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="allDLByDisciplinaAnoSemestre",query="SELECT dl FROM DisciplinaLetiva dl WHERE dl.disciplina.id = :disciplina AND ano = :ano AND semestre = :semestre"),
		@NamedQuery(name="allDLByAnoSemestreTurno",query="SELECT dl FROM DisciplinaLetiva dl WHERE dl.ano = :ano AND semestre = :semestre AND turno = :turno"),
		@NamedQuery(name="allDLByAnoSemestreTurnoProfessor",query="From DisciplinaLetiva where ano = :ano AND semestre = :semestre AND turno = :turno AND professor.nome = :professor"),
		@NamedQuery(name="allDLByAlunoDisciplinaAnoSemestre",query="SELECT dl FROM DisciplinaLetiva dl left join fetch dl.aluno aluno WHERE aluno.id = :aluno AND dl.disciplina.id =  :disciplina AND dl.ano = :ano AND dl.semestre = :semestre")
})


public @Data class DisciplinaLetiva implements Serializable {
	
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
	
	@Column(nullable = false)
	private String sala;
	
	@ManyToMany
	private List<Aluno> aluno;
	
	@ManyToMany(targetEntity=DisciplinaLetivaHorario.class)
	private List<Horario> horarios;
	
	@OneToOne
	private Professor professor;
	
	@OneToOne
	private Disciplina disciplina;
	

	public String toString(){
		return "Turno: "+turno+" -  Semestre: "+semestre+" -  Nome: "+disciplina.getNome();
		
	}

}
