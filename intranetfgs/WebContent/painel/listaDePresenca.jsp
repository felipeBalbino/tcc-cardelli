<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
        <%@ taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css" media="print">
   #result{display:none}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Imprimir lista de presença do Aluno</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
</head>
<body>
<div id="retorno" align="right">
<img src="../images/arrow_categ.gif"><a href="../painel/letiva!lista.java" style="color:#FF4500;text-align:right;border-style:none;">Lista de Disciplinas Letivas</a>
</div>
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
 
<b>Alunos cadastrados</b>
<hr></hr>
<div id="result" >
	<table width="100%">
		<tr>
			<td><b>Id</b></td>
			<td><b>Matrícula</b></td>
			<td><b>Nome Completo</b></td>
			
		</tr>
		
		<s:iterator value="disciplinaLetiva.aluno">
			<tr>
				<td><s:property value="id"/></td>
				<td><s:property value="matricula"/></td>	
				<td><s:property value="nome"/></td>
			</tr>
		</s:iterator>
	</table>
	
</div>
<div id="result" align="right" >
	<input align="right" type="button" value="imprimir" onClick="window.print()" id="botao" />
</div>
</body>
</html>