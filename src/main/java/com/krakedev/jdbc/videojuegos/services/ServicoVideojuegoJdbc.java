package com.krakedev.jdbc.videojuegos.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.krakedev.jdbc.videojuegos.VideojuegoJbdc;
import com.krakedev.videojuegos.entidades.Videojuego;


@Service
public class ServicoVideojuegoJdbc {
	
	
	private static final Logger log = LogManager.getLogger(ServicoVideojuegoJdbc.class);
	
	public Videojuego crear(Videojuego videojuego) {
		log.info("Videojuego creado");
		return VideojuegoJbdc.insertar(videojuego);
	}
	
	public List<Videojuego> listar() {
		log.info("Lista de Videojuego");
		return VideojuegoJbdc.listar();
	}
	
	public Videojuego buscarPorCodigo(String codigo) {
		log.info("Videojuego buscado");
		return VideojuegoJbdc.buscar(codigo);
	}
	
	public Videojuego actualizar(Videojuego videojuego) {
		log.info("Videojuego actualizado");
		return VideojuegoJbdc.actualizar(videojuego);
	}
	
	public boolean eliminar(String codigo) {
		log.info("Videojuego eliminado");
		return VideojuegoJbdc.Delete(codigo);
	}

}
