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
padding:17px 0px 0 39px;
}


#explanation { 
float:left;
width:340px;
}

#mensagem {background:transparent  no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#titulo {background:transparent  no-repeat 2% 50%; padding:2px 0px 2px 0px;}



 
#contact-form { 
border-left:1px solid #313b45; 
margin-left:370px; 
padding-left:30px; 
width:295px; 
}


</style>
<script type="text/javascript">
 function restartTrs(){
	document.getElementById("titulo").style.backgroundColor = "transparent";
 	document.getElementById("mensagem").style.backgroundColor = "transparent";

 }

 function restartMensagemErro(){
                   document.getElementById("mensagem_ocultos").style.display = "none";
 }
 

 
 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.adicionar;
         var erro = false;
         //validar titulo
         if (d.titulo.value == ""){
	           document.getElementById("titulo").style.backgroundColor = "#FF6A6A";
                   d.titulo.focus();
                   erro = true;
         }
         //validar telefone
         if (d.mensagem.value == ""){
                  document.getElementById("mensagem").style.backgroundColor = "#FF6A6A";
                  d.mensagem.focus();
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
<title>Intranet - Faculdade Gama & Souza | Adicionar Nova Notícia</title>
</head>
<body>
<div id="retorno" align="right">
<img src="../images/arrow_categ.gif"><a href="../painel/noticia!lista.java" style="color:#FF4500;text-align:left;border-style:none;">Lista de Notícias</a>
</div>
<div id="experience" >
					<div id="explanation">
						<h3>
						<strong>
						Adicionar Nova Notícia
						</strong>
						</h3>
						<p>
						Noticias adicionadas aqui, serão visualizadas na página inicial.<br><br>
						Título:<br>
						O título da Notícia, pequena descrição da Notícia.<br>
						Exemplo: Novo Windows 7<br>
						<br>
						Mensagem:<br>
						O conteudo da Notícia.
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
						<s:form action="/painel/noticia!save.java" onSubmit="return validaForm()" name="adicionar">
							Titulo: <s:textfield cssStyle="width:80%" name="noticia.title" id="titulo" onkeypress="contarCaracterestitulo(this.value,30,'sprestante2')" maxLength="40"></s:textfield>
							<span id="sprestante2" style="font-family:Georgia;"></span><br>
							Mensagem: <s:textarea cssStyle="width:80%; height:200px;" name="noticia.mensagem" id="mensagem" ></s:textarea>
							
							<s:submit align="left" value="Adicionar"></s:submit>
						</s:form>
					</div>		
		</div>
		
		
</body>
</html>