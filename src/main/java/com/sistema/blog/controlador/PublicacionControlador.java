package com.sistema.blog.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.servicio.PublicacionServicio;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {
	
	@Autowired
	private PublicacionServicio publicacionServicio;
	
	@GetMapping
	public List<PublicacionDTO> listarPublicaciones(@RequestParam(value = "pageNO", defaultValue = "0", required = false) int numeroDePagina,@RequestParam(value="pageSize", defaultValue = "10", required = false)int medidaDePagina){
		return publicacionServicio.obtenerTodasLasPublicaciones(numeroDePagina, medidaDePagina);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(publicacionServicio.obtenerPublicacionPorID(id));
	}
	
	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO){
		return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO,@PathVariable(name = "id") Long id ){
		PublicacionDTO publicacionRespuesta = publicacionServicio.actualizarPublicacion(publicacionDTO, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id){
		publicacionServicio.EliminarPublicacion(id);
		return new ResponseEntity<>("Publicacion Eliminada con exito", HttpStatus.OK);
	}
}
