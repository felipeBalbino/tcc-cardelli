package br.edu.gamaesouza.intranet.params.impl;


import org.springframework.beans.factory.annotation.Autowired;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetivaHorario;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.HorarioDAO;
import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;



public class DisciplinaLetivaHorarioNovoParams implements DisciplinaLetivaHorarioParams {

	private DiaSemanaEnum diaSemana;
	private Integer disciplinaLetivaId;
	private Integer horarioId ;
	
	@Autowired private DisciplinaDAO disciplinaDAO;
	@Autowired private HorarioDAO horarioDAO;

	@Override
	public boolean isEmpty() {
		return false;
	}
	public DisciplinaLetivaHorario getDisciplinaLetivaHorario(){
		DisciplinaLetivaHorario disciplinaLetivaHorario = new DisciplinaLetivaHorario();
		try {
			disciplinaLetivaHorario.setDiaSemana( diaSemana );
			disciplinaLetivaHorario.setDisciplinaLetiva( disciplinaDAO.getDisciplinaLetivaById( disciplinaLetivaId )  );
			disciplinaLetivaHorario.setHorario( horarioDAO.getHorarioById( horarioId ) );
		} catch ( IntranetException e ) {
			e.printStackTrace();
		}

		return disciplinaLetivaHorario;
		
	}
	public DiaSemanaEnum getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana( DiaSemanaEnum diaSemana ) {
		this.diaSemana = diaSemana;
	}
	public Integer getHorarioId() {
		return horarioId;
	}
	public void setHorarioId( Integer horarioId ) {
		this.horarioId = horarioId;
	}
	public Integer getDisciplinaLetivaId() {
		return disciplinaLetivaId;
	}
	public void setDisciplinaLetivaId(Integer disciplinaLetivaId) {
		this.disciplinaLetivaId = disciplinaLetivaId;
	}	
}
