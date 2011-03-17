<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
        <%@ taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Horários</title>
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

function deletar(id){

	  if (confirm('Excluir o horário?')) {    
	         location.href ="../painel/horario!delete.java?id="+id;
	     } 
}
	
</script>
</head>
<body>
<b>Campos para adicionar Horários</b>
<hr></hr>

<s:form action="/painel/horario!save.java">
	<table>
		<tr>
			<td>Hora Inicio:</td>
			<td><s:textfield name="horaInicio" /></td>
			<td>Hora Final:</td>
			<td><s:textfield name="horaFinal" /></td>
			<td>Ano:</td>		
			<td><sx:autocompleter headerKey="-1" headerValue="" id="ano" name="ano" list="anos" /></td>
			<td>Semestre:</td>
			<td><sx:autocompleter headerKey="-1" headerValue="" id="semestre" name="semestre" list="semestres" /></td>
			<td><s:submit value="Adicionar"></s:submit></td>
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
<b>Horários cadastrados</b>
<hr></hr>
<div id="result" >
	<table width="100%">
		<tr>
			<td><b>Id</b></td>
			<td><b>Ano</b></td>
			<td><b>Semestre</b></td>
			<td><b>Hora Inicial</b></td>
			<td><b>Hora Final</b></td>
			<td><b>Deletar</b></td>
		</tr>
		
		<s:iterator value="horarios">
			<tr>
				<td><s:property value="id"/></td>
				<td><s:property value="ano"/></td>
				<td><s:property value="semestre"/></td>	
				<td><s:property value="horaInicio"/></td>
				<td><s:property value="horaFim"/></td>	
	
				<td><a onclick="javascript:deletar(<s:property value='id' />)""><img src="../images/lixeira.gif" /></a></td>
			</tr>
		</s:iterator>
	</table>
</div>
</body>
</html>