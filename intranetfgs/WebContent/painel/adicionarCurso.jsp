<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Adicionar Curso</title>
</head>
<body>
<b>Adicionar Curso</b>
<hr></hr>
<s:actionerror/>
<s:actionmessage/>
<s:form action="/painel/curso!novo.java">

	
	Nome: <s:textfield id="cursoNovoParams.nomeCurso" name="cursoNovoParams.nomeCurso"></s:textfield>

	<br></br>
	<b>Selecione as disciplinas lecionadas neste curso</b>
<hr></hr>

	<select id="cursoNovoParams.disciplinasCurso" name="cursoNovoParams.disciplinasCurso" multiple="multiple" size="10" >
	<s:iterator value="allDisciplinas" status="st" var="disciplina">
	
		<option><s:property value="nome"/></option>
												
	</s:iterator> 
	</select>
	<s:submit align="left"></s:submit>
</s:form>
</body>
</html>