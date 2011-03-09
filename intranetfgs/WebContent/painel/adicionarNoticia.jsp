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
 
 function contarCaracteres(box,valor,campospan){
		var conta = valor - box.length;
		document.getElementById(campospan).innerHTML = "Você ainda pode digitar " + conta + " caracteres";
		if(box.length >= valor){
			document.getElementById(campospan).innerHTML = "Você não pode mais digitar..";
			document.getElementById("mensagem").value = document.getElementById("mensagem").value.substr(0,valor);
		}	
	}

 function contarCaracterestitulo(box,valor,campospan){
		var conta = valor - box.length;
		document.getElementById(campospan).innerHTML = "";
		if(box.length >= valor){
			document.getElementById(campospan).innerHTML = "Você não pode mais digitar - (Limite 30 caracteres).";
			document.getElementById("titulo").value = document.getElementById("titulo").value.substr(0,valor);
		}	
	}
	
 function mostrarResultado(box,num_max,campospan){
 	var contagem_carac = box.length;
 	if (contagem_carac != 0){
 		document.getElementById(campospan).innerHTML = contagem_carac + " caracteres digitados";
 		if (contagem_carac == 1){
 			document.getElementById(campospan).innerHTML = contagem_carac + " caracter digitado";
 		}
 		if (contagem_carac >= num_max){
 			document.getElementById(campospan).innerHTML = "Limite de caracteres excedido!";
 		}
 	}else{
 		document.getElementById(campospan).innerHTML = "Ainda não temos nada digitado..";
 	}
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
<s:actionmessage/>
<div id="experience" >
					<div id="explanation">
						<h3>
						<strong>
						Adicionar Nova Notícia
						</strong>
						</h3>
						<p>Mensagens para a Ouvidoria devem ser enviadas pelo do formulário eletrônico on line disponível ao lado.

O sistema permite que as informações enviadas sejam armazenadas de forma segura e sigilosa e são essenciais para o devido encaminhamento das demandas e demais comunicações junto aos órgãos responsáveis facilitando um retorno satisfatório das questões apresentadas.
<br>
</p>
						<div id="mensagem_ocultos" style="display:none;color:red;"><p align="center">O(s) Campo(s) em vermelhor é(são) requirido(s).</p></div>
						
					</div>	
					
					<div id="contact-form">
						<s:form action="/painel/noticia!save.java" onSubmit="return validaForm()" name="adicionar">
							Titulo: <s:textfield cssStyle="width:80%" name="noticia.title" id="titulo" onkeypress="contarCaracterestitulo(this.value,30,'sprestante2')"></s:textfield>
							<span id="sprestante2" style="font-family:Georgia;"></span><br>
							Mensagem: <s:textarea cssStyle="width:80%; height:200px;" name="noticia.mensagem" id="mensagem" onkeypress="mostrarResultado(this.value,500,'spcontando');contarCaracteres(this.value,500,'sprestante')"></s:textarea>
							
							<span id="spcontando" style="font-family:Georgia;">Ainda não temos nada digitado..</span><br />
							<span id="sprestante" style="font-family:Georgia;"></span>
							
							<s:submit align="left"></s:submit>
						</s:form>
					</div>		
		</div>
		
		
</body>
</html>