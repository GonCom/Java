package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.DaoIes;
import dao.DaoOfertaeducativa;
import entidades.Ies;
import entidades.Ofertaeducativa;





/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		HttpSession session = request.getSession(true);
		
		DaoIes daoies = null;
		switch (operacion) {
		case "listadoies":
			daoies = new DaoIes();
			ArrayList<Ies> listadoies = null;
			try {
				listadoies = daoies.listadoIes();
				session.setAttribute("listadoies", listadoies);
				response.sendRedirect("inicio.jsp");
			} catch (SQLException e) {
				e.getMessage();
			} catch (Exception ex) {
				ex.getMessage();
			}
			break;
			
		case "verOfertaEducativa":
			ArrayList<Ofertaeducativa> listaoferta = null;
			DaoOfertaeducativa daoofertaeducativa = new DaoOfertaeducativa();
			
			
			try {
				String idies = request.getParameter("idies");
				long id = Integer.parseInt(idies);
				listaoferta = daoofertaeducativa.mostrarOfertaEducativa(id);
				session.setAttribute("listaoferta", listaoferta);
				response.sendRedirect("inicio.jsp");
			} catch (Exception e) {
				e.getMessage();
			}
			break;
		case"editarOfertaEducativa":
			 Ofertaeducativa oferta = new Ofertaeducativa();
			 daoofertaeducativa = new DaoOfertaeducativa();
			try {
				String idciclo = request.getParameter("idciclo");
				long id = Integer.parseInt(idciclo);
				String idturno = request.getParameter("idturno");
				long id2 = Integer.parseInt(idturno);
				System.out.println(id);
				System.out.println(id2);
				oferta = daoofertaeducativa.editar(id,id2);
				session.setAttribute("oferta", oferta);
				response.sendRedirect("editar.jsp");
			
			} catch (Exception e) {
				e.getMessage();
			}
			break;
		case"actualizarOferta":
			 oferta = new Ofertaeducativa();
			 daoofertaeducativa = new DaoOfertaeducativa();
			
			try {
				String id = request.getParameter("idciclo");
				long idciclo = Integer.parseInt(id);
				String id2 = request.getParameter("idturno");
				long idturno = Integer.parseInt(id2);
				String id3 = request.getParameter("plazas");
				long plazas = Integer.parseInt(id3);
				oferta.setIdciclo(idciclo);
				oferta.setTurno(idturno);
				oferta.setPlazas(plazas);
				daoofertaeducativa.actualizarOferta(oferta);
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				e.getMessage();
			}
			break;
		case"eliminarOfertaEducativa":
			 oferta = new Ofertaeducativa();
			 daoofertaeducativa = new DaoOfertaeducativa();
			try {
				String idciclo = request.getParameter("idciclo");
				long id = Integer.parseInt(idciclo);
				String idturno = request.getParameter("idturno");
				long id2 = Integer.parseInt(idturno);
				System.out.println(id);
				System.out.println(id2);
				oferta = daoofertaeducativa.editar(id,id2);
				session.setAttribute("oferta", oferta);
				response.sendRedirect("eliminar.jsp");
			
			} catch (Exception e) {
				e.getMessage();
			}
			break;
		case"ActualizarEliminado":
			 oferta = new Ofertaeducativa();
			 daoofertaeducativa = new DaoOfertaeducativa();
			
			try {
				String id = request.getParameter("idciclo");
				long idciclo = Integer.parseInt(id);
				String id2 = request.getParameter("idturno");
				long idturno = Integer.parseInt(id2);
				oferta.setIdciclo(idciclo);
				oferta.setTurno(idturno);
				daoofertaeducativa.eliminarOferta(oferta);
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				e.getMessage();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
