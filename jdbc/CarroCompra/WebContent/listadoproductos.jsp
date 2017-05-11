<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css" href="resources/css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="contenedor">
		<div id="header"></div>
		<c:if test="${!sessionScope.listadoProductos.isEmpty()}">
			<div id="tabla">
				<table width="100%" border="1">
					<caption>Listado Productos</caption>
					<tr>
						<th scope="col"> ID </th>
						<th scope="col">NOMBRE</th>
						<th scope="col">PRECIO NORMAL</th>
						<th scope="col">Añadir</th>
						<th scope="col">Eliminar</th>
					</tr>
					<c:forEach items="${sessionScope.listadoProductos}" var="producto">
					<tr>
						<th>${producto.id}</th>
						<th>${producto.nombre}</th>
						<th>${producto.precio_normal}</th>
						<th>
							<a href="/CarroCompra/controller?operacion=addProducto&id=${producto.id}"><input type="image" border="1" width="50" height="50"  src="resources/img/carro.jpg" /></a>
						</th>
						<th>
							 <a href="/CarroCompra/controller?operacion=quitarProducto&id=${producto.id}"><input type="image" border="1" width="50" height="50"  src="resources/img/quitarCarro.jpg" /></a>
						</th>
					</tr>
					</c:forEach>
				</table> 
			</div>
		</c:if>

		<br><br>
		<c:if test="${!sessionScope.carro.productos.isEmpty()}">
		<div id="tabla">
			<table width="100%" border="1">
			
				<caption>Listado Productos</caption>
				<tr>
					<th scope="col"> ID </th>
					<th scope="col">NOMBRE</th>
					<th scope="col">PRECIO NORMAL</th>
					<th scope="col">CANTIDAD</th>
					<th scope="col">PRECIO</th>
				</tr>
				<c:forEach items="${sessionScope.carro.productos}" var="producto">
						<c:set var="cantidad" value="${producto.cantidad}"></c:set>
						
						<tr>
						<th>${producto.id}</th>
						<th>${producto.nombre}</th>
						<th>${producto.precio_normal}</th>
						<th>${producto.cantidad}</th>
						<th>${producto.precio_normal*cantidad}</th>
							<c:set var="total" value="${total+(producto.precio_normal*cantidad)}"></c:set>
						</tr>
				</c:forEach>
				<tr>
					<th colspan="5" align="right">
						<c:out value="Total: ${total}"></c:out>
					</th>
				</tr>
			</table>
			<h2><a href="/CarroCompra/formalizarpedido.jsp">Formalizar pedido</a></h2>
		</div>
		</c:if>
	</div>
</body>
</html>