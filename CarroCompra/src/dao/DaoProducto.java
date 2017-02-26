package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Producto;
import entidades.ProductoCarro;

public class DaoProducto {

	public DaoProducto() {
		
	}
	
	public ArrayList<Producto> listadoProductos() throws Exception{
		ArrayList<Producto> productos=new ArrayList<Producto>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			Conexion micon=new Conexion();
			con=micon.getConexion();
			
			ps=con.prepareStatement("SELECT ID,NOMBRE,PRECIO_NORMAL FROM PRODUCTO");
			rs=ps.executeQuery();
			
			while(rs.next()){
				Producto p=new Producto();
				p.setId(rs.getInt("ID"));
				p.setNombre(rs.getString("NOMBRE"));
				p.setPrecio_normal(rs.getInt("PRECIO_NORMAL"));
				productos.add(p);
			}
		}catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		
		return productos;
		
	}//listadoProductos
	
	
	public Producto buscarProducto(int id) throws Exception{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Producto pc=new Producto();
		
		try{
			Conexion micon=new Conexion();
			con=micon.getConexion();
			ps=con.prepareStatement("SELECT ID,NOMBRE,PRECIO_NORMAL FROM PRODUCTO WHERE ID=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			rs.next();
			pc.setId(rs.getInt("ID"));
			pc.setNombre(rs.getString("NOMBRE"));
			pc.setPrecio_normal(rs.getInt("PRECIO_NORMAL"));
			pc.setCantidad(1);
			
		}catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return pc;
	}//mostrarProducto
	
}//listadoProductos
