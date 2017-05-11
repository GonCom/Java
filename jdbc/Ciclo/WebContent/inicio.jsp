<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ciclo</title>
</head>
<body>
	<div align="center">
	
	 					<%-- <select>
	 					<c:forEach items="${listadoies}" var="ies">
							<option onclick="/Ciclo/controller?operacion=verOfertaEducativa&idies=${ies.idies}">${ies.nombre}</option>
						</c:forEach>
	 					</select> --%>
	 					
						<c:forEach items="${listadoies}" var="ies">
							<a href="/Ciclo/controller?operacion=verOfertaEducativa&idies=${ies.idies}">${ies.nombre}</a>
						</c:forEach>
	
			
	</div>
	<br>
	<div align="center">
		<c:if test="${sessionScope.listaoferta!=null}">
			<c:choose>
				<c:when test="${!sessionScope.listaoferta.isEmpty()}">
					<div>
						<table>
							
							<tr>
								<th>CICLO</th>
								<th>TURNO</th>
								<th>PLAZAS</th>
								<th></th>
								<th></th>
							</tr>
							<c:forEach items="${listaoferta}" var="ies">
								<tr>
									<td align="center">${ies.nombre}</td>
									<td align="center">${ies.descripcion}</td>
									<td align="center">${ies.plazas}</td>
									<td align="center"><a href="/Ciclo/controller?operacion=editarOfertaEducativa&idciclo=${ies.idciclo}&idturno=${ies.turno}">Editar</a></td>
									<td align="center"><a href="/Ciclo/controller?operacion=eliminarOfertaEducativa&idciclo=${ies.idciclo}&idturno=${ies.turno}">Eliminar</a></td>
								</tr>
							</c:forEach>
							<c:remove scope="session" var="ies" />
						</table>
					</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<c:remove scope="session" var="nombre" />
		</c:if>
		</div>
	



</body>
</html>