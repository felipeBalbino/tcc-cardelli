package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaAlteraParams;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaDeletaParams;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaSearchParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

import com.opensymphony.xwork2.ActionSupport;

public class DisciplinaAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private static final String RULE_DISCIPLINA_LISTA = "RULE_DISCIPLINA_LISTA";
	private static final String RULE_DISCIPLINA_NOVO = "RULE_DISCIPLINA_NOVO";
	private static final String RULE_DISCIPLINA_SALVA = "RULE_DISCIPLINA_SALVA";
	private static final String RULE_DISCIPLINA_ALTERA = "RULE_DISCIPLINA_ALTERA";
	private static final String RULE_DISCIPLINA_DELETE = "RULE_DISCIPLINA_DELETE";

	
	private List<Curso> cursos;
	private List<Curso> allCursos;
	private List<String> cursosParam = new ArrayList<String>();
	private Integer[]   checkBoxSelecionados;
	
	@Autowired private Disciplina disciplina;
	
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@Autowired private DisciplinaSearchParams disciplinaSearchParams;
	
	@Autowired private Curso curso;
	@Autowired private CursoDAO cursoDAO;
	@Autowired private DisciplinaDAO disciplinaDAO;
	@Autowired private DisciplinaDeletaParams disciplinaDeletaParams;
	@Autowired private DisciplinaAlteraParams disciplinaAlteraParams;
	
	public String alterar() {
		UserData.grantAccess(RULE_DISCIPLINA_ALTERA);
				
			try {
				disciplinaDAO.merge(disciplinaAlteraParams.getDisciplina());
				disciplina = (Disciplina) SpringUtil.getBean("disciplina");
				addActionMessage("Disciplina Alterada com Sucesso!");
			} catch (IntranetException e) {
				addActionMessage("Ocorreu um erro ao tentar alterar a disciplina!");
			}
			
		return lista();
		
	}
	
	
	public String lista() {
		
		UserData.grantAccess(RULE_DISCIPLINA_LISTA);
			
			try {
				disciplinas = disciplinaDAO.getAllByParams(disciplinaSearchParams);
				allCursos = cursoDAO.getAllCursos();
			} catch (IntranetException e) {}
			
			return "disciplinas";
		
	}
	
	public String novo() {
		UserData.grantAccess(RULE_DISCIPLINA_NOVO);
		
		try {
			cursos = cursoDAO.getAllCursos();
		} catch (IntranetException e) {}
		
		return "adicionarMateria";
		
	}
	
	public String save() throws Exception{
		UserData.grantAccess(RULE_DISCIPLINA_SALVA);
		disciplina.setCursos(cursoDAO.getAllCursosByNome(cursosParam));
		
			try{
				disciplinaDAO.save(disciplina);
				cursos = cursoDAO.getAllCursos();
				disciplina = (Disciplina) SpringUtil.getBean("disciplina");
				checkBoxSelecionados = new Integer[]{};
				
				addActionMessage("Disciplina Adicionada com Sucesso!");
			}catch(IntranetException e){
				addActionError("Erro ao adicionar a disciplina!");
			}
		
			return "adicionarMateria";

	}
	
	public String delete() {

		UserData.grantAccess(RULE_DISCIPLINA_DELETE);
		List<DisciplinaLetiva> disciplinasLetivas = null;
		Disciplina disciplina = null;
			try {
				disciplina = disciplinaDeletaParams.getDisciplina();
				disciplinasLetivas = disciplinaDAO.getDisciplinaLetivaByDisciplinaId(disciplina.getId());
				if(disciplinasLetivas.isEmpty()) {
					disciplinaDAO.deleteDisciplina(disciplina);
					disciplina = (Disciplina) SpringUtil.getBean("disciplina");
					addActionMessage("Disciplina deletada com sucesso");
				}else{
					addActionError("N�o foi possivel deletar esta disciplina, existe(m) "+disciplinasLetivas.size()+" Disciplina(s) letiva(s) vincula(s) a essa disciplina.");	
					for(DisciplinaLetiva letiva : disciplinasLetivas) {
						addActionError("Turno: "+letiva.getTurno()+
								" - Ano: "+letiva.getAno()+
								" - Semestre: "+letiva.getSemestre()+
								" - Disciplina: "+letiva.getDisciplina().getNome()+
								" - Professor: "+letiva.getProfessor().getNome());
					}
				}
			} catch (IntranetException e) {	
				addActionError("N�o foi possivel deletar o disciplina, ocorreu um erro interno no Servidor");
			}
			return lista();
	
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Disciplina getMateria() {
		return disciplina;
	}

	public void setMateria(Disciplina materia) {
		this.disciplina = materia;
	}

	public Integer[] getCheckBoxSelecionados() {
		return checkBoxSelecionados;
	}

	public void setCheckBoxSelecionados(Integer[] checkBoxSelecionados) {
		this.checkBoxSelecionados = checkBoxSelecionados;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	public List<Curso> getAllCursos() {
		return allCursos;
	}


	public void setAllCursos(List<Curso> allCursos) {
		this.allCursos = allCursos;
	}


	public List<String> getCursosParam() {
		return cursosParam;
	}


	public void setCursosParam(List<String> cursosParam) {
		this.cursosParam = cursosParam;
	}


	public DisciplinaSearchParams getDisciplinaSearchParams() {
		return disciplinaSearchParams;
	}


	public void setDisciplinaSearchParams(
			DisciplinaSearchParams disciplinaSearchParams) {
		this.disciplinaSearchParams = disciplinaSearchParams;
	}


	public DisciplinaDeletaParams getDisciplinaDeletaParams() {
		return disciplinaDeletaParams;
	}


	public void setDisciplinaDeletaParams(
			DisciplinaDeletaParams disciplinaDeletaParams) {
		this.disciplinaDeletaParams = disciplinaDeletaParams;
	}


	public DisciplinaAlteraParams getDisciplinaAlteraParams() {
		return disciplinaAlteraParams;
	}


	public void setDisciplinaAlteraParams(
			DisciplinaAlteraParams disciplinaAlteraParams) {
		this.disciplinaAlteraParams = disciplinaAlteraParams;
	}
	
	
	
	
}
