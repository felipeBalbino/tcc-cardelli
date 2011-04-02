package br.edu.gamaesouza.intranet.action;

import java.text.ParseException;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Atividade;
import br.edu.gamaesouza.intranet.bean.HoraAEP;
import br.edu.gamaesouza.intranet.bean.HoraComplementar;
import br.edu.gamaesouza.intranet.bean.result.HorasAtividadeResultBean;
import br.edu.gamaesouza.intranet.bean.result.HorasCursoResultBean;
import br.edu.gamaesouza.intranet.dao.HoraDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.params.impl.AlunoSearchParams;
import br.edu.gamaesouza.intranet.params.impl.GeraComprovanteHoraComplementarParams;
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
import br.edu.gamaesouza.intranet.utils.SpringUtil;

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
	
	@Autowired private GeraComprovanteHoraComplementarParams geraComprovanteHoraComplementarParams;
	
	private AlunoSearchParams alunoSearchParams = new AlunoSearchParams();
	
	@Autowired private HoraDAO horaDAO;
	@Autowired private PessoaDAO pessoaDAO;
	
	// Aluno retornado pela matr�cula
	@Autowired private Aluno aluno;
	
	@Autowired private HoraComplementar horaComplementar;
	
	@Autowired private EnviarEmail enviarEmail;

	// Carrega Lista mostrada ao Usuário
	private List<HoraAEP> horasAEP;
	private List<HoraComplementar> horasComplementares;
	private List<Atividade> atividades;
	private List<HorasAtividadeResultBean> horasAtividadeResultBean;
	private HorasCursoResultBean horasCursoResultBean;
	
	private String totalHorasAEPAluno;
	
	private final String RETURN_LIST_AEP_SUCCESS = "listAEPSuccess";
	private final String RETURN_LIST_AEP_FAILURE = "listAEPFailure";

	private final String RETURN_DELETE_AEP_SUCCESS = "listAEPSuccess";
	private final String RETURN_SAVE_AEP_SUCCESS = "saveAEPSuccess";

	
	private final String RULE_LISTA_AEP = "RULE_LISTA_AEP";
	private final String RULE_DELETA_AEP = "RULE_DELETA_AEP";
	private final String RULE_SALVA_AEP = "RULE_SALVA_AEP";
	
	
	private final String RETURN_LIST_COMPLEMENTAR_SUCCESS = "listComplementarSuccess";
	private final String RETURN_LIST_COMPLEMENTAR_FAILURE = "listComplementarFailure";
	private final String RETURN_DELETE_COMPLEMENTAR_SUCCESS = "listComplementarSuccess";
	private final String RETURN_SAVE_COMPLEMENTAR_SUCCESS = "saveComplementarSuccess";
	
	private final String RULE_LISTA_COMPLEMENTAR = "RULE_LISTA_COMPLEMENTAR";
	private final String RULE_DELETA_COMPLEMENTAR = "RULE_DELETA_COMPLEMENTAR";
	private final String RULE_SALVA_COMPLEMENTAR = "RULE_SALVA_COMPLEMENTAR";
	

	
	// Lista todas as horas AEP do aluno;
	public String listaAEP(){
		UserData.grantAccess(RULE_LISTA_AEP);	
		try {
			this.aluno = (Aluno) pessoaDAO.getPessoaById(horaAEPListaParams.getId());	
			horasAEP = DateUtil.getDiferencaDatasListAEP(horaDAO.getHorasAEP(aluno));
			totalHorasAEPAluno = DateUtil.getSomaHorasAEP(horasAEP);
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
			this.aluno = (Aluno)pessoaDAO.getPessoaById(horaComplementarListaParams.getId());
			this.atividades = horaDAO.getAtividades();
			horasComplementares = horaDAO.getHorasComplementares(aluno,horaComplementarListaParams.getAtividadeKey());
			horasComplementares = DateUtil.getFormatedFields(horasComplementares);
			horasAtividadeResultBean = horaDAO.getHorasGroupByAtividade(horaComplementarListaParams.getId());
			List<HorasCursoResultBean> resultList = horaDAO.getHorasCursoAndAluno(horaComplementarListaParams.getId());
			if(resultList.size() > 0){
				horasCursoResultBean = resultList.get(0);
			}
			
			return RETURN_LIST_COMPLEMENTAR_SUCCESS;
		} catch (IntranetException e) {
			return RETURN_LIST_COMPLEMENTAR_FAILURE;	
		}
	}
	
	// Preapara para ir para página de cadastrar nova hora complementar
	// Carrega as Atividades
	
	public String prepareCadastroAEP() throws IntranetException{
		aluno = pessoaDAO.getAlunoById(aluno.getId());
		return RETURN_SAVE_AEP_SUCCESS;
	}
	
	public String novaHoraComplementar() throws IntranetException{
		UserData.grantAccess(RULE_SALVA_COMPLEMENTAR);	
		atividades = horaDAO.getAtividades();
		aluno = pessoaDAO.getAlunoById(aluno.getId());
		return RETURN_SAVE_COMPLEMENTAR_SUCCESS;
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
		
		return RETURN_SAVE_AEP_SUCCESS;
	}
	
	public String salvaComplementar() throws IntranetException{
		UserData.grantAccess(RULE_SALVA_COMPLEMENTAR);
			try{
				horaDAO.save(horaComplementarNovoParams.getHora());
				horaComplementar = horaDAO.getHorasComplementares(horaComplementarNovoParams.getHora());
				if(horaComplementar == null){
					addActionError("Hora adicionada com sucesso, n�o foi poss�vel gerar o comprovante.");
				}else{
				this.aluno = pessoaDAO.getAlunoById(horaComplementarNovoParams.getAluno().getId());
				addActionMessage("Hora adicionada com sucesso, utilize o links dispon�vel para gerar um comprovante para o aluno.");
				}
				
			}catch(HibernateException he){
				addActionError("Ocorreu um erro ao tentar salvar a hora.");
				atividades = horaDAO.getAtividades();
				throw new IntranetException(he.getMessage());
			}finally{
				horaComplementarNovoParams = (HoraComplementarNovoParams) SpringUtil.getBean("horaComplementarNovoParams");
			}
		
			return RETURN_SAVE_COMPLEMENTAR_SUCCESS;
	}
	
	public String deletaAEP(){
		UserData.grantAccess(RULE_DELETA_AEP);	
		try {
			horaDAO.deleteAEP(horaAEPDeletaParams.getAlunoId(),horaAEPDeletaParams.getHoraId());
			addActionMessage("Hora deletada com sucesso!");
		} catch (IntranetException e) {
			addActionMessage(e.getMessage());
		}
		return RETURN_DELETE_AEP_SUCCESS;
	}
	
	public String deletaComplementar(){
		UserData.grantAccess(RULE_DELETA_COMPLEMENTAR);	
		try {
			this.horaDAO.deleteComplementar(this.horaComplementarDeletaParams.getAlunoId(),this.horaComplementarDeletaParams.getHoraId());
			this.aluno = this.pessoaDAO.getAlunoById(Integer.parseInt(horaComplementarDeletaParams.getAlunoId()));
			this.atividades = this.horaDAO.getAtividades();
			this.horasComplementares = this.horaDAO.getHorasComplementares(this.aluno,null);
			this.horasComplementares = DateUtil.getFormatedFields(this.horasComplementares);
			horasAtividadeResultBean = this.horaDAO.getHorasGroupByAtividade(Integer.parseInt(horaComplementarDeletaParams.getAlunoId()));
			List<HorasCursoResultBean> resultList = this.horaDAO.getHorasCursoAndAluno(Integer.parseInt(horaComplementarDeletaParams.getAlunoId()));
			if(resultList.size() > 0){
				horasCursoResultBean = resultList.get(0);
			}	
			
			addActionMessage("Hora deletada com sucesso!");
		} catch (IntranetException e) {
			addActionMessage(e.getMessage());
		}
		return RETURN_DELETE_COMPLEMENTAR_SUCCESS;
	}
	
	public String gerarComprovanteHoraComplementar() throws IntranetException{
		this.aluno = geraComprovanteHoraComplementarParams.getAluno();
		this.horaComplementar = geraComprovanteHoraComplementarParams.getHoraComplementar();
		this.horaComplementar.setTotalHoras(DateUtil.getHourMinutesFormated(horaComplementar.getMinutos()));
		return "geraComprovante";	
	}
	
	public String enviarComprovanteEmail() throws IntranetException{
		this.aluno = geraComprovanteHoraComplementarParams.getAluno();
		this.horaComplementar = geraComprovanteHoraComplementarParams.getHoraComplementar();
		try{
			enviarEmail.sendComprovanteToAluno(aluno,horaComplementar);
			addActionMessage("Comprovante enviado com sucesso.");
		}catch(IntranetException e){
			addActionError("Ocorreu um erro ao tentar enviar o comprovante pro e-mail do aluno.");
		}
		return "geraComprovante";	
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

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public HoraComplementar getHoraComplementar() {
		return horaComplementar;
	}

	public void setHoraComplementar(HoraComplementar horaComplementar) {
		this.horaComplementar = horaComplementar;
	}

	public GeraComprovanteHoraComplementarParams getGeraComprovanteHoraComplementarParams() {
		return geraComprovanteHoraComplementarParams;
	}

	public void setGeraComprovanteHoraComplementarParams(
			GeraComprovanteHoraComplementarParams geraComprovanteHoraComplementarParams) {
		this.geraComprovanteHoraComplementarParams = geraComprovanteHoraComplementarParams;
	}

	public String getTotalHorasAEPAluno() {
		return totalHorasAEPAluno;
	}

	public void setTotalHorasAEPAluno(String totalHorasAEPAluno) {
		this.totalHorasAEPAluno = totalHorasAEPAluno;
	}
	
	
	
	
}
