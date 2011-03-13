<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Arquivos</title>
<script type="text/javascript">

function deletar(id){

	  if (confirm('Excluir o arquivo?')) {    
	         location.href ="../painel/upload!delete.java?id="+id;
	     } 
}
 
</script>

</head>
<body>
<div align="right">
	<a  title="Novo Evento" href="../painel/upload!prepare.java">
	<img border="0" src="../images/novo.jpg" />Upload Arquivo</a>
</div>
<b>Meus Arquivos</b>
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
<div id="result" >


	<table width="100%">
	
	
		<tr>
		
			<td><b>Nome do Arquivo</b></td>
			<td><b>Tamanho</b></td>
			<td><b>Matéria</b></td>
			<td><b>Turno</b></td>
			<td><b>Data de Envio</b></td>
			<td><b>Ano / Semestre</b></td>
			<td><b>Deletar</b></td>
		
		</tr>
		
		<s:iterator value="arquivos">
		
		
		
			<tr>
		
				<td><a style="border:0px;" href="<%= application.getContextPath() %>/arquivos/<s:property value='url' />"><s:property value="nome"/></a></td>
				<td><s:property value="uploadFileSize"/></td>
				<td><s:property value="disciplinaLetiva.disciplina.nome"/></td>
				<td><s:property value="disciplinaLetiva.turno"/></td>
				<td><s:date name="dataEnvio" format="dd/MM/yy HH:mm:ss" /></td>
				<td><s:property value="disciplinaLetiva.ano"/> / <s:property value="disciplinaLetiva.semestre"/></td>
				<td><a onclick="javascript:deletar(<s:property value='id' />)""><img src="../images/lixeira.gif" /></a></td>
				
				
		
			</tr>
		
		
		
		</s:iterator>
	
	
	</table>


</div>
</body>
</html>