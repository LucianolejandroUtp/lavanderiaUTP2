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
import com.dto.Prenda;
import com.dto.TipoDePrenda;
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
public class TipoDePrendaJpaController implements Serializable {

  public TipoDePrendaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public TipoDePrendaJpaController() {
    emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
  }

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(TipoDePrenda tipoDePrenda) {
    if (tipoDePrenda.getPrendaCollection() == null) {
      tipoDePrenda.setPrendaCollection(new ArrayList<Prenda>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Collection<Prenda> attachedPrendaCollection = new ArrayList<Prenda>();
      for (Prenda prendaCollectionPrendaToAttach : tipoDePrenda.getPrendaCollection()) {
        prendaCollectionPrendaToAttach = em.getReference(prendaCollectionPrendaToAttach.getClass(), prendaCollectionPrendaToAttach.getId());
        attachedPrendaCollection.add(prendaCollectionPrendaToAttach);
      }
      tipoDePrenda.setPrendaCollection(attachedPrendaCollection);
      em.persist(tipoDePrenda);
      for (Prenda prendaCollectionPrenda : tipoDePrenda.getPrendaCollection()) {
        TipoDePrenda oldTipoDePrendaIdOfPrendaCollectionPrenda = prendaCollectionPrenda.getTipoDePrendaId();
        prendaCollectionPrenda.setTipoDePrendaId(tipoDePrenda);
        prendaCollectionPrenda = em.merge(prendaCollectionPrenda);
        if (oldTipoDePrendaIdOfPrendaCollectionPrenda != null) {
          oldTipoDePrendaIdOfPrendaCollectionPrenda.getPrendaCollection().remove(prendaCollectionPrenda);
          oldTipoDePrendaIdOfPrendaCollectionPrenda = em.merge(oldTipoDePrendaIdOfPrendaCollectionPrenda);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(TipoDePrenda tipoDePrenda) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      TipoDePrenda persistentTipoDePrenda = em.find(TipoDePrenda.class, tipoDePrenda.getId());
      Collection<Prenda> prendaCollectionOld = persistentTipoDePrenda.getPrendaCollection();
      Collection<Prenda> prendaCollectionNew = tipoDePrenda.getPrendaCollection();
      Collection<Prenda> attachedPrendaCollectionNew = new ArrayList<Prenda>();
      for (Prenda prendaCollectionNewPrendaToAttach : prendaCollectionNew) {
        prendaCollectionNewPrendaToAttach = em.getReference(prendaCollectionNewPrendaToAttach.getClass(), prendaCollectionNewPrendaToAttach.getId());
        attachedPrendaCollectionNew.add(prendaCollectionNewPrendaToAttach);
      }
      prendaCollectionNew = attachedPrendaCollectionNew;
      tipoDePrenda.setPrendaCollection(prendaCollectionNew);
      tipoDePrenda = em.merge(tipoDePrenda);
      for (Prenda prendaCollectionOldPrenda : prendaCollectionOld) {
        if (!prendaCollectionNew.contains(prendaCollectionOldPrenda)) {
          prendaCollectionOldPrenda.setTipoDePrendaId(null);
          prendaCollectionOldPrenda = em.merge(prendaCollectionOldPrenda);
        }
      }
      for (Prenda prendaCollectionNewPrenda : prendaCollectionNew) {
        if (!prendaCollectionOld.contains(prendaCollectionNewPrenda)) {
          TipoDePrenda oldTipoDePrendaIdOfPrendaCollectionNewPrenda = prendaCollectionNewPrenda.getTipoDePrendaId();
          prendaCollectionNewPrenda.setTipoDePrendaId(tipoDePrenda);
          prendaCollectionNewPrenda = em.merge(prendaCollectionNewPrenda);
          if (oldTipoDePrendaIdOfPrendaCollectionNewPrenda != null && !oldTipoDePrendaIdOfPrendaCollectionNewPrenda.equals(tipoDePrenda)) {
            oldTipoDePrendaIdOfPrendaCollectionNewPrenda.getPrendaCollection().remove(prendaCollectionNewPrenda);
            oldTipoDePrendaIdOfPrendaCollectionNewPrenda = em.merge(oldTipoDePrendaIdOfPrendaCollectionNewPrenda);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = tipoDePrenda.getId();
        if (findTipoDePrenda(id) == null) {
          throw new NonexistentEntityException("The tipoDePrenda with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void softDelete(TipoDePrenda tipoDePrenda) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      TipoDePrenda persistentTipoDePrenda = em.find(TipoDePrenda.class, tipoDePrenda.getId());
      Collection<Prenda> prendaCollectionOld = persistentTipoDePrenda.getPrendaCollection();
      Collection<Prenda> prendaCollectionNew = tipoDePrenda.getPrendaCollection();
      Collection<Prenda> attachedPrendaCollectionNew = new ArrayList<Prenda>();
      for (Prenda prendaCollectionNewPrendaToAttach : prendaCollectionNew) {
        prendaCollectionNewPrendaToAttach = em.getReference(prendaCollectionNewPrendaToAttach.getClass(), prendaCollectionNewPrendaToAttach.getId());
        attachedPrendaCollectionNew.add(prendaCollectionNewPrendaToAttach);
      }
      prendaCollectionNew = attachedPrendaCollectionNew;
      tipoDePrenda.setPrendaCollection(prendaCollectionNew);
      tipoDePrenda = em.merge(tipoDePrenda);
      for (Prenda prendaCollectionOldPrenda : prendaCollectionOld) {
        if (!prendaCollectionNew.contains(prendaCollectionOldPrenda)) {
          prendaCollectionOldPrenda.setTipoDePrendaId(null);
          prendaCollectionOldPrenda = em.merge(prendaCollectionOldPrenda);
        }
      }
      for (Prenda prendaCollectionNewPrenda : prendaCollectionNew) {
        if (!prendaCollectionOld.contains(prendaCollectionNewPrenda)) {
          TipoDePrenda oldTipoDePrendaIdOfPrendaCollectionNewPrenda = prendaCollectionNewPrenda.getTipoDePrendaId();
          prendaCollectionNewPrenda.setTipoDePrendaId(tipoDePrenda);
          prendaCollectionNewPrenda = em.merge(prendaCollectionNewPrenda);
          if (oldTipoDePrendaIdOfPrendaCollectionNewPrenda != null && !oldTipoDePrendaIdOfPrendaCollectionNewPrenda.equals(tipoDePrenda)) {
            oldTipoDePrendaIdOfPrendaCollectionNewPrenda.getPrendaCollection().remove(prendaCollectionNewPrenda);
            oldTipoDePrendaIdOfPrendaCollectionNewPrenda = em.merge(oldTipoDePrendaIdOfPrendaCollectionNewPrenda);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = tipoDePrenda.getId();
        if (findTipoDePrenda(id) == null) {
          throw new NonexistentEntityException("The tipoDePrenda with id " + id + " no longer exists.");
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
      TipoDePrenda tipoDePrenda;
      try {
        tipoDePrenda = em.getReference(TipoDePrenda.class, id);
        tipoDePrenda.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The tipoDePrenda with id " + id + " no longer exists.", enfe);
      }
      Collection<Prenda> prendaCollection = tipoDePrenda.getPrendaCollection();
      for (Prenda prendaCollectionPrenda : prendaCollection) {
        prendaCollectionPrenda.setTipoDePrendaId(null);
        prendaCollectionPrenda = em.merge(prendaCollectionPrenda);
      }
      em.remove(tipoDePrenda);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<TipoDePrenda> findTipoDePrendaEntities() {
    return findTipoDePrendaEntities(true, -1, -1);
  }

  public List<TipoDePrenda> findTipoDePrendaEntities(int maxResults, int firstResult) {
    return findTipoDePrendaEntities(false, maxResults, firstResult);
  }

  private List<TipoDePrenda> findTipoDePrendaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(TipoDePrenda.class));
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

  public TipoDePrenda findTipoDePrenda(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(TipoDePrenda.class, id);
    } finally {
      em.close();
    }
  }

  public int getTipoDePrendaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<TipoDePrenda> rt = cq.from(TipoDePrenda.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
