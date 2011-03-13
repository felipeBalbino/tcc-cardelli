package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.bean.Rule;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.impl.ProfessorNovoParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

import com.opensymphony.xwork2.ActionSupport;

public class ProfessorAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static final String RULE_PROFESSOR_SALVA = "RULE_PROFESSOR_SALVA";
	private static final String RULE_PROFESSOR_LISTA = "RULE_PROFESSOR_LISTA";
	private static final String RULE_PROFESSOR_ALTERA = "RULE_PROFESSOR_ALTERA";
	private static final String RULE_PROFESSOR_DELETE = "RULE_PROFESSOR_DELETE";

	@Autowired private Professor professor;
	
	@Autowired private ProfessorNovoParams professorNovoParams;
	
	private List<Professor> professores = new ArrayList<Professor>();
	private List<Rule> rules = new ArrayList<Rule>();
	private List<Rule> allRules = new ArrayList<Rule>();
	private List<String> rulesParam = new ArrayList<String>();

	@Autowired private PessoaDAO pessoaDAO;

	public String save() {

		UserData.grantAccess(RULE_PROFESSOR_SALVA);
			try {
				pessoaDAO.save(professorNovoParams.getProfessor());
				addActionMessage("Professor adicionado com sucesso.");
				professorNovoParams = (ProfessorNovoParams) SpringUtil.getBean("professorNovoParams");
			} catch (Exception e) {
				addActionError("Ocorreu um erro ao tentar adicionar o Professor.");
			}

			return lista();
		

	}
	
	public String editar() {
		UserData.grantAccess(RULE_PROFESSOR_ALTERA);
		try {
			professor.setRegras(pessoaDAO.getRuleListByStringList(rulesParam));
			pessoaDAO.merge(professor);
			professor = (Professor) SpringUtil.getBean("professor");

			addActionMessage("Professor alterado com sucesso.");
		} catch (Exception e) {
			addActionError("Ocorreu um erro ao tentar alterar o Professor.");
		}
		return lista();
	}
	public String delete() {

		UserData.grantAccess(RULE_PROFESSOR_DELETE);
			try {
				
				Professor professor = pessoaDAO.getProfessorById( this.professor.getId() );
				pessoaDAO.deleteProfessor(professor);
				
				professor = (Professor) SpringUtil.getBean("professor");
				addActionMessage("Professor deletada com sucesso");
				
			} catch (Exception e) {
				addActionError("Não foi possivel deletar o professor, ocorreu um erro interno no Servidor");
			}
			return lista();
	
	}
	
	
	public String prepare() {
		UserData.grantAccess(RULE_PROFESSOR_SALVA);
		try {
			rules = pessoaDAO.getAllRules();
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "this";
		

	}
	
	public String lista() {
		UserData.grantAccess(RULE_PROFESSOR_LISTA);
		
		try {
			professores = pessoaDAO.getAll();
			allRules = pessoaDAO.getAllRules();
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "modificarProfessor";
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public List<Rule> getAllRules() {
		return allRules;
	}

	public void setAllRules(List<Rule> allRules) {
		this.allRules = allRules;
	}

	public List<String> getRulesParam() {
		return rulesParam;
	}

	public void setRulesParam(List<String> paramRules) {
		this.rulesParam = paramRules;
	}

	public ProfessorNovoParams getProfessorNovoParams() {
		return professorNovoParams;
	}

	public void setProfessorNovoParams(ProfessorNovoParams professorNovoParams) {
		this.professorNovoParams = professorNovoParams;
	}
	
	

}
