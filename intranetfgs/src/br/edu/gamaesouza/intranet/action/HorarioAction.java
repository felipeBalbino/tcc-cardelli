package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetivaHorario;
import br.edu.gamaesouza.intranet.bean.Horario;
import br.edu.gamaesouza.intranet.bean.Professor;

import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.HorarioDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaHorarioNovoParams;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaNovoParams;
import br.edu.gamaesouza.intranet.params.impl.DisciplinaLetivaSearchParams;
import br.edu.gamaesouza.intranet.params.impl.HorarioNovoParams;

import br.edu.gamaesouza.intranet.security.UserData;

import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;
import br.edu.gamaesouza.intranet.utils.FormUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

import com.opensymphony.xwork2.ActionSupport;

public class HorarioAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private List<DisciplinaLetivaHorario> disciplinaLetivaHorario = new ArrayList<DisciplinaLetivaHorario>();
	private List<Horario> horarios = new ArrayList<Horario>();
	private List<Integer> anos  = new ArrayList<Integer>();
	private List<Integer> semestres = new ArrayList<Integer>();
	private List<DisciplinaLetiva> disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
	private List<DiaSemanaEnum> diasSemana = new ArrayList<DiaSemanaEnum>();
	
		
	private Integer id;
	private Integer idDisciplinaLetiva;
	private DiaSemanaEnum diaSemana;
	private Integer horarioId ;
	
	@Autowired private Horario horario;
	@Autowired private HorarioDAO horarioDAO;
	@Autowired private DisciplinaDAO disciplinaDAO;
	@Autowired private DisciplinaLetiva disciplinaLetiva;
	@Autowired private HorarioNovoParams horarioNovoParams;
	@Autowired private DisciplinaLetivaHorarioNovoParams disciplinaLetivaHorarioNovoParams;

	public String prepare()  {
		//UserData.grantAccess();
		
		try {
			setAnos(FormUtil.getAnosList(2));
			setSemestres(FormUtil.getSemestresList());
			horarios = 	horarioDAO.getAllHorarios();
		} catch ( IntranetException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "novoList";
	
	}
	
	public String save() throws Exception{
		horarioDAO.save(horarioNovoParams.getHorario());
		addActionMessage("Horário criado com sucesso");
		return prepare();
	}
	
	public String delete() throws Exception{
		try{
		horarioDAO.delete( horarioDAO.getHorarioById( id ) );
		addActionMessage("Horário deletado com sucesso");			
	} catch (Exception e) {		
		addActionError("Não foi possivel deletar Horário, ocorreu um erro interno no Servidor");			
	}
		return prepare();
	}
	
	public String deleteDisciplinaLetivaHorario() throws Exception{
		try{
		DisciplinaLetivaHorario disciplinaLetivaHorario = horarioDAO.getDisciplinaLetivaHorarioById( id );
		horarioDAO.deleteDisciplinaLetivaHorario( disciplinaLetivaHorario );
		addActionMessage("Horário deletado com sucesso");			
	} catch (Exception e) {		
		addActionError("Não foi possivel deletar Horário, ocorreu um erro interno no Servidor");			
	}
		return listarHorarioPorDisciplinaLetiva();
	}
	
	public String listarHorarioPorDisciplinaLetiva() throws Exception{
		try{
		setDiasSemana( Arrays.asList(DiaSemanaEnum.values()) );
		horarios = 	horarioDAO.getAllHorarios();
		disciplinaLetivaHorario = 	horarioDAO.getAllDisciplinaLetivaHorariosById(idDisciplinaLetiva);
		} catch (Exception e) {		
			addActionError("Não foi possivel listar Horários, ocorreu um erro interno no Servidor");			
		}
	return "listarHorarioDiscplinaLetiva";
	}
	
	
	public String saveHorariosEmDisciplinaLetiva() throws Exception{
		try{
				DisciplinaLetiva letiva = disciplinaDAO.getDisciplinaLetivaById(idDisciplinaLetiva);
				Horario horario = horarioDAO.getHorarioById( horarioId );
				DisciplinaLetivaHorario disciplinaLetivaHorario = new DisciplinaLetivaHorario();
				disciplinaLetivaHorario.setDiaSemana(diaSemana);
				disciplinaLetivaHorario.setDisciplinaLetiva(letiva);
				disciplinaLetivaHorario.setHorario(horario);
				disciplinaDAO.update(letiva);
				addActionMessage("Horario adicionado em disciplina letiva com sucesso");
		} catch (Exception e) {		
			addActionError("Não foi possivel Adicionar, ocorreu um erro interno no Servidor");			
		}
		return listarHorarioPorDisciplinaLetiva();
	}

	public DisciplinaLetiva getDisciplinaLetiva() {
		return disciplinaLetiva;
	}


	public void setDisciplinaLetiva(DisciplinaLetiva disciplinaLetiva) {
		this.disciplinaLetiva = disciplinaLetiva;
	}


	
	public Integer getHorarioId() {
		return horarioId;
	}

	public void setHorarioId(Integer horarioId) {
		this.horarioId = horarioId;
	}

	public List<Integer> getSemestres() {
		return semestres;
	}


	public void setSemestres(List<Integer> semestres) {
		this.semestres = semestres;
	}


	public List<DisciplinaLetiva> getDisciplinasLetivas() {
		return disciplinasLetivas;
	}


	public void setDisciplinasLetivas(List<DisciplinaLetiva> disciplinasLetivas) {
		this.disciplinasLetivas = disciplinasLetivas;
	}

	public void setAnos(List<Integer> anos) {
		this.anos = anos;
	}


	public List<Integer> getAnos() {
		return anos;
	}


	public void setHorario(Horario horario) {
		this.horario = horario;
	}


	public Horario getHorario() {
		return horario;
	}

	public void setHorarios( List<Horario> horarios ) {
		this.horarios = horarios;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setHorarioNovoParams(HorarioNovoParams horarioNovoParams) {
		this.horarioNovoParams = horarioNovoParams;
	}

	public HorarioNovoParams getHorarioNovoParams() {
		return horarioNovoParams;
	}

	public void setDisciplinaLetivaHorario( List<DisciplinaLetivaHorario> disciplinaLetivaHorario ) {
		this.disciplinaLetivaHorario = disciplinaLetivaHorario;
	}

	public List<DisciplinaLetivaHorario> getDisciplinaLetivaHorario() {
		return disciplinaLetivaHorario;
	}

	public void setDiasSemana( List<DiaSemanaEnum> diasSemana ) {
		this.diasSemana = diasSemana;
	}

	public List<DiaSemanaEnum> getDiasSemana() {
		return diasSemana;
	}

	public void setDisciplinaLetivaHorarioNovoParams( DisciplinaLetivaHorarioNovoParams disciplinaLetivaHorarioNovoParams ) {
		this.disciplinaLetivaHorarioNovoParams = disciplinaLetivaHorarioNovoParams;
	}

	public DisciplinaLetivaHorarioNovoParams getDisciplinaLetivaHorarioNovoParams() {
		return disciplinaLetivaHorarioNovoParams;
	}

	public Integer getIdDisciplinaLetiva() {
		return idDisciplinaLetiva;
	}

	public void setIdDisciplinaLetiva( Integer idDisciplinaLetiva ) {
		this.idDisciplinaLetiva = idDisciplinaLetiva;
	}

	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}

	public DisciplinaDAO getDisciplinaDAO() {
		return disciplinaDAO;
	}

	public void setDiaSemana(DiaSemanaEnum diaSemana) {
		this.diaSemana = diaSemana;
	}

	public DiaSemanaEnum getDiaSemana() {
		return diaSemana;
	}





	
	
}
