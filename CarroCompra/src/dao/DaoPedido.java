package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexiones.Conexion;
import entidades.Pedido;

public class DaoPedido {

	public DaoPedido() {
		
	}
	/*****************************Método que busca si existe el cliente introducido en el formulario***********************/
	public boolean buscarCliente(int id) throws Exception{
		boolean existe=false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int idcliente;
		String idCliente;
		try{
			Conexion micon=new Conexion();
			con=micon.getConexion();
			
			ps=con.prepareStatement("SELECT ID FROM CLIENTE WHERE ID=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			if(rs.next()){
				idcliente=rs.getInt("ID");
				existe=true;
			}
			
			
		}catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		}
		
		return existe;
		
	}//buscarSocio


	/***********************************Insertar Pedido*******************************************/
	public void insertarPedido(Pedido pedido) throws Exception{
		Connection con=null;
		PreparedStatement ps=null;
		
		try{
			Conexion micon=new Conexion();
			con=micon.getConexion();
			
			ps=con.prepareStatement("INSERT INTO PEDIDO(IDPEDIDO,IDCLIENTE,DIRECCIONDEENVIO) VALUES(?,?,?)");
			ps.setInt(1, pedido.getIdpedido());
			ps.setInt(2,pedido.getIdcliente());
			ps.setString(3,pedido.getDireccionenvio());
			
			ps.execute();
			con.commit();
			
		}catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}//insertarPedido
	

	/********************************Buscar el idpedido*******************************/
	public int buscarIdpedido() throws Exception{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int idpedido = 0;
		try{
			Conexion micon=new Conexion();
			con=micon.getConexion();
			
			ps=con.prepareStatement("SELECT (SEQ_PEDIDO.NEXTVAL)IDPEDIDO FROM DUAL");

			rs=ps.executeQuery();
			if(rs.next()){
				idpedido=rs.getInt("IDPEDIDO");
			}
			
		}catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
		}
		
		return idpedido;
		
	}//buscarPedido
	
}//DaoPedido
