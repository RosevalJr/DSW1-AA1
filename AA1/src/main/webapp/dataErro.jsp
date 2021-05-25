<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<fmt:bundle basename="messages">
	<head>
		<title>Data Incorreta</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
<body>
	<h2>Data Incorreta</h2>
	<c:if test="${mensagensErros.existeErros}">
		<div id="erro">
			<ul>
				<c:forEach var="erro" items="${mensagensErros.erros}">
					<li> ${erro} </li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
</body>
</fmt:bundle>
</html>