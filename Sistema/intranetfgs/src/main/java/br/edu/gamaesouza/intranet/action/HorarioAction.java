package br.edu.gamaesouza.intranet.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
	
	@Getter @Setter private List<DisciplinaLetivaHorario> disciplinaLetivaHorario = new ArrayList<DisciplinaLetivaHorario>();
	@Getter @Setter private List<Horario> horarios = new ArrayList<Horario>();
	@Getter @Setter private List<Integer> anos  = new ArrayList<Integer>();
	@Getter @Setter private List<Integer> semestres = new ArrayList<Integer>();
	@Getter @Setter private List<DisciplinaLetiva> disciplinasLetivas = new ArrayList<DisciplinaLetiva>();
	@Getter @Setter private List<DiaSemanaEnum> diasSemana = new ArrayList<DiaSemanaEnum>();
	
		
	@Getter @Setter private Integer id;
	@Getter @Setter private Integer idDisciplinaLetiva;
	@Getter @Setter private DiaSemanaEnum diaSemana;
	@Getter @Setter private Integer horarioId;
	@Getter @Setter private Integer idHorario;
	
	@Getter @Setter @Autowired private Horario horario;
	@Getter @Setter @Autowired private HorarioDAO horarioDAO;
	@Getter @Setter @Autowired private DisciplinaDAO disciplinaDAO;
	@Getter @Setter @Autowired private DisciplinaLetiva disciplinaLetiva;
	@Getter @Setter @Autowired private HorarioNovoParams horarioNovoParams;
	@Getter @Setter @Autowired private DisciplinaLetivaHorarioNovoParams disciplinaLetivaHorarioNovoParams;

	public String prepare()  {
		//UserData.grantAccess();
		
		try {
			setAnos(FormUtil.getAnosList(2));
			setSemestres(FormUtil.getSemestresList());
			horarios = 	horarioDAO.getAllHorarios();
		} catch ( IntranetException e ) {
			addActionMessage(e.getMessage());
	
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
	
	public String deleteDisciplinaLetivaHorario() throws Exception{
		try{
		DisciplinaLetivaHorario disciplinaLetivaHorario = horarioDAO.getDisciplinaLetivaHorarioByHorarioAndDisciplinaLetivaId(idDisciplinaLetiva,idHorario);
		horarioDAO.deleteDisciplinaLetivaHorario( disciplinaLetivaHorario );
		addActionMessage("Hor�rio deletado com sucesso");			
	} catch (Exception e) {		
		addActionError("N�o foi possivel deletar Hor�rio, ocorreu um erro interno no Servidor");
		throw new IntranetException(e.getMessage());
	}
		return listarHorarioPorDisciplinaLetiva();
	}
	
	public String listarHorarioPorDisciplinaLetiva() throws Exception{
		try{
		disciplinaLetiva = disciplinaDAO.getDisciplinaLetivaById(idDisciplinaLetiva);
		setDiasSemana( Arrays.asList(DiaSemanaEnum.values()) );
		horarios = 	horarioDAO.getAllHorarios();
		disciplinaLetivaHorario = 	horarioDAO.getAllDisciplinaLetivaHorariosById(idDisciplinaLetiva);
		} catch (Exception e) {		
			addActionError("N�o foi possivel listar Hor�rios, ocorreu um erro interno no Servidor");		
			e.printStackTrace();
			throw new IntranetException(e.getMessage());
		}
	return "listarHorarioDiscplinaLetiva";
	}
	
	
	public String saveHorariosEmDisciplinaLetiva() throws Exception{
		try{
				DisciplinaLetiva letiva = disciplinaDAO.getDisciplinaLetivaById(idDisciplinaLetiva);
				Horario horario = horarioDAO.getHorarioById( horarioId );
				DisciplinaLetivaHorario disciplinaLetivaHorario = new DisciplinaLetivaHorario();
				disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().setDiaSemana(diaSemana);
				disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().setDisciplinaLetiva(letiva);
				disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().setHorario(horario);
				
				if(horarioDAO.validationDisciplinaLetivaHorario(disciplinaLetivaHorario)){
					horarioDAO.saveDisciplinaLetivaHorario(disciplinaLetivaHorario);
					addActionMessage("Horario adicionado em disciplina letiva com sucesso");
				}else{
					addActionError("N�o foi possivel Adicionar, este horário já existe nesta disciplina. ");		
				}
		} catch (Exception e) {		
			addActionError("N�o foi possivel Adicionar, ocorreu um erro interno no Servidor");
			e.printStackTrace();
			throw new IntranetException(e.getMessage());
		}
		return listarHorarioPorDisciplinaLetiva();
	}

		
	
}
