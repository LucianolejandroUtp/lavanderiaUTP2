/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "prenda")
@NamedQueries({
  @NamedQuery(name = "Prenda.findAll", query = "SELECT p FROM Prenda p"),
  @NamedQuery(name = "Prenda.findById", query = "SELECT p FROM Prenda p WHERE p.id = :id"),
  @NamedQuery(name = "Prenda.findByUniqueId", query = "SELECT p FROM Prenda p WHERE p.uniqueId = :uniqueId"),
  @NamedQuery(name = "Prenda.findByCantidad", query = "SELECT p FROM Prenda p WHERE p.cantidad = :cantidad"),
  @NamedQuery(name = "Prenda.findByColor", query = "SELECT p FROM Prenda p WHERE p.color = :color"),
  @NamedQuery(name = "Prenda.findByMarca", query = "SELECT p FROM Prenda p WHERE p.marca = :marca"),
  @NamedQuery(name = "Prenda.findByEstadoDePrenda", query = "SELECT p FROM Prenda p WHERE p.estadoDePrenda = :estadoDePrenda"),
  @NamedQuery(name = "Prenda.findByEstado", query = "SELECT p FROM Prenda p WHERE p.estado = :estado"),
  @NamedQuery(name = "Prenda.findByCreatedAt", query = "SELECT p FROM Prenda p WHERE p.createdAt = :createdAt"),
  @NamedQuery(name = "Prenda.findByUpdatedAt", query = "SELECT p FROM Prenda p WHERE p.updatedAt = :updatedAt")})
public class Prenda implements Serializable {

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
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "cantidad")
  private Double cantidad;
  @Size(max = 255)
  @Column(name = "color")
  private String color;
  @Size(max = 255)
  @Column(name = "marca")
  private String marca;
  @Size(max = 255)
  @Column(name = "estado_de_prenda")
  private String estadoDePrenda;
  @Lob
  @Size(max = 65535)
  @Column(name = "observacion")
  private String observacion;
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
  @JoinColumn(name = "persona_id_cliente", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.EAGER)
  private Persona personaIdCliente;
  @JoinColumn(name = "persona_id_empleado", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.EAGER)
  private Persona personaIdEmpleado;
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.EAGER)
  private Servicio servicioId;
  @JoinColumn(name = "tipo_de_prenda_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.EAGER)
  private TipoDePrenda tipoDePrendaId;

  public Prenda() {
  }

  public Prenda(Long id) {
    this.id = id;
  }

  public Prenda(Long id, String uniqueId, String estado) {
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

  public Double getCantidad() {
    return cantidad;
  }

  public void setCantidad(Double cantidad) {
    this.cantidad = cantidad;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getEstadoDePrenda() {
    return estadoDePrenda;
  }

  public void setEstadoDePrenda(String estadoDePrenda) {
    this.estadoDePrenda = estadoDePrenda;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
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

  public Persona getPersonaIdCliente() {
    return personaIdCliente;
  }

  public void setPersonaIdCliente(Persona personaIdCliente) {
    this.personaIdCliente = personaIdCliente;
  }

  public Persona getPersonaIdEmpleado() {
    return personaIdEmpleado;
  }

  public void setPersonaIdEmpleado(Persona personaIdEmpleado) {
    this.personaIdEmpleado = personaIdEmpleado;
  }

  public Servicio getServicioId() {
    return servicioId;
  }

  public void setServicioId(Servicio servicioId) {
    this.servicioId = servicioId;
  }

  public TipoDePrenda getTipoDePrendaId() {
    return tipoDePrendaId;
  }

  public void setTipoDePrendaId(TipoDePrenda tipoDePrendaId) {
    this.tipoDePrendaId = tipoDePrendaId;
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
    if (!(object instanceof Prenda)) {
      return false;
    }
    Prenda other = (Prenda) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.Prenda[ id=" + id + " ]";
  }
  
}
