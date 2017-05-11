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
		<div id="Buscador">
		<form action="../controller" method="post">
			<fieldset>
			<legend>Devolución</legend>
			<table>
				<tr>
					<td>
						<label>ID Ejemplar</label>
						<input type="text" name="ejemplar"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" name="devolver" value="Devolver"/>
						<input type="hidden" name="operacion" value="devolverEjemplar"/>
					</td>
				</tr>
			</table>
			</fieldset>
		</form>
		</div>
	</div>
</body>
</html>