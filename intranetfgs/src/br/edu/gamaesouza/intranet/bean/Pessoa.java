package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
@DiscriminatorColumn(name="service_class",discriminatorType=DiscriminatorType.STRING)
@NamedQueries(value={
		@NamedQuery(name="pessoaByLoginSenha",query="FROM Pessoa WHERE login = :login AND senha = :senha"),
		@NamedQuery(name="pessoaById",query="FROM Pessoa WHERE id = :id"),
		@NamedQuery(name="pessoaByEmail",query="FROM Pessoa WHERE email = :email")
})
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

	public void setDataUltimoAcesso(Calendar dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
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
