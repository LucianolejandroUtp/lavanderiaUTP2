/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import com.dto.Direccion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dto.Distrito;
import com.dto.DireccionPersona;
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
public class DireccionJpaController implements Serializable {

  public DireccionJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public DireccionJpaController() {
    emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
  }

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Direccion direccion) {
    if (direccion.getDireccionPersonaCollection() == null) {
      direccion.setDireccionPersonaCollection(new ArrayList<DireccionPersona>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Distrito distritoId = direccion.getDistritoId();
      if (distritoId != null) {
        distritoId = em.getReference(distritoId.getClass(), distritoId.getId());
        direccion.setDistritoId(distritoId);
      }
      Collection<DireccionPersona> attachedDireccionPersonaCollection = new ArrayList<DireccionPersona>();
      for (DireccionPersona direccionPersonaCollectionDireccionPersonaToAttach : direccion.getDireccionPersonaCollection()) {
        direccionPersonaCollectionDireccionPersonaToAttach = em.getReference(direccionPersonaCollectionDireccionPersonaToAttach.getClass(), direccionPersonaCollectionDireccionPersonaToAttach.getId());
        attachedDireccionPersonaCollection.add(direccionPersonaCollectionDireccionPersonaToAttach);
      }
      direccion.setDireccionPersonaCollection(attachedDireccionPersonaCollection);
      em.persist(direccion);
      if (distritoId != null) {
        distritoId.getDireccionCollection().add(direccion);
        distritoId = em.merge(distritoId);
      }
      for (DireccionPersona direccionPersonaCollectionDireccionPersona : direccion.getDireccionPersonaCollection()) {
        Direccion oldDireccionIdOfDireccionPersonaCollectionDireccionPersona = direccionPersonaCollectionDireccionPersona.getDireccionId();
        direccionPersonaCollectionDireccionPersona.setDireccionId(direccion);
        direccionPersonaCollectionDireccionPersona = em.merge(direccionPersonaCollectionDireccionPersona);
        if (oldDireccionIdOfDireccionPersonaCollectionDireccionPersona != null) {
          oldDireccionIdOfDireccionPersonaCollectionDireccionPersona.getDireccionPersonaCollection().remove(direccionPersonaCollectionDireccionPersona);
          oldDireccionIdOfDireccionPersonaCollectionDireccionPersona = em.merge(oldDireccionIdOfDireccionPersonaCollectionDireccionPersona);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Direccion direccion) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Direccion persistentDireccion = em.find(Direccion.class, direccion.getId());
      Distrito distritoIdOld = persistentDireccion.getDistritoId();
      Distrito distritoIdNew = direccion.getDistritoId();
      Collection<DireccionPersona> direccionPersonaCollectionOld = persistentDireccion.getDireccionPersonaCollection();
      Collection<DireccionPersona> direccionPersonaCollectionNew = direccion.getDireccionPersonaCollection();
      if (distritoIdNew != null) {
        distritoIdNew = em.getReference(distritoIdNew.getClass(), distritoIdNew.getId());
        direccion.setDistritoId(distritoIdNew);
      }
      Collection<DireccionPersona> attachedDireccionPersonaCollectionNew = new ArrayList<DireccionPersona>();
      for (DireccionPersona direccionPersonaCollectionNewDireccionPersonaToAttach : direccionPersonaCollectionNew) {
        direccionPersonaCollectionNewDireccionPersonaToAttach = em.getReference(direccionPersonaCollectionNewDireccionPersonaToAttach.getClass(), direccionPersonaCollectionNewDireccionPersonaToAttach.getId());
        attachedDireccionPersonaCollectionNew.add(direccionPersonaCollectionNewDireccionPersonaToAttach);
      }
      direccionPersonaCollectionNew = attachedDireccionPersonaCollectionNew;
      direccion.setDireccionPersonaCollection(direccionPersonaCollectionNew);
      direccion = em.merge(direccion);
      if (distritoIdOld != null && !distritoIdOld.equals(distritoIdNew)) {
        distritoIdOld.getDireccionCollection().remove(direccion);
        distritoIdOld = em.merge(distritoIdOld);
      }
      if (distritoIdNew != null && !distritoIdNew.equals(distritoIdOld)) {
        distritoIdNew.getDireccionCollection().add(direccion);
        distritoIdNew = em.merge(distritoIdNew);
      }
      for (DireccionPersona direccionPersonaCollectionOldDireccionPersona : direccionPersonaCollectionOld) {
        if (!direccionPersonaCollectionNew.contains(direccionPersonaCollectionOldDireccionPersona)) {
          direccionPersonaCollectionOldDireccionPersona.setDireccionId(null);
          direccionPersonaCollectionOldDireccionPersona = em.merge(direccionPersonaCollectionOldDireccionPersona);
        }
      }
      for (DireccionPersona direccionPersonaCollectionNewDireccionPersona : direccionPersonaCollectionNew) {
        if (!direccionPersonaCollectionOld.contains(direccionPersonaCollectionNewDireccionPersona)) {
          Direccion oldDireccionIdOfDireccionPersonaCollectionNewDireccionPersona = direccionPersonaCollectionNewDireccionPersona.getDireccionId();
          direccionPersonaCollectionNewDireccionPersona.setDireccionId(direccion);
          direccionPersonaCollectionNewDireccionPersona = em.merge(direccionPersonaCollectionNewDireccionPersona);
          if (oldDireccionIdOfDireccionPersonaCollectionNewDireccionPersona != null && !oldDireccionIdOfDireccionPersonaCollectionNewDireccionPersona.equals(direccion)) {
            oldDireccionIdOfDireccionPersonaCollectionNewDireccionPersona.getDireccionPersonaCollection().remove(direccionPersonaCollectionNewDireccionPersona);
            oldDireccionIdOfDireccionPersonaCollectionNewDireccionPersona = em.merge(oldDireccionIdOfDireccionPersonaCollectionNewDireccionPersona);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = direccion.getId();
        if (findDireccion(id) == null) {
          throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.");
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
      Direccion direccion;
      try {
        direccion = em.getReference(Direccion.class, id);
        direccion.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.", enfe);
      }
      Distrito distritoId = direccion.getDistritoId();
      if (distritoId != null) {
        distritoId.getDireccionCollection().remove(direccion);
        distritoId = em.merge(distritoId);
      }
      Collection<DireccionPersona> direccionPersonaCollection = direccion.getDireccionPersonaCollection();
      for (DireccionPersona direccionPersonaCollectionDireccionPersona : direccionPersonaCollection) {
        direccionPersonaCollectionDireccionPersona.setDireccionId(null);
        direccionPersonaCollectionDireccionPersona = em.merge(direccionPersonaCollectionDireccionPersona);
      }
      em.remove(direccion);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Direccion> findDireccionEntities() {
    return findDireccionEntities(true, -1, -1);
  }

  public List<Direccion> findDireccionEntities(int maxResults, int firstResult) {
    return findDireccionEntities(false, maxResults, firstResult);
  }

  private List<Direccion> findDireccionEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Direccion.class));
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

  public Direccion findDireccion(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Direccion.class, id);
    } finally {
      em.close();
    }
  }

  public int getDireccionCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Direccion> rt = cq.from(Direccion.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }

}
