package com.krakedev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.Videojuego;

public class VideojuegoJbdc {

	private static final Logger log = LogManager.getLogger(VideojuegoJbdc.class);

	/* funcoin para insertar un nuevo juego */
	public static Videojuego insertar(Videojuego videojuego) {
		Videojuego videojuegoCreado = null;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
				INSERT INTO videojuegos(codigo, nombre, plataforma, precio, disponible, genero)
				VALUES(?, ?, ?, ?, ?, ?)
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, videojuego.getCodigo());
			ps.setString(2, videojuego.getNombre());
			ps.setString(3, videojuego.getPlataforma());
			ps.setDouble(4, videojuego.getPrecio());
			ps.setBoolean(5, videojuego.isDisponible());
			ps.setString(6, videojuego.getGenero());

			int filas = ps.executeUpdate();

			videojuegoCreado = videojuego;

			log.info("Se insertaron las filas correctamente " + filas);

		} catch (Exception e) {
			log.error("Ocurrio un error en la conexion" + e.getMessage());
		} finally {
			try {
				con.close();
				ps.close();
				log.info("Conexion cerrada");
			} catch (SQLException e) {
				log.error("Ocurrio un error al cerrar la conexion: " + e.getMessage());
			}
		}
		return videojuegoCreado;
	}

	/* funcion para mostrar la lista de juego que tengo */
	public static List<Videojuego> listar() {
		List<Videojuego> videojuegos = new ArrayList<Videojuego>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = """
				select codigo, nombre, plataforma, precio, disponible, genero fron videojuegos;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Videojuego videojuego = new Videojuego();

				videojuego.setCodigo(rs.getString(1));
				videojuego.setNombre(rs.getString(2));
				videojuego.setPlataforma(rs.getString(3));
				videojuego.setPrecio(rs.getDouble(4));
				videojuego.setDisponible(rs.getBoolean(5));
				videojuego.setGenero(rs.getString(6));

				log.info("Se insertaron las filas correctamente ");
			}
		} catch (Exception e) {
			log.error("Ocurrio un error en la conexion" + e.getMessage());
		} finally {
			Conexion.cerrarConexiones(con, ps, rs);
		}
		return videojuegos;
	}

	/* funcion para buscar por el codigo */
	public static Videojuego buscar(String codigo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = """
				select codigo, nombre, plataforma, precio, disponible, genero fron videojuegos where codigo = ?;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Videojuego videojuego = new Videojuego();

				videojuego.setCodigo(rs.getString(1));
				videojuego.setNombre(rs.getString(2));
				videojuego.setPlataforma(rs.getString(3));
				videojuego.setPrecio(rs.getDouble(4));
				videojuego.setDisponible(rs.getBoolean(5));
				videojuego.setGenero(rs.getString(6));

				log.info("Videojuego encontrado");

				return videojuego;
			}
		} catch (Exception e) {
			log.error("Ocurrio un error en la busqueda" + e.getMessage());
		} finally {
			Conexion.cerrarConexiones(con, ps, rs);
		}
		return null;
	}

	/* funcoin para actualidar los datos de un videojuego por su codigo */
	public static Videojuego actualizar(Videojuego videojuego) {

		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
				update videojuego set nombre = ?, plataforma = ?, precio = ?, disponible = ?, genero = ? where codigo = ?;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, videojuego.getNombre());
			ps.setString(2, videojuego.getPlataforma());
			ps.setDouble(3, videojuego.getPrecio());
			ps.setBoolean(4, videojuego.isDisponible());
			ps.setString(5, videojuego.getGenero());

			ps.setString(6, videojuego.getCodigo());

			int fila = ps.executeUpdate();

			if (fila > 0) {
				log.info("Videojuego actualizado con exito" + fila);
				return buscar(videojuego.getCodigo());
			}

		} catch (Exception e) {
			log.error("Ocurrio un error en la actualizacion del videojuego" + e.getMessage());
		} finally {
			Conexion.cerrarConexiones(con, ps, null);
		}

		return null;
	}
	
	
	/*fUNCION PARA ELIMINAR VIDEOJUEGO*/
	public static boolean Delete(String codigo) {

		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
				delete from videojuego where codigo = ?;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);

			int fila = ps.executeUpdate();

			if (fila > 0) {
				log.info("Videojuego Eliminado con exito" + fila);
				return true;
			}

		} catch (Exception e) {
			log.error("Ocurrio un error en la eliminacion del videojuego" + e.getMessage());
		} finally {
			Conexion.cerrarConexiones(con, ps, null);
		}

		return false;
	}

}
