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
import com.dto.Vehiculo;
import java.util.ArrayList;
import java.util.Collection;
import com.dto.DireccionPersona;
import com.dto.Factura;
import com.dto.Prenda;
import com.dto.Telefono;
import com.dto.Cita;
import com.dto.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desti
 */
public class PersonaJpaController implements Serializable {

  public PersonaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public PersonaJpaController() {
    emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
  }

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Persona persona) {
    if (persona.getVehiculoCollection() == null) {
      persona.setVehiculoCollection(new ArrayList<Vehiculo>());
    }
    if (persona.getDireccionPersonaCollection() == null) {
      persona.setDireccionPersonaCollection(new ArrayList<DireccionPersona>());
    }
    if (persona.getFacturaCollection() == null) {
      persona.setFacturaCollection(new ArrayList<Factura>());
    }
    if (persona.getPrendaCollection() == null) {
      persona.setPrendaCollection(new ArrayList<Prenda>());
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
      Collection<Vehiculo> attachedVehiculoCollection = new ArrayList<Vehiculo>();
      for (Vehiculo vehiculoCollectionVehiculoToAttach : persona.getVehiculoCollection()) {
        vehiculoCollectionVehiculoToAttach = em.getReference(vehiculoCollectionVehiculoToAttach.getClass(), vehiculoCollectionVehiculoToAttach.getId());
        attachedVehiculoCollection.add(vehiculoCollectionVehiculoToAttach);
      }
      persona.setVehiculoCollection(attachedVehiculoCollection);
      Collection<DireccionPersona> attachedDireccionPersonaCollection = new ArrayList<DireccionPersona>();
      for (DireccionPersona direccionPersonaCollectionDireccionPersonaToAttach : persona.getDireccionPersonaCollection()) {
        direccionPersonaCollectionDireccionPersonaToAttach = em.getReference(direccionPersonaCollectionDireccionPersonaToAttach.getClass(), direccionPersonaCollectionDireccionPersonaToAttach.getId());
        attachedDireccionPersonaCollection.add(direccionPersonaCollectionDireccionPersonaToAttach);
      }
      persona.setDireccionPersonaCollection(attachedDireccionPersonaCollection);
      Collection<Factura> attachedFacturaCollection = new ArrayList<Factura>();
      for (Factura facturaCollectionFacturaToAttach : persona.getFacturaCollection()) {
        facturaCollectionFacturaToAttach = em.getReference(facturaCollectionFacturaToAttach.getClass(), facturaCollectionFacturaToAttach.getId());
        attachedFacturaCollection.add(facturaCollectionFacturaToAttach);
      }
      persona.setFacturaCollection(attachedFacturaCollection);
      Collection<Prenda> attachedPrendaCollection = new ArrayList<Prenda>();
      for (Prenda prendaCollectionPrendaToAttach : persona.getPrendaCollection()) {
        prendaCollectionPrendaToAttach = em.getReference(prendaCollectionPrendaToAttach.getClass(), prendaCollectionPrendaToAttach.getId());
        attachedPrendaCollection.add(prendaCollectionPrendaToAttach);
      }
      persona.setPrendaCollection(attachedPrendaCollection);
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
      for (Vehiculo vehiculoCollectionVehiculo : persona.getVehiculoCollection()) {
        Persona oldPersonaIdOfVehiculoCollectionVehiculo = vehiculoCollectionVehiculo.getPersonaId();
        vehiculoCollectionVehiculo.setPersonaId(persona);
        vehiculoCollectionVehiculo = em.merge(vehiculoCollectionVehiculo);
        if (oldPersonaIdOfVehiculoCollectionVehiculo != null) {
          oldPersonaIdOfVehiculoCollectionVehiculo.getVehiculoCollection().remove(vehiculoCollectionVehiculo);
          oldPersonaIdOfVehiculoCollectionVehiculo = em.merge(oldPersonaIdOfVehiculoCollectionVehiculo);
        }
      }
      for (DireccionPersona direccionPersonaCollectionDireccionPersona : persona.getDireccionPersonaCollection()) {
        Persona oldPersonaIdOfDireccionPersonaCollectionDireccionPersona = direccionPersonaCollectionDireccionPersona.getPersonaId();
        direccionPersonaCollectionDireccionPersona.setPersonaId(persona);
        direccionPersonaCollectionDireccionPersona = em.merge(direccionPersonaCollectionDireccionPersona);
        if (oldPersonaIdOfDireccionPersonaCollectionDireccionPersona != null) {
          oldPersonaIdOfDireccionPersonaCollectionDireccionPersona.getDireccionPersonaCollection().remove(direccionPersonaCollectionDireccionPersona);
          oldPersonaIdOfDireccionPersonaCollectionDireccionPersona = em.merge(oldPersonaIdOfDireccionPersonaCollectionDireccionPersona);
        }
      }
      for (Factura facturaCollectionFactura : persona.getFacturaCollection()) {
        Persona oldPersonaIdOfFacturaCollectionFactura = facturaCollectionFactura.getPersonaId();
        facturaCollectionFactura.setPersonaId(persona);
        facturaCollectionFactura = em.merge(facturaCollectionFactura);
        if (oldPersonaIdOfFacturaCollectionFactura != null) {
          oldPersonaIdOfFacturaCollectionFactura.getFacturaCollection().remove(facturaCollectionFactura);
          oldPersonaIdOfFacturaCollectionFactura = em.merge(oldPersonaIdOfFacturaCollectionFactura);
        }
      }
      for (Prenda prendaCollectionPrenda : persona.getPrendaCollection()) {
        Persona oldPersonaIdOfPrendaCollectionPrenda = prendaCollectionPrenda.getPersonaId();
        prendaCollectionPrenda.setPersonaId(persona);
        prendaCollectionPrenda = em.merge(prendaCollectionPrenda);
        if (oldPersonaIdOfPrendaCollectionPrenda != null) {
          oldPersonaIdOfPrendaCollectionPrenda.getPrendaCollection().remove(prendaCollectionPrenda);
          oldPersonaIdOfPrendaCollectionPrenda = em.merge(oldPersonaIdOfPrendaCollectionPrenda);
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
      Collection<Vehiculo> vehiculoCollectionOld = persistentPersona.getVehiculoCollection();
      Collection<Vehiculo> vehiculoCollectionNew = persona.getVehiculoCollection();
      Collection<DireccionPersona> direccionPersonaCollectionOld = persistentPersona.getDireccionPersonaCollection();
      Collection<DireccionPersona> direccionPersonaCollectionNew = persona.getDireccionPersonaCollection();
      Collection<Factura> facturaCollectionOld = persistentPersona.getFacturaCollection();
      Collection<Factura> facturaCollectionNew = persona.getFacturaCollection();
      Collection<Prenda> prendaCollectionOld = persistentPersona.getPrendaCollection();
      Collection<Prenda> prendaCollectionNew = persona.getPrendaCollection();
      Collection<Telefono> telefonoCollectionOld = persistentPersona.getTelefonoCollection();
      Collection<Telefono> telefonoCollectionNew = persona.getTelefonoCollection();
      Collection<Cita> citaCollectionOld = persistentPersona.getCitaCollection();
      Collection<Cita> citaCollectionNew = persona.getCitaCollection();
      if (tipoPersonaIdNew != null) {
        tipoPersonaIdNew = em.getReference(tipoPersonaIdNew.getClass(), tipoPersonaIdNew.getId());
        persona.setTipoPersonaId(tipoPersonaIdNew);
      }
      Collection<Vehiculo> attachedVehiculoCollectionNew = new ArrayList<Vehiculo>();
      for (Vehiculo vehiculoCollectionNewVehiculoToAttach : vehiculoCollectionNew) {
        vehiculoCollectionNewVehiculoToAttach = em.getReference(vehiculoCollectionNewVehiculoToAttach.getClass(), vehiculoCollectionNewVehiculoToAttach.getId());
        attachedVehiculoCollectionNew.add(vehiculoCollectionNewVehiculoToAttach);
      }
      vehiculoCollectionNew = attachedVehiculoCollectionNew;
      persona.setVehiculoCollection(vehiculoCollectionNew);
      Collection<DireccionPersona> attachedDireccionPersonaCollectionNew = new ArrayList<DireccionPersona>();
      for (DireccionPersona direccionPersonaCollectionNewDireccionPersonaToAttach : direccionPersonaCollectionNew) {
        direccionPersonaCollectionNewDireccionPersonaToAttach = em.getReference(direccionPersonaCollectionNewDireccionPersonaToAttach.getClass(), direccionPersonaCollectionNewDireccionPersonaToAttach.getId());
        attachedDireccionPersonaCollectionNew.add(direccionPersonaCollectionNewDireccionPersonaToAttach);
      }
      direccionPersonaCollectionNew = attachedDireccionPersonaCollectionNew;
      persona.setDireccionPersonaCollection(direccionPersonaCollectionNew);
      Collection<Factura> attachedFacturaCollectionNew = new ArrayList<Factura>();
      for (Factura facturaCollectionNewFacturaToAttach : facturaCollectionNew) {
        facturaCollectionNewFacturaToAttach = em.getReference(facturaCollectionNewFacturaToAttach.getClass(), facturaCollectionNewFacturaToAttach.getId());
        attachedFacturaCollectionNew.add(facturaCollectionNewFacturaToAttach);
      }
      facturaCollectionNew = attachedFacturaCollectionNew;
      persona.setFacturaCollection(facturaCollectionNew);
      Collection<Prenda> attachedPrendaCollectionNew = new ArrayList<Prenda>();
      for (Prenda prendaCollectionNewPrendaToAttach : prendaCollectionNew) {
        prendaCollectionNewPrendaToAttach = em.getReference(prendaCollectionNewPrendaToAttach.getClass(), prendaCollectionNewPrendaToAttach.getId());
        attachedPrendaCollectionNew.add(prendaCollectionNewPrendaToAttach);
      }
      prendaCollectionNew = attachedPrendaCollectionNew;
      persona.setPrendaCollection(prendaCollectionNew);
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
      for (DireccionPersona direccionPersonaCollectionOldDireccionPersona : direccionPersonaCollectionOld) {
        if (!direccionPersonaCollectionNew.contains(direccionPersonaCollectionOldDireccionPersona)) {
          direccionPersonaCollectionOldDireccionPersona.setPersonaId(null);
          direccionPersonaCollectionOldDireccionPersona = em.merge(direccionPersonaCollectionOldDireccionPersona);
        }
      }
      for (DireccionPersona direccionPersonaCollectionNewDireccionPersona : direccionPersonaCollectionNew) {
        if (!direccionPersonaCollectionOld.contains(direccionPersonaCollectionNewDireccionPersona)) {
          Persona oldPersonaIdOfDireccionPersonaCollectionNewDireccionPersona = direccionPersonaCollectionNewDireccionPersona.getPersonaId();
          direccionPersonaCollectionNewDireccionPersona.setPersonaId(persona);
          direccionPersonaCollectionNewDireccionPersona = em.merge(direccionPersonaCollectionNewDireccionPersona);
          if (oldPersonaIdOfDireccionPersonaCollectionNewDireccionPersona != null && !oldPersonaIdOfDireccionPersonaCollectionNewDireccionPersona.equals(persona)) {
            oldPersonaIdOfDireccionPersonaCollectionNewDireccionPersona.getDireccionPersonaCollection().remove(direccionPersonaCollectionNewDireccionPersona);
            oldPersonaIdOfDireccionPersonaCollectionNewDireccionPersona = em.merge(oldPersonaIdOfDireccionPersonaCollectionNewDireccionPersona);
          }
        }
      }
      for (Factura facturaCollectionOldFactura : facturaCollectionOld) {
        if (!facturaCollectionNew.contains(facturaCollectionOldFactura)) {
          facturaCollectionOldFactura.setPersonaId(null);
          facturaCollectionOldFactura = em.merge(facturaCollectionOldFactura);
        }
      }
      for (Factura facturaCollectionNewFactura : facturaCollectionNew) {
        if (!facturaCollectionOld.contains(facturaCollectionNewFactura)) {
          Persona oldPersonaIdOfFacturaCollectionNewFactura = facturaCollectionNewFactura.getPersonaId();
          facturaCollectionNewFactura.setPersonaId(persona);
          facturaCollectionNewFactura = em.merge(facturaCollectionNewFactura);
          if (oldPersonaIdOfFacturaCollectionNewFactura != null && !oldPersonaIdOfFacturaCollectionNewFactura.equals(persona)) {
            oldPersonaIdOfFacturaCollectionNewFactura.getFacturaCollection().remove(facturaCollectionNewFactura);
            oldPersonaIdOfFacturaCollectionNewFactura = em.merge(oldPersonaIdOfFacturaCollectionNewFactura);
          }
        }
      }
      for (Prenda prendaCollectionOldPrenda : prendaCollectionOld) {
        if (!prendaCollectionNew.contains(prendaCollectionOldPrenda)) {
          prendaCollectionOldPrenda.setPersonaId(null);
          prendaCollectionOldPrenda = em.merge(prendaCollectionOldPrenda);
        }
      }
      for (Prenda prendaCollectionNewPrenda : prendaCollectionNew) {
        if (!prendaCollectionOld.contains(prendaCollectionNewPrenda)) {
          Persona oldPersonaIdOfPrendaCollectionNewPrenda = prendaCollectionNewPrenda.getPersonaId();
          prendaCollectionNewPrenda.setPersonaId(persona);
          prendaCollectionNewPrenda = em.merge(prendaCollectionNewPrenda);
          if (oldPersonaIdOfPrendaCollectionNewPrenda != null && !oldPersonaIdOfPrendaCollectionNewPrenda.equals(persona)) {
            oldPersonaIdOfPrendaCollectionNewPrenda.getPrendaCollection().remove(prendaCollectionNewPrenda);
            oldPersonaIdOfPrendaCollectionNewPrenda = em.merge(oldPersonaIdOfPrendaCollectionNewPrenda);
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
      Collection<Vehiculo> vehiculoCollection = persona.getVehiculoCollection();
      for (Vehiculo vehiculoCollectionVehiculo : vehiculoCollection) {
        vehiculoCollectionVehiculo.setPersonaId(null);
        vehiculoCollectionVehiculo = em.merge(vehiculoCollectionVehiculo);
      }
      Collection<DireccionPersona> direccionPersonaCollection = persona.getDireccionPersonaCollection();
      for (DireccionPersona direccionPersonaCollectionDireccionPersona : direccionPersonaCollection) {
        direccionPersonaCollectionDireccionPersona.setPersonaId(null);
        direccionPersonaCollectionDireccionPersona = em.merge(direccionPersonaCollectionDireccionPersona);
      }
      Collection<Factura> facturaCollection = persona.getFacturaCollection();
      for (Factura facturaCollectionFactura : facturaCollection) {
        facturaCollectionFactura.setPersonaId(null);
        facturaCollectionFactura = em.merge(facturaCollectionFactura);
      }
      Collection<Prenda> prendaCollection = persona.getPrendaCollection();
      for (Prenda prendaCollectionPrenda : prendaCollection) {
        prendaCollectionPrenda.setPersonaId(null);
        prendaCollectionPrenda = em.merge(prendaCollectionPrenda);
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
