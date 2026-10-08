package com.krakedev.jdbc.videojuegos.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.jdbc.videojuegos.services.ServicoVideojuegoJdbc;
import com.krakedev.videojuegos.entidades.Videojuego;

@RestController
@RequestMapping("/jdbc/videojuegos")
public class VideojuegoJdbcController {
	
	private static final Logger log = LogManager.getLogger(VideojuegoJdbcController.class);
	
	private final ServicoVideojuegoJdbc  ServicioVideojuegos;

	public VideojuegoJdbcController(ServicoVideojuegoJdbc servicioVideojuegos) {
		ServicioVideojuegos = servicioVideojuegos;
	}
	
	@PostMapping
	public ResponseEntity<Videojuego> crear(@RequestBody Videojuego videojuego){
		Videojuego creado = ServicioVideojuegos.crear(videojuego);
		if(creado != null) {
			return new ResponseEntity<Videojuego>(creado, HttpStatus.CREATED);
		}
		return new ResponseEntity<Videojuego>(creado, HttpStatus.BAD_REQUEST);
	}

	

}
