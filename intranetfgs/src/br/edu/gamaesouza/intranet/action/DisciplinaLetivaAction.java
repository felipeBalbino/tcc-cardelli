package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

	@Getter @Setter private Integer id;	
	@Getter @Setter private Integer ano;
	@Getter @Setter private String sala;
	@Getter @Setter private String tempoDeResposta;
	
	@Getter @Setter private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	@Getter @Setter private List<Integer> anos  = new ArrayList<Integer>();
	@Getter @Setter private List<Integer> semestres = new ArrayList<Integer>();
	@Getter @Setter private List<String> turnos = new ArrayList<String>();
	@Getter @Setter private List<DisciplinaLetiva> disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
	@Getter @Setter private List<Professor> professores = new ArrayList<Professor>();
	
	@Getter @Setter @Autowired private Horario horario;
	@Getter @Setter @Autowired private Professor professor;
	@Getter @Setter @Autowired private DisciplinaLetiva disciplinaLetiva;
	@Getter @Setter @Autowired private PessoaDAO pessoaDao;
	@Getter @Setter @Autowired private DisciplinaLetivaNovoParams disciplinaLetivaNovoParams;
	@Getter @Setter @Autowired private DisciplinaLetivaSearchParams disciplinaLetivaSearchParams;
	@Getter @Setter @Autowired private Disciplina disciplina;
	@Getter @Setter @Autowired private DisciplinaDAO disciplinaDAO;

	
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
			addActionMessage(e.getMessage());
		}
		
		return "nova";
	
	}

	
	public String lista()  {
		UserData.grantAccess(RULE_DISCIPLINA_LETIVA_LISTA);
		try {
			long inicio = System.currentTimeMillis();  
			setAnos(FormUtil.getAnosList(3));
			setSemestres(FormUtil.getSemestresList());
			setTurnos(FormUtil.getTurnosList());
			disciplinas = disciplinaDAO.getAllDisciplinas();
			professores = pessoaDao.getAll();
			disciplinasLetivas = disciplinaDAO.getAllByParamsDisciplinaLetiva(disciplinaLetivaSearchParams);
			long  end = System.currentTimeMillis();  
			setTempoDeResposta(FormUtil.tempoResposta(disciplinasLetivas, inicio, end)); 
			
		} catch (IntranetException e) {
			addActionMessage(e.getMessage());
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
			addActionMessage(e.getMessage());
		}
		return "listaPresenca";
	}
	

	
}
