package com.dto;

import com.dto.Departamento;
import com.dto.Direccion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-11-11T00:49:26")
@StaticMetamodel(Distrito.class)
public class Distrito_ { 

    public static volatile SingularAttribute<Distrito, String> descripcion;
    public static volatile SingularAttribute<Distrito, Date> createdAt;
    public static volatile SingularAttribute<Distrito, String> estado;
    public static volatile SingularAttribute<Distrito, Long> id;
    public static volatile CollectionAttribute<Distrito, Direccion> direccionCollection;
    public static volatile SingularAttribute<Distrito, Departamento> departamentoId;
    public static volatile SingularAttribute<Distrito, Date> updatedAt;

}