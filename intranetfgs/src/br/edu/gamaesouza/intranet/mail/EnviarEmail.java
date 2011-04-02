package br.edu.gamaesouza.intranet.mail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Arquivo;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.HoraComplementar;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.bean.Rule;
import br.edu.gamaesouza.intranet.dao.DisciplinaDAO;
import br.edu.gamaesouza.intranet.dao.PessoaDAO;
import br.edu.gamaesouza.intranet.security.UserData;
import br.edu.gamaesouza.intranet.utils.ContextUtil;
import br.edu.gamaesouza.intranet.utils.DateUtil;
import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.vo.OuvidoriaVO;

public class EnviarEmail {

	@Autowired
	private DisciplinaDAO disciplinaDAO;
	@Autowired
	private PessoaDAO pessoaDAO;
	private EmailConfiguration configuration;

	public void sendMailToOuvidoria(OuvidoriaVO ouvidoriaVO)
			throws IntranetException {

		configuration = new GmailConfiguration();

		Session session = Session.getInstance(configuration.getConfiguration(),
				configuration.getAuth());

		MimeMessage message = new MimeMessage(session);

		try {

			String ouvidoriaEmail = ServletActionContext.getServletContext()
					.getInitParameter("EmailOuvidoria");

			message.setFrom(new InternetAddress("intranetfgs@gmail.com",
					"Intranet FGS"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					ouvidoriaEmail, "Ouvidoria @intranetfgs"));
		} catch (Exception e) {
			throw new IntranetException(e.getMessage());
		}

		try {
			message.setSubject("Enviado por " + ouvidoriaVO.getNome()
					+ " @intranetfgs");
			message.setContent(
					TemplateManager.getOuvidoriaTemplate(ouvidoriaVO),
					"text/html");

			Transport.send(message);
		} catch (Exception e) {
			throw new IntranetException(e.getMessage());
		}

	}

