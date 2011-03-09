<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

#login {background:transparent url(../images/profile_ico_transparent.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#senha {background:transparent url(../images/lock.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}




 
#contact-form { 
border-left:1px solid #313b45; 
margin-left:370px; 
padding-left:30px; 
width:295px; 
}


</style>
<script type="text/javascript">
 function restartTrs(){
	document.getElementById("login").style.backgroundColor = "transparent";
 	document.getElementById("senha").style.backgroundColor = "transparent";
 }

 function restartMensagemErro(){
                   document.getElementById("login_ocultos").style.display = "none";
		  		   document.getElementById("senha_ocultos").style.display = "none";

 }

 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.ouvidoria;
         var erro = false;
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

         
		if (erro != true) {
         	return true;
		}else{
			document.getElementById("mensagem_ocultos").style.display = "inline";
         	return false;
		}
}

</script>

<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Login</title>
</head>
<body>
<c:if test="${sessionScope.pessoa == null}">
	<s:actionmessage />
	<s:actionerror/>
			<div id="experience" >
					<div id="explanation">
						<h3>
							<strong>
								Efetue seu login para poder acessar a área restrita do Sistema.
							</strong>
						</h3>
						<p>Aréa reservada aos alunos e professores da faculdade Gama e Souza.<br>
							<img src="../images/alert-ico.gif"> Lembre-se: sua senha de acesso é secreta; não a informe a ninguém.
	A FGS jamais solicitará sua senha por e-mail ou por telefone. <br><br> Primeira vez? <a href="../jsp/log!prepare.java" style="color:red;"><b>Registre-se<b></b></a> 
						</p>
						<div id="login_ocultos" style="display:none;color:red;"><p align="center">Campo login é requirido.</p></div>
						<div id="senha_ocultos" style="display:none;color:red"><p align="center">Campo senha é requirido.</p></div>
						
					</div>	
					<br><br>
					<div id="contact-form">
						<s:form method="post" action="../jsp/log!in.java">
							Login: <s:textfield name="login" id="login"></s:textfield>
							Senha: <s:password name="senha" id="senha"></s:password>
							<sx:submit align="left" value="Entrar" label="Log in"></sx:submit>
						</s:form>
					</div>		
		</div>
				
</c:if>

<c:if test="${sessionScope.pessoa != null}">
Você já está logado.
</c:if>
</body>
</html>