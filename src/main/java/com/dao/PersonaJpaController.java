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
import com.dto.Distrito;
import com.dto.Persona;
import com.dto.TipoPersona;
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
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Distrito distritoId = persona.getDistritoId();
      if (distritoId != null) {
        distritoId = em.getReference(distritoId.getClass(), distritoId.getId());
        persona.setDistritoId(distritoId);
      }
      TipoPersona tipoPersonaId = persona.getTipoPersonaId();
      if (tipoPersonaId != null) {
        tipoPersonaId = em.getReference(tipoPersonaId.getClass(), tipoPersonaId.getId());
        persona.setTipoPersonaId(tipoPersonaId);
      }
      em.persist(persona);
      if (distritoId != null) {
        distritoId.getPersonaCollection().add(persona);
        distritoId = em.merge(distritoId);
      }
      if (tipoPersonaId != null) {
        tipoPersonaId.getPersonaCollection().add(persona);
        tipoPersonaId = em.merge(tipoPersonaId);
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
      Distrito distritoIdOld = persistentPersona.getDistritoId();
      Distrito distritoIdNew = persona.getDistritoId();
      TipoPersona tipoPersonaIdOld = persistentPersona.getTipoPersonaId();
      TipoPersona tipoPersonaIdNew = persona.getTipoPersonaId();
      if (distritoIdNew != null) {
        distritoIdNew = em.getReference(distritoIdNew.getClass(), distritoIdNew.getId());
        persona.setDistritoId(distritoIdNew);
      }
      if (tipoPersonaIdNew != null) {
        tipoPersonaIdNew = em.getReference(tipoPersonaIdNew.getClass(), tipoPersonaIdNew.getId());
        persona.setTipoPersonaId(tipoPersonaIdNew);
      }
      persona = em.merge(persona);
      if (distritoIdOld != null && !distritoIdOld.equals(distritoIdNew)) {
        distritoIdOld.getPersonaCollection().remove(persona);
        distritoIdOld = em.merge(distritoIdOld);
      }
      if (distritoIdNew != null && !distritoIdNew.equals(distritoIdOld)) {
        distritoIdNew.getPersonaCollection().add(persona);
        distritoIdNew = em.merge(distritoIdNew);
      }
      if (tipoPersonaIdOld != null && !tipoPersonaIdOld.equals(tipoPersonaIdNew)) {
        tipoPersonaIdOld.getPersonaCollection().remove(persona);
        tipoPersonaIdOld = em.merge(tipoPersonaIdOld);
      }
      if (tipoPersonaIdNew != null && !tipoPersonaIdNew.equals(tipoPersonaIdOld)) {
        tipoPersonaIdNew.getPersonaCollection().add(persona);
        tipoPersonaIdNew = em.merge(tipoPersonaIdNew);
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
      Distrito distritoId = persona.getDistritoId();
      if (distritoId != null) {
        distritoId.getPersonaCollection().remove(persona);
        distritoId = em.merge(distritoId);
      }
      TipoPersona tipoPersonaId = persona.getTipoPersonaId();
      if (tipoPersonaId != null) {
        tipoPersonaId.getPersonaCollection().remove(persona);
        tipoPersonaId = em.merge(tipoPersonaId);
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