	public void sendMailToAluno(DisciplinaLetiva dl, String fileName,
			Pessoa professor) throws Throwable {

		configuration = new GmailConfiguration();

		// DisciplinaLetiva disAluno = disciplinaDAO.getAlunosByDL(dl.getId());

		for (Aluno aluno : dl.getAluno()) {

			Session session = Session.getInstance(
					configuration.getConfiguration(), configuration.getAuth());
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("intranetfgs@gmail.com",
					"Intranet FGS"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					aluno.getEmail(), aluno.getNome()));

			String mensagem = "Prezado "
					+ aluno.getNome()
					+ ", <br> o professor "
					+ professor.getNome()
					+ " adicionou um novo documento em sua disciplina "
					+ dl.getDisciplina().getNome()
					+ " intitulado "
					+ fileName
					+ ".<br>"
					+ "Para obter o arquivo acesse a intranet da FGS em um dos computadores da instituição.";

			String emailContent = "<b>Enviado por </b>" + professor.getNome();
			emailContent = emailContent + "<br>";
			emailContent = emailContent + "<br>";
			emailContent = emailContent + "<b>Arquivo: </b>" + fileName;
			emailContent = emailContent + "<br>";
			emailContent = emailContent + "<br>";
			emailContent = emailContent + "<b>Disciplina: </b>"
					+ dl.getDisciplina().getNome();
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
			emailContent = emailContent
					+ "Não responda este email, ele foi gerado automaticamente.";
			emailContent = emailContent + "<br>";
			emailContent = emailContent + "Att. Equipe CQI";

			message.setSubject("Intranet FGS - Novo arquivo em "
					+ dl.getDisciplina().getNome());
			message.setContent(emailContent, "text/html");

			Transport.send(message);
		}

	}

	public void sendArquivoPeloAluno(Arquivo arquivo, String fileName,
			Pessoa aluno) throws Throwable {

		configuration = new GmailConfiguration();
		Session session = Session.getInstance(configuration.getConfiguration(),
				configuration.getAuth());
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("intranetfgs@gmail.com",
				"Intranet FGS"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				aluno.getEmail(), aluno.getNome()));

		String mensagem = "Prezado "
				+ aluno.getNome()
				+ ",<br>Arquivo em anexo da intranet da FGS, enviado de um dos computadores da institui��o.";
		String emailContent = "<b>Enviado por </b>" + aluno.getNome();
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<b>Arquivo: </b>" + fileName;
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + mensagem;
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "<br>";
		emailContent = emailContent
				+ "N�o responda este email, ele foi gerado automaticamente.";
		emailContent = emailContent + "<br>";
		emailContent = emailContent + "Att. Equipe CQI";

		message.setSubject("Intranet FGS - Email com Arquivo ");

		MimeMultipart mpRoot = new MimeMultipart("mixed");
		MimeMultipart mpContent = new MimeMultipart("alternative");
		MimeBodyPart contentPartRoot = new MimeBodyPart();
		contentPartRoot.setContent(mpContent);
		mpRoot.addBodyPart(contentPartRoot);

		// enviando html
		MimeBodyPart mbp2 = new MimeBodyPart();
		mbp2.setContent(emailContent, "text/html");
		mpContent.addBodyPart(mbp2);

		ServletContext sContext = ServletActionContext.getServletContext();
		String diretorio = sContext.getRealPath("/arquivos");

		// enviando anexo
		MimeBodyPart mbp3 = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(diretorio + "\\"
				+ arquivo.getUrl());
		mbp3.setDisposition(Part.ATTACHMENT);
		mbp3.setDataHandler(new DataHandler(fds));
		mbp3.setFileName(arquivo.getUrl());

		mpRoot.addBodyPart(mbp3);

		message.setContent(mpRoot);
		message.saveChanges();

		Transport.send(message);

	}

	public void sendExceptionToAdministrador(Exception rex, Pessoa p)
			throws MessagingException, UnsupportedEncodingException {

		configuration = new GmailConfiguration();

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		rex.printStackTrace(pw);

		if (p == null) {
			p = new Pessoa();
		}

		Session session = Session.getInstance(configuration.getConfiguration(),
				configuration.getAuth());

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("intranetfgs@gmail.com",
				"Intranet FGS"));

		List<String> developers = ContextUtil.getAllDevelopers();
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"intranetfgs@gmail.com", "Intranet FGS"));
		for (String email : developers) {
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(
					email, email));
		}

		String msgBody = "";
		msgBody = msgBody + "<b>Exception Message : </b>" + rex.getMessage()
				+ "<br><br>";
		msgBody = msgBody
				+ "<hr style='border-bottom:1px;border-style:dotted;border-color:#CCCCCC'><center><h3>Dados do Usu�rio</h3></center><hr style='border-bottom:1px;border-style:dotted;border-color:#CCCCCC'><table><tr><td>";
		msgBody = msgBody + "<b>Id: </b></td><td>" + p.getId() + "</td></tr>";
		msgBody = msgBody + "<tr><td><b>Nome: </b></td><td>" + p.getNome()
				+ "</td></tr>";
		msgBody = msgBody + "<tr><td><b>E-mail: </b></td><td>" + p.getEmail()
				+ "</td></tr>";
		msgBody = msgBody + "<tr><td><b>Login: </b></td><td>" + p.getLogin()
				+ "</td></tr>";
		msgBody = msgBody + "<tr><td valign='top'><b>Rules: </b></td><td>";
		for (Rule r : p.getRegras()) {
			msgBody = msgBody + r.getNome() + "<br>";
		}
		msgBody = msgBody + "</td></tr></table><br><br><br>";
		msgBody = msgBody
				+ "<hr style='border-bottom:1px;border-style:dotted;border-color:#CCCCCC'><center><h3>Print Stack Trace</h3></center><hr style='border-bottom:1px;border-style:dotted;border-color:#CCCCCC'> ";

		msgBody = msgBody + sw.toString();
		msgBody = msgBody + "<br></br>";
		msgBody = msgBody + "";
		msgBody = msgBody + "<br></br>";
		msgBody = msgBody + "<br></br>";
		msgBody = msgBody
				+ "Intranet FGS <br></br> Desenvolvido pelo Centro de Qualidade da Informa��o.";

		message.setSubject(rex.getMessage() + " em @intranetfgs");
		message.setContent(msgBody, "text/html");

		Transport.send(message);

	}

	public void sendEmailWithLoginAndPassword(String email)
			throws IntranetException, Exception {

		configuration = new GmailConfiguration();

		Pessoa pessoa = pessoaDAO.getPessoaByEmail(email);

		if (pessoa == null) {
			throw new Exception("E-Mail n�o cadastrado");
		}

		Session session = Session.getInstance(configuration.getConfiguration(),
				configuration.getAuth());

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("intranetfgs@gmail.com",
					"Intranet FGS"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email, "Intranet FGS"));
		} catch (Exception e) {
			throw new IntranetException(e.getMessage());

		}

		String msgBody = "";
		msgBody = msgBody + "Prezado " + pessoa.getNome()
				+ ", segue abaixo seus dados cadastrais.<br><br>";
		msgBody = msgBody + "<b>Login: </b>" + pessoa.getLogin() + "<br>";
		msgBody = msgBody + "<b>Senha: </b>" + pessoa.getSenha() + "<br>";

		msgBody = msgBody + "<br></br>";
		msgBody = msgBody
				+ "Intranet FGS <br></br> Desenvolvido pelo Centro de Qualidade da Informa��o.";

		try {
			message.setSubject("Recuperar Senha @intranetfgs");
			message.setContent(msgBody, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new IntranetException(e.getMessage());
		}

	}

	public void sendComprovanteToAluno(Aluno aluno,
			HoraComplementar horaComplementar) throws IntranetException {

		configuration = new GmailConfiguration();

		Session session = Session.getInstance(configuration.getConfiguration(),
				configuration.getAuth());

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("intranetfgs@gmail.com",
					"Intranet FGS"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					aluno.getEmail(), aluno.getNome()));
		} catch (Exception e) {
			throw new IntranetException(e.getMessage());

		}

		String msgBody = "";

		msgBody = msgBody
				+ "<table border=\"0\" width=\"100%\" bgcolor=\"#FFFAF0\">";
		msgBody = msgBody + "<tr>";
		msgBody = msgBody
				+ "<td colspan=\"1\"><img src=\"http://bancariosrio.org.br/uploads/uploadsFCkEditor/image/gama-e-souza.jpg\" /></td>";
		msgBody = msgBody
				+ "<td><h3 style=\"text-size: 14px;\">Comprovante de Hora Complementar</h3></td>";
		msgBody = msgBody + "<tr>";
		msgBody = msgBody + "<tr>";

		msgBody = msgBody + "<td valign=\"top\">";

		msgBody = msgBody
				+ "<table border=\"1\" cellpadding=\"2\" cellspacing=\"0\" width=\"100%\" style=\"border-style:dotted;\">";
		msgBody = msgBody + "<tr style=\"border-style:dotted;\">";
		msgBody = msgBody
				+ "	<td colspan=\"2\" style=\"border-style:dotted;\">";
		msgBody = msgBody + "	<center><h3>Dados da Hora</h3></center>";
		msgBody = msgBody + "	</td>";
		msgBody = msgBody + "</tr>";
		msgBody = msgBody + "<tr style=\"border-style:dotted;\">";
		msgBody = msgBody
				+ "	<td width=\"30%\" style=\"border-style:dotted;\"><b>T�tulo: </b></td>";
		msgBody = msgBody
				+ "<td width=\"70%\" style=\"border-style:dotted;\">"+ horaComplementar.getTitulo() + "</td>";

		msgBody = msgBody + "	</tr>";
		msgBody = msgBody + "<tr style=\"border-style:dotted;\">";

		msgBody = msgBody
				+ " <td width=\"30%\" style=\"border-style:dotted;\"><b>Qtd. de Horas: </b></td>";
		msgBody = msgBody
				+ "<td width=\"70%\" style=\"border-style:dotted;\">"+ DateUtil.getHourMinutesFormated(horaComplementar.getMinutos()) +"</td>";

		msgBody = msgBody + "</tr>";
		msgBody = msgBody + "<tr style=\"border-style:dotted;\">";
		msgBody = msgBody + "<td width=\"30%\" style=\"border-style:dotted;\">";
		msgBody = msgBody + "	<b>Atividade: </b>";
		msgBody = msgBody + " </td>";
		msgBody = msgBody
				+ "<td width=\"70%\" style=\"border-style:dotted;\">"+ horaComplementar.getAtividade().getNome() +"</td>";
		msgBody = msgBody + "</tr>";

		msgBody = msgBody + "</table>";

		msgBody = msgBody + "</td>";
		msgBody = msgBody + "<td valign=\"top\">";
		msgBody = msgBody
				+ "<table style=\"border-style:dotted;\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">";
		msgBody = msgBody + "	<tr style=\"border-style:dotted;\">";
		msgBody = msgBody + "<td colspan=\"2\" style=\"border-style:dotted;\">";
		msgBody = msgBody + "<center><h3>Dados do Aluno</h3></center>";
		msgBody = msgBody + "</td>";
		msgBody = msgBody + "</tr>";
		msgBody = msgBody + "<tr style=\"border-style:dotted;\">";
		msgBody = msgBody
				+ "<td width=\"30%\"  style=\"border-style:dotted;\"><b>Nome: </b></td>";
		msgBody = msgBody
				+ "<td  width=\"70%\" style=\"border-style:dotted;\">"+ aluno.getNome() +"</td>";

		msgBody = msgBody + "</tr>";
		msgBody = msgBody + "<tr style=\"border-style:dotted;\">";

		msgBody = msgBody
				+ "	<td width=\"30%\"  style=\"border-style:dotted;\"><b>Matr�cula: </b></td>";
		msgBody = msgBody
				+ "<td  width=\"70%\" style=\"border-style:dotted;\">"+aluno.getMatricula()+"</td>";

		msgBody = msgBody + "</tr>";
		msgBody = msgBody + "	<tr style=\"border-style:dotted;\">";
		msgBody = msgBody
				+ "<td width=\"30%\"  style=\"border-style:dotted;\">";
		msgBody = msgBody + "	<b>Per�odo: </b>";
		msgBody = msgBody + "</td>";
		msgBody = msgBody
				+ "<td  width=\"70%\" style=\"border-style:dotted;\">"+aluno.getPeriodo()+"</td>";
		msgBody = msgBody + "</tr>";

		msgBody = msgBody + " <tr style=\"border-style:dotted;\">";
		msgBody = msgBody
				+ " <td width=\"30%\"  style=\"border-style:dotted;\">";
		msgBody = msgBody + "<b>Curso: </b>";
		msgBody = msgBody + " </td>";
		msgBody = msgBody
				+ "<td  width=\"70%\" style=\"border-style:dotted;\">"+aluno.getCurso().getNome()+"</td>";
		msgBody = msgBody + "</tr>";

		msgBody = msgBody + "	</table>";
		msgBody = msgBody + " </td>";

		msgBody = msgBody + "</tr>";
		msgBody = msgBody + " <tr>";
		msgBody = msgBody + "	<td colspan=\"4\">";
		msgBody = msgBody + "	<br></br>";
		msgBody = msgBody
		+ "* Ao assinar este comprovante voc� est� de acordo com os dados contidos no mesmo.<br>";
		msgBody = msgBody
		+ "** Esse comprovante foi gerado diretamente para seu e-mail em "+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) +" pelo professor "+ UserData.getLoggedUser().getNome() +", para ele ter valor � necess�rio que voc� imprima e leve para o coordenador assinar.";
		
		msgBody = msgBody + "</td>";
		msgBody = msgBody + "</tr>";

		msgBody = msgBody + " <tr>";
		msgBody = msgBody + "<td width=\"50%\">";
		msgBody = msgBody + "<br><br><br><br>";
		msgBody = msgBody + "<hr width=\"80%\" style=\"border-style: line;\">";
		msgBody = msgBody + "	<center><b>Assinatura do Aluno</b></center>";
		msgBody = msgBody + "	</td>";
		msgBody = msgBody + "	<td width=\"50%\">";
		msgBody = msgBody + "<br><br><br><br>";
		msgBody = msgBody + "	<hr width=\"80%\">";
		msgBody = msgBody + "	<center><b>Assinatura do Coordenador</b></center>";
		msgBody = msgBody + "	</td>";
		msgBody = msgBody + " </tr>";

		msgBody = msgBody + "</table>";

		try {
			message.setSubject("[Comprovante / Hora Complementar] "
					+ horaComplementar.getTitulo());
			message.setContent(msgBody, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new IntranetException(e.getMessage());
		}

	}

}
