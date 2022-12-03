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
@Table(name = "distrito")
@NamedQueries({
  @NamedQuery(name = "Distrito.findAll", query = "SELECT d FROM Distrito d"),
  @NamedQuery(name = "Distrito.findById", query = "SELECT d FROM Distrito d WHERE d.id = :id"),
  @NamedQuery(name = "Distrito.findByUniqueId", query = "SELECT d FROM Distrito d WHERE d.uniqueId = :uniqueId"),
  @NamedQuery(name = "Distrito.findByDescripcion", query = "SELECT d FROM Distrito d WHERE d.descripcion = :descripcion"),
  @NamedQuery(name = "Distrito.findByEstado", query = "SELECT d FROM Distrito d WHERE d.estado = :estado"),
  @NamedQuery(name = "Distrito.findByCreatedAt", query = "SELECT d FROM Distrito d WHERE d.createdAt = :createdAt"),
  @NamedQuery(name = "Distrito.findByUpdatedAt", query = "SELECT d FROM Distrito d WHERE d.updatedAt = :updatedAt")})
public class Distrito implements Serializable {

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
  @JoinColumn(name = "departamento_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.EAGER)
  private Departamento departamentoId;
  @OneToMany(mappedBy = "distritoId", fetch = FetchType.EAGER)
  private Collection<Direccion> direccionCollection;

  public Distrito() {
  }

  public Distrito(Long id) {
    this.id = id;
  }

  public Distrito(Long id, String uniqueId, String estado) {
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

  public Departamento getDepartamentoId() {
    return departamentoId;
  }

  public void setDepartamentoId(Departamento departamentoId) {
    this.departamentoId = departamentoId;
  }

  public Collection<Direccion> getDireccionCollection() {
    return direccionCollection;
  }

  public void setDireccionCollection(Collection<Direccion> direccionCollection) {
    this.direccionCollection = direccionCollection;
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
    if (!(object instanceof Distrito)) {
      return false;
    }
    Distrito other = (Distrito) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.Distrito[ id=" + id + " ]";
  }
  
}
