package com.aval.api.service;

import java.util.List;

import com.aval.api.model.Comentario;
import com.aval.api.model.Comentarios;

public interface ComentarioService {

	public List<Comentarios> findAllById(Integer id);

	public Comentario save(Comentario comentario);
}
