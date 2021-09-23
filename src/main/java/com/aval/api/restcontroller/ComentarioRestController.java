package com.aval.api.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aval.api.model.Comentario;
import com.aval.api.model.Comentarios;
import com.aval.api.service.ComentarioService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/aval-api")
public class ComentarioRestController {

	@Autowired
	ComentarioService comentarioService;

	@RequestMapping(value = "/comentario/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Comentarios>> reporteGeneral2(@PathVariable Integer id) {

		List<Comentarios> data = new ArrayList<Comentarios>();
		data = comentarioService.findAllById(id);
		return new ResponseEntity<List<Comentarios>>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/comentario/registro", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<?> create(@RequestBody Comentario comentario) {
		Comentario comentarioNew = null;

		Map<String, Object> response = new HashMap<>();
		try {
			comentarioNew = comentarioService.save(comentario);
		} catch (Exception e) {
			response.put("msg", "Error al realizar el insert");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Comentario>(comentarioNew, HttpStatus.CREATED);
	}
}
