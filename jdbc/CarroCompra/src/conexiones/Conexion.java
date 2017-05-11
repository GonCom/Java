package conexiones;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class Conexion {
	public Conexion() {
	}

	public Connection getConexion() throws SQLException, Exception {
		//String url = "jdbc:oracle:thin:VS2DAW12/VS2DAW12@10.0.1.12:1521:oradai";
		String url="jdbc:oracle:thin:VS2DAW12/VS2DAW12@80.59.249.199:1521:oradai";
		Connection con; 
		OracleConnectionPoolDataSource ocpds;
		try {
			ocpds = new OracleConnectionPoolDataSource();
			ocpds.setURL(url);
			con = ocpds.getConnection();
			DatabaseMetaData meta = con.getMetaData();
			// gets driver info:
			System.out.println("JDBC driver version is "
					+ meta.getDriverVersion());
			System.out.println("Data Source definido y conexion establecida");
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		}
		return con;
	}
}
