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
@Table(name = "tipomaterial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMaterial.findAll", query = "SELECT t FROM TipoMaterial t")
    , @NamedQuery(name = "TipoMaterial.findByTmateId", query = "SELECT t FROM TipoMaterial t WHERE t.tmateId = :tmateId")
    , @NamedQuery(name = "TipoMaterial.findByTmateNombre", query = "SELECT t FROM TipoMaterial t WHERE t.tmateNombre = :tmateNombre")})
public class TipoMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tipomaterial_tmate_id_seq")
    @SequenceGenerator(name="tipomaterial_tmate_id_seq", sequenceName="tipomaterial_tmate_id_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "tmate_id")
    private Integer tmateId;
    @Basic(optional = false)
    @Column(name = "tmate_nombre")
    private String tmateNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mateTipo")
    private List<Material> materialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matrecTipomaterial")
    private List<MaterialRecolectado> materialRecolectadoList;

    public TipoMaterial() {
    }

    public TipoMaterial(Integer tmateId) {
        this.tmateId = tmateId;
    }

    public TipoMaterial(Integer tmateId, String tmateNombre) {
        this.tmateId = tmateId;
        this.tmateNombre = tmateNombre;
    }

    public Integer getTmateId() {
        return tmateId;
    }

    public void setTmateId(Integer tmateId) {
        this.tmateId = tmateId;
    }

    public String getTmateNombre() {
        return tmateNombre;
    }

    public void setTmateNombre(String tmateNombre) {
        this.tmateNombre = tmateNombre;
    }

    @XmlTransient
    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
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
        hash += (tmateId != null ? tmateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMaterial)) {
            return false;
        }
        TipoMaterial other = (TipoMaterial) object;
        if ((this.tmateId == null && other.tmateId != null) || (this.tmateId != null && !this.tmateId.equals(other.tmateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.TipoMaterial[ tmateId=" + tmateId + " ]";
    }
    
}
