package br.edu.gamaesouza.intranet.params.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Hora;
import br.edu.gamaesouza.intranet.bean.HoraComplementar;
import br.edu.gamaesouza.intranet.dao.HoraDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.HoraComplementarParams;
import br.edu.gamaesouza.intranet.utils.DateUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class HoraComplementarNovoParams implements HoraComplementarParams {
	
	private Integer atividadeId;
	private String numeroHoras;
	private String nomeEvento;
	private Aluno aluno;
	
	@Autowired private PessoaDAO pessoaDAO;
	@Autowired private HoraDAO horaDAO;

	public Integer getAtividadeId() {
		return atividadeId;
	}

	public void setAtividadeId(Integer atividadeId) {
		this.atividadeId = atividadeId;
	}

	public String getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(String numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	public Hora getHora() throws IntranetException {
		HoraComplementar horaComplementar = new HoraComplementar();
		horaComplementar.setAluno(pessoaDAO.getAlunoById(aluno.getId()));
		horaComplementar.setAtividade(horaDAO.getAtividadeById(atividadeId));
		horaComplementar.setTitulo(nomeEvento);
		horaComplementar.setMinutos(DateUtil.getMinutesByHourMinutes(numeroHoras));
		return horaComplementar;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	
	
}
