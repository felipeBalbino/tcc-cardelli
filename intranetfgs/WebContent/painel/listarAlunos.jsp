<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
    <%@ taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Alunos</title>


<script type="text/javascript">


 	function restartTrs(){
 		document.getElementById("nome").style.backgroundColor = "transparent";
 	 	document.getElementById("email").style.backgroundColor = "transparent";
 		document.getElementById("login").style.backgroundColor = "transparent";
 	 	document.getElementById("matricula").style.backgroundColor = "transparent";
 	 }

 	 function restartMensagemErro(){
 	                   document.getElementById("mensagem_ocultos").style.display = "none";
 			  		   document.getElementById("email_ocultos").style.display = "none";
 			  		   document.getElementById("email2_ocultos").style.display = "none";

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
</head>
<body>
<div align="right">
	<a  title="Novo Aluno" href="../painel/aluno!prepare.java">
	<img border="0" src="../images/novo.jpg" />Novo Aluno</a>
</div>
<br>

<b>Filtros de Pesquisa</b>
<hr></hr>			
<s:form action="/painel/aluno!lista.java">
	<table>
		<tr>
			<td>Nome:</td>
			<td><s:textfield name="alunoSearchParams.nome" id="alunoSearchParams.nome"></s:textfield></td>
			
			<td>Periodo:</td>
			<td><s:textfield  maxLength="2" name="alunoSearchParams.periodo" id="periodo"></s:textfield></td>
		</tr>
		<tr>	
			<td>Curso:</td>
			<td><sx:autocompleter  headerKey=""  headerValue=""  id="curso" list="cursos"  name="alunoSearchParams.cursoId" listValue="nome" listKey="id" /></td>
			
			<td>Status Matrícula:</td>
			<td><sx:autocompleter  headerKey=""  headerValue=""  id="statusMatricula" list="allStatusMatricula"  name="alunoSearchParams.statusMatricula"/></td>
		</tr>	
		<tr>
			<td>Email:</td>
			<td><s:textfield name="alunoSearchParams.email" id="alunoSearchParams.email"></s:textfield></td>
			
			<td>Matrícula:</td>
			<td><s:textfield  name="alunoSearchParams.matricula" id="matricula" maxLength="8"></s:textfield></td>
		</tr>
		<tr>
			<td><s:submit value="Pesquisar"></s:submit></td>
		</tr>
	</table>  
	 
	
	
</s:form>	
<b>Alunos</b>
<hr></hr>
<strong>
		<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O(s) Campo(s) em vermelho(s) é(são) requerido(s).</p></div>
		<div id="email_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email é requerido.</p></div>
		<div id="email2_ocultos" style="display:none;color:red"><p><img src="../images/imgErro.gif"/>  Campo email com caracteres inválidos.</p></div>
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
<table width="100%">
				<s:iterator value="alunos" var="aluno">
				
					<tr>
						<td>
							<table width="100%" bgcolor="#FAFAD2">
								<tr>
									<td width="1%"><img src="../images/event_icon.png" /></td>
									<td width="70%" style="font-size: 10px;font-weight:bold"> <s:property  value="nome" /> </td>
									<td width="19%" align="right">
										<a title="Exibir aluno" class="exibir">
											<img src="../images/open.png" />
										</a>
										<a  title="Ocultar aluno" class="ocultar">
											<img src="../images/close.png" />
										</a>
										<a  title="Exibir e imprimir grade do aluno" border="30" href="javascript:grade(<s:property value="id" />)">
											<img border="0" src="../images/gradeHorario.png" />
										</a>
										<a  title="Exibir e cadastrar Horas AEP" border="30" href="javascript:aep(<s:property value="id" />)">
											<img border="0" src="../images/aep.png" />
										</a>
										<a  title="Exibir e cadastrar Horas Complementares" border="30" href="javascript:complementar(<s:property value="id" />)">
											<img border="0" src="../images/complementar.png" />
										</a>
										<a  title="Exibir e cadastrar disciplinas do aluno" border="30" href="javascript:dis(<s:property value="id" />)">
											<img border="0" src="../images/letra-d.gif" />
										</a>
										<a   title="Deletar aluno" border="30" href="javascript:del(<s:property value="id" />)">
											<img border="0" src="../images/lixeira.gif" />
										</a>
										<a   title="Gerar nova Senha" border="30" href="javascript:gerarSenha(<s:property value="id" />)">
											<img border="0" src="../images/key.png" />
										</a>
									</td>	
								</tr>					
							</table>
						</td>
					</tr>
					<tr>
						<td>
						<div class="conteudo">
						<s:form name="registra" onSubmit="return validaForm()" action="/painel/aluno!editar.java" method="get">
						<s:hidden id="aluno.id" name="alunoAlteraParams.id" value="%{id}" />
							
								<table width="100%">
								
									<tr>
										<td><b>Nome:</b> <s:textfield id="nome" name="alunoAlteraParams.nome" value="%{nome}"  /></td>
									</tr>
									
									<tr>
										<td><b>Status Matrícula:</b> <br><sx:autocompleter  headerKey=""  headerValue=""  id="statusMatricula" list="allStatusMatricula"  name="alunoAlteraParams.statusMatricula" value="%{statusMatricula}"/></td>
									</tr>
									
									<tr>
										<td><b>Curso:</b> <br><sx:autocompleter  headerKey=""  headerValue=""  id="curso" list="cursos"  name="alunoAlteraParams.cursoId" listValue="nome" listKey="id" value="%{curso.id}"/></td>
									</tr>
									
									<tr>
										<td><b>Período:</b> <s:textfield maxLength="2" id="periodo" name="alunoAlteraParams.periodo" value="%{periodo}"  /></td>
									</tr>
									
									<tr>
										<td><b>Matricula:</b> <s:textfield maxLength="8" id="matricula" name="alunoAlteraParams.matricula" value="%{matricula}"  /></td>
									</tr>
									
									<tr>
										<td><b>Login:</b> <s:textfield id="login" name="alunoAlteraParams.login" value="%{login}"  /></td>
									</tr>
									
									<tr>
										<td><b>E-mail:</b> <s:textfield id="email" name="alunoAlteraParams.email" value="%{email}"  />  </td>
									</tr>		
										
									<tr>
										<td><s:submit align="left" value="Alterar"></s:submit></td>
									</tr>
										
										
				
								</table>
							
						</s:form>
						</div>
						
						</td>
					</tr>
				
				</s:iterator>
			</table>
			<center style="font-size: 10px;color: grey;"><s:property value="%{tempoDeResposta}"/></center>
			
</body>
</html>