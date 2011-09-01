package br.edu.gamaesouza.intranet.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.gamaesouza.intranet.bean.HoraAEP;
import br.edu.gamaesouza.intranet.bean.HoraComplementar;

public class DateUtil {
	
	public static Integer getMinutesByDateInicioAndDataFim(Date dataInicio,Date dataFim){
		
		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(dataInicio);
		
		Calendar calendarFim = Calendar.getInstance();
		calendarFim.setTime(dataFim);
		
		
		Integer horaInicio = calendarInicio.get(Calendar.HOUR);
		Integer horaFim = calendarFim.get(Calendar.HOUR);
		
		Integer minutoInicio = calendarInicio.get(Calendar.MINUTE);
		Integer minutoFim = calendarFim.get(Calendar.MINUTE);
		
		Integer minutosInicio = (horaInicio * 60) + minutoInicio;
		Integer minutosFim = (horaFim * 60) + minutoFim;
		
		Integer total = minutosFim - minutosInicio;
		
		return total;	
		
	}
	
	public static List<HoraAEP> getDiferencaDatasListAEP(List<HoraAEP> horasAEP){
		for(HoraAEP horaAEP : horasAEP){
			horaAEP.setDifHora(getHourMinutesFormated(getMinutesByDateInicioAndDataFim(horaAEP.getHoraInicio(),horaAEP.getHoraFim())));
		}
		
		return horasAEP;
	}
	
	public static String getHourMinutesFormated(Integer minutos){
		
		if (minutos >= 60){
			Integer horas = minutos / 60;
			minutos = minutos % 60;
			return horas + "h(s) " + minutos + " min(s)";
		}else{
			return minutos + " min(s)";
		}
		
		
	}
	
	public static Integer getMinutesByHourMinutes(String h){
		String[] split = h.split(":");
		
		return (Integer.parseInt(split[0]) * 60) + Integer.parseInt(split[1]);
	}

	public static List<HoraComplementar> getFormatedFields(
			List<HoraComplementar> horasComplementares) {
		for (HoraComplementar h : horasComplementares){
			h.setTotalHoras(getHourMinutesFormated(h.getMinutos()));
		}
		return horasComplementares;
	}
	
	public static String getSomaHorasAEP(List<HoraAEP> aeps){
		Integer total = 0;
		for(HoraAEP aep : aeps){
		
			Calendar calendarInicio = Calendar.getInstance();
			calendarInicio.setTime(aep.getHoraInicio());
			
			Calendar calendarFim = Calendar.getInstance();
			calendarFim.setTime(aep.getHoraFim());
			
			
			Integer horaInicio = calendarInicio.get(Calendar.HOUR);
			Integer horaFim = calendarFim.get(Calendar.HOUR);
			
			Integer minutoInicio = calendarInicio.get(Calendar.MINUTE);
			Integer minutoFim = calendarFim.get(Calendar.MINUTE);
			
			Integer minutosInicio = (horaInicio * 60) + minutoInicio;
			Integer minutosFim = (horaFim * 60) + minutoFim;
			
			total = total + (minutosFim - minutosInicio);
		}
		return getHourMinutesFormated(total);	
		
	}

}
