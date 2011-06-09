package br.edu.gamaesouza.intranet.action;
/**
 * @author Felipe Balbino
 * @since 02/06/2011
 */

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Empresa;
import br.edu.gamaesouza.intranet.bean.Vaga;
import br.edu.gamaesouza.intranet.dao.EmpresaDAO;
import br.edu.gamaesouza.intranet.dao.VagaDAO;
import br.edu.gamaesouza.intranet.security.UserData;
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

	private List<Vaga> vagas;
	private List<Empresa> empresas;
	private Long idVaga;
	
	@Autowired private VagaDAO vagaDAO;
	@Autowired private EmpresaDAO empresaDAO;

	private String tempoDeResposta;
	public String lista() {
		
		UserData.grantAccess(RULE_VAGA_LISTA);
		try {			
			long inicio = System.currentTimeMillis();  
			vagas = vagaDAO.getAll();
			empresas = empresaDAO.getAll();
			long  end = System.currentTimeMillis();  
			setTempoDeResposta(FormUtil.tempoResposta(vagas, inicio, end)); 
		} catch (IntranetException e) {
			logger.warning("Erro M�todo lista() em VagaEmpregoAction.");
		}
		return "this";
		
	}
	
 

	public void confirmar() throws IntranetException{
		try {
			UserData.grantAccess(RULE_VAGA_LISTA);
			Vaga vaga = new Vaga();
			vaga = vagaDAO.getEventoById(idVaga);
			vaga.setConfirmacao(true);
			vagaDAO.merge(vaga);
		} catch (IntranetException e) {
			logger.warning("Erro Metodo confirmar em VagaEmpregoAction.");
		}		
		lista();
	}
	
	public void desconfirmar(){
		
		try {
			UserData.grantAccess(RULE_VAGA_LISTA);
			Vaga vaga = new Vaga();
			vaga = vagaDAO.getEventoById(idVaga);
			vaga.setConfirmacao(false);
			vagaDAO.merge(vaga);
		} catch (IntranetException e) {
			logger.warning("Erro Metodo desconfirmar em VagaEmpregoAction.");
		}		
		lista();
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

	

}
