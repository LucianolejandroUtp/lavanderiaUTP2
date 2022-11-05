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
import com.dto.Departamento;
import com.dto.Direccion;
import com.dto.Distrito;
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
  private EntityManagerFactory emf = null;

  public DistritoJpaController() {
    emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
  }

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Distrito distrito) {
    if (distrito.getDireccionCollection() == null) {
      distrito.setDireccionCollection(new ArrayList<Direccion>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Departamento departamentoId = distrito.getDepartamentoId();
      if (departamentoId != null) {
        departamentoId = em.getReference(departamentoId.getClass(), departamentoId.getId());
        distrito.setDepartamentoId(departamentoId);
      }
      Collection<Direccion> attachedDireccionCollection = new ArrayList<Direccion>();
      for (Direccion direccionCollectionDireccionToAttach : distrito.getDireccionCollection()) {
        direccionCollectionDireccionToAttach = em.getReference(direccionCollectionDireccionToAttach.getClass(), direccionCollectionDireccionToAttach.getId());
        attachedDireccionCollection.add(direccionCollectionDireccionToAttach);
      }
      distrito.setDireccionCollection(attachedDireccionCollection);
      em.persist(distrito);
      if (departamentoId != null) {
        departamentoId.getDistritoCollection().add(distrito);
        departamentoId = em.merge(departamentoId);
      }
      for (Direccion direccionCollectionDireccion : distrito.getDireccionCollection()) {
        Distrito oldDistritoIdOfDireccionCollectionDireccion = direccionCollectionDireccion.getDistritoId();
        direccionCollectionDireccion.setDistritoId(distrito);
        direccionCollectionDireccion = em.merge(direccionCollectionDireccion);
        if (oldDistritoIdOfDireccionCollectionDireccion != null) {
          oldDistritoIdOfDireccionCollectionDireccion.getDireccionCollection().remove(direccionCollectionDireccion);
          oldDistritoIdOfDireccionCollectionDireccion = em.merge(oldDistritoIdOfDireccionCollectionDireccion);
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
      Departamento departamentoIdOld = persistentDistrito.getDepartamentoId();
      Departamento departamentoIdNew = distrito.getDepartamentoId();
      Collection<Direccion> direccionCollectionOld = persistentDistrito.getDireccionCollection();
      Collection<Direccion> direccionCollectionNew = distrito.getDireccionCollection();
      if (departamentoIdNew != null) {
        departamentoIdNew = em.getReference(departamentoIdNew.getClass(), departamentoIdNew.getId());
        distrito.setDepartamentoId(departamentoIdNew);
      }
      Collection<Direccion> attachedDireccionCollectionNew = new ArrayList<Direccion>();
      for (Direccion direccionCollectionNewDireccionToAttach : direccionCollectionNew) {
        direccionCollectionNewDireccionToAttach = em.getReference(direccionCollectionNewDireccionToAttach.getClass(), direccionCollectionNewDireccionToAttach.getId());
        attachedDireccionCollectionNew.add(direccionCollectionNewDireccionToAttach);
      }
      direccionCollectionNew = attachedDireccionCollectionNew;
      distrito.setDireccionCollection(direccionCollectionNew);
      distrito = em.merge(distrito);
      if (departamentoIdOld != null && !departamentoIdOld.equals(departamentoIdNew)) {
        departamentoIdOld.getDistritoCollection().remove(distrito);
        departamentoIdOld = em.merge(departamentoIdOld);
      }
      if (departamentoIdNew != null && !departamentoIdNew.equals(departamentoIdOld)) {
        departamentoIdNew.getDistritoCollection().add(distrito);
        departamentoIdNew = em.merge(departamentoIdNew);
      }
      for (Direccion direccionCollectionOldDireccion : direccionCollectionOld) {
        if (!direccionCollectionNew.contains(direccionCollectionOldDireccion)) {
          direccionCollectionOldDireccion.setDistritoId(null);
          direccionCollectionOldDireccion = em.merge(direccionCollectionOldDireccion);
        }
      }
      for (Direccion direccionCollectionNewDireccion : direccionCollectionNew) {
        if (!direccionCollectionOld.contains(direccionCollectionNewDireccion)) {
          Distrito oldDistritoIdOfDireccionCollectionNewDireccion = direccionCollectionNewDireccion.getDistritoId();
          direccionCollectionNewDireccion.setDistritoId(distrito);
          direccionCollectionNewDireccion = em.merge(direccionCollectionNewDireccion);
          if (oldDistritoIdOfDireccionCollectionNewDireccion != null && !oldDistritoIdOfDireccionCollectionNewDireccion.equals(distrito)) {
            oldDistritoIdOfDireccionCollectionNewDireccion.getDireccionCollection().remove(direccionCollectionNewDireccion);
            oldDistritoIdOfDireccionCollectionNewDireccion = em.merge(oldDistritoIdOfDireccionCollectionNewDireccion);
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

  public void softDelete(Distrito distrito) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Distrito persistentDistrito = em.find(Distrito.class, distrito.getId());
      Departamento departamentoIdOld = persistentDistrito.getDepartamentoId();
      Departamento departamentoIdNew = distrito.getDepartamentoId();
      Collection<Direccion> direccionCollectionOld = persistentDistrito.getDireccionCollection();
      Collection<Direccion> direccionCollectionNew = distrito.getDireccionCollection();
      if (departamentoIdNew != null) {
        departamentoIdNew = em.getReference(departamentoIdNew.getClass(), departamentoIdNew.getId());
        distrito.setDepartamentoId(departamentoIdNew);
      }
      Collection<Direccion> attachedDireccionCollectionNew = new ArrayList<Direccion>();
      for (Direccion direccionCollectionNewDireccionToAttach : direccionCollectionNew) {
        direccionCollectionNewDireccionToAttach = em.getReference(direccionCollectionNewDireccionToAttach.getClass(), direccionCollectionNewDireccionToAttach.getId());
        attachedDireccionCollectionNew.add(direccionCollectionNewDireccionToAttach);
      }
      direccionCollectionNew = attachedDireccionCollectionNew;
      distrito.setDireccionCollection(direccionCollectionNew);
      distrito = em.merge(distrito);
      if (departamentoIdOld != null && !departamentoIdOld.equals(departamentoIdNew)) {
        departamentoIdOld.getDistritoCollection().remove(distrito);
        departamentoIdOld = em.merge(departamentoIdOld);
      }
      if (departamentoIdNew != null && !departamentoIdNew.equals(departamentoIdOld)) {
        departamentoIdNew.getDistritoCollection().add(distrito);
        departamentoIdNew = em.merge(departamentoIdNew);
      }
      for (Direccion direccionCollectionOldDireccion : direccionCollectionOld) {
        if (!direccionCollectionNew.contains(direccionCollectionOldDireccion)) {
          direccionCollectionOldDireccion.setDistritoId(null);
          direccionCollectionOldDireccion = em.merge(direccionCollectionOldDireccion);
        }
      }
      for (Direccion direccionCollectionNewDireccion : direccionCollectionNew) {
        if (!direccionCollectionOld.contains(direccionCollectionNewDireccion)) {
          Distrito oldDistritoIdOfDireccionCollectionNewDireccion = direccionCollectionNewDireccion.getDistritoId();
          direccionCollectionNewDireccion.setDistritoId(distrito);
          direccionCollectionNewDireccion = em.merge(direccionCollectionNewDireccion);
          if (oldDistritoIdOfDireccionCollectionNewDireccion != null && !oldDistritoIdOfDireccionCollectionNewDireccion.equals(distrito)) {
            oldDistritoIdOfDireccionCollectionNewDireccion.getDireccionCollection().remove(direccionCollectionNewDireccion);
            oldDistritoIdOfDireccionCollectionNewDireccion = em.merge(oldDistritoIdOfDireccionCollectionNewDireccion);
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
      Departamento departamentoId = distrito.getDepartamentoId();
      if (departamentoId != null) {
        departamentoId.getDistritoCollection().remove(distrito);
        departamentoId = em.merge(departamentoId);
      }
      Collection<Direccion> direccionCollection = distrito.getDireccionCollection();
      for (Direccion direccionCollectionDireccion : direccionCollection) {
        direccionCollectionDireccion.setDistritoId(null);
        direccionCollectionDireccion = em.merge(direccionCollectionDireccion);
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
