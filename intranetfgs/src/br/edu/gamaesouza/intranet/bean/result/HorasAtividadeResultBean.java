package br.edu.gamaesouza.intranet.bean.result;

public class HorasAtividadeResultBean {
	
	private Long totalHorasAluno;
	private Integer totalHorasAtividade;
	private String nomeAtividade;

	public HorasAtividadeResultBean(Long totalHorasAluno, String nomeAtividade,Integer totalHorasAtividade) {
		super();
		this.totalHorasAluno = totalHorasAluno;
		this.nomeAtividade = nomeAtividade;
		this.totalHorasAtividade = totalHorasAtividade;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public Long getTotalHorasAluno() {
		return totalHorasAluno;
	}

	public void setTotalHorasAluno(Long totalHorasAluno) {
		this.totalHorasAluno = totalHorasAluno;
	}

	public Integer getTotalHorasAtividade() {
		return totalHorasAtividade;
	}

	public void setTotalHorasAtividade(Integer totalHorasAtividade) {
		this.totalHorasAtividade = totalHorasAtividade;
	}
	
	
	
}
