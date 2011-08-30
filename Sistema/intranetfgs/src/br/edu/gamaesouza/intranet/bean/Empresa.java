package br.edu.gamaesouza.intranet.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Empresa {
	
	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	@NotEmpty
	private String nome;
	
	@Getter @Setter
	private String ramo;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	private String site;
	
	@OneToMany(mappedBy="empresa")
	
	@Getter @Setter
	private List<Vaga> vagas;
	
	@OneToOne
	@Getter @Setter
	private Endereco endereco;

	@Override
	public String toString() {
		return nome;
	}
	
}
