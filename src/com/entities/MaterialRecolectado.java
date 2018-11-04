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
@Table(name = "materialrecolectado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialRecolectado.findAll", query = "SELECT m FROM MaterialRecolectado m")
    , @NamedQuery(name = "MaterialRecolectado.findByMatrecId", query = "SELECT m FROM MaterialRecolectado m WHERE m.matrecId = :matrecId")})
public class MaterialRecolectado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="materialrecolectado_matrec_id_seq")
    @SequenceGenerator(name="materialrecolectado_matrec_id_seq", sequenceName="materialrecolectado_matrec_id_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "matrec_id")
    private Integer matrecId;
    @JoinColumn(name = "matrec_puntorecoleccion", referencedColumnName = "prec_id")
    @ManyToOne(optional = false)
    private PuntoRecoleccion matrecPuntorecoleccion;
    @JoinColumn(name = "matrec_puntuacion", referencedColumnName = "punt_id")
    @ManyToOne(optional = false)
    private Puntuacion matrecPuntuacion;
    @JoinColumn(name = "matrec_tipomaterial", referencedColumnName = "tmate_id")
    @ManyToOne(optional = false)
    private TipoMaterial matrecTipomaterial;

    public MaterialRecolectado() {
    }

    public MaterialRecolectado(Integer matrecId) {
        this.matrecId = matrecId;
    }

    public Integer getMatrecId() {
        return matrecId;
    }

    public void setMatrecId(Integer matrecId) {
        this.matrecId = matrecId;
    }

    public PuntoRecoleccion getMatrecPuntorecoleccion() {
        return matrecPuntorecoleccion;
    }

    public void setMatrecPuntorecoleccion(PuntoRecoleccion matrecPuntorecoleccion) {
        this.matrecPuntorecoleccion = matrecPuntorecoleccion;
    }

    public Puntuacion getMatrecPuntuacion() {
        return matrecPuntuacion;
    }

    public void setMatrecPuntuacion(Puntuacion matrecPuntuacion) {
        this.matrecPuntuacion = matrecPuntuacion;
    }

    public TipoMaterial getMatrecTipomaterial() {
        return matrecTipomaterial;
    }

    public void setMatrecTipomaterial(TipoMaterial matrecTipomaterial) {
        this.matrecTipomaterial = matrecTipomaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matrecId != null ? matrecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialRecolectado)) {
            return false;
        }
        MaterialRecolectado other = (MaterialRecolectado) object;
        if ((this.matrecId == null && other.matrecId != null) || (this.matrecId != null && !this.matrecId.equals(other.matrecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.MaterialRecolectado[ matrecId=" + matrecId + " ]";
    }
    
}
