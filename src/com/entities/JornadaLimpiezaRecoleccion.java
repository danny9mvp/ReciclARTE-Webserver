/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DanielMauricio
 */
@Entity
@Table(name = "jornadalimpiezarecoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JornadaLimpiezaRecoleccion.findAll", query = "SELECT j FROM JornadaLimpiezaRecoleccion j")
    , @NamedQuery(name = "JornadaLimpiezaRecoleccion.findByJorlrId", query = "SELECT j FROM JornadaLimpiezaRecoleccion j WHERE j.jorlrId = :jorlrId")
    , @NamedQuery(name = "JornadaLimpiezaRecoleccion.findByJorlrDescripcion", query = "SELECT j FROM JornadaLimpiezaRecoleccion j WHERE j.jorlrDescripcion = :jorlrDescripcion")
    , @NamedQuery(name = "JornadaLimpiezaRecoleccion.findByJorlrFechainicio", query = "SELECT j FROM JornadaLimpiezaRecoleccion j WHERE j.jorlrFechainicio = :jorlrFechainicio")
    , @NamedQuery(name = "JornadaLimpiezaRecoleccion.findByJorlrFechafin", query = "SELECT j FROM JornadaLimpiezaRecoleccion j WHERE j.jorlrFechafin = :jorlrFechafin")})
public class JornadaLimpiezaRecoleccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="jornadalimpiezarecoleccion_jorlr_id_seq")
    @SequenceGenerator(name="jornadalimpiezarecoleccion_jorlr_id_seq", sequenceName="jornadalimpiezarecoleccion_jorlr_id_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "jorlr_id")
    private Integer jorlrId;
    @Basic(optional = false)
    @Column(name = "jorlr_descripcion")
    private String jorlrDescripcion;
    @Basic(optional = false)
    @Column(name = "jorlr_fechainicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jorlrFechainicio;
    @Basic(optional = false)
    @Column(name = "jorlr_fechafin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jorlrFechafin;
    @JoinColumn(name = "jorlr_coordenadas", referencedColumnName = "prec_id")
    @ManyToOne(optional = false)
    private PuntoRecoleccion jorlrCoordenadas;

    public JornadaLimpiezaRecoleccion() {
    }

    public JornadaLimpiezaRecoleccion(Integer jorlrId) {
        this.jorlrId = jorlrId;
    }

    public JornadaLimpiezaRecoleccion(Integer jorlrId, String jorlrDescripcion, Date jorlrFechainicio, Date jorlrFechafin) {
        this.jorlrId = jorlrId;
        this.jorlrDescripcion = jorlrDescripcion;
        this.jorlrFechainicio = jorlrFechainicio;
        this.jorlrFechafin = jorlrFechafin;
    }

    public Integer getJorlrId() {
        return jorlrId;
    }

    public void setJorlrId(Integer jorlrId) {
        this.jorlrId = jorlrId;
    }

    public String getJorlrDescripcion() {
        return jorlrDescripcion;
    }

    public void setJorlrDescripcion(String jorlrDescripcion) {
        this.jorlrDescripcion = jorlrDescripcion;
    }

    public Date getJorlrFechainicio() {
        return jorlrFechainicio;
    }

    public void setJorlrFechainicio(Date jorlrFechainicio) {
        this.jorlrFechainicio = jorlrFechainicio;
    }

    public Date getJorlrFechafin() {
        return jorlrFechafin;
    }

    public void setJorlrFechafin(Date jorlrFechafin) {
        this.jorlrFechafin = jorlrFechafin;
    }

    public PuntoRecoleccion getJorlrCoordenadas() {
        return jorlrCoordenadas;
    }

    public void setJorlrCoordenadas(PuntoRecoleccion jorlrCoordenadas) {
        this.jorlrCoordenadas = jorlrCoordenadas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jorlrId != null ? jorlrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JornadaLimpiezaRecoleccion)) {
            return false;
        }
        JornadaLimpiezaRecoleccion other = (JornadaLimpiezaRecoleccion) object;
        if ((this.jorlrId == null && other.jorlrId != null) || (this.jorlrId != null && !this.jorlrId.equals(other.jorlrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.JornadaLimpiezaRecoleccion[ jorlrId=" + jorlrId + " ]";
    }
    
}
