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
				<a href="/<%=contextPath%>/users/empresas/cadastroVaga"> 
					<fmt:message key="vaga.criar" />
				</a> 
			</h2>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="vaga.id" /></th>
					<th><fmt:message key="vaga.cnpj" /></th>
					<th><fmt:message key="vaga.descricao" /></th>
					<th><fmt:message key="vaga.remuneracao" /></th>
					<th><fmt:message key="vaga.datalimite" /></th>
					<th><fmt:message key="vaga.aberta" /></th>
				</tr>
				<c:forEach var="vaga" items="${requestScope.listaVagas}">
					<tr>
						<td><c:out value="${vaga.idvaga}" /></td>
						<td><c:out value="${vaga.cnpjempresa}" /></td>
						<td><c:out value="${vaga.descricao}" /></td>
						<td><c:out value="${vaga.remuneracao}" /></td>
						<td><c:out value="${vaga.datalimite}" /></td>
						<c:choose>
							<c:when test="${vaga.aberta == 'true'}">
								<td><fmt:message key="vaga.aberta.true" /></td>
							</c:when>
							<c:otherwise>
								<td><fmt:message key="vaga.aberta.false" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>