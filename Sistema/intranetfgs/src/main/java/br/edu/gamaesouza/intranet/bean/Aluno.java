package br.edu.gamaesouza.intranet.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import br.edu.gamaesouza.intranet.utils.DiaSemanaEnum;
import br.edu.gamaesouza.intranet.utils.StatusMatriculaEnum;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="alunosByCurso",query="FROM Aluno a where a.curso.id = :curso"),
		@NamedQuery(name="alunoById",query="FROM Aluno WHERE id = :id"),
		@NamedQuery(name="totalHorasCursoEAluno",query="SELECT DISTINCT new br.edu.gamaesouza.intranet.bean.result.HorasCursoResultBean(sum(comp.minutos), comp.aluno.curso.cargaHorariaComplementar) FROM HoraComplementar comp  where comp.aluno.id = :aluno GROUP BY comp.aluno.id")
})
public class Aluno extends Pessoa implements Serializable {
	
	@OneToOne @Getter @Setter
	private Curso curso;

	@OneToMany @Getter @Setter
	private List<Hora> horas;
	
	@Getter @Setter
	private Integer periodo;
	
	@Enumerated(EnumType.STRING) @Getter @Setter
	private StatusMatriculaEnum statusMatricula;

	

}
