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
@Table(name = "barrio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barrio.findAll", query = "SELECT b FROM Barrio b")
    , @NamedQuery(name = "Barrio.findByBarrId", query = "SELECT b FROM Barrio b WHERE b.barrId = :barrId")
    , @NamedQuery(name = "Barrio.findByBarrNombre", query = "SELECT b FROM Barrio b WHERE b.barrNombre = :barrNombre")})
public class Barrio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="barrio_barr_id_seq")
    @SequenceGenerator(name="barrio_barr_id_seq", sequenceName="barrio_barr_id_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "barr_id")
    private Integer barrId;
    @Basic(optional = false)
    @Column(name = "barr_nombre")
    private String barrNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuaBarrio")
    private List<Usuario> usuarioList;

    public Barrio() {
    }

    public Barrio(Integer barrId) {
        this.barrId = barrId;
    }

    public Barrio(Integer barrId, String barrNombre) {
        this.barrId = barrId;
        this.barrNombre = barrNombre;
    }

    public Integer getBarrId() {
        return barrId;
    }

    public void setBarrId(Integer barrId) {
        this.barrId = barrId;
    }

    public String getBarrNombre() {
        return barrNombre;
    }

    public void setBarrNombre(String barrNombre) {
        this.barrNombre = barrNombre;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (barrId != null ? barrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barrio)) {
            return false;
        }
        Barrio other = (Barrio) object;
        if ((this.barrId == null && other.barrId != null) || (this.barrId != null && !this.barrId.equals(other.barrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Barrio[ barrId=" + barrId + " ]";
    }
    
}
