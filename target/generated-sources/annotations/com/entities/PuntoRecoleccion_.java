package com.entities;

import com.entities.Coordenada;
import com.entities.JornadaLimpiezaRecoleccion;
import com.entities.MaterialRecolectado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-15T15:48:58")
@StaticMetamodel(PuntoRecoleccion.class)
public class PuntoRecoleccion_ { 

    public static volatile SingularAttribute<PuntoRecoleccion, Coordenada> precCoordenadas;
    public static volatile ListAttribute<PuntoRecoleccion, MaterialRecolectado> materialRecolectadoList;
    public static volatile ListAttribute<PuntoRecoleccion, JornadaLimpiezaRecoleccion> jornadaLimpiezaRecoleccionList;
    public static volatile SingularAttribute<PuntoRecoleccion, String> precNombre;
    public static volatile SingularAttribute<PuntoRecoleccion, Integer> precId;

}