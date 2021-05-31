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
				<fmt:message key="candidaturas.usuario" /> <c:out value="${profissional.nome}" />
			</h1>
			<h2>
				<a href="${pageContext.request.contextPath}/logout.jsp"> 
					<fmt:message key="sair.link" />
				</a>
				<br/>
				<br/>
				<a href="#" onclick="history.go(-1)"> 
				<fmt:message key="voltar" />
				</a> 
			</h2>
			<br/>
		</div>
		<div align="center">
			<h2> <fmt:message key="profissional.info" /> </h2>
			<table border="1">
				<tr>
					<th><fmt:message key="profissional.id" /></th>
					<th><fmt:message key="profissional.nome" /></th>
					<th><fmt:message key="profissional.cpf" /></th>
					<th><fmt:message key="profissional.telefone" /></th>
					<th><fmt:message key="profissional.sexo" /></th>
					<th><fmt:message key="profissional.nascimento" /></th>
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
			<h2> <fmt:message key="vaga.info" /> </h2>
						<table border="1">
				<tr>
					<th><fmt:message key="vaga.id" /></th>
					<th><fmt:message key="vaga.descricao" /></th>
					<th><fmt:message key="vaga.remuneracao" /></th>
					<th><fmt:message key="vaga.datalimite" /></th>
					<th><fmt:message key="vaga.curriculo" /></th>
				</tr>
					<tr>
						<td><c:out value="${candidatura.idvaga}" /></td>
						<td><c:out value="${candidatura.descricao}" /></td>
						<td><c:out value="${candidatura.remuneracao}" /></td>
						<td><c:out value="${candidatura.datalimite}" /></td>
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
		<td><label for="link"><fmt:message key="link.conferencia" />
		</label></td>
		<td><input type="text" name="link" size="45"></td>
				</tr>
				<tr>
		<td colspan="2" align="center"><input type="submit" name="entrevista"
			value="<fmt:message key="entrevista" />" /></td>
		<td colspan="2" align="center"><input type="submit" name="naoSelecionado"
			value="<fmt:message key="naoSelecionado" />" /></td>
				</tr>
				</table>
			</form>
		</div>
	</body>
</fmt:bundle>

</html>