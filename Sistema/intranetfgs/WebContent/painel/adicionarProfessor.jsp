<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


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
         d = document.f1;
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
 
 function selecionar_tudo(){
	 document.getElementById("ocultartudo").style.display = "inline";
	 document.getElementById("selecionartudo").style.display = "none";
	 document.getElementById("selecionapadraoprofessor").style.display = "none";
	   for (i=0;i<document.f1.elements.length;i++)
	      if(document.f1.elements[i].type == "checkbox")
	         document.f1.elements[i].checked=1
	} 

 function selecionar_padrao_professor(){
	 document.getElementById("selecionapadraoprofessor").style.display = "none";
	 document.getElementById("ocultartudo").style.display = "inline";
	 document.getElementById("selecionartudo").style.display = "none";
	   for (i=0;i<document.f1.elements.length;i++)
	      if(document.f1.elements[i].type == "checkbox")
		      if(document.f1.elements[i].value == "RULE_ALTERA_AEP" ||
		    		  document.f1.elements[i].value == "RULE_ALTERA_COMPLEMENTAR" ||
		    			  document.f1.elements[i].value == "RULE_ALUNOS_ALTERA" ||
		    				  document.f1.elements[i].value == "RULE_ALUNOS_LISTA" ||
		    					  document.f1.elements[i].value == "RULE_ALUNOS_SAVE" ||
		    						  document.f1.elements[i].value == "RULE_CURSO_LISTA" ||
		    							  document.f1.elements[i].value == "RULE_DELETA_AEP" ||
		    								  document.f1.elements[i].value == "RULE_DELETA_COMPLEMENTAR" ||
		    									  document.f1.elements[i].value == "RULE_DISCIPLINA_ALTERA" ||
		    										  document.f1.elements[i].value == "RULE_DISCIPLINA_DELETE" ||
		    										  	 document.f1.elements[i].value == "RULE_DISCIPLINA_LETIVA_DELETE" ||
		    										 	    document.f1.elements[i].value == "RULE_DISCIPLINA_LETIVA_LISTA" ||
		    										  		   document.f1.elements[i].value == "RULE_DISCIPLINA_LETIVA_SAVE" ||
		    										  		      document.f1.elements[i].value == "RULE_DISCIPLINA_LISTA" ||
					    										     document.f1.elements[i].value == "RULE_DISCIPLINA_NOVO" ||
					    										        document.f1.elements[i].value == "RULE_DISCIPLINA_SALVA" ||
					    										           document.f1.elements[i].value == "RULE_LISTA_AEP" ||
					    										              document.f1.elements[i].value == "RULE_LISTA_COMPLEMENTAR" ||
					    										                document.f1.elements[i].value == "RULE_SALVA_AEP" ||
					    										                  document.f1.elements[i].value == "RULE_SALVA_COMPLEMENTAR" ||
					    										                    document.f1.elements[i].value == "RULE_UPLOAD_DELETA" ||
					    										                     document.f1.elements[i].value == "RULE_UPLOAD_LISTA" ||
					    										                      document.f1.elements[i].value == "RULE_UPLOAD_NOVO" ||
		    											  							   document.f1.elements[i].value == "RULE_DISCIPLINA_LETIVA_ALTERA" )
	         	document.f1.elements[i].checked=1
	}
 
 function deselecionar_tudo(){
	 document.getElementById("ocultartudo").style.display = "none";
	 document.getElementById("selecionartudo").style.display = "inline";
	document.getElementById("selecionapadraoprofessor").style.display = "inline";
	   for (i=0;i<document.f1.elements.length;i++)
	      if(document.f1.elements[i].type == "checkbox")
	         document.f1.elements[i].checked=0
	} 

	

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Adicionar Professor</title>
</head>
<body>
<div id="retorno" align="right">
<img src="../images/arrow_categ.gif"><a href="../painel/professor!lista.java" style="color:#FF4500;text-align:left;border-style:none;">Lista de Professores</a>
</div>
<div id="experience">
					<div id="explanation">
						<h3>
						<strong>
						Adicionar Novo Professor
						</strong>
						</h3>
						<p>Preencha os campos ao lado para cadastrar um novo professor.

						</p>
						<strong>
							<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O(s) Campo(s) em vermelho(s) é(são) requerido(s).</p></div>
							<div id="email_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email é requerido.</p></div>
							<div id="email2_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email com caracteres inválidos.</p></div>
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
						</strong>
					</div>	
					
					<div id="contact-form">
						<s:form name="f1" action="/painel/professor!save.java" onSubmit="return validaForm()">
							Nome: 		<s:textfield id="nome"      name="professorNovoParams.nome"     ></s:textfield>
							Matrícula:  <s:textfield id="matricula" name="professorNovoParams.matricula" maxlength="8"></s:textfield>			
							Login: 		<s:textfield id="login"     name="professorNovoParams.login"    ></s:textfield>
							Senha: 		<s:password  id="senha"     name="professorNovoParams.senha"    ></s:password>
							E-mail:		<s:textfield id="email"     name="professorNovoParams.email"    ></s:textfield><br>
							<div id="lilocal"><h1><a href="#" onclick="javascript:inserirregras()">Inserir Regras</a></h1></div>
							<div id="lilocal2" style="display:none;"><h1><a href="#" onclick="javascript:esconderregras()">Esconder Regras</a></h1></div>
							<div  id="divregras" style="display:none;">
								<br>
								<b>Regras</b>
								<hr width="100%">
								<div style="text-align: right;">
									<a id="selecionartudo" style="color:#666666;cursor:pointer;" onclick="selecionar_tudo()" >Marcar Todas</a><br>
									<a id="selecionapadraoprofessor" style="color:#666666;cursor:pointer;" onclick="selecionar_padrao_professor()" >Marcar Padrão de Professor</a><br>
									<a id="ocultartudo" style="cursor:pointer;color:#666666;" onclick="deselecionar_tudo()" >Desmarcar Todas</a>
								</div>
								<table id="f1">
									<c:set value="0" var="cont"></c:set>
										<s:iterator value="rules" status="st" var="rule">
											<c:if test="${cont == 0}">
												<c:out value="<tr>" escapeXml="false"></c:out>
											</c:if>
												<tr>
										  			<td><s:checkbox name="professorNovoParams.rules" fieldValue="%{nome}" id="rule.id" >${rule.nome}</s:checkbox></td>
										  		</tr>
											<c:if test="${cont > 4}">
												<c:out value="</tr>" escapeXml="false"></c:out>
												<c:set value="0" var="cont"></c:set>
											</c:if>
									  		<c:set value="${cont + 1}" var="cont"></c:set>
										</s:iterator>
								</table>
								
							</div>
							<s:submit value="Adicionar"></s:submit>
					</s:form>
			</div>					
</div>

<!-- Necessário para Ocular Inicialmente a div Ocultar tudo -->
<script type="text/javascript">
	document.getElementById("ocultartudo").style.display = "none";
</script>

</body>
</html>