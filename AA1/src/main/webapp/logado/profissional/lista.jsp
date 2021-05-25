<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="messages">

	<head>
		<title><fmt:message key="header.profissional" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="empresa.receber" />
			</h1>
			<h2>
				<a href="/<%=contextPath%>/profissionais"> 
					<fmt:message key="profissional.entidade" />
				</a> 
				&nbsp;&nbsp;&nbsp;
				<a href="/<%=contextPath%>/usuarios"> 
					<fmt:message key="usuario.entidade" />
				</a> 
				&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/logout.jsp"> 
					<fmt:message key="sair.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/profissional/cadastro">
					<fmt:message key="profissional.criar" />
				</a> 
			</h2>
			<h3><fmt:message key="profissional.listar" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="profissional.id" /></th>
					<th><fmt:message key="profissional.cpf" /></th>
					<th><fmt:message key="profissional.nome" /></th>
					<th><fmt:message key="profissional.senha" /></th>
					<th><fmt:message key="profissional.email" /></th>
					<th><fmt:message key="profissional.telefone" /></th>
					<th><fmt:message key="profissional.sexo" /></th>
					<th><fmt:message key="profissional.nascimento" /></th>
				</tr>
				<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
					<tr>
						<td><c:out value="${profissional.id}" /></td>
						<td><c:out value="${profissional.cpf}" /></td>
						<td><c:out value="${profissional.nome}" /></td>
						<td><c:out value="${profissional.senha}" /></td>
						<td><c:out value="${profissional.email}" /></td>
						<td><c:out value="${profissional.telefone}" /></td>
						<td><c:out value="${profissional.sexo}" /></td>
						<td><c:out value="${profissional.nascimento}" /></td>
						<td><a
							href="/<%= contextPath %>/profissionais/edicao?id=<c:out value='${profissional.id}' />">
								<fmt:message key="profissional.update" />
						</a> 
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a
									href="/<%= contextPath %>/profissionais/remocao?id=<c:out value='${profissional.id}' />"
									onclick="return confirm('<fmt:message key="confirmar.link" />');">
									<fmt:message key="profissional.delete" />
								</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>