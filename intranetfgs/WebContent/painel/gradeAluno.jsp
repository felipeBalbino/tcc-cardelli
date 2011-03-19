<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
        <%@ taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Grade</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>

<script type="text/javascript">
 

$(document).ready(function(){ 

$('.conteudo').hide(); 

$('.exibir').each(function(i){    
$(this).click(function(){        
$('.conteudo').each(function(j){            
	if(i == j) 
		$(this).show('slow');        
});    
});
});
$('.ocultar').each(function(i){    
$(this).click(function(){        
$('.conteudo').each(function(j){            
if(i == j) $(this).hide('slow');        
});    
});
});
});

</script>
</head>
<div id="retorno" align="right">
<img src="../images/arrow_categ.gif"><a href="../painel/aluno!lista.java" style="color:#FF4500;text-align:right;border-style:none;">Lista de Alunos</a>
</div>
<body>
						<s:if test="hasActionMessages()">
							<div class="welcome" >
  							 	<s:actionmessage cssStyle="color:green;background-image : url('../images/icon-true.png');background-repeat: no-repeat;padding:3px 0 7px 45px;"/>
  							</div> 			
						</s:if>
						
						<s:if test="hasActionErrors()">
							 <div class="errors">
  							 	<s:actionerror cssStyle="color:red;background-image : url('../images/imgErro.gif');background-repeat: no-repeat;padding:3px 0 7px 45px;"/>
  							</div> 
						</s:if>
<b>Grade</b>
<hr></hr>
<div id="result" >
	<table width="100%" border="1px">
		<tr>
			<td><b>Segunda</b></td>
			<td><b>Terça</b></td>
			<td><b>Quarta</b></td>
			<td><b>Quinta</b></td>
			<td><b>Sexta</b></td>
			<td><b>Sábado</b></td>
			<td><b>Domingo</b></td>
		</tr>
		
		<s:iterator value="disciplinasLetivas" var="disciplinaLetiva">
			<s:iterator value="horarios" var="horario">
				<tr>
					<td>
						<c:if test="${DiaSemana == 'SEGUNDA'}">
							<s:property  value="disciplina.nome" /><br>
							<hr></hr>
							Professor: <s:property  value="disciplinaLetiva.professor.nome" /><br>
							<s:date format="HH:mm" name="horario.horaInicio" /> <br>
							<s:date format="HH:mm" name="horario.horaFim" /><br>
							<hr></hr>
							Sala: <s:property  value="disciplinaLetiva.sala" /><br>
						</c:if>
							<c:if test="${DiaSemana != 'SEGUNDA'}">
								N/A
							</c:if>
					</td>
					<td>
						<c:if test="${DiaSemana == 'TERCA'}">
							<s:property  value="disciplina.nome" /><br>
							<hr></hr>
							Professor: <s:property  value="disciplinaLetiva.professor.nome" /><br>
							<s:date format="HH:mm" name="horario.horaInicio" /> <br>
							<s:date format="HH:mm" name="horario.horaFim" /><br>
							<hr></hr>
							Sala: <s:property  value="disciplinaLetiva.sala" /><br>
						</c:if>
							<c:if test="${DiaSemana != 'TERCA'}">
								N/A
							</c:if>
						
					</td>
					<td>
						<c:if test="${DiaSemana == 'QUARTA'}">
							<s:property  value="disciplina.nome" /><br>
							<hr></hr>
							Professor: <s:property  value="disciplinaLetiva.professor.nome" /><br>
							<s:date format="HH:mm" name="horario.horaInicio" /> <br>
							<s:date format="HH:mm" name="horario.horaFim" /><br>
							<hr></hr>
							Sala: <s:property  value="disciplinaLetiva.sala" /><br>
						</c:if>
							<c:if test="${DiaSemana != 'QUARTA'}">
								N/A
							</c:if>
					</td>
					<td>
						<c:if test="${DiaSemana == 'QUINTA'}">
							<s:property  value="disciplina.nome" /><br>
							<hr></hr>
							Professor: <s:property  value="disciplinaLetiva.professor.nome" /><br>
							<s:date format="HH:mm" name="horario.horaInicio" /> <br>
							<s:date format="HH:mm" name="horario.horaFim" /><br>
							<hr></hr>
							Sala: <s:property  value="disciplinaLetiva.sala" /><br>
						</c:if>
							<c:if test="${DiaSemana != 'QUINTA'}">
								N/A
							</c:if>
					</td>
					<td>
						<c:if test="${DiaSemana == 'SEXTA'}">
							<s:property  value="disciplina.nome" /><br>
							<hr></hr>
							Professor: <s:property  value="disciplinaLetiva.professor.nome" /><br>
							<s:date format="HH:mm" name="horario.horaInicio" /> <br>
							<s:date format="HH:mm" name="horario.horaFim" /><br>
							<hr></hr>
							Sala: <s:property  value="disciplinaLetiva.sala" /><br>
						</c:if>
							<c:if test="${DiaSemana != 'SEXTA'}">
								N/A
							</c:if>
					</td>
					<td>
						<c:if test="${DiaSemana == 'SABADO'}">
							<s:property  value="disciplina.nome" /><br>
							<hr></hr>
							Professor: <s:property  value="disciplinaLetiva.professor.nome" /><br>
							<s:date format="HH:mm" name="horario.horaInicio" /> <br>
							<s:date format="HH:mm" name="horario.horaFim" /><br>
							<hr></hr>
							Sala: <s:property  value="disciplinaLetiva.sala" /><br>
						</c:if>
							<c:if test="${DiaSemana != 'SABADO'}">
								N/A
							</c:if>
					</td>
					<td>
						<c:if test="${DiaSemana == 'DOMINGO'}">
							<s:property  value="disciplina.nome" /><br>
							<hr></hr>
							Professor: <s:property  value="disciplinaLetiva.professor.nome" /><br>
							<s:date format="HH:mm" name="horario.horaInicio" /> <br>
							<s:date format="HH:mm" name="horario.horaFim" /><br>
							<hr></hr>
							Sala: <s:property  value="disciplinaLetiva.sala" /><br>
							
						</c:if>
							<c:if test="${DiaSemana != 'DOMINGO'}">
								N/A
							</c:if>
					</td>
				</tr>
				

					
						
			</s:iterator>
		</s:iterator>
	</table>
</div>
</html>