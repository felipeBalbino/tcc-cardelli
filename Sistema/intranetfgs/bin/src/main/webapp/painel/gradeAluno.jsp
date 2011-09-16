<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
   <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" 			prefix="c"          %>
 <%@ taglib uri="/WEB-INF/intranetTags.tld"  					prefix="intranet"	%>   
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Grade</title>

</head>

<% if (session.getAttribute("pessoa") instanceof br.edu.gamaesouza.intranet.bean.Professor){ %>
<div id="retorno" align="right">
<img src="../images/arrow_categ.gif"><a href="../painel/aluno!lista.java" style="color:#FF4500;text-align:right;border-style:none;">Lista de Alunos</a>
</div>
<% } %>
									
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
<intranet:gradeAluno semestre="1" idAluno="2" ano="3" turno="4"/>
</html>