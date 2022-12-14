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
@Table(name = "vehiculo")
@NamedQueries({
  @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
  @NamedQuery(name = "Vehiculo.findById", query = "SELECT v FROM Vehiculo v WHERE v.id = :id"),
  @NamedQuery(name = "Vehiculo.findByUniqueId", query = "SELECT v FROM Vehiculo v WHERE v.uniqueId = :uniqueId"),
  @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa"),
  @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca"),
  @NamedQuery(name = "Vehiculo.findByModelo", query = "SELECT v FROM Vehiculo v WHERE v.modelo = :modelo"),
  @NamedQuery(name = "Vehiculo.findByColor", query = "SELECT v FROM Vehiculo v WHERE v.color = :color"),
  @NamedQuery(name = "Vehiculo.findByEstado", query = "SELECT v FROM Vehiculo v WHERE v.estado = :estado"),
  @NamedQuery(name = "Vehiculo.findByCreatedAt", query = "SELECT v FROM Vehiculo v WHERE v.createdAt = :createdAt"),
  @NamedQuery(name = "Vehiculo.findByUpdatedAt", query = "SELECT v FROM Vehiculo v WHERE v.updatedAt = :updatedAt")})
public class Vehiculo implements Serializable {

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
  @Column(name = "placa")
  private String placa;
  @Size(max = 255)
  @Column(name = "marca")
  private String marca;
  @Size(max = 255)
  @Column(name = "modelo")
  private String modelo;
  @Size(max = 255)
  @Column(name = "color")
  private String color;
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
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Persona personaId;
  @OneToMany(mappedBy = "vehiculoId", fetch = FetchType.LAZY)
  private Collection<Cita> citaCollection;

  public Vehiculo() {
  }

  public Vehiculo(Long id) {
    this.id = id;
  }

  public Vehiculo(Long id, String uniqueId, String estado) {
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

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
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

  public Persona getPersonaId() {
    return personaId;
  }

  public void setPersonaId(Persona personaId) {
    this.personaId = personaId;
  }

  public Collection<Cita> getCitaCollection() {
    return citaCollection;
  }

  public void setCitaCollection(Collection<Cita> citaCollection) {
    this.citaCollection = citaCollection;
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
    if (!(object instanceof Vehiculo)) {
      return false;
    }
    Vehiculo other = (Vehiculo) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.dto.Vehiculo[ id=" + id + " ]";
  }
  
}
