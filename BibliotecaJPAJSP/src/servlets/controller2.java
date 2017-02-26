package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import dao.DaoSocio;
import entidades.Socio;
import entidades.Usuario;

/**
 * Servlet implementation class controller2
 */
@WebServlet("/controller2")
public class controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public controller2() {
		super();

	}

	protected void procesarError(HttpServletRequest request,
			HttpServletResponse response, Exception e) throws ServletException,
			IOException {
		String mensaje = e.getMessage();
		request.setAttribute("error", mensaje);
		request.getRequestDispatcher("/admin/error.jsp").forward(request,
				response);
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		HttpSession session = request.getSession(true);

		/************************APARTADO NUEVO SOCIO***************************/
		if (operacion.equals("altaSocio")) {
			DaoSocio daoS = new DaoSocio();
			Socio socio = new Socio();
			String password;

			try {
				socio.setNombre(request.getParameter("usuario"));
				socio.setDireccion(request.getParameter("direccion"));
				password=request.getParameter("contraseña");
				
				daoS.altaSocio(socio,password);

				response.sendRedirect("/BibliotecaJPAJSP/admin/altaCorrecta.jsp");

			} catch (Exception e) {
				procesarError(request, response, e);
			}
		}//altaSocio
		
	}// doGet

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}// doPost

}
