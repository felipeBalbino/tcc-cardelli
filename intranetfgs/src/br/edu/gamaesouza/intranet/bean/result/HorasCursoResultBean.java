package br.edu.gamaesouza.intranet.bean.result;

import br.edu.gamaesouza.intranet.utils.DateUtil;

public class HorasCursoResultBean {

	private Long totalMinutosAluno;
	
	private Integer totalHorasCurso;
	
	private String totalHorasAlunoFormatado;
	
	private Long totalHorasAluno;

	public HorasCursoResultBean(Long totalHorasAluno, Integer totalHorasCurso) {
		this.totalMinutosAluno = totalHorasAluno;
		this.totalHorasCurso = totalHorasCurso;
	}

	
	public Long getTotalMinutosAluno() {
		return totalMinutosAluno;
	}


	public void setTotalMinutosAluno(Long totalMinutosAluno) {
		this.totalMinutosAluno = totalMinutosAluno;
		totalHorasAlunoFormatado = DateUtil.getHourMinutesFormated(Integer.parseInt(totalMinutosAluno.toString()));
		this.setTotalHorasAluno(totalMinutosAluno / 60); 
	}


	public Integer getTotalHorasCurso() {
		return totalHorasCurso;
	}

	public void setTotalHorasCurso(Integer totalHorasCurso) {
		this.totalHorasCurso = totalHorasCurso;
	}


	public String getTotalHorasAlunoFormatado() {
		return totalHorasAlunoFormatado;
	}


	public void setTotalHorasAlunoFormatado(String totalHorasAlunoFormatado) {
		this.totalHorasAlunoFormatado = totalHorasAlunoFormatado;
	}


	public Long getTotalHorasAluno() {
		return totalHorasAluno;
	}


	public void setTotalHorasAluno(Long totalHorasAluno) {
		this.totalHorasAluno = totalHorasAluno;
	}
	
	
	
	
	
}
