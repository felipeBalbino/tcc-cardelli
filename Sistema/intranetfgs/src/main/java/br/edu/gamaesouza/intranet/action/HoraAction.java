package br.edu.gamaesouza.intranet.action;

import java.text.ParseException;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
	@Getter @Setter @Autowired private HoraAEPNovoParams   horaAEPNovoParams;
	@Getter @Setter @Autowired private HoraAEPAlteraParams horaAEPAlteraParams;
	@Getter @Setter @Autowired private HoraAEPDeletaParams horaAEPDeletaParams;
	@Getter @Setter @Autowired private HoraAEPListaParams horaAEPListaParams;
	
	// Params Complementar
	@Getter @Setter @Autowired private HoraComplementarNovoParams   horaComplementarNovoParams;
	@Getter @Setter @Autowired private HoraComplementarAlteraParams horaComplementarAlteraParams;
	@Getter @Setter @Autowired private HoraComplementarDeletaParams horaComplementarDeletaParams;
	@Getter @Setter @Autowired private HoraComplementarListaParams horaComplementarListaParams;
	@Getter @Setter @Autowired private GeraComprovanteHoraComplementarParams geraComprovanteHoraComplementarParams;
	@Getter @Setter private AlunoSearchParams alunoSearchParams = new AlunoSearchParams();
	@Getter @Setter @Autowired private HoraDAO horaDAO;
	@Getter @Setter @Autowired private PessoaDAO pessoaDAO;
	
	// Aluno retornado pela matr�cula
	@Getter @Setter @Autowired private Aluno aluno;
	@Getter @Setter @Autowired private HoraComplementar horaComplementar;
	@Getter @Setter @Autowired private EnviarEmail enviarEmail;

	// Carrega Lista mostrada ao Usuário
	@Getter @Setter private List<HoraAEP> horasAEP;
	@Getter @Setter private List<HoraComplementar> horasComplementares;
	@Getter @Setter private List<Atividade> atividades;
	@Getter @Setter private List<HorasAtividadeResultBean> horasAtividadeResultBean;
	@Getter @Setter private HorasCursoResultBean horasCursoResultBean;
	@Getter @Setter private String totalHorasAEPAluno;
	
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

}
