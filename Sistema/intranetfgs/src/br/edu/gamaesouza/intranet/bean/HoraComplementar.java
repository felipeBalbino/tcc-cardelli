package br.edu.gamaesouza.intranet.bean;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import br.edu.gamaesouza.intranet.utils.DateUtil;

@Entity
@NamedQueries(value={
		
		@NamedQuery(name="horasComplementaresByAluno",query="SELECT comp FROM HoraComplementar comp where comp.aluno.id = :aluno"),
		@NamedQuery(name="horasComplementaresByAlunoAtividade",query="SELECT comp FROM HoraComplementar comp  where comp.aluno.id = :aluno AND comp.atividade.id = :atividade"),
		@NamedQuery(name="totalHorasPorAtividade",query="SELECT new br.edu.gamaesouza.intranet.bean.result.HorasAtividadeResultBean(sum(comp.minutos), comp.atividade.nome, comp.atividade.numeroHoras) FROM HoraComplementar comp  where comp.aluno.id = :aluno GROUP by comp.atividade.nome"),
		@NamedQuery(name="horaComplementarByHora",query="SELECT comp FROM HoraComplementar comp  where comp.aluno.id = :aluno AND comp.titulo = :titulo AND comp.minutos = :minutos")

		
})
public class HoraComplementar extends Hora {
	
	@Getter @Setter
	private String titulo;
	
	@Getter @Setter
	private Integer minutos;
	
	@Getter @Setter
	@Transient private String totalHoras;
	
	@OneToOne
	@Getter @Setter
	private Atividade atividade;
	
}
