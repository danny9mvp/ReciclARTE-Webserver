/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DanielMauricio
 */
@Entity
@Table(name = "material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m")
    , @NamedQuery(name = "Material.findByMateId", query = "SELECT m FROM Material m WHERE m.mateId = :mateId")
    , @NamedQuery(name = "Material.findByMateNombre", query = "SELECT m FROM Material m WHERE m.mateNombre = :mateNombre")
    , @NamedQuery(name = "Material.findByMateDescripcion", query = "SELECT m FROM Material m WHERE m.mateDescripcion = :mateDescripcion")})
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="material_mate_id_seq")
    @SequenceGenerator(name="material_mate_id_seq", sequenceName="material_mate_id_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "mate_id")
    private Integer mateId;
    @Basic(optional = false)
    @Column(name = "mate_nombre")
    private String mateNombre;
    @Basic(optional = false)
    @Column(name = "mate_descripcion")
    private String mateDescripcion;
    @JoinColumn(name = "mate_tipo", referencedColumnName = "tmate_id")
    @ManyToOne(optional = false)
    private TipoMaterial mateTipo;

    public Material() {
    }

    public Material(Integer mateId) {
        this.mateId = mateId;
    }

    public Material(Integer mateId, String mateNombre, String mateDescripcion) {
        this.mateId = mateId;
        this.mateNombre = mateNombre;
        this.mateDescripcion = mateDescripcion;
    }

    public Integer getMateId() {
        return mateId;
    }

    public void setMateId(Integer mateId) {
        this.mateId = mateId;
    }

    public String getMateNombre() {
        return mateNombre;
    }

    public void setMateNombre(String mateNombre) {
        this.mateNombre = mateNombre;
    }

    public String getMateDescripcion() {
        return mateDescripcion;
    }

    public void setMateDescripcion(String mateDescripcion) {
        this.mateDescripcion = mateDescripcion;
    }

    public TipoMaterial getMateTipo() {
        return mateTipo;
    }

    public void setMateTipo(TipoMaterial mateTipo) {
        this.mateTipo = mateTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mateId != null ? mateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.mateId == null && other.mateId != null) || (this.mateId != null && !this.mateId.equals(other.mateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Material[ mateId=" + mateId + " ]";
    }
    
}
