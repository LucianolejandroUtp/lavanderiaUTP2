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
import com.dto.TipoPersona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class TipoPersonaJpaController implements Serializable {

  public TipoPersonaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(TipoPersona tipoPersona) {
    if (tipoPersona.getPersonaCollection() == null) {
      tipoPersona.setPersonaCollection(new ArrayList<Persona>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Collection<Persona> attachedPersonaCollection = new ArrayList<Persona>();
      for (Persona personaCollectionPersonaToAttach : tipoPersona.getPersonaCollection()) {
        personaCollectionPersonaToAttach = em.getReference(personaCollectionPersonaToAttach.getClass(), personaCollectionPersonaToAttach.getId());
        attachedPersonaCollection.add(personaCollectionPersonaToAttach);
      }
      tipoPersona.setPersonaCollection(attachedPersonaCollection);
      em.persist(tipoPersona);
      for (Persona personaCollectionPersona : tipoPersona.getPersonaCollection()) {
        TipoPersona oldTipoPersonaIdOfPersonaCollectionPersona = personaCollectionPersona.getTipoPersonaId();
        personaCollectionPersona.setTipoPersonaId(tipoPersona);
        personaCollectionPersona = em.merge(personaCollectionPersona);
        if (oldTipoPersonaIdOfPersonaCollectionPersona != null) {
          oldTipoPersonaIdOfPersonaCollectionPersona.getPersonaCollection().remove(personaCollectionPersona);
          oldTipoPersonaIdOfPersonaCollectionPersona = em.merge(oldTipoPersonaIdOfPersonaCollectionPersona);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(TipoPersona tipoPersona) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      TipoPersona persistentTipoPersona = em.find(TipoPersona.class, tipoPersona.getId());
      Collection<Persona> personaCollectionOld = persistentTipoPersona.getPersonaCollection();
      Collection<Persona> personaCollectionNew = tipoPersona.getPersonaCollection();
      Collection<Persona> attachedPersonaCollectionNew = new ArrayList<Persona>();
      for (Persona personaCollectionNewPersonaToAttach : personaCollectionNew) {
        personaCollectionNewPersonaToAttach = em.getReference(personaCollectionNewPersonaToAttach.getClass(), personaCollectionNewPersonaToAttach.getId());
        attachedPersonaCollectionNew.add(personaCollectionNewPersonaToAttach);
      }
      personaCollectionNew = attachedPersonaCollectionNew;
      tipoPersona.setPersonaCollection(personaCollectionNew);
      tipoPersona = em.merge(tipoPersona);
      for (Persona personaCollectionOldPersona : personaCollectionOld) {
        if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
          personaCollectionOldPersona.setTipoPersonaId(null);
          personaCollectionOldPersona = em.merge(personaCollectionOldPersona);
        }
      }
      for (Persona personaCollectionNewPersona : personaCollectionNew) {
        if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
          TipoPersona oldTipoPersonaIdOfPersonaCollectionNewPersona = personaCollectionNewPersona.getTipoPersonaId();
          personaCollectionNewPersona.setTipoPersonaId(tipoPersona);
          personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
          if (oldTipoPersonaIdOfPersonaCollectionNewPersona != null && !oldTipoPersonaIdOfPersonaCollectionNewPersona.equals(tipoPersona)) {
            oldTipoPersonaIdOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
            oldTipoPersonaIdOfPersonaCollectionNewPersona = em.merge(oldTipoPersonaIdOfPersonaCollectionNewPersona);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = tipoPersona.getId();
        if (findTipoPersona(id) == null) {
          throw new NonexistentEntityException("The tipoPersona with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void softDelete(TipoPersona tipoPersona) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      TipoPersona persistentTipoPersona = em.find(TipoPersona.class, tipoPersona.getId());
      Collection<Persona> personaCollectionOld = persistentTipoPersona.getPersonaCollection();
      Collection<Persona> personaCollectionNew = tipoPersona.getPersonaCollection();
      Collection<Persona> attachedPersonaCollectionNew = new ArrayList<Persona>();
      for (Persona personaCollectionNewPersonaToAttach : personaCollectionNew) {
        personaCollectionNewPersonaToAttach = em.getReference(personaCollectionNewPersonaToAttach.getClass(), personaCollectionNewPersonaToAttach.getId());
        attachedPersonaCollectionNew.add(personaCollectionNewPersonaToAttach);
      }
      personaCollectionNew = attachedPersonaCollectionNew;
      tipoPersona.setPersonaCollection(personaCollectionNew);
      tipoPersona = em.merge(tipoPersona);
      for (Persona personaCollectionOldPersona : personaCollectionOld) {
        if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
          personaCollectionOldPersona.setTipoPersonaId(null);
          personaCollectionOldPersona = em.merge(personaCollectionOldPersona);
        }
      }
      for (Persona personaCollectionNewPersona : personaCollectionNew) {
        if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
          TipoPersona oldTipoPersonaIdOfPersonaCollectionNewPersona = personaCollectionNewPersona.getTipoPersonaId();
          personaCollectionNewPersona.setTipoPersonaId(tipoPersona);
          personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
          if (oldTipoPersonaIdOfPersonaCollectionNewPersona != null && !oldTipoPersonaIdOfPersonaCollectionNewPersona.equals(tipoPersona)) {
            oldTipoPersonaIdOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
            oldTipoPersonaIdOfPersonaCollectionNewPersona = em.merge(oldTipoPersonaIdOfPersonaCollectionNewPersona);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = tipoPersona.getId();
        if (findTipoPersona(id) == null) {
          throw new NonexistentEntityException("The tipoPersona with id " + id + " no longer exists.");
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
      TipoPersona tipoPersona;
      try {
        tipoPersona = em.getReference(TipoPersona.class, id);
        tipoPersona.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The tipoPersona with id " + id + " no longer exists.", enfe);
      }
      Collection<Persona> personaCollection = tipoPersona.getPersonaCollection();
      for (Persona personaCollectionPersona : personaCollection) {
        personaCollectionPersona.setTipoPersonaId(null);
        personaCollectionPersona = em.merge(personaCollectionPersona);
      }
      em.remove(tipoPersona);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<TipoPersona> findTipoPersonaEntities() {
    return findTipoPersonaEntities(true, -1, -1);
  }

  public List<TipoPersona> findTipoPersonaEntities(int maxResults, int firstResult) {
    return findTipoPersonaEntities(false, maxResults, firstResult);
  }

  private List<TipoPersona> findTipoPersonaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(TipoPersona.class));
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

  public TipoPersona findTipoPersona(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(TipoPersona.class, id);
    } finally {
      em.close();
    }
  }

  public int getTipoPersonaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<TipoPersona> rt = cq.from(TipoPersona.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
