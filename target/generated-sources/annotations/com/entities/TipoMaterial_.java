package com.entities;

import com.entities.Material;
import com.entities.MaterialRecolectado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-18T12:04:45")
@StaticMetamodel(TipoMaterial.class)
public class TipoMaterial_ { 

    public static volatile SingularAttribute<TipoMaterial, Integer> tmateId;
    public static volatile ListAttribute<TipoMaterial, MaterialRecolectado> materialRecolectadoList;
    public static volatile ListAttribute<TipoMaterial, Material> materialList;
    public static volatile SingularAttribute<TipoMaterial, String> tmateNombre;

}