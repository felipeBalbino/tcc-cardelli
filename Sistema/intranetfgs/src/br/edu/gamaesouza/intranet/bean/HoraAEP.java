package br.edu.gamaesouza.intranet.bean;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQueries(value={
		
		@NamedQuery(name="horasAEPByAluno",query="SELECT aep FROM HoraAEP aep where aep.aluno.id = :aluno"),
		@NamedQuery(name="horasAEPByAlunoData",query="SELECT aep FROM HoraAEP aep where aep.aluno.id = :aluno AND data = :data" )
})
public class HoraAEP extends Hora {
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Calendar data;
	
	@Temporal(TemporalType.TIME)
	@Getter @Setter
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	@Getter @Setter
	private Date horaFim;
	
	@Transient
	@Getter @Setter
	private String difHora;
		
}
