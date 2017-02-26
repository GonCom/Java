<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css" href="resources/css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function obligatorio() {
		if (document.form.id.value == "" || document.form.id.value.length == 0
				|| /^\s+$/.test(document.form.id.value)
				|| document.form.direccion.value == ""
				|| document.form.direccion.value.length == 0
				|| /^\s+$/.test(document.form.direccion.value)) {
			alert("Debes rellenar todos los campos");
			return false;
		} else {
			return true;
		}
	}

	function solonumeros(event) {//solo se pueden introducir numeros y la tecla de borrar
		var num = event.keyCode;
		if (!(num >= 96 && num == 105 || num >= 48 && num <= 57 || num==8)) {
			return false;
		} else {
			return true;
		}
	}
</script> 
</head>
<body>
	<div id="contenedor">
		<div id="header"></div>
		<div id="Buscador">
				<form id="formulario" action="/CarroCompra/controller" method="post"
				name="form" onsubmit="return obligatorio();">
				<fieldset>
					<legend>Confirmar pedido</legend>
					<table>
						<tr>
							<td><label>*ID</label> <input type="text" name="id" onkeydown="return solonumeros(event);"/></td>
						</tr>
						<tr>
							<td><label>*Direccion de envío</label> <input type="text"
								name="direccion" /></td>
						</tr>
						<tr>
							<td><input type="submit" name="confirmar" value="Confirmar" />
								<input type="hidden" name="operacion" value="formalizarPedido" />
							</td>
						</tr >
						<c:if test="${sessionScope.existe==false}">
							<tr>
								<td><c:out value="Error: IDCLIENTE=${sessionScope.idcliente} no existe"></c:out></td>
							</tr>
						</c:if>
						<c:remove scope="session" var="existe" />
						<c:remove scope="session" var="idcliente" />
					</table>
				</fieldset>

			</form>
		</div>
	</div>
</body>
</html>