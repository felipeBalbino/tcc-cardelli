package br.edu.gamaesouza.intranet.bean;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries(value={
		
		@NamedQuery(name="horasAEPByAluno",query="SELECT aep FROM HoraAEP aep where aep.aluno.id = :aluno"),
		@NamedQuery(name="horasAEPByAlunoAnoSemestre",query="SELECT aep FROM HoraAEP aep where aep.aluno.id = :aluno AND ano = :ano AND semestre = :semestre")
		
})
public class HoraAEP extends Hora {
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataFim;
	
	private Integer periodo;
	
	private Integer ano;


	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}



	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getPeriodo() {
		return periodo;
	}
	
}
