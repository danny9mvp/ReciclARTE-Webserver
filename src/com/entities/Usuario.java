package com.entities;

public class Usuario {
	private String nombre;
	private String barrio;
	private double puntos;
	
	public Usuario() {
		
	}
	
	public Usuario(String n, String b, double p) {
		this.nombre=n;
		this.barrio=b;
		this.puntos=p;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public double getPuntos() {
		return puntos;
	}
	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}
	
}
