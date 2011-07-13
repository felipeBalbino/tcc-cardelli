package br.edu.gamaesouza.intranet.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
@NamedQuery(name="horasByAlunoAndIdHora",query="SELECT comp FROM Hora comp where comp.aluno.id = :aluno AND comp.id = :hora")
public @Data class Hora {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	private Aluno aluno;
	
}
