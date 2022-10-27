package com.dto;

import com.dto.Distrito;
import com.dto.TipoPersona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-10-27T13:22:25")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellidos;
    public static volatile SingularAttribute<Persona, String> estado;
    public static volatile SingularAttribute<Persona, String> direccion;
    public static volatile SingularAttribute<Persona, String> nombres;
    public static volatile SingularAttribute<Persona, Date> createdAt;
    public static volatile SingularAttribute<Persona, Distrito> distritoId;
    public static volatile SingularAttribute<Persona, String> password;
    public static volatile SingularAttribute<Persona, Date> deletedAt;
    public static volatile SingularAttribute<Persona, Long> id;
    public static volatile SingularAttribute<Persona, String> telefono;
    public static volatile SingularAttribute<Persona, TipoPersona> tipoPersonaId;
    public static volatile SingularAttribute<Persona, String> dni;
    public static volatile SingularAttribute<Persona, String> referencia;
    public static volatile SingularAttribute<Persona, String> email;
    public static volatile SingularAttribute<Persona, Date> updatedAt;

}