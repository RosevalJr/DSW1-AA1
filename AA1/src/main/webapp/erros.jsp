<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<fmt:bundle basename="messages">
	<head>
		<title><fmt:message key="erro" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
<body>
	
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
	<h1>
		<fmt:message key="erro" />
	</h1>
	
	<c:if test="${mensagens.existeErros}">
		<div id="erro">
			<ul>
				<c:forEach var="erro" items="${mensagens.erros}">
					<li> ${erro} </li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	
	<h4>
		<a href="/<%=contextPath%>/empresas"> 
			<fmt:message key="empresa.entidade" />
		</a> 
		
		<a href="/<%=contextPath%>/usuarios"> 
			<fmt:message key="usuario.entidade" />
		</a> 
		
		<a href="/<%=contextPath%>/profissionais"> 
			<fmt:message key="profissional.entidade" />
		</a> 
		
		<a href="/<%=contextPath%>/logout.jsp"> 
			<fmt:message key="sair.link" />
		</a> 
	</h2>
	</div>
</body>
</fmt:bundle>
</html>