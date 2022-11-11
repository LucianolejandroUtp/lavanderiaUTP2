package com.dto;

import com.dto.Cita;
import com.dto.DireccionPersona;
import com.dto.Factura;
import com.dto.Prenda;
import com.dto.Telefono;
import com.dto.TipoPersona;
import com.dto.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-11-10T19:13:46")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellidos;
    public static volatile CollectionAttribute<Persona, Prenda> prendaCollection;
    public static volatile CollectionAttribute<Persona, Cita> citaCollection;
    public static volatile SingularAttribute<Persona, String> estado;
    public static volatile CollectionAttribute<Persona, DireccionPersona> direccionPersonaCollection;
    public static volatile CollectionAttribute<Persona, Vehiculo> vehiculoCollection;
    public static volatile SingularAttribute<Persona, String> nombres;
    public static volatile CollectionAttribute<Persona, Factura> facturaCollection;
    public static volatile SingularAttribute<Persona, Date> createdAt;
    public static volatile SingularAttribute<Persona, String> password;
    public static volatile SingularAttribute<Persona, Long> id;
    public static volatile SingularAttribute<Persona, TipoPersona> tipoPersonaId;
    public static volatile SingularAttribute<Persona, String> dni;
    public static volatile SingularAttribute<Persona, String> email;
    public static volatile SingularAttribute<Persona, Date> updatedAt;
    public static volatile CollectionAttribute<Persona, Telefono> telefonoCollection;

}