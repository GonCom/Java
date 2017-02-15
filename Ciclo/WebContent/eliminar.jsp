<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Ciclo</title>
</head>
<body>

		<div>
			<form action="/Ciclo/controller" method="POST">
					<table>
					<caption>Eliminar Ciclo</caption>
						<tr>
							<td><input type="hidden" name="idciclo"
								value="${oferta.idciclo}" ></td>
						</tr>
						<tr>
							<td><input type="hidden" name="idturno"
								value="${oferta.turno}" 
 ></td>
						</tr>
						<tr>
							<td>Ciclo <input type="text" name="nombre"
								value="${oferta.nombre}" 
>
							<td>
						</tr>
						<tr>
							<td>Turno <input type="text" name=" descripcion"
								value="${oferta.descripcion}">
							<td>
						</tr>
						<tr>
							<td><input type="submit" name="Editar" value="Editar"></td>
							<td><input type="hidden" name="operacion" value="ActualizarEliminado"></td>
						</tr>
					</table>
			</form>
		</div>

</body>
</html>