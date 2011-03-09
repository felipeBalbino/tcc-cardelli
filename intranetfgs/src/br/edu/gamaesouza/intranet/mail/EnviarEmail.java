package br.edu.gamaesouza.intranet.mail;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Arquivo;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.bean.Rule;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.utils.ContextUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.vo.OuvidoriaVO;

public class EnviarEmail {

	@Autowired private DisciplinaDAO disciplinaDAO;	
	private EmailConfiguration configuration;
	
	public void sendMailToOuvidoria(OuvidoriaVO ouvidoriaVO) throws IntranetException{

		configuration = new GmailConfiguration();
		
		Session session = Session.getInstance(configuration.getConfiguration(), configuration.getAuth());

		MimeMessage message = new MimeMessage(session);

		try {
			
			String ouvidoriaEmail = ServletActionContext.getServletContext().getInitParameter("EmailOuvidoria");
			
			message.setFrom(new InternetAddress("intranetfgs@gmail.com", "Intranet FGS"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					ouvidoriaEmail, "Ouvidoria @intranetfgs"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
				
		try {
			message.setSubject("Enviado por " + ouvidoriaVO.getNome() + " @intranetfgs");
			message.setContent(TemplateManager.getOuvidoriaTemplate(ouvidoriaVO), "text/html");

			
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
			
		
	    
	}
	

	public void sendMailToAluno(DisciplinaLetiva dl, String fileName, Pessoa professor) throws Throwable{

		configuration = new GmailConfiguration();
		
			DisciplinaLetiva disAluno = disciplinaDAO.getAlunosByDL(dl.getId());
			
			for(Aluno aluno : disAluno.getAluno()){

				Session session = Session.getInstance(configuration.getConfiguration(), configuration.getAuth());
				System.out.println(1);
				MimeMessage message = new MimeMessage(session);
				System.out.println(2); 	
				message.setFrom(new InternetAddress("intranetfgs@gmail.com", "Intranet FGS"));
				System.out.println(3);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(
						aluno.getEmail(), aluno.getNome()));
	
				
				String mensagem = "Prezado "+aluno.getNome()
					+ ", <br> o professor " + professor.getNome() 
					+ " adicionou um novo documento em sua disciplina " +  dl.getDisciplina().getNome() 
					+ " intitulado " + fileName +".<br>" 
					+ "Para obter o arquivo acesse a intranet da FGS em um dos computadores da instituição.";
				
				String emailContent = "<b>Enviado por </b>" + professor.getNome();
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<b>Arquivo: </b>" + fileName;
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<b>Disciplina: </b>" + dl.getDisciplina().getNome();
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<b>Ao aluno: </b>" + aluno.getNome();
				emailContent = emailContent + "<br></br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + mensagem;
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "Não responda este email, ele foi gerado automaticamente.";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "Att. Equipe CQI";
					
					
				message.setSubject("Intranet FGS - Novo arquivo em " + dl.getDisciplina().getNome());
				message.setContent(emailContent, "text/html");
	
				
				Transport.send(message);
			}
	    
	}
	
	
	public void sendArquivoPeloAluno( Arquivo arquivo, String fileName, Pessoa aluno) throws Throwable{

		configuration = new GmailConfiguration();
	
				Session session = Session.getInstance(configuration.getConfiguration(), configuration.getAuth());				
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("intranetfgs@gmail.com", "Intranet FGS"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(aluno.getEmail(), aluno.getNome()));
	
				
				String mensagem = "Prezado "+aluno.getNome()
					+ "Arquivo da intranet da FGS, enviado de um dos computadores da institui�aso.";
				String emailContent = "<b>Enviado por </b>" + aluno.getNome();
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<b>Arquivo: </b>" + fileName;
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + mensagem;
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "N�o responda este email, ele foi gerado automaticamente.";
				emailContent = emailContent + "<br>";
				emailContent = emailContent + "Att. Equipe CQI";
					
				// Cria um novo objeto para cada arquivo, e anexa o arquivo
					            
					            FileDataSource fds =   new FileDataSource("/arquivos/"+arquivo.getUrl());
					            message.setDataHandler(new DataHandler(fds));
					            message.setFileName(fds.getName());
					 
				 
				message.setSubject("Intranet FGS - Email com Arquivo ");
				message.setContent(emailContent, "text/html");
	
				
				Transport.send(message);
			
	    
	}

	public void sendExceptionToAdministrador(Exception rex,Pessoa p) throws MessagingException, UnsupportedEncodingException {
		
		configuration = new GmailConfiguration();

		 StringWriter sw = new StringWriter();
		 PrintWriter pw = new PrintWriter(sw);
		 rex.printStackTrace(pw);
		 
		 if (p == null){
			 p = new Pessoa();
		 }

		Session session = Session.getInstance(configuration.getConfiguration(), configuration.getAuth());

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("intranetfgs@gmail.com", "Intranet FGS"));
		
		List<String> developers = ContextUtil.getAllDevelopers();
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"intranetfgs@gmail.com", "Intranet FGS"));
		for (String email : developers){
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(
					email, email));
		}
		
		String msgBody = "";
		msgBody = msgBody + "<b>Exception Message : </b>"+ rex.getMessage() + "<br><br>" ;
		msgBody =  msgBody +  "<hr style='border-bottom:1px;border-style:dotted;border-color:#CCCCCC'><center><h3>Dados do Usu�rio</h3></center><hr style='border-bottom:1px;border-style:dotted;border-color:#CCCCCC'><table><tr><td>";
		msgBody =  msgBody + "<b>Id: </b></td><td>" + p.getId() + "</td></tr>" ;
		msgBody =  msgBody + "<tr><td><b>Nome: </b></td><td>" + p.getNome() + "</td></tr>" ;
		msgBody =  msgBody + "<tr><td><b>E-mail: </b></td><td>" + p.getEmail() + "</td></tr>" ;
		msgBody =  msgBody + "<tr><td><b>Login: </b></td><td>" + p.getLogin() + "</td></tr>" ;
		msgBody =  msgBody + "<tr><td valign='top'><b>Rules: </b></td><td>" ;
		for (Rule r : p.getRegras()){
			msgBody =  msgBody + r.getNome() + "<br>";
		}
		msgBody =  msgBody + "</td></tr></table><br><br><br>" ;
        msgBody = msgBody + "<hr style='border-bottom:1px;border-style:dotted;border-color:#CCCCCC'><center><h3>Print Stack Trace</h3></center><hr style='border-bottom:1px;border-style:dotted;border-color:#CCCCCC'> ";
        
        msgBody = msgBody + sw.toString();
        msgBody = msgBody + "<br></br>"; 
        msgBody = msgBody + "";
        msgBody = msgBody + "<br></br>";
        msgBody = msgBody + "<br></br>";
        msgBody = msgBody + "Intranet FGS <br></br> Desenvolvido pelo Centro de Qualidade da Informa��o.";
		
			
			
		message.setSubject(rex.getMessage() + " em @intranetfgs");
		message.setContent(msgBody, "text/html");

		
		Transport.send(message);
		
	}
	
	
	

}
