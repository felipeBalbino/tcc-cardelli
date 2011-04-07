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
	document.getElementById("cargaHorariaComplementar").style.backgroundColor = "transparent";
 }

 function restartMensagemErro(){
                   document.getElementById("mensagem_ocultos").style.display = "none";
 }


 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.adicionar;
         var erro = false;
        
         //validar nome do curso
         if (d.nome.value == ""){
	           document.getElementById("nome").style.backgroundColor = "#FF6A6A";
                 d.nome.focus();
                 erro = true;
       		}  
         
         //validar Carga Horário Complementar
         if (d.cargaHorariaComplementar.value == ""){
	           document.getElementById("cargaHorariaComplementar").style.backgroundColor = "#FF6A6A";
                 d.cargaHorariaComplementar.focus();
                 erro = true;
       		} 
        
         
		if (erro != true) {
         	return true;
		}else{
			document.getElementById("mensagem_ocultos").style.display = "inline";
         	return false;
		}
}
 
 function Numero(e)
 {
	 navegador = /msie/i.test(navigator.userAgent);
	 if (navegador)
	 	var tecla = event.keyCode;
	 else
	 	var tecla = e.which;
	 if(tecla > 47 && tecla < 58) // numeros de 0 a 9
		 return true;
	 else
	 {
	 if (tecla != 8) // backspace
	 return false;
	 else
	 return true;
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
						Adicionar Curso
						</strong>
						</h3>
						<p> 
						  Use os campos ao lado para adicionar um curso. 
						</p>
						<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  Campo(s) em vermelho requerido(s).</p></div>
						
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


	<s:form action="/painel/curso!novo.java" name="adicionar" onSubmit="return validaForm()" >
		Nome: <s:textfield id="nome" name="cursoNovoParams.nomeCurso" value=""></s:textfield><br>
		Carga Horário Complementar: <s:textfield id="cargaHorariaComplementar" name="cursoNovoParams.cargaHorariaComplementar" value="" maxLength="8"  onKeyPress="return Numero(event);"></s:textfield>
			<br></br>
		<b>Selecione as disciplinas lecionadas neste curso</b>
			<hr></hr>
		<select id="disciplinasCurso" name="cursoNovoParams.disciplinasCurso" multiple="multiple" size="10" >
		<s:iterator value="allDisciplinas" status="st" var="disciplina">
	
		<option><s:property value="nome"/></option>
												
	</s:iterator> 
	</select>
	<s:submit align="left" value="Adicionar"></s:submit>
</s:form>
</div>		
</div>
</body>
</html>