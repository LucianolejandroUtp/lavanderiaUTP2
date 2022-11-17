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
import com.dto.Direccion;
import com.dto.DireccionPersona;
import com.dto.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class DireccionPersonaJpaController implements Serializable {

  public DireccionPersonaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(DireccionPersona direccionPersona) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Direccion direccionId = direccionPersona.getDireccionId();
      if (direccionId != null) {
        direccionId = em.getReference(direccionId.getClass(), direccionId.getId());
        direccionPersona.setDireccionId(direccionId);
      }
      Persona personaId = direccionPersona.getPersonaId();
      if (personaId != null) {
        personaId = em.getReference(personaId.getClass(), personaId.getId());
        direccionPersona.setPersonaId(personaId);
      }
      em.persist(direccionPersona);
      if (direccionId != null) {
        direccionId.getDireccionPersonaCollection().add(direccionPersona);
        direccionId = em.merge(direccionId);
      }
      if (personaId != null) {
        personaId.getDireccionPersonaCollection().add(direccionPersona);
        personaId = em.merge(personaId);
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(DireccionPersona direccionPersona) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      DireccionPersona persistentDireccionPersona = em.find(DireccionPersona.class, direccionPersona.getId());
      Direccion direccionIdOld = persistentDireccionPersona.getDireccionId();
      Direccion direccionIdNew = direccionPersona.getDireccionId();
      Persona personaIdOld = persistentDireccionPersona.getPersonaId();
      Persona personaIdNew = direccionPersona.getPersonaId();
      if (direccionIdNew != null) {
        direccionIdNew = em.getReference(direccionIdNew.getClass(), direccionIdNew.getId());
        direccionPersona.setDireccionId(direccionIdNew);
      }
      if (personaIdNew != null) {
        personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
        direccionPersona.setPersonaId(personaIdNew);
      }
      direccionPersona = em.merge(direccionPersona);
      if (direccionIdOld != null && !direccionIdOld.equals(direccionIdNew)) {
        direccionIdOld.getDireccionPersonaCollection().remove(direccionPersona);
        direccionIdOld = em.merge(direccionIdOld);
      }
      if (direccionIdNew != null && !direccionIdNew.equals(direccionIdOld)) {
        direccionIdNew.getDireccionPersonaCollection().add(direccionPersona);
        direccionIdNew = em.merge(direccionIdNew);
      }
      if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
        personaIdOld.getDireccionPersonaCollection().remove(direccionPersona);
        personaIdOld = em.merge(personaIdOld);
      }
      if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
        personaIdNew.getDireccionPersonaCollection().add(direccionPersona);
        personaIdNew = em.merge(personaIdNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = direccionPersona.getId();
        if (findDireccionPersona(id) == null) {
          throw new NonexistentEntityException("The direccionPersona with id " + id + " no longer exists.");
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
      DireccionPersona direccionPersona;
      try {
        direccionPersona = em.getReference(DireccionPersona.class, id);
        direccionPersona.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The direccionPersona with id " + id + " no longer exists.", enfe);
      }
      Direccion direccionId = direccionPersona.getDireccionId();
      if (direccionId != null) {
        direccionId.getDireccionPersonaCollection().remove(direccionPersona);
        direccionId = em.merge(direccionId);
      }
      Persona personaId = direccionPersona.getPersonaId();
      if (personaId != null) {
        personaId.getDireccionPersonaCollection().remove(direccionPersona);
        personaId = em.merge(personaId);
      }
      em.remove(direccionPersona);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<DireccionPersona> findDireccionPersonaEntities() {
    return findDireccionPersonaEntities(true, -1, -1);
  }

  public List<DireccionPersona> findDireccionPersonaEntities(int maxResults, int firstResult) {
    return findDireccionPersonaEntities(false, maxResults, firstResult);
  }

  private List<DireccionPersona> findDireccionPersonaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(DireccionPersona.class));
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

  public DireccionPersona findDireccionPersona(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(DireccionPersona.class, id);
    } finally {
      em.close();
    }
  }

  public int getDireccionPersonaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<DireccionPersona> rt = cq.from(DireccionPersona.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
