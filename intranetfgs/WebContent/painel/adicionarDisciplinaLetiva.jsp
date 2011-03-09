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

<s:actionmessage/>
<s:actionerror/>
<div id="experience" >
					<div id="explanation">
						<h3>
							<strong>
								Criar Nova Disciplina Letiva
							</strong>
						</h3>
							<p> 
							    Na tela Criar matéria ou Editar matéria, clique no ícone Inserir arquivo, disponível nos campos Imagem de índice, Resumo e Texto da matéria.
							    Numa nova janela, será exibida a tela Inserir arquivo com a lista de todos os arquivos existentes no banco de dados do Publique!. Se o arquivo desejado ainda não estiver listado, clique em Enviar novo. A tela Enviar arquivo será exibida.
							 	Clique em OK, feche a janela com a tela Inserir arquivo e continue a criar ou a alterar a sua matéria.
								Clique em OK, feche a janela com a tela Inserir arquivo e continue a criar ou a alterar a sua matéria.
							</p>
						<div id="mensagem_ocultos" style="display:none;color:red;"><p align="center">O(s) Campo(s) em vermelhor é(são) requirido(s).</p></div>
						
					</div>	
					
					<div id="contact-form">
						<s:form name="adicionar" onSubmit="return validaForm()" action="/painel/letiva!save.java" enctype="multipart/form-data" method="post">
							Ano: 			<s:property  value="%{ano}" /><br>
							Turno:		<br><sx:autocompleter  	headerKey="-1"  headerValue=""  id="turno" 		 list="turnos"      name="disciplinaLetivaNovoParams.turno" /><br>
							Semestre:	<br><sx:autocompleter  	headerKey="-1"  headerValue="" 	id="semestre" 	 list="semestres"   name="disciplinaLetivaNovoParams.semestre" /><br>
							Disciplina:	<br><sx:autocompleter 	headerKey="-1" 	headerValue=""  id="materia" 	 list="disciplinas" name="disciplinaLetivaNovoParams.materia" 		value="materia" 	listKey="id" listValue="nome" /><br>
							Professor:	<br><sx:autocompleter 	headerKey="-1" 	headerValue=""  id="professorid" list="professores" name="disciplinaLetivaNovoParams.professorid"  value="professorid" listKey="id" listValue="nome" /><br>		
											<s:submit align="left" value="Adicionar"></s:submit>
						</s:form>
					</div>		
		</div>
</body>
</html>