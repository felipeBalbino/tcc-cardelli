<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#experience { 
padding:30px; 
position:relative;
padding:17px 0px 0 39px;
}


#explanation { 
float:left;
width:340px;
}

#nome {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#cursosParam {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}

#contact-form { 
border-left:1px solid #313b45; 
margin-left:370px; 
padding-left:30px; 
width:295px; 
}

</style>
<script type="text/javascript">


function restartTrs(){
	document.getElementById("nome").style.backgroundColor = "transparent";
 }

 function restartMensagemErro(){
                   document.getElementById("mensagem_ocultos").style.display = "none";
 }


 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.adicionar;
         var erro = false;
        
         //validar arquivo
         if (d.nome.value == ""){
	           document.getElementById("nome").style.backgroundColor = "#FF6A6A";
                 d.nome.focus();
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Adicionar Disciplina</title>
</head>
<body>
<div id="retorno">
<img src="../images/arrow_categ.gif"><a href="../painel/materia!lista.java" style="color:#FF4500;text-align:left;border-style:none;">Lista de Disciplinas</a>
</div>
<div id="experience" >
					<div id="explanation">
						<h3>
						<strong>
						Adicionar Disciplina
						</strong>
						</h3>
						<p> 
    					Use o formulário ao lado para cadastrar uma nova disciplina, em um ou mais cursos cadastrados.
						</p>
						<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O Campo Disciplina em vermelhor é requerido.</p></div>
						<br>
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
					</div>	
				
				<div id="contact-form">	




<s:form action="/painel/materia!save.java" method="post" name="adicionar" onSubmit="return validaForm()" >
	<table>
	
		<tr><td>Disciplina:<br></td> </tr>
		<tr><td><s:textfield id="nome" name="materia.nome" cssStyle="width:200px"></s:textfield><br></td></tr>
		<tr><td><b><s:text name="Selecione os cursos que possuem esta disciplina"/></b><hr></hr><br></td></tr>
		<tr><td><select id="cursosParam" name="cursosParam" multiple="multiple" size="10" >
			<s:iterator value="cursos" status="st" var="curso">
				<option><s:property value="nome"/></option>									
			</s:iterator> 
		</select>
		<br>
		<br>
		</td></tr>
		<tr><td><s:submit title="Cadastrar Matéria" value="Cadastrar Matéria"></s:submit></td></tr>
	
	</table>
</s:form>
</div>		
</div>
</body>
</html>