package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.Horario;
import br.edu.gamaesouza.intranet.bean.Professor;

import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaNovoParams;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaSearchParams;

import br.edu.gamaesouza.intranet.security.UserData;

import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

import com.opensymphony.xwork2.ActionSupport;

public class DisciplinaLetivaAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private static final String RULE_DISCIPLINA_LETIVA_SAVE = "RULE_DISCIPLINA_LETIVA_SAVE";
	private static final String RULE_DISCIPLINA_LETIVA_LISTA = "RULE_DISCIPLINA_LETIVA_LISTA";
	private static final String RULE_DISCIPLINA_LETIVA_ALTERA = "RULE_DISCIPLINA_LETIVA_ALTERA";
	private static final String RULE_DISCIPLINA_LETIVA_DELETE = "RULE_DISCIPLINA_LETIVA_DELETE";

	private Integer id;	
	private Integer ano;
	private String sala;
	
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private List<Integer> anos  = new ArrayList<Integer>();
	private List<Integer> semestres = new ArrayList<Integer>();
	private List<String> turnos = new ArrayList<String>();
	private List<DisciplinaLetiva> disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
	private List<Professor> professores = new ArrayList<Professor>();
	
	
	@Autowired private Horario horario;
	@Autowired private Professor professor;
	@Autowired private DisciplinaLetiva disciplinaLetiva;
	@Autowired private PessoaDAO pessoaDao;
	@Autowired private DisciplinaLetivaNovoParams disciplinaLetivaNovoParams;
	@Autowired private DisciplinaLetivaSearchParams disciplinaLetivaSearchParams;
	@Autowired private Disciplina disciplina;
	@Autowired private DisciplinaDAO disciplinaDAO;

	public String prepare()  {
		UserData.grantAccess(RULE_DISCIPLINA_LETIVA_LISTA);
		try {
			setAno(FormUtil.getAnoAtual());
			setAnos(FormUtil.getAnosList(3));
			setSemestres(FormUtil.getSemestresList());
			setTurnos(FormUtil.getTurnosList());
			disciplinas = disciplinaDAO.getAllDisciplinas();
			professores = pessoaDao.getAll();
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "nova";
	
	}

	
	public String lista()  {
		UserData.grantAccess(RULE_DISCIPLINA_LETIVA_LISTA);
		try {
			setAnos(FormUtil.getAnosList(3));
			setSemestres(FormUtil.getSemestresList());
			setTurnos(FormUtil.getTurnosList());
			disciplinas = disciplinaDAO.getAllDisciplinas();
			professores = pessoaDao.getAll();
			disciplinasLetivas = disciplinaDAO.getAllByParamsDisciplinaLetiva(disciplinaLetivaSearchParams);
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "letiva";
	
	}	
	

	
	public String save() throws Exception{
		UserData.grantAccess(RULE_DISCIPLINA_LETIVA_SAVE);
		try {
			disciplinaLetiva = disciplinaDAO.saveOrReturnDisciplinaLetiva(disciplinaLetivaNovoParams.getDisciplinaLetiva());
			addActionMessage("Disciplina Letiva criada com sucesso");
		} catch (IntranetException e1) {
			addActionError("N�o foi possivel adicionar disciplina letiva, ocorreu um erro interno no Servidor");
		}
		
		return lista();
	}
	
	public String alterar() throws Exception{
		UserData.grantAccess(RULE_DISCIPLINA_LETIVA_ALTERA);
		try {
			disciplinaLetiva = disciplinaDAO.getDisciplinaLetivaById(id);
			disciplinaLetiva.setSala(sala);
			disciplinaLetiva.setProfessor(pessoaDao.getProfessorById(professor.getId()));
			disciplinaDAO.updateDisciplinaLetiva(disciplinaLetiva);	
			
			disciplinaLetiva = (DisciplinaLetiva) SpringUtil.getBean("disciplinaLetiva");
			
			addActionMessage("Disciplina Letiva alterada com sucesso");
		} catch (IntranetException e1) {
			addActionError("N�o foi possivel alterar disciplina letiva, ocorreu um erro interno no Servidor");
		}	
		return lista();
	}
	

	public String delete() {
		UserData.grantAccess(RULE_DISCIPLINA_LETIVA_DELETE);
			try {			
				DisciplinaLetiva disciplinaLetiva = disciplinaDAO.getDisciplinaLetivaById( this.disciplinaLetiva.getId() );
				disciplinaDAO.deleteDisciplinaLetiva(disciplinaLetiva);	
				disciplinaLetiva = (DisciplinaLetiva) SpringUtil.getBean("disciplinaLetiva");
				addActionMessage("Disciplina Letiva deletada com sucesso");			
			} catch (Exception e) {		
				addActionError("N�o foi possivel deletar disciplina letiva, ocorreu um erro interno no Servidor");			
			}
			return lista();
	}
	
	public String listaPresenca() {
		try {
			disciplinaLetiva = disciplinaDAO.getDisciplinaLetivaById(id);
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listaPresenca";
	}
	

	public DisciplinaLetiva getDisciplinaLetiva() {
		return disciplinaLetiva;
	}


	public void setDisciplinaLetiva(DisciplinaLetiva disciplinaLetiva) {
		this.disciplinaLetiva = disciplinaLetiva;
	}


	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}


	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}


	public List<Integer> getSemestres() {
		return semestres;
	}


	public void setSemestres(List<Integer> semestres) {
		this.semestres = semestres;
	}


	public List<String> getTurnos() {
		return turnos;
	}


	public void setTurnos(List<String> turnos) {
		this.turnos = turnos;
	}


	public List<DisciplinaLetiva> getDisciplinasLetivas() {
		return disciplinasLetivas;
	}


	public void setDisciplinasLetivas(List<DisciplinaLetiva> disciplinasLetivas) {
		this.disciplinasLetivas = disciplinasLetivas;
	}


	public DisciplinaLetivaSearchParams getDisciplinaLetivaSearchParams() {
		return disciplinaLetivaSearchParams;
	}


	public void setDisciplinaLetivaSearchParams(
			DisciplinaLetivaSearchParams disciplinaLetivaSearchParams) {
		this.disciplinaLetivaSearchParams = disciplinaLetivaSearchParams;
	}


	public Disciplina getDisciplina() {
		return disciplina;
	}


	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}


	public void setAnos(List<Integer> anos) {
		this.anos = anos;
	}


	public List<Integer> getAnos() {
		return anos;
	}
	
	public DisciplinaDAO getDisciplinaDAO() {
		return disciplinaDAO;
	}


	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}


	public List<Professor> getProfessores() {
		return professores;
	}


	public void setPessoaDao(PessoaDAO pessoaDao) {
		this.pessoaDao = pessoaDao;
	}


	public PessoaDAO getPessoaDao() {
		return pessoaDao;
	}
	


	public Professor getProfessor() {
		return professor;
	}


	public void setProfessor( Professor professor ) {
		this.professor = professor;
	}


	public void setDisciplinaLetivaNovoParams(DisciplinaLetivaNovoParams disciplinaLetivaNovoParams) {
		this.disciplinaLetivaNovoParams = disciplinaLetivaNovoParams;
	}


	public DisciplinaLetivaNovoParams getDisciplinaLetivaNovoParams() {
		return disciplinaLetivaNovoParams;
	}


	public void setAno(Integer ano) {
		this.ano = ano;
	}


	public Integer getAno() {
		return ano;
	}


	public void setHorario(Horario horario) {
		this.horario = horario;
	}


	public Horario getHorario() {
		return horario;
	}


	public String getSala() {
		return sala;
	}


	public void setSala(String sala) {
		this.sala = sala;
	}



	

	
	
}
