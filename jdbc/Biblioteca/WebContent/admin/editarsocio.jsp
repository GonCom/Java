<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="Stylesheet" type="text/css"
	href="../resources/css/estilo.css">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:include flush="true" page="../WEB-INF/menu.jspf"></jsp:include>
		</div>
		<div id="Buscador">
			<form action="../controller" method="POST">
				<fieldset>
					<legend>Editar</legend>
					<table>
						<tr>
							<td><input type="hidden" name="idsocio"
								value="${socio.idsocio}"></td>
						</tr>
						<tr>
							<td>Nombre <input type="text" name="nombre"
								value="${socio.nombre}">
							<td>
						</tr>
						<tr>
							<td>Direccion <input type="text" name="direccion"
								value="${socio.direccion}"></td>
						</tr>
						<tr>
							<td><input type="submit" name="Editar" value="Editar"></td>
							<td><input type="hidden" name="operacion"
								value="actualizarSocio"></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>

	</div>
	</
</body>
</html>