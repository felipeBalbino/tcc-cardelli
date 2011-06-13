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

#cargo {background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#faixaSalarial {background:transparent url(../images/ico_mail.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#perfil {background:transparent url(../images/ico_mail.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#quantidadeDevagas {background:transparent url(../images/profile_ico_transparent.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#regimeDeContratacao  {background:transparent url(../images/ico_user.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#beneficios {background:transparent url(../images/lock.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#nivelHierarquico {background:transparent url(../images/lock.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#horarioDaVaga {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#areas {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#empresas  {background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
 
							
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



 function Numero(e)
 {
	 navegador = /msie/i.test(navigator.userAgent);
	 if (navegador)
	 	var tecla = event.keyCode;
	 else
	 	var tecla = e.which;
	 if(tecla > 47 && tecla < 58) // numeros de 0 a 9
		 return true;
	 else
	 {
	 if (tecla != 8) // backspace
	 return false;
	 else
	 return true;
	 }
 }

  
</script>


<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Enviar Vaga de Emprego</title>
</head>
<div id="retorno" align="left">
<img src="../images/arrow_categ.gif"><a href="../painel/vaga!lista.java" style="color:#FF4500;text-align:right;border-style:none;">Lista de vagas</a>
</div>
<body>
		
			<div id="experience">
					<div id="explanation">
						<h3>
						<strong>
						Enviar um Vaga de emprego
						</strong>
						</h3>
						<p>
							Área reservada, para efetuar registro de Vaga de emprego.
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
						<s:form name="registra" method="post" action="../painel/vaga!save.java"  onSubmit="return validaForm()">
							
							<s:textfield id="cargo"  required="true" label="Cargo" name="vaga.cargo"></s:textfield>
							<s:textfield id="faixaSalarial"  required="true" label="Faixa Salarial" name="vaga.faixaSalarial"></s:textfield>
							<s:textfield id="perfil"  required="true" label="Perfil" name="vaga.perfil"></s:textfield>
							<s:textfield id="quantidadeDevagas"  required="true" label="Quantidade De Vagas" name="vaga.quantidadeDevagas"></s:textfield>
							<s:textfield id="regimeDeContratacao"  required="true" label="Regime De Contratação" name="vaga.regimeDeContratacao"></s:textfield>
							<s:textfield id="beneficios"  required="true" label="Beneficios" name="vaga.beneficios"></s:textfield>
							<s:textfield id="nivelHierarquico"  required="true" label="nivelHierarquico" name="vaga.nivelHierarquico"></s:textfield>
							<s:textfield id="horarioDaVaga"  required="true" label="Horarios" name="vaga.horarioDaVaga"></s:textfield>
							
							<s:select list="areas" id="area" name="vaga.areaProfissional" ></s:select>
							<s:select list="empresas" listKey="id" listValue="nome" id="empresa" name="empresaId" ></s:select>

							<br>
							<sx:submit align="left" value="Entrar" label="Log in"></sx:submit>
						</s:form>
					</div>		
</div>

</body>
</html>