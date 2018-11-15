package com.entities;

import com.entities.TipoMaterial;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-07T01:04:02")
@StaticMetamodel(Material.class)
public class Material_ { 

    public static volatile SingularAttribute<Material, String> mateNombre;
    public static volatile SingularAttribute<Material, String> mateDescripcion;
    public static volatile SingularAttribute<Material, TipoMaterial> mateTipo;
    public static volatile SingularAttribute<Material, Integer> mateId;

}