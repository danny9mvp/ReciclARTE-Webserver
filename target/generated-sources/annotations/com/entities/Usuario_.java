package com.entities;

import com.entities.Barrio;
import com.entities.Puntuacion;
import com.entities.UsuarioPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-24T10:52:57")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> usuaClave;
    public static volatile SingularAttribute<Usuario, Boolean> usuaActivo;
    public static volatile ListAttribute<Usuario, Puntuacion> puntuacionList;
    public static volatile SingularAttribute<Usuario, String> usuaNombres;
    public static volatile SingularAttribute<Usuario, String> usuaApellidos;
    public static volatile SingularAttribute<Usuario, UsuarioPK> usuarioPK;
    public static volatile SingularAttribute<Usuario, String> usuaEmail;
    public static volatile SingularAttribute<Usuario, Barrio> usuaBarrio;

}