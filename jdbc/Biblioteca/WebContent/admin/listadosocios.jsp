<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:directive.page
	import="java.util.ArrayList,entidades.Socio,java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="Stylesheet" type="text/css" href="resources/css/estilo.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Socios</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:include flush="true" page="../WEB-INF/menu.jspf"></jsp:include>
		</div>
		<%
     @SuppressWarnings("unchecked")
     ArrayList<Socio>listadoSocios=(ArrayList<Socio>)request.getAttribute("listadoSocios");

     if(listadoSocios!=null){
 %>
		<div id="tabla">
			<table width="100%" border="1">
				<caption>Listado de Socios</caption>
				<tr>
					<th scope="col">IDSOCIO</th>
					<th scope="col">NOMBRE</th>
					<th scope="col">DIRECCION</th>
				</tr>
				<%
	for(Socio s:listadoSocios){   
 %>
				<tr>
					<td><%=s.getIdsocio() %></td>
					<td><%=s.getNombre() %></td>
					<td><%=s.getDireccion() %></td>
				</tr>

				<%
     }
   %>
			</table>
		</div>
	</div>
	<%
    }
   %>
</body>
</html>