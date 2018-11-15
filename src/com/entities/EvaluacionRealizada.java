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
@Table(name = "evaluacionrealizada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvaluacionRealizada.findAll", query = "SELECT e FROM EvaluacionRealizada e")
    , @NamedQuery(name = "EvaluacionRealizada.findByEvalId", query = "SELECT e FROM EvaluacionRealizada e WHERE e.evalId = :evalId")})
public class EvaluacionRealizada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="evaluacionrealizada_eval_id_seq")
    @SequenceGenerator(name="evaluacionrealizada_eval_id_seq", sequenceName="evaluacionrealizada_eval_id_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "eval_id")
    private Integer evalId;
    @JoinColumn(name = "eval_googlesheet", referencedColumnName = "gosh_id")
    @ManyToOne(optional = false)
    private GoogleSheets evalGooglesheet;
    @JoinColumn(name = "eval_puntuacion", referencedColumnName = "punt_id")
    @ManyToOne(optional = false)
    private Puntuacion evalPuntuacion;

    public EvaluacionRealizada() {
    }

    public EvaluacionRealizada(Integer evalId) {
        this.evalId = evalId;
    }

    public Integer getEvalId() {
        return evalId;
    }

    public void setEvalId(Integer evalId) {
        this.evalId = evalId;
    }

    public GoogleSheets getEvalGooglesheet() {
        return evalGooglesheet;
    }

    public void setEvalGooglesheet(GoogleSheets evalGooglesheet) {
        this.evalGooglesheet = evalGooglesheet;
    }

    public Puntuacion getEvalPuntuacion() {
        return evalPuntuacion;
    }

    public void setEvalPuntuacion(Puntuacion evalPuntuacion) {
        this.evalPuntuacion = evalPuntuacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evalId != null ? evalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionRealizada)) {
            return false;
        }
        EvaluacionRealizada other = (EvaluacionRealizada) object;
        if ((this.evalId == null && other.evalId != null) || (this.evalId != null && !this.evalId.equals(other.evalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.EvaluacionRealizada[ evalId=" + evalId + " ]";
    }
    
}