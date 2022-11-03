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
import org.eclipse.persistence.annotations.AdditionalCriteria;

/**
 *
 * @author desti
 */
@Entity
@AdditionalCriteria("this.estado <> 'eliminado'")
@Table(name = "servicio")
@NamedQueries({
  @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
  @NamedQuery(name = "Servicio.findById", query = "SELECT s FROM Servicio s WHERE s.id = :id"),
  @NamedQuery(name = "Servicio.findByServicio", query = "SELECT s FROM Servicio s WHERE s.servicio = :servicio"),
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
  @Size(max = 255)
  @Column(name = "servicio")
  private String servicio;
  @Lob
  @Size(max = 65535)
  @Column(name = "descripcion")
  private String descripcion;
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
  @JoinColumn(name = "categoria_id", referencedColumnName = "id")
  @ManyToOne
  private Categoria categoriaId;
  @OneToMany(mappedBy = "servicioId")
  private Collection<DetalleFactura> detalleFacturaCollection;

  public Servicio() {
  }

  public Servicio(Long id) {
    this.id = id;
  }

  public Servicio(Long id, String estado) {
    this.id = id;
    this.estado = estado;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getServicio() {
    return servicio;
  }

  public void setServicio(String servicio) {
    this.servicio = servicio;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
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

  public Categoria getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Categoria categoriaId) {
    this.categoriaId = categoriaId;
  }

  public Collection<DetalleFactura> getDetalleFacturaCollection() {
    return detalleFacturaCollection;
  }

  public void setDetalleFacturaCollection(Collection<DetalleFactura> detalleFacturaCollection) {
    this.detalleFacturaCollection = detalleFacturaCollection;
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
