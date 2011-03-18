package br.edu.gamaesouza.intranet.params.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetivaHorario;
import br.edu.gamaesouza.intranet.bean.Horario;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.HorarioDAO;
import br.edu.gamaesouza.intranet.params.HorarioParams;
import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;



public class DisciplinaLetivaHorarioNovoParams implements DisciplinaLetivaHorarioParams {

	private DiaSemanaEnum diaSemana;
	private String disciplinaLetivaId;
	private Integer horarioId ;
	
	@Autowired private DisciplinaDAO disciplinaDAO;
	@Autowired private HorarioDAO horarioDAO;

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	public DisciplinaLetivaHorario getDisciplinaLetivaHorario(){
		DisciplinaLetivaHorario disciplinaLetivaHorario = (DisciplinaLetivaHorario) SpringUtil.getBean("horario");
		try {
			disciplinaLetivaHorario.setDiaSemana( diaSemana );
			disciplinaLetivaHorario.setDisciplinaLetiva( disciplinaDAO.getDisciplinaLetivaById( Integer.parseInt( disciplinaLetivaId ) ) );
			disciplinaLetivaHorario.setHorario( horarioDAO.getHorarioById( horarioId ) );
		} catch ( IntranetException e ) {
			// TODO Auto-generated catch block
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
	public String getDisciplinaLetivaId() {
		return disciplinaLetivaId;
	}
	public void setDisciplinaLetivaId( String disciplinaLetivaId ) {
		this.disciplinaLetivaId = disciplinaLetivaId;
	}

	

	
}
