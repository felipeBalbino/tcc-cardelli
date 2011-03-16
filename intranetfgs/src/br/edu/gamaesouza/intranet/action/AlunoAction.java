package br.edu.gamaesouza.intranet.action;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.impl.AlunoNovoParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import com.opensymphony.xwork2.ActionSupport;

public class AlunoAction extends ActionSupport {
	

	private static final String MSG_REGISTRO_SUCESSO = "Registrado com sucesso.";
	
	private static final String RULE_ALUNOS_LISTA = "RULE_ALUNOS_LISTA";
	private static final String RULE_ALUNOS_SAVE = "RULE_ALUNOS_SAVE";
	private static final String RULE_ALUNOS_ALTERA = "RULE_ALUNOS_ALTERA";
	
	private static final long serialVersionUID = 1L;
	
	private List<Aluno> alunos= new ArrayList<Aluno>();
	private List<Curso> cursos = new ArrayList<Curso>();
	
	@Autowired private AlunoNovoParams alunoNovoParams ;
	@Autowired private PessoaDAO pessoaDAO;	
	@Autowired private CursoDAO cursoDAO;
	

	public String prepare(){
		try {
			setCursos( cursoDAO.getAllCursos() );
		} catch ( IntranetException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "register";	
	}
	
	public String lista() {
		
		UserData.grantAccess(RULE_ALUNOS_LISTA);
		try {
			setAlunos( pessoaDAO.getAllAlunos() );
		} catch (IntranetException e) {
		}
		return "listAlunos";
		
	}
	public String editar() {
		UserData.grantAccess(RULE_ALUNOS_ALTERA);

		return lista();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return prepare();
				
				
		}

	
	public AlunoNovoParams getAlunoNovoParams() {
		return alunoNovoParams;
	}
	public void setAlunoNovoParams(AlunoNovoParams alunoNovoParams) {
		this.alunoNovoParams = alunoNovoParams;
	}

	public void setAlunos( List<Aluno> alunos ) {
		this.alunos = alunos;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setCursos( List<Curso> cursos ) {
		this.cursos = cursos;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

}
