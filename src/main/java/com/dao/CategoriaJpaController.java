/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import com.dto.Categoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dto.Servicio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class CategoriaJpaController implements Serializable {

  public CategoriaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Categoria categoria) {
    if (categoria.getServicioCollection() == null) {
      categoria.setServicioCollection(new ArrayList<Servicio>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Collection<Servicio> attachedServicioCollection = new ArrayList<Servicio>();
      for (Servicio servicioCollectionServicioToAttach : categoria.getServicioCollection()) {
        servicioCollectionServicioToAttach = em.getReference(servicioCollectionServicioToAttach.getClass(), servicioCollectionServicioToAttach.getId());
        attachedServicioCollection.add(servicioCollectionServicioToAttach);
      }
      categoria.setServicioCollection(attachedServicioCollection);
      em.persist(categoria);
      for (Servicio servicioCollectionServicio : categoria.getServicioCollection()) {
        Categoria oldCategoriaIdOfServicioCollectionServicio = servicioCollectionServicio.getCategoriaId();
        servicioCollectionServicio.setCategoriaId(categoria);
        servicioCollectionServicio = em.merge(servicioCollectionServicio);
        if (oldCategoriaIdOfServicioCollectionServicio != null) {
          oldCategoriaIdOfServicioCollectionServicio.getServicioCollection().remove(servicioCollectionServicio);
          oldCategoriaIdOfServicioCollectionServicio = em.merge(oldCategoriaIdOfServicioCollectionServicio);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Categoria categoria) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Categoria persistentCategoria = em.find(Categoria.class, categoria.getId());
      Collection<Servicio> servicioCollectionOld = persistentCategoria.getServicioCollection();
      Collection<Servicio> servicioCollectionNew = categoria.getServicioCollection();
      Collection<Servicio> attachedServicioCollectionNew = new ArrayList<Servicio>();
      for (Servicio servicioCollectionNewServicioToAttach : servicioCollectionNew) {
        servicioCollectionNewServicioToAttach = em.getReference(servicioCollectionNewServicioToAttach.getClass(), servicioCollectionNewServicioToAttach.getId());
        attachedServicioCollectionNew.add(servicioCollectionNewServicioToAttach);
      }
      servicioCollectionNew = attachedServicioCollectionNew;
      categoria.setServicioCollection(servicioCollectionNew);
      categoria = em.merge(categoria);
      for (Servicio servicioCollectionOldServicio : servicioCollectionOld) {
        if (!servicioCollectionNew.contains(servicioCollectionOldServicio)) {
          servicioCollectionOldServicio.setCategoriaId(null);
          servicioCollectionOldServicio = em.merge(servicioCollectionOldServicio);
        }
      }
      for (Servicio servicioCollectionNewServicio : servicioCollectionNew) {
        if (!servicioCollectionOld.contains(servicioCollectionNewServicio)) {
          Categoria oldCategoriaIdOfServicioCollectionNewServicio = servicioCollectionNewServicio.getCategoriaId();
          servicioCollectionNewServicio.setCategoriaId(categoria);
          servicioCollectionNewServicio = em.merge(servicioCollectionNewServicio);
          if (oldCategoriaIdOfServicioCollectionNewServicio != null && !oldCategoriaIdOfServicioCollectionNewServicio.equals(categoria)) {
            oldCategoriaIdOfServicioCollectionNewServicio.getServicioCollection().remove(servicioCollectionNewServicio);
            oldCategoriaIdOfServicioCollectionNewServicio = em.merge(oldCategoriaIdOfServicioCollectionNewServicio);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = categoria.getId();
        if (findCategoria(id) == null) {
          throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void softDelete(Categoria categoria) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Categoria persistentCategoria = em.find(Categoria.class, categoria.getId());
      Collection<Servicio> servicioCollectionOld = persistentCategoria.getServicioCollection();
      Collection<Servicio> servicioCollectionNew = categoria.getServicioCollection();
      Collection<Servicio> attachedServicioCollectionNew = new ArrayList<Servicio>();
      for (Servicio servicioCollectionNewServicioToAttach : servicioCollectionNew) {
        servicioCollectionNewServicioToAttach = em.getReference(servicioCollectionNewServicioToAttach.getClass(), servicioCollectionNewServicioToAttach.getId());
        attachedServicioCollectionNew.add(servicioCollectionNewServicioToAttach);
      }
      servicioCollectionNew = attachedServicioCollectionNew;
      categoria.setServicioCollection(servicioCollectionNew);
      categoria = em.merge(categoria);
      for (Servicio servicioCollectionOldServicio : servicioCollectionOld) {
        if (!servicioCollectionNew.contains(servicioCollectionOldServicio)) {
          servicioCollectionOldServicio.setCategoriaId(null);
          servicioCollectionOldServicio = em.merge(servicioCollectionOldServicio);
        }
      }
      for (Servicio servicioCollectionNewServicio : servicioCollectionNew) {
        if (!servicioCollectionOld.contains(servicioCollectionNewServicio)) {
          Categoria oldCategoriaIdOfServicioCollectionNewServicio = servicioCollectionNewServicio.getCategoriaId();
          servicioCollectionNewServicio.setCategoriaId(categoria);
          servicioCollectionNewServicio = em.merge(servicioCollectionNewServicio);
          if (oldCategoriaIdOfServicioCollectionNewServicio != null && !oldCategoriaIdOfServicioCollectionNewServicio.equals(categoria)) {
            oldCategoriaIdOfServicioCollectionNewServicio.getServicioCollection().remove(servicioCollectionNewServicio);
            oldCategoriaIdOfServicioCollectionNewServicio = em.merge(oldCategoriaIdOfServicioCollectionNewServicio);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = categoria.getId();
        if (findCategoria(id) == null) {
          throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
      Categoria categoria;
      try {
        categoria = em.getReference(Categoria.class, id);
        categoria.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
      }
      Collection<Servicio> servicioCollection = categoria.getServicioCollection();
      for (Servicio servicioCollectionServicio : servicioCollection) {
        servicioCollectionServicio.setCategoriaId(null);
        servicioCollectionServicio = em.merge(servicioCollectionServicio);
      }
      em.remove(categoria);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Categoria> findCategoriaEntities() {
    return findCategoriaEntities(true, -1, -1);
  }

  public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
    return findCategoriaEntities(false, maxResults, firstResult);
  }

  private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Categoria.class));
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

  public Categoria findCategoria(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Categoria.class, id);
    } finally {
      em.close();
    }
  }

  public int getCategoriaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Categoria> rt = cq.from(Categoria.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
