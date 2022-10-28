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
@Table(name = "departamento")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
  @NamedQuery(name = "Departamento.findById", query = "SELECT d FROM Departamento d WHERE d.id = :id"),
  @NamedQuery(name = "Departamento.findByDepartamento", query = "SELECT d FROM Departamento d WHERE d.departamento = :departamento"),
  @NamedQuery(name = "Departamento.findByEstado", query = "SELECT d FROM Departamento d WHERE d.estado = :estado"),
  @NamedQuery(name = "Departamento.findByDeletedAt", query = "SELECT d FROM Departamento d WHERE d.deletedAt = :deletedAt"),
  @NamedQuery(name = "Departamento.findByCreatedAt", query = "SELECT d FROM Departamento d WHERE d.createdAt = :createdAt"),
  @NamedQuery(name = "Departamento.findByUpdatedAt", query = "SELECT d FROM Departamento d WHERE d.updatedAt = :updatedAt")})
public class Departamento implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  @Size(max = 255)
  @Column(name = "departamento")
  private String departamento;
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
  @OneToMany(mappedBy = "departamentoId")
  private Collection<Distrito> distritoCollection;

  public Departamento() {
  }

  public Departamento(Long id) {
    this.id = id;
  }

  public Departamento(Long id, String estado) {
    this.id = id;
    this.estado = estado;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
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

  @XmlTransient
  public Collection<Distrito> getDistritoCollection() {
    return distritoCollection;
  }

  public void setDistritoCollection(Collection<Distrito> distritoCollection) {
    this.distritoCollection = distritoCollection;
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
    if (!(object instanceof Departamento)) {
      return false;
    }
    Departamento other = (Departamento) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.Departamento[ id=" + id + " ]";
  }
  
}
