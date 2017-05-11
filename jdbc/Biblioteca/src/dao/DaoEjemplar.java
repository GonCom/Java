package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Ejemplar;

public class DaoEjemplar {

	public DaoEjemplar() {
		// TODO Auto-generated constructor stub
	}

	/************************METODO LISTAR EJEMPLARES DE UN LIBRO*************************/
	public ArrayList<Ejemplar> listadoEjemplares(String isbn) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT DISTINCT(E.IDEJEMPLAR)"
					+ " FROM LIBRO L,EJEMPLAR E,PRESTAMO P"
					+ " WHERE L.ISBN=E.ISBN"
					+ " AND E.IDEJEMPLAR NOT IN(SELECT P.IDEJEMPLAR"
					+ " FROM PRESTAMO P,  EJEMPLAR E"
					+ " WHERE P.IDEJEMPLAR=E.IDEJEMPLAR)" + " AND L.ISBN=?"
					+ " AND E.ESTADO='DISPONIBLE'");
			ps.setString(1, isbn);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ejemplar ejem = new Ejemplar();
				ejem.setIdejemplar(rs.getInt("IDEJEMPLAR"));
				ejemplares.add(ejem);
			}
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
		return ejemplares;
	}// listadoEjemplares

	/*
	 * Este metodo no borra los ejemplares, solo cambia la columna estado de la
	 * tabla ejemplar de 'DISPONIBLE' a 'BAJA'
	 */
	public void borrarEjemplar(String[] ejemplar) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			for (int i = 0; i < ejemplar.length; i++) {
				ps = con.prepareStatement("UPDATE EJEMPLAR SET ESTADO='BAJA' WHERE IDEJEMPLAR=?");
				ps.setInt(1, Integer.parseInt(ejemplar[i]));
				ps.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}// borrarEjemplar

	
	/* Este metodo si borra los ejemplares de la tabla ejemplar */
	public void borrarEjemplar_2(String[] ejemplar) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			for (int i = 0; i < ejemplar.length; i++) {
				ps = con.prepareStatement("DELETE FROM EJEMPLAR WHERE IDEJEMPLAR=?");
				ps.setInt(1, Integer.parseInt(ejemplar[i]));
				ps.execute();
			}
			con.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}// borrarEjemplar_2

	/****************************** Metodo devolucion de un ejemplar ****************************************/
	public void devolucion(int idejemplar) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("DELETE FROM PRESTAMO WHERE IDEJEMPLAR=?");
			ps.setInt(1, idejemplar);
			ps.execute();
			con.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}// metodo devolucion

}//DaoEjemplar
