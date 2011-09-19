package br.edu.gamaesouza.intranet.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetivaHorario;
import br.edu.gamaesouza.intranet.bean.Horario;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.HorarioDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.params.impl.AlunoAlteraParams;
import br.edu.gamaesouza.intranet.params.impl.AlunoNovoParams;
import br.edu.gamaesouza.intranet.params.impl.AlunoSearchParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.StatusMatriculaEnum;

import com.opensymphony.xwork2.ActionSupport;

@Log
public class AlunoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private static final String MSG_REGISTRO_SUCESSO = "Registrado com sucesso.";
	private static final String MSG_EDITADO_SUCESSO  = "Editado com sucesso";
	private static final String MSG_DELETADO_SUCESSO = "Aluno deletado com sucesso";
	
	private static final String RULE_ALUNOS_LISTA  = "RULE_ALUNOS_LISTA";
	private static final String RULE_ALUNOS_SAVE   = "RULE_ALUNOS_SAVE";
	private static final String RULE_ALUNOS_ALTERA = "RULE_ALUNOS_ALTERA";

	@Getter @Setter private Integer id;
	@Getter @Setter private Integer idAluno;
	@Getter @Setter private String email2;
	@Getter @Setter private String senha2;
	@Getter @Setter private String tempoDeResposta;
	@Getter @Setter Map<Horario,List<DisciplinaLetiva>> mapa;
	@Getter @Setter private List<StatusMatriculaEnum> allStatusMatricula = new ArrayList<StatusMatriculaEnum>();
	@Getter @Setter private List<Aluno> alunos= new ArrayList<Aluno>();
	@Getter @Setter private List<Curso> cursos = new ArrayList<Curso>();
	@Getter @Setter private List<DisciplinaLetiva> disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
	
	@Getter @Setter @Autowired private AlunoNovoParams alunoNovoParams ;
	@Getter @Setter @Autowired private AlunoAlteraParams alunoAlteraParams ;
	@Getter @Setter @Autowired private AlunoSearchParams alunoSearchParams;
	@Getter @Setter @Autowired private PessoaDAO pessoaDAO;	
	@Getter @Setter @Autowired private CursoDAO cursoDAO;
	@Getter @Setter @Autowired private HorarioDAO horarioDAO;
	@Getter @Setter @Autowired private DisciplinaDAO disciplinaDAO;
	@Getter @Setter @Autowired private EnviarEmail enviarEmail;

	public String prepare(){
		try {
			setCursos( cursoDAO.getAllCursos() );
			setAllStatusMatricula(Arrays.asList(StatusMatriculaEnum.values()));
		} catch ( IntranetException e ) {
			addActionMessage(e.getMessage());
		}
		return "register";	
	}
	
	public String validarmatricula(){
		try {
			
			Aluno aluno = pessoaDAO.getAlunoByMatricula(alunoNovoParams.getMatricula());
			if(aluno != null){
				addActionError("Matrícula já existente em nossa base.");
				Pessoa pessoa = pessoaDAO.getPessoaByMatricula( alunoNovoParams.getMatricula() );
				addActionError("Aluno: "+pessoa.getNome()+ " - " + "Email: "+pessoa.getEmail()+" - " + "Matricula: "+pessoa.getMatricula());					
			}
		} catch (IntranetException e) {
			log.warning("Erro ao validar matricula.");
			e.printStackTrace();
		}
		return prepare();	
	}
	
	
	public String gerarNovaSenha(){
		try {
			
			Pessoa pessoa = pessoaDAO.getPessoaById(idAluno);
			
			log.info("Gerando o password e enviando por Email.");
			String randomPass = FormUtil.getRandomPass();
			pessoa.setSenha(randomPass);
			enviarEmail.sendEmailWithLoginAndPassword(pessoa);
			
			log.info("Encriptando, salvando em pessoa e dando merge.");
			pessoa.setSenha(FormUtil.encripta(randomPass));
			pessoaDAO.merge(pessoa);
							
			addActionMessage("login e senha foram enviados para o e-mail "+pessoa.getEmail());
		}catch(IntranetException e){
			log.warning("Ocorreu um erro interno no Servidor. Um e-mail foi enviado ao administrador reportando o erro.");
			addActionError("Ocorreu um erro interno no Servidor. Um e-mail foi enviado ao administrador reportando o erro.");
		}catch(Exception e){
			addActionError("E-mail não confere com nenhum email cadastrado em nosso base.");
			return "recuperar";
		}
		
		return lista(); 
	}
	
	public String lista() {
		UserData.grantAccess(RULE_ALUNOS_LISTA);
		try {	
			long inicio = System.currentTimeMillis();  
			setCursos( cursoDAO.getAllCursos() );
			setAllStatusMatricula(Arrays.asList(StatusMatriculaEnum.values()));
			setAlunos( pessoaDAO.getAllAlunosByParams(alunoSearchParams) );
			long  end = System.currentTimeMillis();  
			setTempoDeResposta(FormUtil.tempoResposta(getAlunos(), inicio, end)); 
			//alunoSearchParams = new AlunoSearchParams();
			log.info("Listando todos os alunos em "+ tempoDeResposta);
		} catch (IntranetException e) {
			log.warning("Erro ao listar alunos");
			addActionMessage(e.getMessage());
		}
		return "listAlunos";
	}
	
	public String editar() {
		UserData.grantAccess(RULE_ALUNOS_ALTERA);
		try {
				pessoaDAO.merge(alunoAlteraParams.getAluno());
				addActionMessage(MSG_EDITADO_SUCESSO);
				log.info("Aluno "+ alunoAlteraParams.getNome() +" Alterado com sucesso");
		} catch (IntranetException e) {
			log.warning("Erro ao editar aluno");
			addActionMessage(e.getMessage());
		}
		return lista();
	}
	
	public String gradeParaAdmin() {
		try {
			disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
			disciplinasLetivas = disciplinaDAO.getDisciplinaLetivaByUser(id);
		} catch (IntranetException e) {
			log.warning("Erro na grade do aluno");
			addActionMessage(e.getMessage());
		}
		return "grade";
	}	
	
	public String delete() {
				
		try {
			
			List<DisciplinaLetiva> disciplinaLetiva = disciplinaDAO.getDisciplinaLetivaByUser(idAluno);
			if(disciplinaLetiva.size() == 0){
				pessoaDAO.deleteAluno(idAluno);
				addActionMessage(MSG_DELETADO_SUCESSO);
			}else{
				addActionError("Não é possível efetuar esta operação, este aluno tem "+disciplinaLetiva.size()+" Disciplina(s) letiva(s) vínculada(s)");
				for(DisciplinaLetiva letiva:disciplinaLetiva){
					addActionError(letiva.getDisciplina().getNome()+" - "+letiva.getAno()+"/"+letiva.getSemestre()+" -  Ano:"+letiva.getAno()+" -  Sala:"+letiva.getSala());
				}
				addActionError("Delete primeiro suas disciplinas clicando no icone D.");
			}
		} catch (IntranetException e) {
			log.warning("Erro ao deletar aluno");
			addActionMessage(e.getMessage());
		}
		return lista();
	}	
	
	public String gradeParaAluno() {
	
		return "grade";
	
	}
	
	
	public String registrar() {	
			UserData.grantAccess(RULE_ALUNOS_SAVE);
			try {
				boolean error = false;
			
				
				if(pessoaDAO.validarLogin(alunoNovoParams.getLogin())){
					error=true;
					addActionError("Login já existente em nossa base.");
				}

				
				if (pessoaDAO.validarEmail(alunoNovoParams.getEmail())){
					error=true;
					addActionError("Email já existente em nossa base.");
				}
						
				
				if (alunoNovoParams.getLogin().length() > 8){
					error=true;
					addActionError("Login do Usuário precisar tem menos de 8 caracteres.");
				}
						
				
				if(pessoaDAO.validarMatricula(alunoNovoParams.getMatricula())){
					error=true;
					addActionError("Matrícula já existente em nossa base.");
					Pessoa pessoa = pessoaDAO.getPessoaByMatricula( alunoNovoParams.getMatricula() );
					addActionError("Aluno: "+pessoa.getNome()+ " - " + "Email: "+pessoa.getEmail()+" - " + "Matricula: "+pessoa.getMatricula());					
				}
					
					
				if(error == false){
					pessoaDAO.saveAluno(alunoNovoParams.getAluno());
					addActionMessage(MSG_REGISTRO_SUCESSO);
					
				}
			} catch (IntranetException e) {
				log.warning("Erro ao registrar aluno");
				addActionMessage(e.getMessage());
			}
			return prepare();
				
				
		}

	
}
