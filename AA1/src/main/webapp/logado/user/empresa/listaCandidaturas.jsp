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
				<c:forEach var="candidatura" items="${requestScope.listaCandidaturas}">
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
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>