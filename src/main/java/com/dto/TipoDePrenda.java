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
import org.eclipse.persistence.annotations.AdditionalCriteria;

/**
 *
 * @author desti
 */
@Entity
@AdditionalCriteria("this.estado <> 'eliminado'")
@Table(name = "tipo_de_prenda")
@NamedQueries({
  @NamedQuery(name = "TipoDePrenda.findAll", query = "SELECT t FROM TipoDePrenda t"),
  @NamedQuery(name = "TipoDePrenda.findById", query = "SELECT t FROM TipoDePrenda t WHERE t.id = :id"),
  @NamedQuery(name = "TipoDePrenda.findByTipoPrenda", query = "SELECT t FROM TipoDePrenda t WHERE t.tipoPrenda = :tipoPrenda"),
  @NamedQuery(name = "TipoDePrenda.findByEstado", query = "SELECT t FROM TipoDePrenda t WHERE t.estado = :estado"),
  @NamedQuery(name = "TipoDePrenda.findByCreatedAt", query = "SELECT t FROM TipoDePrenda t WHERE t.createdAt = :createdAt"),
  @NamedQuery(name = "TipoDePrenda.findByUpdatedAt", query = "SELECT t FROM TipoDePrenda t WHERE t.updatedAt = :updatedAt")})
public class TipoDePrenda implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  @Size(max = 255)
  @Column(name = "tipo_prenda")
  private String tipoPrenda;
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
  @OneToMany(mappedBy = "tipoDePrendaId")
  private Collection<Prenda> prendaCollection;

  public TipoDePrenda() {
  }

  public TipoDePrenda(Long id) {
    this.id = id;
  }

  public TipoDePrenda(Long id, String estado) {
    this.id = id;
    this.estado = estado;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipoPrenda() {
    return tipoPrenda;
  }

  public void setTipoPrenda(String tipoPrenda) {
    this.tipoPrenda = tipoPrenda;
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
    if (!(object instanceof TipoDePrenda)) {
      return false;
    }
    TipoDePrenda other = (TipoDePrenda) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.TipoDePrenda[ id=" + id + " ]";
  }
  
}
