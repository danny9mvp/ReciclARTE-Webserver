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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DanielMauricio
 */
@Entity
@Table(name = "googlesheets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GoogleSheets.findAll", query = "SELECT g FROM GoogleSheets g")
    , @NamedQuery(name = "GoogleSheets.findByGoshId", query = "SELECT g FROM GoogleSheets g WHERE g.goshId = :goshId")})
public class GoogleSheets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "gosh_id")
    private String goshId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evalGooglesheet")
    private List<EvaluacionRealizada> evaluacionrRealizadaList;

    public GoogleSheets() {
    }

    public GoogleSheets(String goshId) {
        this.goshId = goshId;
    }

    public String getGoshId() {
        return goshId;
    }

    public void setGoshId(String goshId) {
        this.goshId = goshId;
    }

    @XmlTransient
    public List<EvaluacionRealizada> getEvaluacionrRealizadaList() {
        return evaluacionrRealizadaList;
    }

    public void setEvaluacionrRealizadaList(List<EvaluacionRealizada> evaluacionrRealizadaList) {
        this.evaluacionrRealizadaList = evaluacionrRealizadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (goshId != null ? goshId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GoogleSheets)) {
            return false;
        }
        GoogleSheets other = (GoogleSheets) object;
        if ((this.goshId == null && other.goshId != null) || (this.goshId != null && !this.goshId.equals(other.goshId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.GoogleSheets[ goshId=" + goshId + " ]";
    }
    
}