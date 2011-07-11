package br.edu.gamaesouza.intranet.bean;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.edu.gamaesouza.intranet.utils.AreaEnum;
import br.edu.gamaesouza.intranet.utils.TiposContratacaoEnum;

@Entity
public class Vaga {
	@Id
	@GeneratedValue
	private Long id;
	
	private String cargo;
	private String faixaSalarial;
	private Calendar dataDoAnuncio;
	private String perfil;
	private Long quantidadeDevagas;
	
	@Enumerated(EnumType.STRING)
	private TiposContratacaoEnum regimeDeContratacao;
	
	private String beneficios;
	private String horarioDaVaga;
	private String nivelHierarquico;
	
	@ManyToOne
	private AreaProfissional areaProfissional;
	
	@ManyToOne
	private Empresa empresa;
	
	@OneToOne
	private Pessoa publicador;
	
	private Boolean confirmacao;
	
	private Boolean seAtivo;
	
	@Transient
	private String email;
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFaixaSalarial() {
		return faixaSalarial;
	}

	public void setFaixaSalarial(String faixaSalarial) {
		this.faixaSalarial = faixaSalarial;
	}

	public Calendar getDataDoAnuncio() {
		return dataDoAnuncio;
	}

	public void setDataDoAnuncio(Calendar dataDoAnuncio) {
		this.dataDoAnuncio = dataDoAnuncio;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Long getQuantidadeDevagas() {
		return quantidadeDevagas;
	}

	public void setQuantidadeDevagas(Long quantidadeDevagas) {
		this.quantidadeDevagas = quantidadeDevagas;
	}

	public String getNivelHierarquico() {
		return nivelHierarquico;
	}

	public void setNivelHierarquico(String nivelHierarquico) {
		this.nivelHierarquico = nivelHierarquico;
	}



	public TiposContratacaoEnum getRegimeDeContratacao() {
		return regimeDeContratacao;
	}

	public void setRegimeDeContratacao(TiposContratacaoEnum regimeDeContratacao) {
		this.regimeDeContratacao = regimeDeContratacao;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}



	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setHorarioDaVaga(String horarioDaVaga) {
		this.horarioDaVaga = horarioDaVaga;
	}

	public String getHorarioDaVaga() {
		return horarioDaVaga;
	}



	public void setConfirmacao(Boolean confirmacao) {
		this.confirmacao = confirmacao;
	}

	public Boolean getConfirmacao() {
		return confirmacao;
	}

	public void setSeAtivo(Boolean seAtivo) {
		this.seAtivo = seAtivo;
	}

	public Boolean getSeAtivo() {
		return seAtivo;
	}

	public void setPublicador(Pessoa publicador) {
		this.publicador = publicador;
	}

	public Pessoa getPublicador() {
		return publicador;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setAreaProfissional(AreaProfissional areaProfissional) {
		this.areaProfissional = areaProfissional;
	}

	public AreaProfissional getAreaProfissional() {
		return areaProfissional;
	}
	
	
}
