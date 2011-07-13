package br.edu.gamaesouza.intranet.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
public @Data class Empresa {
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	private String ramo;
	private String descricao;
	private String site;
	
	@OneToMany(mappedBy="empresa")
	private List<Vaga> vagas;
	
	@OneToOne
	private Endereco endereco;

	@Override
	public String toString() {
		return nome;
	}
	
}
