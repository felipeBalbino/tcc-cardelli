<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>   
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
        <%@ taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Disciplinas</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>

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

 	function del(codigo) {    
	     if (confirm('Excluir a disciplina?')) {    
	         location.href = "../painel/materia!delete.java?disciplinaDeletaParams.id="+ codigo;
	     }  
	 }
	
</script>
</head>
<body>
<div align="right">
	<a  title="Novo Evento" href="../painel/materia!novo.java">
	<img border="0" src="../images/novo.jpg" />Nova Disciplina</a>
</div>

<b>Filtros de Pesquisa</b>
<hr></hr>

<s:form action="/painel/materia!lista.java">
	<table>
		<tr>
			<td>Disciplina:</td>
			<td><s:textfield name="disciplinaSearchParams.disciplinaNome" id="disciplinaSearchParams.disciplinaNome"></s:textfield></td>
			<td>Curso:</td>
			<td><sx:autocompleter name="disciplinaSearchParams.cursoNome" headerKey="" headerValue="" list="allCursos" listValue="nome" listKey="nome">
				
			</sx:autocompleter></td>
			<td><s:submit value="Pesquisar"></s:submit></td>
		</tr>
	</table>  
	
	
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
<b>Disciplinas</b>
<hr></hr>
<table width="100%">
				<s:iterator value="disciplinas" var="disciplina">
				<c:set var="cursoDisciplinas" value="${cursos}"></c:set>
					<tr>
						<td>
							<table width="100%" bgcolor="#FAFAD2">
								<tr>
									<td width="1%"><img src="../images/event_icon.png" /></td>
									<td width="88%" style="font-size: 10px;font-weight:bold"> <s:property  value="nome" /> </td>
									<td width="11%" align="right">
										<a title="Exibir evento" class="exibir">
											<img src="../images/open.png" />
										</a>
										<a  title="Ocultar evento" class="ocultar">
											<img src="../images/close.png" />
										</a>
										<a   title="Deletar evento" href="javascript:del(<s:property value="id" />)">
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
						<s:form action="/painel/materia!alterar.java">
							
							<s:hidden name="disciplinaAlteraParams.id" id="disciplinaAlteraParams.id" value="%{id}"></s:hidden>
				
								<table>	
									<tr>
										<td>Disciplina:<br><s:textfield value="%{nome}" id="disciplinaAlteraParams.nome" name="disciplinaAlteraParams.nome"></s:textfield></td>
									</tr>			
									<tr>
										<td>Cursos:<br><select id="disciplinaAlteraParams.cursos" name="disciplinaAlteraParams.cursos" multiple="multiple" size="10" >
												<s:iterator value="allCursos">
													<option ${intranet:containsCurso(cursoDisciplinas,nome)}><s:property value="nome"/></option>
												</s:iterator>
											</select>
											<br>* Segure o CTRL antes de clicar na lista.
										</td>
									</tr>
									<tr>
										<td>
											<s:submit align="left" value="Alterar"/>
										</td>
									</tr>		
								</table>
							
						</s:form>
						</div>
						</td>
					</tr>
					
				</s:iterator>
			</table>
			
			<center style="font-size: 10px"><s:property value="%{tempoDeResposta}"/></center>
</body>
</html>