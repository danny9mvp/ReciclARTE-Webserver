/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author DanielMauricio
 */
@Embeddable
public class UsuarioPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "usua_identificacion")
    private String usuaIdentificacion;
    @Basic(optional = false)
    @Column(name = "usua_nick")
    private String usuaNick;

    public UsuarioPK() {
    }

    public UsuarioPK(String usuaIdentificacion, String usuaNick) {
        this.usuaIdentificacion = usuaIdentificacion;
        this.usuaNick = usuaNick;
    }

    public String getUsuaIdentificacion() {
        return usuaIdentificacion;
    }

    public void setUsuaIdentificacion(String usuaIdentificacion) {
        this.usuaIdentificacion = usuaIdentificacion;
    }

    public String getUsuaNick() {
        return usuaNick;
    }

    public void setUsuaNick(String usuaNick) {
        this.usuaNick = usuaNick;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuaIdentificacion != null ? usuaIdentificacion.hashCode() : 0);
        hash += (usuaNick != null ? usuaNick.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPK)) {
            return false;
        }
        UsuarioPK other = (UsuarioPK) object;
        if ((this.usuaIdentificacion == null && other.usuaIdentificacion != null) || (this.usuaIdentificacion != null && !this.usuaIdentificacion.equals(other.usuaIdentificacion))) {
            return false;
        }
        if ((this.usuaNick == null && other.usuaNick != null) || (this.usuaNick != null && !this.usuaNick.equals(other.usuaNick))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.UsuarioPK[ usuaIdentificacion=" + usuaIdentificacion + ", usuaNick=" + usuaNick + " ]";
    }
    
}
