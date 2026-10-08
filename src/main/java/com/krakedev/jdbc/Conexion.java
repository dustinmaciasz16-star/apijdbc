package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {

	private static final Logger log = LogManager.getLogger(Conexion.class);

	private static final String URL = "jdbc:mysql://localhost:3306/apijdbc";
	private static final String USER = "root";
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

}
