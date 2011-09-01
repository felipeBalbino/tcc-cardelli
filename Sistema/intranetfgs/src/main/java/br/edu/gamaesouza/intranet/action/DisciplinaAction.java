package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
import br.edu.gamaesouza.intranet.utils.FormUtil;
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

	
	@Getter @Setter private String tempoDeResposta;
	@Getter @Setter private List<Curso> cursos;
	@Getter @Setter private List<Curso> allCursos;
	@Getter @Setter private List<String> cursosParam = new ArrayList<String>();
	@Getter @Setter private Integer[]   checkBoxSelecionados;
	@Getter @Setter private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	@Getter @Setter @Autowired private Disciplina disciplina;
	@Getter @Setter @Autowired private DisciplinaSearchParams disciplinaSearchParams;
	@Getter @Setter @Autowired private Curso curso;
	@Getter @Setter @Autowired private CursoDAO cursoDAO;
	@Getter @Setter @Autowired private DisciplinaDAO disciplinaDAO;
	@Getter @Setter @Autowired private DisciplinaDeletaParams disciplinaDeletaParams;
	@Getter @Setter @Autowired private DisciplinaAlteraParams disciplinaAlteraParams;
	

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
				long inicio = System.currentTimeMillis();  
				disciplinas = disciplinaDAO.getAllByParams(disciplinaSearchParams);
				allCursos = cursoDAO.getAllCursos();
				long  end = System.currentTimeMillis();  
				setTempoDeResposta(FormUtil.tempoResposta(disciplinas, inicio, end)); 
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
				if (disciplinaDAO.getDisciplinaByNomeReturnString(disciplina.getNome()) == true){
					disciplinaDAO.save(disciplina);
					cursos = cursoDAO.getAllCursos();
					disciplina = (Disciplina) SpringUtil.getBean("disciplina");
					checkBoxSelecionados = new Integer[]{};
					addActionMessage("Disciplina Adicionada com Sucesso!");
				}else{
					addActionError("Nome da disciplina já se encontra na base, tente outro nome para esta disciplina!");
				}
				
			}catch(IntranetException e){
				addActionError("Erro ao adicionar a disciplina!");
			}
		
			return novo();

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
					addActionError("Não foi possivel deletar esta disciplina, existe(m) "+disciplinasLetivas.size()+" Disciplina(s) letiva(s) vinculada(s) a essa disciplina.");	
					for(DisciplinaLetiva letiva : disciplinasLetivas) {
						addActionError("Turno: "+letiva.getTurno()+
								" - Ano: "+letiva.getAno()+
								" - Semestre: "+letiva.getSemestre()+
								" - Disciplina: "+letiva.getDisciplina().getNome()+
								" - Professor: "+letiva.getProfessor().getNome());
					}
				}
			} catch (IntranetException e) {	
				addActionError("Não foi possivel deletar o disciplina, ocorreu um erro interno no Servidor");
			}
			return lista();
	
	}
	
}
