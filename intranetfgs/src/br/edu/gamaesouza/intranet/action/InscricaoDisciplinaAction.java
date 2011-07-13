package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaInscricaoSearchParams;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaSearchParams;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.Pessoa;

import com.opensymphony.xwork2.ActionSupport;

public @Data class InscricaoDisciplinaAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private static final String MSG_JA_INSCRITO = "Voce j� est� inscrito nesta disciplina";
	
	private static final String MSG_MARCA_DISCIPLINA = "Marque pelo menos uma Disciplina.";
	private static final String MSG_DELETA_SUCESSO = "Inscri��o deletada com sucesso.";
	private static final String MSG_NENHUMA_DISCIPLINA = "Voc� n�o tem nenhuma disciplina cadastrada.";
	
	@Autowired private DisciplinaDAO disciplinaDAO;
	@Autowired private PessoaDAO pessoaDAO;	
	@Autowired private DisciplinaLetivaSearchParams disciplinaLetivaSearchParams;
	@Autowired private DisciplinaLetivaInscricaoSearchParams disciplinaLetivaInscricaoSearchParams;


	private List<DisciplinaLetiva> disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
	private List<DisciplinaLetiva> disciplinasLetivasCadastradas = new ArrayList<DisciplinaLetiva>();
	
	private DisciplinaLetiva disciplinaLetivaCadastrada;
	private Integer ano =  Calendar.getInstance().get(Calendar.YEAR);
	private List<Integer> semestres = new ArrayList<Integer>();
	private List<String> turnos = new ArrayList<String>();
	private Pessoa pessoa = new Pessoa();
	
	private Integer idAluno;
	private Integer semestre;
	private Integer disciplinaLetiva;
	private String turno;
	
	
	public String buscarDisciplinas(){
		
		try {
			semestres = FormUtil.getSemestresList();
			turnos = FormUtil.getTurnosList();
			disciplinasLetivasCadastradas = disciplinaDAO.getDisciplinaLetivaByUser( idAluno );
			pessoa = pessoaDAO.getPessoaById( idAluno );
			disciplinasLetivas = disciplinaDAO.getAllByParamsDisciplinaLetivaInscricao(disciplinaLetivaInscricaoSearchParams);
		} catch ( IntranetException e ) {
			addActionMessage(e.getMessage());
		}

		return "SUCCESS";
		
	}
	
	public String salva(){
		
		
		try{
			DisciplinaLetiva letiva = disciplinaDAO.getDisciplinaLetivaById(disciplinaLetiva);
			boolean pessoaAlreadyFollow = disciplinaDAO.setAlunoFollowDisciplinhaLetiva(pessoaDAO.getAlunoById(idAluno), letiva.getDisciplina().getId(), letiva.getAno(), letiva.getSemestre());
		
				if(pessoaAlreadyFollow){
					addActionError(MSG_JA_INSCRITO);
				}
				
				return list();
				
		}catch(IntranetException ex){
			addActionError(MSG_MARCA_DISCIPLINA);
			return list();
		}
		
		
	}
	
	public String delete(){
		try {
			
			DisciplinaLetiva disciplinaLetiva = disciplinaDAO.getDisciplinaLetivaById(disciplinaLetivaCadastrada.getId());
			disciplinaDAO.deleteDisciplinaLetivaOfAluno(disciplinaLetiva,idAluno);
			disciplinasLetivasCadastradas = new ArrayList<DisciplinaLetiva>();
			addActionMessage(MSG_DELETA_SUCESSO);
		} catch (IntranetException e) {
			addActionMessage(e.getMessage());
		}
		
		
		return list();
	}
	
	
	public String list(){
	
		try{
			semestres = FormUtil.getSemestresList();
			turnos = FormUtil.getTurnosList();
			disciplinasLetivasCadastradas = disciplinaDAO.getDisciplinaLetivaByUser( idAluno );
			disciplinasLetivas = disciplinaDAO.getAllDisciplinaLetivas();
			pessoa = pessoaDAO.getPessoaById( idAluno );
			return "SUCCESS";
		}catch(IntranetException e){
			addActionError(MSG_NENHUMA_DISCIPLINA);
			return "SUCCESS";
		}
		
	}

}
