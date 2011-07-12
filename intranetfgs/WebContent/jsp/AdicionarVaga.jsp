<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" href="../css/south-street/jquery-ui-1.8.14.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui-1.8.14.custom.min.js"></script>
	<script src="../js/jquery.maskedinput-1.3.js" type="text/javascript"></script>
	
	
	
	
	<script src="../js/jquery.maskedinput-1.3.js" type="text/javascript"></script>


<style type="text/css">
#experience {
	padding: 30px;
	padding: 17px 0px 0 39px;
}

#explanation {
	float: left;
	width: 340px;
}

#cargo {
	background: transparent url(../images/seta_baixo.gif) no-repeat 2% 50%;
	padding: 2px 0px 2px 25px;
}



#perfil {
	background: transparent url(../images/ico_mail.gif) no-repeat 2% 50%;
	padding: 2px 60px 2px 25px;
}

#quantidadeDevagas {
	background: transparent url(../images/profile_ico_transparent.gif)
		no-repeat 2% 50%;
	padding: 2px 0px 2px 25px;
}

#regimeDeContratacao {
	background: transparent url(../images/ico_user.gif) no-repeat 2% 50%;ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
	padding: 2px 60px 2px 25px;
}

#beneficios {
	background: transparent url(../images/lock.gif) no-repeat 2% 50%;
	padding: 2px 0px 2px 25px;
}

#nivelHierarquico {
	background: transparent url(../images/lock.gif) no-repeat 2% 50%;
	padding: 2px 0px 2px 25px;
}

#horarioDaVaga {
	background: transparent no-repeat 2% 50%;
	padding: 2px 0px 2px 0px;
}

#areas {
	background: transparent no-repeat 2% 50%;
	padding: 2px 0px 2px 0px;
}

#empresas {
	background: transparent url(../images/seta_baixo.gif) no-repeat 2% 50%;
	padding: 2px 0px 2px 25px;
}

#contact-form {
	border-left: 1px solid #313b45;
	margin-left: 370px;
	padding-left: 30px;
	width: 295px;
}

body {
	font: 62.5% "Trebuchet MS", sans-serif;
	margin: 50px;
}

.demoHeaders {
	margin-top: 2em;
}

#dialog_link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog_link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}

ul#icons {
	margin: 0;
	padding: 0;
}

ul#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

ul#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}
</style>




<script type="text/javascript">



 

$(document).ready(function(){

	$("#quantidadeDevagas").mask("9999");
	$("#horarioDaVaga").mask("**:** às **:**");
	$("#cep").mask("99999-999");
	
	$('#dialog_emp').dialog({
		autoOpen: false,
		width: 600,
		buttons: {
			"Cancel": function() { 
				$(this).dialog("close"); 
			} 
		}
	});


	$('#dialog_end').dialog({
		autoOpen: false,
		width: 600,
		buttons: {
			"Cancel": function() { 
				$(this).dialog("close"); 
			} 
		}
	});
	
	// Dialog Link
	$('#dialog_link_end').click(function(){
		$('#dialog_end').dialog('open');
		return false;
	});

	$('#dialog_link_emp').click(function(){
		$('#dialog_emp').dialog('open');
		return false;
	});
	
	//hover states on the static widgets
	$('#dialog_link, ul#icons li').hover(
		function() { $(this).addClass('ui-state-hover'); }, 
		function() { $(this).removeClass('ui-state-hover'); }
	);

});
  
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet - Faculdade Gama & Souza | Enviar Vaga de Emprego</title>
</head>
<body>

<table border=0 width="100%">

	<tr>
	
		<td>
					<img src="../images/arrow_categ.gif"><a
					href="../painel/vaga!lista.java"
					style="color: #FF4500; text-align: right; border-style: none;">Lista
					de vagas</a>
		</td>
		<td align="right">
			<a  title="Adicionar Empresas" href="#" id="dialog_link_emp">
			<img  border="0" src="../images/novo.jpg" />Adicionar Empresas</a>
		
			<a  title="Adicionar Empresas" href="#" id="dialog_link_end">
			<img border="0" src="../images/novo.jpg" />Adicionar Endereços</a>
		</td>
	
	</tr>

</table>



<!-- Nova Empresa -->
<div id="dialog_emp" title="Dialog Title">
	<p>
			<s:form name="registraEmpresa" method="post" action="../painel/vaga!saveEmpresa.java" onSubmit="return validaForm()">
				<table>
					<tr>
						<td>
							<s:text name="Nome:"/><s:textfield id="nome" required="true" 
								name="empresa.nome"></s:textfield>
							
							<s:text name="Ramo:"/><s:textfield id="ramo" required="true"
								name="empresa.ramo"></s:textfield>
							
							<s:text name="Descrição:"/><s:textfield id="descricao" required="true" 
								name="empresa.descricao"></s:textfield>
								
							<s:text name="Endereço:"/><s:select list="enderecos" listKey="id" headerKey="" headerValue="" listValue="toString()" id="endereco" name="enderecoId"></s:select>
							<s:text name="Se a lista acima estiver vazia é recomendado voltar na tela anterior e clicar no link 'Adicionar Endereços'"></s:text>
							<br>				
							<sx:submit  align="left" value="Registrar Empresa" label="Log in" cssClass="ui-state-default ui-corner-all"></sx:submit>
						</td>
					</tr>
				</table>
			</s:form>
	</p>
</div>




