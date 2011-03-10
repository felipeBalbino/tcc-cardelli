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

#email {background:transparent url(../images/ico_mail.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
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
	document.getElementById("senha").style.backgroundColor = "transparent";
 	document.getElementById("email").style.backgroundColor = "transparent";
 }

 function restartMensagemErro(){
	 document.getElementById("mensagem_ocultos").style.display = "none";
	   document.getElementById("email_ocultos").style.display = "none";
	   document.getElementById("email2_ocultos").style.display = "none";

 }

 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.senha;
         var erro = false;
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

<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Trocar Senha</title>
</head>
<body>

	<s:actionmessage />
	<s:actionerror/>
	
		
			<div id="experience">
					<div id="explanation">
						<h3>
						<strong>
						Alterar senha
						</strong>
						</h3>
						<p>Clique no menu ao lado. Digite a email atual, a nova senha, e clique em "Alterar". <br>Por segurança, recomendamos que você mude sua senha a cada dois meses.</p>
						
						<div id="mensagem_ocultos" style="display:none;color:red;"><p align="center">O(s) Campo(s) em vermelhor é(são) requirido(s).</p></div>
						<div id="email_ocultos" style="display:none;color:red"><p align="center">Campo email é requirido.</p></div>
						<div id="email2_ocultos" style="display:none;color:red"><p align="center">Campo email com caracteres invalidos.</p></div>
						
					</div>	
					
					<div id="contact-form">
					<s:form name="senha" method="post" action="../jsp/log!senha.java" onSubmit="return validaForm()">
						Email: <s:textfield id="email" name="email"></s:textfield>
						Senha Atual: <s:password id="senha" name="senhaAtual"></s:password>
						Nova Senha: <s:password id="senha" name="novaSenha"></s:password>
						<sx:submit align="left" value="Alterar" label="Log in"></sx:submit>
					</s:form>
</div>		
</div>

</body>
</html>