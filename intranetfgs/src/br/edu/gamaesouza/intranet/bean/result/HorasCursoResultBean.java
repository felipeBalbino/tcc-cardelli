package br.edu.gamaesouza.intranet.bean.result;

public class HorasCursoResultBean {

	private Long totalHorasAluno;
	
	private Integer totalHorasCurso;

	public HorasCursoResultBean(Long totalHorasAluno, Integer totalHorasCurso) {
		this.totalHorasAluno = totalHorasAluno;
		this.totalHorasCurso = totalHorasCurso;
	}

	public Long getTotalHorasAluno() {
		return totalHorasAluno;
	}

	public void setTotalHorasAluno(Long totalHorasAluno) {
		this.totalHorasAluno = totalHorasAluno;
	}

	public Integer getTotalHorasCurso() {
		return totalHorasCurso;
	}

	public void setTotalHorasCurso(Integer totalHorasCurso) {
		this.totalHorasCurso = totalHorasCurso;
	}
	
	
	
	
}
