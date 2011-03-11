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



<div id="experience" >
					<div id="explanation">
						<h3>
						<strong>
						Adicionar Curso
						</strong>
						</h3>
						<p> 
    Na tela Criar matéria ou Editar matéria, clique no ícone Inserir arquivo, disponível nos campos Imagem de índice, Resumo e Texto da matéria.
    Numa nova janela, será exibida a tela Inserir arquivo com a lista de todos os arquivos existentes no banco de dados do Publique!. Se o arquivo desejado ainda não estiver listado, clique em Enviar novo. A tela Enviar arquivo será exibida.
 	Clique em OK, feche a janela com a tela Inserir arquivo e continue a criar ou a alterar a sua matéria.
	Clique em OK, feche a janela com a tela Inserir arquivo e continue a criar ou a alterar a sua matéria.
	
</p>
						<div id="mensagem_ocultos" style="display:none;color:red;"><p align="center">O Campo Nome em vermelhor é requerido.</p></div>
						<div style="color:red;"><p align="center"><s:actionmessage/></p></div>
						<div style="color:red;"><p align="center"><s:actionerror/></p></div>
					</div>	
				
				<div id="contact-form">	


	<s:form action="/painel/curso!novo.java" name="adicionar" onSubmit="return validaForm()" >
		Nome: <s:textfield id="nome" name="cursoNovoParams.nomeCurso"></s:textfield>
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