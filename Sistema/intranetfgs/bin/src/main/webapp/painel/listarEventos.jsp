<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<sx:head/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet - Faculdade Gama & Souza | Eventos</title>


<script type="text/javascript">
 

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
	<img border="0" src="../images/novo.jpg" />Novo Evento</a>
</div>
<br>
<br>


<b>Eventos</b>
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
				<s:iterator value="eventos">
					<tr>
						<td>
							<table width="100%" bgcolor="#FAFAD2">
								<tr>
									<td width="1%"><img src="../images/event_icon.png" /></td>
									<td width="88%" style="font-size: 10px;font-weight:bold"> <s:property  value="title" /> </td>
									<td width="11%" align="right">
										<a title="Exibir evento" class="exibir">
											<img src="../images/open.png" />
										</a>
										<a  title="Ocultar evento" class="ocultar">
											<img src="../images/close.png" />
										</a>
										<a   title="Deletar evento" border="30" href="javascript:delEvento(<s:property value="id" />)">
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
						<div class="conteudo">
						<s:form name="alterar" onSubmit="return validaForm()" action="/painel/evento!altera.java"  >
						
							
								<table>
									<tr>
										<td><s:hidden name="eventoAlteraParams.id" id="id" value="%{id}"></s:hidden></td>
									</tr>
									<tr>
										<td><b>Titulo:</b> <s:textfield cssStyle="width:80%" id="titulo" name="eventoAlteraParams.title" value="%{title}" maxLength="40" /></td>
									</tr>
									
									<tr>
										<td><b>Coordenação:</b> <s:textfield cssStyle="width:80%" id="coorden" name="eventoAlteraParams.coordenacao" value="%{coordenacao}" /></td>
									</tr>
					
									<tr>
										<td>
										<b>Data Início:</b> 
											<sx:datetimepicker  cssStyle="width:190px" name="eventoAlteraParams.dataInicio" displayFormat="dd/MM/yyyy" value="%{dataInicio}"/>
											 - <b>Data Fim:</b> 
										    <sx:datetimepicker  cssStyle="width:190px" name="eventoAlteraParams.dataFim" displayFormat="dd/MM/yyyy" value="%{dataFim}"/>
										</td>
									</tr>	
									
									
									<tr>
										<td>
										Hora Inicio: <s:textfield id="horainicio" name="eventoAlteraParams.horaInicio" maxlength="8" size="10" value="%{horaInicio}"/>
										Hora Fim: <s:textfield  id="horafinal"name="eventoAlteraParams.horaFim" maxlength="8" size="10" value="%{horaFim}"/>
										</td>
									</tr>	
							
									
									<tr>
										<td><b>Local: </b> 
													<s:textfield cssStyle="width:80%" name="eventoAlteraParams.local" id="local" value="%{local}"></s:textfield>
												</td>
									</tr>	
											
									<tr>
										<td><b>Publico Alvo:</b>  
													<s:textarea cssStyle="width:80%; height:50px;" name="eventoAlteraParams.publicoAlvo" id="alvo" value="%{publicoalvo}"></s:textarea>
												</td>
									</tr>	
											
									<tr>
										<td><b>Obs:</b>  
											<s:textarea cssStyle="width:80%; height:80px;" name="eventoAlteraParams.observacoes" id="obs" value="%{obs}"></s:textarea>
										</td>
									</tr>	
									
									
									<tr>
										<td><s:submit align="left" value="Alterar"></s:submit></td>
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