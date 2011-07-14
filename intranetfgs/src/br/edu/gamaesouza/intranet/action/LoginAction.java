package br.edu.gamaesouza.intranet.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;


import org.springframework.beans.factory.annotation.Autowired;
import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.AreaProfissional;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.dao.AreaProfissionalDAO;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.params.impl.AlunoNovoParams;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Log
public class LoginAction extends ActionSupport {
	
	private static final String MSG_LOGIN_DADOS_INVALIDOS = "Dados inválidos, email ou senha atual não conferem.";
	private static final String MSG_REGISTRO_SUCESSO = "Registrado com sucesso, insira seu login e senha registrados anteriormente.";
	private static final String MSG_ALTERA_SENHA_SUCESSO = "Senha editada com sucesso.";
	private static final String MSG_ERRO = "Ocorreu um erro não esperado, retorne para página anteriormente e tente novamente. Se o mesmo erro persistir entre em contato com os administradores.";
	
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private String lingua; 
	
	@Getter @Setter private Aluno aluno;	
	@Getter @Setter private String login;
	@Getter @Setter private String senha;
	@Getter @Setter private String email;
	@Getter @Setter private String novaSenha;
	@Getter @Setter private String senhaAtual;
	@Getter @Setter private Pessoa pessoa; 
	@Getter @Setter private List<Curso> cursos = new ArrayList<Curso>();
	@Getter @Setter private List<AreaProfissional> areas;
	
	@Getter @Setter @Autowired private AlunoNovoParams alunoNovoParams;
	@Getter @Setter @Autowired private PessoaDAO pessoaDAO;
	@Getter @Setter @Autowired private CursoDAO cursoDAO;
	@Getter @Setter @Autowired private EnviarEmail enviarEmail;
	@Getter @Setter @Autowired private AreaProfissionalDAO areaProfissionalDAO;

	public String prepare(){
		try {
			setCursos( cursoDAO.getAllCursos() );
			setAreas(areaProfissionalDAO.getAll());
		} catch ( IntranetException e ) {
			log.warning("Erro no método prepare em LoginAction");
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
				addActionMessage(MSG_ERRO);
			}finally{
				try {
					setCursos( cursoDAO.getAllCursos() );
					setAreas(areaProfissionalDAO.getAll());
				} catch (IntranetException e) {
					addActionMessage(MSG_ERRO);
				}

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
				
				
				if(email.equals(pessoa.getEmail()) && FormUtil.encripta(senhaAtual).equals(pessoa.getSenha()) && pessoa != null){
					pessoa.setSenha(FormUtil.encripta(novaSenha));
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
				
				Pessoa pessoa = pessoaDAO.getPessoaByEmail(email);
				
				//Gerando o password e enviando por Email
				String randomPass = FormUtil.getRandomPass();
				pessoa.setSenha(randomPass);
				enviarEmail.sendEmailWithLoginAndPassword(pessoa);
				

				//Encriptando, salvando em pessoa e dando merge
				pessoa.setSenha(FormUtil.encripta(randomPass));
				pessoaDAO.merge(pessoa);
								
				addActionMessage("Seu login e sua senha foram enviados para o e-mail digitado");
			}catch(IntranetException e){
				addActionError("Ocorreu um erro interno no Servidor. Um e-mail foi enviado ao administrador reportando o erro.");
			}catch(Exception e){
				addActionError("E-mail não confere com nenhum email cadastrado em nosso base.");
				return "recuperar";
			}
			
			return "login";
			
		}
	

}
