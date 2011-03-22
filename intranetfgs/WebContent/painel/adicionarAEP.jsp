<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
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
#disciplinasCurso {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}

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
<title>Intranet - Faculdade Gama & Souza | Adicionar Curso</title>
</head>
<body>

<div id="retorno" align="right">
<img src="../images/arrow_categ.gif"><a href="../painel/curso!lista.java" style="color:#FF4500;text-align:left;border-style:none;">Lista de Cursos</a>
</div>
<div id="experience" >
					<div id="explanation">
						<h3>
						<strong>
						Adicionar Hora AEP
						</strong>
						</h3>
						<p> 
						  Use os campos ao lado para adicionar uma hora AEP;
						</p>
						<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O Campo Nome em vermelhor é requerido.</p></div>
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
				<b>Dados do Aluno</b>
				<hr>
<s:form action="/painel/curso!novo.java" name="adicionar" onSubmit="return validaForm()" >
		
</s:form><br>
<b>Adicionar Hora AEP</b>
				<hr>
	<s:form action="/painel/hora!salvaAEP.java" name="adicionar" onSubmit="return validaForm()" >
		Data: <s:textfield id="data" name="horaAEPNovoParams.data" />
		Hora Inicial: <s:textfield id="nome" name="horaAEPNovoParams.horaInicial"></s:textfield>
		Hora Final: <s:textfield id="nome" name="horaAEPNovoParams.horaFinal"></s:textfield>
	<s:submit align="left" value="Adicionar Hora"></s:submit>
</s:form>
</div>		
</div>
</body>
</html>