<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		<form name="form" method="post" action="../controller">
			<table>
			<tr>
			<td>
				<label>ISBN</label>
				<input name="isbn" type="text">
				<input type="submit" name="Eliminar" value="Eliminar" />
				<input name="operacion" value="eliminarLibro" type="hidden"/>
			</td>
			</tr>
			</table>
		</form>
			<c:if test="${sessionScope.existe==false}">
				<p style="color:red;font-size:20px;">
					<c:out value="Error el libro con isbn ${sessionScope.ISBN} no existe"></c:out>
				</p>
			</c:if>
		</div>
	</div>
	<c:remove var="existe" />
	<c:remove var="ISBN" />
</body>
</html>