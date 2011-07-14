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
import lombok.Getter;
import lombok.Setter;

import br.edu.gamaesouza.intranet.utils.AreaEnum;
import br.edu.gamaesouza.intranet.utils.FaixaSalarialEnum;
import br.edu.gamaesouza.intranet.utils.TiposContratacaoEnum;

@Entity
public @Data class Vaga {
	
	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String cargo;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private FaixaSalarialEnum faixaSalarial;
	
	@Getter @Setter
	private Calendar dataDoAnuncio;
	
	@Getter @Setter
	private String perfil;
	
	@Getter @Setter
	private Long quantidadeDevagas;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private TiposContratacaoEnum regimeDeContratacao;
	
	@Getter @Setter
	private String beneficios;
	
	@Getter @Setter
	private String horarioDaVaga;
	
	@Getter @Setter
	private String nivelHierarquico;
	
	@ManyToOne
	@Getter @Setter
	private AreaProfissional areaProfissional;
	
	@ManyToOne
	@Getter @Setter
	private Empresa empresa;
	
	@OneToOne
	@Getter @Setter
	private Pessoa publicador;
	
	@Getter @Setter
	private Boolean confirmacao;
	
	@Getter @Setter
	private Boolean seAtivo;
	
	@Transient
	@Getter @Setter
	private String email;
	
	
}
