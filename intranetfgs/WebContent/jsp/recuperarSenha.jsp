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
padding:17px 0px 0 39px;
}


#explanation { 
float:left;
width:340px;
}

#email {background:transparent url(../images/ico_mail.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}




 
#contact-form { 
border-left:1px solid #313b45; 
margin-left:370px; 
padding-left:30px; 
width:295px; 
}


</style>
<script type="text/javascript">
 function restartTrs(){
	document.getElementById("email").style.backgroundColor = "transparent";
 }

 function restartMensagemErro(){
		  document.getElementById("email_ocultos").style.display = "none";
		  document.getElementById("email2_ocultos").style.display = "none";

 }

 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.recover;
         var erro = false;

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
			document.getElementById("email_ocultos").style.display = "inline";
         	return false;
		}
}

</script>

<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Recuperar Senha</title>
</head>
<body>
<c:if test="${sessionScope.pessoa == null}">
			<div id="experience" >
					<div id="explanation">
						<h3>
							<strong>
								Esqueceu a sua senha?
							</strong>
						</h3>
						<p>Para redefinir a senha, digite o endereço de e-mail que você usava para acessar o sistema anteriormente.<br> Um email será enviado para este email com instruções de acesso.<br>
							<br><br><img src="../images/alert-ico.gif"> Lembre-se: sua senha de acesso é secreta; não a informe a ninguém.
	A FGS jamais solicitará sua senha por e-mail ou por telefone. <br><br> Primeira vez? <a href="../jsp/log!prepare.java" style="color:red;"><b>Registre-se<b></b></a> 
						</p>
							<div id="email_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email é requerido.</p></div>
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
					<br><br>
					<div id="contact-form">
						<s:form name="recover" method="post" action="../jsp/log!recuperarSenha.java" onSubmit="return validaForm()">
							Endereço de e-mail: <s:textfield name="email" id="email"></s:textfield>
							<sx:submit align="left" value="Redefinir" label="RecuperarSenha"></sx:submit>
						</s:form>
					</div>		
		</div>
				
</c:if>
</body>
</html>