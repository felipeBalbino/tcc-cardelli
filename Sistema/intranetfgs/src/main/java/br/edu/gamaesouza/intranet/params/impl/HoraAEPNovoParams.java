package br.edu.gamaesouza.intranet.params.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.HoraAEP;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.params.HoraAEPParams;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;

public class HoraAEPNovoParams implements HoraAEPParams {

	@Autowired private Aluno aluno;
	@Autowired private PessoaDAO pessoaDAO;
	private String data;
	private String horaInicial;
	private String horaFinal;
	
	public HoraAEP getHoraAEP() throws ParseException, IntranetException{
		
		HoraAEP horaAEP = (HoraAEP) SpringUtil.getBean("horaAEP");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(data)); 
		horaAEP.setData(cal);
		
		sdf = new SimpleDateFormat("hh:mm:ss");
		horaAEP.setHoraInicio(sdf.parse(horaInicial));
		horaAEP.setHoraFim(sdf.parse(horaFinal));
		
		horaAEP.setAluno(pessoaDAO.getAlunoById(aluno.getId()));
		
		return horaAEP;
		
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
	
}
