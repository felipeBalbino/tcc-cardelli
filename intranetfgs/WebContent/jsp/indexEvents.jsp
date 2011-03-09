<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Intranet - Faculdade Gama & Souza | Principal</title>
<meta http-equiv="Content-Type" content="text/html;">
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


</script>
</head>
<body>
<center><img src="../images/banner.jpg" style="border:1px;border-style:dotted; height:180px;border-color:#CCCCCC" /><br></center>
<br>

<table width="100%">
	<tr>
		<td><b>Eventos</b><hr></td>
		<td><b>Notícias</b><hr></td>
	</tr>
	
	<tr>
		<td valign="top">
			<table width="100%">
				<s:iterator value="eventos">
					<tr>
						<td>
							<table width="100%" bgcolor="#FAFAD2">
								<tr>
									<td width="1%"><img src="../images/event_icon.png" /></td>
									<td width="91%" style="font-size: 10px;font-weight:bold"> <s:property  value="title" /> </td>
									<td width="8%" align="right"><a class="exibir"><img src="../images/open.png" /></a><a  class="ocultar"><img src="../images/close.png" /></a></td>	
								</tr>					
							</table>
						</td>
					</tr>
								
					<tr>
						<td align="center"><b>Publicado por:</b> <s:property value="autor.nome" /> <b>em</b> <s:date name="datahoraPublicacao" format="dd/MM/yyyy HH:mm:ss" />	<br></td>
					</tr>
					
					<tr>
						<td>
						
							<div class="conteudo">
								<table>
									<tr>
										<td><b>Coordenação:</b> <s:property escapeHtml="false" value="coordenacao" /></td>
									</tr>
					
									<tr>
										<td><b>Início:</b> <s:date name="datahoraInicio" format="dd/MM/yyyy HH:mm" /> - <b>Fim:</b> <s:date name="datahoraFim" format="dd/MM/yy HH:mm" /></td>
									</tr>	
					
									<tr>
										<td><b>Local: </b><s:property escapeHtml="false" value="local" /></td>
									</tr>
					
									<tr>	
										<td><b>Público Alvo: </b><s:property escapeHtml="false" value="publicoAlvoComQuebra" /></td>	
									</tr>
					
									<tr>
	
										<td><b>Obs: </b><s:property escapeHtml="false" value="obsComQuebra" /></td>
										
									</tr>
								</table>
							</div>
						
						</td>
					</tr>

				</s:iterator>
			</table>
		
		
		</td>
		
		<td valign="top">
			<table width="100%">
				<s:iterator value="noticias">
					<tr>
						<td>
							<table width="100%" bgcolor="#FAFAD2">
								<tr>
									<td width="1%"><img src="../images/news_icon.png" /></td>
									<td width="91%" style="font-size: 10px;font-weight:bold"> <s:property  value="title" /> </td>
									<td width="8%" align="right"><a class="exibir"><img src="../images/open.png" /></a><a  class="ocultar"><img src="../images/close.png" /></a></td>	
								</tr>					
							</table>
						</td>
					</tr>
								
					<tr>
						<td align="center"><b>Publicado por:</b> <s:property value="autor.nome" /> <b>em</b> <s:date name="datahoraPublicacao" format="dd/MM/yyyy HH:mm:ss" />	<br></td>
					</tr>
					
					<tr>
						<td>
						
							<div class="conteudo">
								<table>
									<tr>
										<td><s:property escapeHtml="false" value="mensagemComQuebra"  />
									</tr>
					
								</table>
							</div>
						
						</td>
					</tr>

				</s:iterator>
			</table>
		
		
		</td>
	
	</tr>



</table>


</body>
</html>