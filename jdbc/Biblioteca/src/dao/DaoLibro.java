package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Libro;

public class DaoLibro {

	public DaoLibro() {

	}

	/*****************************METODO PARA LISTAR LOS LIBROS*******************************/
	public ArrayList<Libro> listadoLibros(String autor, String titulo,
			String isbn) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Libro> listLibros = new ArrayList<Libro>();

		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT LI.ISBN,TRANSLATE(UPPER(NOMBRE),'Á,É,Í,Ó,Ú','A,E,I,O,U')NOMBRE,TITULO,EJEMPLARESTOTALES,NVL(B.EJEMPLARESPRESTADOS,0)EJEMPLARESPRESTADOS,(EJEMPLARESTOTALES-NVL(B.EJEMPLARESPRESTADOS,0))EJEMPLARESDISPONIBLES"
					+ " FROM LIBRO LI,AUTOR AU,(SELECT L.ISBN, COUNT(*)EJEMPLARESTOTALES"
					+ " FROM EJEMPLAR E,LIBRO L"
					+ " WHERE E.ISBN=L.ISBN"
					+ " GROUP BY L.ISBN)A,"
					+ " (SELECT L.ISBN, COUNT(*)EJEMPLARESPRESTADOS"
					+ " FROM EJEMPLAR E,LIBRO L,PRESTAMO P"
					+ " WHERE E.ISBN=L.ISBN"
					+ " AND P.IDEJEMPLAR=E.IDEJEMPLAR"
					+ " GROUP BY L.ISBN)B"
					+ " WHERE  A.ISBN=B.ISBN(+)"
					+ " AND LI.ISBN=A.ISBN"
					+ " AND AU.IDAUTOR=LI.IDAUTOR"
					+ " AND TRANSLATE(UPPER(NOMBRE),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U')"
					+ " AND TRANSLATE(UPPER(TITULO),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U')"
					+ " AND LI.ISBN LIKE ? " + " ORDER BY LI.ISBN");
			ps.setString(1, "%" + autor + "%");
			ps.setString(2, "%" + titulo + "%");
			ps.setString(3, isbn);
			rs = ps.executeQuery();

			while (rs.next()) {
				Libro libro = new Libro();
				libro.setIsbn(rs.getString("ISBN"));
				libro.setAutor(rs.getString("NOMBRE"));
				libro.setTitulo(rs.getString("TITULO"));
				libro.setEjemplarestotales(rs.getString("EJEMPLARESTOTALES"));
				libro.setEjemplaresprestados(rs
						.getString("EJEMPLARESPRESTADOS"));
				libro.setEjemplaresdisponibles(rs
						.getString("EJEMPLARESDISPONIBLES"));
				listLibros.add(libro);
			}

		} catch (SQLException se) {
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
		return listLibros;

	}// fin listadoLibros

	
}//DaoLibro
