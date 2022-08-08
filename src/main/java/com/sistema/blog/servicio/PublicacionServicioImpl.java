package com.sistema.blog.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entidades.Publicacion;
import com.sistema.blog.execpciones.ResourceNotFoundException;
import com.sistema.blog.repositorio.PublicacionRepositorio;

@Service
public class PublicacionServicioImpl implements PublicacionServicio{

	@Autowired
	PublicacionRepositorio publicacionRepositorio;
	
	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = mapearEntidad(publicacionDTO);
		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);
		PublicacionDTO publicacionRespuesta = mapearDTO(nuevaPublicacion);
		return publicacionRespuesta;
	}

	@Override
	public List<PublicacionDTO> obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina) {
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina);
		
		Page<Publicacion> publicaciones = publicacionRepositorio.findAll(pageable);
		
		List<Publicacion> listDePublicaciones = publicaciones.getContent();	
		return listDePublicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
	}
	
	//convertir Enitdad a DTO
	private PublicacionDTO mapearDTO(Publicacion publicacion){
		PublicacionDTO publicacionDTO = new PublicacionDTO();
		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setDescripcion(publicacion.getDescricion());
		publicacionDTO.setContenido(publicacion.getContenido());
		return publicacionDTO;
	}
	
	//Convierte de DTO a Entidad
	private Publicacion mapearEntidad(PublicacionDTO publicacionDTO){
		Publicacion publicacion = new Publicacion();
		
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescricion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		return publicacion;
	}

	@Override
	public PublicacionDTO obtenerPublicacionPorID(Long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Publicacion", "id", id));
		return mapearDTO(publicacion);
	}

	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Publicacion", "id", id));
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescricion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
		return mapearDTO(publicacionActualizada);
	}

	@Override
	public void EliminarPublicacion(Long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Publicacion", "id", id));
		publicacionRepositorio.delete(publicacion);
	}

}
