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

<div id="retorno" align="right">
	<img src="../images/arrow_categ.gif">
		<a href="../painel/aluno!lista.java"  style="color:#FF4500;text-align:left;border-style:none;">
			Listar Alunos
		</a>
	
	<img src="../images/arrow_categ.gif">
		<a href="../painel/hora!prepareCadastroAEP.java?aluno.id=<s:property value="aluno.id"/>"   style="color:#FF4500;text-align:left;border-style:none;">
			Adicionar Hora AEP
		</a>
</div>

<br>
<br>
<b>Filtros de Pesquisa</b>
<hr></hr>
				
<s:form action="/painel/curso!lista.java">
	<table>
		<tr>
			<td>Data:</td>
			<td><s:textfield name="cursoSearchParams.cursoNome" id="cursoSearchParams.cursoNome"></s:textfield></td>
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
<b>Horas </b>
<hr></hr>
<table width="100%">
	<tr>
			<td><b>Data</b></td>
			<td><b>Hora Início</b></td>
			<td><b>Hora Fim</b></td>
			<td><b>Total de Horas</b></td>
			<td>	
				
							
				
				
			</td>
		</tr>
	<s:iterator value="horasAEP" var="horaAEP">
		<tr>
			<td><s:date name="data" format="dd/MM/yyyy" /></td>
			<td><s:date name="horaInicio" format="hh:mm:ss" /></td>
			<td><s:date name="horaFim" format="hh:mm:ss" /></td>
			<td><s:property value="difHora" /></td>
			<td>	
				
							
				<a title="Deletar evento" href="javascript:delcurso(<s:property value="%{id}" />)">
					<img border="0" src="../images/lixeira.gif" /> 
				</a>
				
			</td>
		</tr>

	</s:iterator>
	
</table>
<br><br>
<b>Total de Horas AEP: </b><s:property value="totalHorasAEPAluno" />
</body>
</html>