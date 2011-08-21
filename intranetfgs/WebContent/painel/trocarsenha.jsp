<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



<script type="text/javascript">
 function restartTrs(){
	document.getElementById("senhaAtual").style.backgroundColor = "transparent";
	document.getElementById("novaSenha").style.backgroundColor = "transparent";
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
         if (d.senhaAtual.value == ""){
                   document.getElementById("senhaAtual").style.backgroundColor = "#FF6A6A";
                   d.senhaAtual.focus();
                   erro = true;
         }

         if (d.novaSenha.value == ""){
             document.getElementById("novaSenha").style.backgroundColor = "#FF6A6A";
             d.novaSenha.focus();
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

	
	
		
			<div id="experience">
					<div id="explanation">
						<h3>
						<strong>
						Alterar senha
						</strong>
						</h3>
						<p>Digite o email atual a nova senha, e clique em "Alterar". <br>Por segurança, recomendamos que você mude sua senha a cada dois meses.</p>
						
						<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O(s) Campo(s) em vermelhor é(são) requirido(s).</p></div>
						<div id="email_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email é requirido.</p></div>
						<div id="email2_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email com caracteres invalidos.</p></div>
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
					<s:form name="senha" method="post" action="../jsp/log!senha.java" onSubmit="return validaForm()">
						Email: <s:textfield id="email" name="email"></s:textfield>
						Senha Atual: <s:password id="senhaAtual" name="senhaAtual"></s:password>
						Nova Senha: <s:password id="novaSenha" name="novaSenha"></s:password>
						<sx:submit align="left" value="Alterar" label="Log in"></sx:submit>
					</s:form>
</div>		
</div>

</body>
</html>