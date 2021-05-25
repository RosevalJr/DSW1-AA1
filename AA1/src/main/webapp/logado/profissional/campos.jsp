<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${profissional != null}">
				<fmt:message key="profissional.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="profissional.cria" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${profissional != null}">
		<input type="hidden" name="id" value="<c:out value='${profissional.id}' />" />
	</c:if>
	<tr>
		<td><label for="cpf"> <fmt:message key="profissional.cpf" />
		</label></td>
		<td><input type="text" id="cpf" name="cpf" size="18" required
			value="<c:out value='${profissional.cpf}' />" /></td>
	</tr>
	<tr>
		<td><label for="nome"><fmt:message key="profissional.nome" />
		</label></td>
		<td><input type="text" name="nome" size="45" required
			value="<c:out value='${profissional.nome}' />" /></td>
	</tr>
	
	<tr>
		<td><label for="senha"><fmt:message key="profissional.senha" />
		</label></td>
		<td><input type="text" name="senha" size="45" required
			value="<c:out value='${profissional.senha}' />" /></td>
	</tr>
	
	<tr>
		<td><label for="email"><fmt:message key="profissional.email" />
		</label></td>
		<td><input type="text" name="email" size="45" required
			value="<c:out value='${profissional.email}' />" /></td>
	</tr>
	
	<tr>
		<td><label for="telefone"><fmt:message key="profissional.telefone" />
		</label></td>
		<td><input type="text" name="telefone" size="45" required
			value="<c:out value='${profissional.telefone}' />" /></td>
	</tr>
	
	<tr>
		<td><label for="sexo"><fmt:message key="profissional.sexo" />
		</label></td>
		<td><input type="text" name="sexo" size="45" required
			value="<c:out value='${profissional.sexo}' />" /></td>
	</tr>
	
	<tr>
		<td><label for="nascimento"><fmt:message key="profissional.nascimento" />
		</label></td>
		<td><input type="text" name="nascimento" size="45" required
			value="<c:out value='${profissional.nascimento}' />" /></td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="salvar.link" />" /></td>
	</tr>
</table>