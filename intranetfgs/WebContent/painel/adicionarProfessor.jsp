<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#experience { 
padding:30px; 
position:relative; 
padding:17px 0px 0 39px;
}


#explanation { 
float:left;
width:340px;
}

#email {background:transparent url(../images/ico_mail.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#login {background:transparent url(../images/profile_ico_transparent.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#nome  {background:transparent url(../images/ico_user.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#senha {background:transparent url(../images/lock.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#matricula {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}


 
#contact-form { 
border-left:1px solid #313b45; 
margin-left:370px; 
padding-left:30px; 
width:295px; 
}


</style>


<script type="text/javascript">
 function restartTrs(){
	document.getElementById("nome").style.backgroundColor = "transparent";
 	document.getElementById("email").style.backgroundColor = "transparent";
	document.getElementById("login").style.backgroundColor = "transparent";
 	document.getElementById("senha").style.backgroundColor = "transparent";
 	document.getElementById("matricula").style.backgroundColor = "transparent";
 }

 function restartMensagemErro(){
                   document.getElementById("mensagem_ocultos").style.display = "none";
		  		   document.getElementById("email_ocultos").style.display = "none";
		  		   document.getElementById("email2_ocultos").style.display = "none";

 }
 
 function inserirregras(){
	 document.getElementById("divregras").style.display = "inline";
	 document.getElementById("lilocal2").style.display = "inline";
	 document.getElementById("lilocal").style.display = "none";
 } 
 
 
 function esconderregras(){
	 document.getElementById("divregras").style.display = "none";
	 document.getElementById("lilocal2").style.display = "none";
	 document.getElementById("lilocal").style.display = "inline";
 } 
 
 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.registra;
         var erro = false;
         //validar nome
         if (d.nome.value == ""){
	           document.getElementById("nome").style.backgroundColor = "#FF6A6A";
                   d.nome.focus();
                   erro = true;
         }
         //validar login
         if (d.login.value == ""){
                  document.getElementById("login").style.backgroundColor = "#FF6A6A";
                  d.login.focus();
                  erro = true;
         }

         
         //validar senha
         if (d.senha.value == ""){
                   document.getElementById("senha").style.backgroundColor = "#FF6A6A";
                   d.senha.focus();
                   erro = true;
         }
        


         //validar email
         if (d.email.value == ""){
                   document.getElementById("email").style.backgroundColor = "#FF6A6A";
                   d.email.focus();
                   erro = true;
         }

         if (d.email2.value == ""){
             document.getElementById("email2").style.backgroundColor = "#FF6A6A";
             d.email.focus();
             erro = true;
   }

         //validar email(verificar caracteres)
         parte1 = d.email.value.indexOf("@");
         parte2 = d.email.value.indexOf(".");
         parte3 = d.email.value.length;
         if (!(parte1 >= 3 && parte2 >= 6 && parte3 >= 9)) {
                   document.getElementById("email").style.backgroundColor = "#FF6A6A";
                   document.getElementById("email2_ocultos").style.display = "inline";
                   d.email.focus();
                   erro = true;
         }
		if (erro != true) {
         	return true;
		}else{
			document.getElementById("mensagem_ocultos").style.display = "inline";
         	return false;
		}
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Adicionar Professor</title>
</head>
<body>

<s:actionmessage/>

<div id="experience">
					<div id="explanation">
						<h3>
						<strong>
						Adicionar Novo Professor
						</strong>
						</h3>
						<p>We care as much about your users as you do (maybe more), and we are passionate about making sure you get the best possible solution to whatever problems need solving.</p>
						<p>From web applications to content management, from blogging to podcasting, from desktop to mobile, we've done it all (and we've even written a few books on the subjects).</p>
						<p>
						Use our handy contact form to get the discussion the first step towards turning our team into

						</p>
						<strong>
							<div id="mensagem_ocultos" style="display:none;color:red;"><p align="center">O(s) Campo(s) em vermelhor Ã©(sÃ£o) requirido(s).</p></div>
							<div id="email_ocultos" style="display:none;color:red"><p align="center">Campo email Ã© requirido.</p></div>
							<div id="email2_ocultos" style="display:none;color:red"><p align="center">Campo email com caracteres invalidos.</p></div>
							<b style="color:red;align:center;"><s:actionmessage/></b>
						</strong>
					</div>	
					
					<div id="contact-form">
						<s:form action="/painel/professor!save.java">
							Nome: 		<s:textfield id="nome"      name="professor.nome"     ></s:textfield>
							Matrícula:  <s:textfield id="matricula" name="professor.matricula"></s:textfield>			
							Login: 		<s:textfield id="login"     name="professor.login"    ></s:textfield>
							Senha: 		<s:password  id="senha"     name="professor.senha"    ></s:password>
							E-mail:		<s:textfield id="email"     name="professor.email"    ></s:textfield><br></br>
							<div id="lilocal"><h1><a href="#" onclick="javascript:inserirregras()">Inserir Regras</a></h1></div>
							<div id="lilocal2" style="display:none;"><h1><a href="#" onclick="javascript:esconderregras()">Esconder Regras</a></h1>+++</div>
							<div  id="divregras" style="display:none;">
								<br><br>
								<b>Regras</b>
								<hr width="100%">
								<table>
									<c:set value="0" var="cont"></c:set>
										<s:iterator value="rules" status="st" var="rule">
											<c:if test="${cont == 0}">
												<c:out value="<tr>" escapeXml="false"></c:out>
											</c:if>
												<tr>
										  			<td><s:checkbox name="checkBoxSelecionados" fieldValue="%{id}" id="rule.id" >${rule.nome}</s:checkbox></td>
										  		</tr>
											<c:if test="${cont > 4}">
												<c:out value="</tr>" escapeXml="false"></c:out>
												<c:set value="0" var="cont"></c:set>
											</c:if>
									  		<c:set value="${cont + 1}" var="cont"></c:set>
										</s:iterator>
								</table>
								<s:submit></s:submit>
							</div>
					</s:form>
			</div>					
</div>
</body>
</html>