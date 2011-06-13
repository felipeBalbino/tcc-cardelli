package br.edu.gamaesouza.intranet.action;
/**
 * @author Felipe Balbino
 * @since 02/06/2011
 */

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Empresa;
import br.edu.gamaesouza.intranet.bean.Rule;
import br.edu.gamaesouza.intranet.bean.Vaga;
import br.edu.gamaesouza.intranet.dao.EmpresaDAO;
import br.edu.gamaesouza.intranet.dao.VagaDAO;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.AreaEnum;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;


import com.opensymphony.xwork2.ActionSupport;

public class VagasEmpregoAction extends ActionSupport {

	private final Logger logger = Logger.getLogger("br.edu.gamaesouza.intranet.action.VagasEmpregoAction");
	
	private static final long serialVersionUID = 1L;
	
	private static final String RULE_VAGA_NOVO = "RULE_VAGA_NOVO";
	private static final String RULE_VAGA_ALTERA = "RULE_VAGA_ALTERA";
	private static final String RULE_VAGA_DELETA = "RULE_VAGA_DELETA";
	private static final String RULE_VAGA_LISTA = "RULE_VAGA_LISTA";

	/*
	  Parametros vindo da tela para save
	*/
	private Vaga vaga;
	private Long empresaId;
	
	
	private List<AreaEnum> areas;
	private List<Vaga> vagas;
	private List<Empresa> empresas;
	private Long idVaga;
	Boolean seRegra = false;
	
	@Autowired private VagaDAO vagaDAO;
	@Autowired private EmpresaDAO empresaDAO;

	private String tempoDeResposta;
	
	public String lista() {
		this.seRegra = false;
		
		long inicio = System.currentTimeMillis();  
		try {
			empresas = empresaDAO.getAll();
			setAreas(Arrays.asList(AreaEnum.values()));
			
			for (Rule ruleOfPerson : UserData.getLoggedUser().getRegras()){
				if(ruleOfPerson.getNome().equals(RULE_VAGA_LISTA))
					seRegra = true;
			}
			
			if(seRegra){
				vagas = vagaDAO.getAll();
			}else{
				vagas = vagaDAO.getVagasByIdAluno(UserData.getLoggedUser().getId());			
			}
			
		} catch (IntranetException e) {
			addActionError("Erro ao Listar Vagas!");
		}
		long  end = System.currentTimeMillis();  
		setTempoDeResposta(FormUtil.tempoResposta(vagas, inicio, end)); 
		

		if(seRegra){
			return "this";
		}else{
			return "listaVagaParaAluno";		
		}
		
	}
	
	
	public String prepare() {		
		try {
			setEmpresas(empresaDAO.getAll());
			setAreas(Arrays.asList(AreaEnum.values()));
		} catch (IntranetException e) {
			e.printStackTrace();
		}
		return "prepare";
	}
 

	public String confirmar() throws IntranetException{
		try {
			UserData.grantAccess(RULE_VAGA_LISTA);
				Vaga vaga = new Vaga();
				vaga = vagaDAO.getVagaById(idVaga);
				vaga.setConfirmacao(true);
				vaga.setSeAtivo(true);
				vagaDAO.merge(vaga);
		} catch (IntranetException e) {
			logger.warning("Erro Metodo confirmar em VagaEmpregoAction.");
		}		
		return lista();
	}
	
	public String desconfirmar(){
		
		try {
			UserData.grantAccess(RULE_VAGA_LISTA);
			Vaga vaga = new Vaga();
			vaga = vagaDAO.getVagaById(idVaga);
			vaga.setConfirmacao(false);
			vaga.setSeAtivo(false);
			vagaDAO.merge(vaga);
		} catch (IntranetException e) {
			logger.warning("Erro Metodo desconfirmar em VagaEmpregoAction.");
		}		
		return lista();
	}
	
	public String deleta(){
			
		this.seRegra = false;
		try {		
			Vaga vaga = vagaDAO.getVagaById(idVaga);
			vaga = vagaDAO.merge(vaga);
			
			for (Rule ruleOfPerson : UserData.getLoggedUser().getRegras()){
				if(ruleOfPerson.getNome().equals(RULE_VAGA_DELETA))
					seRegra = true;
			}	
			
			if(seRegra){
				vagaDAO.delete(vaga);
			}else{
				if(vaga.getSeAtivo() == false){
					vagaDAO.delete(vaga);
				}else{
					addActionError("Não é possivel deletar uma vaga ativa!");
				}				
			}
			
		} catch (IntranetException e) {
			addActionError("Erro ao cadastrar vaga!");
		}
			
		return lista();
	}
	
	
	public String save() {	
		this.seRegra = false;
		try {
			this.vaga.setDataDoAnuncio(Calendar.getInstance());
			this.vaga.setPublicador(UserData.getLoggedUser());
			this.vaga.setEmpresa(empresaDAO.getEmpresaById(empresaId));
			
			for (Rule ruleOfPerson : UserData.getLoggedUser().getRegras()){
				if(ruleOfPerson.getNome().equals(RULE_VAGA_NOVO))
					seRegra = true;
			}
			
			if(seRegra){
				this.vaga.setSeAtivo(true);
				this.vaga.setConfirmacao(true);
				//email(vaga);
			}else{
				this.vaga.setSeAtivo(false);
				this.vaga.setConfirmacao(false);				
			}
			
			vagaDAO.save(this.vaga);
		} catch (IntranetException e) {
			addActionError("Erro ao cadastrar vaga!");
		}
		return lista();
		
	}

	// Gets and Sets

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public VagaDAO getVagaDAO() {
		return vagaDAO;
	}

	public void setVagaDAO(VagaDAO vagaDAO) {
		this.vagaDAO = vagaDAO;
	}

	public void setTempoDeResposta(String tempoDeResposta) {
		this.tempoDeResposta = tempoDeResposta;
	}

	public String getTempoDeResposta() {
		return tempoDeResposta;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}

	public Long getIdVaga() {
		return idVaga;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public List<AreaEnum> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaEnum> areas) {
		this.areas = areas;
	}


	public Boolean getSeRegra() {
		return seRegra;
	}


	public void setSeRegra(Boolean seRegra) {
		this.seRegra = seRegra;
	}

	

}
