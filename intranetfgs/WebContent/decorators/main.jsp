<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"	%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page"      prefix="page"		%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 				prefix="c"			%>
<%@ taglib uri="/struts-tags" 									prefix="s"			%>
<%@ taglib uri="/struts-dojo-tags" 								prefix="sx"			%>

<html>
<head>
	<link href="style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/slider.js"></script>
	<script type="text/javascript" src="js/jqModal.js"></script>

	<title><decorator:title default="Faculdade Gama e Souza | Intranet " /></title>
	
	<decorator:head />

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/decorators/main.css" />

	<script type="text/javascript" src="<%=request.getContextPath()%>/decorators/js/jquery.js"></script>

	<script type="text/javascript">

		var timeout = 500;
		var closetimer = 0;
		var ddmenuitem = 0;

		function showlayer(layer){
			var myLayer = document.getElementById(layer);
			if(myLayer.style.display=="none" || myLayer.style.display==""){
			myLayer.style.display="block";
			} else {
			myLayer.style.display="none";
			}
			}
	
		function mopen(id)
		{	
	
			mcancelclosetime();


			if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	
			ddmenuitem = document.getElementById(id);
			ddmenuitem.style.visibility = 'visible';

		}

		function mclose()
		{
			if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
		}


		function mclosetime()
		{
			closetimer = window.setTimeout(mclose, timeout);
		}


		function mcancelclosetime()
		{
			if(closetimer)
			{
				window.clearTimeout(closetimer);
				closetimer = null;
			}
		}


		document.onclick = mclose; 
				
	</script>
</head>
<body>

	<table width="97%" align="center" id="table">
		<tr>
			<td>
				<div id="content">
					<table id="contenttablelogo" align="left" width="10%">
						<tr>
							<td colspan="1"><img src="../images/logo.png" /></td>
			
						</tr>
					</table>

				<c:if test="${sessionScope.pessoa == null}">
					<div id="m1" style="text-align: right;width: 98%;" >
						<ul id="sddm2" style="align: right; width: 100%; margin: 0pt auto;">	
							<li><a href="../jsp/log!prepare.java">Registrar</a></li>
							<li><span style="color:#FFFFFF">|</span></li>
							<li><a href="../jsp/login.jsp">Login</a></li>
							<li><span style="color:#FFFFFF">|</span></li>
							<li><a href="../jsp/index!events.java">Principal</a></li>
						</ul>
						<div style="clear: both"></div>
					</div>
				</c:if> 
		
				<c:if test="${sessionScope.pessoa != null}">
					<div id="m1" style="text-align: right;width: 98%;" >
						<ul id="sddm2" style="align: right; width: 100%; margin: 0pt auto;">
							<li><a href="../jsp/log!out.java">Sair&nbsp;(${sessionScope.pessoa.login})</a></li>
							<li><span style="color:#FFFFFF">|</span></li>
							<li><a href="#" onmouseover="mopen('m3')" onmouseout="mclosetime()">Opções</a>
								<div id="m3" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
									<% if (session.getAttribute("pessoa") instanceof br.edu.gamaesouza.intranet.bean.Professor){ %>
							
										<a href="../painel/curso!lista.java">Cursos</a> 
										<a href="../painel/materia!lista.java">Disciplinas</a> 
										<a href="../painel/evento!lista.java">Eventos</a> 
										<a href="../painel/noticia!lista.java">Notícias</a>
										<a href="../painel/professor!lista.java">Professores</a> 
										<a href="../painel/upload!lista.java">Arquivos</a> 
										<a href="../painel/letiva!lista.java">Disciplina Letiva</a> 
										<a href="../painel/aluno!lista.java">Alunos</a> 
										
									<% } %>
									
									<% if (session.getAttribute("pessoa") instanceof br.edu.gamaesouza.intranet.bean.Aluno){ %>
										<a href="../painel/aluno!gradeParaAluno.java">Ver&nbsp;grade</a> 
									<% } %>
									<a href="../painel/vaga!lista.java">Vagas&nbsp;Enviadas</a> 
									<a href="../painel/trocarsenha.jsp">Alterar&nbsp;Senha</a>	
								</div>
							</li>
							<li><span style="color:#FFFFFF">|</span></li>
							<li><a href="../jsp/index!events.java"> Principal </a></li>
						</ul>
						<div style="clear: both"></div>
					</div>
				</c:if>
				</div>
			</td>
		</tr>
	</table>

	<table width="100%" align="center" id="all" style="background-color: #D3D3D3;">
		<tr>
			<td>
				<div id="content" >
					<table id="contenttable" align="center" cellpadding="10" style="width: 800px; margin: 0pt auto;border-style:outset;border-width:1px;border-color:#D3D3D3;" >
						<tr id=table2>
							<td align="center">
								<div style="text-align: center">
									<ul id="sddm" style="width: 110%;">
										<li>
											<a href="#" onmouseover="mopen('m2')" onmouseout="mclosetime()">Links&nbsp;Úteis</a>
											<div id="m2" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
												<a href="../jsp/biblioteca.jsp">Bibliotecas Virtuais&nbsp;/&nbsp;Digitais</a> 
												<a href="../jsp/revistasletras.jsp">Revistas de Letras&nbsp;/&nbsp;Digitais</a>
											</div>
										</li>
										<li><a href="../jsp/nossahistoria.jsp">História</a></li>
										<li><a href="../jsp/regulamentos.jsp">Regulamentos</a></li>
										<li><a target="" href="../jsp/regimento.jsp">Regimento</a></li>
										<li><a href="../jsp/cursos.jsp">Cursos</a></li>
										<li><a href="../jsp/buscarMaterial!carregaFiltrosPesquisa.java">Material</a></li>
										<li><a href="../jsp/ouvidoria!prepare.java">Ouvidoria</a></li>
									</ul>
									<div style="clear: both"></div>
								</div>

							</td>
						</tr>
						<tr>
							<td>
								<decorator:body />
							</td>
						</tr>	
					</table>
				</div>
			</td>
		</tr>
	</table>
	<!-- Application Footer -->
	<table align="center" width="100%" height="30" id="footer" cellspacing="3" id="footer">
		<tr>
			<td>
			<center>© 2011 - Intranet Gama & Souza 1.0 Beta ${sessionScope.pessoa.dataUltimoAcesso}</br>
			<a href="http://www.opengti.com.br" >Todos os direito reservados - Http://www.opengti.com.br</a>
			</center>
				
			</td>
		</tr>
	</table>
</body>
</html>
