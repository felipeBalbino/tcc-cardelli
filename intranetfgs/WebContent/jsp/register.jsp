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
#email2 {background:transparent url(../images/ico_mail.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#login {background:transparent url(../images/profile_ico_transparent.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#nome  {background:transparent url(../images/ico_user.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#senha {background:transparent url(../images/lock.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#senha2 {background:transparent url(../images/lock.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
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
 	document.getElementById("email2").style.backgroundColor = "transparent";
	document.getElementById("login").style.backgroundColor = "transparent";
 	document.getElementById("senha").style.backgroundColor = "transparent";
 	document.getElementById("senha2").style.backgroundColor = "transparent";
 }

 function restartMensagemErro(){
                   document.getElementById("mensagem_ocultos").style.display = "none";
		  		   document.getElementById("email_ocultos").style.display = "none";
		  		   document.getElementById("email2_ocultos").style.display = "none";
		  		   document.getElementById("senha_nao_confere").style.display = "none";
		  		 document.getElementById("email_nao_confere").style.display = "none";
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

	     //validar senha de confirmacao
         if (d.senha2.value == ""){
                   document.getElementById("senha2").style.backgroundColor = "#FF6A6A";
                   d.senha2.focus();
                   erro = true;
         }

		//Conferir senha com confirmar senha
		if(d.senha.value != d.senha2.value){
			   document.getElementById("senha").style.backgroundColor = "#FF6A6A";
		           document.getElementById("senha2").style.backgroundColor = "#FF6A6A";
	                   document.getElementById("senha_nao_confere").style.display = "inline";
	                   d.senha.focus();
	                   erro = true;
		} 

		//Conferir email com confirmar email
		if(d.email.value != d.email2.value){
			   document.getElementById("email").style.backgroundColor = "#FF6A6A";
		           document.getElementById("email2").style.backgroundColor = "#FF6A6A";
	                   document.getElementById("email_nao_confere").style.display = "inline";
	                   d.email.focus();
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

 function contarCaracteres(box,valor,campospan){
		var conta = valor - box.length;
		document.getElementById(campospan).innerHTML = "Você ainda pode digitar " + conta + " caracteres no campo Login";
		if(box.length >= valor){
			document.getElementById(campospan).innerHTML = "";
			document.getElementById("login").value = document.getElementById("login").value.substr(0,valor);
		}	
	}

</script>


<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Login</title>
</head>
<body>


	
	<s:actionerror/>
	
		
			<div id="experience">
					<div id="explanation">
						<h3>
						<strong>
						Registrar Aluno
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
							<div id="senha_nao_confere" style="display:none;color:red"><p align="center">As senhas necessitam ser iguais no campo senha e confirmar senha.</p></div>
							<div id="email_nao_confere" style="display:none;color:red"><p align="center">Os emails necessitam ser iguais no campo email e confirmar email.</p></div>
							<p align="center"><span id="spcontando" style="font-family:Georgia;color:red;"></span></p><br /><br>
							<div style="color:red;align:center;"><s:actionmessage/></div>
						</strong>
					</div>	
					
					<div id="contact-form">
						<s:form name="registra" method="post" action="../jsp/log!registrar.java"  onSubmit="return validaForm()">
		
							Curso:<br><sx:autocompleter  headerKey=""  headerValue=""  id="curso" name="alunoNovoParams.cursoId" list="cursos" listValue="nome" listKey="id"/><br>
							Nome Completo: <s:textfield id="nome" name="alunoNovoParams.nome"></s:textfield><br>
							Matrícula: <s:textfield  id="matricula" name="alunoNovoParams.matricula"></s:textfield><br>
							Email: <s:textfield id="email" name="alunoNovoParams.email" ></s:textfield><br>
							Confirmar Email: <s:textfield id="email2" name="aluno.email"></s:textfield><br>
							Login: <s:textfield id="login" name="alunoNovoParams.login" onkeypress="contarCaracteres(this.value,8,'spcontando')"></s:textfield><br>
							Senha: <s:password id="senha" name="alunoNovoParams.senha"></s:password><br>
							Confirmar Senha: <s:password id="senha2"></s:password><br>
							
							<sx:submit align="left" value="Entrar" label="Log in"></sx:submit>
						</s:form>
					</div>		
</div>

</body>
</html>