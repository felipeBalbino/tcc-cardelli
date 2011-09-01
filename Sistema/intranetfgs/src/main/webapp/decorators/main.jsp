<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" />
	<link type="text/css" href="../css/custom.css" rel="stylesheet" />
	<link type="text/css" href="../css/south-street/jquery-ui-1.8.14.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui-1.8.14.custom.min.js"></script>
	<script type="text/javascript" src="../js/custom.js"></script>
	<script src="../js/jquery.maskedinput-1.3.js" type="text/javascript"></script>
	

<title><decorator:title
		default="Faculdade Gama e Souza | Intranet " />
</title>

<decorator:head />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/decorators/main.css" />



<script type="text/javascript">
	var timeout = 500;
	var closetimer = 0;
	var ddmenuitem = 0;

	function showlayer(layer) {
		var myLayer = document.getElementById(layer);
		if (myLayer.style.display == "none" || myLayer.style.display == "") {
			myLayer.style.display = "block";
		} else {
			myLayer.style.display = "none";
		}
	}

	function mopen(id) {

		mcancelclosetime();

		if (ddmenuitem)
			ddmenuitem.style.visibility = 'hidden';

		ddmenuitem = document.getElementById(id);
		ddmenuitem.style.visibility = 'visible';

	}

	function mclose() {
		if (ddmenuitem)
			ddmenuitem.style.visibility = 'hidden';
	}

	function mclosetime() {
		closetimer = window.setTimeout(mclose, timeout);
	}

	function mcancelclosetime() {
		if (closetimer) {
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
							<td colspan="1"><img src="../images/logo.png" />
							</td>

						</tr>
					</table>
					
					<c:if test="${not empty param.lingua}">
						<fmt:setLocale value="${param.lingua}" scope="session" />
					</c:if>
					
					<c:if test="${sessionScope.pessoa == null}">
						<div id="m1" style="text-align: right; width: 98%;">
							<ul id="sddm2"
								style="align: right; width: 100%; margin: 0pt auto;">
								<li><a href="../jsp/log!prepare.java"><fmt:message
											key="menu.registrar" />
								</a>
								</li>
								<li><span style="color: #FFFFFF">|</span>
								</li>
								<li><a href="../jsp/login.jsp"><fmt:message
											key="menu.login" />
								</a>
								</li>
								<li><span style="color: #FFFFFF">|</span>
								</li>
								<li><a href="../jsp/index!events.java"> <fmt:message
											key="menu.principal" /> </a>
								</li>
							</ul>
							<div style="clear: both"></div>
						</div>
					</c:if>

					<c:if test="${sessionScope.pessoa != null}">
						<div id="m1" style="text-align: right; width: 98%;">
							<ul id="sddm2"
								style="align: right; width: 100%; margin: 0pt auto;">
								<li><a href="../jsp/log!out.java"><fmt:message
											key="menu.sair" />&nbsp;(${sessionScope.pessoa.login})</a>
								</li>
								<li><span style="color: #FFFFFF">|</span>
								</li>
								<li><a href="#" onmouseover="mopen('m3')"
									onmouseout="mclosetime()"><fmt:message key="menu.opcoes" />
								</a>
									<div id="m3" onmouseover="mcancelclosetime()"
										onmouseout="mclosetime()">
										<%
											if (session.getAttribute("pessoa") instanceof br.edu.gamaesouza.intranet.bean.Professor) {
										%>

										<a href="../painel/curso!lista.java"><fmt:message
												key="menu.cursos" />
										</a> <a href="../painel/materia!lista.java"><fmt:message
												key="menu.disciplinas" />
										</a> <a href="../painel/evento!lista.java"><fmt:message
												key="menu.eventos" />
										</a> <a href="../painel/noticia!lista.java"><fmt:message
												key="menu.noticias" />
										</a> <a href="../painel/professor!lista.java"><fmt:message
												key="menu.professores" />
										</a> <a href="../painel/upload!lista.java"><fmt:message
												key="menu.arquivos" />
										</a> <a href="../painel/letiva!lista.java"><fmt:message
												key="menu.disciplinaletiva" />
										</a> <a href="../painel/aluno!lista.java"><fmt:message
												key="menu.alunos" />
										</a>

										<%
											}
										%>

										<%
											if (session.getAttribute("pessoa") instanceof br.edu.gamaesouza.intranet.bean.Aluno) {
										%>
										<a href="../painel/aluno!gradeParaAluno.java"><fmt:message
												key="menu.vergrade" />
										</a>
										<%
											}
										%>
										<a href="../painel/vaga!lista.java"><fmt:message
												key="menu.vagasenviadas" />
										</a> <a href="../painel/trocarsenha.jsp"><fmt:message
												key="menu.alterarsenha" />
										</a>
									</div></li>
								<li><span style="color: #FFFFFF">|</span>
								</li>
								<li><a href="../jsp/index!events.java"> <fmt:message
											key="menu.principal" /> </a>
								</li>
							</ul>
							<div style="clear: both"></div>
						</div>
					</c:if>
				</div></td>
		</tr>
	</table>

	<table width="100%" align="center" id="all"
		style="background-color: #D3D3D3;">
		<tr>
			<td>
				<div id="content">
					<table id="contenttable" align="center" cellpadding="10"
						style="width: 800px; margin: 0pt auto; border-style: outset; border-width: 1px; border-color: #D3D3D3;">
						<tr id=table2>
							<td align="center">
								<div style="text-align: center">
									<ul id="sddm" style="width: 110%;">
										<li><a href="#" onmouseover="mopen('m2')"
											onmouseout="mclosetime()"><fmt:message
													key="menu.linksuteis" />
										</a>
											<div id="m2" onmouseover="mcancelclosetime()"
												onmouseout="mclosetime()">
												<a href="../jsp/biblioteca.jsp"><fmt:message
														key="menu.biblioteca" />
												</a> <a href="../jsp/revistasletras.jsp"><fmt:message
														key="menu.revista" />
												</a>
											</div></li>
										<li><a href="../jsp/nossahistoria.jsp"><fmt:message
													key="menu.historia" />
										</a>
										</li>
										<li><a href="../jsp/regulamentos.jsp"><fmt:message
													key="menu.regulamentos" />
										</a>
										</li>
										<li><a target="" href="../jsp/regimento.jsp"><fmt:message
													key="menu.regimento" />
										</a>
										</li>
										<li><a href="../jsp/cursos.jsp"><fmt:message
													key="menu.cursos" />
										</a>
										</li>
										<li><a
											href="../jsp/buscarMaterial!carregaFiltrosPesquisa.java"><fmt:message
													key="menu.material" />
										</a>
										</li>
										<li><a href="../jsp/ouvidoria!prepare.java"><fmt:message
													key="menu.ouvidoria" />
										</a>
										</li>
									</ul>
									<div style="clear: both"></div>
								</div></td>
						</tr>
						<tr>
							<td><decorator:body /></td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
	<!-- Application Footer -->
	<table align="center" width="100%" height="30" id="footer"
		cellspacing="3" id="footer">
		<tr>
			<td>
				<center>
					© 2011 - Intranet Gama & Souza 2.0 Beta
					${sessionScope.pessoa.dataUltimoAcesso}</br> <a
						href="http://www.opengti.com.br">http://www.opengti.com.br</a>
				</center></td>
		</tr>
	</table>
</body>
</html>
