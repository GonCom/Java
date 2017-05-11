package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Prestamo;

public class DaoPrestamo {

	public DaoPrestamo() {
		// TODO Auto-generated constructor stub
	}

	/***************************************************************/
	/*
	 * Consulta para sacar los libros de la tabla prestamo SELECT TITULO FROM
	 * LIBRO L,EJEMPLAR E,PRESTAMO P WHERE P.IDEJEMPLAR=E.IDEJEMPLAR AND
	 * E.ISBN=L.ISBN AND P.IDSOCIO=3 AND SYSDATE>FECHALIMITEDEVOLUCION
	 */
	/**
	 * @throws Exception
	 **************************************************************/

	/*************************** METODO MOSTRAR LIBROS *********************************/
	public ArrayList<Prestamo> mostrarLibros(long idsocio) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Prestamo> libros = new ArrayList<Prestamo>();
		long id = idsocio;
		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT L.TITULO,TRUNC(SYSDATE-FECHALIMITEDEVOLUCION)DIAS FROM LIBRO L,EJEMPLAR E,PRESTAMO P WHERE P.IDEJEMPLAR=E.IDEJEMPLAR  AND E.ISBN=L.ISBN AND P.IDSOCIO=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Prestamo pre = new Prestamo();
				pre.setTitulo(rs.getString("TITULO"));
				pre.setDiasdemora(rs.getInt("DIAS"));
				libros.add(pre);
			}
		} catch (SQLException se) {
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
		return libros;
	}// fin mostrarLibros

	/************************* METODO PARA DAR DE ALTA A UN NUEVO PRESTAMO ******************************/
	public void nuevoPrestamo(Prestamo prestamo) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("INSERT INTO PRESTAMO(IDEJEMPLAR,IDSOCIO,FECHAPRESTAMO) VALUES(?,?,SYSDATE)");
			ps.setInt(1, prestamo.getIdejemplar());
			ps.setInt(2, prestamo.getIdsocio());
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
	}// nuevoPrestamo

}// DaoPrestamo
