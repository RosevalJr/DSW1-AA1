<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<fmt:bundle basename="messages">
	<head>
<title><fmt:message key="header.Login" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<c:if test="${mensagensErros.existeErros}">
			<div id="erro">
				<ul>
					<c:forEach var="erro" items="${mensagensErros.erros}">
						<li>${erro}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<div align="center" class="container">
			<h2>Login</h2>
			<form name="login" action="index.jsp" method="POST">
				<input type="text" name="login"
					placeholder="<fmt:message key="nomeUsuario.Login"/>" /> <br> <br>

				<input type="password" name="senha"
					placeholder="<fmt:message key="senha.Login"/>" /> <br> <br>

				<input type="submit" name="loginOk"
					value="<fmt:message key="entrar.Login"/>">
			</form>
			<br>
			<h2>
				<a href="/<%=contextPath%>/listarVagasAbertas"> <fmt:message
						key="header.vaga" />
				</a>
			</h2>
		</div>
	</body>
</fmt:bundle>
</html>
