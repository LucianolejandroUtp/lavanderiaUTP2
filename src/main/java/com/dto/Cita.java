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
@Table(name = "cita")
@NamedQueries({
  @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
  @NamedQuery(name = "Cita.findById", query = "SELECT c FROM Cita c WHERE c.id = :id"),
  @NamedQuery(name = "Cita.findByUniqueId", query = "SELECT c FROM Cita c WHERE c.uniqueId = :uniqueId"),
  @NamedQuery(name = "Cita.findByFecha", query = "SELECT c FROM Cita c WHERE c.fecha = :fecha"),
  @NamedQuery(name = "Cita.findByHora", query = "SELECT c FROM Cita c WHERE c.hora = :hora"),
  @NamedQuery(name = "Cita.findByEstado", query = "SELECT c FROM Cita c WHERE c.estado = :estado"),
  @NamedQuery(name = "Cita.findByCreatedAt", query = "SELECT c FROM Cita c WHERE c.createdAt = :createdAt"),
  @NamedQuery(name = "Cita.findByUpdatedAt", query = "SELECT c FROM Cita c WHERE c.updatedAt = :updatedAt")})
public class Cita implements Serializable {

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
  @Column(name = "fecha")
  @Temporal(TemporalType.DATE)
  private Date fecha;
  @Column(name = "hora")
  @Temporal(TemporalType.TIME)
  private Date hora;
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
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.EAGER)
  private Persona personaId;
  @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.EAGER)
  private Vehiculo vehiculoId;

  public Cita() {
  }

  public Cita(Long id) {
    this.id = id;
  }

  public Cita(Long id, String uniqueId, String estado) {
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

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Date getHora() {
    return hora;
  }

  public void setHora(Date hora) {
    this.hora = hora;
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

  public Persona getPersonaId() {
    return personaId;
  }

  public void setPersonaId(Persona personaId) {
    this.personaId = personaId;
  }

  public Vehiculo getVehiculoId() {
    return vehiculoId;
  }

  public void setVehiculoId(Vehiculo vehiculoId) {
    this.vehiculoId = vehiculoId;
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
    if (!(object instanceof Cita)) {
      return false;
    }
    Cita other = (Cita) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.Cita[ id=" + id + " ]";
  }
  
}
