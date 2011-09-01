package br.edu.gamaesouza.intranet.params.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.dao.CursoDAO;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.utils.SpringUtil;
import br.edu.gamaesouza.intranet.vo.OuvidoriaVO;

public class OuvidoriaEmailParams implements br.edu.gamaesouza.intranet.params.OuvidoriaParams{
	
	@Autowired private CursoDAO cursoDAO;
	
	private String nome;
	private String email;
	private String telefone;
	private String mensagem;
	private String matricula;
	private String curso;
	private Integer cursoId;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Integer getCursoId() {
		return cursoId;
	}
	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public OuvidoriaVO getData() throws IntranetException {
		Curso curso = (Curso) SpringUtil.getBean("curso");
		curso = cursoDAO.getCursoById(cursoId);
		
		OuvidoriaVO ouvidoriaVO = (OuvidoriaVO) SpringUtil.getBean("ouvidoriaVO");
		ouvidoriaVO.setCurso(curso);
		ouvidoriaVO.setEmail(email);
		ouvidoriaVO.setMatricula(matricula);
		ouvidoriaVO.setMensagem(mensagem);
		ouvidoriaVO.setNome(nome);
		ouvidoriaVO.setTelefone(telefone);
		
		return ouvidoriaVO;
	}
	
	
	
	
}
