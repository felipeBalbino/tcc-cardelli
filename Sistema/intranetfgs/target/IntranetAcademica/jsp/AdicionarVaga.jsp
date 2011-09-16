<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 				prefix="fmt" 		%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript"> 

function validaFormEmpresa(){
	
	if($("#nome").val() == ""){
		alert("Campo 'Nome da Empresa' é requerido.");
		return false;
	}
	if($("#ramo").val() == ""){
		alert("Campo 'Ramo' é requerido.");
		return false;
	}
	if($("#descricao").val() == ""){
		alert("Campo 'Descrição' é requerido.");
		return false;
	}
	
	if($("#endereco option:selected").val() == ""){
		alert("Campo 'Endereço' é requerido.");
		return false;
	}

	return true;
	
}
 
function validaFormEndereco(){
	
	if($("#rua").val() == ""){
		alert("Campo 'Rua' é requerido.");
		return false;
	}
	if($("#numero").val() == ""){
		alert("Campo 'Número' é requerido.");
		return false;
	}
	if($("#complemento").val() == ""){
		alert("Campo 'Complemento' é requerido.");
		return false;
	}
	
	if($("#bairro").val() == ""){
		alert("Campo 'Bairro' é requerido.");
		return false;
	}
	if($("#cep").val() == ""){
		alert("Campo 'Cep' é requerido.");
		return false;
	}
	if($("#cidade").val() == ""){
		alert("Campo 'Cidade' é requerido.");
		return false;
	}
	
	if($("#estado").val() == ""){
		alert("Campo 'Estado' é requerido.");
		return false;
	}
	if($("#paises option:selected").val() == ""){
		alert("Campo 'Pais' é requerido.");
		return false;
	}
	return true;
	
}


function validaForm(){
	
	if($("#cargo").val() == ""){
		alert("Campo 'Cargo' é requerido.");
		return false;
	}
	if($("#email").val() == ""){
		alert("Campo 'Email' é requerido.");
		return false;
	}
	
	if($("#faixaSalarial").val() == ""){
		alert("Campo 'Faixa Salarial' é requerido.");
		return false;
	}
	if($("#perfil").val() == ""){
		alert("Campo 'Perfil' é requerido.");
		return false;
	}
	
	if($("#quantidadeDevagas").val() == ""){
		alert("Campo 'Quantidade de Vagas' é requerido.");
		return false;
	}
	if($("#beneficios").val() == ""){
		alert("Campo 'Beneficios' é requerido.");
		return false;
	}
	if($("#nivelHierarquico").val() == ""){
		alert("Campo 'Nivel Hierarquico' é requerido.");
		return false;
	}
	
	if($("#horarioDaVaga").val() == ""){
		alert("Campo 'Horário da Vaga' é requerido.");
		return false;
	}
	if($("#area option:selected").val() == ""){
		alert("Campo 'Área' é requerido.");
		return false;
	}
	if($("#empresa option:selected").val() == ""){
		alert("Campo 'Empresa' é requerido.");
		return false;
	}
	
	if($("#tipoContratacao option:selected").val() == ""){
		alert("Campo 'Tipo de contratação' é requerido.");
		return false;
	}

    //validar email(verificar caracteres)
    var email = $("#email").val();
    parte1 = email.indexOf("@");
    parte2 = email.indexOf(".");
    parte3 = email.length;
    if (!(parte1 >= 3 && parte2 >= 6 && parte3 >= 9)) {
		alert("Campo email com caracteres invalidos..");
    	return false;
    }
	return true;
	
}

  
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
		
			<a  title="Adicionar Endereços" href="#" id="dialog_link_end">
			<img border="0" src="../images/novo.jpg" />Adicionar Endereços</a>
		</td>
	
	</tr>

</table>



<!-- Nova Empresa -->
<div id="dialog_emp" title="Dialog Title">
	<p>
			<s:form name="registraEmpresa" method="post" action="../painel/vaga!saveEmpresa.java" onSubmit="return validaFormEmpresa()">
				<table>
					<tr>
						<td>
							<s:text name="Nome:"/><s:textfield id="nome" required="true" 
								name="empresa.nome"></s:textfield>
							
							<s:text name="Ramo:"/><s:textfield id="ramo" required="true"
								name="empresa.ramo"></s:textfield>
							
							<s:text name="Descrição:"/><s:textfield id="descricao" required="true" 
								name="empresa.descricao"></s:textfield>
								
							<s:text name="Endereço:"/><s:select list="enderecos" listKey="id" headerKey="" headerValue="Selecione" listValue="toString()" id="endereco" name="enderecoId"></s:select>
							<sx:submit align="left" value="Registrar Endereços" id="dialog_link_end2" href="#" label="Log in" cssClass="ui-state-default ui-corner-all"></sx:submit>
						
							<s:text name="Se a lista acima estiver vazia é recomendado clicar no link 'Adicionar Endereços' ao lado." ></s:text>
							<br>				
							<sx:submit  align="right" value="Registrar Empresa" label="Log in" cssClass="ui-state-default ui-corner-all"></sx:submit>
						</td>
					</tr>
				</table>
			</s:form>
	</p>
</div>




<!-- Novo Endereço -->
<div id="dialog_end" title="Dialog Title">
	<p>
			<s:form name="registraEndereco" method="post" action="../painel/vaga!saveEndereco.java" onSubmit="return validaFormEndereco()">
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
								headerValue="Selecione" id="paises" name="endereco.pais"></s:select>
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
				<s:if test="hasActionMessages()">
					<div class="welcome">
						<s:actionmessage
							cssStyle="color:green;background-image : url('../images/icon-true.png');background-repeat: no-repeat;padding:3px 0 7px 45px;" />
					</div>
				</s:if> <s:if test="hasActionErrors()">
					<div class="errors">
						<s:actionerror
							cssStyle="color:red;background-image : url('../images/imgErro.gif');background-repeat: no-repeat;padding:3px 0 7px 45px;" />
					</div>
				</s:if> 
			</strong>
		</div>


		<div id="contact-form">
			<s:form name="registra" method="post"
				action="../painel/vaga!save.java" onSubmit="return validaForm()">

				<s:text name="Cargo:"/><s:textfield id="cargo" required="true"
					name="vaga.cargo"></s:textfield>
				<s:text name="Email para Contato:"/><s:textfield id="email" required="true"
					name="vaga.email"></s:textfield>
				<s:text name="Faixa Salarial: R$"/><s:select list="salarios" listValue="name" headerKey=""
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
				<s:text name="Área Profissional:"/><s:select list="areas" listKey="id" headerKey="" headerValue="Selecione"
					listValue="nome" id="area" name="areaProfissionalId"></s:select>
				<s:text name="Empresa:"/><s:select list="empresas" listKey="id" headerKey="" headerValue="Selecione"
					listValue="nome" id="empresa" name="empresaId"></s:select>
				<s:text name="Se a lista acima estiver vazia ou não tiver a empresa que procura, é recomendado clicar no link na aba superior em 'Adicionar Empresas'"></s:text>
				<br><br>
				<s:text name="Regime de Contratação:"/><s:select list="tiposDeContratacao" listValue="name" headerKey=""
					headerValue="Selecione" id="tipoContratacao" name="vaga.regimeDeContratacao"></s:select>
				<sx:submit  align="left" value="Registrar" label="Log in" cssClass="ui-state-default ui-corner-all"></sx:submit>
			</s:form>
		</div>
	</div>

</body>
</html>