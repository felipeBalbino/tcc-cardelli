package br.edu.gamaesouza.intranet.mail;

import br.edu.gamaesouza.intranet.vo.OuvidoriaVO;

public class TemplateManager {
	
	public static String getOuvidoriaTemplate(OuvidoriaVO ouvidoriaVO){
		String emailContent = "";
		emailContent = emailContent + "<b>Enviado por </b>" + ouvidoriaVO.getNome();
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<b>E-mail: </b>" + ouvidoriaVO.getEmail();
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<b>Curso: </b>" + ouvidoriaVO.getCurso().getNome();
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<b>Matricula: </b>" + ouvidoriaVO.getMatricula();
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<b>Telefone: </b>" + ouvidoriaVO.getTelefone();
		emailContent = emailContent + "<br></br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + ouvidoriaVO.getMensagem();
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "Att. Equipe CQI";
		return emailContent;
	}
	
}
