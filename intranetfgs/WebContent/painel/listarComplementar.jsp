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
<title>Intranet - Faculdade Gama & Souza | Horas AEP</title>
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
	src="../images/novo.jpg" />Adicionar Hora Complementar</a></div>
<br>
<br>
<b>Filtros de Pesquisa</b>
<hr></hr>
				
<s:form action="/painel/curso!lista.java">
	<table>
		<tr>
			<td>Atividade:</td>
			<td><sx:autocompleter/></td>
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
<b>Horas Complementares </b>
<hr></hr>
<table width="100%">
	<tr>
			<td><b>Nome Evento</b></td>
			<td><b>Carga Horária</b></td>
			<td><b>Atividade</b></td>
			<td><b></b></td>
			<td>	
				
							
				
				
			</td>
		</tr>
	<s:iterator value="horasComplementares" var="horaComplementar">
		<tr>
			
			<td><s:property value="nomeEvento" /></td>
			<td><s:property value="numeroHoras" /></td>
			<td><s:property value="atividade.nome" /></td>
			<td></td>
			<td>	
				
							
				<a title="Deletar evento" href="javascript:delcurso(<s:property value="%{id}" />)">
					<img border="0" src="../images/lixeira.gif" /> 
				</a>
				
			</td>
		</tr>

	</s:iterator>

</table>
<div id="totalHora" style="text-align:center">
<table border="1">

<tr>
<td colspan="2" style="text-align:center"><b>Total de Horas por Atividade</b></td>
</tr>
<tr>

	<td><b><center>Nome</center></b></td>
	<td><b><center>Total</center></b></td>

</tr>


</table></div>
</body>
</html>