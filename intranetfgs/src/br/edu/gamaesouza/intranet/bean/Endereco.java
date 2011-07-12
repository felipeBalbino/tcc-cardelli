package br.edu.gamaesouza.intranet.bean;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.edu.gamaesouza.intranet.utils.PaisEnum;

@Entity
public class Endereco {

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

	public String getRua() {
		return rua;
	}

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

	public void setRua(String rua) {
		this.rua = rua;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public PaisEnum getPais() {
		return pais;
	}


	public void setPais(PaisEnum pais) {
		this.pais = pais;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
	}


	public void setSePrincipal(Boolean sePrincipal) {
		this.sePrincipal = sePrincipal;
	}


	public Boolean getSePrincipal() {
		return sePrincipal;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getCep() {
		return cep;
	}
	
	
	
}
