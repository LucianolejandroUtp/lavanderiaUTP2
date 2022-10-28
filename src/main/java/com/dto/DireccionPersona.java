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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desti
 */
@Entity
@Table(name = "direccion_persona")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "DireccionPersona.findAll", query = "SELECT d FROM DireccionPersona d"),
  @NamedQuery(name = "DireccionPersona.findById", query = "SELECT d FROM DireccionPersona d WHERE d.id = :id"),
  @NamedQuery(name = "DireccionPersona.findByEstado", query = "SELECT d FROM DireccionPersona d WHERE d.estado = :estado"),
  @NamedQuery(name = "DireccionPersona.findByDeletedAt", query = "SELECT d FROM DireccionPersona d WHERE d.deletedAt = :deletedAt"),
  @NamedQuery(name = "DireccionPersona.findByCreatedAt", query = "SELECT d FROM DireccionPersona d WHERE d.createdAt = :createdAt"),
  @NamedQuery(name = "DireccionPersona.findByUpdatedAt", query = "SELECT d FROM DireccionPersona d WHERE d.updatedAt = :updatedAt")})
public class DireccionPersona implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 8)
  @Column(name = "estado")
  private String estado;
  @Column(name = "deleted_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date deletedAt;
  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;
  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;
  @JoinColumn(name = "direccion_id", referencedColumnName = "id")
  @ManyToOne
  private Direccion direccionId;
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  @ManyToOne
  private Persona personaId;

  public DireccionPersona() {
  }

  public DireccionPersona(Long id) {
    this.id = id;
  }

  public DireccionPersona(Long id, String estado) {
    this.id = id;
    this.estado = estado;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
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

  public Direccion getDireccionId() {
    return direccionId;
  }

  public void setDireccionId(Direccion direccionId) {
    this.direccionId = direccionId;
  }

  public Persona getPersonaId() {
    return personaId;
  }

  public void setPersonaId(Persona personaId) {
    this.personaId = personaId;
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
    if (!(object instanceof DireccionPersona)) {
      return false;
    }
    DireccionPersona other = (DireccionPersona) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.DireccionPersona[ id=" + id + " ]";
  }
  
}
