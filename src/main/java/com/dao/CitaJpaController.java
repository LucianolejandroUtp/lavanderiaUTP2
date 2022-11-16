/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import com.dto.Cita;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dto.Persona;
import com.dto.Vehiculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class CitaJpaController implements Serializable {

  public CitaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Cita cita) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Persona personaId = cita.getPersonaId();
      if (personaId != null) {
        personaId = em.getReference(personaId.getClass(), personaId.getId());
        cita.setPersonaId(personaId);
      }
      Vehiculo vehiculoId = cita.getVehiculoId();
      if (vehiculoId != null) {
        vehiculoId = em.getReference(vehiculoId.getClass(), vehiculoId.getId());
        cita.setVehiculoId(vehiculoId);
      }
      em.persist(cita);
      if (personaId != null) {
        personaId.getCitaCollection().add(cita);
        personaId = em.merge(personaId);
      }
      if (vehiculoId != null) {
        vehiculoId.getCitaCollection().add(cita);
        vehiculoId = em.merge(vehiculoId);
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Cita cita) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Cita persistentCita = em.find(Cita.class, cita.getId());
      Persona personaIdOld = persistentCita.getPersonaId();
      Persona personaIdNew = cita.getPersonaId();
      Vehiculo vehiculoIdOld = persistentCita.getVehiculoId();
      Vehiculo vehiculoIdNew = cita.getVehiculoId();
      if (personaIdNew != null) {
        personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
        cita.setPersonaId(personaIdNew);
      }
      if (vehiculoIdNew != null) {
        vehiculoIdNew = em.getReference(vehiculoIdNew.getClass(), vehiculoIdNew.getId());
        cita.setVehiculoId(vehiculoIdNew);
      }
      cita = em.merge(cita);
      if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
        personaIdOld.getCitaCollection().remove(cita);
        personaIdOld = em.merge(personaIdOld);
      }
      if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
        personaIdNew.getCitaCollection().add(cita);
        personaIdNew = em.merge(personaIdNew);
      }
      if (vehiculoIdOld != null && !vehiculoIdOld.equals(vehiculoIdNew)) {
        vehiculoIdOld.getCitaCollection().remove(cita);
        vehiculoIdOld = em.merge(vehiculoIdOld);
      }
      if (vehiculoIdNew != null && !vehiculoIdNew.equals(vehiculoIdOld)) {
        vehiculoIdNew.getCitaCollection().add(cita);
        vehiculoIdNew = em.merge(vehiculoIdNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = cita.getId();
        if (findCita(id) == null) {
          throw new NonexistentEntityException("The cita with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void softDelete(Cita cita) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Cita persistentCita = em.find(Cita.class, cita.getId());
      Persona personaIdOld = persistentCita.getPersonaId();
      Persona personaIdNew = cita.getPersonaId();
      Vehiculo vehiculoIdOld = persistentCita.getVehiculoId();
      Vehiculo vehiculoIdNew = cita.getVehiculoId();
      if (personaIdNew != null) {
        personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
        cita.setPersonaId(personaIdNew);
      }
      if (vehiculoIdNew != null) {
        vehiculoIdNew = em.getReference(vehiculoIdNew.getClass(), vehiculoIdNew.getId());
        cita.setVehiculoId(vehiculoIdNew);
      }
      cita = em.merge(cita);
      if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
        personaIdOld.getCitaCollection().remove(cita);
        personaIdOld = em.merge(personaIdOld);
      }
      if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
        personaIdNew.getCitaCollection().add(cita);
        personaIdNew = em.merge(personaIdNew);
      }
      if (vehiculoIdOld != null && !vehiculoIdOld.equals(vehiculoIdNew)) {
        vehiculoIdOld.getCitaCollection().remove(cita);
        vehiculoIdOld = em.merge(vehiculoIdOld);
      }
      if (vehiculoIdNew != null && !vehiculoIdNew.equals(vehiculoIdOld)) {
        vehiculoIdNew.getCitaCollection().add(cita);
        vehiculoIdNew = em.merge(vehiculoIdNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = cita.getId();
        if (findCita(id) == null) {
          throw new NonexistentEntityException("The cita with id " + id + " no longer exists.");
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
      Cita cita;
      try {
        cita = em.getReference(Cita.class, id);
        cita.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The cita with id " + id + " no longer exists.", enfe);
      }
      Persona personaId = cita.getPersonaId();
      if (personaId != null) {
        personaId.getCitaCollection().remove(cita);
        personaId = em.merge(personaId);
      }
      Vehiculo vehiculoId = cita.getVehiculoId();
      if (vehiculoId != null) {
        vehiculoId.getCitaCollection().remove(cita);
        vehiculoId = em.merge(vehiculoId);
      }
      em.remove(cita);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Cita> findCitaEntities() {
    return findCitaEntities(true, -1, -1);
  }

  public List<Cita> findCitaEntities(int maxResults, int firstResult) {
    return findCitaEntities(false, maxResults, firstResult);
  }

  private List<Cita> findCitaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Cita.class));
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

  public Cita findCita(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Cita.class, id);
    } finally {
      em.close();
    }
  }

  public int getCitaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Cita> rt = cq.from(Cita.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
