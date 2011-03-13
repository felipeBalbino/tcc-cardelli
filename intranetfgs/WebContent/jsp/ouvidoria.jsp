<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib uri="/struts-dojo-tags" 								prefix="sx"			%>
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
#nome {background:transparent url(../images/ico_user.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#telefone {background:transparent url(../images/icon_phone.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#matricula {background:transparent url(../images/ico_user.gif) no-repeat 2% 50%; padding:2px 60px 2px 25px;}
#curso {background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; padding:2px 0px 2px 22px;}
#mensagem {background:transparent  no-repeat 2% 50%; padding:2px 14px 2px 0;}




 
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
	document.getElementById("telefone").style.backgroundColor = "transparent";
 	document.getElementById("matricula").style.backgroundColor = "transparent";
 	document.getElementById("mensagem").style.backgroundColor = "transparent";
 }

 function restartMensagemErro(){
                   document.getElementById("mensagem_ocultos").style.display = "none";
		  		   document.getElementById("email_ocultos").style.display = "none";
		  		   document.getElementById("email2_ocultos").style.display = "none";
 }

 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.ouvidoria;
         var erro = false;
         //validar nome
         if (d.nome.value == ""){
	           document.getElementById("nome").style.backgroundColor = "#FF6A6A";
                   d.nome.focus();
                   erro = true;
         }
         //validar telefone
         if (d.telefone.value == ""){
                  document.getElementById("telefone").style.backgroundColor = "#FF6A6A";
                  d.telefone.focus();
                  erro = true;
         }

       //validar matricula
         if (d.matricula.value == ""){
                  document.getElementById("matricula").style.backgroundColor = "#FF6A6A";
                  d.matricula.focus();
                  erro = true;
         }

         //validar mensagem
         if (d.mensagem.value == ""){
                  document.getElementById("mensagem").style.backgroundColor = "#FF6A6A";
                  d.matricula.focus();
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

 function mascara(o,f){
	 
	  v_obj=o;
	 
	  v_fun=f;
	 
	  setTimeout("execmascara()",1);
	 
	 }
	 
	 
	 
	 function execmascara(){
	 
	  v_obj.value=v_fun(v_obj.value);
	 
	 } 
	 
 function Telefone(v){
	 
	  v=v.replace(/\D/g,""); 
	 
	  v=v.replace(/^(\d\d)(\d{4})(\d{4})/g,"($1)$2-$3");
	 
	  return v;
	 
	 } 



</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Ouvidoria</title>
</head>
<body>


		<div id="experience" >
					<div id="explanation">
						<h3>
						<strong>
						Ouvidoria
						</strong>
						</h3>
						<p>Mensagens para a Ouvidoria devem ser enviadas pelo do formulário eletrônico on line disponível ao lado.

O sistema permite que as informações enviadas sejam armazenadas de forma segura e sigilosa e são essenciais para o devido encaminhamento das demandas e demais comunicações junto aos órgãos responsáveis facilitando um retorno satisfatório das questões apresentadas.
<br>

Informe seus dados e mensagem, assim que possível, entraremos em contato.

Antes de nos enviar sua manifestação leia estes lembretes:<br>

    * email: informe corretamente seu endereço eletrônico para receber o retorno.<br>
    * Comunidade: pedimos àqueles que atuam junto à FGS que nos informem a qual categoria pertence (aluno, docente ou servidor público). Estas informações são importantes para compor nosso relatório de atividades.
						</p>
						<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O(s) Campo(s) em vermelhor é(são) requerido(s).</p></div>
						<div id="email_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email é requerido.</p></div>
						<div id="email2_ocultos" style="display:none;color:red;background-repeat: no-repeat;"><p><img src="../images/imgErro.gif"/>  Campo email com caracteres inválidos.</p></div>
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
						<s:form name="ouvidoria" action="/jsp/ouvidoria.java" onSubmit="return validaForm()">
							<table>
								<tr>
									<td>
										<s:text name="Nome:"/><s:textfield id="nome" name="ouvidoriaEmailParams.nome" ></s:textfield><br>
										<s:text name="Email:"/><s:textfield id="email" name="ouvidoriaEmailParams.email" ></s:textfield><br>
										<s:text name="Telefone:"/><i style="color:gray;">(21)2222-2222</i><s:textfield id="telefone" onkeypress="mascara(this,Telefone)" maxlength="14"  name="ouvidoriaEmailParams.telefone" ></s:textfield><br>
										<s:text name="Matricula:"/><br><s:textfield id="matricula" name="ouvidoriaEmailParams.matricula" ></s:textfield><br>
										<s:text name="Curso:"/><br><sx:autocompleter headerKey=""   headerValue=""  id="curso" name="ouvidoriaEmailParams.cursoId" list="cursos" listValue="nome" listKey="id"/><br>
										<s:text name="Mensagem:"/><s:textarea id="mensagem" name="ouvidoriaEmailParams.mensagem"  cssStyle="width: 230px;height:170"></s:textarea><br>
										<s:submit align="left" value="Enviar"></s:submit>
									</td>
								</tr>
							</table>
						</s:form>
					</div>		
		</div>
</body>
</html>