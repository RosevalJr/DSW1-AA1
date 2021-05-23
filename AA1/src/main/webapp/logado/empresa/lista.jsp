<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="messages">

	<head>
		<title><fmt:message key="header.empresa" /></title>
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
				<a href="/<%=contextPath%>/empresas/cadastro">
					<fmt:message key="empresa.criar" />
				</a> 
			</h2>
			<h3><fmt:message key="empresa.listar" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="empresa.id" /></th>
					<th><fmt:message key="empresa.cnpj" /></th>
					<th><fmt:message key="empresa.nome" /></th>
					<th><fmt:message key="empresa.senha" /></th>
					<th><fmt:message key="empresa.email" /></th>
					<th><fmt:message key="empresa.descricao" /></th>
					<th><fmt:message key="empresa.cidade" /></th>
				</tr>
				<c:forEach var="empresa" items="${requestScope.listaEmpresas}">
					<tr>
						<td><c:out value="${empresa.id}" /></td>
						<td><c:out value="${empresa.cnpj}" /></td>
						<td><c:out value="${empresa.nome}" /></td>
						<td><c:out value="${empresa.senha}" /></td>
						<td><c:out value="${empresa.email}" /></td>
						<td><c:out value="${empresa.descricao}" /></td>
						<td><c:out value="${empresa.cidade}" /></td>
						<td><a
							href="/<%= contextPath %>/empresas/edicao?id=<c:out value='${empresa.id}' />">
								<fmt:message key="empresa.update" />
						</a> 
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a
									href="/<%= contextPath %>/empresas/remocao?id=<c:out value='${empresa.id}' />"
									onclick="return confirm('<fmt:message key="confirm.link" />');">
									<fmt:message key="empresa.delete" />
								</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>