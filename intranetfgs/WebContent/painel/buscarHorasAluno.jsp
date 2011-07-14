	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	    <%@taglib uri="/struts-tags" prefix="s" %>
	    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
	    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
	    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	
	<style type="text/css">
	#experience { 
	padding:30px; 
	padding:17px 0px 0 39px;
	}
	
	
	#explanation { 
	float:left;
	width:340px;
	}
	 
	#contact-form { 
	border-left:1px solid #313b45; 
	margin-left:370px; 
	padding-left:30px; 
	width:295px; 
	}
	
	
	</style>
	
	<s:head/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Intranet - Faculdade Gama & Souza | Buscar Horas AEP e Complementares</title>
	</head>
	<body>
	
		
		
			
				<div id="experience">
						<div id="explanation">
							<h3>
							<strong>
							Buscar Horas AEP e Complementares
							</strong>
							</h3>
							<p>Digite o email atual a nova senha, e clique em "Alterar". <br>Por segurança, recomendamos que você mude sua senha a cada dois meses.</p>
							<br>
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
						</div>	
						
						<div id="contact-form">
							<s:form action="/painel/hora!buscarAluno.java" name="senha" method="post" onSubmit="return validaForm()">
							Matricula: <s:textfield  id="alunoSearchParams.matricula" name="alunoSearchParams.matricula"></s:textfield>
							<sx:submit  align="left" value="Buscar" label="Log in"></sx:submit>
						</s:form>
	</div>		
	</div>
	
	</body>
	</html>