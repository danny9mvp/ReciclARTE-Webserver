package com.entities;

import com.entities.PuntoRecoleccion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-18T12:04:45")
@StaticMetamodel(JornadaLimpiezaRecoleccion.class)
public class JornadaLimpiezaRecoleccion_ { 

    public static volatile SingularAttribute<JornadaLimpiezaRecoleccion, Date> jorlrFechafin;
    public static volatile SingularAttribute<JornadaLimpiezaRecoleccion, Date> jorlrFechainicio;
    public static volatile SingularAttribute<JornadaLimpiezaRecoleccion, PuntoRecoleccion> jorlrCoordenadas;
    public static volatile SingularAttribute<JornadaLimpiezaRecoleccion, Integer> jorlrId;
    public static volatile SingularAttribute<JornadaLimpiezaRecoleccion, String> jorlrDescripcion;

}