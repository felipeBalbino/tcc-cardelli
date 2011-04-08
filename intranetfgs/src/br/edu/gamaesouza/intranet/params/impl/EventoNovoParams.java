package br.edu.gamaesouza.intranet.params.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.params.EventoParams;
import br.edu.gamaesouza.intranet.params.Params;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public class EventoNovoParams implements EventoParams {

	private String titulo;
	private String coordenacao;
	
	private Calendar dataInicio;
	private Calendar dataFim;
	private Date horaInicio;
	private Date horaFim;
	
	private String local;
	private String publicoAlvo;
	private String observacoes;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Evento getEvento(){
		Evento evento = (Evento) SpringUtil.getBean("evento");
			evento.setTitle(titulo);
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(String coordenacao) {
		this.coordenacao = coordenacao;
	}



	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
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
