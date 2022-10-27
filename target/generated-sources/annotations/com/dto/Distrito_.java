package com.dto;

import com.dto.Persona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-10-26T19:33:11")
@StaticMetamodel(Distrito.class)
public class Distrito_ { 

    public static volatile SingularAttribute<Distrito, Date> createdAt;
    public static volatile SingularAttribute<Distrito, Date> deletedAt;
    public static volatile CollectionAttribute<Distrito, Persona> personaCollection;
    public static volatile SingularAttribute<Distrito, Long> id;
    public static volatile SingularAttribute<Distrito, String> nombre;
    public static volatile SingularAttribute<Distrito, Date> updatedAt;

}