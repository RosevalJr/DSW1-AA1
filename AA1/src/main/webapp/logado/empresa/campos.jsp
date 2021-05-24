<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${empresa != null}">
				<fmt:message key="empresa.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="empresa.cria" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${empresa != null}">
		<input type="hidden" name="id" value="<c:out value='${empresa.id}' />" />
	</c:if>
	<tr>
		<td><label for="cnpj"> <fmt:message key="empresa.cnpj" />
		</label></td>
		<td><input type="text" id="cnpj" name="cnpj" size="18" required
			value="<c:out value='${empresa.cnpj}' />" /></td>
	</tr>
	<tr>
		<td><label for="nome"><fmt:message key="empresa.nome" />
		</label></td>
		<td><input type="text" name="nome" size="45" required
			value="<c:out value='${empresa.nome}' />" /></td>
	</tr>
	
	<tr>
		<td><label for="senha"><fmt:message key="empresa.senha" />
		</label></td>
		<td><input type="text" name="senha" size="45" required
			value="<c:out value='${empresa.senha}' />" /></td>
	</tr>
	
	<tr>
		<td><label for="email"><fmt:message key="empresa.email" />
		</label></td>
		<td><input type="text" name="email" size="45" required
			value="<c:out value='${empresa.email}' />" /></td>
	</tr>
	
	<tr>
		<td><label for="descricao"><fmt:message key="empresa.descricao" />
		</label></td>
		<td><input type="text" name="descricao" size="45" required
			value="<c:out value='${empresa.descricao}' />" /></td>
	</tr>
	
	<tr>
		<td><label for="cidade"><fmt:message key="empresa.cidade" />
		</label></td>
		<td><input type="text" name="cidade" size="45" required
			value="<c:out value='${empresa.cidade}' />" /></td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="salvar.link" />" /></td>
	</tr>
</table>