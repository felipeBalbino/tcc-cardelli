<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">

#mensagem {background:transparent  no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#titulo {background:transparent  no-repeat 2% 50%; padding:2px 0px 2px 0px;}

}


</style>
<script type="text/javascript">

 
 function contarCaracteres(box,valor,campospan){
		var conta = valor - box.length;
		document.getElementById(campospan).innerHTML = "Você ainda pode digitar " + conta + " caracteres";
		if(box.length >= valor){
			document.getElementById(campospan).innerHTML = "Você não pode mais digitar..";
			document.getElementById("mensagem").value = document.getElementById("mensagem").value.substr(0,valor);
		}	
	}

 function contarCaracterestitulo(box,valor,campospan){
		var conta = valor - box.length;
		document.getElementById(campospan).innerHTML = "";
		if(box.length >= valor){
			document.getElementById(campospan).innerHTML = "Você não pode mais digitar - (Limite 30 caracteres).";
			document.getElementById("titulo").value = document.getElementById("titulo").value.substr(0,valor);
		}	
	}
	
 function mostrarResultado(box,num_max,campospan){
 	var contagem_carac = box.length;
 	if (contagem_carac != 0){
 		document.getElementById(campospan).innerHTML = contagem_carac + " caracteres digitados";
 		if (contagem_carac == 1){
 			document.getElementById(campospan).innerHTML = contagem_carac + " caracter digitado";
 		}
 		if (contagem_carac >= num_max){
 			document.getElementById(campospan).innerHTML = "Limite de caracteres excedido!";
 		}
 	}else{
 		document.getElementById(campospan).innerHTML = "";
 	}
 }
 

</script>
<sx:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Notícias</title>
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
	     if (confirm('Excluir Noticia?')) {    
	         location.href = "../painel/noticia!delete.java?noticia.id="+ codigo;
	     }  
	 }
	
</script>
</head>
<body>
<div align="right">
	<a  title="Novo Evento" href="../painel/adicionarNoticia.jsp">
	<img border="0" src="../images/novo.jpg" />Nova Notícia</a>
</div>
<br>
<br>


<b>Notícias</b>
<hr></hr>
<s:actionmessage/>
<s:actionerror/>
<table width="100%">
				<s:iterator value="noticias">
					<tr>
						<td>
							<table width="100%" bgcolor="#FAFAD2">
								<tr>
									<td width="1%"><img src="../images/event_icon.png" /></td>
									<td width="91%" style="font-size: 10px;font-weight:bold"> <s:property  value="title" /> </td>
									<td width="8%" align="right">
										<a title="Exibir evento" class="exibir">
											<img src="../images/open.png" />
										</a>
										<a  title="Ocultar evento" class="ocultar">
											<img src="../images/close.png" />
										</a>
										<a  href="javascript:del(<s:property value="id" />)">
											<img border="0" src="../images/lixeira.gif" />
										</a>
									</td>	
								</tr>					
							</table>
						</td>
					</tr>
								
					<tr>
						<td align="center"><b>Publicado por:</b> <s:property value="autor.nome" /> <b>em</b> <s:date name="datahoraPublicacao" format="dd/MM/yyyy HH:mm:ss" /></td>
					</tr>
					
					<tr>
						<td>
						<s:form action="/painel/noticia!altera.java" >
						<s:hidden id="noticia.id" name="noticia.id" value="%{id}" />
							<div class="conteudo">
								<table width="100%">
								
									<tr>
										<td><b>Titulo:</b> <s:textfield cssStyle="width:100%"  id="titulo" name="noticia.title" value="%{title}" onkeypress="contarCaracterestitulo(this.value,30,'sprestante2')"/></td>
										
									</tr>
							
									<tr><td><span id="sprestante2" style="font-family:Georgia;"></span></td><tr>
									<tr>
										<td><b>Mensagem:</b> <s:textarea cssStyle="width:100%; height:200px;" id="mensagem" name="noticia.mensagem" value="%{mensagem}" onkeypress="mostrarResultado(this.value,200,'spcontando');contarCaracteres(this.value,140,'sprestante')"> </s:textarea></td>
									<tr>
									
									<tr>
										<td>
											<span id="spcontando" style="font-family:Georgia;"></span><br />
											<span id="sprestante" style="font-family:Georgia;"></span>
										</td>
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