package com.aval.api.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Comentario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String user;
	private String comentario;
	private Integer estrellas;
	private Integer likes;
	private Integer dislikes;
	private String fechaCreacion;
	private Integer producto;
	private String parentId;

}
