package br.edu.gamaesouza.intranet.action;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Atividade;
import br.edu.gamaesouza.intranet.bean.HoraAEP;
import br.edu.gamaesouza.intranet.bean.HoraComplementar;
import br.edu.gamaesouza.intranet.bean.result.HorasAtividadeResultBean;
import br.edu.gamaesouza.intranet.bean.result.HorasCursoResultBean;
import br.edu.gamaesouza.intranet.dao.HoraDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.impl.AlunoSearchParams;
import br.edu.gamaesouza.intranet.params.impl.HoraAEPAlteraParams;
import br.edu.gamaesouza.intranet.params.impl.HoraAEPDeletaParams;
import br.edu.gamaesouza.intranet.params.impl.HoraAEPListaParams;
import br.edu.gamaesouza.intranet.params.impl.HoraAEPNovoParams;
import br.edu.gamaesouza.intranet.params.impl.HoraComplementarAlteraParams;
import br.edu.gamaesouza.intranet.params.impl.HoraComplementarDeletaParams;
import br.edu.gamaesouza.intranet.params.impl.HoraComplementarListaParams;
import br.edu.gamaesouza.intranet.params.impl.HoraComplementarNovoParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.DateUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;

import com.opensymphony.xwork2.ActionSupport;

public class HoraAction extends ActionSupport {


	private static final long serialVersionUID = 1L;
	
	// Params AEP
	@Autowired private HoraAEPNovoParams   horaAEPNovoParams;
	@Autowired private HoraAEPAlteraParams horaAEPAlteraParams;
	@Autowired private HoraAEPDeletaParams horaAEPDeletaParams;
	@Autowired private HoraAEPListaParams horaAEPListaParams;
	
	// Params Complementar
	@Autowired private HoraComplementarNovoParams   horaComplementarNovoParams;
	@Autowired private HoraComplementarAlteraParams horaComplementarAlteraParams;
	@Autowired private HoraComplementarDeletaParams horaComplementarDeletaParams;
	@Autowired private HoraComplementarListaParams horaComplementarListaParams;
	
	private AlunoSearchParams alunoSearchParams = new AlunoSearchParams();
	
	@Autowired private HoraDAO horaDAO;
	@Autowired private PessoaDAO pessoaDAO;
	
	// Aluno retornado pela matr�cula
	private Aluno aluno;

	// Carrega Lista mostrada ao Usuário
	private List<HoraAEP> horasAEP;
	private List<HoraComplementar> horasComplementares;
	private List<Atividade> atividades;
	private List<HorasAtividadeResultBean> horasAtividadeResultBean;
	private HorasCursoResultBean horasCursoResultBean;
	
	private final String RETURN_LIST_AEP_SUCCESS = "listAEPSuccess";
	private final String RETURN_LIST_AEP_FAILURE = "listAEPFailure";
	private final String RETURN_ALTER_AEP_SUCCESS = "alterAEPSuccess";
	private final String RETURN_ALTER_AEP_FAILURE = "alterAEPFailure";
	private final String RETURN_DELETE_AEP_SUCCESS = "deleteAEPSuccess";
	private final String RETURN_DELETE_AEP_FAILURE = "deleteAEPFailure";
	private final String RETURN_SAVE_AEP_SUCCESS = "saveAEPSuccess";
	private final String RETURN_SAVE_AEP_FAILURE = "saveAEPFailure";
	
