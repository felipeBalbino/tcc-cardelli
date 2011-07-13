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

import lombok.Data;

import br.edu.gamaesouza.intranet.utils.AreaEnum;
import br.edu.gamaesouza.intranet.utils.FaixaSalarialEnum;
import br.edu.gamaesouza.intranet.utils.TiposContratacaoEnum;

@Entity
public @Data class Vaga {
	@Id
	@GeneratedValue
	private Long id;
	
	private String cargo;
	
	@Enumerated(EnumType.STRING)
	private FaixaSalarialEnum faixaSalarial;
	
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
	
	
}
