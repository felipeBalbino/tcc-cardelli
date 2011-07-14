package br.edu.gamaesouza.intranet.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Arquivo;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.dao.ArquivoDAO;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;

import com.opensymphony.xwork2.ActionSupport;

public  class BuscarMaterialAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private static final String LOAD_BUSCAR_MATERIAL_FIELDS = "loadFields"; 
	
	@Getter @Setter private List<Disciplina> disciplinas    = new ArrayList<Disciplina>();
	@Getter @Setter private List<Integer>    anos     		= new ArrayList<Integer>();
	@Getter @Setter private List<Integer>    semestres      = new ArrayList<Integer>();
	@Getter @Setter private List<Arquivo>    arquivos 		= new ArrayList<Arquivo>();
	@Getter @Setter private List<Professor>  professores    = new ArrayList<Professor>();
	@Getter @Setter private List<String>     turnos         = new ArrayList<String>();
	
	@Getter @Setter private Integer curso;
	@Getter @Setter private Integer professor;
	@Getter @Setter private Integer disciplina;
	@Getter @Setter private Integer ano;
	@Getter @Setter private Integer semestre;
	@Getter @Setter private Integer turno;
	
	@Getter @Setter private Integer idarquivo;
	
	@Getter @Setter @Autowired private EnviarEmail   enviarEmail;
	@Getter @Setter @Autowired private ArquivoDAO    arquivoDAO;
	@Getter @Setter @Autowired private DisciplinaDAO disciplinaDAO;
	@Getter @Setter @Autowired private PessoaDAO     pessoaDAO;
	

	
	public String carregaFiltrosPesquisa()  {
		loadSearchFilters();
		return LOAD_BUSCAR_MATERIAL_FIELDS;
		
	}
	
	public String carregaResultado() throws Exception {
		
		loadSearchFilters();
		arquivos = arquivoDAO.getArquivos(disciplina, ano, semestre,professor);
		return LOAD_BUSCAR_MATERIAL_FIELDS;
		
	}
	
	private void loadSearchFilters(){
		try {
			disciplinas = disciplinaDAO.getAllDisciplinas();
			professores = pessoaDAO.getAll();
			anos= FormUtil.getAnos();
			semestres = FormUtil.getSemestresList();
			//turnos = FormUtil.getTurnosList();
		} catch (IntranetException e) {
			addActionMessage(e.getMessage());
		}
	}
	

	
	public String enviaremail() {

		Arquivo arquivo;
		try {
			arquivo = arquivoDAO.getArquivoById(idarquivo);						
			//enviar email dd arquivo para pessoa logada
			ServletContext sContext = ServletActionContext.getServletContext();  
			String diretorio = sContext.getRealPath("/arquivos");
			File fds = new File(diretorio + "\\" +arquivo.getUrl()); 
			
			if(fds.length() <= 25000000){
				try {
					enviarEmail.sendArquivoPeloAluno( arquivo, arquivo.getNome(), UserData.getLoggedUser() );
					addActionMessage("Arquivo enviado com sucesso - "+UserData.getLoggedUser().getEmail());
				} catch ( Throwable e ) {
					addActionError("N�o foi poss�vel enviar o email");
				}			
			}else{
				addActionError("O arquivo selecionado precisa ser menor que 25MB.");
			}
				
				
				return carregaFiltrosPesquisa();
		} catch (Exception e1) {
			return carregaFiltrosPesquisa();
		}
		
	
}
}
