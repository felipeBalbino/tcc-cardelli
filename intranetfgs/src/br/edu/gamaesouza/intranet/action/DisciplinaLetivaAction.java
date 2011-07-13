package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

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

public @Data class DisciplinaLetivaAction extends ActionSupport{

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

	private String tempoDeResposta;
	
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
