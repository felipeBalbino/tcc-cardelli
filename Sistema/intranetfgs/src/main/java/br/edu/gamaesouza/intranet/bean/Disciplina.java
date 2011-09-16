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
		@NamedQuery(name="VaziaDisciplina",query="FROM Disciplina")
})
public class Disciplina implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Getter @Setter
	private Integer id;

	@Column
	@Getter @Setter
	@NotEmpty
	private String nome;


    @ManyToMany(mappedBy="disciplinas")
    @Getter @Setter
	public List<Curso> cursos;
	
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
