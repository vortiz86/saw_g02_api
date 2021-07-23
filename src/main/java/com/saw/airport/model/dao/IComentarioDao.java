package com.saw.airport.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saw.airport.model.entities.Comentario;

public interface IComentarioDao extends JpaRepository<Comentario, Long> {
	
    @Query("SELECT t FROM Comentario t WHERE t.aeropuerto = ?1 ")
    List<Comentario> findByAirpot(String code);

}
