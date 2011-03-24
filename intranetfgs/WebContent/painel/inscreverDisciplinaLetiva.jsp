<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">

#semestre {
	background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; 
	padding:2px 0px 2px 25px;
}
#turno {
	background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; 
	padding:2px 0px 2px 25px;
}
#disciplina {
	background:transparent url(../images/seta_baixo.gif) no-repeat 2% 50%; 
	padding:2px 0px 2px 25px;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Inscrever aluno - Disciplinas letivas</title>
<script type="text/javascript">

function buscarDisciplinas(semestre,ano,turno,id) {    
    location.href = "disciplinaLetiva!buscarDisciplinas.java?ano="+ano+"&semestre=" +semestre+"&turno=" +turno+"&idAluno=" +id;
	      
}
	function del(codigo,id) {  
	     if (confirm('Excluir disciplina?')) {    
	         location.href = "../painel/disciplinaLetiva!delete.java?disciplinaLetivaCadastrada.id="+ codigo+"&idAluno=" +id;
	     }  
	 }
	
	function restartTrs(){
	 	document.getElementById("turno").style.backgroundColor = "transparent";
		document.getElementById("semestre").style.backgroundColor = "transparent";
	 	document.getElementById("disciplina").style.backgroundColor = "transparent";
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
	       
	         if (d.disciplina.value == -1){
                 document.getElementById("disciplina").style.backgroundColor = "#FF6A6A";
                 d.disciplina.focus();
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
<div id="retorno" align="right">
<img src="../images/arrow_categ.gif"><a href="../painel/aluno!lista.java" style="color:#FF4500;text-align:right;border-style:none;">Lista de Alunos</a>
</div>
<b>Inscreve <s:property value="pessoa.nome" /> em Disciplinas letivas</b>
<hr></hr>

<div id="search">
	<s:form name="adicionar" onSubmit="return validaForm()" action="/painel/disciplinaLetiva!salva.java" method="post" >
		<table cellspacing="10" >
			<tr>
				<td>
					<b>Aluno:</b> <s:property value="pessoa.nome" /><b> - Matrícula:</b> <s:property value="pessoa.matricula" /> <b> - Email:</b> <s:property value="pessoa.email" /><br>
				</td>
			</tr>
		</table>
		<table cellspacing="10">
		 	<tr>
		 		<s:hidden name="idAluno" id="idAluno" value="%{pessoa.id}" ></s:hidden>				
				<td>Ano: 		<s:property  value="%{ano}" /></td>	
                <td>Disciplinas Letivas:	<sx:autocompleter headerKey="-1" headerValue=""  id="disciplinaLetiva" list="disciplinasLetivas" name="disciplinaLetiva" value="disciplinaLetiva" listKey="id" listValue="disciplina.nome" /></td>	
				<td colspan="1"><s:submit value="Inscrever aluno" /></td>
			</tr>
		</table>
	</s:form>
</div>
<div id="mensagem_ocultos" style="display:none;color:red;"><p><img src="../images/imgErro.gif"/>  O(s) Campo(s) em vermelhor é(são) requerido(s).</p></div>
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
<br>
<b>Disciplinas inscritas de <s:property value="pessoa.nome" /></b>
<hr></hr>
<div id="result" >


	<table width="100%">
	
		
		<tr>
		
			<td><b>id</b></td>
			<td><b>Disciplina</b></td>
			<td><b>Ano</b></td>
			<td><b>Turno</b></td>
			<td><b>Sem.</b></td>
			<td><b>Deletar</b></td>
		
		</tr>
		<c:forEach items="${disciplinasLetivasCadastradas}" var="disciplinaLetiva">
		
		
			<tr>	
				
			<td><c:out value="${disciplinaLetiva.id}" /></td>
			<td><c:out value="${disciplinaLetiva.disciplina.nome}" /></td>
			<td><c:out value="${disciplinaLetiva.ano}" /></td>
			<td><c:out value="${disciplinaLetiva.turno}" /></td>
			<td><c:out value="${disciplinaLetiva.semestre}" /></td>
				<td>
					<a href="javascript:del(${disciplinaLetiva.id},${idAluno})">
						<img border="0" src="../images/lixeira.gif" />
					</a>
				</td>
			
			</tr>
		
		</c:forEach>
	
	
	</table>


</div>
</body>
</html>