package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {

	private static final Logger log = LogManager.getLogger(Conexion.class);

	private static final String URL = "jdbc:postgresql://localhost:5432/apijdbc";
	private static final String USER = "postgres";
	private static final String PASSWORD = "dustin";

	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			log.info("Conecion");
			return con;
		} catch (Exception e) {
			log.error("Error de conexion exitada" + e.getMessage());
			throw new RuntimeException("No se pudo exitar", e);
		}
	}
	
	public static void cerrarConexiones(Connection con, PreparedStatement ps, ResultSet rs) {
		
		try {
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}
		}catch(Exception e) {
			
		}
		
	}

}
