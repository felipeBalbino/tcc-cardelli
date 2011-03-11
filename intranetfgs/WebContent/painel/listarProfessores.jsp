<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
    <%@taglib uri="/struts-dojo-tags" prefix="sx" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/intranet-core-tags.tld" prefix="intranet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Professores</title>
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
	     if (confirm('Excluir o professor?')) {    
	         location.href = "../painel/professor!delete.java?professor.id="+ codigo;
	     }  
	 }
	
</script>
</head>
<body>
<div align="right">
	<a  title="Novo Professor" href="../painel/professor!prepare.java">
	<img border="0" src="../images/novo.jpg" />Novo Professor</a>
</div>
<br>
<br>


<b>Professores</b>
<hr></hr>
<s:actionmessage/>
<s:actionerror/>
<table width="100%">
				<s:iterator value="professores" var="professor">
					<c:set var="userRules" value="${regras}"></c:set>
					<tr>
						<td>
							<table width="100%" bgcolor="#FAFAD2">
								<tr>
									<td width="1%"><img src="../images/event_icon.png" /></td>
									<td width="88%" style="font-size: 10px;font-weight:bold"> <s:property  value="nome" /> </td>
									<td width="11%" align="right">
										<a title="Exibir professor" class="exibir">
											<img src="../images/open.png" />
										</a>
										<a  title="Ocultar professor" class="ocultar">
											<img src="../images/close.png" />
										</a>
										<a   title="Deletar professor" border="30" href="javascript:del(<s:property value="id" />)">
											<img border="0" src="../images/lixeira.gif" />
										</a>
									</td>	
								</tr>					
							</table>
						</td>
					</tr>
					
								
					
					
					<tr>
						<td>
						<s:form action="/painel/professor!editar.java" method="get">
						<s:hidden id="professor.id" name="professor.id" value="%{id}" />
							<div class="conteudo">
								<table width="100%">
								
									<tr>
										<td><b>Nome:</b> <s:textfield id="professor.nome" name="professor.nome" value="%{nome}"  /></td>
									</tr>
									
									<tr>
										<td><b>Matricula:</b> <s:textfield id="professor.matricula" name="professor.matricula" value="%{matricula}"  /></td>
									</tr>
									
									<tr>
										<td><b>Login:</b> <s:textfield id="professor.login" name="professor.login" value="%{login}"  /></td>
									</tr>
									
									<tr>
										<td><b>Senha:</b> <s:textfield id="professor.senha" name="professor.senha" value="%{senha}"  /></td>
									</tr>
									
									<tr>
										<td><b>E-mail:</b> <s:textfield id="professor.email" name="professor.email" value="%{email}"  />  </td>
									</tr>
										
									<tr>
										<td><b>Regras</b><hr>
										
										<select id="rulesParam" name="rulesParam" multiple="multiple" size="10" >
  										
										
										<s:iterator value="allRules">
											
												<option ${intranet:contains(userRules,nome)}><s:property value="nome"/></option>
											
											</s:iterator>
										</select>* Segure o CTRL antes de clicar na lista.</td></tr>
											
										
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