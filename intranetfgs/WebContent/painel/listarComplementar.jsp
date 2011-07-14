<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Horas AEP</title>
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

 	function delcomplementar(alunoId,horaId) {    
	     if (confirm('Excluir hora complementar?')) {    
	         location.href = "../painel/hora!deletaComplementar.java?horaComplementarDeletaParams.alunoId="+ alunoId+ "&horaComplementarDeletaParams.horaId=" + horaId; 
	     }  
	 }

	
</script>
</head>
<body>
<table border=0 width="100%">

	<tr>
	
		<td><img src="../images/arrow_categ.gif" />
		<a href="../painel/aluno!lista.java" style="color:#FF4500;text-align:left;border-style:none;">Lista de Alunos</a>
		</td>
		<td align="right"><a title="Novo Evento"
	href="../painel/hora!novaHoraComplementar.java?aluno.id=<s:property value="aluno.id"/>"> <img border="0"
	src="../images/novo.jpg" />Adicionar Hora Complementar</a>
		</td>
	
	</tr>

</table>


<br>
<br>
<b>Filtros de Pesquisa</b>
<hr></hr>
				
<s:form method="get" action="hora!listaComplementar.java">
	<s:hidden name="horaComplementarListaParams.id" value="%{aluno.id}"></s:hidden>
	<table>
		<tr>
			<td><b>Atividade:</b></td>
			<td><sx:autocompleter headerKey="-1" headerValue="Todos"  name="horaComplementarListaParams.atividade" cssStyle="width:300px;" list="atividades" listKey="id" listValue="nome" /></td>
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
<table width="100%"><tr ><td width="95%"><b>Horas Complementares </b></td><td width="5%"></td></tr></table>
<hr></hr>
<table width="100%">
	<tr>
			<td><b>Nome Evento</b></td>
			<td><b>Carga Horária</b></td>
			<td><b>Atividade</b></td>
			<td><b></b></td>
			<td><b></b></td>
			<td>	
				
							
				
				
			</td>
		</tr>
	<s:iterator value="horasComplementares" var="horaComplementar">
		<tr>
			
			<td><s:property value="titulo" /></td>
			<td><s:property value="totalHoras" /></td>
			<td><s:property value="atividade.nome" /></td>
			<td></td>
			<td>	
				
							
				<a title="Deletar Hora Complementar" href="javascript:delcomplementar(<s:property value="%{aluno.id}" />,<s:property value="%{id}" />)">
					<img border="0" src="../images/lixeira.gif" /> 
				</a>
				
			</td>
			<td>	
				
							
				<a style="border: 0px;" href="hora!enviarComprovanteEmail.java?geraComprovanteHoraComplementarParams.alunoId=<s:property value="aluno.id"/>&geraComprovanteHoraComplementarParams.horaId=<s:property value="id"/>">
				 <img style="border: 0px;" src="../images/email_go.png" />
				 </a>
				
			</td>
		</tr>

	</s:iterator>

</table>
<br>
<b>Total de Horas por Atividade</b>
<hr>
<div id="totalHora" style="text-align:center">


<table border="0" align="center" width="70%">
<tr>

	<td><b>Nome</b></td>
	<td><b>Horas do Aluno</b></td>
	<td><b>Status</b></td>

</tr>
<s:iterator value="horasAtividadeResultBean" var="horaComplementar">
<tr>

	<td><s:property value="nomeAtividade" /></td>
	<td><s:property value="totalHorasAlunoFormatado" /> / <s:property value="totalHorasAtividade" />h(s)</td>
	<td><c:if test="${(totalMinutosAluno / 60) ge totalHorasAtividade}">Completo</c:if><c:if test="${(totalMinutosAluno / 60) lt totalHorasAtividade}">Incompleto</c:if></td>
	
</tr>

</s:iterator>
<tr>

	<td></td>
	<td></td>
	<td></td>
	
</tr>
<tr>

	<td><b>Total de Horas (Aluno/Curso):</b></td>
	<td colspan="1"><s:property value="horasCursoResultBean.totalHorasAlunoFormatado"/> / <s:property value="horasCursoResultBean.totalHorasCurso"/>h(s)</td>
	<td><c:if test="${horasCursoResultBean.totalHorasAluno ge horasCursoResultBean.totalHorasCurso}">Aprovado</c:if><c:if test="${horasCursoResultBean.totalHorasAluno lt horasCursoResultBean.totalHorasCurso}">Reprovado</c:if></td>
	
</tr>
</table></div>
</body>
</html>