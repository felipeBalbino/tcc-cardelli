package br.edu.gamaesouza.intranet.bean;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQueries(value={
		
		@NamedQuery(name="horasAEPByAluno",query="SELECT aep FROM HoraAEP aep where aep.aluno.id = :aluno"),
		@NamedQuery(name="horasAEPByAlunoData",query="SELECT aep FROM HoraAEP aep where aep.aluno.id = :aluno AND data = :data" )
})
public class HoraAEP extends Hora {
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	private Date horaFim;
	
	@Transient
	private String difHora;
	
	public String getDifHora() {
		return difHora;
	}

	public void setDifHora(String difHora) {
		this.difHora = difHora;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	
	
}
