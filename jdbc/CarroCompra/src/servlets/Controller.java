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

import carrrocompra.Carro;
import dao.DaoDetallePedido;
import dao.DaoPedido;
import dao.DaoProducto;
import entidades.DetallePedido;
import entidades.Pedido;
import entidades.Producto;
import entidades.ProductoCarro;


@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Controller() {
        super(); 
    }

    protected void procesarError(HttpServletRequest request,HttpServletResponse response, Exception e) throws ServletException,
			IOException {
		String mensaje = e.getMessage();
		request.setAttribute("error", mensaje);
		request.getRequestDispatcher("error.jsp").forward(request,
				response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		HttpSession session = request.getSession(true);
		Carro micarro=null;
		
		/***************************************Comprar***************************************/
		if(operacion.equals("comprar")){
			DaoProducto dao=new DaoProducto();
			ArrayList<Producto> listadoProductos=new ArrayList<Producto>();
			
			micarro=(Carro) session.getAttribute("carro");
			if(micarro==null){
				micarro=new Carro();
				session.setAttribute("carro", micarro);
			}
			
			try{
				listadoProductos=dao.listadoProductos();
				
				session.setAttribute("listadoProductos", listadoProductos);
				response.sendRedirect("/CarroCompra/listadoproductos.jsp");
				
			}catch (Exception e) {
				procesarError(request, response, e);
			}
		}//if-comprar
		
		/***********************************Añadir producto***********************************/
		if(operacion.equals("addProducto")){
			int id;
			DaoProducto dao=new DaoProducto();
			Producto pc=new Producto();
			micarro=(Carro) session.getAttribute("carro");
			try{
				id=Integer.parseInt(request.getParameter("id"));
				pc=dao.buscarProducto(id);
				
				micarro.addProducto(pc);
				
				session.setAttribute("carro", micarro);
				response.sendRedirect("/CarroCompra/listadoproductos.jsp");
			}catch (Exception e) {
				procesarError(request, response, e);
			} 
			
		}//if-addProducto
		
		if(operacion.equals("quitarProducto")){
			int id;
			DaoProducto dao=new DaoProducto();
			Producto pc=new Producto();
			micarro=(Carro) session.getAttribute("carro");
			try{
				id=Integer.parseInt(request.getParameter("id"));
				pc=dao.buscarProducto(id);
				
				micarro.quitarProducto(pc);
				
				session.setAttribute("carro", micarro);
				response.sendRedirect("/CarroCompra/listadoproductos.jsp");
			}catch (Exception e) {
				procesarError(request, response, e);
			} 
			
		}//if-quitarProducto
		
		/*******************************Formalizar Pedido***********************************/
		if(operacion.equals("formalizarPedido")){
			micarro=(Carro) session.getAttribute("carro");
			int idcliente,idpedido;
			String direccion;
			boolean existe;
			DaoPedido daoP=new DaoPedido();
			DaoDetallePedido daoDP=new DaoDetallePedido(); 
			ArrayList<Producto> productos=micarro.getProductos();
			Pedido pedido=new Pedido();
			DetallePedido detalle=null;
			try{
				idcliente=Integer.parseInt(request.getParameter("id"));
				existe=daoP.buscarCliente(idcliente);
				
				if(existe==true){
					direccion=request.getParameter("direccion");
					idpedido=daoP.buscarIdpedido();
					pedido.setIdpedido(idpedido);
					pedido.setIdcliente(idcliente);
					pedido.setDireccionenvio(direccion);
					daoP.insertarPedido(pedido);
					 
					for (int i=0;i<productos.size();i++){
						Producto prod=new Producto();
						prod=productos.get(i);
						detalle=new DetallePedido(idpedido,i+1,prod.getId(),prod.getCantidad(),prod.getPrecio_normal(),(prod.getCantidad()*prod.getPrecio_normal()));
						daoDP.insertarDetallePedido(detalle);
					}
					response.sendRedirect("/CarroCompra/altaCorrecta.jsp");
					
				}else{
					session.setAttribute("idcliente", idcliente);
					session.setAttribute("existe", existe);
					response.sendRedirect("/CarroCompra/formalizarpedido.jsp");
				}

			}catch (SQLException se) {
				procesarError(request,response,se);
				
			}catch(Exception e){
				procesarError(request,response,e);
			}
		}//if-formalizarPedido
		
	}//doGet
	 
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}//doPost

}//Controller
