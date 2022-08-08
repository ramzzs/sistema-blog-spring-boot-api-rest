package com.sistema.blog.servicio;

import java.util.List;

import com.sistema.blog.dto.PublicacionDTO;

public interface PublicacionServicio {
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	
	public List<PublicacionDTO> obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina);
	
	public PublicacionDTO obtenerPublicacionPorID(Long id);
	
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id);
	
	public void EliminarPublicacion(Long id);
}
