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


#titulo {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#coorden {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}

#inicio {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#final {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#local {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#alvo {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}
#obs {background:transparent no-repeat 2% 50%; padding:2px 0px 2px 0px;}


 
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
 	document.getElementById("coorden").style.backgroundColor = "transparent";

 }

 function restartMensagemErro(){
                   document.getElementById("mensagem_ocultos").style.display = "none";
 }


 function inserirlocal(){
	 document.getElementById("divlocal").style.display = "inline";
	 document.getElementById("lilocal").style.display = "none";
 }	
 
 function inserirpublicoalvo(){
	 document.getElementById("divalvo").style.display = "inline";
	 document.getElementById("lialvo").style.display = "none";
 }
 
 function inserirobs(){
	 document.getElementById("divobs").style.display = "inline";
	 document.getElementById("liobs").style.display = "none";
 } 

 function validaForm(){
	 restartTrs();
         restartMensagemErro();
         d = document.adicionar;
         var erro = false;
        
         //validar arquivo
         if (d.titulo.value == ""){
	           document.getElementById("titulo").style.backgroundColor = "#FF6A6A";
                 d.titulo.focus();
                 erro = true;
       		}
         
         
         //validar namearquivo
         if (d.coorden.value == ""){
	           document.getElementById("coorden").style.backgroundColor = "#FF6A6A";
                   d.coorden.focus();
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
<title>Intranet - Faculdade Gama & Souza | Adicionar Evento</title>
<sx:head/>
</head>
<body>

<s:actionmessage/>


<div id="experience" >
					<div id="explanation">
						<h3>
						<strong>
						Adicionar Novo Evento
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
<s:form name="adicionar" onSubmit="return validaForm()" action="/painel/evento!novo.java" >

	Titulo: 
	<s:textfield cssStyle="width:80%" name="eventoNovoParams.titulo" id="titulo"></s:textfield>
	
	<br>
	
	Coordenação: 
	<s:textfield cssStyle="width:80%" name="eventoNovoParams.coordenacao" id="coorden"></s:textfield>
	<br>
	
	Data / Hora Inicio: 
	<sx:datetimepicker cssStyle="width:190px" id="inicio" name="eventoNovoParams.dataHoraInicio" displayFormat="dd/MM/yyyy hh:mm" />
	<br>Utilize o formato da data dd/MM/yyyy hh:mm<br><br>
	
	Data / Hora Fim: 
	<sx:datetimepicker  cssStyle="width:190px"  id="final"name="eventoNovoParams.dataHoraFim" displayFormat="dd/MM/yyyy hh:mm" />
	<br>Utilize o formato da data dd/MM/yyyy hh:mm<br><br>
	
	<li id="lilocal"><a   href="#" onclick="javascript:inserirlocal()">Inserir Local</a><br><br></li>
	<li id="lialvo"><a href="#" onclick="javascript:inserirpublicoalvo()">Inserir Publico Alvo</a><br><br></li>
	<li id="liobs"><a  href="#" onclick="javascript:inserirobs()">Inserir Observação</a><br></li>
	
	<div id="divlocal" style="display:none;" >
	<br>
	<hr></hr>
	<br>
		Local: 
			<s:textfield cssStyle="width:80%" name="eventoNovoParams.local" id="local"></s:textfield>
		<br>
	</div>
	
	<div id="divalvo" style="display:none;">
		Publico Alvo: 
			<s:textarea cssStyle="width:80%; height:50px;" name="eventoNovoParams.publicoAlvo" id="alvo"></s:textarea>
		<br>
	</div>
	
	<div id="divobs" style="display:none;">
		Obs: 
		<s:textarea cssStyle="width:80%; height:80px;" name="eventoNovoParams.observacoes" id="obs" ></s:textarea>
	</div>
	<br></br>
	<s:submit align="left" value="Enviar"></s:submit>

</s:form>
</div>		
</div>
</body>
</html>