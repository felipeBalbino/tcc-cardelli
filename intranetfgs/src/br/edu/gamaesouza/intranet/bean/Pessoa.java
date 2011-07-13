package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
@NamedQueries(value={
		@NamedQuery(name="pessoaByLoginSenha",query="FROM Pessoa WHERE login = :login AND senha = :senha"),
		@NamedQuery(name="pessoaById",query="FROM Pessoa WHERE id = :id"),
		@NamedQuery(name="pessoaByEmail",query="FROM Pessoa WHERE email = :email"),
		@NamedQuery(name="pessoaByAreaProfissional",query="SELECT dl FROM Pessoa dl left join fetch dl.areasProfissionais area WHERE area.id = :area")
})
public @Data class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;	
	

	private String nome;
	
	@Column(unique = true, nullable = false, length=8)
	private String login;
	
	@Column(unique = true)
	private Integer matricula;

	private String senha;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataUltimoAcesso;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@ManyToMany(targetEntity=Rule.class) 
	  @JoinTable (
	      name="pessoa_rule",
	      joinColumns=@JoinColumn (name="pessoa_id"),
	      inverseJoinColumns=@JoinColumn (name="regra_id"))
	private List<Rule> regras ;
	
	@ManyToMany
	private List<AreaProfissional> areasProfissionais;

	@OneToOne
	private Endereco endereco;
	
	@OneToMany(mappedBy="pessoa")
	private List<Telefone> telefones;
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	// Bug Struts - S� passa par�metros como String.
	public void setId(String id) {
		setId(Integer.parseInt(id));
	}


	public String getDataUltimoAcesso() {
		if (dataUltimoAcesso != null){
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return "- �ltimo acesso em: "+sdf.format(dataUltimoAcesso.getTime());
		}else{
			return "";
		}
	}


	
	
	
	
}
