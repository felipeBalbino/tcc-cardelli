package br.edu.gamaesouza.intranet.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

@Entity @Audited
/*@NamedQueries(value={
		@NamedQuery(name="allAreasByPessoa",query="SELECT dl FROM AreaProfissional dl left join fetch dl.pessoas pessoa WHERE pessoa.id = :pessoa")
})*/
public class AreaProfissional {
	
	@Id
	@GeneratedValue @Getter @Setter
	private Long id;
	
	@Getter @Setter 
	@NotEmpty
	private String nome;

	@OneToMany(mappedBy="areaProfissional") @Getter @Setter
	private List<Vaga> vagas;
	
	@ManyToMany(mappedBy="areasProfissionais") @Getter @Setter
	private List<Pessoa> pessoas;

	
}
	
