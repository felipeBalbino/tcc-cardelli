package br.edu.gamaesouza.intranet.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				addActionError("Matrï¿½cula jï¿½ existente em nossa base.");
				Pessoa pessoa = pessoaDAO.getPessoaByMatricula( alunoNovoParams.getMatricula() );
				addActionError("Aluno: "+pessoa.getNome()+ " - " + "Email: "+pessoa.getEmail()+" - " + "Matricula: "+pessoa.getMatricula());					
			}
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepare();	
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
				addActionError("Nï¿½o ï¿½ possï¿½vel efetuar esta operaï¿½ï¿½o, este aluno tem "+disciplinaLetiva.size()+" Disciplina(s) letiva(s) vï¿½nculada(s)");
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
	
	 mapa = new HashMap<Horario,List<DisciplinaLetiva>>();
		List<Horario> horarios;
		
		try {
			setDisciplinasLetivas(disciplinaDAO.getDisciplinaLetivaByUser(UserData.getLoggedUser().getId(),2011,1));
			horarios = horarioDAO.getAllHorarios(2011,1);
			
			for (Horario h : horarios){
				mapa.put(h, new ArrayList<DisciplinaLetiva>());
			}
			
			for (Horario h : horarios){
				List<DisciplinaLetiva> dl = mapa.get(h);
				dl.add(new DisciplinaLetiva());
				dl.add(new DisciplinaLetiva());
				dl.add(new DisciplinaLetiva());
				dl.add(new DisciplinaLetiva());	
				dl.add(new DisciplinaLetiva());	
				for (DisciplinaLetiva d : disciplinasLetivas){
						
					List<DisciplinaLetivaHorario> disciplinaLetivaHorarios = horarioDAO.getDisciplinaLetivaHorarioByIds(h.getId(),d.getId(),2011,1);
					
					if (disciplinaLetivaHorarios != null){
					
						for (DisciplinaLetivaHorario disciplinaLetivaHorario : disciplinaLetivaHorarios){
							if(disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().getDiaSemana().toString().equals("SEGUNDA")){						
								dl.add(0, d);					
							} else if(disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().getDiaSemana().toString().equals("TERÇA")){						
								dl.add(1, d);					
							} else if(disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().getDiaSemana().toString().equals("QUARTA")){						
								dl.add(2, d);					
							} else if(disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().getDiaSemana().toString().equals("QUINTA")){						
								dl.add(3, d);					
							} else if(disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().getDiaSemana().toString().equals("SEXTA")){						
								dl.add(4, d);					
							}
						}
						
					
					}
				}
			}
			
			
		} catch (IntranetException e) {
				
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
					addActionError("Login jï¿½ existente em nossa base.");
				}

				
				if (pessoaDAO.validarEmail(alunoNovoParams.getEmail())){
					error=true;
					addActionError("Email jï¿½ existente em nossa base.");
				}
						
				
				if (alunoNovoParams.getLogin().length() > 8){
					error=true;
					addActionError("Login do Usuï¿½rio precisar tem menos de 8 caracteres.");
				}
						
				
				if(pessoaDAO.validarMatricula(alunoNovoParams.getMatricula())){
					error=true;
					addActionError("Matrï¿½cula jï¿½ existente em nossa base.");
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

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public HorarioDAO getHorarioDAO() {
		return horarioDAO;
	}

	public void setHorarioDAO(HorarioDAO horarioDAO) {
		this.horarioDAO = horarioDAO;
	}

	public void setTempoDeResposta(String tempoDeResposta) {
		this.tempoDeResposta = tempoDeResposta;
	}

	public String getTempoDeResposta() {
		return tempoDeResposta;
	}

	public Map<Horario, List<DisciplinaLetiva>> getMapa() {
		return mapa;
	}

	public void setMapa(Map<Horario, List<DisciplinaLetiva>> mapa) {
		this.mapa = mapa;
	}


	

	
}
