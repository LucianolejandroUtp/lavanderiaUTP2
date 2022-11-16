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
import org.eclipse.persistence.annotations.AdditionalCriteria;

/**
 *
 * @author desti
 */
@Entity
@AdditionalCriteria("this.estado <> 'eliminado'")
@Table(name = "detalle_factura")
@NamedQueries({
  @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d"),
  @NamedQuery(name = "DetalleFactura.findById", query = "SELECT d FROM DetalleFactura d WHERE d.id = :id"),
  @NamedQuery(name = "DetalleFactura.findByUniqueId", query = "SELECT d FROM DetalleFactura d WHERE d.uniqueId = :uniqueId"),
  @NamedQuery(name = "DetalleFactura.findByCantidad", query = "SELECT d FROM DetalleFactura d WHERE d.cantidad = :cantidad"),
  @NamedQuery(name = "DetalleFactura.findByPrecio", query = "SELECT d FROM DetalleFactura d WHERE d.precio = :precio"),
  @NamedQuery(name = "DetalleFactura.findBySubtotal", query = "SELECT d FROM DetalleFactura d WHERE d.subtotal = :subtotal"),
  @NamedQuery(name = "DetalleFactura.findByIgv", query = "SELECT d FROM DetalleFactura d WHERE d.igv = :igv"),
  @NamedQuery(name = "DetalleFactura.findByTotal", query = "SELECT d FROM DetalleFactura d WHERE d.total = :total"),
  @NamedQuery(name = "DetalleFactura.findByEstado", query = "SELECT d FROM DetalleFactura d WHERE d.estado = :estado"),
  @NamedQuery(name = "DetalleFactura.findByCreatedAt", query = "SELECT d FROM DetalleFactura d WHERE d.createdAt = :createdAt"),
  @NamedQuery(name = "DetalleFactura.findByUpdatedAt", query = "SELECT d FROM DetalleFactura d WHERE d.updatedAt = :updatedAt")})
public class DetalleFactura implements Serializable {

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
  @Column(name = "cantidad")
  private Integer cantidad;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "precio")
  private Double precio;
  @Column(name = "subtotal")
  private Double subtotal;
  @Column(name = "igv")
  private Double igv;
  @Column(name = "total")
  private Double total;
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
  @JoinColumn(name = "factura_id", referencedColumnName = "id")
  @ManyToOne
  private Factura facturaId;
  @JoinColumn(name = "prenda_id", referencedColumnName = "id")
  @ManyToOne
  private Prenda prendaId;
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  @ManyToOne
  private Servicio servicioId;

  public DetalleFactura() {
  }

  public DetalleFactura(Long id) {
    this.id = id;
  }

  public DetalleFactura(Long id, String uniqueId, String estado) {
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

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  public Double getIgv() {
    return igv;
  }

  public void setIgv(Double igv) {
    this.igv = igv;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
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

  public Factura getFacturaId() {
    return facturaId;
  }

  public void setFacturaId(Factura facturaId) {
    this.facturaId = facturaId;
  }

  public Prenda getPrendaId() {
    return prendaId;
  }

  public void setPrendaId(Prenda prendaId) {
    this.prendaId = prendaId;
  }

  public Servicio getServicioId() {
    return servicioId;
  }

  public void setServicioId(Servicio servicioId) {
    this.servicioId = servicioId;
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
    if (!(object instanceof DetalleFactura)) {
      return false;
    }
    DetalleFactura other = (DetalleFactura) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.DetalleFactura[ id=" + id + " ]";
  }
  
}
