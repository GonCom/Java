package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Usuario;

public class DaoUsuario {

	public DaoUsuario() {

	}

	/******************************METODO PARA DAR DE ALTA A UN NUEVO USUARIO**************************/
	public void altaUsuario(Usuario user) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();

			ps = con.prepareStatement("INSERT INTO USUARIOS (USUARIO,CLAVE) VALUES(?,MD5HASH(?))");
			ps.setString(1, user.getUsuario());
			ps.setString(2, user.getContraseña());
			ps.execute();
			con.commit();

		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}

	}// altaUsuario

}// DaoUsuario
