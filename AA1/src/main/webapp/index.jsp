<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<html>
<fmt:bundle basename="messages">
	<head>
		<title><fmt:message key="header.Login"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
<body>
	<div class="container">
		<h2>Login</h2>
		<form name="login" action="login.jsp" method="POST">
			<input type="text" name="login" placeholder="<fmt:message key="nomeUsuario.Login"/>"/> <br> <br>
			
			<input type="text" name="senha" placeholder="<fmt:message key="senha.Login"/>"/> <br> <br>
			
			<input type="submit" name="fazLogin" value="<fmt:message key="entrar.Login"/>">
			
		</form>
	</div>
</body>
</fmt:bundle>
</html>
