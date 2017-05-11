package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Autor;

public class DaoAutor {

	public DaoAutor() {

	}
	
	/*************************METODO PARA LISTAR LOS AUTORES*****************************/
	public ArrayList<Autor> listadoAutores() throws SQLException, Exception {
		ArrayList<Autor> listaautores;
		listaautores = new ArrayList<Autor>();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.createStatement();
			String ordenSQL = "SELECT * FROM AUTOR ORDER BY NOMBRE";
			rs = st.executeQuery(ordenSQL);
			while (rs.next()) {
				Autor miAutor = new Autor();
				miAutor.setIdautor(rs.getInt("IDAUTOR"));
				miAutor.setNombre(rs.getString("NOMBRE"));
				miAutor.setFecha(rs.getDate("FECHANACIMIENTO"));
				listaautores.add(miAutor);
			}
		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}
		System.out.println("El listado de autores se devuelve");
		return listaautores;
	}//listadoAutores

	/*****************************METODO INSERTAR AUTOR ********************************/
	public void insertarAutor(Autor autor) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("INSERT INTO AUTOR (IDAUTOR,NOMBRE,FECHANACIMIENTO) VALUES (S_AUTOR.NEXTVAL,?,?) ");
			Date fecha = new Date(autor.getFecha().getTime());
			ps.setString(1, autor.getNombre());
			ps.setDate(2, fecha);
			ps.executeUpdate();
			ps.close();
			con.close();
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
	}//insertarAutor

}//DaoAutor
