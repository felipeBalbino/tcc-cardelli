package br.edu.gamaesouza.intranet.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
/*@NamedQueries(value={
		@NamedQuery(name="allAreasByPessoa",query="SELECT dl FROM AreaProfissional dl left join fetch dl.pessoas pessoa WHERE pessoa.id = :pessoa")
})*/
public @Data class AreaProfissional {
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;

	@OneToMany(mappedBy="areaProfissional")
	private List<Vaga> vagas;
	
	@ManyToMany(mappedBy="areasProfissionais")
	private List<Pessoa> pessoas;

	
}
	
