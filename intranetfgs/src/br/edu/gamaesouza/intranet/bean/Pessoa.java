package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
@DiscriminatorColumn(name="service_class",discriminatorType=DiscriminatorType.STRING) 
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;	
	

	private String nome;
	
	@Column(unique = true, nullable = false)
	private String login;
	
	@Column(unique = true)
	private Integer matricula;

	private String senha;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@ManyToMany(targetEntity=Rule.class) 
	  @JoinTable (
	      name="pessoa_rule",
	      joinColumns=@JoinColumn (name="pessoa_id"),
	      inverseJoinColumns=@JoinColumn (name="regra_id"))
	private List<Rule> regras ;

	public List<Rule> getRegras() {
		return regras;
	}

	public void setRegras(List<Rule> rules) {
		this.regras = rules;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getLogin() {
		return login;
	}
	
	
	public void setLogin(String login) {
		this.login = login;
	}

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMatricula( Integer matricula ) {
		this.matricula = matricula;
	}

	public Integer getMatricula() {
		return matricula;
	}

	
	
	
	
}
