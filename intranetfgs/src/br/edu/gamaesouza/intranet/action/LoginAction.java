package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.impl.AlunoNovoParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private static final String MSG_LOGIN_DADOS_INVALIDOS = "Dados inv�lidos.";
	private static final String MSG_REGISTRO_SUCESSO = "Registrado com sucesso, insira seu login e senha registrados anteriormente.";
	private static final String MSG_ALTERA_SENHA_SUCESSO = "Senha editada com sucesso.";
	
	
	private static final long serialVersionUID = 1L;
	
	private Aluno aluno;
	
	
	private String login;
	private String senha;
	private String email;
	private String novaSenha;
	
	private Pessoa pessoa; 
	private List<Curso> cursos = new ArrayList<Curso>();
	
	@Autowired private AlunoNovoParams alunoNovoParams ;
	@Autowired private PessoaDAO pessoaDAO;
	@Autowired private CursoDAO cursoDAO;
	
	

	public String prepare(){
		try {
			setCursos( cursoDAO.getAll() );
		} catch ( IntranetException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "register";
		
		
	}
	
	

	
	
		public String in() {

			Pessoa pessoa = null;
			try {
				pessoa = pessoaDAO.getPessoa(login, senha);
			} catch (IntranetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (pessoa == null) {
				addActionMessage(MSG_LOGIN_DADOS_INVALIDOS);
				return "login";
			} else {
				Map<String, Object> mapaSessao = ActionContext.getContext().getSession();
				mapaSessao.put("pessoa", pessoa);
				return "logged";
			}

		}
		
		public String registrar() {			
			try {
				
				if(pessoaDAO.validarLogin(alunoNovoParams.getLogin())){
					addActionMessage("Login já existente em nossa base.");	
						if (pessoaDAO.validarEmail(alunoNovoParams.getEmail()))
							addActionMessage("Email já existente em nossa base.");
				}else{
					pessoaDAO.saveAluno(alunoNovoParams.getAluno());
					addActionMessage(MSG_REGISTRO_SUCESSO);
					return "login";
				}
			} catch (IntranetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return prepare();
				
				
		}
	
	

		public String out() {
	
			Map<String, Object> sessao = ActionContext.getContext().getSession();
			Pessoa pessoa = (Pessoa) sessao.get("pessoa");
			if (pessoa != null) {
				Map<String, Object> session = ActionContext.getContext().getSession();
				((org.apache.struts2.dispatcher.SessionMap<String, Object>) session).invalidate();
	
				return "login";
			} else {
				return "login";
			}
		}
		
		
		public String senha() {
			try {
				Map<String, Object> sessao = ActionContext.getContext().getSession();
				Pessoa pessoa = (Pessoa) sessao.get("pessoa");
				
				if(email.equals(pessoa.getEmail()) && pessoa != null){
					pessoa.setSenha(novaSenha);
					pessoaDAO.update(pessoa);
					sessao.put("pessoa", pessoa);
					addActionMessage(MSG_ALTERA_SENHA_SUCESSO);
					
				}else{
					addActionMessage(MSG_LOGIN_DADOS_INVALIDOS);
				
				}
			} catch (IntranetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "senha";
		

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
	

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	public void setCursos( List<Curso> cursos ) {
		this.cursos = cursos;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	
	
	public AlunoNovoParams getAlunoNovoParams() {
		return alunoNovoParams;
	}
	public void setAlunoNovoParams(AlunoNovoParams alunoNovoParams) {
		this.alunoNovoParams = alunoNovoParams;
	}

}
