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
		<c:if test="${!sessionScope.morosos.isEmpty()}">
			<div id="tabla">
				<table width="100%" border="1">
					<caption>Listado de Morosos</caption>
					<tr>
						<th scope="col">IDSOCIO</th>
						<th scope="col">NOMBRE</th>
						<th scope="col">LIBROS</th>
					</tr>
					<c:forEach items="${sessionScope.morosos}" var="socio">
						<tr>
							<td>${socio.idsocio}</td>
							<td>${socio.nombre}</td>
							<td><a
								href="/Biblioteca/controller?operacion=verlibros&idsocio=${socio.idsocio}">Ver
									libros</a></td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</c:if>
		<br>
		<c:if test="${sessionScope.listalibros!=null}">
			<c:choose>
				<c:when test="${!sessionScope.listalibros.isEmpty()}">
					<div id="tabla">
						<table width="100%" border="1">
							<caption>Listado de libros de: ${sessionScope.nombre}</caption>
							<tr>
								<th scope="col">TÍTULO</th>
								<th scope="col">DIAS DE DEMORA</th>
							</tr>
							<c:forEach items="${sessionScope.listalibros}" var="libro">
								<tr>
									<td>${libro.titulo}</td>
									<td align="center">${libro.diasdemora}</td>
								</tr>
							</c:forEach>
							<c:remove scope="session" var="listalibros" />
						</table>
					</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<c:remove scope="session" var="nombre" />
		</c:if>

	</div>
</body>
</html>