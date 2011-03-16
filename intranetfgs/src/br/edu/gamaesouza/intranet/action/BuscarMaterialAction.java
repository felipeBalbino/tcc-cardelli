package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Arquivo;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.dao.ArquivoDAO;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;

import com.opensymphony.xwork2.ActionSupport;

public class BuscarMaterialAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private static final String LOAD_BUSCAR_MATERIAL_FIELDS = "loadFields"; 
	
	private List<Disciplina> disciplinas    = new ArrayList<Disciplina>();
	private List<Integer>    anos     		= new ArrayList<Integer>();
	private List<Integer>    semestres      = new ArrayList<Integer>();
	private List<Arquivo>    arquivos 		= new ArrayList<Arquivo>();
	private List<Professor>  professores    = new ArrayList<Professor>();
	private List<String>     turnos         = new ArrayList<String>();
	
	private Integer curso;
	private Integer professor;
	private Integer disciplina;
	private Integer ano;
	private Integer semestre;
	private Integer turno;
	
	private Integer idarquivo;
	
	@Autowired private EnviarEmail   enviarEmail;
	@Autowired private ArquivoDAO    arquivoDAO;
	@Autowired private DisciplinaDAO disciplinaDAO;
	@Autowired private PessoaDAO     pessoaDAO;
	

	
	public String carregaFiltrosPesquisa()  {
		loadSearchFilters();
		return LOAD_BUSCAR_MATERIAL_FIELDS;
		
	}
	
	public String carregaResultado() throws Exception {
		
		loadSearchFilters();
		arquivos = arquivoDAO.getArquivos(disciplina, ano, semestre,professor);
		return LOAD_BUSCAR_MATERIAL_FIELDS;
		
	}
	
	private void loadSearchFilters(){
		try {
			disciplinas = disciplinaDAO.getAllDisciplinas();
			professores = pessoaDAO.getAll();
			anos= FormUtil.getAnos();
			semestres = FormUtil.getSemestresList();
			//turnos = FormUtil.getTurnosList();
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public String enviaremail() {

		Arquivo arquivo;
		try {
			arquivo = arquivoDAO.getArquivoById(idarquivo);						
			//enviar email dd arquivo para pessoa logada
		
				try {
					enviarEmail.sendArquivoPeloAluno( arquivo, arquivo.getNome(), UserData.getLoggedUser() );
				} catch ( Throwable e ) {
					addActionError("Não foi possível enviar o email");
				}			
			
				return carregaFiltrosPesquisa();
		} catch (Exception e1) {
			return carregaFiltrosPesquisa();
		}
		
	
}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public List<Integer> getAnos() {
		return anos;
	}

	public void setAnos(List<Integer> anos) {
		this.anos = anos;
	}

	public List<Integer> getSemestres() {
		return semestres;
	}

	public void setSemestres(List<Integer> semestres) {
		this.semestres = semestres;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Integer getProfessor() {
		return professor;
	}

	public void setProfessor(Integer professor) {
		this.professor = professor;
	}

	public Integer getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}


	public void setTurnos( List<String> turnos ) {
		this.turnos = turnos;
	}

	public List<String> getTurnos() {
		return turnos;
	}
	
	public Integer getTurno() {
		return turno;
	}

	public void setTurno( Integer turno ) {
		this.turno = turno;
	}
	
	public Integer getIdarquivo() {
		return idarquivo;
	}

	public void setIdarquivo(Integer idarquivo) {
		this.idarquivo = idarquivo;
	}

}
