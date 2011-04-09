package br.edu.gamaesouza.intranet.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.params.impl.AlunoNovoParams;
import br.edu.gamaesouza.intranet.params.impl.EventoAlteraParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private static final String MSG_LOGIN_DADOS_INVALIDOS = "Dados inv�lidos, email ou senha atual n�o conferem.";
	private static final String MSG_REGISTRO_SUCESSO = "Registrado com sucesso, insira seu login e senha registrados anteriormente.";
	private static final String MSG_ALTERA_SENHA_SUCESSO = "Senha editada com sucesso.";
	
	
	private static final long serialVersionUID = 1L;
	
	private Aluno aluno;
	
	
	private String login;
	private String senha;
	private String email;
	private String novaSenha;
	private String senhaAtual;
	
	private Pessoa pessoa; 
	private List<Curso> cursos = new ArrayList<Curso>();
	
	@Autowired private AlunoNovoParams alunoNovoParams ;
	@Autowired private PessoaDAO pessoaDAO;
	@Autowired private CursoDAO cursoDAO;
	@Autowired private EnviarEmail enviarEmail;
	
	

	public String prepare(){
		try {
			setCursos( cursoDAO.getAllCursos() );
		} catch ( IntranetException e ) {
			addActionMessage(e.getMessage());
		}
		return "register";
		
		
	}
		public String in() {

			Pessoa pessoa = null;
			try {
				pessoa = pessoaDAO.getPessoa(login, senha);
			} catch (IntranetException e) {
				
			}

			if (pessoa == null) {
				addActionError(MSG_LOGIN_DADOS_INVALIDOS);
				return "login";
			} else {
				Map<String, Object> mapaSessao = ActionContext.getContext().getSession();
				mapaSessao.put("pessoa", pessoa);
				return "logged";
			}

		}
		
		public String registrar() {			
			try {				
				boolean error = false;
			
				
				if(pessoaDAO.validarLogin(alunoNovoParams.getLogin())){
					error=true;
					addActionError("Login j� existente em nossa base.");
				}

				
				if (pessoaDAO.validarEmail(alunoNovoParams.getEmail())){
					error=true;
					addActionError("Email j� existente em nossa base.");
				}
						
				
				if (alunoNovoParams.getLogin().length() > 8){
					error=true;
					addActionError("Login do Usu�rio precisar tem menos de 8 caracteres.");
				}
						
				
				if(pessoaDAO.validarMatricula(alunoNovoParams.getMatricula())){
					error=true;
					addActionError("Matr�cula j� existente em nossa base.");
				}
					
					
				if(error == false){
					pessoaDAO.saveAluno(alunoNovoParams.getAluno());
					addActionMessage(MSG_REGISTRO_SUCESSO);
					return "register";
				}
			} catch (IntranetException e) {
				addActionMessage(e.getMessage());
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
		
		
		
		public String sessionClosed() {
			addActionError("A sua sessão expirou, você ficou muito tempo sem enviar ou alterar nada dentro da intranet, ocasionando o fechamento da sua sessão. Efetue o login novamente.");
			return "login";
		}
		
		public String senha() {
			try {
				Map<String, Object> sessao = ActionContext.getContext().getSession();
				Pessoa pessoa = (Pessoa) sessao.get("pessoa");
				
				if(email.equals(pessoa.getEmail()) && 
				   senhaAtual.equals(pessoa.getSenha()) && 
				   pessoa != null){
					
					pessoa.setSenha(novaSenha);
					pessoaDAO.merge(pessoa);
					sessao.put("pessoa", pessoa);
					pessoa = (Pessoa) SpringUtil.getBean("pessoa");
					addActionMessage(MSG_ALTERA_SENHA_SUCESSO);
					
				}else{
					addActionError(MSG_LOGIN_DADOS_INVALIDOS);
				
				}
			} catch (IntranetException e) {
				addActionError(MSG_LOGIN_DADOS_INVALIDOS);
			}
			
			return "senha";
		

		}
		
		public String recuperarSenha(){
			try {
				enviarEmail.sendEmailWithLoginAndPassword(email);
				addActionMessage("Seu login e sua senha foram enviados para o e-mail digitado");
			}catch(IntranetException e){
				addActionError("Ocorreu um erro interno no Servidor. Um e-mail foi enviado ao administrador reportando o erro.");
			}catch(Exception e){
				addActionError("E-mail n�o confere com nenhum email cadastrado em nosso base.");
				return "recuperar";
			}
			
			return "login";
			
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





	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}





	public String getSenhaAtual() {
		return senhaAtual;
	}

}
