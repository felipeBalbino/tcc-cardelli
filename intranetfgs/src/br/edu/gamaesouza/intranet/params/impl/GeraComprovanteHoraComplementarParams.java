package br.edu.gamaesouza.intranet.params.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.HoraComplementar;
import br.edu.gamaesouza.intranet.dao.HoraDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class GeraComprovanteHoraComplementarParams {
	
	private String alunoId;
	private String horaId;
	@Autowired private PessoaDAO pessoaDAO;
	@Autowired private HoraDAO horaDAO;
	
	public Aluno getAluno() throws IntranetException{
		return pessoaDAO.getAlunoById(getAlunoId());
	}
	
	public HoraComplementar getHoraComplementar(){
		return horaDAO.getHoraComplementarById(getHoraId());
	}

	public Integer getAlunoId() {
		return Integer.parseInt(alunoId);
	}

	public void setAlunoId(String alunoId) {
		this.alunoId = alunoId;
	}

	public Integer getHoraId() {
		return Integer.parseInt(horaId);
	}

	public void setHoraId(String horaId) {
		this.horaId = horaId;
	}
	
	
	
}
