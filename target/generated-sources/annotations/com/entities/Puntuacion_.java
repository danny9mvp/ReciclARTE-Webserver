package com.entities;

import com.entities.MaterialRecolectado;
import com.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-07T01:04:02")
@StaticMetamodel(Puntuacion.class)
public class Puntuacion_ { 

    public static volatile SingularAttribute<Puntuacion, Date> puntFecha;
    public static volatile SingularAttribute<Puntuacion, Integer> puntId;
    public static volatile ListAttribute<Puntuacion, MaterialRecolectado> materialRecolectadoList;
    public static volatile SingularAttribute<Puntuacion, Float> puntValor;
    public static volatile SingularAttribute<Puntuacion, Usuario> usuario;

}