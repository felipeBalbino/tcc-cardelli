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

jQuery(function($){
	   $("#matricula").mask("99999999");
	}); 

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





  
</script>


<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Registrar Aluno</title>
</head>
<div id="retorno" align="left">
<img src="../images/arrow_categ.gif"><a href="../painel/aluno!lista.java" style="color:#FF4500;text-align:right;border-style:none;">Lista de Alunos</a>
</div>
<body>
				<c:if test="${not empty param.lingua}">
						<fmt:setLocale value="${param.lingua}" scope="session" />
					</c:if>
			<div id="experience">
					<div id="explanation">
						<h3>
						<strong>
						Registrar Aluno
						</strong>
						</h3>
						<p>
							Área reservado aos coordenadores, para efetuar registro de aluno.
						</p>
						<strong>
							<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O(s) Campo(s) em vermelhor é(são) requerido(s).</p></div>
							<div id="email_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email é requerido.</p></div>
							<div id="email2_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email com caracteres invalidos.</p></div>
							<div id="senha_nao_confere" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  As senhas necessitam ser iguais no campo senha e confirmar senha.</p></div>
							<div id="email_nao_confere" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Os emails necessitam ser iguais no campo email e confirmar email.</p></div>
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
						</strong>
					</div>	
					
					<div id="contact-form">
						<s:form name="registra" method="post" action="../painel/aluno!registrar.java"  onSubmit="return validaForm()">
							<s:text name="Nome Completo"/><s:textfield id="nome"  required="true" name="alunoNovoParams.nome"></s:textfield>
							<s:text name="Matrícula"/><s:textfield  required="true"  maxLength="8" id="matricula" name="alunoNovoParams.matricula"></s:textfield>
							<br>
							<hr></hr>
							<br>
							<s:text name="Curso"/><br><sx:autocompleter  headerKey=""  headerValue=""  id="curso" name="alunoNovoParams.cursoId" list="cursos" listValue="nome" listKey="id"/>
							<s:text name="Período"/><br><s:select id="periodo" required="true"  headerKey="" headerValue=""
									       list="#{'01':'01', '02':'02','03':'03', '04':'04','05':'05', '06':'06','07':'07', '08':'08','09':'09', '10':'10','11':'11', '12':'12','13':'13','14':'14'}"
									       name="alunoNovoParams.periodo"
									></s:select>
							<s:text name="Status Matrícula"/><br><sx:autocompleter  headerKey=""   headerValue=""  id="statusMatricula" list="allStatusMatricula"  name="alunoNovoParams.statusMatricula" />
							<br>
							<br>
							<hr></hr>
							<br>
							<s:text name="Email"/><s:textfield id="email" required="true" name="alunoNovoParams.email" ></s:textfield>
							<s:text name="Confirmar E-mail"/><s:textfield id="email2"  required="true" name="email2"></s:textfield>
							<br>
							<hr></hr>
							<br>
							<s:text name="Login"/><s:textfield id="login"  required="true" name="alunoNovoParams.login" maxlength="8"></s:textfield>
							<br>
							<hr></hr>
							<br>
							<s:text name="Senha"/><s:password id="senha" required="true"     name="alunoNovoParams.senha"></s:password>
							<s:text name="Confirmar Senha"/><s:password id="senha2" required="true"   name="senha2"></s:password>
							<br>
							<sx:submit align="left" value="Registrar" label="Log in"></sx:submit>
						</s:form>
					</div>		
</div>

</body>
</html>