package com.saw.airport.service;

import java.util.List;


import com.saw.airport.model.entities.Comentario;


public interface IComentarioService {	
	
	public List<Comentario> getComentarios(String id);
	
	public Comentario save(Comentario comentario);

	public Comentario findById(Long id);
	
	public void delete(Long id);

}
