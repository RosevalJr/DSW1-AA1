<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="messages">

	<head>
		<title><fmt:message key="header.vaga" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="vaga.empresa" /> <c:out value="${usuarioLogado.nome}" />
			</h1>
			<h2>
				<a href="${pageContext.request.contextPath}/logout.jsp"> 
					<fmt:message key="sair.link" />
				</a>
				<br/>
				<br/>
			</h2>
			<br/>
		</div>
		<div align="center">
			<h2> Informaçõs do Profissional </h2>
			<table border="1">
				<tr>
					<th>IDPROFISSIONAL</th>
					<th>NOME</th>
					<th>CPF</th>
					<th>TELEFONE</th>
					<th>SEXO</th>
					<th>NASCIMENTO</th>
				</tr>
					<tr>
						<td><c:out value="${profissional.id}" /></td>
						<td><c:out value="${profissional.nome}" /></td>
						<td><c:out value="${profissional.cpf}" /></td>
						<td><c:out value="${profissional.telefone}" /></td>
						<td><c:out value="${profissional.sexo}" /></td>
						<td><c:out value="${profissional.nascimento}" /></td>
					</tr>
			</table>
			<br>
			<h2> Informações da Vaga </h2>
						<table border="1">
				<tr>
					<th>IDVAGA</th>
					<th>IDPESSOA</th>
					<th>CNPJ EMPRESA</th>
					<th>DESCRICAO</th>
					<th>REMUNERAÇÃO</th>
					<th>DATA LIMITE</th>
					<th>STATUS</th>
					<th>CURRICULO</th>
				</tr>
					<tr>
						<td><c:out value="${candidatura.idvaga}" /></td>
						<td><c:out value="${candidatura.idpessoa}" /></td>
						<td><c:out value="${candidatura.descricao}" /></td>
						<td><c:out value="${candidatura.remuneracao}" /></td>
						<td><c:out value="${candidatura.datalimite}" /></td>
						<c:choose>
					    	<c:when test="${candidatura.status=='ABERTO'}">
							<td><a href="/<%=contextPath%>/users/empresas/avaliando?idvaga=<c:out value="${candidatura.idvaga}" />&idpessoa=<c:out value="${candidatura.idpessoa}" /> "> 
							<c:out value="${candidatura.status}" />
							</a> </td>
					    	</c:when>    
					    	<c:otherwise>
							<td><c:out value="${candidatura.status}" /> </td>
					    	</c:otherwise>
						</c:choose>
						<td><a href="/<%=contextPath%>/curriculo/<c:out value="${candidatura.curriculo}" />"> 
					<c:out value="${candidatura.curriculo}" />
				</a> </td>
					</tr>
			</table>
			<form action="avaliaCandidatura" method="post">
			<input type="hidden" name="idVaga" value="<c:out value='${candidatura.idvaga}' />" />
			<input type="hidden" name="idProfissional" value="<c:out value='${candidatura.idpessoa}' />" />
			<table border=1>
					<tr>
		<td><label for="link">Link Conferência
		</label></td>
		<td><input type="text" name="link" size="45"></td>
				</tr>
				<tr>
		<td colspan="2" align="center"><input type="submit" name="entrevista"
			value="Entrevista" /></td>
		<td colspan="2" align="center"><input type="submit" name="naoSelecionado"
			value="Não Selecionado" /></td>
				</tr>
				</table>
			</form>
		</div>
	</body>
</fmt:bundle>

</html>