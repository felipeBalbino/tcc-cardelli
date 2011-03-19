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
	
	@Autowired private Horario horario;
	@Autowired private HorarioDAO horarioDAO;
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
	addActionMessage("Hor�rio criado com sucesso");
		return prepare();
	}
	
	public String delete() throws Exception{
		try{
		horarioDAO.delete( horarioDAO.getHorarioById( id ) );
		addActionMessage("Hor�rio deletado com sucesso");			
	} catch (Exception e) {		
		addActionError("N�o foi possivel deletar Hor�rio, ocorreu um erro interno no Servidor");			
	}
		return prepare();
	}
	
	public String listarHorarioPorDisciplinaLetiva() throws Exception{
		try{
		setDiasSemana( Arrays.asList(DiaSemanaEnum.values()) );
		horarios = 	horarioDAO.getAllHorarios();
		disciplinaLetivaHorario = 	horarioDAO.getAllDisciplinaLetivaHorariosById(id);
		} catch (Exception e) {		
			addActionError("N�o foi possivel listar Hor�rios, ocorreu um erro interno no Servidor");			
		}
	return "listarHorarioDiscplinaLetiva";
	}
	
	
	public String saveHorariosEmDisciplinaLetiva() throws Exception{
		try{
		horarioDAO.saveDisciplinaLetivaHorario(disciplinaLetivaHorarioNovoParams.getDisciplinaLetivaHorario());
		addActionMessage("Horario adicionado em disciplina letiva com sucesso");
		} catch (Exception e) {		
			addActionError("N�o foi possivel Adicionar, ocorreu um erro interno no Servidor");			
		}
		return "listaDisciplinaLetiva";
	}

	public DisciplinaLetiva getDisciplinaLetiva() {
		return disciplinaLetiva;
	}


	public void setDisciplinaLetiva(DisciplinaLetiva disciplinaLetiva) {
		this.disciplinaLetiva = disciplinaLetiva;
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





	
	
}
