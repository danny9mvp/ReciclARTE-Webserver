/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DanielMauricio
 */
@Entity
@Table(name = "puntuacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puntuacion.findAll", query = "SELECT p FROM Puntuacion p")
    , @NamedQuery(name = "Puntuacion.findByPuntId", query = "SELECT p FROM Puntuacion p WHERE p.puntId = :puntId")
    , @NamedQuery(name = "Puntuacion.findByPuntValor", query = "SELECT p FROM Puntuacion p WHERE p.puntValor = :puntValor")
    , @NamedQuery(name = "Puntuacion.findByPuntFecha", query = "SELECT p FROM Puntuacion p WHERE p.puntFecha = :puntFecha")})
public class Puntuacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="puntuacion_punt_id_seq")
    @SequenceGenerator(name="puntuacion_punt_id_seq", sequenceName="puntuacion_punt_id_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "punt_id")
    private Integer puntId;
    @Basic(optional = false)
    @Column(name = "punt_valor")
    private float puntValor;
    @Basic(optional = false)
    @Column(name = "punt_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date puntFecha;
    @JoinColumns({
        @JoinColumn(name = "punt_usuarioidentificacion", referencedColumnName = "usua_identificacion")
        , @JoinColumn(name = "punt_usuarionick", referencedColumnName = "usua_nick")})
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matrecPuntuacion")
    private List<MaterialRecolectado> materialRecolectadoList;

    public Puntuacion() {
    }

    public Puntuacion(Integer puntId) {
        this.puntId = puntId;
    }

    public Puntuacion(Integer puntId, float puntValor, Date puntFecha) {
        this.puntId = puntId;
        this.puntValor = puntValor;
        this.puntFecha = puntFecha;
    }

    public Integer getPuntId() {
        return puntId;
    }

    public void setPuntId(Integer puntId) {
        this.puntId = puntId;
    }

    public float getPuntValor() {
        return puntValor;
    }

    public void setPuntValor(float puntValor) {
        this.puntValor = puntValor;
    }

    public Date getPuntFecha() {
        return puntFecha;
    }

    public void setPuntFecha(Date puntFecha) {
        this.puntFecha = puntFecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<MaterialRecolectado> getMaterialRecolectadoList() {
        return materialRecolectadoList;
    }

    public void setMaterialRecolectadoList(List<MaterialRecolectado> materialRecolectadoList) {
        this.materialRecolectadoList = materialRecolectadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (puntId != null ? puntId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntuacion)) {
            return false;
        }
        Puntuacion other = (Puntuacion) object;
        if ((this.puntId == null && other.puntId != null) || (this.puntId != null && !this.puntId.equals(other.puntId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Puntuacion[ puntId=" + puntId + " ]";
    }
    
}
