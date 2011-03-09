<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Buscar Material</title>
<script type="text/javascript">

function buscarDisciplinas(semestre,ano,turno) {    
	         location.href = "disciplinaLetiva!buscarDisciplinas.java?ano="+ano+"&semestre=" +semestre+"&turno=" +turno ;
	      
}
	function del(codigo) {  
	     if (confirm('Excluir disciplina?')) {    
	         location.href = "../painel/disciplinaLetiva!delete.java?disciplinaLetivaCadastrada.id="+ codigo;
	     }  
	 }

</script>
</head>
<body>
<b>Inscreve-se em Disciplinas</b>
<hr></hr>
<div id="search">
<s:form action="/painel/disciplinaLetiva!salva.java">
<table cellspacing="10">
	 	<tr>
			
			<td><b>Ano:</b></td>		
			<td><s:property  value="%{ano}" /></td>	
			
			<td><b>Semestre:</b></td>
			<td><sx:autocompleter onchange="javascript:buscarDisciplinas(this.value,'%{ano}',turno.value)" headerKey="-1"  headerValue=""  id="semestre" name="semestre" list="semestres" /></td>
			
			
			<td><b>Turno:</b></td>
			<td><sx:autocompleter onchange="javascript:buscarDisciplinas(semestre.value,'%{ano}',this.value)" headerKey="-1"  headerValue=""  id="turno" name="turno" list="turnos" /></td>
			
			
			<td><b>Disciplina:</b></td>
			<td><sx:autocompleter headerKey="-1" headerValue=""  id="disciplina" list="disciplinasLetivas" name="disciplina" value="disciplina" listKey="disciplina.id" listValue="disciplina.nome" /></td>	
			<td colspan="1">
			
			<s:submit value="Inscrever-se" /></td>
		</tr>
		
	 
	</table>
	
</s:form>
</div>
<s:actionerror />
<br>
<b>Minhas Inscrições</b>
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
					<a href="javascript:del(${disciplinaLetiva.id})">
						<img border="0" src="../images/lixeira.gif" />
					</a>
				</td>
			
			</tr>
		
		</c:forEach>
	
	
	</table>


</div>
</body>
</html>