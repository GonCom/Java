package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.DetallePedido;
import entidades.Producto;

public class DaoDetallePedido {

	public DaoDetallePedido() {
		
	}

	/******************************Insertar DetallePedido********************************/
	public void insertarDetallePedido(DetallePedido detPedido)  throws Exception{
		Connection con=null;
		PreparedStatement ps=null;
		
		try{
			Conexion micon=new Conexion();
			con=micon.getConexion();
			
			ps=con.prepareStatement("INSERT INTO DETALLEPEDIDO VALUES(?,?,?,?,?,?)");
			ps.setInt(1, detPedido.getIdpedido());
			ps.setInt(2, detPedido.getLineadetalle());
			ps.setInt(3, detPedido.getIdproducto());
			ps.setInt(4, detPedido.getCantidad());
			ps.setInt(5,detPedido.getPreciounidad());
			ps.setInt(6,(detPedido.getPreciounidad()*detPedido.getCantidad()));
			
			ps.execute();
			con.commit();
			
		}catch(SQLException se){
			throw se;
		}catch(Exception e){
			throw e;
		}finally{
			if(ps!=null)
				ps.close();
			if(con!=null)
				con.close();
		}		
	}//insertarDetallePedido
	
}//DaoDetallePedido
