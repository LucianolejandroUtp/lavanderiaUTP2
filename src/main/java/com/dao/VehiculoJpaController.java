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
import com.dto.Persona;
import com.dto.Cita;
import com.dto.Vehiculo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class VehiculoJpaController implements Serializable {

  public VehiculoJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Vehiculo vehiculo) {
    if (vehiculo.getCitaCollection() == null) {
      vehiculo.setCitaCollection(new ArrayList<Cita>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Persona personaId = vehiculo.getPersonaId();
      if (personaId != null) {
        personaId = em.getReference(personaId.getClass(), personaId.getId());
        vehiculo.setPersonaId(personaId);
      }
      Collection<Cita> attachedCitaCollection = new ArrayList<Cita>();
      for (Cita citaCollectionCitaToAttach : vehiculo.getCitaCollection()) {
        citaCollectionCitaToAttach = em.getReference(citaCollectionCitaToAttach.getClass(), citaCollectionCitaToAttach.getId());
        attachedCitaCollection.add(citaCollectionCitaToAttach);
      }
      vehiculo.setCitaCollection(attachedCitaCollection);
      em.persist(vehiculo);
      if (personaId != null) {
        personaId.getVehiculoCollection().add(vehiculo);
        personaId = em.merge(personaId);
      }
      for (Cita citaCollectionCita : vehiculo.getCitaCollection()) {
        Vehiculo oldVehiculoIdOfCitaCollectionCita = citaCollectionCita.getVehiculoId();
        citaCollectionCita.setVehiculoId(vehiculo);
        citaCollectionCita = em.merge(citaCollectionCita);
        if (oldVehiculoIdOfCitaCollectionCita != null) {
          oldVehiculoIdOfCitaCollectionCita.getCitaCollection().remove(citaCollectionCita);
          oldVehiculoIdOfCitaCollectionCita = em.merge(oldVehiculoIdOfCitaCollectionCita);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Vehiculo vehiculo) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Vehiculo persistentVehiculo = em.find(Vehiculo.class, vehiculo.getId());
      Persona personaIdOld = persistentVehiculo.getPersonaId();
      Persona personaIdNew = vehiculo.getPersonaId();
      Collection<Cita> citaCollectionOld = persistentVehiculo.getCitaCollection();
      Collection<Cita> citaCollectionNew = vehiculo.getCitaCollection();
      if (personaIdNew != null) {
        personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
        vehiculo.setPersonaId(personaIdNew);
      }
      Collection<Cita> attachedCitaCollectionNew = new ArrayList<Cita>();
      for (Cita citaCollectionNewCitaToAttach : citaCollectionNew) {
        citaCollectionNewCitaToAttach = em.getReference(citaCollectionNewCitaToAttach.getClass(), citaCollectionNewCitaToAttach.getId());
        attachedCitaCollectionNew.add(citaCollectionNewCitaToAttach);
      }
      citaCollectionNew = attachedCitaCollectionNew;
      vehiculo.setCitaCollection(citaCollectionNew);
      vehiculo = em.merge(vehiculo);
      if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
        personaIdOld.getVehiculoCollection().remove(vehiculo);
        personaIdOld = em.merge(personaIdOld);
      }
      if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
        personaIdNew.getVehiculoCollection().add(vehiculo);
        personaIdNew = em.merge(personaIdNew);
      }
      for (Cita citaCollectionOldCita : citaCollectionOld) {
        if (!citaCollectionNew.contains(citaCollectionOldCita)) {
          citaCollectionOldCita.setVehiculoId(null);
          citaCollectionOldCita = em.merge(citaCollectionOldCita);
        }
      }
      for (Cita citaCollectionNewCita : citaCollectionNew) {
        if (!citaCollectionOld.contains(citaCollectionNewCita)) {
          Vehiculo oldVehiculoIdOfCitaCollectionNewCita = citaCollectionNewCita.getVehiculoId();
          citaCollectionNewCita.setVehiculoId(vehiculo);
          citaCollectionNewCita = em.merge(citaCollectionNewCita);
          if (oldVehiculoIdOfCitaCollectionNewCita != null && !oldVehiculoIdOfCitaCollectionNewCita.equals(vehiculo)) {
            oldVehiculoIdOfCitaCollectionNewCita.getCitaCollection().remove(citaCollectionNewCita);
            oldVehiculoIdOfCitaCollectionNewCita = em.merge(oldVehiculoIdOfCitaCollectionNewCita);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = vehiculo.getId();
        if (findVehiculo(id) == null) {
          throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.");
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
      Vehiculo vehiculo;
      try {
        vehiculo = em.getReference(Vehiculo.class, id);
        vehiculo.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.", enfe);
      }
      Persona personaId = vehiculo.getPersonaId();
      if (personaId != null) {
        personaId.getVehiculoCollection().remove(vehiculo);
        personaId = em.merge(personaId);
      }
      Collection<Cita> citaCollection = vehiculo.getCitaCollection();
      for (Cita citaCollectionCita : citaCollection) {
        citaCollectionCita.setVehiculoId(null);
        citaCollectionCita = em.merge(citaCollectionCita);
      }
      em.remove(vehiculo);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Vehiculo> findVehiculoEntities() {
    return findVehiculoEntities(true, -1, -1);
  }

  public List<Vehiculo> findVehiculoEntities(int maxResults, int firstResult) {
    return findVehiculoEntities(false, maxResults, firstResult);
  }

  private List<Vehiculo> findVehiculoEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Vehiculo.class));
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

  public Vehiculo findVehiculo(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Vehiculo.class, id);
    } finally {
      em.close();
    }
  }

  public int getVehiculoCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Vehiculo> rt = cq.from(Vehiculo.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
