package dao;

import conexiones.Conexion;
import entidades.Prestamo;
import entidades.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoSocio {

	public DaoSocio() {
	}

	/*************************** Listado de socios paginado ***********************************/
	public ArrayList<Socio> listadoSocios(int pag, int numreg)
			throws SQLException, Exception {
		ArrayList<Socio> listasocios;
		listasocios = new ArrayList<Socio>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.prepareStatement("SELECT IDSOCIO, NOMBRE,DIRECCION FROM(SELECT ROWNUM FILA, IDSOCIO, NOMBRE,DIRECCION "
					+ "FROM(SELECT IDSOCIO, NOMBRE,DIRECCION FROM SOCIO  ORDER BY NOMBRE) )"
					+ "WHERE FILA>=? AND FILA<=?");

			st.setInt(1, (pag * numreg) + 1);
			st.setInt(2, (pag * numreg) + numreg);

			rs = st.executeQuery();
			while (rs.next()) {
				Socio miSocio = new Socio();
				miSocio.setIdsocio(rs.getLong("IDSOCIO"));
				miSocio.setNombre(rs.getString("NOMBRE"));
				miSocio.setDireccion(rs.getString("DIRECCION"));
				listasocios.add(miSocio);
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
		System.out.println("El listado de socios se devuelve");
		return listasocios;
	}// fin ListarSocios

	/*************************************** Metodo calcula numero de registros *********************************************/
	public int numRegistros() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int registros;
		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT COUNT(*)  FROM SOCIO");
			rs = ps.executeQuery();
			rs.next();
			registros = rs.getInt(1);
			System.out.println("Registros: " + registros);
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
		return registros;
	}// fin numRegistros
	

	/************************* Metodos para el apartado modificar socio ************************/
	
	/************************** METODO PARA MOSTRAR LOS SOCIOS QUE NOSOSTROS BUSCAMOS*******************************/
	public ArrayList<Socio> listadoBusqueda(String texto) throws Exception {
		ArrayList<Socio> listado = new ArrayList<Socio>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT * FROM SOCIO WHERE NOMBRE LIKE ?");
			ps.setString(1, "%" + texto + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Socio miSocio = new Socio();
				miSocio.setIdsocio(rs.getLong("IDSOCIO"));
				miSocio.setNombre(rs.getString("NOMBRE"));
				miSocio.setDireccion(rs.getString("DIRECCION"));
				listado.add(miSocio);
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
		return listado;
	}//fin listadoBusqueda

	/**********************************METODO PARA EDITAR AL SOCIO SELECCIONADO**************************/
	public Socio editar(long idsocio) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Socio socio = new Socio();

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT * FROM SOCIO WHERE IDSOCIO=?");// consulta
																				// parametrizada
			ps.setLong(1, idsocio);// metodo para pasar el parametro a la
									// consulta anterior
			rs = ps.executeQuery();// ejecuta la consulta

			rs.next();
			socio.setIdsocio(rs.getLong("IDSOCIO"));
			socio.setNombre(rs.getString("NOMBRE"));
			socio.setDireccion(rs.getString("DIRECCION"));

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
		return socio;
	}//fin editar

	/***************************METODO QUE ACTUALIZA AL SOCIO EDITADO***************************/
	public void actualizarSocio(Socio socio) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("UPDATE SOCIO SET NOMBRE=?,DIRECCION=? WHERE IDSOCIO=?");
			ps.setString(1, socio.getNombre());
			ps.setString(2, socio.getDireccion());
			ps.setLong(3, socio.getIdsocio());
			ps.executeUpdate();
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
	}//fin actualizarSocio

	/********************************** Metodo para Nuevo Socio ***********************************/
	public void altaSocio(Socio socio) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();

			ps = con.prepareStatement("INSERT INTO SOCIO (IDSOCIO,NOMBRE,DIRECCION) VALUES(S_SOCIO.NEXTVAL,?,?)");
			ps.setString(1, socio.getNombre());
			ps.setString(2, socio.getDireccion());
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
	}// altaSocio

	/********************************* Listado de socios morosos ***********************************/
	public ArrayList<Socio> listadoMosoros() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Socio> listsocio = new ArrayList<Socio>();

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT DISTINCT (S.IDSOCIO),NOMBRE FROM SOCIO S,PRESTAMO P WHERE S.IDSOCIO = P.IDSOCIO AND TRUNC(P.FECHALIMITEDEVOLUCION)<TRUNC(SYSDATE)");
			rs = ps.executeQuery();
			while (rs.next()) {
				Socio socio = new Socio();
				socio.setIdsocio(rs.getLong("IDSOCIO"));
				socio.setNombre(rs.getString("NOMBRE"));
				listsocio.add(socio);
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
		return listsocio;

	}// fin listadoMorosos

	/***************************METODO QUE NOS DEVUELVE EL NOMBRE DEL SOCIO******************************/
	public String mostrarSocio(long idsocio) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String nombre;
		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT NOMBRE FROM SOCIO WHERE IDSOCIO=?");
			ps.setLong(1, idsocio);
			rs = ps.executeQuery();
			rs.next();
			nombre = rs.getString("NOMBRE");

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
		return nombre;

	}// fin mostrarSocio

}// fin DaoSocio
