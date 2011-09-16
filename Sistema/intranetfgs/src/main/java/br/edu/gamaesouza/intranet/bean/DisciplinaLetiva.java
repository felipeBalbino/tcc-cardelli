package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity @Audited
@NamedQueries(value={
		@NamedQuery(name="allDLByDisciplinaAnoSemestre",query="SELECT dl FROM DisciplinaLetiva dl WHERE dl.disciplina.id = :disciplina AND ano = :ano AND semestre = :semestre"),
		@NamedQuery(name="allDLByAnoSemestreTurno",query="SELECT dl FROM DisciplinaLetiva dl WHERE dl.ano = :ano AND semestre = :semestre AND turno = :turno"),
		@NamedQuery(name="allDLByAnoSemestreTurnoProfessor",query="From DisciplinaLetiva where ano = :ano AND semestre = :semestre AND turno = :turno AND professor.nome = :professor"),
		@NamedQuery(name="allDLByAlunoDisciplinaAnoSemestre",query="SELECT dl FROM DisciplinaLetiva dl left join fetch dl.aluno aluno WHERE aluno.id = :aluno AND dl.disciplina.id =  :disciplina AND dl.ano = :ano AND dl.semestre = :semestre")
})


public class DisciplinaLetiva implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	@NotEmpty
	private Integer ano;
	
	@Getter @Setter
	@NotEmpty
	private Integer semestre;

	@Getter @Setter
	@NotEmpty
	private String turno;
	
	@Getter @Setter
	private String sala;
	
	@ManyToMany
	@Getter @Setter
	private List<Aluno> aluno;
	
	@ManyToMany(targetEntity=DisciplinaLetivaHorario.class)
	@Getter @Setter
	private List<Horario> horarios;
	
	@OneToOne
	@Getter @Setter
	@NotEmpty
	private Professor professor;
	
	@OneToOne
	@Getter @Setter
	@NotEmpty
	private Disciplina disciplina;
	

	public String toString(){
		return "Turno: "+turno+" -  Semestre: "+semestre+" -  Nome: "+disciplina.getNome();
		
	}

}
