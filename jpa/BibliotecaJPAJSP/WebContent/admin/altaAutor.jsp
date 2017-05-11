<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		<form method="POST" action="../controller">
			<fieldset>
				<legend id="datosAutor">Nuevo Autor</legend>
				<table id="darAlta" align="center"; >
					<tr>
						<td>Nombre <input type="text" name="Nombre" id="Nombre"></td>
					</tr>
					<tr>
						<td>Fecha Nacimiento <input type="text" name="Fecha"
							id="Fecha">(dd-MM-yyyy)
						</td>
					</tr>
					<tr>
						<td><input type="submit" name="Enviar" value="Enviar"></td>
						<td><input name="operacion" value="insertarAutor"
							type="hidden"></td>
					</tr>
					<br>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>