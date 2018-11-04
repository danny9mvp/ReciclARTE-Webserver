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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByUsuaNombres", query = "SELECT u FROM Usuario u WHERE u.usuaNombres = :usuaNombres")
    , @NamedQuery(name = "Usuario.findByUsuaApellidos", query = "SELECT u FROM Usuario u WHERE u.usuaApellidos = :usuaApellidos")
    , @NamedQuery(name = "Usuario.findByUsuaEmail", query = "SELECT u FROM Usuario u WHERE u.usuaEmail = :usuaEmail")
    , @NamedQuery(name = "Usuario.findByUsuaIdentificacion", query = "SELECT u FROM Usuario u WHERE u.usuarioPK.usuaIdentificacion = :usuaIdentificacion")
    , @NamedQuery(name = "Usuario.findByUsuaNick", query = "SELECT u FROM Usuario u WHERE u.usuarioPK.usuaNick = :usuaNick")
    , @NamedQuery(name = "Usuario.findByUsuaClave", query = "SELECT u FROM Usuario u WHERE u.usuaClave = :usuaClave")
    , @NamedQuery(name = "Usuario.findByUsuaActivo", query = "SELECT u FROM Usuario u WHERE u.usuaActivo = :usuaActivo")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioPK usuarioPK;
    @Basic(optional = false)
    @Column(name = "usua_nombres")
    private String usuaNombres;
    @Basic(optional = false)
    @Column(name = "usua_apellidos")
    private String usuaApellidos;
    @Basic(optional = false)
    @Column(name = "usua_email")
    private String usuaEmail;
    @Basic(optional = false)
    @Column(name = "usua_clave")
    private String usuaClave;
    @Basic(optional = false)
    @Column(name = "usua_activo")
    private boolean usuaActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Puntuacion> puntuacionList;
    @JoinColumn(name = "usua_barrio", referencedColumnName = "barr_id")
    @ManyToOne(optional = false)
    private Barrio usuaBarrio;

    public Usuario() {
    }

    public Usuario(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public Usuario(UsuarioPK usuarioPK, String usuaNombres, String usuaApellidos, String usuaEmail, String usuaClave, boolean usuaActivo) {
        this.usuarioPK = usuarioPK;
        this.usuaNombres = usuaNombres;
        this.usuaApellidos = usuaApellidos;
        this.usuaEmail = usuaEmail;
        this.usuaClave = usuaClave;
        this.usuaActivo = usuaActivo;
    }

    public Usuario(String usuaIdentificacion, String usuaNick) {
        this.usuarioPK = new UsuarioPK(usuaIdentificacion, usuaNick);
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public String getUsuaNombres() {
        return usuaNombres;
    }

    public void setUsuaNombres(String usuaNombres) {
        this.usuaNombres = usuaNombres;
    }

    public String getUsuaApellidos() {
        return usuaApellidos;
    }

    public void setUsuaApellidos(String usuaApellidos) {
        this.usuaApellidos = usuaApellidos;
    }

    public String getUsuaEmail() {
        return usuaEmail;
    }

    public void setUsuaEmail(String usuaEmail) {
        this.usuaEmail = usuaEmail;
    }

    public String getUsuaClave() {
        return usuaClave;
    }

    public void setUsuaClave(String usuaClave) {
        this.usuaClave = usuaClave;
    }

    public boolean getUsuaActivo() {
        return usuaActivo;
    }

    public void setUsuaActivo(boolean usuaActivo) {
        this.usuaActivo = usuaActivo;
    }

    @XmlTransient
    public List<Puntuacion> getPuntuacionList() {
        return puntuacionList;
    }

    public void setPuntuacionList(List<Puntuacion> puntuacionList) {
        this.puntuacionList = puntuacionList;
    }

    public Barrio getUsuaBarrio() {
        return usuaBarrio;
    }

    public void setUsuaBarrio(Barrio usuaBarrio) {
        this.usuaBarrio = usuaBarrio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioPK != null ? usuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioPK == null && other.usuarioPK != null) || (this.usuarioPK != null && !this.usuarioPK.equals(other.usuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Usuario[ usuarioPK=" + usuarioPK + " ]";
    }
    
}
