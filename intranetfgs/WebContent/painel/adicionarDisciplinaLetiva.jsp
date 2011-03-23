<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
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


#namearquivo {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#arquivo {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#semestre {background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#turno {background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#materia {background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}
#professorid{background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; padding:2px 0px 2px 25px;}




 
#contact-form { 
border-left:1px solid #313b45; 
margin-left:370px; 
padding-left:30px; 
width:295px; 
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Adicionar Disciplina Letiva</title>

<script type="text/javascript">




function restartTrs(){

 	document.getElementById("turno").style.backgroundColor = "transparent";
	document.getElementById("semestre").style.backgroundColor = "transparent";
 	document.getElementById("materia").style.backgroundColor = "transparent";
 	document.getElementById("professor").style.backgroundColor = "transparent";
 }

 function restartMensagemErro(){
                   document.getElementById("mensagem_ocultos").style.display = "none";
 }

 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.adicionar;
         var erro = false;
        

         //validar turno
         if (d.turno.value == -1){
                  document.getElementById("turno").style.backgroundColor = "#FF6A6A";
                  d.turno.focus();
                  erro = true;
         }

       //validar semestre
         if (d.semestre.value == -1){
                  document.getElementById("semestre").style.backgroundColor = "#FF6A6A";
                  d.semestre.focus();
                  erro = true;
         }

       //validar materia
         if (d.materia.value == -1){
                  document.getElementById("materia").style.backgroundColor = "#FF6A6A";
                  d.materia.focus();
                  erro = true;
         }
       //validar professor
         if (d.professor.value == -1){
                  document.getElementById("professor").style.backgroundColor = "#FF6A6A";
                  d.professor.focus();
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
<div id="retorno" align="left">
<img src="../images/arrow_categ.gif"><a href="../painel/letiva!lista.java" style="color:#FF4500;text-align:left;border-style:none;">Lista de Disciplinas letivas</a>
</div>
<div id="experience" >
					<div id="explanation">
						<h3>
							<strong>
								Criar Nova Disciplina Letiva
							</strong>
						</h3>
							<p> 
							    </p>
						<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O(s) Campo(s) em vermelhor é(são) requerido(s).</p></div>
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
						<s:form name="adicionar" onSubmit="return validaForm()" action="/painel/letiva!save.java" enctype="multipart/form-data" method="post">
							Ano: 			<s:property  value="ano" /><br>
							Turno:		<br><sx:autocompleter  	headerKey="-1"  headerValue=""  id="turno" 		 list="turnos"      name="disciplinaLetivaNovoParams.turno" /><br>
							Semestre:	<br><sx:autocompleter  	headerKey="-1"  headerValue="" 	id="semestre" 	 list="semestres"   name="disciplinaLetivaNovoParams.semestre" /><br>
							Disciplina:	<br><sx:autocompleter 	headerKey="-1" 	headerValue=""  id="materia" 	 list="disciplinas" name="disciplinaLetivaNovoParams.materia" 		value="materia" 	listKey="id" listValue="nome" /><br>
							Professor:	<br><sx:autocompleter 	headerKey="-1" 	headerValue=""  id="professorid" list="professores" name="disciplinaLetivaNovoParams.professorid"  value="professorid" listKey="id" listValue="nome" /><br>		
							Sala:	<br><s:textfield id="sala" name="disciplinaLetivaNovoParams.sala" /><br>		
											
											<s:submit align="left" value="Adicionar"></s:submit>
						</s:form>
					</div>		
		</div>
</body>
</html>