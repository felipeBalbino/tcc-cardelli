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

@Entity
@NamedQueries(value={
		
		@NamedQuery(name="horasAEPByAluno",query="SELECT aep FROM HoraAEP aep where aep.aluno.id = :aluno"),
		@NamedQuery(name="horasAEPByAlunoData",query="SELECT aep FROM HoraAEP aep where aep.aluno.id = :aluno AND data = :data" )
})
public @Data class HoraAEP extends Hora {
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	private Date horaFim;
	
	@Transient
	private String difHora;
		
}
