package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2010
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="VaziaDisciplina",query="FROM Disciplina")
})
public class Disciplina implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String nome;


    @ManyToMany(
       fetch=FetchType.EAGER,
       mappedBy="disciplinas"
    )
	public List<Curso> cursos;
	
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	@Override
	public boolean equals(Object obj) {
		Disciplina disciplina = null;
		if (obj instanceof Disciplina){
			disciplina = (Disciplina) obj;
		}else{
			return false;
		}
		
		if (this.getId().equals(disciplina.getId())){
			return true;
		}else{
			return false;
		}

	}
	
}