<!-- Novo Endereço -->
<div id="dialog_end" title="Dialog Title">
	<p>
			<s:form name="registraEndereco" method="post" action="../painel/vaga!saveEndereco.java" onSubmit="return validaForm()">
				<table>
					<tr>
						<td>
							<s:text name="Rua:"/><s:textfield id="rua" required="true" 
								name="endereco.rua"></s:textfield>
						
							<s:text name="Número:"/><s:textfield id="numero" required="true" 
								name="endereco.numero"></s:textfield>
							
							<s:text name="Complemento:"/><s:textfield id="complemento" required="true"
								name="endereco.complemento"></s:textfield>					

							<s:text name="Bairro:"/><s:textfield id="bairro" required="true" 
								name="endereco.bairro"></s:textfield>	
								
							<s:text name="Cep:"/><s:textfield id="cep" required="true" 
								name="endereco.cep"></s:textfield>	
								
							<s:text name="Cidade:"/><s:textfield id="cidade" required="true" 
								name="endereco.cidade"></s:textfield>		
							
							<s:text name="Estado:"/><s:textfield id="estado" required="true" 
								name="endereco.estado"></s:textfield>	
								
							<s:text name="País:"/><s:select list="paises" listValue="name" headerKey=""
								headerValue="" id="paises" name="endereco.pais"></s:select>
							<br>				
							<sx:submit  align="left" value="Registrar Endereço" label="Log				<br> in" cssClass="ui-state-default ui-corner-all"></sx:submit>
						</td>
					</tr>
				</table>
			</s:form>
	</p>
</div>




	<div id="experience">
		<div id="explanation">
			<h3>
				<strong> Enviar um Vaga de emprego </strong>
			</h3>
			<p>Área reservada, para efetuar registro de Vaga de emprego.</p>
			<strong>
				<div id="mensagem_ocultos" style="display: none; color: red;">
					<p>
						<img src="../images/imgErro.gif" /> O(s) Campo(s) em vermelhor
						é(são) requerido(s).
					</p>
				</div>
				<div id="email_ocultos" style="display: none; color: red">
					<p>
						<img src="../images/imgErro.gif" /> Campo email é requerido.
					</p>
				</div>
				<div id="email2_ocultos" style="display: none; color: red">
					<p>
						<img src="../images/imgErro.gif" /> Campo email com caracteres
						invalidos.
					</p>
				</div>
				<div id="senha_nao_confere" style="display: none; color: red">
					<p>
						<img src="../images/imgErro.gif" /> As senhas necessitam ser
						iguais no campo senha e confirmar senha.
					</p>
				</div>
				<div id="email_nao_confere" style="display: none; color: red">
					<p>
						<img src="../images/imgErro.gif" /> Os emails necessitam ser
						iguais no campo email e confirmar email.
					</p>
				</div> <s:if test="hasActionMessages()">
					<div class="welcome">
						<s:actionmessage
							cssStyle="color:green;background-image : url('../images/icon-true.png');background-repeat: no-repeat;padding:3px 0 7px 45px;" />
					</div>
				</s:if> <s:if test="hasActionErrors()">
					<div class="errors">
						<s:actionerror
							cssStyle="color:red;background-image : url('../images/imgErro.gif');background-repeat: no-repeat;padding:3px 0 7px 45px;" />
					</div>
				</s:if> </strong>
		</div>


		<div id="contact-form">
			<s:form name="registra" method="post"
				action="../painel/vaga!save.java" onSubmit="return validaForm()">

				<s:text name="Cargo:"/><s:textfield id="cargo" required="true"
					name="vaga.cargo"></s:textfield>
				<s:text name="Faixa Salarial:"/><s:select list="salarios" listValue="name" headerKey=""
					headerValue="" id="faixaSalarial" name="vaga.faixaSalarial"></s:select>
				<s:text name="Perfil:"/><s:textfield id="perfil" required="true"
					name="vaga.perfil"></s:textfield>
				<s:text name="Quantidade De Vagas:"/><s:textfield id="quantidadeDevagas" required="true"
					 name="vaga.quantidadeDevagas"></s:textfield>
				<s:text name="Beneficios:"/><s:textfield id="beneficios" required="true" 
					name="vaga.beneficios"></s:textfield>
				<s:text name="Nível Hierárquico:"/><s:textfield id="nivelHierarquico" required="true"
					name="vaga.nivelHierarquico"></s:textfield>
				<s:text name="Horários:"/><s:textfield id="horarioDaVaga" required="true"
					name="vaga.horarioDaVaga"></s:textfield>
				<br>
				<s:text name="Área Profissional:"/><s:select list="areas" listKey="id" headerKey="" headerValue=""
					listValue="nome" id="area" name="areaProfissionalId"></s:select>
				<s:text name="Empresa:"/><s:select list="empresas" listKey="id" headerKey="" headerValue=""
					listValue="nome" id="empresa" name="empresaId"></s:select>
				<s:text name="Se a lista acima estiver vazia ou não tiver a empresa que procura, é recomendado clicar no link na aba superior em 'Adicionar Empresas'"></s:text>
				<br><br>
				<s:text name="Regime de Contratação:"/><s:select list="tiposDeContratacao" listValue="name" headerKey=""
					headerValue="" id="tipoContratacao" name="vaga.regimeDeContratacao"></s:select>
				<sx:submit  align="left" value="Registrar" label="Log in" cssClass="ui-state-default ui-corner-all"></sx:submit>
			</s:form>
		</div>
	</div>

</body>
</html>