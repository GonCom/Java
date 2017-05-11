<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css"
	href="../resources/css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:include flush="true" page="../WEB-INF/menu.jspf"></jsp:include>
		</div>
		<div id="Buscador">
			<form method="POST" action="../controller">
				<fieldset>
					<legend>Buscador de socios</legend>
					<table>
						<tr>
							<td>Nombre <input type="text" name="buscador" id="buscador"
								value="${sessionScope.buscador}"></td>
						</tr>
						<tr>
							<td><input type="submit" value="Enviar" name="Enviar"></td>
							<td><input type="hidden" name="operacion"
								value="buscarSocio"></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>


		<!-- Código para mostrar los resultados de la búsqueda -->
		<c:if test="${listado!=null}">
			<c:choose>
				<c:when test="${!sessionScope.listado.isEmpty()}">
					<div id="tabla">
						<table width="100%" border="1">
							<caption>Listado de Socios</caption>
							<tr>
								<th scope="col">IDSOCIO</th>
								<th scope="col">NOMBRE</th>
								<th scope="col">DIRECCION</th>
								<th scope="col">Editar</th>
							</tr>
							<c:forEach items="${sessionScope.listado}" var="socio">
								<tr>
									<td>${socio.idsocio}</td>
									<td>${socio.nombre}</td>
									<td>${socio.direccion}</td>
									<td><a
										href="/BibliotecaJPAJSP/controller?operacion=editarSocio&id=${socio.idsocio}">Editar</a></td>
								</tr>
							</c:forEach>
							<c:remove scope="session" var="listado" />
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<p align="center">No existen coincidencias</p>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
</body>
</html>