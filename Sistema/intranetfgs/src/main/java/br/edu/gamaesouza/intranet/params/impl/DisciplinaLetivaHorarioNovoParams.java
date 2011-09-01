package br.edu.gamaesouza.intranet.params.impl;


import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetivaHorario;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.HorarioDAO;
import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;



public @Data class DisciplinaLetivaHorarioNovoParams implements DisciplinaLetivaHorarioParams {

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
			disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().setDiaSemana( diaSemana );
			disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().setDisciplinaLetiva( disciplinaDAO.getDisciplinaLetivaById( disciplinaLetivaId )  );
			disciplinaLetivaHorario.getDisciplinaLetivaHorarioPK().setHorario( horarioDAO.getHorarioById( horarioId ) );
		} catch ( IntranetException e ) {
			e.printStackTrace();
		}

		return disciplinaLetivaHorario;
		
	}
}
