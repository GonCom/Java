package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.glassfish.jersey.process.internal.RequestScope;

import dao.DaoAutor;
import dao.DaoLibro;
import dao.DaoSocio;
import entidades.Autor;
import entidades.Ejemplar;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;

/**
 * Servlet implementation class Controller
 */
@WebServlet(name = "controller", urlPatterns = { "/controller" })
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
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void procesarError(HttpServletRequest request,
			HttpServletResponse response, Exception e) throws ServletException,
			IOException {
		String mensaje = e.getMessage();
		request.setAttribute("error", mensaje);
		request.getRequestDispatcher("/admin/error.jsp").forward(request,
				response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		HttpSession session = request.getSession(true);

		/***************************** LISTAR SOCIOS PAGINADO ***********************************/
		if (operacion.equals("listarSocios")) {
			List<Socio> listadoSocios = null;
			DaoSocio dao = new DaoSocio();
			String pag, numreg;
			int pagina = 0, numReg = 0;
			long paginamasalta = 0;
			long totalReg = 0;
			int comienzo;
			try {
				totalReg = dao.numRegistros();
				
				numreg = request.getParameter("numreg");
				if(numreg==null){
					numReg=5;
				}else{
					numReg = Integer.parseInt(numreg);
				}

				paginamasalta = (totalReg / numReg);

				if (totalReg % numReg == 0) {
					paginamasalta -= 1;
				} else {
					paginamasalta = (int) Math.floor(totalReg / numReg);

				}

				pag = request.getParameter("pagina");
				if (pag == null) {
					numreg = request.getParameter("numreg");
					if(numreg==null){
						numReg=5;
					}else{
						numReg = Integer.parseInt(numreg);
					}
					
					comienzo=pagina*numReg;
					listadoSocios = dao.listarSocios(comienzo, numReg);
					
					// listadoSocios=dao.listadoSocios();

					session.setAttribute("listadoSocios", listadoSocios);
					session.setAttribute("totalReg", totalReg);
					session.setAttribute("paginamasalta", paginamasalta);
					session.setAttribute("numReg", numReg);
					response.sendRedirect("/BibliotecaJPAJSP/admin/listadosociosJSTL.jsp");
					
				} else {
					pag = request.getParameter("pagina");
					numreg = request.getParameter("numreg");

					pagina = Integer.parseInt(pag);
					numReg = Integer.parseInt(numreg);
					comienzo=pagina*numReg;
					listadoSocios = dao.listarSocios(comienzo, numReg);
					// listadoSocios=dao.listadoSocios();

					session.setAttribute("paginaactual", pagina);
					session.setAttribute("listadoSocios", listadoSocios);
					session.setAttribute("totalReg", totalReg);
					session.setAttribute("paginamasalta", paginamasalta);
					session.setAttribute("numReg", numReg);
					response.sendRedirect("/BibliotecaJPAJSP/admin/listadosociosJSTL.jsp");

				}

			} catch (Exception e) {
				procesarError(request, response, e);
			}
		}// if-listarSocios

		/***************************** LISTAR AUTORES ****************************/
		if (operacion.equals("listarAutores")) {
			List<Autor> listadoAutores = null;
			DaoAutor dao = new DaoAutor();
			try {
				listadoAutores = dao.getAllAutores();
				session.setAttribute("listadoAutores", listadoAutores);
				/*request.getRequestDispatcher("/admin/listadoautoresJSTL.jsp")
						.forward(request, response); */
				response.sendRedirect("/BibliotecaJPAJSP/admin/listadoautoresJSTL.jsp");
			} catch (Exception e) {
				procesarError(request, response, e);
			}

		}// if-listarAutores  
		
		if (operacion.equals("insertarAutor")) {
			DaoAutor dao = new DaoAutor();
			Autor autor = new Autor();
			Date formatoFecha = null;
			String fecha = request.getParameter("Fecha");
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			formato.setLenient(false);
			try {
				formatoFecha = formato.parse(fecha);
				autor.setNombre(request.getParameter("Nombre"));
				autor.setFechanacimiento(formatoFecha);
				dao.crearAutor(autor);
				/*request.getRequestDispatcher("/admin/altaCorrecta.jsp")
						.forward(request, response);*/
				response.sendRedirect("/BibliotecaJPAJSP/admin/altaCorrecta.jsp");
			} catch (ParseException pe) {
				procesarError(request, response, pe);
			} catch (Exception e) {
				procesarError(request, response, e);
			}
		}// if-insertarAutor

		/**************************** MODIFICAR SOCIO ***************************/
		if (operacion.equals("buscarSocio")) {
			List<Socio> listado = null;
			DaoSocio dao = new DaoSocio();
			String texto = null;
			try {
				texto = request.getParameter("buscador");
				listado = dao.sociosPorNombre(texto);
				session.setAttribute("listado", listado);
				session.setAttribute("buscador", texto);
				response.sendRedirect("/BibliotecaJPAJSP/admin/modificarsocio.jsp");
				// request.getRequestDispatcher("/admin/modificarsocio.jsp").forward(request,response);
			} catch (Exception e) {
				procesarError(request, response, e);
			}
		}// if-buscarSocio
		
		if (operacion.equals("editarSocio")) {
			Socio socio = new Socio();
			DaoSocio dao = new DaoSocio();
			long id;
			try {
				String idsocio = request.getParameter("id");
				id = Integer.parseInt(idsocio);
				socio = dao.buscarSocio(id);
				session.setAttribute("socio", socio);
				response.sendRedirect("/BibliotecaJPAJSP/admin/editarsocio.jsp");
				// request.getRequestDispatcher("/admin/editarsocio.jsp").forward(request,response);

			} catch (Exception e) {
				procesarError(request, response, e);
			}
		}// if-editarSocio
		
		if (operacion.equals("actualizarSocio")) {
			DaoSocio dao = new DaoSocio();
			Socio socio = new Socio();
			long idsocio;
			String nombre, direccion, id;
			try {
				id = request.getParameter("idsocio");
				idsocio = Integer.parseInt(id);
				nombre = request.getParameter("nombre");
				direccion = request.getParameter("direccion");
				socio.setIdsocio(idsocio);
				socio.setNombre(nombre);
				socio.setDireccion(direccion);
				dao.actualizarSocio(socio);
				response.sendRedirect("/BibliotecaJPAJSP/admin/altaCorrecta.jsp");
				// request.getRequestDispatcher("/admin/altaCorrecta.jsp").forward(request,
				// response);;
			} catch (Exception e) {
				procesarError(request, response, e);
			}
		}// if-actualizarSocio
			// ****************FIN MODIFICAR SOCIO************************

		/************************* Listado de Morosos ****************************/
		/*if (operacion.equals("listarMorosos")) {
			DaoSocio dao = new DaoSocio();
			ArrayList<Socio> morosos = null;
			try {
				morosos = dao.listadoMosoros();

				session.setAttribute("morosos", morosos);
				response.sendRedirect("/BibliotecaJPAJSP/admin/listadomorosos.jsp");
			} catch (SQLException e) {
				procesarError(request, response, e);
			} catch (Exception e) {
				procesarError(request, response, e);
			}

		}// if-listarMorosos
		if (operacion.equals("verlibros")) {
			ArrayList<Prestamo> listalibros = null;
			DaoPrestamo daoP = new DaoPrestamo();
			DaoSocio daoS = new DaoSocio();
			String idsocio, nombre;
			long id;
			try {
				idsocio = request.getParameter("idsocio");
				id = Integer.parseInt(idsocio);

				listalibros = daoP.mostrarLibros(id);
				nombre = daoS.mostrarSocio(id);

				session.setAttribute("nombre", nombre);
				session.setAttribute("listalibros", listalibros);
				response.sendRedirect("/BibliotecaJPAJSP/admin/listadomorosos.jsp");
			} catch (Exception e) {
				procesarError(request, response, e);
			}
		}// if-verlibros*/
		
		/*******************************LISTADO DE LIBROS, CONSULTA DEL USUARIO*************************/
		if(operacion.equals("listarLibros")){
			DaoLibro dao=new DaoLibro();
			List<Object> libros;
			String opcion;
			String autor = null,titulo = null,isbn = null;
			try{
				opcion=request.getParameter("tipo");
				String valor=request.getParameter("valor");

				if(opcion.equals("autor")){
					autor=request.getParameter("buscador");
					titulo="%";
					isbn="%";
					session.setAttribute("buscador", autor);
				}
				if(opcion.equals("titulo")){
					autor="%";
					titulo=request.getParameter("buscador");
					isbn="%";
					session.setAttribute("buscador", titulo);
				}
				if(opcion.equals("isbn")){
					autor="%";
					titulo="%";
					isbn=request.getParameter("buscador");
					session.setAttribute("buscador", isbn);
				}
				//System.out.println("AUTOR: " + autor + " TITULO: " + titulo + " ISBN: " + isbn);

				libros=dao.listadoLibros(autor, titulo, isbn);
				session.setAttribute("opcion", request.getParameter("tipo"));
				session.setAttribute("libros", libros);
				if(valor.equals("listar")){
					response.sendRedirect("/BibliotecaJPAJSP/socios/listadolibros.jsp");
				}else{
					response.sendRedirect("/BibliotecaJPAJSP/admin/eliminarejemplar.jsp");
				}
				
			}catch (Exception e) {
				procesarError(request, response, e);
			}
			
		}//OPERACION LISTADO DE LIBROS
		
		
		if(operacion.equals("eliminarLibro")){
			DaoLibro dao=new DaoLibro();
			String isbn;
			boolean existe;
			try{
				
				isbn=request.getParameter("isbn");
				existe=dao.buscarLibro(isbn);
				
				if(existe==true){
					dao.eliminarLibros(isbn);
					response.sendRedirect("/BibliotecaJPAJSP/admin/altaCorrecta.jsp");
				}else{
					session.setAttribute("ISBN", isbn);
					session.setAttribute("existe", existe);
					response.sendRedirect("/BibliotecaJPAJSP/admin/eliminarlibros.jsp");
				}
				
				
			}catch(Exception e){
				procesarError(request, response, e);
			}
		}
		
		
		/**************************MOSTRAR EJEMPLARES********************************/
		/*if(operacion.equals("mostrarEjemplares")){
			DaoEjemplar dao=new DaoEjemplar();
			ArrayList<Ejemplar> ejemplares=new ArrayList<Ejemplar>();
			String isbn,titulo;
			try{
				isbn=request.getParameter("isbn");
				titulo=request.getParameter("titulo");
				ejemplares=dao.listadoEjemplares(isbn);
				
				session.setAttribute("ejemplares", ejemplares);
				session.setAttribute("titulo", titulo);
				response.sendRedirect("/BibliotecaJPAJSP/admin/eliminarejemplar.jsp");
				
			}catch (Exception e) {
				procesarError(request, response, e);
			}
		}//MOSTRAR EJEMPLARES*/
		
		
		/*************************ELIMINAR EJEMPLARES SELECCIONADOS**********************************/
		/*if(operacion.equals("eliminarEjemplar")){
			DaoEjemplar dao=new DaoEjemplar();
			String[] ejemplares=null;
			
			try{
				ejemplares = request.getParameterValues("ejemplaresEliminar");
				for(int i=0;i<ejemplares.length;i++){
					System.out.println(ejemplares[i]);
				}
				dao.borrarEjemplar(ejemplares);//borrado logico
				//dao.borrarEjemplar_2(ejemplares);//borrado fisico
				response.sendRedirect("/BibliotecaJPAJSP/admin/altaCorrecta.jsp");
				
			}catch (Exception e) {
				procesarError(request, response, e);
			}
		}//eliminarEjemplar*/
		
		
		/*************************************Nuevo Prestamo************************************/
		/*if(operacion.equals("nuevoPrestamo")){
			DaoPrestamo dao=new DaoPrestamo();
			Prestamo prestamo=new Prestamo();
			
			try{
				prestamo.setIdsocio(Integer.parseInt(request.getParameter("socio")));
				prestamo.setIdejemplar(Integer.parseInt(request.getParameter("ejemplar")));
				
				dao.nuevoPrestamo(prestamo);
				response.sendRedirect("/BibliotecaJPAJSP/admin/altaCorrecta.jsp");
				
			}catch (Exception e) {
				procesarError(request, response, e);
			}
		}//nuevoPrestamo*/
		
		
		/*********************************Devolver Ejemplar**********************************/
		/*if(operacion.equals("devolverEjemplar")){
			DaoEjemplar dao=new DaoEjemplar();
			int idejemplar=0;
			
			try{
				idejemplar=Integer.parseInt(request.getParameter("ejemplar"));
				dao.devolucion(idejemplar);
				response.sendRedirect("/BibliotecaJPAJSP/admin/altaCorrecta.jsp");
			}catch (Exception e) {
				procesarError(request, response, e);
			}
		}//devolverEjemplar*/
		
	}// doGet

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}// doPost
}