<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<fmt:message key="vaga.criar" />
	</caption>

	<tr>
		<td><label for="descricao"> <fmt:message
					key="vaga.descricao" />
		</label></td>
		<td><input type="text" id="descricao" name="descricao" size="45"
			required value="<c:out value='${vaga.descricao}' />" /></td>
	</tr>
	<tr>
		<td><label for="remuneracao"><fmt:message
					key="vaga.remuneracao" /> </label></td>
		<td><input type="text" name="remuneracao" size="45" required
			value="<c:out value='${vaga.remuneracao}' />" /></td>
	</tr>

	<tr>
		<td><label for="datalimite"><fmt:message
					key="vaga.datalimite" /> </label></td>
		<td><input type="text" name="datalimite" size="45" required
			value="<c:out value='${vaga.datalimite}' />" /></td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="salvar.link" />" /></td>
	</tr>
</table>