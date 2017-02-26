package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexiones.Conexion;
import entidades.Usuario;

public class DaoGrupo {

	public DaoGrupo() {

	}

	/*************************METODO PARA DAR DE ALTA A UN USUARIO EN UN DETERMINADO GRUPO*****************************/
	public void altaGrupo(Usuario user) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("INSERT INTO GRUPOS(IDGRUPO,IDUSUARIO) VALUES ('sociosbiblioteca',?)");
			ps.setString(1, user.getUsuario());
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
	}//altaGrupo

}//DaoGrupo
