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

/**
 *
 * @author desti
 */
@Entity
@Table(name = "servicio")
@NamedQueries({
  @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
  @NamedQuery(name = "Servicio.findById", query = "SELECT s FROM Servicio s WHERE s.id = :id"),
  @NamedQuery(name = "Servicio.findByUniqueId", query = "SELECT s FROM Servicio s WHERE s.uniqueId = :uniqueId"),
  @NamedQuery(name = "Servicio.findByDescripcion", query = "SELECT s FROM Servicio s WHERE s.descripcion = :descripcion"),
  @NamedQuery(name = "Servicio.findByPrecio", query = "SELECT s FROM Servicio s WHERE s.precio = :precio"),
  @NamedQuery(name = "Servicio.findByEstado", query = "SELECT s FROM Servicio s WHERE s.estado = :estado"),
  @NamedQuery(name = "Servicio.findByCreatedAt", query = "SELECT s FROM Servicio s WHERE s.createdAt = :createdAt"),
  @NamedQuery(name = "Servicio.findByUpdatedAt", query = "SELECT s FROM Servicio s WHERE s.updatedAt = :updatedAt")})
public class Servicio implements Serializable {

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
  @Lob
  @Size(max = 65535)
  @Column(name = "detalles")
  private String detalles;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "precio")
  private Double precio;
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
  @OneToMany(mappedBy = "servicioId", fetch = FetchType.LAZY)
  private Collection<DetalleComprobante> detalleComprobanteCollection;
  @JoinColumn(name = "categoria_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Categoria categoriaId;
  @OneToMany(mappedBy = "servicioId", fetch = FetchType.LAZY)
  private Collection<Prenda> prendaCollection;

  public Servicio() {
  }

  public Servicio(Long id) {
    this.id = id;
  }

  public Servicio(Long id, String uniqueId, String estado) {
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

  public String getDetalles() {
    return detalles;
  }

  public void setDetalles(String detalles) {
    this.detalles = detalles;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
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

  public Collection<DetalleComprobante> getDetalleComprobanteCollection() {
    return detalleComprobanteCollection;
  }

  public void setDetalleComprobanteCollection(Collection<DetalleComprobante> detalleComprobanteCollection) {
    this.detalleComprobanteCollection = detalleComprobanteCollection;
  }

  public Categoria getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Categoria categoriaId) {
    this.categoriaId = categoriaId;
  }

  public Collection<Prenda> getPrendaCollection() {
    return prendaCollection;
  }

  public void setPrendaCollection(Collection<Prenda> prendaCollection) {
    this.prendaCollection = prendaCollection;
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
    if (!(object instanceof Servicio)) {
      return false;
    }
    Servicio other = (Servicio) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.Servicio[ id=" + id + " ]";
  }
  
}
