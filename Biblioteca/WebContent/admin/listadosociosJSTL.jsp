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
		<c:if test="${!sessionScope.listadoSocios.isEmpty()}">
			<div id="tabla">
				<table width="100%" border="1">
					<caption>Listado de Socios</caption>
					<tr>
						<th scope="col">IDSOCIO</th>
						<th scope="col">NOMBRE</th>
						<th scope="col">DIRECCION</th>
					</tr>
					<c:forEach items="${sessionScope.listadoSocios}" var="socio">
						<tr>
							<td>${socio.idsocio}</td>
							<td>${socio.nombre}</td>
							<td>${socio.direccion}</td>
						</tr>
					</c:forEach>
					<c:remove scope="session" var="listadoSocios" />
				</table>
			</div>
		</c:if>

		<div id="paginacion">
			<c:set var="totalReg" value="${sessionScope.totalReg}"></c:set>
			<c:set var="paginaactual" value="${paginaactual}"></c:set>
			<c:set var="nReg" value="${numReg}"></c:set>
			<c:set var="paginamasalta" value="${paginamasalta}"></c:set>
			<c:out value="Total registros: ${totalReg}"></c:out>
			<c:out value="Desde el: ${(paginaactual*nReg)+1} "></c:out>
			<c:out
				value="Hasta el: ${((paginaactual*nReg)+nReg)>totalReg?totalReg:((paginaactual*nReg)+nReg)} "></c:out>


			<a
				href="/Biblioteca/controller?operacion=listarSocios&pagina=${paginaactual+1>paginamasalta?0:paginaactual+1}&numreg=${nReg}">Siguiente</a>
			<a
				href="/Biblioteca/controller?operacion=listarSocios&pagina=${paginaactual-1>=0?paginaactual-1:paginamasalta}&numreg=${nReg}">Anterior</a>
		</div>
	</div>
</body>
</html>