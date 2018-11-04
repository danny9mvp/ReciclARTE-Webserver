package com.util.jsonpojos;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestPuntaje {
	@XmlElement private String material;
	@XmlElement private float peso;
	@XmlElement private String puntoRecoleccion;
	@XmlElement private Date fecha;
	@XmlElement private String identificacion;
	@XmlElement private String nickname;
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String getPuntoRecoleccion() {
		return this.puntoRecoleccion;
	}
	public void setPuntoRecoleccion(String puntoRecoleccion) {
		this.puntoRecoleccion = puntoRecoleccion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
