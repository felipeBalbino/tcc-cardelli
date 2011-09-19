package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */

@Entity @Audited
@Inheritance(strategy=InheritanceType.JOINED) 
@NamedQueries(value={
		@NamedQuery(name="pessoaByLoginSenha",query="FROM Pessoa WHERE login = :login AND senha = :senha"),
		@NamedQuery(name="pessoaById",query="FROM Pessoa WHERE id = :id"),
		@NamedQuery(name="pessoaByEmail",query="FROM Pessoa WHERE email = :email"),
		@NamedQuery(name="pessoaByAreaProfissional",query="SELECT dl FROM Pessoa dl left join fetch dl.areasProfissionais area WHERE area.id = :area")
})
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Getter
	private Integer id;	
	
	@Getter @Setter
	@NotEmpty
	private String nome;
	
	@Column(unique = true, nullable = false, length=8)
	@Getter @Setter
	@NotEmpty 
	private String login;
	
	@Column(unique = true)
	@Getter @Setter
	private Integer matricula;

	@Getter @Setter
	@NotEmpty
	private String senha;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Setter
	private Calendar dataUltimoAcesso;
	
	@Column(unique = true, nullable = false)
	@Getter @Setter
	@Email @NotEmpty
	private String email;
	
	@Getter @Setter
	@ManyToMany(targetEntity=Rule.class) 
	  @JoinTable (
	      name="pessoa_rule",
	      joinColumns=@JoinColumn (name="pessoa_id"),
	      inverseJoinColumns=@JoinColumn (name="regra_id"))
	private List<Rule> regras ;
	
	@ManyToMany
	@Getter @Setter
	private List<AreaProfissional> areasProfissionais;

	@OneToOne
	@Getter @Setter
	private Endereco endereco;
	
	@OneToMany(mappedBy="pessoa")
	@Getter @Setter
	private List<Telefone> telefones;
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	// Bug Struts - Se passa parâmetros como String.
	public void setId(String id) {
		setId(Integer.parseInt(id));
	}


	public String getDataUltimoAcesso() {
		if (dataUltimoAcesso != null){
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return "- último acesso em: "+sdf.format(dataUltimoAcesso.getTime());
		}else{
			return "";
		}
	}


	
	
	
	
}
