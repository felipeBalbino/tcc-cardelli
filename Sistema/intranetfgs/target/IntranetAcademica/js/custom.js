$(document).ready(function() {

	$("#telefone").mask("(99)9999-9999");
	$("#matricula").mask("99999999");
	$("#searchmatricula").mask("99999999");
	$("#horainicio").mask("99:99:99");
	$("#horafinal").mask("99:99:99");
	$("#periodo").mask("99");
	$("cargaHorariaComplementar").mask("99999999");
	$("#quantidadeDevagas").mask("9999");
	$("#horarioDaVaga").mask("99:99 ás 99:99");
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
	

	$('#cep').change(function(){
		$.ajax({
			   type: "POST",
			   url: "vaga!saveEndereco.java",
			   data: "name=John&location=Boston",
			   success: function(msg){
			     alert( "Data Saved: " + msg );
			   }
			 });
	});
	


	$('#dialog_end').dialog({
		autoOpen: false,
		title: "Adicionar Endere�os",
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
	
	$('#dialog_link_end2').click(function(){
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

$(document).ready(function() {

	$('.conteudo').hide();

	$('.exibir').each(function(i) {
		$(this).click(function() {
			$('.conteudo').each(function(j) {
				if (i == j)
					$(this).show('slow');
			});
		});
	});
	$('.ocultar').each(function(i) {
		$(this).click(function() {
			$('.conteudo').each(function(j) {
				if (i == j)
					$(this).hide('slow');
			});
		});
	});
});


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



	function delcurso(codigo) {    
	     if (confirm('Excluir o curso?')) {    
	         location.href = "../painel/curso!delete.java?cursoDeletaParams.id="+ codigo;
	     }  
	 }
	
 	function delcomplementar(alunoId,horaId) {    
	     if (confirm('Excluir hora complementar?')) {    
	         location.href = "../painel/hora!deletaComplementar.java?horaComplementarDeletaParams.alunoId="+ alunoId+ "&horaComplementarDeletaParams.horaId=" + horaId; 
	     }  
	 }
 	
 	function delDisciplinaLetiva(codigo) {    
	     if (confirm('Excluir a disciplina letiva?')) {    
	         location.href = "../painel/letiva!delete.java?disciplinaLetiva.id="+ codigo;
	     }  
	 }
	
	function horario(codigo) {       
       location.href = "../painel/horario!listarHorarioPorDisciplinaLetiva.java?idDisciplinaLetiva="+ codigo; 
	}	
	function listaPresenca(codigo) {       
       location.href = "../painel/letiva!listaPresenca.java?id="+ codigo; 
	}
	


 	function delEvento(codigo) {    
	     if (confirm('Excluir o evento?')) {    
	         location.href = "../painel/evento!deleta.java?eventoDeletaParams.id="+ codigo;
	     }  
	 }

 	function del(codigo) {    
	     if (confirm('Excluir o Aluno?')) {    
	         location.href = "../painel/aluno!delete.java?idAluno="+ codigo;
	     }  
	 }

	function gerarSenha(codigo) {    
	     if (confirm('Gerar nova senha?')) {    
	         location.href = "../painel/aluno!gerarNovaSenha.java?idAluno="+ codigo;
	     }  
	 }
	
	function dis(codigo) {       
	         location.href = "../painel/disciplinaLetiva!list.java?idAluno="+ codigo; 
	 }
	
	function deletar(id){

		  if (confirm('Tem certeza que deseja excluir este hor�rio? Esta opera��o � irrevers�vel.')) {  
		         location.href ="../painel/horario!delete.java?id="+id;
		     } 
	}
	
 	function delMaterias(codigo) {    
	     if (confirm('Excluir a disciplina?')) {    
	         location.href = "../painel/materia!delete.java?disciplinaDeletaParams.id="+ codigo;
	     }  
	 }
 	
 	function delProfessores(codigo) {    
	     if (confirm('Excluir o professor?')) {    
	         location.href = "../painel/professor!delete.java?professor.id="+ codigo;
	     }  
	 }
 	
 	function delVagas(codigo) {    
	     if (confirm('Excluir Vaga?')) {    
	         location.href = "../painel/vaga!deleta.java?idVaga="+ codigo;
	     }  
	 }

	function desconfirmar(codigo) { 
		if (confirm('Deseja marcar como n�o confirmado?')) {    
	         location.href = "../painel/vaga!desconfirmar.java?idVaga="+ codigo;
	     } 
	}

	function confirmar(codigo) { 
		if (confirm('Deseja marcar como confirmado?')) {    
	         location.href = "../painel/vaga!confirmar.java?idVaga="+ codigo;
	     } 
	}

	function gerarNovaSenha(codigo) {    
	     if (confirm('Gerar e enviar senha para o professor?')) {    
	         location.href = "../painel/professor!gerarNovaSenha.java?professor.id="+ codigo;
	     }  
	 }
 	
 	function delNoticias(codigo) {    
	     if (confirm('Excluir Noticia?')) {    
	         location.href = "../painel/noticia!delete.java?noticia.id="+ codigo;
	     }  
	 }
	


	function deletarHorarios(id,idDisciplinaLetiva,idHora,idDisciplinaLetiva){

		  if (confirm('Tem certeza que deseja excluir este horário? Esta operação é irreversível.')) {  
		         location.href ="../painel/horario!deleteDisciplinaLetivaHorario.java?id="+id+"&idDisciplinaLetiva="+idDisciplinaLetiva+"&idHorario="+idHora;
		     } 
	}
	
	function aep(codigo) {       
       location.href = "../painel/hora!listaAEP.java?horaAEPListaParams.id="+ codigo; 
}

	function grade(codigo) {       
       location.href = "../painel/aluno!gradeParaAdmin.java?id="+ codigo; 
}
	
	function complementar(codigo) {       
       location.href = "../painel/hora!listaComplementar.java?horaComplementarListaParams.id="+ codigo;  
}