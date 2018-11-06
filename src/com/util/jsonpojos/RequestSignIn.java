package com.util.jsonpojos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestSignIn {
	@XmlElement private String nombres;
	@XmlElement private String apellidos;
	@XmlElement private String email;
	@XmlElement private String identificacion;
	@XmlElement private String nick;
	@XmlElement private String clave;
	@XmlElement private boolean activo;
	@XmlElement private Integer barrioId;
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public boolean getActivo() {
		return this.activo;
	}
	public Integer getBarrioId() {
		return barrioId;
	}
	public void setBarrioId(Integer barrioId) {
		this.barrioId = barrioId;
	}
	
	
	
}
