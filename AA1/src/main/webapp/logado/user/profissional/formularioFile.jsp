<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<fmt:bundle basename="messages">
	<head>
<title><fmt:message key="header.aplicarCandidatura" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="candidaturaVaga.usuario" />
				<c:out value="${usuarioLogado.nome}" />
			</h1>
			<h2>
				<a href="#" onclick="history.go(-1)"> <fmt:message key="voltar" />
				</a> &nbsp;&nbsp;&nbsp; <a
					href="${pageContext.request.contextPath}/logout.jsp"> <fmt:message
						key="sair.link" />
				</a> <br /> <br />
			</h2>

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
				</table>
				<br />
				<h3>
					<fmt:message key="candidatura.upload" />
				</h3>
				<form method="post" action="insereCandidatura"
					enctype="multipart/form-data">
					<fmt:message key="candidatura.escolhaArquivo" />
					<input type="file" name="uploadFile" /> <input type="submit"
						value="<fmt:message key="enviar" />" />
				</form>
			</div>
		</div>

	</body>
</fmt:bundle>
</html>