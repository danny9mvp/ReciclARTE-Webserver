/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DanielMauricio
 */
@Entity
@Table(name = "coordenada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coordenada.findAll", query = "SELECT c FROM Coordenada c")
    , @NamedQuery(name = "Coordenada.findByCoorId", query = "SELECT c FROM Coordenada c WHERE c.coorId = :coorId")
    , @NamedQuery(name = "Coordenada.findByCoorLongitud", query = "SELECT c FROM Coordenada c WHERE c.coorLongitud = :coorLongitud")
    , @NamedQuery(name = "Coordenada.findByCoorLatitud", query = "SELECT c FROM Coordenada c WHERE c.coorLatitud = :coorLatitud")})
public class Coordenada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="coordenada_coor_id_seq")
    @SequenceGenerator(name="coordenada_coor_id_seq", sequenceName="coordenada_coor_id_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "coor_id")
    private Integer coorId;
    @Basic(optional = false)
    @Column(name = "coor_longitud")
    private float coorLongitud;
    @Basic(optional = false)
    @Column(name = "coor_latitud")
    private float coorLatitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "precCoordenadas")
    private List<PuntoRecoleccion> puntoRecoleccionList;

    public Coordenada() {
    }

    public Coordenada(Integer coorId) {
        this.coorId = coorId;
    }

    public Coordenada(Integer coorId, float coorLongitud, float coorLatitud) {
        this.coorId = coorId;
        this.coorLongitud = coorLongitud;
        this.coorLatitud = coorLatitud;
    }

    public Integer getCoorId() {
        return coorId;
    }

    public void setCoorId(Integer coorId) {
        this.coorId = coorId;
    }

    public float getCoorLongitud() {
        return coorLongitud;
    }

    public void setCoorLongitud(float coorLongitud) {
        this.coorLongitud = coorLongitud;
    }

    public float getCoorLatitud() {
        return coorLatitud;
    }

    public void setCoorLatitud(float coorLatitud) {
        this.coorLatitud = coorLatitud;
    }

    @XmlTransient
    public List<PuntoRecoleccion> getPuntoRecoleccionList() {
        return puntoRecoleccionList;
    }

    public void setPuntoRecoleccionList(List<PuntoRecoleccion> puntoRecoleccionList) {
        this.puntoRecoleccionList = puntoRecoleccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coorId != null ? coorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coordenada)) {
            return false;
        }
        Coordenada other = (Coordenada) object;
        if ((this.coorId == null && other.coorId != null) || (this.coorId != null && !this.coorId.equals(other.coorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Coordenada[ coorId=" + coorId + " ]";
    }
    
}
