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

import br.edu.gamaesouza.intranet.bean.AreaProfissional;
import br.edu.gamaesouza.intranet.bean.Empresa;
import br.edu.gamaesouza.intranet.bean.Endereco;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.bean.Rule;
import br.edu.gamaesouza.intranet.bean.Vaga;
import br.edu.gamaesouza.intranet.dao.AreaProfissionalDAO;
import br.edu.gamaesouza.intranet.dao.EmpresaDAO;
import br.edu.gamaesouza.intranet.dao.EnderecoDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.dao.VagaDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.AreaEnum;
import br.edu.gamaesouza.intranet.utils.FaixaSalarialEnum;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.PaisEnum;
import br.edu.gamaesouza.intranet.utils.TiposContratacaoEnum;


import com.opensymphony.xwork2.ActionSupport;

public class VagasEmpregoAction extends ActionSupport {

	private final Logger logger = Logger.getLogger("br.edu.gamaesouza.intranet.action.VagasEmpregoAction");
	
	private static final long serialVersionUID = 1L;
	
	private static final String RULE_VAGA_NOVO = "RULE_VAGA_NOVO";
	private static final String RULE_VAGA_ALTERA = "RULE_VAGA_ALTERA";
	private static final String RULE_VAGA_DELETA = "RULE_VAGA_DELETA";
	private static final String RULE_VAGA_LISTA = "RULE_VAGA_LISTA";
	private static final String RULE_EMPRESA_NOVO = "RULE_EMPRESA_NOVO";

	/*
	  Parametros vindo da tela para save
	*/
	private Vaga vaga;
	private Long empresaId;
	private Long areaProfissionalId;
	private Long enderecoId;
	
	private Empresa empresa;
	private Endereco endereco;
	
	private List<TiposContratacaoEnum> tiposDeContratacao;
	private List<PaisEnum> paises;
	private List<FaixaSalarialEnum> salarios;
	private List<AreaProfissional> areas;
	private List<Vaga> vagas;
	private List<Empresa> empresas; 
	private List<Endereco> enderecos; 
	private Long idVaga;
	Boolean seRegra = false;
	
	@Autowired private VagaDAO vagaDAO;
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private EnderecoDAO enderecoDAO;
	@Autowired private AreaProfissionalDAO areaProfissionalDAO;
	@Autowired private EnviarEmail   enviarEmail;
	@Autowired private PessoaDAO pessoaDAO;	
	
	private String tempoDeResposta;
	
	public String lista() {
		this.seRegra = false;
		
		long inicio = System.currentTimeMillis();  
		try {
			empresas = empresaDAO.getAll();
			setAreas(areaProfissionalDAO.getAll());
			setTiposDeContratacao(Arrays.asList(TiposContratacaoEnum.values()));
			setPaises(Arrays.asList(PaisEnum.values()));
			setEnderecos(enderecoDAO.getAll());
			setSalarios(Arrays.asList(FaixaSalarialEnum.values()));
			
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
			logger.warning("Erro ao Listar Vagas!");
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
			setAreas(areaProfissionalDAO.getAll());
			setTiposDeContratacao(Arrays.asList(TiposContratacaoEnum.values()));
			setPaises(Arrays.asList(PaisEnum.values()));
			setEnderecos(enderecoDAO.getAll());
			setSalarios(Arrays.asList(FaixaSalarialEnum.values()));
		} catch (IntranetException e) {
			logger.warning("Erro no prepare em vagasEmpregoAction");
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
				
				vaga  = vagaDAO.merge(vaga);
				
				List <Pessoa>  pessoas = pessoaDAO.getAlunosByAreaProfissional(vaga.getAreaProfissional().getId());
				try {
					enviarEmail.enviarEmailComVagaParaAluno(vaga, pessoas);
				} catch (Throwable e) {
					// COLOCAR VALIDACAO
					e.printStackTrace();
				}
				
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
			logger.warning("Erro ao cadastrar vaga!");
		}
			
		return lista();
	}
	
	
	public String save() {	
		this.seRegra = false;
		try {
			this.vaga.setDataDoAnuncio(Calendar.getInstance());
			this.vaga.setPublicador(UserData.getLoggedUser());
			this.vaga.setEmpresa(empresaDAO.getEmpresaById(empresaId));
			this.vaga.setAreaProfissional(areaProfissionalDAO.getAreaProfissionalById(areaProfissionalId));
			
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
			logger.warning("Erro ao cadastrar vaga!");
		}
		return lista();
		
	}
	
	
	public String saveEmpresa() {	
		try {
			
			this.empresa.setEndereco(enderecoDAO.getEnderecoByid(enderecoId));
			empresaDAO.save(this.empresa);
			
		} catch (IntranetException e) {
			logger.warning("Erro ao cadastrar Empresa!");
		}
		return prepare();
		
	}
	
	
	public String saveEndereco() {	
		try {
			
			enderecoDAO.save(this.endereco);
			
		} catch (IntranetException e) {
			logger.warning("Erro ao cadastrar Empresa!");
		}
		return prepare();
		
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

	public Boolean getSeRegra() {
		return seRegra;
	}


	public void setSeRegra(Boolean seRegra) {
		this.seRegra = seRegra;
	}


	public void setAreaProfissionalId(Long areaProfissionalId) {
		this.areaProfissionalId = areaProfissionalId;
	}


	public Long getAreaProfissionalId() {
		return areaProfissionalId;
	}


	public EmpresaDAO getEmpresaDAO() {
		return empresaDAO;
	}


	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}


	public AreaProfissionalDAO getAreaProfissionalDAO() {
		return areaProfissionalDAO;
	}


	public void setAreaProfissionalDAO(AreaProfissionalDAO areaProfissionalDAO) {
		this.areaProfissionalDAO = areaProfissionalDAO;
	}


	public void setAreas(List<AreaProfissional> areas) {
		this.areas = areas;
	}


	public List<AreaProfissional> getAreas() {
		return areas;
	}


	public void setTiposDeContratacao(List<TiposContratacaoEnum> tiposDeContratacao) {
		this.tiposDeContratacao = tiposDeContratacao;
	}


	public List<TiposContratacaoEnum> getTiposDeContratacao() {
		return tiposDeContratacao;
	}


	public void setEnviarEmail(EnviarEmail enviarEmail) {
		this.enviarEmail = enviarEmail;
	}


	public EnviarEmail getEnviarEmail() {
		return enviarEmail;
	}


	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}


	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}


	public void setPaises(List<PaisEnum> paises) {
		this.paises = paises;
	}


	public List<PaisEnum> getPaises() {
		return paises;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
	}


	public Long getEnderecoId() {
		return enderecoId;
	}


	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	public List<Endereco> getEnderecos() {
		return enderecos;
	}


	public void setSalarios(List<FaixaSalarialEnum> salarios) {
		this.salarios = salarios;
	}


	public List<FaixaSalarialEnum> getSalarios() {
		return salarios;
	}

	

}
