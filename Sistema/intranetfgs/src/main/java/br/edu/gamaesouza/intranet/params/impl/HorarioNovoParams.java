package br.edu.gamaesouza.intranet.params.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import br.edu.gamaesouza.intranet.bean.Horario;
import br.edu.gamaesouza.intranet.params.HorarioParams;
import br.edu.gamaesouza.intranet.utils.SpringUtil;



public class HorarioNovoParams implements HorarioParams {

	private String horaInicio;
	private String horaFim;
	private Integer semestre;
	private Integer ano;


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	public Horario getHorario(){
		Horario horario = (Horario) SpringUtil.getBean("horario");
		try {
			horario.setSemestre(semestre);
			SimpleDateFormat formHora = new SimpleDateFormat("HH:mm:ss");  
			horario.setHoraInicio(formHora.parse(horaInicio));
			horario.setHoraFim(formHora.parse(horaFim));
			horario.setAno(ano);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return horario;
		
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

	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	
	
}
