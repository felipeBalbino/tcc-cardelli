package br.edu.gamaesouza.intranet.params.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.dao.EventoDAO;
import br.edu.gamaesouza.intranet.params.EventoParams;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public class EventoAlteraParams implements EventoParams{

	private Integer id;
	private String title;
	private String coordenacao;
	private Calendar dataInicio;
	private Calendar dataFim;
	private Date horaInicio;
	private Date horaFim;
	private String local;
	private String publicoAlvo;
	private String observacoes;
	
	@Autowired EventoDAO eventoDAO;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Evento getEvento(){
		Evento evento = (Evento) SpringUtil.getBean("evento");
			evento.setId(id);
			evento.setTitle(title);
			evento.setCoordenacao(coordenacao);
			evento.setDataInicio(dataInicio);
			evento.setDataFim(dataFim);
			evento.setHoraInicio(horaInicio);
			evento.setHoraFim(horaFim);
			evento.setLocal(local);
			evento.setPublicoalvo(publicoAlvo);
			evento.setObs(observacoes);
			evento.setAutor((Professor)UserData.getLoggedUser());
			evento.setDatahoraPublicacao(Calendar.getInstance());
		return evento;
	}

	public void setHoraInicio(String horaInicio) {
		 DateFormat formatter = new SimpleDateFormat("hh:mm:ss");  
		 try {
			 this.horaInicio = (Date)formatter.parse(horaInicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void setHoraFim(String horaFim) {
		 DateFormat formatter = new SimpleDateFormat("hh:mm:ss");  
		 try {
			this.horaFim = (Date)formatter.parse(horaFim);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
