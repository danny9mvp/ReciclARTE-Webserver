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
    , @NamedQuery(name = "RankingPuntos.findByPuesto", query = "SELECT r FROM RankingPuntos r WHERE r.puesto = :puesto")
    , @NamedQuery(name = "RankingPuntos.findByNick", query = "SELECT r FROM RankingPuntos r WHERE r.nick = :nick")
    , @NamedQuery(name = "RankingPuntos.findByPuntajetotal", query = "SELECT r FROM RankingPuntos r WHERE r.puntajetotal = :puntajetotal")})
public class RankingPuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "puesto")
    private BigInteger puesto;
    @Id
    @Column(name = "nick")
    private String nick;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "puntajetotal")
    private Float puntajetotal;

    public RankingPuntos() {
    }

    public BigInteger getPuesto() {
        return puesto;
    }

    public void setPuesto(BigInteger puesto) {
        this.puesto = puesto;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Float getPuntajetotal() {
        return puntajetotal;
    }

    public void setPuntajetotal(Float puntajetotal) {
        this.puntajetotal = puntajetotal;
    }
    
}
