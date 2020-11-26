package com.bcp.automatizacion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionBD {

	// singleton
	private static ConexionBD obj = null;

	private ConexionBD() {
	}

	public static ConexionBD getInstancia() {
		instanciar();
		return obj;
	}

	private synchronized static void instanciar() {
		if (obj == null) {
			obj = new ConexionBD();
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	// singleton

	public Connection conexionOracle(String aplicativo) {
		Connection conexion = null;
		String url = "";
		String user = "";
		String pass = "";

		/*switch (aplicativo) {
		case Constantes.sas:
			url = "jdbc:oracle:thin:@//" + Constantes.sasBdServer + ":" + Constantes.sasBdPuerto + "/"
					+ Constantes.sasBdSid;
			user = Constantes.sasBdUser1;
			pass = Constantes.sasBdPass1;
			break;
		
		default:
			break;
		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println(url + "\t\t" + user + "\t\t" + pass);
			conexion = DriverManager.getConnection(url, user, pass);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}*/

		return conexion;
	}

	public Connection conexionMysql(String aplicativo) {
		return null;
	}

	public void cerrarConnection(Connection connection) throws Exception {
		if (connection != null) {
			connection.close();
		}
	}

	public void cerrarStatement(Statement statement) throws Exception {
		if (statement != null) {
			statement.close();
		}
	}

	public void cerrarResultSet(ResultSet resultSet) throws Exception {
		if (resultSet != null) {
			resultSet.close();
		}
	}
}
