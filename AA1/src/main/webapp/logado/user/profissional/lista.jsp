<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="messages">
	<head>
		<title><fmt:message key="header.candidatura" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="candidaturas.usuario" /> <c:out value="${usuarioLogado.nome}" />
			</h1>
			<h2>
				<a href="${pageContext.request.contextPath}/logout.jsp"> 
					<fmt:message key="sair.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/users/profissionais/aplicarVaga"> 
					<fmt:message key="candidatura.aplicar" />
				</a> 
			</h2>
			<br/>
			<h3><fmt:message key="candidatura.listar" /></h3>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="vaga.id" /></th>
					<th><fmt:message key="vaga.cnpj" /></th>
					<th><fmt:message key="vaga.descricao" /></th>
					<th><fmt:message key="vaga.remuneracao" /></th>
					<th><fmt:message key="vaga.datalimite" /></th>
					<th><fmt:message key="vaga.status" /></th>
					<th><fmt:message key="vaga.curriculo" /></th>
				</tr>
				<c:forEach var="candidatura" items="${requestScope.listaCandidaturas}">
					<tr>
						<td><c:out value="${candidatura.idvaga}" /></td>
						<td><c:out value="${candidatura.cnpjempresa}" /></td>
						<td><c:out value="${candidatura.descricao}" /></td>
						<td><c:out value="${candidatura.remuneracao}" /></td>
						<td><c:out value="${candidatura.datalimite}" /></td>
						<td><c:out value="${candidatura.status}" /></td>
						<td><a href="/<%=contextPath%>/curriculo/<c:out value="${candidatura.curriculo}" />"> 
					<c:out value="${candidatura.curriculo}" />
				</a> </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>