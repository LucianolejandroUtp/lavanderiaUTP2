/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import com.dto.Departamento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class DepartamentoJpaController implements Serializable {

  public DepartamentoJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public DepartamentoJpaController() {
    emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
  }

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Departamento departamento) {
    if (departamento.getDistritoCollection() == null) {
      departamento.setDistritoCollection(new ArrayList<Distrito>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Collection<Distrito> attachedDistritoCollection = new ArrayList<Distrito>();
      for (Distrito distritoCollectionDistritoToAttach : departamento.getDistritoCollection()) {
        distritoCollectionDistritoToAttach = em.getReference(distritoCollectionDistritoToAttach.getClass(), distritoCollectionDistritoToAttach.getId());
        attachedDistritoCollection.add(distritoCollectionDistritoToAttach);
      }
      departamento.setDistritoCollection(attachedDistritoCollection);
      em.persist(departamento);
      for (Distrito distritoCollectionDistrito : departamento.getDistritoCollection()) {
        Departamento oldDepartamentoIdOfDistritoCollectionDistrito = distritoCollectionDistrito.getDepartamentoId();
        distritoCollectionDistrito.setDepartamentoId(departamento);
        distritoCollectionDistrito = em.merge(distritoCollectionDistrito);
        if (oldDepartamentoIdOfDistritoCollectionDistrito != null) {
          oldDepartamentoIdOfDistritoCollectionDistrito.getDistritoCollection().remove(distritoCollectionDistrito);
          oldDepartamentoIdOfDistritoCollectionDistrito = em.merge(oldDepartamentoIdOfDistritoCollectionDistrito);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Departamento departamento) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Departamento persistentDepartamento = em.find(Departamento.class, departamento.getId());
      Collection<Distrito> distritoCollectionOld = persistentDepartamento.getDistritoCollection();
      Collection<Distrito> distritoCollectionNew = departamento.getDistritoCollection();
      Collection<Distrito> attachedDistritoCollectionNew = new ArrayList<Distrito>();
      for (Distrito distritoCollectionNewDistritoToAttach : distritoCollectionNew) {
        distritoCollectionNewDistritoToAttach = em.getReference(distritoCollectionNewDistritoToAttach.getClass(), distritoCollectionNewDistritoToAttach.getId());
        attachedDistritoCollectionNew.add(distritoCollectionNewDistritoToAttach);
      }
      distritoCollectionNew = attachedDistritoCollectionNew;
      departamento.setDistritoCollection(distritoCollectionNew);
      departamento = em.merge(departamento);
      for (Distrito distritoCollectionOldDistrito : distritoCollectionOld) {
        if (!distritoCollectionNew.contains(distritoCollectionOldDistrito)) {
          distritoCollectionOldDistrito.setDepartamentoId(null);
          distritoCollectionOldDistrito = em.merge(distritoCollectionOldDistrito);
        }
      }
      for (Distrito distritoCollectionNewDistrito : distritoCollectionNew) {
        if (!distritoCollectionOld.contains(distritoCollectionNewDistrito)) {
          Departamento oldDepartamentoIdOfDistritoCollectionNewDistrito = distritoCollectionNewDistrito.getDepartamentoId();
          distritoCollectionNewDistrito.setDepartamentoId(departamento);
          distritoCollectionNewDistrito = em.merge(distritoCollectionNewDistrito);
          if (oldDepartamentoIdOfDistritoCollectionNewDistrito != null && !oldDepartamentoIdOfDistritoCollectionNewDistrito.equals(departamento)) {
            oldDepartamentoIdOfDistritoCollectionNewDistrito.getDistritoCollection().remove(distritoCollectionNewDistrito);
            oldDepartamentoIdOfDistritoCollectionNewDistrito = em.merge(oldDepartamentoIdOfDistritoCollectionNewDistrito);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = departamento.getId();
        if (findDepartamento(id) == null) {
          throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void softDelete(Departamento departamento) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Departamento persistentDepartamento = em.find(Departamento.class, departamento.getId());
      Collection<Distrito> distritoCollectionOld = persistentDepartamento.getDistritoCollection();
      Collection<Distrito> distritoCollectionNew = departamento.getDistritoCollection();
      Collection<Distrito> attachedDistritoCollectionNew = new ArrayList<Distrito>();
      for (Distrito distritoCollectionNewDistritoToAttach : distritoCollectionNew) {
        distritoCollectionNewDistritoToAttach = em.getReference(distritoCollectionNewDistritoToAttach.getClass(), distritoCollectionNewDistritoToAttach.getId());
        attachedDistritoCollectionNew.add(distritoCollectionNewDistritoToAttach);
      }
      distritoCollectionNew = attachedDistritoCollectionNew;
      departamento.setDistritoCollection(distritoCollectionNew);
      departamento = em.merge(departamento);
      for (Distrito distritoCollectionOldDistrito : distritoCollectionOld) {
        if (!distritoCollectionNew.contains(distritoCollectionOldDistrito)) {
          distritoCollectionOldDistrito.setDepartamentoId(null);
          distritoCollectionOldDistrito = em.merge(distritoCollectionOldDistrito);
        }
      }
      for (Distrito distritoCollectionNewDistrito : distritoCollectionNew) {
        if (!distritoCollectionOld.contains(distritoCollectionNewDistrito)) {
          Departamento oldDepartamentoIdOfDistritoCollectionNewDistrito = distritoCollectionNewDistrito.getDepartamentoId();
          distritoCollectionNewDistrito.setDepartamentoId(departamento);
          distritoCollectionNewDistrito = em.merge(distritoCollectionNewDistrito);
          if (oldDepartamentoIdOfDistritoCollectionNewDistrito != null && !oldDepartamentoIdOfDistritoCollectionNewDistrito.equals(departamento)) {
            oldDepartamentoIdOfDistritoCollectionNewDistrito.getDistritoCollection().remove(distritoCollectionNewDistrito);
            oldDepartamentoIdOfDistritoCollectionNewDistrito = em.merge(oldDepartamentoIdOfDistritoCollectionNewDistrito);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = departamento.getId();
        if (findDepartamento(id) == null) {
          throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
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
      Departamento departamento;
      try {
        departamento = em.getReference(Departamento.class, id);
        departamento.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
      }
      Collection<Distrito> distritoCollection = departamento.getDistritoCollection();
      for (Distrito distritoCollectionDistrito : distritoCollection) {
        distritoCollectionDistrito.setDepartamentoId(null);
        distritoCollectionDistrito = em.merge(distritoCollectionDistrito);
      }
      em.remove(departamento);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Departamento> findDepartamentoEntities() {
    return findDepartamentoEntities(true, -1, -1);
  }

  public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
    return findDepartamentoEntities(false, maxResults, firstResult);
  }

  private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Departamento.class));
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

  public Departamento findDepartamento(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Departamento.class, id);
    } finally {
      em.close();
    }
  }

  public int getDepartamentoCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Departamento> rt = cq.from(Departamento.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
