package br.edu.gamaesouza.intranet.params.impl;

import java.util.Calendar;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.params.EventoParams;
import br.edu.gamaesouza.intranet.params.Params;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public class EventoNovoParams implements EventoParams {

	private String titulo;
	private String coordenacao;
	private Calendar dataHoraInicio;
	private Calendar dataHoraFim;
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
			evento.setDatahoraInicio(dataHoraInicio);
			evento.setDatahoraFim(dataHoraFim);
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

	public Calendar getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Calendar dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Calendar getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Calendar dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
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

}
