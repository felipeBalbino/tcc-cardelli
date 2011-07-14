package br.edu.gamaesouza.intranet.action;
/**
 * @author Felipe Balbino
 * @since 02/06/2011
 */

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
	@Getter @Setter private Vaga vaga;
	@Getter @Setter private Long empresaId;
	@Getter @Setter private Long areaProfissionalId;
	@Getter @Setter private Long enderecoId;
	@Getter @Setter private Empresa empresa;
	@Getter @Setter private Endereco endereco;
	@Getter @Setter private List<TiposContratacaoEnum> tiposDeContratacao;
	@Getter @Setter private List<PaisEnum> paises;
	@Getter @Setter private List<FaixaSalarialEnum> salarios;
	@Getter @Setter private List<AreaProfissional> areas;
	@Getter @Setter private List<Vaga> vagas;
	@Getter @Setter private List<Empresa> empresas; 
	@Getter @Setter private List<Endereco> enderecos; 
	@Getter @Setter private Long idVaga;
	@Getter @Setter Boolean seRegra = false;
	@Getter @Setter private String tempoDeResposta;
	
	@Getter @Setter @Autowired private VagaDAO vagaDAO;
	@Getter @Setter @Autowired private EmpresaDAO empresaDAO;
	@Getter @Setter @Autowired private EnderecoDAO enderecoDAO;
	@Getter @Setter @Autowired private AreaProfissionalDAO areaProfissionalDAO;
	@Getter @Setter @Autowired private EnviarEmail   enviarEmail;
	@Getter @Setter @Autowired private PessoaDAO pessoaDAO;	
	
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
	

}
