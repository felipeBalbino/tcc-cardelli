<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
<%@ taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Cursos</title>

<script type="text/javascript">
 


 	function delcurso(codigo) {    
	     if (confirm('Excluir o curso?')) {    
	         location.href = "../painel/curso!delete.java?cursoDeletaParams.id="+ codigo;
	     }  
	 }

 	

 	function restartTrs(){
 		document.getElementById("nome").style.backgroundColor = "transparent";
 		document.getElementById("cargaHorariaComplementar").style.backgroundColor = "transparent";
 	 }

 	 function restartMensagemErro(){
 	                   document.getElementById("mensagem_ocultos").style.display = "none";
 	 }


 	 function validaForm(){
 		 restartTrs();
 	         restartMensagemErro();
 	         d = document.altera;
 	         var erro = false;

 	         //validar nome do curso
 	         if ( document.getElementById("nome").value == ""){
 		           document.getElementById("nome").style.backgroundColor = "#FF6A6A";
 	                 d.nome.focus();
 	                 erro = true;
 	       	 }  
 	         
 	         //validar Carga Horário Complementar
 	         if (document.getElementById("cargaHorariaComplementar").value == ""){
 		           document.getElementById("cargaHorariaComplementar").style.backgroundColor = "#FF6A6A";
 	                 d.cargaHorariaComplementar.focus();
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
<div align="right"><a title="Novo Evento"
	href="../painel/curso!execute.java"> <img border="0"
	src="../images/novo.jpg" />Novo Curso</a></div>
<br>
<br>
<b>Filtros de Pesquisa</b>
<hr></hr>
				
<s:form action="/painel/curso!lista.java">
	<table>
		<tr>
			<td>Curso:</td>
			<td><s:textfield name="cursoSearchParams.cursoNome" id="cursoSearchParams.cursoNome"></s:textfield></td>
			<td>Disciplina:</td>
			<td><sx:autocompleter id="cursoSearchParams.disciplinaNome" name="cursoSearchParams.disciplinaNome" list="allDisciplinas" listValue="nome" listKey="nome" headerKey="" headerValue=""></sx:autocompleter></td>
			<td><s:submit value="Pesquisar"></s:submit></td>
		</tr>
	</table>  
<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  Campo(s) em vermelho requerido(s).</p></div>
						
<br>
</s:form>

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
<b>Cursos</b>
<hr></hr>
<table width="100%">
	<s:iterator value="cursos" var="curso">
	<c:set var="cursoDisciplinas" value="${disciplinas}"></c:set>
		<tr>
			<td>	
				<table width="100%" bgcolor="#FAFAD2">
					<tr>
						<td width="1%">
							<img src="../images/event_icon.png" />
						</td>
						
						<td width="88%" style="font-size: 10px; font-weight: bold">
							<s:property value="nome" />
						</td>
					
						<td width="11%" align="right">
							<a title="Exibir evento" class="exibir"> 
								<img src="../images/open.png" /> 
							</a> 
							
							<a title="Ocultar evento" class="ocultar"> 
								<img src="../images/close.png" /> 
							</a> 
							
							<a title="Deletar evento" href="javascript:delcurso(<s:property value="%{id}" />)">
								<img border="0" src="../images/lixeira.gif" /> 
							</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td>
			<div class="conteudo">
			<s:form action="/painel/curso!altera.java" name="altera" onSubmit="return validaForm()">
				
				<s:hidden name="cursoAlteraParams.id" id="cursoAlteraParams.id" value="%{id}"></s:hidden>
				<table>
					<tr>
						<td><b>Nome: </b><s:textfield id="nome" name="cursoAlteraParams.nomeCurso" value="%{nome}" /></td>
					</tr>
					
					<tr>
						<td><b>Carga Horário Complementar: </b><s:textfield id="cargaHorariaComplementar" name="cursoAlteraParams.cargaHorariaComplementar" value="%{cargaHorariaComplementar}" maxLength="8" /></td>
					</tr>
				
					<tr><td>
					<select id="cursoAlteraParams.disciplinas" name="cursoAlteraParams.disciplinas" multiple="multiple" size="10" >
  										
										
										<s:iterator value="allDisciplinas">
											
												<option ${intranet:containsDisciplina(cursoDisciplinas,nome)}><s:property value="nome"/></option>
											
											</s:iterator>
						</select>* Segure o CTRL antes de clicar na lista.</td></tr>
					
						
					
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