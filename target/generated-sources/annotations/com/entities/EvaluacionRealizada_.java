package com.entities;

import com.entities.GoogleSheets;
import com.entities.Puntuacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-15T15:48:58")
@StaticMetamodel(EvaluacionRealizada.class)
public class EvaluacionRealizada_ { 

    public static volatile SingularAttribute<EvaluacionRealizada, Integer> evalId;
    public static volatile SingularAttribute<EvaluacionRealizada, Puntuacion> evalPuntuacion;
    public static volatile SingularAttribute<EvaluacionRealizada, GoogleSheets> evalGooglesheet;

}