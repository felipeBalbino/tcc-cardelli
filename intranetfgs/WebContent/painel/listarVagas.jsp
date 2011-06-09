<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">

#titulo {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#coorden {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#local {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#alvo {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#obs {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}


 



</style>
<sx:head/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet - Faculdade Gama & Souza | Eventos</title>
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
	     if (confirm('Excluir o evento?')) {    
	         location.href = "../painel/evento!deleta.java?eventoDeletaParams.id="+ codigo;
	     }  
	 }

 	function desconfirmar(codigo) { 
 		if (confirm('Deseja marcar como não confirmado?')) {    
	         location.href = "../painel/vaga!desconfirmar.java?idVaga="+ codigo;
	     } 
 	}

 	function confirmar(codigo) { 
 		if (confirm('Deseja marcar como confirmado?')) {    
	         location.href = "../painel/vaga!desconfirmar.java?idVaga="+ codigo;
	     } 
 	}
 	function restartTrs(){
 		document.getElementById("titulo").style.backgroundColor = "transparent";
 	 	document.getElementById("coorden").style.backgroundColor = "transparent";

 	 }

 	 function restartMensagemErro(){
 	                   document.getElementById("mensagem_ocultos").style.display = "none";
 	 }
 	 	
 	function validaForm(){
 		 restartTrs();
 	         restartMensagemErro();
 	         d = document.alterar;
 	         var erro = false;
 	        
 	         //validar arquivo
 	         if (d.titulo.value == ""){
 		           document.getElementById("titulo").style.backgroundColor = "#FF6A6A";
 	                 d.titulo.focus();
 	                 erro = true;
 	       		}
 	         
 	         
 	         //validar namearquivo
 	         if (d.coorden.value == ""){
 		           document.getElementById("coorden").style.backgroundColor = "#FF6A6A";
 	                   d.coorden.focus();
 	                   erro = true;
 	         }
 	     
 	         


 	         
 			if (erro != true) {
 	         	return true;
 			}else{
 				document.getElementById("mensagem_ocultos").style.display = "inline";
 	         	return false;
 			}
 	}
</script>
</head>
<body>
<div align="right">
	<a  title="Novo Evento" href="../painel/adicionarEvento.jsp">
	<img border="0" src="../images/novo.jpg" />Cadastrar Vaga</a>
</div>
<br>
<br>


<b>Vagas</b>
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
				<div id="mensagem_ocultos" style="display:none;color:red;"><p align="center">O(s) Campo(s) em vermelhor é(são) requirido(s).</p></div>
				<table width="100%">
				<s:iterator value="vagas">
					<tr>
						<td>
							<table width="100%" bgcolor="#FAFAD2">
								<tr>
									<td width="1%"><img src="../images/event_icon.png" /></td>
									<td width="75%" style="font-size: 10px;font-weight:bold"> 
										<s:property  value="cargo" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<s:text name="Vagas: "/><s:property  value="quantidadeDevagas" /> </td>
										
										
									</td> 
									
									<td width="11%" align="right">
										<s:if test="confirmacao">
											<a   title="Status confirmado" border="30"href="javascript:desconfirmar(<s:property value="id" />)">
												<img border="0" src="../images/ativo.png" />
											</a>
										</s:if>
										<s:else>
											<a   title="Status Não Confirmado" border="30" href="javascript:confirmar(<s:property value="id" />)">
												<img border="0" src="../images/inativo.png" />
											</a>
										</s:else>
										
										<a title="Exibir vaga" class="exibir">
											<img src="../images/open.png" />
										</a>
										<a  title="Ocultar vaga" class="ocultar">
											<img src="../images/close.png" />
										</a>
										<a   title="Deletar vaga" border="30" href="javascript:del(<s:property value="id" />)">
											<img border="0" src="../images/lixeira.gif" />
										</a>
									</td>	
								</tr>					
							</table>
						</td>
					</tr>
								
					<tr>
						<td align="center"><b>Publicado por:</b> <s:property value="publicador.nome" /> <b>em</b> <s:date name="datahoraPublicacao" format="dd/MM/yyyy HH:mm:ss" /></td>
					</tr>
					
					<tr>
						<td>
						<div class="conteudo">
						<s:form name="alterar" onSubmit="return validaForm()" action="/painel/evento!altera.java"  >
						
							
								<table>
									<tr>
										<td><s:hidden name="eventoAlteraParams.id" id="id" value="%{id}"></s:hidden></td>
									</tr>
									<tr>
										<td><b>Cargo:</b> <s:textfield cssStyle="width:80%" id="cargo" name="vagaAlteraParams.cargo" value="%{cargo}" maxLength="40" /></td>
									</tr>
									
									<tr>
										<td><b>Empresa:</b> <s:select list="empresas" id="empresa" name="vagaAlteraParams.empresa"  headerKey="" headerValue="Nenhuma"/></td>
									</tr>
									
									
								</table>
								
						
						</s:form>
							</div>
						</td>
						
					</tr>
				
				</s:iterator>
			</table>
			
			<center style="font-size: 10px;color: grey;"><s:property value="%{tempoDeResposta}"/></center>
</body>
</html>