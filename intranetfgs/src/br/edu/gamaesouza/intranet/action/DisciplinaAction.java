package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
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
	
	public String alterar() throws IntranetException {
		UserData.grantAccess(RULE_DISCIPLINA_ALTERA);
	
			
			disciplina.setCursos(cursoDAO.getCursoListByStringList(cursosParam,disciplina));
			disciplinaDAO.merge(disciplina);
			disciplina = (Disciplina) SpringUtil.getBean("disciplina");

		
		return lista();
		
	}
	
	
	public String lista() {
		
		UserData.grantAccess(RULE_DISCIPLINA_LISTA);
			
			try {
				disciplinas = disciplinaDAO.getAllByParams(disciplinaSearchParams);
				allCursos = cursoDAO.getAll();
			} catch (IntranetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "disciplinas";
		
	}
	
	public String novo() throws Exception {
		UserData.grantAccess(RULE_DISCIPLINA_NOVO);
	
			
			cursos = cursoDAO.getAll();

		

		return "adicionarMateria";
		
	}
	
	public String save() throws Exception{
		
		
		UserData.grantAccess(RULE_DISCIPLINA_SALVA);
			
			
			disciplina.setCursos(cursoDAO.getAllCursosByNome(cursosParam));
		
			try{
				disciplinaDAO.save(disciplina);
				cursos = cursoDAO.getAll();
				disciplina = (Disciplina) SpringUtil.getBean("disciplina");
				checkBoxSelecionados = new Integer[]{};
				
				addActionMessage("Disciplina Adicionada com Sucesso!");
			}catch(Exception e){
				addActionMessage("Erro ao adicionar a disciplina!");
				throw new Exception(e);
			}
		
			return "adicionarMateria";
			
		
		
	}
	
	public String delete() {

		UserData.grantAccess(RULE_DISCIPLINA_DELETE);
		List<DisciplinaLetiva> disciplinasLetivas = null;
			try {
				disciplinasLetivas = disciplinaDAO.getDisciplinaLetivaByIdTheDisciplina( this.disciplina.getId() );
				if(disciplinasLetivas.isEmpty()) {
					Disciplina disciplina = disciplinaDAO.getDisciplinaById( this.disciplina.getId() );
					disciplinaDAO.deleteDisciplina(disciplina);
					disciplina = (Disciplina) SpringUtil.getBean("disciplina");
					addActionMessage("Disciplina deletada com sucesso");
				}else{
					addActionMessage("Não foi possivel deletar esta disciplina, existe(m) "+disciplinasLetivas.size()+" Disciplina(s) letiva(s) vincula(s) a essa disciplina.");	
					for(DisciplinaLetiva letiva : disciplinasLetivas) {
						addActionMessage("Id:"+letiva.getId()+
								"   Turno:"+letiva.getTurno()+
								"   Ano:"+letiva.getAno()+
								"   Semestre:"+letiva.getSemestre()+
								"   Disciplina:"+letiva.getDisciplina().getNome()+
								"   Professor:"+letiva.getProfessor().getNome());
					}
				}
			} catch (Exception e) {
				
				addActionMessage("Não foi possivel deletar o disciplina, ocorreu um erro interno no Servidor");
				
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
	
	
	
}
