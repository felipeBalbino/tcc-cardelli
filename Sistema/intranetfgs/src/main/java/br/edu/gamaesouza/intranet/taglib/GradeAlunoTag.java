package br.edu.gamaesouza.intranet.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.Horario;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.HorarioDAO;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class GradeAlunoTag extends TagSupport{


	@Autowired HorarioDAO horarioDAO = new HorarioDAO();
	@Autowired DisciplinaDAO disciplinaDAO;
	
	private List<Horario> horarios;
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
			horarios = horarioDAO.getAllHorarios(ano, semestre);
			String html = "<table>";
			html = html + 	"<tr>";
			html = html +   "<td>Horário</td>";
			html = html +   "<td>SEGUNDA</td>";
			html = html +   "<td>TERÇA</td>";
			html = html +   "<td>QUARTA</td>";
			html = html +   "<td>QUINTA</td>";
			html = html +   "<td>SEXTA</td>";
			html = html + 	"</tr>";
			
			for (Horario horario : horarios){
				
				html = html + 	"<tr>";
				html = html +   "<td>"+horario.getHoraInicio() +" à " +horario.getHoraFim()+"</td>";
				html = html +   "<td>SEGUNDA</td>";
				html = html +   "<td>TERÇA</td>";
				html = html +   "<td>QUARTA</td>";
				html = html +   "<td>QUINTA</td>";
				html = html +   "<td>SEXTA</td>";
				html = html + 	"</tr>";

			}
			
			try {
				pageContext.getOut().write(html);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return super.doStartTag();
		} catch (IntranetException e) {
			
			return 1;
		}
		
		
	}
	
	
	private Integer idAluno;
	private Integer ano;
	private Integer semestre;
	private Integer turno;
	
	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer id) {
		this.idAluno = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}
	
	
}
