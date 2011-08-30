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
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
	@Getter @Setter
	private Integer id;

	@Column(unique=true)
	@Getter @Setter
	@NotEmpty
	private String nome;

	@ManyToMany(fetch=FetchType.EAGER)
	@Getter @Setter
	public List<Disciplina> disciplinas; 
	
	@Getter @Setter
	@NotEmpty(message="Campo 'Carga Horária Completar' é requerido.")
	private Integer cargaHorariaComplementar;
	
}
