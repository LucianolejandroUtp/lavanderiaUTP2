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
import com.dto.DetalleComprobante;
import java.util.ArrayList;
import java.util.Collection;
import com.dto.Prenda;
import com.dto.Servicio;
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
    if (servicio.getDetalleComprobanteCollection() == null) {
      servicio.setDetalleComprobanteCollection(new ArrayList<DetalleComprobante>());
    }
    if (servicio.getPrendaCollection() == null) {
      servicio.setPrendaCollection(new ArrayList<Prenda>());
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
      Collection<DetalleComprobante> attachedDetalleComprobanteCollection = new ArrayList<DetalleComprobante>();
      for (DetalleComprobante detalleComprobanteCollectionDetalleComprobanteToAttach : servicio.getDetalleComprobanteCollection()) {
        detalleComprobanteCollectionDetalleComprobanteToAttach = em.getReference(detalleComprobanteCollectionDetalleComprobanteToAttach.getClass(), detalleComprobanteCollectionDetalleComprobanteToAttach.getId());
        attachedDetalleComprobanteCollection.add(detalleComprobanteCollectionDetalleComprobanteToAttach);
      }
      servicio.setDetalleComprobanteCollection(attachedDetalleComprobanteCollection);
      Collection<Prenda> attachedPrendaCollection = new ArrayList<Prenda>();
      for (Prenda prendaCollectionPrendaToAttach : servicio.getPrendaCollection()) {
        prendaCollectionPrendaToAttach = em.getReference(prendaCollectionPrendaToAttach.getClass(), prendaCollectionPrendaToAttach.getId());
        attachedPrendaCollection.add(prendaCollectionPrendaToAttach);
      }
      servicio.setPrendaCollection(attachedPrendaCollection);
      em.persist(servicio);
      if (categoriaId != null) {
        categoriaId.getServicioCollection().add(servicio);
        categoriaId = em.merge(categoriaId);
      }
      for (DetalleComprobante detalleComprobanteCollectionDetalleComprobante : servicio.getDetalleComprobanteCollection()) {
        Servicio oldServicioIdOfDetalleComprobanteCollectionDetalleComprobante = detalleComprobanteCollectionDetalleComprobante.getServicioId();
        detalleComprobanteCollectionDetalleComprobante.setServicioId(servicio);
        detalleComprobanteCollectionDetalleComprobante = em.merge(detalleComprobanteCollectionDetalleComprobante);
        if (oldServicioIdOfDetalleComprobanteCollectionDetalleComprobante != null) {
          oldServicioIdOfDetalleComprobanteCollectionDetalleComprobante.getDetalleComprobanteCollection().remove(detalleComprobanteCollectionDetalleComprobante);
          oldServicioIdOfDetalleComprobanteCollectionDetalleComprobante = em.merge(oldServicioIdOfDetalleComprobanteCollectionDetalleComprobante);
        }
      }
      for (Prenda prendaCollectionPrenda : servicio.getPrendaCollection()) {
        Servicio oldServicioIdOfPrendaCollectionPrenda = prendaCollectionPrenda.getServicioId();
        prendaCollectionPrenda.setServicioId(servicio);
        prendaCollectionPrenda = em.merge(prendaCollectionPrenda);
        if (oldServicioIdOfPrendaCollectionPrenda != null) {
          oldServicioIdOfPrendaCollectionPrenda.getPrendaCollection().remove(prendaCollectionPrenda);
          oldServicioIdOfPrendaCollectionPrenda = em.merge(oldServicioIdOfPrendaCollectionPrenda);
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
      Collection<DetalleComprobante> detalleComprobanteCollectionOld = persistentServicio.getDetalleComprobanteCollection();
      Collection<DetalleComprobante> detalleComprobanteCollectionNew = servicio.getDetalleComprobanteCollection();
      Collection<Prenda> prendaCollectionOld = persistentServicio.getPrendaCollection();
      Collection<Prenda> prendaCollectionNew = servicio.getPrendaCollection();
      if (categoriaIdNew != null) {
        categoriaIdNew = em.getReference(categoriaIdNew.getClass(), categoriaIdNew.getId());
        servicio.setCategoriaId(categoriaIdNew);
      }
      Collection<DetalleComprobante> attachedDetalleComprobanteCollectionNew = new ArrayList<DetalleComprobante>();
      for (DetalleComprobante detalleComprobanteCollectionNewDetalleComprobanteToAttach : detalleComprobanteCollectionNew) {
        detalleComprobanteCollectionNewDetalleComprobanteToAttach = em.getReference(detalleComprobanteCollectionNewDetalleComprobanteToAttach.getClass(), detalleComprobanteCollectionNewDetalleComprobanteToAttach.getId());
        attachedDetalleComprobanteCollectionNew.add(detalleComprobanteCollectionNewDetalleComprobanteToAttach);
      }
      detalleComprobanteCollectionNew = attachedDetalleComprobanteCollectionNew;
      servicio.setDetalleComprobanteCollection(detalleComprobanteCollectionNew);
      Collection<Prenda> attachedPrendaCollectionNew = new ArrayList<Prenda>();
      for (Prenda prendaCollectionNewPrendaToAttach : prendaCollectionNew) {
        prendaCollectionNewPrendaToAttach = em.getReference(prendaCollectionNewPrendaToAttach.getClass(), prendaCollectionNewPrendaToAttach.getId());
        attachedPrendaCollectionNew.add(prendaCollectionNewPrendaToAttach);
      }
      prendaCollectionNew = attachedPrendaCollectionNew;
      servicio.setPrendaCollection(prendaCollectionNew);
      servicio = em.merge(servicio);
      if (categoriaIdOld != null && !categoriaIdOld.equals(categoriaIdNew)) {
        categoriaIdOld.getServicioCollection().remove(servicio);
        categoriaIdOld = em.merge(categoriaIdOld);
      }
      if (categoriaIdNew != null && !categoriaIdNew.equals(categoriaIdOld)) {
        categoriaIdNew.getServicioCollection().add(servicio);
        categoriaIdNew = em.merge(categoriaIdNew);
      }
      for (DetalleComprobante detalleComprobanteCollectionOldDetalleComprobante : detalleComprobanteCollectionOld) {
        if (!detalleComprobanteCollectionNew.contains(detalleComprobanteCollectionOldDetalleComprobante)) {
          detalleComprobanteCollectionOldDetalleComprobante.setServicioId(null);
          detalleComprobanteCollectionOldDetalleComprobante = em.merge(detalleComprobanteCollectionOldDetalleComprobante);
        }
      }
      for (DetalleComprobante detalleComprobanteCollectionNewDetalleComprobante : detalleComprobanteCollectionNew) {
        if (!detalleComprobanteCollectionOld.contains(detalleComprobanteCollectionNewDetalleComprobante)) {
          Servicio oldServicioIdOfDetalleComprobanteCollectionNewDetalleComprobante = detalleComprobanteCollectionNewDetalleComprobante.getServicioId();
          detalleComprobanteCollectionNewDetalleComprobante.setServicioId(servicio);
          detalleComprobanteCollectionNewDetalleComprobante = em.merge(detalleComprobanteCollectionNewDetalleComprobante);
          if (oldServicioIdOfDetalleComprobanteCollectionNewDetalleComprobante != null && !oldServicioIdOfDetalleComprobanteCollectionNewDetalleComprobante.equals(servicio)) {
            oldServicioIdOfDetalleComprobanteCollectionNewDetalleComprobante.getDetalleComprobanteCollection().remove(detalleComprobanteCollectionNewDetalleComprobante);
            oldServicioIdOfDetalleComprobanteCollectionNewDetalleComprobante = em.merge(oldServicioIdOfDetalleComprobanteCollectionNewDetalleComprobante);
          }
        }
      }
      for (Prenda prendaCollectionOldPrenda : prendaCollectionOld) {
        if (!prendaCollectionNew.contains(prendaCollectionOldPrenda)) {
          prendaCollectionOldPrenda.setServicioId(null);
          prendaCollectionOldPrenda = em.merge(prendaCollectionOldPrenda);
        }
      }
      for (Prenda prendaCollectionNewPrenda : prendaCollectionNew) {
        if (!prendaCollectionOld.contains(prendaCollectionNewPrenda)) {
          Servicio oldServicioIdOfPrendaCollectionNewPrenda = prendaCollectionNewPrenda.getServicioId();
          prendaCollectionNewPrenda.setServicioId(servicio);
          prendaCollectionNewPrenda = em.merge(prendaCollectionNewPrenda);
          if (oldServicioIdOfPrendaCollectionNewPrenda != null && !oldServicioIdOfPrendaCollectionNewPrenda.equals(servicio)) {
            oldServicioIdOfPrendaCollectionNewPrenda.getPrendaCollection().remove(prendaCollectionNewPrenda);
            oldServicioIdOfPrendaCollectionNewPrenda = em.merge(oldServicioIdOfPrendaCollectionNewPrenda);
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
      Collection<DetalleComprobante> detalleComprobanteCollection = servicio.getDetalleComprobanteCollection();
      for (DetalleComprobante detalleComprobanteCollectionDetalleComprobante : detalleComprobanteCollection) {
        detalleComprobanteCollectionDetalleComprobante.setServicioId(null);
        detalleComprobanteCollectionDetalleComprobante = em.merge(detalleComprobanteCollectionDetalleComprobante);
      }
      Collection<Prenda> prendaCollection = servicio.getPrendaCollection();
      for (Prenda prendaCollectionPrenda : prendaCollection) {
        prendaCollectionPrenda.setServicioId(null);
        prendaCollectionPrenda = em.merge(prendaCollectionPrenda);
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
