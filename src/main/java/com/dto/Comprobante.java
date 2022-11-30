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
@Table(name = "comprobante")
@NamedQueries({
  @NamedQuery(name = "Comprobante.findAll", query = "SELECT c FROM Comprobante c"),
  @NamedQuery(name = "Comprobante.findById", query = "SELECT c FROM Comprobante c WHERE c.id = :id"),
  @NamedQuery(name = "Comprobante.findByUniqueId", query = "SELECT c FROM Comprobante c WHERE c.uniqueId = :uniqueId"),
  @NamedQuery(name = "Comprobante.findByNumero", query = "SELECT c FROM Comprobante c WHERE c.numero = :numero"),
  @NamedQuery(name = "Comprobante.findBySerie", query = "SELECT c FROM Comprobante c WHERE c.serie = :serie"),
  @NamedQuery(name = "Comprobante.findByTipo", query = "SELECT c FROM Comprobante c WHERE c.tipo = :tipo"),
  @NamedQuery(name = "Comprobante.findByFecha", query = "SELECT c FROM Comprobante c WHERE c.fecha = :fecha"),
  @NamedQuery(name = "Comprobante.findByHora", query = "SELECT c FROM Comprobante c WHERE c.hora = :hora"),
  @NamedQuery(name = "Comprobante.findByEstado", query = "SELECT c FROM Comprobante c WHERE c.estado = :estado"),
  @NamedQuery(name = "Comprobante.findByCreatedAt", query = "SELECT c FROM Comprobante c WHERE c.createdAt = :createdAt"),
  @NamedQuery(name = "Comprobante.findByUpdatedAt", query = "SELECT c FROM Comprobante c WHERE c.updatedAt = :updatedAt")})
public class Comprobante implements Serializable {

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
  @Column(name = "numero")
  private String numero;
  @Size(max = 255)
  @Column(name = "serie")
  private String serie;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 7)
  @Column(name = "tipo")
  private String tipo;
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
  @OneToMany(mappedBy = "comprobanteId")
  private Collection<DetalleComprobante> detalleComprobanteCollection;
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  @ManyToOne
  private Persona personaId;

  public Comprobante() {
  }

  public Comprobante(Long id) {
    this.id = id;
  }

  public Comprobante(Long id, String uniqueId, String tipo, String estado) {
    this.id = id;
    this.uniqueId = uniqueId;
    this.tipo = tipo;
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

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getSerie() {
    return serie;
  }

  public void setSerie(String serie) {
    this.serie = serie;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
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

  public Collection<DetalleComprobante> getDetalleComprobanteCollection() {
    return detalleComprobanteCollection;
  }

  public void setDetalleComprobanteCollection(Collection<DetalleComprobante> detalleComprobanteCollection) {
    this.detalleComprobanteCollection = detalleComprobanteCollection;
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
    if (!(object instanceof Comprobante)) {
      return false;
    }
    Comprobante other = (Comprobante) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.Comprobante[ id=" + id + " ]";
  }
  
}
