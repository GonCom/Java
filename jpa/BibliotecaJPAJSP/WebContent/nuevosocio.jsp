<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css"
	href="resources/css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:include flush="true" page="WEB-INF/menu.jspf"></jsp:include>
		</div>
		<div id="Buscador">
			<form action="controller2" method="post">
				<table border="1px solid">
					<tr>
						<td>Nombre <input type="text" size="40" name="usuario"></td>
					</tr>
					<tr>
						<td>Dirección <input type="text" size="40" name="direccion"></td>
					</tr>
					<tr>
						<td>Contraseña <input type="password" size="40"
							name="contraseña"></td>
					</tr>
					<tr>
						<td><input type="submit" name="enviar" value="Enviar"></td>
						<td><input type="hidden" name="operacion" value="altaSocio"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>