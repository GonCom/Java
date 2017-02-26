<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de identificacion</title>
<link rel="Stylesheet" type="text/css"
	href="/Biblioteca/resources/css/estilo.css">
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<form action="j_security_check" method="POST">
			<table cellspacing="2" cellpadding="3" border="0" width="100%">
				<tr>
					<td colspan="2">
						<h2>Introduzca sus datos de usuario</h2>
					</td>
				</tr>
				<tr>
					<td colspan="2"><c:choose>
							<c:when test="${sessionScope.intentosLogin==null}">
								<c:set var="intentosLogin" scope="session" value="0" />
							</c:when>
							<c:otherwise>
								<font color="#ff0000"> <strong>Error</strong> :Usuario o
									password incorrectos <c:set var="intentosLogin" scope="session"
										value="${sessionScope.intentosLogin + 1}" />
								</font>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td width="11%">*Username</td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td>*Password</td>
					<td><input type="password" name="j_password" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" name="login" value="Login" />
						<a href="/Biblioteca/nuevosocio.jsp">Registrate</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>