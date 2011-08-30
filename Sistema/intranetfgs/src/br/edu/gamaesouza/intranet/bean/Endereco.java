package br.edu.gamaesouza.intranet.bean;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import br.edu.gamaesouza.intranet.utils.PaisEnum;

@Entity
public class Endereco {

	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String rua;
	
	@Getter @Setter
	private String numero;
	
	@Getter @Setter
	private String complemento;
	
	@Getter @Setter
	private String bairro;
	
	@Getter @Setter
	private String cep; 
	
	@Getter @Setter
	private String cidade;
	
	@Getter @Setter
	private String estado;

	@OneToOne(mappedBy="endereco")
	
	@Getter @Setter
	private Empresa empresa;
	
	@Enumerated(EnumType.STRING)
	
	@Getter @Setter
	private PaisEnum pais;

	@OneToOne(mappedBy="endereco")
	
	@Getter @Setter
	private Pessoa pessoa;
	
	@Getter @Setter
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
