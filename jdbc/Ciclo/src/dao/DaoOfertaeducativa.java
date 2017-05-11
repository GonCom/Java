package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Ofertaeducativa;


public class DaoOfertaeducativa {

	public DaoOfertaeducativa() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Ofertaeducativa> mostrarOfertaEducativa(long idies) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Ofertaeducativa> oferta = new ArrayList<Ofertaeducativa>();
		long id = idies;
		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT * FROM OFERTAEDUCATIVA OE, CICLO CI, IES IE, TURNO TU "
					+ "WHERE OE.IDIES = IE.IDIES "
					+ "AND OE.IDCICLO = CI.IDCICLO "
					+ "AND OE.TURNO = TU.IDTURNO "
					+ "AND IE.IDIES =?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ofertaeducativa ofer = new Ofertaeducativa();
				ofer.setIdciclo(rs.getLong("IDCICLO"));
				ofer.setIdies(rs.getLong("IDIES"));
				ofer.setPlazas(rs.getLong("PLAZAS"));
				ofer.setTurno(rs.getLong("TURNO"));
				ofer.setNombre(rs.getString("NOMBRE"));
				ofer.setDescripcion(rs.getString("DESCRIPCION"));
				oferta.add(ofer);
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
		return oferta;
	}
	public Ofertaeducativa editar(long idciclo, long turno) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Ofertaeducativa ofertaeducativa = new Ofertaeducativa();
		System.out.println(idciclo);
		System.out.println(turno);
		try {
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("SELECT * FROM OFERTAEDUCATIVA OE, CICLO CI, IES IE, TURNO TU "
					+ "WHERE OE.IDIES = IE.IDIES "
					+ "AND OE.IDCICLO = CI.IDCICLO "
					+ "AND OE.TURNO = TU.IDTURNO "
					+ "AND CI.IDCICLO =?"
					+ "AND OE.TURNO=?");
			ps.setLong(1, idciclo);
			ps.setLong(2,turno);
			System.out.println(ps);
			rs = ps.executeQuery();
			rs.next();
			ofertaeducativa.setIdciclo(rs.getLong("IDCICLO"));
			ofertaeducativa.setDescripcion(rs.getString("DESCRIPCION"));
			ofertaeducativa.setPlazas(rs.getLong("PLAZAS"));
			ofertaeducativa.setNombre(rs.getString("NOMBRE"));
			ofertaeducativa.setTurno(rs.getLong("TURNO"));
			

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
		return ofertaeducativa;
	}
	public void actualizarOferta(Ofertaeducativa oferta) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		System.out.println("llega");
		try {
			System.out.println("llega2");
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("UPDATE OFERTAEDUCATIVA SET PLAZAS=? WHERE IDCICLO=? AND TURNO=?");
			ps.setLong(1, oferta.getPlazas());
			ps.setLong(2, oferta.getIdciclo());
			ps.setLong(3, oferta.getTurno());
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
	}
	public void eliminarOferta(Ofertaeducativa oferta) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		System.out.println("llega");
		try {
			System.out.println("llega2");
			Conexion micon = new Conexion();
			con = micon.getConexion();
			ps = con.prepareStatement("DELETE FROM OFERTAEDUCATIVA WHERE IDCICLO =? AND TURNO=?");
			ps.setLong(1, oferta.getIdciclo());
			ps.setLong(2, oferta.getTurno());
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
	}

}

