/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DanielMauricio
 */
@Entity
@Table(name = "coordenadas_jornadas_recoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoordenadasJornadasRecoleccion.findAll", query = "SELECT c FROM CoordenadasJornadasRecoleccion c")
    , @NamedQuery(name = "CoordenadasJornadasRecoleccion.findById", query = "SELECT c FROM CoordenadasJornadasRecoleccion c WHERE c.id = :id")
    , @NamedQuery(name = "CoordenadasJornadasRecoleccion.findByLongitud", query = "SELECT c FROM CoordenadasJornadasRecoleccion c WHERE c.longitud = :longitud")
    , @NamedQuery(name = "CoordenadasJornadasRecoleccion.findByLatitud", query = "SELECT c FROM CoordenadasJornadasRecoleccion c WHERE c.latitud = :latitud")
    , @NamedQuery(name = "CoordenadasJornadasRecoleccion.findByDescripcion", query = "SELECT c FROM CoordenadasJornadasRecoleccion c WHERE c.descripcion = :descripcion")})
public class CoordenadasJornadasRecoleccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "longitud")
    private Float longitud;
    @Column(name = "latitud")
    private Float latitud;
    @Column(name = "descripcion")
    private String descripcion;

    public CoordenadasJornadasRecoleccion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