	private final String RULE_LISTA_AEP = "RULE_LISTA_AEP";
	private final String RULE_DELETA_AEP = "RULE_DELETA_AEP";
	private final String RULE_ALTERA_AEP = "RULE_ALTERA_AEP";
	private final String RULE_SALVA_AEP = "RULE_SALVA_AEP";
	
	
	private final String RETURN_LIST_COMPLEMENTAR_SUCCESS = "listComplementarSuccess";
	private final String RETURN_LIST_COMPLEMENTAR_FAILURE = "listComplementarFailure";
	private final String RETURN_ALTER_COMPLEMENTAR_SUCCESS = "alterAEPSuccess";
	private final String RETURN_ALTER_COMPLEMENTAR_FAILURE = "alterAEPFailure";
	private final String RETURN_DELETE_COMPLEMENTAR_SUCCESS = "deleteAEPSuccess";
	private final String RETURN_DELETE_COMPLEMENTAR_FAILURE = "deleteAEPFailure";
	private final String RETURN_SAVE_COMPLEMENTAR_SUCCESS = "saveAEPSuccess";
	private final String RETURN_SAVE_COMPLEMENTAR_FAILURE = "saveAEPFailure";
	
	private final String RULE_LISTA_COMPLEMENTAR = "RULE_LISTA_COMPLEMENTAR";
	private final String RULE_DELETA_COMPLEMENTAR = "RULE_DELETA_COMPLEMENTAR";
	private final String RULE_ALTERA_COMPLEMENTAR = "RULE_ALTERA_COMPLEMENTAR";
	private final String RULE_SALVA_COMPLEMENTAR = "RULE_SALVA_COMPLEMENTAR";
	

	
	// Lista todas as horas AEP do aluno;
	public String listaAEP(){
		UserData.grantAccess(RULE_LISTA_AEP);	
		try {
			this.aluno = (Aluno) pessoaDAO.getPessoaById(horaAEPListaParams.getId());	
			horasAEP = DateUtil.getDiferencaDatasListAEP(horaDAO.getHorasAEP(aluno));
			alunoSearchParams = new AlunoSearchParams();
			return RETURN_LIST_AEP_SUCCESS;
		} catch (IntranetException e) {
			return RETURN_LIST_AEP_FAILURE;			
		}		
		
	}
	
	// Lista todas as horas Complementares do aluno;
	public String listaComplementar(){
		UserData.grantAccess(RULE_LISTA_COMPLEMENTAR);	
		try {
			Aluno aluno = (Aluno)pessoaDAO.getPessoaById(horaComplementarListaParams.getId());
			horasComplementares = horaDAO.getHorasComplementares(aluno);
			horasAtividadeResultBean = horaDAO.getHorasGroupByAtividade(horaComplementarListaParams.getId());
			horasCursoResultBean = horaDAO.getHorasCursoAndAluno(horaComplementarListaParams.getId()).get(0);
			return RETURN_LIST_COMPLEMENTAR_SUCCESS;
		} catch (IntranetException e) {
			return RETURN_LIST_COMPLEMENTAR_FAILURE;	
		}
	}
	
	// Preapara para ir para página de cadastrar nova hora complementar
	// Carrega as Atividades
	
	public String prepareCadastroAEP() throws IntranetException{
		aluno = pessoaDAO.getAlunoById(aluno.getId());
		return "saveAEPSuccess";
	}
	
	public String novaHoraComplementar(){
		UserData.grantAccess(RULE_SALVA_COMPLEMENTAR);	
		return null;
	}
	
	public String salvaAEP() throws IntranetException{
		UserData.grantAccess(RULE_SALVA_AEP);	
		
		try {
			horaDAO.save(horaAEPNovoParams.getHoraAEP());
			this.aluno = pessoaDAO.getAlunoById(horaAEPNovoParams.getAluno().getId());
			addActionMessage("Hora AEP adicionada com sucesso.");
		} catch (ParseException e) {
			addActionMessage("Ocorreu um erro ao adicionar uma hora AEP.");
			throw new IntranetException(e.getMessage());
		}
		
		return "saveAEPSuccess";
	}
	
	public String salvaComplementar(){
		UserData.grantAccess(RULE_SALVA_COMPLEMENTAR);	
		return null;
	}
	
	public String alteraAEP(){
		UserData.grantAccess(RULE_ALTERA_AEP);	
		return null;
	}
	
