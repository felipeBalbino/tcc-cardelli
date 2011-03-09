<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Adicionar Disciplina</title>
</head>
<body>
<b>Adicionar Disciplina</b>
<hr></hr>
<s:actionmessage/>
<s:form action="/painel/materia!save.java" method="post">
Disciplina: <s:textfield name="materia.nome" cssStyle="width:200px"></s:textfield>
<br></br>
<b>Selecione os cursos que possuem esta disciplina</b>
<hr></hr>

	<select id="cursosParam" name="cursosParam" multiple="multiple" size="10" >
	<s:iterator value="cursos" status="st" var="curso">
	
		<option><s:property value="nome"/></option>
												
	</s:iterator> 
	</select>


<s:submit title="Cadastrar Matéria" value="Cadastrar Matéria"></s:submit>
</s:form>
</body>
</html>