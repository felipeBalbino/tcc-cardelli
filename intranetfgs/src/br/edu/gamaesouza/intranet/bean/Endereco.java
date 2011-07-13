package br.edu.gamaesouza.intranet.bean;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

import br.edu.gamaesouza.intranet.utils.PaisEnum;

@Entity
public @Data class Endereco {

	@Id
	@GeneratedValue
	private Long id;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep; 
	private String cidade;
	private String estado;

	@OneToOne(mappedBy="endereco")
	private Empresa empresa;
	
	@Enumerated(EnumType.STRING)
	private PaisEnum pais;

	@OneToOne(mappedBy="endereco")
	private Pessoa pessoa;
	
	private Boolean sePrincipal;


	@Override
	public String toString() {
		StringBuilder ps = new StringBuilder();
		ps.append("Rua " + rua);
		ps.append(", " + numero);
		ps.append(" - " + complemento);
		ps.append(" - " + cidade);
		ps.append(" - " + estado);
		ps.append(",  " + cep);
		ps.append(",  " + pais.getName());		
		return ps.toString();
	}
}
