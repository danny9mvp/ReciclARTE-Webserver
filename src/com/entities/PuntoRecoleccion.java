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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "puntorecoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuntoRecoleccion.findAll", query = "SELECT p FROM PuntoRecoleccion p")
    , @NamedQuery(name = "PuntoRecoleccion.findByPrecId", query = "SELECT p FROM PuntoRecoleccion p WHERE p.precId = :precId")
    , @NamedQuery(name = "PuntoRecoleccion.findByPrecNombre", query = "SELECT p FROM PuntoRecoleccion p WHERE p.precNombre = :precNombre")})
public class PuntoRecoleccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="puntorecoleccion_prec_id_seq")
    @SequenceGenerator(name="puntorecoleccion_prec_id_seq", sequenceName="puntorecoleccion_prec_id_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "prec_id")
    private Integer precId;
    @Basic(optional = false)
    @Column(name = "prec_nombre")
    private String precNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matrecPuntorecoleccion")
    private List<MaterialRecolectado> materialRecolectadoList;
    @JoinColumn(name = "prec_coordenadas", referencedColumnName = "coor_id")
    @ManyToOne(optional = false)
    private Coordenada precCoordenadas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jorlrCoordenadas")
    private List<JornadaLimpiezaRecoleccion> jornadaLimpiezaRecoleccionList;

    public PuntoRecoleccion() {
    }

    public PuntoRecoleccion(Integer precId) {
        this.precId = precId;
    }

    public PuntoRecoleccion(Integer precId, String precNombre) {
        this.precId = precId;
        this.precNombre = precNombre;
    }

    public Integer getPrecId() {
        return precId;
    }

    public void setPrecId(Integer precId) {
        this.precId = precId;
    }

    public String getPrecNombre() {
        return precNombre;
    }

    public void setPrecNombre(String precNombre) {
        this.precNombre = precNombre;
    }

    @XmlTransient
    public List<MaterialRecolectado> getMaterialRecolectadoList() {
        return materialRecolectadoList;
    }

    public void setMaterialRecolectadoList(List<MaterialRecolectado> materialRecolectadoList) {
        this.materialRecolectadoList = materialRecolectadoList;
    }

    public Coordenada getPrecCoordenadas() {
        return precCoordenadas;
    }

    public void setPrecCoordenadas(Coordenada precCoordenadas) {
        this.precCoordenadas = precCoordenadas;
    }

    @XmlTransient
    public List<JornadaLimpiezaRecoleccion> getJornadaLimpiezaRecoleccionList() {
        return jornadaLimpiezaRecoleccionList;
    }

    public void setJornadaLimpiezaRecoleccionList(List<JornadaLimpiezaRecoleccion> jornadaLimpiezaRecoleccionList) {
        this.jornadaLimpiezaRecoleccionList = jornadaLimpiezaRecoleccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (precId != null ? precId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntoRecoleccion)) {
            return false;
        }
        PuntoRecoleccion other = (PuntoRecoleccion) object;
        if ((this.precId == null && other.precId != null) || (this.precId != null && !this.precId.equals(other.precId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.PuntoRecoleccion[ precId=" + precId + " ]";
    }
    
}
