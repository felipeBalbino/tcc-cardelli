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

#namearquivo {
	background:transparent no-repeat 2% 50%; 
	padding:2px 0px 2px 0px;
}
#arquivo {
	background:transparent no-repeat 2% 50%; 
	padding:2px 0px 2px 0px;
}
#semestre {
	background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; 
	padding:2px 0px 2px 25px;
}
#turno {
	background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; 
	padding:2px 0px 2px 25px;
}
#materia {
	background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; 
	padding:2px 0px 2px 25px;
}
 
#contact-form { 
	border-left:1px solid #313b45; 
	margin-left:370px; 
	padding-left:30px; 
	width:295px; 
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Adicionar Arquivo</title>

<script type="text/javascript">

function buscarDisciplinas(semestre,ano,turno) {    
	location.href = "upload!buscarDisciplinasProfessor.java?ano="+ano+"&semestre=" +semestre+"&turno=" +turno ;
	      
}

function restartTrs(){
	document.getElementById("namearquivo").style.backgroundColor = "transparent";
 	document.getElementById("turno").style.backgroundColor = "transparent";
	document.getElementById("semestre").style.backgroundColor = "transparent";
 	document.getElementById("materia").style.backgroundColor = "transparent";
 	document.getElementById("arquivo").style.backgroundColor = "transparent";
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
         if (d.arquivo.value == ""){
	           document.getElementById("arquivo").style.backgroundColor = "#FF6A6A";
                 d.arquivo.focus();
                 erro = true;
       		}
         
         
         //validar namearquivo
         if (d.namearquivo.value == ""){
	           document.getElementById("namearquivo").style.backgroundColor = "#FF6A6A";
                   d.namearquivo.focus();
                   erro = true;
         }
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

         //validar materiaarquivo
         if (d.materia.value == -1){
                  document.getElementById("materia").style.backgroundColor = "#FF6A6A";
                  d.materia.focus();
                  erro = true;
         }

		if (erro != true) {
			document.getElementById("mensagem_carregando").style.display = "inline";
         	return true;
		}else{
			document.getElementById("mensagem_ocultos").style.display = "inline";
         	return false;
		}
}


</script>
</head>
<body>

<div id="experience" >
					<div id="explanation">
						<h3>
						<strong>
						Adicionar Novo Arquivo
						</strong>
						</h3>
						<p> 
						    Você pode criar novos arquivos em qualquer programa e dar a cada um deles um nome significativo para ajudá-lo a organizar seu trabalho.<br>
						    Selecione ao lado o arquivo que você gostaria de enviar em seu computador ou rede local, não esquece de selecionar com exatidão o turno, semestre e disciplina letiva.
						</p>
						<strong>
						Tamanho máximo do seu upload: 200MB
						</strong>
						<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O(s) Campo(s) em vermelhor é(são) requerido(s).</p></div>
						<br>
						<br>
						<div id="mensagem_carregando" style="display:none;color:red;"><p align="center"> O arquivo esta sendo enviado para o servidor<br>O tempo de envio é relativo ao tamanho do arquivo.<br><img src="../images/load.gif"></p></div>
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
					<s:form name="adicionar" onSubmit="return validaForm()" action="/painel/upload!send.java" enctype="multipart/form-data" method="post">
						Ano: 				<s:property  value="%{ano}" /><br>
						Turno:			<br><sx:autocompleter onchange="javascript:buscarDisciplinas(semestre.value,'%{ano}',this.value)" headerKey="-1"  headerValue=""  id="turno" name="turno" list="turnos" /><br>
						Semestre:		<br><sx:autocompleter onchange="javascript:buscarDisciplinas(this.value,'%{ano}',turno.value)" headerKey="-1"  headerValue="" id="semestre" name="semestre" list="semestres" /><br>
						Disciplina:		<br><sx:autocompleter headerKey="-1" headerValue=""  id="materia" list="disciplinasLetivas" name="materia" value="materia" listKey="disciplina.id" listValue="disciplina.nome" /><br>
						Nome do Arquivo:<br><s:textfield id="namearquivo" name="arquivo.nome"></s:textfield><br>
						Arquivo:		<br><s:file 	 id="arquivo" 	  name="arquivo.upload"></s:file><br>	
											
						<s:submit align="left" value="Adicionar"></s:submit>
					</s:form>
</div>		
		</div>
</body>
</html>