package com.aval.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Comentarios implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String user;
	private String comentario;
	private Integer estrellas;
	private Integer likes;
	private Integer dislikes;
	private String fechaCreacion;
	private Integer producto;
	// private String parentId;
	private List<Comentarios> children;

	public Comentarios() {
		super();
		this.children = new ArrayList<Comentarios>();
	}

}
