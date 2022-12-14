/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author desti
 */
@Entity
@Table(name = "persona")
@NamedQueries({
  @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
  @NamedQuery(name = "Persona.findById", query = "SELECT p FROM Persona p WHERE p.id = :id"),
  @NamedQuery(name = "Persona.findByUniqueId", query = "SELECT p FROM Persona p WHERE p.uniqueId = :uniqueId"),
  @NamedQuery(name = "Persona.findByNombres", query = "SELECT p FROM Persona p WHERE p.nombres = :nombres"),
  @NamedQuery(name = "Persona.findByApellidos", query = "SELECT p FROM Persona p WHERE p.apellidos = :apellidos"),
  @NamedQuery(name = "Persona.findByDni", query = "SELECT p FROM Persona p WHERE p.dni = :dni"),
  @NamedQuery(name = "Persona.findByEmail", query = "SELECT p FROM Persona p WHERE p.email = :email"),
  @NamedQuery(name = "Persona.findByPassword", query = "SELECT p FROM Persona p WHERE p.password = :password"),
  @NamedQuery(name = "Persona.findByEstado", query = "SELECT p FROM Persona p WHERE p.estado = :estado"),
  @NamedQuery(name = "Persona.findByCreatedAt", query = "SELECT p FROM Persona p WHERE p.createdAt = :createdAt"),
  @NamedQuery(name = "Persona.findByUpdatedAt", query = "SELECT p FROM Persona p WHERE p.updatedAt = :updatedAt")})
public class Persona implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 36)
  @Column(name = "unique_id")
  private String uniqueId;
  @Size(max = 255)
  @Column(name = "nombres")
  private String nombres;
  @Size(max = 255)
  @Column(name = "apellidos")
  private String apellidos;
  @Size(max = 255)
  @Column(name = "dni")
  private String dni;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Size(max = 255)
  @Column(name = "email")
  private String email;
  @Size(max = 255)
  @Column(name = "password")
  private String password;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 9)
  @Column(name = "estado")
  private String estado;
  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;
  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;
  @JoinColumn(name = "tipo_persona_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY)
  private TipoPersona tipoPersonaId;
  @OneToMany(mappedBy = "personaId", fetch = FetchType.LAZY)
  private Collection<Comprobante> comprobanteCollection;
  @OneToMany(mappedBy = "personaId", fetch = FetchType.LAZY)
  private Collection<Direccion> direccionCollection;
  @OneToMany(mappedBy = "personaId", fetch = FetchType.LAZY)
  private Collection<Vehiculo> vehiculoCollection;
  @OneToMany(mappedBy = "personaIdCliente", fetch = FetchType.LAZY)
  private Collection<Prenda> prendaCollection;
  @OneToMany(mappedBy = "personaIdEmpleado", fetch = FetchType.LAZY)
  private Collection<Prenda> prendaCollection1;
  @OneToMany(mappedBy = "personaId", fetch = FetchType.LAZY)
  private Collection<Telefono> telefonoCollection;
  @OneToMany(mappedBy = "personaId", fetch = FetchType.LAZY)
  private Collection<Cita> citaCollection;

  public Persona() {
  }

  public Persona(Long id) {
    this.id = id;
  }

  public Persona(Long id, String uniqueId, String estado) {
    this.id = id;
    this.uniqueId = uniqueId;
    this.estado = estado;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public TipoPersona getTipoPersonaId() {
    return tipoPersonaId;
  }

  public void setTipoPersonaId(TipoPersona tipoPersonaId) {
    this.tipoPersonaId = tipoPersonaId;
  }

  public Collection<Comprobante> getComprobanteCollection() {
    return comprobanteCollection;
  }

  public void setComprobanteCollection(Collection<Comprobante> comprobanteCollection) {
    this.comprobanteCollection = comprobanteCollection;
  }

  public Collection<Direccion> getDireccionCollection() {
    return direccionCollection;
  }

  public void setDireccionCollection(Collection<Direccion> direccionCollection) {
    this.direccionCollection = direccionCollection;
  }

  public Collection<Vehiculo> getVehiculoCollection() {
    return vehiculoCollection;
  }

  public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
    this.vehiculoCollection = vehiculoCollection;
  }

  public Collection<Prenda> getPrendaCollection() {
    return prendaCollection;
  }

  public void setPrendaCollection(Collection<Prenda> prendaCollection) {
    this.prendaCollection = prendaCollection;
  }

  public Collection<Prenda> getPrendaCollection1() {
    return prendaCollection1;
  }

  public void setPrendaCollection1(Collection<Prenda> prendaCollection1) {
    this.prendaCollection1 = prendaCollection1;
  }

  public Collection<Telefono> getTelefonoCollection() {
    return telefonoCollection;
  }

  public void setTelefonoCollection(Collection<Telefono> telefonoCollection) {
    this.telefonoCollection = telefonoCollection;
  }

  public Collection<Cita> getCitaCollection() {
    return citaCollection;
  }

  public void setCitaCollection(Collection<Cita> citaCollection) {
    this.citaCollection = citaCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Persona)) {
      return false;
    }
    Persona other = (Persona) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.Persona[ id=" + id + " ]";
  }
  
}