	public String alteraComplementar(){
		UserData.grantAccess(RULE_ALTERA_COMPLEMENTAR);	
		return null;
	}

	// Gets and Sets
	
	public HoraAEPNovoParams getHoraAEPNovoParams() {
		return horaAEPNovoParams;
	}

	public void setHoraAEPNovoParams(HoraAEPNovoParams horaAEPNovoParams) {
		this.horaAEPNovoParams = horaAEPNovoParams;
	}

	public HoraAEPAlteraParams getHoraAEPAlteraParams() {
		return horaAEPAlteraParams;
	}

	public void setHoraAEPAlteraParams(HoraAEPAlteraParams horaAEPAlteraParams) {
		this.horaAEPAlteraParams = horaAEPAlteraParams;
	}

	public HoraAEPDeletaParams getHoraAEPDeletaParams() {
		return horaAEPDeletaParams;
	}

	public void setHoraAEPDeletaParams(HoraAEPDeletaParams horaAEPDeletaParams) {
		this.horaAEPDeletaParams = horaAEPDeletaParams;
	}

	public HoraComplementarNovoParams getHoraComplementarNovoParams() {
		return horaComplementarNovoParams;
	}

	public void setHoraComplementarNovoParams(
			HoraComplementarNovoParams horaComplementarNovoParams) {
		this.horaComplementarNovoParams = horaComplementarNovoParams;
	}

	public HoraComplementarAlteraParams getHoraComplementarAlteraParams() {
		return horaComplementarAlteraParams;
	}

	public void setHoraComplementarAlteraParams(
			HoraComplementarAlteraParams horaComplementarAlteraParams) {
		this.horaComplementarAlteraParams = horaComplementarAlteraParams;
	}

	public HoraComplementarDeletaParams getHoraComplementarDeletaParams() {
		return horaComplementarDeletaParams;
	}

	public void setHoraComplementarDeletaParams(
			HoraComplementarDeletaParams horaComplementarDeletaParams) {
		this.horaComplementarDeletaParams = horaComplementarDeletaParams;
	}

	public List<HoraAEP> getHorasAEP() {
		return horasAEP;
	}

	public void setHorasAEP(List<HoraAEP> horasAEP) {
		this.horasAEP = horasAEP;
	}

	public List<HoraComplementar> getHorasComplementares() {
		return horasComplementares;
	}

	public void setHorasComplementares(List<HoraComplementar> horasComplementares) {
		this.horasComplementares = horasComplementares;
	}		
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public AlunoSearchParams getAlunoSearchParams() {
		return alunoSearchParams;
	}

	public void setAlunoSearchParams(AlunoSearchParams alunoSearchParams) {
		this.alunoSearchParams = alunoSearchParams;
	}

	public HoraAEPListaParams getHoraAEPListaParams() {
		return horaAEPListaParams;
	}

	public void setHoraAEPListaParams(HoraAEPListaParams horaAEPListaParams) {
		this.horaAEPListaParams = horaAEPListaParams;
	}

	public HoraComplementarListaParams getHoraComplementarListaParams() {
		return horaComplementarListaParams;
	}

	public void setHoraComplementarListaParams(
			HoraComplementarListaParams horaComplementarListaParams) {
		this.horaComplementarListaParams = horaComplementarListaParams;
	}

	public List<HorasAtividadeResultBean> getHorasAtividadeResultBean() {
		return horasAtividadeResultBean;
	}

	public void setHorasAtividadeResultBean(
			List<HorasAtividadeResultBean> horasAtividadeResultBean) {
		this.horasAtividadeResultBean = horasAtividadeResultBean;
	}

	public HorasCursoResultBean getHorasCursoResultBean() {
		return horasCursoResultBean;
	}

	public void setHorasCursoResultBean(HorasCursoResultBean horasCursoResultBean) {
		this.horasCursoResultBean = horasCursoResultBean;
	}
	
	
	
	
}
