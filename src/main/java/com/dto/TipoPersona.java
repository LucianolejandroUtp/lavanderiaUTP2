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
@Table(name = "tipo_persona")
@NamedQueries({
  @NamedQuery(name = "TipoPersona.findAll", query = "SELECT t FROM TipoPersona t"),
  @NamedQuery(name = "TipoPersona.findById", query = "SELECT t FROM TipoPersona t WHERE t.id = :id"),
  @NamedQuery(name = "TipoPersona.findByUniqueId", query = "SELECT t FROM TipoPersona t WHERE t.uniqueId = :uniqueId"),
  @NamedQuery(name = "TipoPersona.findByDescripcion", query = "SELECT t FROM TipoPersona t WHERE t.descripcion = :descripcion"),
  @NamedQuery(name = "TipoPersona.findByEstado", query = "SELECT t FROM TipoPersona t WHERE t.estado = :estado"),
  @NamedQuery(name = "TipoPersona.findByCreatedAt", query = "SELECT t FROM TipoPersona t WHERE t.createdAt = :createdAt"),
  @NamedQuery(name = "TipoPersona.findByUpdatedAt", query = "SELECT t FROM TipoPersona t WHERE t.updatedAt = :updatedAt")})
public class TipoPersona implements Serializable {

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
  @Column(name = "descripcion")
  private String descripcion;
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
  @OneToMany(mappedBy = "tipoPersonaId", fetch = FetchType.LAZY)
  private Collection<Persona> personaCollection;

  public TipoPersona() {
  }

  public TipoPersona(Long id) {
    this.id = id;
  }

  public TipoPersona(Long id, String uniqueId, String estado) {
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

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
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

  public Collection<Persona> getPersonaCollection() {
    return personaCollection;
  }

  public void setPersonaCollection(Collection<Persona> personaCollection) {
    this.personaCollection = personaCollection;
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
    if (!(object instanceof TipoPersona)) {
      return false;
    }
    TipoPersona other = (TipoPersona) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.TipoPersona[ id=" + id + " ]";
  }
  
}
