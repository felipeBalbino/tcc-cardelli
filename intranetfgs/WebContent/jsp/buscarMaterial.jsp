<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">


function enviaremail(idarquivo) {    
	document.getElementById("mensagem_carregando").style.display = "inline";
    location.href = "buscarMaterial!enviaremail.java?idarquivo="+idarquivo;
    
 
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Buscar Material</title>
</head>
<body>
<b>Buscar Material</b>
<hr></hr>
<div id="search">
<s:form action="/jsp/buscarMaterial!carregaResultado.java">

	<table cellspacing="10">
		<tr>
			<td>Matéria:</td>
			<td><sx:autocompleter headerKey="-1" headerValue=""  id="disciplina" list="disciplinas" name="disciplina" value="disciplina" listKey="id" listValue="nome" /></td>	
			<td>Ano:</td>		
			<td><sx:autocompleter headerKey="-1" headerValue="" id="ano" list="anos" name="ano" /></td>
			<td>Semestre:</td>
			<td><sx:autocompleter headerKey="-1" headerValue="" id="semestre" name="semestre" list="semestres" /></td>
			<!--<td>Turno:</td>
			<td><sx:autocompleter headerKey="-1" headerValue="" autoComplete="true" id="turno" name="turno" list="turnos" value="turno"/></td>
		 	<td>Curso:</td>
			<td><sx:autocompleter headerKey="-1" headerValue="" autoComplete="true" id="curso" name="curso" list="cursos" listKey="nome" listValue="nome" /></td> -->
			<td>Professor:</td>
			<td><sx:autocompleter headerKey="-1" headerValue=""  id="professor" name="professor" list="professores" listKey="id" listValue="nome" /></td>		
			<td colspan="1"><s:submit value="Pesquisar" /></td>
		</tr>
	
	</table>
</s:form>
</div>
<hr></hr>
		<div id="mensagem_carregando" style="display:none;color:red;"><p align="center">O arquivo esta sendo enviado para seu email<br>O tempo de envio é relativo ao tamanho do arquivo.<br><br><img src="../images/load.gif"></p></div>
						
<div id="result" >
	
				

	<table width="100%">
		
	
		<tr>
			<td><b>Nome do Arquivo</b></td>
			<td><b>Tamanho(Kb)</b></td>
			<td><b>Matéria</b></td>
			<td><b>Turno</b></td>
			<td><b>Data de Envio</b></td>
			<td><b>Ano / Semestre.</b></td>
			<td><b>Professor</b></td>
			<td></td>
		
		</tr>
		
	
		<s:actionmessage/>
		<s:actionerror/>
		
		<s:iterator value="arquivos">
		
		
		
			<tr>
				
				<td><s:property value="nome"/></td>
				<td><s:property value="uploadFileSize"/></td>
				<td><s:property value="disciplinaLetiva.disciplina.nome"/></td>
				<td><s:property value="disciplinaLetiva.turno"/></td>
				<td><s:date name="dataEnvio" format="dd/MM/yy HH:mm:ss" /></td>
				<td><s:property value="disciplinaLetiva.ano"/> / <s:property value="disciplinaLetiva.semestre"/></td>
				<td><s:property value="professor.nome"/></td>
				<td><a style="border: 0px;" href="<%= application.getContextPath() %>/arquivos/<s:property value='url' />"><img src="../images/download.png" /></a></td>
				
					<c:if test="${sessionScope.pessoa != null}">
						<td><a style="border:0px;" onClick="enviaremail(<s:property value="id"/>);" ><img src="../images/email.jpg" /></a></td>
					</c:if>
			
			</tr>
		
		
		
		</s:iterator>		
		
	
	
	</table>


</div>
</body>
</html>