/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dto;

import java.io.Serializable;
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

/**
 *
 * @author desti
 */
@Entity
@Table(name = "cliente_servicio")
@NamedQueries({
    @NamedQuery(name = "ClienteServicio.findAll", query = "SELECT c FROM ClienteServicio c"),
    @NamedQuery(name = "ClienteServicio.findByIdClienteServicio", query = "SELECT c FROM ClienteServicio c WHERE c.idClienteServicio = :idClienteServicio")})
public class ClienteServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_ClienteServicio")
    private Integer idClienteServicio;
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne
    private Cliente idCliente;
    @JoinColumn(name = "id_Factura", referencedColumnName = "id_Factura")
    @ManyToOne(optional = false)
    private Factura idFactura;

    public ClienteServicio() {
    }

    public ClienteServicio(Integer idClienteServicio) {
        this.idClienteServicio = idClienteServicio;
    }

    public Integer getIdClienteServicio() {
        return idClienteServicio;
    }

    public void setIdClienteServicio(Integer idClienteServicio) {
        this.idClienteServicio = idClienteServicio;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClienteServicio != null ? idClienteServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteServicio)) {
            return false;
        }
        ClienteServicio other = (ClienteServicio) object;
        if ((this.idClienteServicio == null && other.idClienteServicio != null) || (this.idClienteServicio != null && !this.idClienteServicio.equals(other.idClienteServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dto.ClienteServicio[ idClienteServicio=" + idClienteServicio + " ]";
    }
    
}
