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
	     if (confirm('Excluir a disciplina letiva?')) {    
	         location.href = "../painel/letiva!delete.java?disciplinaLetiva.id="+ codigo;
	     }  
	 }
	
</script>
</head>
<body>
<div align="right">
	<a  title="Nova Disciplina Letiva" href="../painel/letiva!prepare.java">
	<img border="0" src="../images/novo.jpg" />Nova Disciplina Letiva</a>
</div>

<b>Filtros de Pesquisa</b>
<hr></hr>

<s:form action="/painel/letiva!lista.java">
	<table>
		<tr>
			<td>Disciplinas Letivas:</td>
			<td><s:textfield name="disciplinaLetivaSearchParams.disciplinaNome" id="disciplinaLetivaSearchParams.disciplinaNome"></s:textfield></td>
			<td>Ano:</td>		
			<td><sx:autocompleter headerKey="-1" headerValue="" id="disciplinaLetivaSearchParams.ano" list="anos" name="disciplinaLetivaSearchParams.ano" /></td>
			<td>Semestre:</td>
			<td><sx:autocompleter headerKey="-1" headerValue="" id="disciplinaLetivaSearchParams.semestre" name="disciplinaLetivaSearchParams.semestre" list="semestres" /></td>
			<td>Turno:</td>
			<td><sx:autocompleter headerKey="-1" headerValue="" id="disciplinaLetivaSearchParams.turno" name="disciplinaLetivaSearchParams.turno" list="turnos" /></td>
			<td>Professor:</td>
			<td><sx:autocompleter headerKey="-1" headerValue="" id="disciplinaLetivaSearchParams.professor" name="disciplinaLetivaSearchParams.professor" list="professores" listValue="nome" listKey="id"/></td>
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
<b>Disciplinas Letivas</b>
<hr></hr>
<table width="100%">
				<s:iterator value="disciplinasLetivas" var="disciplinaLetiva">
				
					<tr>
						<td>
							<table width="100%" bgcolor="#FAFAD2">
								<tr>
									<td width="1%"><img src="../images/event_icon.png" /></td>
									<td width="91%" style="font-size: 10px;font-weight:bold"> 
											<s:property  value="disciplina.nome" />
										 -   <s:property  value="ano" />.<s:property  value="semestre" />/<s:property  value="turno" />
										 -   <s:property  value="professor.nome" />
									</td>
									<td width="8%" align="right">
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
									<s:property  value="disciplinaLetiva.horarios.DiaSemana" />
								</tr>					
							</table>
						</td>
					</tr>
					
					
					
				 	<tr>
						<td>
						<s:form action="../painel/letiva!alterar.java">
							<div class="conteudo">
								<table>		
									<tr>
										<td><s:hidden name="id" id="id" value="%{id}"></s:hidden></td>
									</tr>
									<tr>	
										<td>Professor:<br><sx:autocompleter headerKey="-1" headerValue=""  id="professor" list="professores" name="professor.id"  listKey="id" listValue="nome" /><br></td>	
									</tr>	
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