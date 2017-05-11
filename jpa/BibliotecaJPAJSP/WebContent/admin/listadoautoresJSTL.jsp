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
		<c:if test="${!sessionScope.listadoAutores.isEmpty()}">
			<div id="tabla">
				<table width="100%" border="1">
					<caption>Listado de Autores</caption>
					<tr>
						<th scope="col">IDAUTOR</th>
						<th scope="col">NOMBRE</th>
						<th scope="col">FECHA NACIMIENTO</th>
						<th scope="col">LIBROS</th>
					</tr>
					<c:forEach items="${sessionScope.listadoAutores}" var="autor">
						<tr>
							<td>${autor.idautor}</td>
							<td>${autor.nombre}</td>
							<td>${autor.getFechanacimientoformateada()}</td>
							<td>${autor.libros.size()}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>