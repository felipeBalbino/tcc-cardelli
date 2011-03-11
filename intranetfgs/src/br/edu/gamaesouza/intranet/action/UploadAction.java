package br.edu.gamaesouza.intranet.action;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.gamaesouza.intranet.bean.Arquivo;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.dao.ArquivoDAO;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.mail.EnviarEmail;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.ArquivoUtil;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;

@Conversion
public class UploadAction extends ActionSupport {
	
	private static final long serialVersionUID = -8803364095951231024L;
	private static final String RULE_UPLOAD_NOVO = "RULE_UPLOAD_NOVO";
	private static final String RULE_UPLOAD_LISTA = "RULE_UPLOAD_LISTA";
	private static final String RULE_UPLOAD_DELETA = "RULE_UPLOAD_DELETA";
	
	
	private Integer materia;
	private Integer semestre;
	private Integer idarquivo;
	private Integer ano =  Calendar.getInstance().get(Calendar.YEAR);
	private Integer id;
	private String turno;
	
	private List<Arquivo> arquivos;
	private List<Disciplina> disciplinas;
	private List<DisciplinaLetiva> disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
	private List<Integer> anos  = new ArrayList<Integer>();
	private List<Integer> semestres = new ArrayList<Integer>();
	private List<String> turnos = new ArrayList<String>();

	@Autowired private ArquivoDAO arquivoDAO;
	@Autowired private DisciplinaDAO disciplinaDAO;
	@Autowired private EnviarEmail enviarEmail;
	@Autowired private Arquivo arquivo;
	
	public String prepare() throws Exception {
		setAnos(FormUtil.getAnosList(1));
		setSemestres(FormUtil.getSemestresList());
		disciplinas = disciplinaDAO.getAll();
		setTurnos(FormUtil.getTurnosList());
		return "adicionarArquivo";
	}
	
	

	public String send() {
		UserData.grantAccess(RULE_UPLOAD_NOVO);

			DisciplinaLetiva dl = null;
			String fileName = null;
			try {
				DisciplinaLetiva letiva = (DisciplinaLetiva) SpringUtil.getBean("disciplinaLetiva");
				Disciplina disc = disciplinaDAO.getDisciplinaById(materia);

				letiva.setDisciplina(disc);
				letiva.setSemestre(semestre);
				letiva.setAno(ano);


				
				dl = disciplinaDAO.saveOrReturnDisciplinaLetiva(letiva);
				
				
				arquivo.setDisciplinaLetiva(dl);

				fileName = ArquivoUtil.saveFile(arquivo);

				

				arquivo.setUrl(fileName);
				arquivo.setProfessor((Professor)UserData.getLoggedUser());
				arquivo.setDataEnvio(Calendar.getInstance());
				
				arquivoDAO.save(arquivo);
				
				addActionMessage("Arquivo enviado com sucesso");
			} catch (IntranetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				arquivo = (Arquivo) SpringUtil.getBean("arquivo");
				materia = null;
				try {
					disciplinas = disciplinaDAO.getAll();
				} catch (IntranetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			
			//enviar email para todos os alunos desta DisciplinaLetiva
		
				try {
					enviarEmail.sendMailToAluno( dl , fileName, UserData.getLoggedUser() );
				} catch ( Throwable e ) {
					System.out.println("Não foi possível enviar emails para os alunos");
				}			
			
			
				return lista();
		
	}
	
	public String enviaremail() {

		Arquivo arquivo;
		try {
			arquivo = arquivoDAO.getArquivoById(idarquivo);
			String fileName = arquivo.getNome();
			addActionMessage("Arquivo enviado com sucesso");
						
			arquivo = (Arquivo) SpringUtil.getBean("arquivo");
			
			//enviar email dd arquivo para pessoa logada
		
				try {
					enviarEmail.sendArquivoPeloAluno( arquivo, fileName, UserData.getLoggedUser() );
				} catch ( Throwable e ) {
					System.out.println("Não foi possível enviar emails para os alunos");
				}			
			
			
				return lista();
		} catch (Exception e1) {
			return lista();
		}
		
	
}
	
	public String buscarDisciplinas() throws Exception{
		semestres = FormUtil.getSemestresList();
		turnos = FormUtil.getTurnosList();

		
		disciplinasLetivas = disciplinaDAO.getDisciplinasLetivas(ano, semestre,turno);
		
		
		for(DisciplinaLetiva dl : disciplinasLetivas){
			System.out.println(dl.getId() + " " + dl.getDisciplina().getNome());
		}
		
		System.out.println(disciplinasLetivas.size() + " TAMANHO");
		
		return prepare();
		
	}
	
	public String buscarDisciplinasProfessor() throws Exception{
		semestres = FormUtil.getSemestresList();
		turnos = FormUtil.getTurnosList();
		disciplinasLetivas = disciplinaDAO.getDisciplinasLetivas(ano, semestre,turno,UserData.getLoggedUser());
		
		return prepare();
		
	}
	

	public String lista() {
		UserData.grantAccess(RULE_UPLOAD_LISTA);
			
			try {
				arquivos = arquivoDAO.getArquivos((Professor)UserData.getLoggedUser());
			} catch (IntranetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "modificarArquivo";
		
	}

	public String delete() {
		UserData.grantAccess(RULE_UPLOAD_DELETA);

			try {
				arquivoDAO.delete(id, (Professor)UserData.getLoggedUser());
				addActionMessage("Arquivo deletado com sucesso!");
			} catch (Exception e) {

				addActionMessage("Nï¿½o foi possivel remover o arquivo.");
				e.printStackTrace();
			} finally {
				try {
					arquivos = arquivoDAO.getArquivos((Professor) UserData.getLoggedUser());
				} catch (IntranetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return "modificarArquivo";

	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Integer getMateria() {
		return materia;
	}

	public void setMateria(Integer materia) {
		this.materia = materia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public void setAnos(List<Integer> anos) {
		this.anos = anos;
	}

	public List<Integer> getAnos() {
		return anos;
	}

	public void setSemestres(List<Integer> semestres) {
		this.semestres = semestres;
	}

	public List<Integer> getSemestres() {
		return semestres;
	}

	public void setTurnos(List<String> turnos) {
		this.turnos = turnos;
	}

	public List<String> getTurnos() {
		return turnos;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	public List<DisciplinaLetiva> getDisciplinasLetivas() {
		return disciplinasLetivas;
	}



	public void setDisciplinasLetivas(List<DisciplinaLetiva> disciplinasLetivas) {
		this.disciplinasLetivas = disciplinasLetivas;
	}
	
	
	public Integer getIdarquivo() {
		return idarquivo;
	}



	public void setIdarquivo(Integer idarquivo) {
		this.idarquivo = idarquivo;
	}

}