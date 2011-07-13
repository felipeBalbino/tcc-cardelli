package br.edu.gamaesouza.intranet.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

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

public @Data class AlunoAction extends ActionSupport {
	

	private static final String MSG_REGISTRO_SUCESSO = "Registrado com sucesso.";
	private static final String MSG_EDITADO_SUCESSO  = "Editado com sucesso";
	private static final String MSG_DELETADO_SUCESSO = "Aluno deletado com sucesso";
	
	private static final String RULE_ALUNOS_LISTA  = "RULE_ALUNOS_LISTA";
	private static final String RULE_ALUNOS_SAVE   = "RULE_ALUNOS_SAVE";
	private static final String RULE_ALUNOS_ALTERA = "RULE_ALUNOS_ALTERA";

	private Integer id;
	private Integer idAluno;
	
	private String email2;
	private String senha2;
	
	private static final long serialVersionUID = 1L;
	
	List<StatusMatriculaEnum> allStatusMatricula = new ArrayList<StatusMatriculaEnum>();
	private List<Aluno> alunos= new ArrayList<Aluno>();
	private List<Curso> cursos = new ArrayList<Curso>();
	private List<DisciplinaLetiva> disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
	

	@Autowired private AlunoNovoParams alunoNovoParams ;
	@Autowired private AlunoAlteraParams alunoAlteraParams ;
	@Autowired private AlunoSearchParams alunoSearchParams;
	@Autowired private PessoaDAO pessoaDAO;	
	@Autowired private CursoDAO cursoDAO;
	@Autowired private HorarioDAO horarioDAO;
	@Autowired private DisciplinaDAO disciplinaDAO;
	@Autowired private EnviarEmail enviarEmail;
	
	private String tempoDeResposta;
	
	Map<Horario,List<DisciplinaLetiva>> mapa;


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
				addActionError("Matr�cula j� existente em nossa base.");
				Pessoa pessoa = pessoaDAO.getPessoaByMatricula( alunoNovoParams.getMatricula() );
				addActionError("Aluno: "+pessoa.getNome()+ " - " + "Email: "+pessoa.getEmail()+" - " + "Matricula: "+pessoa.getMatricula());					
			}
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepare();	
	}
	
	
	public String gerarNovaSenha(){
		try {
			
			Pessoa pessoa = pessoaDAO.getPessoaById(idAluno);
			
			//Gerando o password e enviando por Email
			String randomPass = FormUtil.getRandomPass();
			pessoa.setSenha(randomPass);
			enviarEmail.sendEmailWithLoginAndPassword(pessoa);
			

			//Encriptando, salvando em pessoa e dando merge
			pessoa.setSenha(FormUtil.encripta(randomPass));
			pessoaDAO.merge(pessoa);
							
			addActionMessage("login e sua senha foram enviados para o e-mail "+pessoa.getEmail());
		}catch(IntranetException e){
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
		} catch (IntranetException e) {
			addActionMessage(e.getMessage());
		}
		return "listAlunos";
	}
	
	public String editar() {
		UserData.grantAccess(RULE_ALUNOS_ALTERA);
		try {
				pessoaDAO.merge(alunoAlteraParams.getAluno());
				addActionMessage(MSG_EDITADO_SUCESSO);
			
		} catch (IntranetException e) {
			addActionMessage(e.getMessage());
		}
		return lista();
	}
	
	public String gradeParaAdmin() {
		try {
			disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
			disciplinasLetivas = disciplinaDAO.getDisciplinaLetivaByUser(id);
		} catch (IntranetException e) {
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
				addActionError("N�o � poss�vel efetuar esta opera��o, este aluno tem "+disciplinaLetiva.size()+" Disciplina(s) letiva(s) v�nculada(s)");
				for(DisciplinaLetiva letiva:disciplinaLetiva){
					addActionError(letiva.getDisciplina().getNome()+" - "+letiva.getAno()+"/"+letiva.getSemestre()+" -  Ano:"+letiva.getAno()+" -  Sala:"+letiva.getSala());
				}
				addActionError("Delete primeiro suas disciplinas clicando no icone D.");
			}
		} catch (IntranetException e) {
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
					Pessoa pessoa = pessoaDAO.getPessoaByMatricula( alunoNovoParams.getMatricula() );
					addActionError("Aluno: "+pessoa.getNome()+ " - " + "Email: "+pessoa.getEmail()+" - " + "Matricula: "+pessoa.getMatricula());					
				}
					
					
				if(error == false){
					pessoaDAO.saveAluno(alunoNovoParams.getAluno());
					addActionMessage(MSG_REGISTRO_SUCESSO);
				}
			} catch (IntranetException e) {
				addActionMessage(e.getMessage());
			}
			return prepare();
				
				
		}

	
}
