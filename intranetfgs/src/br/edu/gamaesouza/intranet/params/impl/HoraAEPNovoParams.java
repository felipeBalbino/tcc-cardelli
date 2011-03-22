package br.edu.gamaesouza.intranet.params.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.edu.gamaesouza.intranet.bean.HoraAEP;
import br.edu.gamaesouza.intranet.params.HoraAEPParams;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public class HoraAEPNovoParams implements HoraAEPParams {

	private String data;
	private String horaInicial;
	private String horaFinal;
	
	public HoraAEP getHoraAEP() throws ParseException{
		
		HoraAEP horaAEP = (HoraAEP) SpringUtil.getBean("horaAEP");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(data)); 
		horaAEP.setData(cal);
		
		sdf = new SimpleDateFormat("hh:mm:ss");
		horaAEP.setHoraInicio(sdf.parse(horaInicial));
		horaAEP.setHoraFim(sdf.parse(horaFinal));
		
		return horaAEP;
		
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	
}
