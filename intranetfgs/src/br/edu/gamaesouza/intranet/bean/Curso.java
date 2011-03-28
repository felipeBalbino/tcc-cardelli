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
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="VaziaCurso",query="FROM Curso")
})
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique=true)
	private String nome;

	@ManyToMany(
			fetch=FetchType.EAGER
	     ) 
	    @JoinTable (name="disciplina_curso",
	            joinColumns={@JoinColumn(name="curso_id")},
	            inverseJoinColumns={@JoinColumn(name="disciplina_id")}
	            )
	public List<Disciplina> disciplinas; 
	
	private Integer cargaHorariaComplementar;
	
	public Integer getCargaHorariaComplementar() {
		return cargaHorariaComplementar;
	}

	public void setCargaHorariaComplementar(Integer cargaHorariaComplementar) {
		this.cargaHorariaComplementar = cargaHorariaComplementar;
	}

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

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}



}
