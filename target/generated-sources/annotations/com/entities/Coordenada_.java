package com.entities;

import com.entities.PuntoRecoleccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-15T15:48:58")
@StaticMetamodel(Coordenada.class)
public class Coordenada_ { 

    public static volatile ListAttribute<Coordenada, PuntoRecoleccion> puntoRecoleccionList;
    public static volatile SingularAttribute<Coordenada, Float> coorLatitud;
    public static volatile SingularAttribute<Coordenada, Float> coorLongitud;
    public static volatile SingularAttribute<Coordenada, Integer> coorId;

}