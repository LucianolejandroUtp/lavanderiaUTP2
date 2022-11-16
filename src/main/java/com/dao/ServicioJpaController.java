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
import com.dto.Categoria;
import com.dto.DetalleFactura;
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
public class ServicioJpaController implements Serializable {

  public ServicioJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Servicio servicio) {
    if (servicio.getDetalleFacturaCollection() == null) {
      servicio.setDetalleFacturaCollection(new ArrayList<DetalleFactura>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Categoria categoriaId = servicio.getCategoriaId();
      if (categoriaId != null) {
        categoriaId = em.getReference(categoriaId.getClass(), categoriaId.getId());
        servicio.setCategoriaId(categoriaId);
      }
      Collection<DetalleFactura> attachedDetalleFacturaCollection = new ArrayList<DetalleFactura>();
      for (DetalleFactura detalleFacturaCollectionDetalleFacturaToAttach : servicio.getDetalleFacturaCollection()) {
        detalleFacturaCollectionDetalleFacturaToAttach = em.getReference(detalleFacturaCollectionDetalleFacturaToAttach.getClass(), detalleFacturaCollectionDetalleFacturaToAttach.getId());
        attachedDetalleFacturaCollection.add(detalleFacturaCollectionDetalleFacturaToAttach);
      }
      servicio.setDetalleFacturaCollection(attachedDetalleFacturaCollection);
      em.persist(servicio);
      if (categoriaId != null) {
        categoriaId.getServicioCollection().add(servicio);
        categoriaId = em.merge(categoriaId);
      }
      for (DetalleFactura detalleFacturaCollectionDetalleFactura : servicio.getDetalleFacturaCollection()) {
        Servicio oldServicioIdOfDetalleFacturaCollectionDetalleFactura = detalleFacturaCollectionDetalleFactura.getServicioId();
        detalleFacturaCollectionDetalleFactura.setServicioId(servicio);
        detalleFacturaCollectionDetalleFactura = em.merge(detalleFacturaCollectionDetalleFactura);
        if (oldServicioIdOfDetalleFacturaCollectionDetalleFactura != null) {
          oldServicioIdOfDetalleFacturaCollectionDetalleFactura.getDetalleFacturaCollection().remove(detalleFacturaCollectionDetalleFactura);
          oldServicioIdOfDetalleFacturaCollectionDetalleFactura = em.merge(oldServicioIdOfDetalleFacturaCollectionDetalleFactura);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Servicio persistentServicio = em.find(Servicio.class, servicio.getId());
      Categoria categoriaIdOld = persistentServicio.getCategoriaId();
      Categoria categoriaIdNew = servicio.getCategoriaId();
      Collection<DetalleFactura> detalleFacturaCollectionOld = persistentServicio.getDetalleFacturaCollection();
      Collection<DetalleFactura> detalleFacturaCollectionNew = servicio.getDetalleFacturaCollection();
      if (categoriaIdNew != null) {
        categoriaIdNew = em.getReference(categoriaIdNew.getClass(), categoriaIdNew.getId());
        servicio.setCategoriaId(categoriaIdNew);
      }
      Collection<DetalleFactura> attachedDetalleFacturaCollectionNew = new ArrayList<DetalleFactura>();
      for (DetalleFactura detalleFacturaCollectionNewDetalleFacturaToAttach : detalleFacturaCollectionNew) {
        detalleFacturaCollectionNewDetalleFacturaToAttach = em.getReference(detalleFacturaCollectionNewDetalleFacturaToAttach.getClass(), detalleFacturaCollectionNewDetalleFacturaToAttach.getId());
        attachedDetalleFacturaCollectionNew.add(detalleFacturaCollectionNewDetalleFacturaToAttach);
      }
      detalleFacturaCollectionNew = attachedDetalleFacturaCollectionNew;
      servicio.setDetalleFacturaCollection(detalleFacturaCollectionNew);
      servicio = em.merge(servicio);
      if (categoriaIdOld != null && !categoriaIdOld.equals(categoriaIdNew)) {
        categoriaIdOld.getServicioCollection().remove(servicio);
        categoriaIdOld = em.merge(categoriaIdOld);
      }
      if (categoriaIdNew != null && !categoriaIdNew.equals(categoriaIdOld)) {
        categoriaIdNew.getServicioCollection().add(servicio);
        categoriaIdNew = em.merge(categoriaIdNew);
      }
      for (DetalleFactura detalleFacturaCollectionOldDetalleFactura : detalleFacturaCollectionOld) {
        if (!detalleFacturaCollectionNew.contains(detalleFacturaCollectionOldDetalleFactura)) {
          detalleFacturaCollectionOldDetalleFactura.setServicioId(null);
          detalleFacturaCollectionOldDetalleFactura = em.merge(detalleFacturaCollectionOldDetalleFactura);
        }
      }
      for (DetalleFactura detalleFacturaCollectionNewDetalleFactura : detalleFacturaCollectionNew) {
        if (!detalleFacturaCollectionOld.contains(detalleFacturaCollectionNewDetalleFactura)) {
          Servicio oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura = detalleFacturaCollectionNewDetalleFactura.getServicioId();
          detalleFacturaCollectionNewDetalleFactura.setServicioId(servicio);
          detalleFacturaCollectionNewDetalleFactura = em.merge(detalleFacturaCollectionNewDetalleFactura);
          if (oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura != null && !oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura.equals(servicio)) {
            oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura.getDetalleFacturaCollection().remove(detalleFacturaCollectionNewDetalleFactura);
            oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura = em.merge(oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = servicio.getId();
        if (findServicio(id) == null) {
          throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void softDelete(Servicio servicio) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Servicio persistentServicio = em.find(Servicio.class, servicio.getId());
      Categoria categoriaIdOld = persistentServicio.getCategoriaId();
      Categoria categoriaIdNew = servicio.getCategoriaId();
      Collection<DetalleFactura> detalleFacturaCollectionOld = persistentServicio.getDetalleFacturaCollection();
      Collection<DetalleFactura> detalleFacturaCollectionNew = servicio.getDetalleFacturaCollection();
      if (categoriaIdNew != null) {
        categoriaIdNew = em.getReference(categoriaIdNew.getClass(), categoriaIdNew.getId());
        servicio.setCategoriaId(categoriaIdNew);
      }
      Collection<DetalleFactura> attachedDetalleFacturaCollectionNew = new ArrayList<DetalleFactura>();
      for (DetalleFactura detalleFacturaCollectionNewDetalleFacturaToAttach : detalleFacturaCollectionNew) {
        detalleFacturaCollectionNewDetalleFacturaToAttach = em.getReference(detalleFacturaCollectionNewDetalleFacturaToAttach.getClass(), detalleFacturaCollectionNewDetalleFacturaToAttach.getId());
        attachedDetalleFacturaCollectionNew.add(detalleFacturaCollectionNewDetalleFacturaToAttach);
      }
      detalleFacturaCollectionNew = attachedDetalleFacturaCollectionNew;
      servicio.setDetalleFacturaCollection(detalleFacturaCollectionNew);
      servicio = em.merge(servicio);
      if (categoriaIdOld != null && !categoriaIdOld.equals(categoriaIdNew)) {
        categoriaIdOld.getServicioCollection().remove(servicio);
        categoriaIdOld = em.merge(categoriaIdOld);
      }
      if (categoriaIdNew != null && !categoriaIdNew.equals(categoriaIdOld)) {
        categoriaIdNew.getServicioCollection().add(servicio);
        categoriaIdNew = em.merge(categoriaIdNew);
      }
      for (DetalleFactura detalleFacturaCollectionOldDetalleFactura : detalleFacturaCollectionOld) {
        if (!detalleFacturaCollectionNew.contains(detalleFacturaCollectionOldDetalleFactura)) {
          detalleFacturaCollectionOldDetalleFactura.setServicioId(null);
          detalleFacturaCollectionOldDetalleFactura = em.merge(detalleFacturaCollectionOldDetalleFactura);
        }
      }
      for (DetalleFactura detalleFacturaCollectionNewDetalleFactura : detalleFacturaCollectionNew) {
        if (!detalleFacturaCollectionOld.contains(detalleFacturaCollectionNewDetalleFactura)) {
          Servicio oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura = detalleFacturaCollectionNewDetalleFactura.getServicioId();
          detalleFacturaCollectionNewDetalleFactura.setServicioId(servicio);
          detalleFacturaCollectionNewDetalleFactura = em.merge(detalleFacturaCollectionNewDetalleFactura);
          if (oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura != null && !oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura.equals(servicio)) {
            oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura.getDetalleFacturaCollection().remove(detalleFacturaCollectionNewDetalleFactura);
            oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura = em.merge(oldServicioIdOfDetalleFacturaCollectionNewDetalleFactura);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = servicio.getId();
        if (findServicio(id) == null) {
          throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
      Servicio servicio;
      try {
        servicio = em.getReference(Servicio.class, id);
        servicio.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
      }
      Categoria categoriaId = servicio.getCategoriaId();
      if (categoriaId != null) {
        categoriaId.getServicioCollection().remove(servicio);
        categoriaId = em.merge(categoriaId);
      }
      Collection<DetalleFactura> detalleFacturaCollection = servicio.getDetalleFacturaCollection();
      for (DetalleFactura detalleFacturaCollectionDetalleFactura : detalleFacturaCollection) {
        detalleFacturaCollectionDetalleFactura.setServicioId(null);
        detalleFacturaCollectionDetalleFactura = em.merge(detalleFacturaCollectionDetalleFactura);
      }
      em.remove(servicio);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Servicio> findServicioEntities() {
    return findServicioEntities(true, -1, -1);
  }

  public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
    return findServicioEntities(false, maxResults, firstResult);
  }

  private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Servicio.class));
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

  public Servicio findServicio(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Servicio.class, id);
    } finally {
      em.close();
    }
  }

  public int getServicioCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Servicio> rt = cq.from(Servicio.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
