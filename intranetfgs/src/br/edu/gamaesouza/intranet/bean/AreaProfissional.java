package br.edu.gamaesouza.intranet.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
/*@NamedQueries(value={
		@NamedQuery(name="allAreasByPessoa",query="SELECT dl FROM AreaProfissional dl left join fetch dl.pessoas pessoa WHERE pessoa.id = :pessoa")
})*/
public class AreaProfissional {
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;

	@OneToMany(mappedBy="areaProfissional")
	private List<Vaga> vagas;
	
	@ManyToMany(mappedBy="areasProfissionais")
	private List<Pessoa> pessoas;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
}
	
