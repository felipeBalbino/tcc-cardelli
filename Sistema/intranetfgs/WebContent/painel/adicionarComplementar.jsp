<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head/>
	
	
	
	

<script type="text/javascript">
jQuery(function($){
	   $("#data").mask("99:99");

	}); 


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
<title>Intranet - Faculdade Gama & Souza | Adicionar Hora Complementar</title>
</head>
<body>
<table>
	<tr>
		<td><img src="../images/arrow_categ.gif" />
		<a href="../painel/hora!listaComplementar.java?horaComplementarListaParams.id=<s:property value="aluno.id" />" style="color:#FF4500;text-align:left;border-style:none;">Lista de Horas Complementares</a>
		<img src="../images/arrow_categ.gif" />
		<a href="../painel/aluno!lista.java" style="color:#FF4500;text-align:left;border-style:none;">Lista de Alunos</a>
	</td>
	</tr>
</table>
	
	
	
	
	<div id="experience" >
		<div id="explanation">
			<h3>
				<strong>
					Adicionar Hora Complementar
				</strong>
			</h3>
			<p> 
				Use os campos ao lado para adicionar uma hora Complementar;
			</p>
			<div id="mensagem_ocultos" style="display:none;color:red;">
				<p>
					<img src="../images/imgErro.gif"/>  
					O Campo Nome em vermelhor é requerido.
				</p>
			</div>
			<br>
			<s:if test="hasActionMessages()">
				<div class="welcome" >
  					<s:actionmessage cssStyle="color:green;background-image : url('../images/icon-true.png');background-repeat: no-repeat;padding:3px 0 7px 45px;"/><br>
					<a style="border:0px;" href="hora!gerarComprovanteHoraComplementar.java?geraComprovanteHoraComplementarParams.alunoId=<s:property value="aluno.id"/>&geraComprovanteHoraComplementarParams.horaId=<s:property value="horaComplementar.id"/>">
						<table><tr><td><img style="border:0px;" src="../images/comprovante.gif"/></td><td>Gerar Comprovante</td></tr></table>
						
					</a>
  				</div> 			
			</s:if>
						
			<s:if test="hasActionErrors()">
				<div class="errors">
  					<s:actionerror cssStyle="color:red;background-image : url('../images/imgErro.gif');background-repeat: no-repeat;padding:3px 0 7px 45px;"/>
  				</div> 
			</s:if>
		</div>	
				
		<div id="contact-form">	
			<hr>
			<b>Dados do Aluno</b>
			<hr>
			<table>
				<tr>
					<td><b>Nome:</b></td>
					<td><s:property value="aluno.nome"/></td>
				</tr>
				<tr>
					<td><b>Matrícula:</b></td>
					<td><s:property value="aluno.matricula"/></td>
				</tr>
			</table>
			<br>
			<hr>
			<b>Adicionar Hora Complementar</b>
			<hr>
			<s:form action="/painel/hora!salvaComplementar.java" name="adicionar" onSubmit="return validaForm()" >
				<s:hidden value="%{aluno.id}" name="horaComplementarNovoParams.aluno.id" />
				Título: <br><s:textfield id="data" id="titulohora" name="horaComplementarNovoParams.nomeEvento"   /><br>
				Horas (hh:mm): <br><s:textfield maxlength="5" id="data" name="horaComplementarNovoParams.numeroHoras" /><br>
				Atividade:<br><sx:autocompleter cssStyle="width:270px;" headerKey="-1" headerValue=""  id="atividade" list="atividades" name="horaComplementarNovoParams.atividadeId"   value="atividade" listKey="id" listValue="nome" /><br>
						<br>
				<s:submit align="left" value="Adicionar Hora"></s:submit>
			</s:form>
		</div>		
</div>
</body>
</html>