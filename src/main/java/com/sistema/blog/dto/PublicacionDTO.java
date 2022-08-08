package com.sistema.blog.dto;

public class PublicacionDTO {
	private Long id;
	private String titulo;
	private String Descripcion;
	private String contenido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
		super();
		this.id = id;
		this.titulo = titulo;
		Descripcion = descripcion;
		this.contenido = contenido;
	}

	public PublicacionDTO() {
		super();
	}

}
