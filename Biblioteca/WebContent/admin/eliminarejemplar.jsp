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

			<form method="post" action="../controller">
				<table>
					<tr>
						<td><label>Buscar</label> <input type="text" name="buscador"
							id="buscador" value="${sessionScope.buscador}"></td>
						<td><select name="tipo" id="tipo">
								<c:choose>
									<c:when
										test="${sessionScope.opcion==null or sessionScope.opcion=='autor'}">
										<option value="autor" name="Autor" selected>Autor</option>
										<option value="titulo" name="Titulo">Titulo</option>
										<option value="isbn" name="ISBN">ISBN</option>
									</c:when>
									<c:when test="${sessionScope.opcion=='titulo'}">
										<option value="autor" name="Autor">Autor</option>
										<option value="titulo" name="Titulo" selected>Titulo</option>
										<option value="isbn" name="ISBN">ISBN</option>
									</c:when>
									<c:otherwise>
										<option value="autor" name="Autor">Autor</option>
										<option value="titulo" name="Titulo">Titulo</option>
										<option value="isbn" name="ISBN" selected>ISBN</option>
									</c:otherwise>
								</c:choose>
						</select></td>

						<td><input type="submit" name="Enviar" value="Enviar" /> <input
							type="hidden" name="operacion" value="listarLibros" /> <input
							type="hidden" name="valor" value="eliminar" /></td>
					</tr>
				</table>
			</form>
		</div>

		<c:if test="${sessionScope.libros!=null}">
			<c:choose>
				<c:when test="${!sessionScope.libros.isEmpty()}">
					<div id="tabla">
						<table width="100%" border="1">
							<caption>Listado de Libros</caption>
							<tr>
								<th scope="col">AUTOR</th>
								<th scope="col">TITULO</th>
								<th scope="col">ELIMINAR</th>
							</tr>
							<c:forEach items="${sessionScope.libros}" var="libro">
								<tr>
									<td>${libro.autor}</td>
									<td>${libro.titulo}</td>
									<td><a
										href="/Biblioteca/controller?operacion=mostrarEjemplares&isbn=${libro.isbn}&titulo=${libro.titulo}">Eliminar
											ejemplares</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<p align="center">No existen coincidencias</p>
				</c:otherwise>
			</c:choose>
		</c:if>

			<c:if test="${sessionScope.ejemplares!=null}">
				<div id="tabla">
				<form action="../controller" method="post" >
					<table width="100%" border="1">
						<caption>Listado de ejemplares del libro ${sessionScope.titulo} </caption>
						<tr>
							<th scope="col">Codigo de Ejemplar</th>
						</tr>
						<c:forEach items="${sessionScope.ejemplares}" var="ejemplar">
							<tr align="center">
								<td><input name="ejemplaresEliminar" type="checkbox"
									value="${ejemplar.idejemplar}" />${ejemplar.idejemplar}</td>
								<br>
							</tr>
						</c:forEach>
						<tr align="center">
							<td><input type="submit" name="eliminar" value="Eliminar" />
								<input type="hidden" name="operacion" value="eliminarEjemplar" />
							</td>
						</tr>
						
					</table>
				</form>
				</div>
			<c:remove scope="session" var="ejemplares" />
		</c:if>
	</div>
</body>
</html>