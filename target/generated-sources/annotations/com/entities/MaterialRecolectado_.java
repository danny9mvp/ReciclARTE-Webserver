package com.entities;

import com.entities.PuntoRecoleccion;
import com.entities.Puntuacion;
import com.entities.TipoMaterial;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-24T10:52:57")
@StaticMetamodel(MaterialRecolectado.class)
public class MaterialRecolectado_ { 

    public static volatile SingularAttribute<MaterialRecolectado, PuntoRecoleccion> matrecPuntorecoleccion;
    public static volatile SingularAttribute<MaterialRecolectado, Puntuacion> matrecPuntuacion;
    public static volatile SingularAttribute<MaterialRecolectado, TipoMaterial> matrecTipomaterial;
    public static volatile SingularAttribute<MaterialRecolectado, Integer> matrecId;

}