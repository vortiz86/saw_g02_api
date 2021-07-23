package com.saw.airport.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comentarios")
public class Comentario {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Column(name = "update_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_at;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 4, message = "debe tener al menos 4 caracteres")
	@Column(nullable = false)
	private String comentario;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 4, message = "debe tener al menos 4 caracteres")
	@Column(nullable = false)
	private String usuario;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String aeropuerto;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAeropuerto() {
		return aeropuerto;
	}

	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}

	public void setAeropuerto(String aeropuerto) {
		this.aeropuerto = aeropuerto;
	}
	
	
	

}
