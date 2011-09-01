package br.edu.gamaesouza.intranet.bean.result;

import br.edu.gamaesouza.intranet.utils.DateUtil;

public class HorasAtividadeResultBean {
	
	private Long totalMinutosAluno;
	private String totalHorasAlunoFormatado;
	private Integer totalHorasAtividade;
	private String nomeAtividade;

	public HorasAtividadeResultBean(Long totalHorasAluno, String nomeAtividade,Integer totalHorasAtividade) {
		super();
		this.totalMinutosAluno = totalHorasAluno;
		this.nomeAtividade = nomeAtividade;
		this.totalHorasAtividade = totalHorasAtividade;
		totalHorasAlunoFormatado = DateUtil.getHourMinutesFormated(Integer.parseInt(totalMinutosAluno.toString()));
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	
	

	public Long getTotalMinutosAluno() {
		return totalMinutosAluno;
	}

	public void setTotalMinutosAluno(Long totalMinutosAluno) {
		this.totalMinutosAluno = totalMinutosAluno;
		totalHorasAlunoFormatado = DateUtil.getHourMinutesFormated(Integer.parseInt(totalMinutosAluno.toString()));
	}

	public Integer getTotalHorasAtividade() {
		return totalHorasAtividade;
	}

	public void setTotalHorasAtividade(Integer totalHorasAtividade) {
		this.totalHorasAtividade = totalHorasAtividade;
	}

	public String getTotalHorasAlunoFormatado() {
		return totalHorasAlunoFormatado;
	}

	public void setTotalHorasAlunoFormatado(String totalHorasAlunoFormatado) {
		this.totalHorasAlunoFormatado = totalHorasAlunoFormatado;
	}
	
	
	
}
