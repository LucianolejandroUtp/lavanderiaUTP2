/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dto.TipoPersona;
import com.dto.Comprobante;
import java.util.ArrayList;
import java.util.Collection;
import com.dto.Direccion;
import com.dto.Vehiculo;
import com.dto.Prenda;
import com.dto.Telefono;
import com.dto.Cita;
import com.dto.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class PersonaJpaController implements Serializable {

  public PersonaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Persona persona) {
    if (persona.getComprobanteCollection() == null) {
      persona.setComprobanteCollection(new ArrayList<Comprobante>());
    }
    if (persona.getDireccionCollection() == null) {
      persona.setDireccionCollection(new ArrayList<Direccion>());
    }
    if (persona.getVehiculoCollection() == null) {
      persona.setVehiculoCollection(new ArrayList<Vehiculo>());
    }
    if (persona.getPrendaCollection() == null) {
      persona.setPrendaCollection(new ArrayList<Prenda>());
    }
    if (persona.getPrendaCollection1() == null) {
      persona.setPrendaCollection1(new ArrayList<Prenda>());
    }
    if (persona.getTelefonoCollection() == null) {
      persona.setTelefonoCollection(new ArrayList<Telefono>());
    }
    if (persona.getCitaCollection() == null) {
      persona.setCitaCollection(new ArrayList<Cita>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      TipoPersona tipoPersonaId = persona.getTipoPersonaId();
      if (tipoPersonaId != null) {
        tipoPersonaId = em.getReference(tipoPersonaId.getClass(), tipoPersonaId.getId());
        persona.setTipoPersonaId(tipoPersonaId);
      }
      Collection<Comprobante> attachedComprobanteCollection = new ArrayList<Comprobante>();
      for (Comprobante comprobanteCollectionComprobanteToAttach : persona.getComprobanteCollection()) {
        comprobanteCollectionComprobanteToAttach = em.getReference(comprobanteCollectionComprobanteToAttach.getClass(), comprobanteCollectionComprobanteToAttach.getId());
        attachedComprobanteCollection.add(comprobanteCollectionComprobanteToAttach);
      }
      persona.setComprobanteCollection(attachedComprobanteCollection);
      Collection<Direccion> attachedDireccionCollection = new ArrayList<Direccion>();
      for (Direccion direccionCollectionDireccionToAttach : persona.getDireccionCollection()) {
        direccionCollectionDireccionToAttach = em.getReference(direccionCollectionDireccionToAttach.getClass(), direccionCollectionDireccionToAttach.getId());
        attachedDireccionCollection.add(direccionCollectionDireccionToAttach);
      }
      persona.setDireccionCollection(attachedDireccionCollection);
      Collection<Vehiculo> attachedVehiculoCollection = new ArrayList<Vehiculo>();
      for (Vehiculo vehiculoCollectionVehiculoToAttach : persona.getVehiculoCollection()) {
        vehiculoCollectionVehiculoToAttach = em.getReference(vehiculoCollectionVehiculoToAttach.getClass(), vehiculoCollectionVehiculoToAttach.getId());
        attachedVehiculoCollection.add(vehiculoCollectionVehiculoToAttach);
      }
      persona.setVehiculoCollection(attachedVehiculoCollection);
      Collection<Prenda> attachedPrendaCollection = new ArrayList<Prenda>();
      for (Prenda prendaCollectionPrendaToAttach : persona.getPrendaCollection()) {
        prendaCollectionPrendaToAttach = em.getReference(prendaCollectionPrendaToAttach.getClass(), prendaCollectionPrendaToAttach.getId());
        attachedPrendaCollection.add(prendaCollectionPrendaToAttach);
      }
      persona.setPrendaCollection(attachedPrendaCollection);
      Collection<Prenda> attachedPrendaCollection1 = new ArrayList<Prenda>();
      for (Prenda prendaCollection1PrendaToAttach : persona.getPrendaCollection1()) {
        prendaCollection1PrendaToAttach = em.getReference(prendaCollection1PrendaToAttach.getClass(), prendaCollection1PrendaToAttach.getId());
        attachedPrendaCollection1.add(prendaCollection1PrendaToAttach);
      }
      persona.setPrendaCollection1(attachedPrendaCollection1);
      Collection<Telefono> attachedTelefonoCollection = new ArrayList<Telefono>();
      for (Telefono telefonoCollectionTelefonoToAttach : persona.getTelefonoCollection()) {
        telefonoCollectionTelefonoToAttach = em.getReference(telefonoCollectionTelefonoToAttach.getClass(), telefonoCollectionTelefonoToAttach.getId());
        attachedTelefonoCollection.add(telefonoCollectionTelefonoToAttach);
      }
      persona.setTelefonoCollection(attachedTelefonoCollection);
      Collection<Cita> attachedCitaCollection = new ArrayList<Cita>();
      for (Cita citaCollectionCitaToAttach : persona.getCitaCollection()) {
        citaCollectionCitaToAttach = em.getReference(citaCollectionCitaToAttach.getClass(), citaCollectionCitaToAttach.getId());
        attachedCitaCollection.add(citaCollectionCitaToAttach);
      }
      persona.setCitaCollection(attachedCitaCollection);
      em.persist(persona);
      if (tipoPersonaId != null) {
        tipoPersonaId.getPersonaCollection().add(persona);
        tipoPersonaId = em.merge(tipoPersonaId);
      }
      for (Comprobante comprobanteCollectionComprobante : persona.getComprobanteCollection()) {
        Persona oldPersonaIdOfComprobanteCollectionComprobante = comprobanteCollectionComprobante.getPersonaId();
        comprobanteCollectionComprobante.setPersonaId(persona);
        comprobanteCollectionComprobante = em.merge(comprobanteCollectionComprobante);
        if (oldPersonaIdOfComprobanteCollectionComprobante != null) {
          oldPersonaIdOfComprobanteCollectionComprobante.getComprobanteCollection().remove(comprobanteCollectionComprobante);
          oldPersonaIdOfComprobanteCollectionComprobante = em.merge(oldPersonaIdOfComprobanteCollectionComprobante);
        }
      }
      for (Direccion direccionCollectionDireccion : persona.getDireccionCollection()) {
        Persona oldPersonaIdOfDireccionCollectionDireccion = direccionCollectionDireccion.getPersonaId();
        direccionCollectionDireccion.setPersonaId(persona);
        direccionCollectionDireccion = em.merge(direccionCollectionDireccion);
        if (oldPersonaIdOfDireccionCollectionDireccion != null) {
          oldPersonaIdOfDireccionCollectionDireccion.getDireccionCollection().remove(direccionCollectionDireccion);
          oldPersonaIdOfDireccionCollectionDireccion = em.merge(oldPersonaIdOfDireccionCollectionDireccion);
        }
      }
      for (Vehiculo vehiculoCollectionVehiculo : persona.getVehiculoCollection()) {
        Persona oldPersonaIdOfVehiculoCollectionVehiculo = vehiculoCollectionVehiculo.getPersonaId();
        vehiculoCollectionVehiculo.setPersonaId(persona);
        vehiculoCollectionVehiculo = em.merge(vehiculoCollectionVehiculo);
        if (oldPersonaIdOfVehiculoCollectionVehiculo != null) {
          oldPersonaIdOfVehiculoCollectionVehiculo.getVehiculoCollection().remove(vehiculoCollectionVehiculo);
          oldPersonaIdOfVehiculoCollectionVehiculo = em.merge(oldPersonaIdOfVehiculoCollectionVehiculo);
        }
      }
      for (Prenda prendaCollectionPrenda : persona.getPrendaCollection()) {
        Persona oldPersonaIdClienteOfPrendaCollectionPrenda = prendaCollectionPrenda.getPersonaIdCliente();
        prendaCollectionPrenda.setPersonaIdCliente(persona);
        prendaCollectionPrenda = em.merge(prendaCollectionPrenda);
        if (oldPersonaIdClienteOfPrendaCollectionPrenda != null) {
          oldPersonaIdClienteOfPrendaCollectionPrenda.getPrendaCollection().remove(prendaCollectionPrenda);
          oldPersonaIdClienteOfPrendaCollectionPrenda = em.merge(oldPersonaIdClienteOfPrendaCollectionPrenda);
        }
      }
      for (Prenda prendaCollection1Prenda : persona.getPrendaCollection1()) {
        Persona oldPersonaIdEmpleadoOfPrendaCollection1Prenda = prendaCollection1Prenda.getPersonaIdEmpleado();
        prendaCollection1Prenda.setPersonaIdEmpleado(persona);
        prendaCollection1Prenda = em.merge(prendaCollection1Prenda);
        if (oldPersonaIdEmpleadoOfPrendaCollection1Prenda != null) {
          oldPersonaIdEmpleadoOfPrendaCollection1Prenda.getPrendaCollection1().remove(prendaCollection1Prenda);
          oldPersonaIdEmpleadoOfPrendaCollection1Prenda = em.merge(oldPersonaIdEmpleadoOfPrendaCollection1Prenda);
        }
      }
      for (Telefono telefonoCollectionTelefono : persona.getTelefonoCollection()) {
        Persona oldPersonaIdOfTelefonoCollectionTelefono = telefonoCollectionTelefono.getPersonaId();
        telefonoCollectionTelefono.setPersonaId(persona);
        telefonoCollectionTelefono = em.merge(telefonoCollectionTelefono);
        if (oldPersonaIdOfTelefonoCollectionTelefono != null) {
          oldPersonaIdOfTelefonoCollectionTelefono.getTelefonoCollection().remove(telefonoCollectionTelefono);
          oldPersonaIdOfTelefonoCollectionTelefono = em.merge(oldPersonaIdOfTelefonoCollectionTelefono);
        }
      }
      for (Cita citaCollectionCita : persona.getCitaCollection()) {
        Persona oldPersonaIdOfCitaCollectionCita = citaCollectionCita.getPersonaId();
        citaCollectionCita.setPersonaId(persona);
        citaCollectionCita = em.merge(citaCollectionCita);
        if (oldPersonaIdOfCitaCollectionCita != null) {
          oldPersonaIdOfCitaCollectionCita.getCitaCollection().remove(citaCollectionCita);
          oldPersonaIdOfCitaCollectionCita = em.merge(oldPersonaIdOfCitaCollectionCita);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Persona persona) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Persona persistentPersona = em.find(Persona.class, persona.getId());
      TipoPersona tipoPersonaIdOld = persistentPersona.getTipoPersonaId();
      TipoPersona tipoPersonaIdNew = persona.getTipoPersonaId();
      Collection<Comprobante> comprobanteCollectionOld = persistentPersona.getComprobanteCollection();
      Collection<Comprobante> comprobanteCollectionNew = persona.getComprobanteCollection();
      Collection<Direccion> direccionCollectionOld = persistentPersona.getDireccionCollection();
      Collection<Direccion> direccionCollectionNew = persona.getDireccionCollection();
      Collection<Vehiculo> vehiculoCollectionOld = persistentPersona.getVehiculoCollection();
      Collection<Vehiculo> vehiculoCollectionNew = persona.getVehiculoCollection();
      Collection<Prenda> prendaCollectionOld = persistentPersona.getPrendaCollection();
      Collection<Prenda> prendaCollectionNew = persona.getPrendaCollection();
      Collection<Prenda> prendaCollection1Old = persistentPersona.getPrendaCollection1();
      Collection<Prenda> prendaCollection1New = persona.getPrendaCollection1();
      Collection<Telefono> telefonoCollectionOld = persistentPersona.getTelefonoCollection();
      Collection<Telefono> telefonoCollectionNew = persona.getTelefonoCollection();
      Collection<Cita> citaCollectionOld = persistentPersona.getCitaCollection();
      Collection<Cita> citaCollectionNew = persona.getCitaCollection();
      if (tipoPersonaIdNew != null) {
        tipoPersonaIdNew = em.getReference(tipoPersonaIdNew.getClass(), tipoPersonaIdNew.getId());
        persona.setTipoPersonaId(tipoPersonaIdNew);
      }
      Collection<Comprobante> attachedComprobanteCollectionNew = new ArrayList<Comprobante>();
      for (Comprobante comprobanteCollectionNewComprobanteToAttach : comprobanteCollectionNew) {
        comprobanteCollectionNewComprobanteToAttach = em.getReference(comprobanteCollectionNewComprobanteToAttach.getClass(), comprobanteCollectionNewComprobanteToAttach.getId());
        attachedComprobanteCollectionNew.add(comprobanteCollectionNewComprobanteToAttach);
      }
      comprobanteCollectionNew = attachedComprobanteCollectionNew;
      persona.setComprobanteCollection(comprobanteCollectionNew);
      Collection<Direccion> attachedDireccionCollectionNew = new ArrayList<Direccion>();
      for (Direccion direccionCollectionNewDireccionToAttach : direccionCollectionNew) {
        direccionCollectionNewDireccionToAttach = em.getReference(direccionCollectionNewDireccionToAttach.getClass(), direccionCollectionNewDireccionToAttach.getId());
        attachedDireccionCollectionNew.add(direccionCollectionNewDireccionToAttach);
      }
      direccionCollectionNew = attachedDireccionCollectionNew;
      persona.setDireccionCollection(direccionCollectionNew);
      Collection<Vehiculo> attachedVehiculoCollectionNew = new ArrayList<Vehiculo>();
      for (Vehiculo vehiculoCollectionNewVehiculoToAttach : vehiculoCollectionNew) {
        vehiculoCollectionNewVehiculoToAttach = em.getReference(vehiculoCollectionNewVehiculoToAttach.getClass(), vehiculoCollectionNewVehiculoToAttach.getId());
        attachedVehiculoCollectionNew.add(vehiculoCollectionNewVehiculoToAttach);
      }
      vehiculoCollectionNew = attachedVehiculoCollectionNew;
      persona.setVehiculoCollection(vehiculoCollectionNew);
      Collection<Prenda> attachedPrendaCollectionNew = new ArrayList<Prenda>();
      for (Prenda prendaCollectionNewPrendaToAttach : prendaCollectionNew) {
        prendaCollectionNewPrendaToAttach = em.getReference(prendaCollectionNewPrendaToAttach.getClass(), prendaCollectionNewPrendaToAttach.getId());
        attachedPrendaCollectionNew.add(prendaCollectionNewPrendaToAttach);
      }
      prendaCollectionNew = attachedPrendaCollectionNew;
      persona.setPrendaCollection(prendaCollectionNew);
      Collection<Prenda> attachedPrendaCollection1New = new ArrayList<Prenda>();
      for (Prenda prendaCollection1NewPrendaToAttach : prendaCollection1New) {
        prendaCollection1NewPrendaToAttach = em.getReference(prendaCollection1NewPrendaToAttach.getClass(), prendaCollection1NewPrendaToAttach.getId());
        attachedPrendaCollection1New.add(prendaCollection1NewPrendaToAttach);
      }
      prendaCollection1New = attachedPrendaCollection1New;
      persona.setPrendaCollection1(prendaCollection1New);
      Collection<Telefono> attachedTelefonoCollectionNew = new ArrayList<Telefono>();
      for (Telefono telefonoCollectionNewTelefonoToAttach : telefonoCollectionNew) {
        telefonoCollectionNewTelefonoToAttach = em.getReference(telefonoCollectionNewTelefonoToAttach.getClass(), telefonoCollectionNewTelefonoToAttach.getId());
        attachedTelefonoCollectionNew.add(telefonoCollectionNewTelefonoToAttach);
      }
      telefonoCollectionNew = attachedTelefonoCollectionNew;
      persona.setTelefonoCollection(telefonoCollectionNew);
      Collection<Cita> attachedCitaCollectionNew = new ArrayList<Cita>();
      for (Cita citaCollectionNewCitaToAttach : citaCollectionNew) {
        citaCollectionNewCitaToAttach = em.getReference(citaCollectionNewCitaToAttach.getClass(), citaCollectionNewCitaToAttach.getId());
        attachedCitaCollectionNew.add(citaCollectionNewCitaToAttach);
      }
      citaCollectionNew = attachedCitaCollectionNew;
      persona.setCitaCollection(citaCollectionNew);
      persona = em.merge(persona);
      if (tipoPersonaIdOld != null && !tipoPersonaIdOld.equals(tipoPersonaIdNew)) {
        tipoPersonaIdOld.getPersonaCollection().remove(persona);
        tipoPersonaIdOld = em.merge(tipoPersonaIdOld);
      }
      if (tipoPersonaIdNew != null && !tipoPersonaIdNew.equals(tipoPersonaIdOld)) {
        tipoPersonaIdNew.getPersonaCollection().add(persona);
        tipoPersonaIdNew = em.merge(tipoPersonaIdNew);
      }
      for (Comprobante comprobanteCollectionOldComprobante : comprobanteCollectionOld) {
        if (!comprobanteCollectionNew.contains(comprobanteCollectionOldComprobante)) {
          comprobanteCollectionOldComprobante.setPersonaId(null);
          comprobanteCollectionOldComprobante = em.merge(comprobanteCollectionOldComprobante);
        }
      }
      for (Comprobante comprobanteCollectionNewComprobante : comprobanteCollectionNew) {
        if (!comprobanteCollectionOld.contains(comprobanteCollectionNewComprobante)) {
          Persona oldPersonaIdOfComprobanteCollectionNewComprobante = comprobanteCollectionNewComprobante.getPersonaId();
          comprobanteCollectionNewComprobante.setPersonaId(persona);
          comprobanteCollectionNewComprobante = em.merge(comprobanteCollectionNewComprobante);
          if (oldPersonaIdOfComprobanteCollectionNewComprobante != null && !oldPersonaIdOfComprobanteCollectionNewComprobante.equals(persona)) {
            oldPersonaIdOfComprobanteCollectionNewComprobante.getComprobanteCollection().remove(comprobanteCollectionNewComprobante);
            oldPersonaIdOfComprobanteCollectionNewComprobante = em.merge(oldPersonaIdOfComprobanteCollectionNewComprobante);
          }
        }
      }
      for (Direccion direccionCollectionOldDireccion : direccionCollectionOld) {
        if (!direccionCollectionNew.contains(direccionCollectionOldDireccion)) {
          direccionCollectionOldDireccion.setPersonaId(null);
          direccionCollectionOldDireccion = em.merge(direccionCollectionOldDireccion);
        }
      }
      for (Direccion direccionCollectionNewDireccion : direccionCollectionNew) {
        if (!direccionCollectionOld.contains(direccionCollectionNewDireccion)) {
          Persona oldPersonaIdOfDireccionCollectionNewDireccion = direccionCollectionNewDireccion.getPersonaId();
          direccionCollectionNewDireccion.setPersonaId(persona);
          direccionCollectionNewDireccion = em.merge(direccionCollectionNewDireccion);
          if (oldPersonaIdOfDireccionCollectionNewDireccion != null && !oldPersonaIdOfDireccionCollectionNewDireccion.equals(persona)) {
            oldPersonaIdOfDireccionCollectionNewDireccion.getDireccionCollection().remove(direccionCollectionNewDireccion);
            oldPersonaIdOfDireccionCollectionNewDireccion = em.merge(oldPersonaIdOfDireccionCollectionNewDireccion);
          }
        }
      }
      for (Vehiculo vehiculoCollectionOldVehiculo : vehiculoCollectionOld) {
        if (!vehiculoCollectionNew.contains(vehiculoCollectionOldVehiculo)) {
          vehiculoCollectionOldVehiculo.setPersonaId(null);
          vehiculoCollectionOldVehiculo = em.merge(vehiculoCollectionOldVehiculo);
        }
      }
      for (Vehiculo vehiculoCollectionNewVehiculo : vehiculoCollectionNew) {
        if (!vehiculoCollectionOld.contains(vehiculoCollectionNewVehiculo)) {
          Persona oldPersonaIdOfVehiculoCollectionNewVehiculo = vehiculoCollectionNewVehiculo.getPersonaId();
          vehiculoCollectionNewVehiculo.setPersonaId(persona);
          vehiculoCollectionNewVehiculo = em.merge(vehiculoCollectionNewVehiculo);
          if (oldPersonaIdOfVehiculoCollectionNewVehiculo != null && !oldPersonaIdOfVehiculoCollectionNewVehiculo.equals(persona)) {
            oldPersonaIdOfVehiculoCollectionNewVehiculo.getVehiculoCollection().remove(vehiculoCollectionNewVehiculo);
            oldPersonaIdOfVehiculoCollectionNewVehiculo = em.merge(oldPersonaIdOfVehiculoCollectionNewVehiculo);
          }
        }
      }
      for (Prenda prendaCollectionOldPrenda : prendaCollectionOld) {
        if (!prendaCollectionNew.contains(prendaCollectionOldPrenda)) {
          prendaCollectionOldPrenda.setPersonaIdCliente(null);
          prendaCollectionOldPrenda = em.merge(prendaCollectionOldPrenda);
        }
      }
      for (Prenda prendaCollectionNewPrenda : prendaCollectionNew) {
        if (!prendaCollectionOld.contains(prendaCollectionNewPrenda)) {
          Persona oldPersonaIdClienteOfPrendaCollectionNewPrenda = prendaCollectionNewPrenda.getPersonaIdCliente();
          prendaCollectionNewPrenda.setPersonaIdCliente(persona);
          prendaCollectionNewPrenda = em.merge(prendaCollectionNewPrenda);
          if (oldPersonaIdClienteOfPrendaCollectionNewPrenda != null && !oldPersonaIdClienteOfPrendaCollectionNewPrenda.equals(persona)) {
            oldPersonaIdClienteOfPrendaCollectionNewPrenda.getPrendaCollection().remove(prendaCollectionNewPrenda);
            oldPersonaIdClienteOfPrendaCollectionNewPrenda = em.merge(oldPersonaIdClienteOfPrendaCollectionNewPrenda);
          }
        }
      }
      for (Prenda prendaCollection1OldPrenda : prendaCollection1Old) {
        if (!prendaCollection1New.contains(prendaCollection1OldPrenda)) {
          prendaCollection1OldPrenda.setPersonaIdEmpleado(null);
          prendaCollection1OldPrenda = em.merge(prendaCollection1OldPrenda);
        }
      }
      for (Prenda prendaCollection1NewPrenda : prendaCollection1New) {
        if (!prendaCollection1Old.contains(prendaCollection1NewPrenda)) {
          Persona oldPersonaIdEmpleadoOfPrendaCollection1NewPrenda = prendaCollection1NewPrenda.getPersonaIdEmpleado();
          prendaCollection1NewPrenda.setPersonaIdEmpleado(persona);
          prendaCollection1NewPrenda = em.merge(prendaCollection1NewPrenda);
          if (oldPersonaIdEmpleadoOfPrendaCollection1NewPrenda != null && !oldPersonaIdEmpleadoOfPrendaCollection1NewPrenda.equals(persona)) {
            oldPersonaIdEmpleadoOfPrendaCollection1NewPrenda.getPrendaCollection1().remove(prendaCollection1NewPrenda);
            oldPersonaIdEmpleadoOfPrendaCollection1NewPrenda = em.merge(oldPersonaIdEmpleadoOfPrendaCollection1NewPrenda);
          }
        }
      }
      for (Telefono telefonoCollectionOldTelefono : telefonoCollectionOld) {
        if (!telefonoCollectionNew.contains(telefonoCollectionOldTelefono)) {
          telefonoCollectionOldTelefono.setPersonaId(null);
          telefonoCollectionOldTelefono = em.merge(telefonoCollectionOldTelefono);
        }
      }
      for (Telefono telefonoCollectionNewTelefono : telefonoCollectionNew) {
        if (!telefonoCollectionOld.contains(telefonoCollectionNewTelefono)) {
          Persona oldPersonaIdOfTelefonoCollectionNewTelefono = telefonoCollectionNewTelefono.getPersonaId();
          telefonoCollectionNewTelefono.setPersonaId(persona);
          telefonoCollectionNewTelefono = em.merge(telefonoCollectionNewTelefono);
          if (oldPersonaIdOfTelefonoCollectionNewTelefono != null && !oldPersonaIdOfTelefonoCollectionNewTelefono.equals(persona)) {
            oldPersonaIdOfTelefonoCollectionNewTelefono.getTelefonoCollection().remove(telefonoCollectionNewTelefono);
            oldPersonaIdOfTelefonoCollectionNewTelefono = em.merge(oldPersonaIdOfTelefonoCollectionNewTelefono);
          }
        }
      }
      for (Cita citaCollectionOldCita : citaCollectionOld) {
        if (!citaCollectionNew.contains(citaCollectionOldCita)) {
          citaCollectionOldCita.setPersonaId(null);
          citaCollectionOldCita = em.merge(citaCollectionOldCita);
        }
      }
      for (Cita citaCollectionNewCita : citaCollectionNew) {
        if (!citaCollectionOld.contains(citaCollectionNewCita)) {
          Persona oldPersonaIdOfCitaCollectionNewCita = citaCollectionNewCita.getPersonaId();
          citaCollectionNewCita.setPersonaId(persona);
          citaCollectionNewCita = em.merge(citaCollectionNewCita);
          if (oldPersonaIdOfCitaCollectionNewCita != null && !oldPersonaIdOfCitaCollectionNewCita.equals(persona)) {
            oldPersonaIdOfCitaCollectionNewCita.getCitaCollection().remove(citaCollectionNewCita);
            oldPersonaIdOfCitaCollectionNewCita = em.merge(oldPersonaIdOfCitaCollectionNewCita);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = persona.getId();
        if (findPersona(id) == null) {
          throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Long id) throws NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Persona persona;
      try {
        persona = em.getReference(Persona.class, id);
        persona.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
      }
      TipoPersona tipoPersonaId = persona.getTipoPersonaId();
      if (tipoPersonaId != null) {
        tipoPersonaId.getPersonaCollection().remove(persona);
        tipoPersonaId = em.merge(tipoPersonaId);
      }
      Collection<Comprobante> comprobanteCollection = persona.getComprobanteCollection();
      for (Comprobante comprobanteCollectionComprobante : comprobanteCollection) {
        comprobanteCollectionComprobante.setPersonaId(null);
        comprobanteCollectionComprobante = em.merge(comprobanteCollectionComprobante);
      }
      Collection<Direccion> direccionCollection = persona.getDireccionCollection();
      for (Direccion direccionCollectionDireccion : direccionCollection) {
        direccionCollectionDireccion.setPersonaId(null);
        direccionCollectionDireccion = em.merge(direccionCollectionDireccion);
      }
      Collection<Vehiculo> vehiculoCollection = persona.getVehiculoCollection();
      for (Vehiculo vehiculoCollectionVehiculo : vehiculoCollection) {
        vehiculoCollectionVehiculo.setPersonaId(null);
        vehiculoCollectionVehiculo = em.merge(vehiculoCollectionVehiculo);
      }
      Collection<Prenda> prendaCollection = persona.getPrendaCollection();
      for (Prenda prendaCollectionPrenda : prendaCollection) {
        prendaCollectionPrenda.setPersonaIdCliente(null);
        prendaCollectionPrenda = em.merge(prendaCollectionPrenda);
      }
      Collection<Prenda> prendaCollection1 = persona.getPrendaCollection1();
      for (Prenda prendaCollection1Prenda : prendaCollection1) {
        prendaCollection1Prenda.setPersonaIdEmpleado(null);
        prendaCollection1Prenda = em.merge(prendaCollection1Prenda);
      }
      Collection<Telefono> telefonoCollection = persona.getTelefonoCollection();
      for (Telefono telefonoCollectionTelefono : telefonoCollection) {
        telefonoCollectionTelefono.setPersonaId(null);
        telefonoCollectionTelefono = em.merge(telefonoCollectionTelefono);
      }
      Collection<Cita> citaCollection = persona.getCitaCollection();
      for (Cita citaCollectionCita : citaCollection) {
        citaCollectionCita.setPersonaId(null);
        citaCollectionCita = em.merge(citaCollectionCita);
      }
      em.remove(persona);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Persona> findPersonaEntities() {
    return findPersonaEntities(true, -1, -1);
  }

  public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
    return findPersonaEntities(false, maxResults, firstResult);
  }

  private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Persona.class));
      Query q = em.createQuery(cq);
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Persona findPersona(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Persona.class, id);
    } finally {
      em.close();
    }
  }

  public int getPersonaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Persona> rt = cq.from(Persona.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
