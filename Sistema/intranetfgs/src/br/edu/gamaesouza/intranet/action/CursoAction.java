package br.edu.gamaesouza.intranet.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;


import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.impl.CursoAlteraParams;
import br.edu.gamaesouza.intranet.params.impl.CursoDeletaParams;
import br.edu.gamaesouza.intranet.params.impl.CursoNovoParams;
import br.edu.gamaesouza.intranet.params.impl.CursoSearchParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;

import com.opensymphony.xwork2.ActionSupport;

@Log
public class CursoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG_CURSO_NOVO_FAILURE = "Ocorreu um erro interno no servidor. Não foi possível cadastrar o curso.";
	private static final String MSG_CURSO_DELETA_SUCESSO = "Curso deletado com sucesso!";
	private static final String MSG_CURSO_DELETA_INSUCESSO = "Ocorreu um erro interno no servidor. Não foi possível deletar o curso.";
	private static final String MSG_CURSO_ALTERA_FAILURE = "Ocorreu um erro interno no servidor. Não foi possível alterar o curso.";

	private static final String RULE_CURSO_NOVO = "RULE_CURSO_NOVO";
	private static final String RULE_CURSO_LISTA = "RULE_CURSO_LISTA";
	private static final String RULE_CURSO_DELETA = "RULE_CURSO_DELETA";
	private static final String RULE_CURSO_ALTERA = "RULE_CURSO_ALTERA";
	
	private static final String RETURN_CURSO_NOVO = "adicionarCurso";
	private static final String RETURN_CURSO_LISTA = "cursos";
	
	@Getter @Setter private List<Curso> cursos = new ArrayList<Curso>();	
	@Getter @Setter private List<Disciplina> allDisciplinas = new ArrayList<Disciplina>();
	@Getter @Setter private List<String> disciplinasParam = new ArrayList<String>();
	@Getter @Setter private String tempoDeResposta;
	
	@Getter @Setter @Autowired private CursoSearchParams cursoSearchParams;
	@Getter @Setter @Autowired private CursoNovoParams cursoNovoParams;
	@Getter @Setter @Autowired private CursoAlteraParams cursoAlteraParams;
	@Getter @Setter @Autowired private CursoDeletaParams cursoDeletaParams;
	@Getter @Setter @Autowired private CursoDAO cursoDAO;
	@Getter @Setter @Autowired private DisciplinaDAO disciplinaDAO;
	@Getter @Setter @Autowired private PessoaDAO pessoaDAO;

	public String execute(){
		try {	
			allDisciplinas = disciplinaDAO.getAllDisciplinas();
		} catch (IntranetException e) {
			log.warning("Erro no método execute na classe CursoAction");
		}
		return RETURN_CURSO_NOVO;
	}
	
	public String novo() throws IntranetException {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		UserData.grantAccess(RULE_CURSO_NOVO);	
			try {
				if(cursoDAO.getCursoByNome(cursoNovoParams.getNomeCurso())!= null){
					addActionError("já existe um curso cadastrado com o mesmo nome em nossa base.");
				}
				else if(cursoNovoParams.getNomeCurso().equals("") || cursoNovoParams.getCargaHorariaComplementar() == null) {
					if(cursoNovoParams.getNomeCurso().equals(""))
					addActionError("Campo Nome é obrigatório.");
										
					if(cursoNovoParams.getCargaHorariaComplementar() == null)
					addActionError("Campo Carga Horária é obrigatório.");
				}else{
				cursoDAO.save(cursoNovoParams.getCurso());
				addActionMessage(" Curso " + cursoNovoParams.getNomeCurso() + " cadastrado com sucesso em "+sdf.format(Calendar.getInstance().getTime()));
				}
			} catch (IntranetException e) {
				log.warning("Erro no método 'Novo' Curso na classe CursoAction");
				addActionError(MSG_CURSO_NOVO_FAILURE);
			}
		return execute();

	}
	
	public String altera() {
		UserData.grantAccess(RULE_CURSO_ALTERA);
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			try {
				if(cursoDAO.getCursoByNome(cursoNovoParams.getNomeCurso())!= null){
					addActionError("já existe um curso cadastrado com o mesmo nome em nossa base.");
				}
				else if(cursoAlteraParams.getNomeCurso().equals("") || cursoAlteraParams.getCargaHorariaComplementar() == null) {
							if(cursoAlteraParams.getNomeCurso().equals(""))
							addActionError("Campo Nome é obrigatório.");
							
							if(cursoAlteraParams.getCargaHorariaComplementar() == null)
							addActionError("Campo Carga Horária é obrigatório.");
				}else{
					cursoDAO.merge(cursoAlteraParams.getCurso());
					addActionMessage(" Curso " + cursoAlteraParams.getNomeCurso() + " alterado com sucesso em "+sdf.format(Calendar.getInstance().getTime()));
				}
			} catch (IntranetException e) {
				log.warning("Erro no método 'altera' Curso na classe CursoAction");
				addActionError(MSG_CURSO_ALTERA_FAILURE);
			}
		return lista();
	}

	public String lista() {
		UserData.grantAccess(RULE_CURSO_LISTA);
			try{
				 long inicio = System.currentTimeMillis();  
					 cursos = cursoDAO.getAllByParams(cursoSearchParams);
					 allDisciplinas = disciplinaDAO.getAllDisciplinas();
				 long  end = System.currentTimeMillis();  
				 setTempoDeResposta(FormUtil.tempoResposta(cursos, inicio, end)); 
				return RETURN_CURSO_LISTA;
			}catch(IntranetException e){	
				log.warning("Erro no método 'Lista' Curso na classe CursoAction");
				return RETURN_CURSO_LISTA;
			}
	}
	
	public String delete(){
		
		UserData.grantAccess(RULE_CURSO_DELETA);
		try {
			Curso curso = cursoDeletaParams.getCurso();
			List<Aluno> alunos = pessoaDAO.getAlunosByCurso(curso);
			if(alunos.isEmpty()){
				cursoDAO.delete(curso);
				addActionMessage(MSG_CURSO_DELETA_SUCESSO);
			}else{
				addActionError("Não foi possível deletar este curso, "+alunos.size()+" aluno(s) esta(ão) cadastrado(s) nele.");
				for(Aluno aluno : alunos) {
					addActionError("Nome: "+aluno.getNome()+
							" - Matrícula: "+aluno.getMatricula()+
							" - Email: "+aluno.getEmail());
				}
			}
			
		} catch (IntranetException e) {
			log.warning("Erro no método 'Delete' Curso na classe CursoAction");
			addActionError(MSG_CURSO_DELETA_INSUCESSO);
		}
	
		return lista();
	}


}
