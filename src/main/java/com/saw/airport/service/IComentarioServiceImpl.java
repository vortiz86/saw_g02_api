package com.saw.airport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saw.airport.model.dao.IComentarioDao;
import com.saw.airport.model.entities.Comentario;

@Service
public class IComentarioServiceImpl implements IComentarioService{
	
	@Autowired
	private IComentarioDao comentarioDao;

	@Override
	public List<Comentario> getComentarios(String id) {
		// TODO Auto-generated method stub
		return comentarioDao.findByAirpot(id.toUpperCase());
	}

	@Override
	public Comentario save(Comentario comentario) {
		// TODO Auto-generated method stub
		return comentarioDao.save(comentario);
	}

}
