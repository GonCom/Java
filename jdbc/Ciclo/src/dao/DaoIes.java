package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Ies;


public class DaoIes {

	public DaoIes() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Ies> listadoIes() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Ies> listadoies = new ArrayList<Ies>();

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT * FROM IES");
			rs = ps.executeQuery();
			while (rs.next()) {
				Ies ies = new Ies();
				ies.setIdies(rs.getLong("IDIES"));
				ies.setNombre(rs.getString("NOMBRE"));
				listadoies.add(ies);
			}

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
		return listadoies;
	}
}
