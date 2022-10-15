/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author desti
 */
@Entity
@Table(name = "servicios")
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s"),
    @NamedQuery(name = "Servicios.findByIdServicio", query = "SELECT s FROM Servicios s WHERE s.idServicio = :idServicio"),
    @NamedQuery(name = "Servicios.findByTipodeServicio", query = "SELECT s FROM Servicios s WHERE s.tipodeServicio = :tipodeServicio"),
    @NamedQuery(name = "Servicios.findByDetallesPrendas", query = "SELECT s FROM Servicios s WHERE s.detallesPrendas = :detallesPrendas"),
    @NamedQuery(name = "Servicios.findByCantidad", query = "SELECT s FROM Servicios s WHERE s.cantidad = :cantidad")})
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id_Servicio")
    private Integer idServicio;
    @Basic(optional = false)
    @Column(name = "Tipo_de_Servicio")
    private String tipodeServicio;
    @Basic(optional = false)
    @Column(name = "DetallesPrendas")
    private String detallesPrendas;
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private int cantidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicio")
    private Collection<Categoria> categoriaCollection;

    public Servicios() {
    }

    public Servicios(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Servicios(Integer idServicio, String tipodeServicio, String detallesPrendas, int cantidad) {
        this.idServicio = idServicio;
        this.tipodeServicio = tipodeServicio;
        this.detallesPrendas = detallesPrendas;
        this.cantidad = cantidad;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getTipodeServicio() {
        return tipodeServicio;
    }

    public void setTipodeServicio(String tipodeServicio) {
        this.tipodeServicio = tipodeServicio;
    }

    public String getDetallesPrendas() {
        return detallesPrendas;
    }

    public void setDetallesPrendas(String detallesPrendas) {
        this.detallesPrendas = detallesPrendas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Collection<Categoria> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<Categoria> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dto.Servicios[ idServicio=" + idServicio + " ]";
    }
    
}
