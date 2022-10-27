/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import com.dto.Distrito;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dto.Persona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desti
 */
public class DistritoJpaController implements Serializable {

  public DistritoJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public DistritoJpaController() {
    emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
  }

  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Distrito distrito) {
    if (distrito.getPersonaCollection() == null) {
      distrito.setPersonaCollection(new ArrayList<Persona>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Collection<Persona> attachedPersonaCollection = new ArrayList<Persona>();
      for (Persona personaCollectionPersonaToAttach : distrito.getPersonaCollection()) {
        personaCollectionPersonaToAttach = em.getReference(personaCollectionPersonaToAttach.getClass(), personaCollectionPersonaToAttach.getId());
        attachedPersonaCollection.add(personaCollectionPersonaToAttach);
      }
      distrito.setPersonaCollection(attachedPersonaCollection);
      em.persist(distrito);
      for (Persona personaCollectionPersona : distrito.getPersonaCollection()) {
        Distrito oldDistritoIdOfPersonaCollectionPersona = personaCollectionPersona.getDistritoId();
        personaCollectionPersona.setDistritoId(distrito);
        personaCollectionPersona = em.merge(personaCollectionPersona);
        if (oldDistritoIdOfPersonaCollectionPersona != null) {
          oldDistritoIdOfPersonaCollectionPersona.getPersonaCollection().remove(personaCollectionPersona);
          oldDistritoIdOfPersonaCollectionPersona = em.merge(oldDistritoIdOfPersonaCollectionPersona);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Distrito distrito) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Distrito persistentDistrito = em.find(Distrito.class, distrito.getId());
      Collection<Persona> personaCollectionOld = persistentDistrito.getPersonaCollection();
      Collection<Persona> personaCollectionNew = distrito.getPersonaCollection();
      Collection<Persona> attachedPersonaCollectionNew = new ArrayList<Persona>();
      for (Persona personaCollectionNewPersonaToAttach : personaCollectionNew) {
        personaCollectionNewPersonaToAttach = em.getReference(personaCollectionNewPersonaToAttach.getClass(), personaCollectionNewPersonaToAttach.getId());
        attachedPersonaCollectionNew.add(personaCollectionNewPersonaToAttach);
      }
      personaCollectionNew = attachedPersonaCollectionNew;
      distrito.setPersonaCollection(personaCollectionNew);
      distrito = em.merge(distrito);
      for (Persona personaCollectionOldPersona : personaCollectionOld) {
        if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
          personaCollectionOldPersona.setDistritoId(null);
          personaCollectionOldPersona = em.merge(personaCollectionOldPersona);
        }
      }
      for (Persona personaCollectionNewPersona : personaCollectionNew) {
        if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
          Distrito oldDistritoIdOfPersonaCollectionNewPersona = personaCollectionNewPersona.getDistritoId();
          personaCollectionNewPersona.setDistritoId(distrito);
          personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
          if (oldDistritoIdOfPersonaCollectionNewPersona != null && !oldDistritoIdOfPersonaCollectionNewPersona.equals(distrito)) {
            oldDistritoIdOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
            oldDistritoIdOfPersonaCollectionNewPersona = em.merge(oldDistritoIdOfPersonaCollectionNewPersona);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = distrito.getId();
        if (findDistrito(id) == null) {
          throw new NonexistentEntityException("The distrito with id " + id + " no longer exists.");
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
      Distrito distrito;
      try {
        distrito = em.getReference(Distrito.class, id);
        distrito.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The distrito with id " + id + " no longer exists.", enfe);
      }
      Collection<Persona> personaCollection = distrito.getPersonaCollection();
      for (Persona personaCollectionPersona : personaCollection) {
        personaCollectionPersona.setDistritoId(null);
        personaCollectionPersona = em.merge(personaCollectionPersona);
      }
      em.remove(distrito);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Distrito> findDistritoEntities() {
    return findDistritoEntities(true, -1, -1);
  }

  public List<Distrito> findDistritoEntities(int maxResults, int firstResult) {
    return findDistritoEntities(false, maxResults, firstResult);
  }

  private List<Distrito> findDistritoEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Distrito.class));
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

  public Distrito findDistrito(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Distrito.class, id);
    } finally {
      em.close();
    }
  }

  public int getDistritoCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Distrito> rt = cq.from(Distrito.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }

}
