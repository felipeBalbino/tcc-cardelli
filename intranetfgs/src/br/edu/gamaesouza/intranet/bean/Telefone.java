package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import javax.persistence.*;

import br.edu.gamaesouza.intranet.utils.EnumTipoTelefone;
/**
 * @author Felipe Balbino
 * @since 11/06/2011
 */

@Entity
public class Telefone implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;	
	
	private Long ddd;
	
	@Column(nullable=false)
	private Long numero;
	
	private Boolean sePrincipal;

	@Enumerated(EnumType.STRING)
	private EnumTipoTelefone tipo;
	
	@ManyToOne
	private Pessoa pessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getDdd() {
		return ddd;
	}

	public void setDdd(Long ddd) {
		this.ddd = ddd;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Boolean getSePrincipal() {
		return sePrincipal;
	}

	public void setSePrincipal(Boolean sePrincipal) {
		this.sePrincipal = sePrincipal;
	}

	public EnumTipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoTelefone tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
}
