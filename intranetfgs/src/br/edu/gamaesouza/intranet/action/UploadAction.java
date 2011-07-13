package br.edu.gamaesouza.intranet.action;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lombok.Data;

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
public @Data class UploadAction extends ActionSupport {
	
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
		disciplinas = disciplinaDAO.getAllDisciplinas();
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
				fileName = ArquivoUtil.saveFile(arquivo);
				arquivo.setDisciplinaLetiva(dl);
				arquivo.setUrl(fileName);
				arquivo.setProfessor((Professor)UserData.getLoggedUser());
				arquivo.setDataEnvio(Calendar.getInstance());
				arquivoDAO.save(arquivo);
				enviarEmail.sendMailToAluno( dl , fileName, UserData.getLoggedUser() );
				addActionMessage("Arquivo enviado com sucesso");
			} catch (IntranetException e1) {
				addActionMessage(e1.getMessage());
			} catch ( Throwable e ) {
				addActionMessage(e.getMessage());
			}finally{
				arquivo = (Arquivo) SpringUtil.getBean("arquivo");
				materia = null;
				try {
					disciplinas = disciplinaDAO.getAllDisciplinas();
				} catch (IntranetException e) {
					addActionMessage(e.getMessage());
				}
			}			
				return lista();
		
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
				addActionMessage(e.getMessage());
			}
			
			return "modificarArquivo";
		
	}

	public String delete() {
		UserData.grantAccess(RULE_UPLOAD_DELETA);

			try {
				arquivoDAO.delete(id, (Professor)UserData.getLoggedUser());
				addActionMessage("Arquivo deletado com sucesso!");
			} catch (Exception e) {
				addActionMessage("N�o foi possivel remover o arquivo.");
				addActionMessage(e.getMessage());
			} finally {
				try {
					arquivos = arquivoDAO.getArquivos((Professor) UserData.getLoggedUser());
				} catch (IntranetException e) {
					addActionMessage(e.getMessage());addActionMessage(e.getMessage());
				}
			}

			return "modificarArquivo";

	}

}