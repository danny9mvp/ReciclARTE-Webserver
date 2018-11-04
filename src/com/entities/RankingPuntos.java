/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DanielMauricio
 */
@Entity
@Table(name = "ranking_puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RankingPuntos.findAll", query = "SELECT r FROM RankingPuntos r")
    , @NamedQuery(name = "RankingPuntos.findById", query = "SELECT r FROM RankingPuntos r WHERE r.id = :id")
    , @NamedQuery(name = "RankingPuntos.findByNick", query = "SELECT r FROM RankingPuntos r WHERE r.nick = :nick")
    , @NamedQuery(name = "RankingPuntos.findByPuntaje", query = "SELECT r FROM RankingPuntos r WHERE r.puntaje = :puntaje")})
public class RankingPuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "nick")
    private String nick;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "puntaje")
    private Float puntaje;

    public RankingPuntos() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Float puntaje) {
        this.puntaje = puntaje;
    }
    
}
