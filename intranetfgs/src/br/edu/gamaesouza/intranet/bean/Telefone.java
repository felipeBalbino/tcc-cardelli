package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import br.edu.gamaesouza.intranet.utils.EnumTipoTelefone;
/**
 * @author Felipe Balbino
 * @since 11/06/2011
 */

@Entity
public @Data class Telefone implements Serializable {
	
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
	
	
}
