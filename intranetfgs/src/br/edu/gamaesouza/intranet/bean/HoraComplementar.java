package br.edu.gamaesouza.intranet.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries(value={
		
		@NamedQuery(name="horasComplementaresByAluno",query="SELECT comp FROM HoraComplementar comp where comp.aluno.id = :aluno"),
		@NamedQuery(name="horasComplementaresByAlunoAtividade",query="SELECT comp FROM HoraComplementar comp  where comp.aluno.id = :aluno AND comp.atividade.id = :atividade"),
		@NamedQuery(name="totalHorasPorAtividade",query="SELECT new br.edu.gamaesouza.intranet.bean.result.HorasAtividadeResultBean(sum(comp.numeroHoras), comp.atividade.nome, comp.atividade.numeroHoras) FROM HoraComplementar comp  where comp.aluno.id = :aluno GROUP by comp.atividade.nome")
		
})
public class HoraComplementar extends Hora {
	
	private String nomeEvento;
	
	private Integer numeroHoras;
	
	@OneToOne
	private Atividade atividade;

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public Integer getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(Integer numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	
}
