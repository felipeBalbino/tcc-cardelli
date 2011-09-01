<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@ taglib uri="/struts-dojo-tags" prefix="sx" %>
    <%@ taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | HorÃ¡rios</title>

</head>
<body>
<div id="retorno" align="right">
<img src="../images/arrow_categ.gif"><a href="../painel/letiva!lista.java" style="color:#FF4500;text-align:right;border-style:none;">Lista de Disciplinas Letivas</a>
</div>
<b>Vincular HorÃ¡rio em Disciplina - <b style="color: #FF4500"><s:property  value="%{disciplinaLetiva.disciplina.nome}"/> </b>

<hr></hr>
<s:form method="POST" action="/painel/horario!saveHorariosEmDisciplinaLetiva.java">
	<table>
		<tr>
			<td><s:hidden value="%{idDisciplinaLetiva}" name="idDisciplinaLetiva" /></td>
			<td>HorÃ¡rios:</td>		
			<td><sx:autocompleter  id="horario" name="horarioId" list="horarios" listKey="id" /></td>
			<td>Dia da Semana:</td>
			<td><sx:autocompleter   id="diaSemanaEnum" list="diasSemana"  name="diaSemana" /></td>
			<td><s:submit value="Adicionar"></s:submit></td>
		</tr>
	</table>  
</s:form>
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
<b>HorÃ¡rios cadastrados</b>
<hr></hr>
<div id="result" >
	<table width="100%">
		<tr>
			<td><b>Dia da Semana</b></td>
			<td><b>Hora Inicio</b></td>
			<td><b>Hora Final</b></td>
			<td><b>Deletar</b></td>
		</tr>
		
		<s:iterator value="disciplinaLetivaHorario" >
			<tr>
				<td><s:property value="%{disciplinaLetivaHorarioPK.DiaSemana}"/></td>
				<td><s:property value="disciplinaLetivaHorarioPK.horario.horaInicio"/></td>
				<td><s:property value="disciplinaLetivaHorarioPK.horario.horaFim"/></td>
				<td><a onclick="javascript:deletarHorarios('<s:property value='id' />','${idDisciplinaLetiva}',<s:property value='disciplinaLetivaHorarioPK.horario.id' />,<s:property value='disciplinaLetivaHorarioPK.disciplinaLetiva.id' />)" >
				<img src="../images/lixeira.gif" /></a></td>
			</tr>
		</s:iterator>
	</table>
</div>
</body>
</html>