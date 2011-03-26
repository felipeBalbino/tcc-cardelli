package br.edu.gamaesouza.intranet.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import br.edu.gamaesouza.intranet.params.impl.AlunoAlteraParams;
import br.edu.gamaesouza.intranet.params.impl.AlunoNovoParams;
import br.edu.gamaesouza.intranet.params.impl.AlunoSearchParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.StatusMatriculaEnum;

import com.opensymphony.xwork2.ActionSupport;

public class AlunoAction extends ActionSupport {
	

	private static final String MSG_REGISTRO_SUCESSO = "Registrado com sucesso.";
	private static final String MSG_EDITADO_SUCESSO = 	"Editado com sucesso";
	
	private static final String RULE_ALUNOS_LISTA = "RULE_ALUNOS_LISTA";
	private static final String RULE_ALUNOS_SAVE = "RULE_ALUNOS_SAVE";
	private static final String RULE_ALUNOS_ALTERA = "RULE_ALUNOS_ALTERA";

	private Integer id;
	
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
	


	public String prepare(){
		try {
			setCursos( cursoDAO.getAllCursos() );
			setAllStatusMatricula(Arrays.asList(StatusMatriculaEnum.values()));
		} catch ( IntranetException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "register";	
	}
	
	public String lista() {
		UserData.grantAccess(RULE_ALUNOS_LISTA);
		try {
			setCursos( cursoDAO.getAllCursos() );
			setAllStatusMatricula(Arrays.asList(StatusMatriculaEnum.values()));
			setAlunos( pessoaDAO.getAllAlunosByParams(alunoSearchParams) );
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listAlunos";
	}
	public String editar() {
		UserData.grantAccess(RULE_ALUNOS_ALTERA);
		try {
				pessoaDAO.merge( alunoAlteraParams.getAluno() );
				addActionMessage(MSG_EDITADO_SUCESSO);
			
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista();
	}
	
	public String gradeParaAdmin() {
		try {
			setDisciplinasLetivas(disciplinaDAO.getDisciplinaLetivaByUser(id));
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "grade";
	}	
	
	public String gradeParaAluno() {
		List<Horario> horarioSegunda = new ArrayList<Horario>();
		List<Horario> horarioTerca = new ArrayList<Horario>();
		List<Horario> horarioQuarta = new ArrayList<Horario>();
		List<Horario> horarioQuinta = new ArrayList<Horario>();
		List<Horario> horarioSexta = new ArrayList<Horario>();
		List<Horario> horarioSabado = new ArrayList<Horario>();
		List<Horario> horarioDomingo = new ArrayList<Horario>();
		try {
			setDisciplinasLetivas(disciplinaDAO.getDisciplinaLetivaByUser(UserData.getLoggedUser().getId()));
			for(DisciplinaLetiva letiva :disciplinasLetivas){
				for( Horario horario : letiva.getHorarios()){
					DisciplinaLetivaHorario disciplinaLetivaHorario = horarioDAO.getDisciplinaLetivaHorarioByIds(horario.getId(),letiva.getId());
					if(disciplinaLetivaHorario.getDiaSemana().equals("SEGUNDA")){
						
					}
					if(disciplinaLetivaHorario.getDiaSemana().equals("TERCA")){
						
					}
					if(disciplinaLetivaHorario.getDiaSemana().equals("QUARTA")){
						
					}
					if(disciplinaLetivaHorario.getDiaSemana().equals("QUINTA")){
						
					}
					if(disciplinaLetivaHorario.getDiaSemana().equals("SEXTA")){
						
					}
					if(disciplinaLetivaHorario.getDiaSemana().equals("SABADO")){
						
					}
					if(disciplinaLetivaHorario.getDiaSemana().equals("DOMINGO")){
						
					}
				}
			}
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


	public AlunoAlteraParams getAlunoAlteraParams() {
		return alunoAlteraParams;
	}

	public void setAlunoAlteraParams( AlunoAlteraParams alunoAlteraParams ) {
		this.alunoAlteraParams = alunoAlteraParams;
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	public void setPessoaDAO( PessoaDAO pessoaDAO ) {
		this.pessoaDAO = pessoaDAO;
	}

	public CursoDAO getCursoDAO() {
		return cursoDAO;
	}

	public void setCursoDAO( CursoDAO cursoDAO ) {
		this.cursoDAO = cursoDAO;
	}

	public void setAlunoSearchParams( AlunoSearchParams alunoSearchParams ) {
		this.alunoSearchParams = alunoSearchParams;
	}

	public AlunoSearchParams getAlunoSearchParams() {
		return alunoSearchParams;
	}

	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}

	public DisciplinaDAO getDisciplinaDAO() {
		return disciplinaDAO;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setDisciplinasLetivas(List<DisciplinaLetiva> disciplinasLetivas) {
		this.disciplinasLetivas = disciplinasLetivas;
	}

	public List<DisciplinaLetiva> getDisciplinasLetivas() {
		return disciplinasLetivas;
	}



	public void setSenha2( String senha2 ) {
		this.senha2 = senha2;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setEmail2( String email2 ) {
		this.email2 = email2;
	}

	public String getEmail2() {
		return email2;
	}

	public List<StatusMatriculaEnum> getAllStatusMatricula() {
		return allStatusMatricula;
	}

	public void setAllStatusMatricula(List<StatusMatriculaEnum> allStatusMatricula) {
		this.allStatusMatricula = allStatusMatricula;
	}




	
}
