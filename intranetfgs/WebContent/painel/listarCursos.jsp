<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Cursos</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>

<script type="text/javascript">
 

$(document).ready(function(){ 

$('.conteudo').hide(); 

$('.exibir').each(function(i){    
$(this).click(function(){        
$('.conteudo').each(function(j){            
	if(i == j) 
		$(this).show('slow');        
});    
});
});
$('.ocultar').each(function(i){    
$(this).click(function(){        
$('.conteudo').each(function(j){            
if(i == j) $(this).hide('slow');        
});    
});
});
});

 	function delcurso(codigo) {    
	     if (confirm('Excluir o curso?')) {    
	         location.href = "../painel/curso!delete.java?cursoDeletaParams.id="+ codigo;
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
	 
	
	
</s:form>
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
			<td><s:form action="/painel/curso!altera.java">
				<div class="conteudo">
				<s:hidden name="cursoAlteraParams.id" id="cursoAlteraParams.id" value="%{id}"></s:hidden>
				<table>
					<tr>
						<td><b>Nome: </b><s:textfield id="cursoAlteraParams.nomeCurso" name="cursoAlteraParams.nomeCurso" value="%{nome}" /></td>
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
				</div>
			</s:form>
		</td>
		</tr>

	</s:iterator>
</table>
</body>
</html>