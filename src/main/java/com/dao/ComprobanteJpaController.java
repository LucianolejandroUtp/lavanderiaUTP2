/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import com.dto.Comprobante;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dto.Persona;
import com.dto.DetalleComprobante;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class ComprobanteJpaController implements Serializable {

  public ComprobanteJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Comprobante comprobante) {
    if (comprobante.getDetalleComprobanteCollection() == null) {
      comprobante.setDetalleComprobanteCollection(new ArrayList<DetalleComprobante>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Persona personaId = comprobante.getPersonaId();
      if (personaId != null) {
        personaId = em.getReference(personaId.getClass(), personaId.getId());
        comprobante.setPersonaId(personaId);
      }
      Collection<DetalleComprobante> attachedDetalleComprobanteCollection = new ArrayList<DetalleComprobante>();
      for (DetalleComprobante detalleComprobanteCollectionDetalleComprobanteToAttach : comprobante.getDetalleComprobanteCollection()) {
        detalleComprobanteCollectionDetalleComprobanteToAttach = em.getReference(detalleComprobanteCollectionDetalleComprobanteToAttach.getClass(), detalleComprobanteCollectionDetalleComprobanteToAttach.getId());
        attachedDetalleComprobanteCollection.add(detalleComprobanteCollectionDetalleComprobanteToAttach);
      }
      comprobante.setDetalleComprobanteCollection(attachedDetalleComprobanteCollection);
      em.persist(comprobante);
      if (personaId != null) {
        personaId.getComprobanteCollection().add(comprobante);
        personaId = em.merge(personaId);
      }
      for (DetalleComprobante detalleComprobanteCollectionDetalleComprobante : comprobante.getDetalleComprobanteCollection()) {
        Comprobante oldComprobanteIdOfDetalleComprobanteCollectionDetalleComprobante = detalleComprobanteCollectionDetalleComprobante.getComprobanteId();
        detalleComprobanteCollectionDetalleComprobante.setComprobanteId(comprobante);
        detalleComprobanteCollectionDetalleComprobante = em.merge(detalleComprobanteCollectionDetalleComprobante);
        if (oldComprobanteIdOfDetalleComprobanteCollectionDetalleComprobante != null) {
          oldComprobanteIdOfDetalleComprobanteCollectionDetalleComprobante.getDetalleComprobanteCollection().remove(detalleComprobanteCollectionDetalleComprobante);
          oldComprobanteIdOfDetalleComprobanteCollectionDetalleComprobante = em.merge(oldComprobanteIdOfDetalleComprobanteCollectionDetalleComprobante);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Comprobante comprobante) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Comprobante persistentComprobante = em.find(Comprobante.class, comprobante.getId());
      Persona personaIdOld = persistentComprobante.getPersonaId();
      Persona personaIdNew = comprobante.getPersonaId();
      Collection<DetalleComprobante> detalleComprobanteCollectionOld = persistentComprobante.getDetalleComprobanteCollection();
      Collection<DetalleComprobante> detalleComprobanteCollectionNew = comprobante.getDetalleComprobanteCollection();
      if (personaIdNew != null) {
        personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
        comprobante.setPersonaId(personaIdNew);
      }
      Collection<DetalleComprobante> attachedDetalleComprobanteCollectionNew = new ArrayList<DetalleComprobante>();
      for (DetalleComprobante detalleComprobanteCollectionNewDetalleComprobanteToAttach : detalleComprobanteCollectionNew) {
        detalleComprobanteCollectionNewDetalleComprobanteToAttach = em.getReference(detalleComprobanteCollectionNewDetalleComprobanteToAttach.getClass(), detalleComprobanteCollectionNewDetalleComprobanteToAttach.getId());
        attachedDetalleComprobanteCollectionNew.add(detalleComprobanteCollectionNewDetalleComprobanteToAttach);
      }
      detalleComprobanteCollectionNew = attachedDetalleComprobanteCollectionNew;
      comprobante.setDetalleComprobanteCollection(detalleComprobanteCollectionNew);
      comprobante = em.merge(comprobante);
      if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
        personaIdOld.getComprobanteCollection().remove(comprobante);
        personaIdOld = em.merge(personaIdOld);
      }
      if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
        personaIdNew.getComprobanteCollection().add(comprobante);
        personaIdNew = em.merge(personaIdNew);
      }
      for (DetalleComprobante detalleComprobanteCollectionOldDetalleComprobante : detalleComprobanteCollectionOld) {
        if (!detalleComprobanteCollectionNew.contains(detalleComprobanteCollectionOldDetalleComprobante)) {
          detalleComprobanteCollectionOldDetalleComprobante.setComprobanteId(null);
          detalleComprobanteCollectionOldDetalleComprobante = em.merge(detalleComprobanteCollectionOldDetalleComprobante);
        }
      }
      for (DetalleComprobante detalleComprobanteCollectionNewDetalleComprobante : detalleComprobanteCollectionNew) {
        if (!detalleComprobanteCollectionOld.contains(detalleComprobanteCollectionNewDetalleComprobante)) {
          Comprobante oldComprobanteIdOfDetalleComprobanteCollectionNewDetalleComprobante = detalleComprobanteCollectionNewDetalleComprobante.getComprobanteId();
          detalleComprobanteCollectionNewDetalleComprobante.setComprobanteId(comprobante);
          detalleComprobanteCollectionNewDetalleComprobante = em.merge(detalleComprobanteCollectionNewDetalleComprobante);
          if (oldComprobanteIdOfDetalleComprobanteCollectionNewDetalleComprobante != null && !oldComprobanteIdOfDetalleComprobanteCollectionNewDetalleComprobante.equals(comprobante)) {
            oldComprobanteIdOfDetalleComprobanteCollectionNewDetalleComprobante.getDetalleComprobanteCollection().remove(detalleComprobanteCollectionNewDetalleComprobante);
            oldComprobanteIdOfDetalleComprobanteCollectionNewDetalleComprobante = em.merge(oldComprobanteIdOfDetalleComprobanteCollectionNewDetalleComprobante);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = comprobante.getId();
        if (findComprobante(id) == null) {
          throw new NonexistentEntityException("The comprobante with id " + id + " no longer exists.");
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
      Comprobante comprobante;
      try {
        comprobante = em.getReference(Comprobante.class, id);
        comprobante.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The comprobante with id " + id + " no longer exists.", enfe);
      }
      Persona personaId = comprobante.getPersonaId();
      if (personaId != null) {
        personaId.getComprobanteCollection().remove(comprobante);
        personaId = em.merge(personaId);
      }
      Collection<DetalleComprobante> detalleComprobanteCollection = comprobante.getDetalleComprobanteCollection();
      for (DetalleComprobante detalleComprobanteCollectionDetalleComprobante : detalleComprobanteCollection) {
        detalleComprobanteCollectionDetalleComprobante.setComprobanteId(null);
        detalleComprobanteCollectionDetalleComprobante = em.merge(detalleComprobanteCollectionDetalleComprobante);
      }
      em.remove(comprobante);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Comprobante> findComprobanteEntities() {
    return findComprobanteEntities(true, -1, -1);
  }

  public List<Comprobante> findComprobanteEntities(int maxResults, int firstResult) {
    return findComprobanteEntities(false, maxResults, firstResult);
  }

  private List<Comprobante> findComprobanteEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Comprobante.class));
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

  public Comprobante findComprobante(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Comprobante.class, id);
    } finally {
      em.close();
    }
  }

  public int getComprobanteCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Comprobante> rt = cq.from(Comprobante.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
