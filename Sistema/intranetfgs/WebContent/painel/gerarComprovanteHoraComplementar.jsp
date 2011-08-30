<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css"  media="print">
   #enviarEmail, #imprimir, #imprimir, #table,  #table2, #footer, #retorno, #alunoscadastrados{display:none;}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet - Faculdade Gama & Souza | Gerar Comprovante de Horas</title>
</head>
<body>
<div id="retorno" align="right">
		<img src="../images/arrow_categ.gif" />
		<a href="../painel/aluno!lista.java" style="color:#FF4500;text-align:left;border-style:none;">Lista de Alunos</a>
	</div>
<div id="comprovante" style="boder:1px;">
<div id="opcoes" style="text-align:right;">

<a tyle="border: 0px;" onClick="window.print()" id="imprimir">
	<img src="../images/imprimir.gif" /> 
</a>

<a id="enviarEmail" style="border: 0px;" href="hora!enviarComprovanteEmail.java?geraComprovanteHoraComplementarParams.alunoId=<s:property value="aluno.id"/>&geraComprovanteHoraComplementarParams.horaId=<s:property value="horaComplementar.id"/>"> 
	<img style="border: 0px;" src="../images/email.gif" />
</a>
  				</div><br>
  						<s:if test="hasActionMessages()">
				<div class="welcome" >
  					<s:actionmessage cssStyle="color:green;background-image : url('../images/icon-true.png');background-repeat: no-repeat;padding:3px 0 7px 45px;"/><br>
						</div> 			
			</s:if>
						
			<s:if test="hasActionErrors()">
				<div class="errors">
  					<s:actionerror cssStyle="color:red;background-image : url('../images/imgErro.gif');background-repeat: no-repeat;padding:3px 0 7px 45px;"/>
  				</div> 
			</s:if>
<table border="0" width="100%" bgcolor="#FFFAF0">
	<tr>
	
		<td colspan=1><img src="../images/logoComprovante.jpg" /></td>
		<td><h2 style="text-size: 14px;">Comprovante de Hora Complementar</h2></td>
		
	<tr>
	<tr>
	
		<td valign="top">
		
			<table border="1" cellpadding="2" cellspacing="0" width="100%" style="border-style:dotted;">
				<tr style="border-style:dotted;">
					<td colspan="2" style="border-style:dotted;">
						<center><h3>Dados da Hora</h3></center>
					</td>
				</tr>
				<tr style="border-style:dotted;">
					<td width="30%" style="border-style:dotted;"><b>Título: </b></td>
					<td width="70%" style="border-style:dotted;"><s:property value="horaComplementar.titulo" /></td>
					
				</tr>
				<tr style="border-style:dotted;">
				
					<td width="30%" style="border-style:dotted;"><b>Qtd. de Horas: </b></td>
					<td width="70%" style="border-style:dotted;"><s:property value="horaComplementar.totalHoras" /></td>
					
				
				</tr>
				<tr style="border-style:dotted;">
				<td width="30%" style="border-style:dotted;">
					<b>Atividade: </b>
				</td>
				<td width="70%" style="border-style:dotted;"><s:property value="horaComplementar.atividade.nome" /></td>
				</tr>
				
			</table>
		
		</td>
	<td valign="top">
		<table style="border-style:dotted;" border="1" cellpadding="2" cellspacing="0" width="100%">
				<tr style="border-style:dotted;">
					<td colspan="2" style="border-style:dotted;">
						<center><h3>Dados do Aluno</h3></center>
					</td>
				</tr>
				<tr style="border-style:dotted;">
					<td width="30%"  style="border-style:dotted;"><b>Nome: </b></td>
					<td  width="70%" style="border-style:dotted;"><s:property value="%{aluno.nome}" /></td>
					
				</tr>
				<tr style="border-style:dotted;">
				
					<td width="30%"  style="border-style:dotted;"><b>Matrícula: </b></td>
					<td  width="70%" style="border-style:dotted;"><s:property value="aluno.matricula" /></td>
					
				
				</tr>
				<tr style="border-style:dotted;">
				<td width="30%"  style="border-style:dotted;">
					<b>Período: </b>
				</td>
				<td  width="70%" style="border-style:dotted;"><s:property value="aluno.periodo" /></td>
				</tr>
				
				<tr style="border-style:dotted;">
				<td width="30%"  style="border-style:dotted;">
					<b>Curso: </b>
				</td>
				<td  width="70%" style="border-style:dotted;"><s:property value="aluno.curso.nome" /></td>
				</tr>
				
			</table>
	</td>

</tr>
<tr>
	<td colspan="4">
	<br></br>
		* Ao assinar este comprovante você está de acordo com os dados contidos no mesmo.
	</td>
</tr>

<tr>
	<td width="50%">
	<br><br><br><br>
		<hr width="80%" style="border-style: line;">
		<center><b>Assinatura do Aluno</b></center>
	</td>
	<td width="50%">
	<br><br><br><br>
		<hr width="80%">
		<center><b>Assinatura do Coordenador</b></center>
	</td>
</tr>

</table>
</div>
</body>
</html>