package com.dto;

import com.dto.Persona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-11-03T02:01:35")
@StaticMetamodel(TipoPersona.class)
public class TipoPersona_ { 

    public static volatile SingularAttribute<TipoPersona, Date> createdAt;
    public static volatile SingularAttribute<TipoPersona, String> estado;
    public static volatile CollectionAttribute<TipoPersona, Persona> personaCollection;
    public static volatile SingularAttribute<TipoPersona, Long> id;
    public static volatile SingularAttribute<TipoPersona, String> nombre;
    public static volatile SingularAttribute<TipoPersona, Date> updatedAt;

}