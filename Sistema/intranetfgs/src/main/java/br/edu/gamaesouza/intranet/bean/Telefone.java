package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
	@Getter @Setter
	private Integer id;	
	
	@Getter @Setter
	@NotEmpty
	private Long ddd;
	
	@Getter @Setter
	@NotEmpty
	private Long numero;
	
	@Getter @Setter
	private Boolean sePrincipal;

	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private EnumTipoTelefone tipo;
	
	@ManyToOne
	@Getter @Setter
	private Pessoa pessoa;
	
	
}
