package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.params.impl.CursoAlteraParams;
import br.edu.gamaesouza.intranet.params.impl.CursoDeletaParams;
import br.edu.gamaesouza.intranet.params.impl.CursoNovoParams;
import br.edu.gamaesouza.intranet.params.impl.CursoSearchParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.IntranetException;

import com.opensymphony.xwork2.ActionSupport;

public class CursoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger("CursoAction");
	
	private static final String MSG_CURSO_NOVO_SUCESSO = "Curso cadastrado com sucesso!";
	private static final String MSG_CURSO_NOVO_FAILURE = "Ocorreu um erro interno no servidor. Não foi possível cadastrar o curso.";
	private static final String MSG_CURSO_DELETA_SUCESSO = "Curso deletado com sucesso!";
	private static final String MSG_CURSO_DELETA_INSUCESSO = "Ocorreu um erro interno no servidor. Não foi possível deletar o curso.";
	
	private static final String MSG_CURSO_ALTERA_SUCESSO = "Curso alterado com sucesso!";
	private static final String MSG_CURSO_ALTERA_FAILURE = "Ocorreu um erro interno no servidor. NÃ£o foi possível alterar o curso.";

	private static final String RULE_CURSO_NOVO = "RULE_CURSO_NOVO";
	private static final String RULE_CURSO_LISTA = "RULE_CURSO_LISTA";
	private static final String RULE_CURSO_DELETA = "RULE_CURSO_DELETA";
	private static final String RULE_CURSO_ALTERA = "RULE_CURSO_ALTERA";

	private static final String RETURN_CURSO_NOVO = "adicionarCurso";
	private static final String RETURN_CURSO_LISTA = "cursos";
	
	private List<Curso> cursos = new ArrayList<Curso>();	
	private List<Disciplina> allDisciplinas = new ArrayList<Disciplina>();
	private List<String> disciplinasParam = new ArrayList<String>();
	
	@Autowired private CursoSearchParams cursoSearchParams;
	@Autowired private CursoNovoParams cursoNovoParams;
	@Autowired private CursoAlteraParams cursoAlteraParams;
	@Autowired private CursoDeletaParams cursoDeletaParams;
	@Autowired private CursoDAO cursoDAO;
	@Autowired private DisciplinaDAO disciplinaDAO;

	
	public String execute(){
		try {
			allDisciplinas = disciplinaDAO.getAll();
		} catch (IntranetException e) {
			// TODO Falta Implementar
		}
		return RETURN_CURSO_NOVO;
	}
	
	public String novo() throws IntranetException {

		UserData.grantAccess(RULE_CURSO_NOVO);	
			try {
				
				cursoDAO.save(cursoNovoParams.getCurso());
				addActionMessage(MSG_CURSO_NOVO_SUCESSO);
			} catch (IntranetException e) {
				addActionMessage(MSG_CURSO_NOVO_FAILURE);
			}
		
		return execute();

	}
	
	public String altera() {
		UserData.grantAccess(RULE_CURSO_ALTERA);
			try {
				cursoDAO.merge(cursoAlteraParams.getCurso());
				addActionMessage(MSG_CURSO_ALTERA_SUCESSO);
			} catch (IntranetException e) {
				addActionMessage(MSG_CURSO_ALTERA_FAILURE);
			}
		
		return lista();
	}

	public String lista() {
		UserData.grantAccess(RULE_CURSO_LISTA);
			try{
				cursos = cursoDAO.getAllByParams(cursoSearchParams);
				allDisciplinas = disciplinaDAO.getAll();
				return RETURN_CURSO_LISTA;
			}catch(IntranetException e){	
				return RETURN_CURSO_LISTA;
			}
	}
	
	public String delete(){
		
		UserData.grantAccess(RULE_CURSO_DELETA);
		try {
			cursoDAO.delete(cursoDeletaParams.getCurso());
			addActionMessage(MSG_CURSO_DELETA_SUCESSO);
		} catch (IntranetException e) {
			addActionMessage(MSG_CURSO_DELETA_INSUCESSO);
		}
	
		return lista();
	}

	// Gets and Sets


	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Disciplina> getAllDisciplinas() {
		return allDisciplinas;
	}

	public void setAllDisciplinas(List<Disciplina> allDisciplinas) {
		this.allDisciplinas = allDisciplinas;
	}

	public List<String> getDisciplinasParam() {
		return disciplinasParam;
	}

	public void setDisciplinasParam(List<String> disciplinasParam) {
		this.disciplinasParam = disciplinasParam;
	}

	public CursoSearchParams getCursoSearchParams() {
		return cursoSearchParams;
	}

	public void setCursoSearchParams(CursoSearchParams cursoSearchParams) {
		this.cursoSearchParams = cursoSearchParams;
	}

	public CursoNovoParams getCursoNovoParams() {
		return cursoNovoParams;
	}

	public void setCursoNovoParams(CursoNovoParams cursoNovoParams) {
		this.cursoNovoParams = cursoNovoParams;
	}

	public CursoAlteraParams getCursoAlteraParams() {
		return cursoAlteraParams;
	}

	public void setCursoAlteraParams(CursoAlteraParams cursoAlteraParams) {
		this.cursoAlteraParams = cursoAlteraParams;
	}

	public CursoDeletaParams getCursoDeletaParams() {
		return cursoDeletaParams;
	}

	public void setCursoDeletaParams(CursoDeletaParams cursoDeletaParams) {
		this.cursoDeletaParams = cursoDeletaParams;
	}
	
	
	

}
