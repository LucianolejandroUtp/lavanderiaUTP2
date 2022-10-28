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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desti
 */
@Entity
@Table(name = "direccion")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d"),
  @NamedQuery(name = "Direccion.findById", query = "SELECT d FROM Direccion d WHERE d.id = :id"),
  @NamedQuery(name = "Direccion.findByDireccion", query = "SELECT d FROM Direccion d WHERE d.direccion = :direccion"),
  @NamedQuery(name = "Direccion.findByEstado", query = "SELECT d FROM Direccion d WHERE d.estado = :estado"),
  @NamedQuery(name = "Direccion.findByDeletedAt", query = "SELECT d FROM Direccion d WHERE d.deletedAt = :deletedAt"),
  @NamedQuery(name = "Direccion.findByCreatedAt", query = "SELECT d FROM Direccion d WHERE d.createdAt = :createdAt"),
  @NamedQuery(name = "Direccion.findByUpdatedAt", query = "SELECT d FROM Direccion d WHERE d.updatedAt = :updatedAt")})
public class Direccion implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  @Size(max = 255)
  @Column(name = "direccion")
  private String direccion;
  @Lob
  @Size(max = 65535)
  @Column(name = "referencia")
  private String referencia;
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
  @JoinColumn(name = "distrito_id", referencedColumnName = "id")
  @ManyToOne
  private Distrito distritoId;
  @OneToMany(mappedBy = "direccionId")
  private Collection<DireccionPersona> direccionPersonaCollection;

  public Direccion() {
  }

  public Direccion(Long id) {
    this.id = id;
  }

  public Direccion(Long id, String estado) {
    this.id = id;
    this.estado = estado;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getReferencia() {
    return referencia;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
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

  public Distrito getDistritoId() {
    return distritoId;
  }

  public void setDistritoId(Distrito distritoId) {
    this.distritoId = distritoId;
  }

  @XmlTransient
  public Collection<DireccionPersona> getDireccionPersonaCollection() {
    return direccionPersonaCollection;
  }

  public void setDireccionPersonaCollection(Collection<DireccionPersona> direccionPersonaCollection) {
    this.direccionPersonaCollection = direccionPersonaCollection;
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
    if (!(object instanceof Direccion)) {
      return false;
    }
    Direccion other = (Direccion) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.Direccion[ id=" + id + " ]";
  }
  
}
