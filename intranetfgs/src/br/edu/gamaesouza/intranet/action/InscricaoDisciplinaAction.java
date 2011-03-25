package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

public class InscricaoDisciplinaAction extends ActionSupport{
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			addActionMessage(MSG_DELETA_SUCESSO);
		} catch (IntranetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public List<DisciplinaLetiva> getDisciplinasLetivas() {
		return disciplinasLetivas;
	}

	public void setDisciplinasLetivas(List<DisciplinaLetiva> disciplinasLetivas) {
		this.disciplinasLetivas = disciplinasLetivas;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<Integer> getSemestres() {
		return semestres;
	}

	public void setSemestres(List<Integer> semestres) {
		this.semestres = semestres;
	}
	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}



	public List<DisciplinaLetiva> getDisciplinasLetivasCadastradas() {
		return disciplinasLetivasCadastradas;
	}

	public void setDisciplinasLetivasCadastradas(
			List<DisciplinaLetiva> disciplinasLetivasCadastradas) {
		this.disciplinasLetivasCadastradas = disciplinasLetivasCadastradas;
	}
	
	public DisciplinaLetiva getDisciplinaLetivaCadastrada() {
		return disciplinaLetivaCadastrada;
	}

	public void setDisciplinaLetivaCadastrada(
			DisciplinaLetiva disciplinaLetivaCadastrada) {
		this.disciplinaLetivaCadastrada = disciplinaLetivaCadastrada;
	}

	public void setTurno( String turno ) {
		this.turno = turno;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurnos( List<String> turnos ) {
		this.turnos = turnos;
	}

	public List<String> getTurnos() {
		return turnos;
	}


	public void setPessoa( Pessoa pessoa ) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoaDAO( PessoaDAO pessoaDAO ) {
		this.pessoaDAO = pessoaDAO;
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}
	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno( Integer idAluno ) {
		this.idAluno = idAluno;
	}


	public void setDisciplinaLetivaSearchParams(
			DisciplinaLetivaSearchParams disciplinaLetivaSearchParams) {
		this.disciplinaLetivaSearchParams = disciplinaLetivaSearchParams;
	}

	public DisciplinaLetivaSearchParams getDisciplinaLetivaSearchParams() {
		return disciplinaLetivaSearchParams;
	}
	

	
	public DisciplinaLetivaInscricaoSearchParams getDisciplinaLetivaInscricaoSearchParams() {
		return disciplinaLetivaInscricaoSearchParams;
	}

	public void setDisciplinaLetivaInscricaoSearchParams(
			DisciplinaLetivaInscricaoSearchParams disciplinaLetivaInscricaoSearchParams) {
		this.disciplinaLetivaInscricaoSearchParams = disciplinaLetivaInscricaoSearchParams;
	}

	public void setDisciplinaLetiva(Integer disciplinaLetiva) {
		this.disciplinaLetiva = disciplinaLetiva;
	}

	public Integer getDisciplinaLetiva() {
		return disciplinaLetiva;
	}
	
}
