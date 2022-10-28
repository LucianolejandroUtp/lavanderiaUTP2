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
import com.dto.Persona;
import com.dto.DetalleFactura;
import com.dto.Factura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class FacturaJpaController implements Serializable {

  public FacturaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Factura factura) {
    if (factura.getDetalleFacturaCollection() == null) {
      factura.setDetalleFacturaCollection(new ArrayList<DetalleFactura>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Persona personaId = factura.getPersonaId();
      if (personaId != null) {
        personaId = em.getReference(personaId.getClass(), personaId.getId());
        factura.setPersonaId(personaId);
      }
      Collection<DetalleFactura> attachedDetalleFacturaCollection = new ArrayList<DetalleFactura>();
      for (DetalleFactura detalleFacturaCollectionDetalleFacturaToAttach : factura.getDetalleFacturaCollection()) {
        detalleFacturaCollectionDetalleFacturaToAttach = em.getReference(detalleFacturaCollectionDetalleFacturaToAttach.getClass(), detalleFacturaCollectionDetalleFacturaToAttach.getId());
        attachedDetalleFacturaCollection.add(detalleFacturaCollectionDetalleFacturaToAttach);
      }
      factura.setDetalleFacturaCollection(attachedDetalleFacturaCollection);
      em.persist(factura);
      if (personaId != null) {
        personaId.getFacturaCollection().add(factura);
        personaId = em.merge(personaId);
      }
      for (DetalleFactura detalleFacturaCollectionDetalleFactura : factura.getDetalleFacturaCollection()) {
        Factura oldFacturaIdOfDetalleFacturaCollectionDetalleFactura = detalleFacturaCollectionDetalleFactura.getFacturaId();
        detalleFacturaCollectionDetalleFactura.setFacturaId(factura);
        detalleFacturaCollectionDetalleFactura = em.merge(detalleFacturaCollectionDetalleFactura);
        if (oldFacturaIdOfDetalleFacturaCollectionDetalleFactura != null) {
          oldFacturaIdOfDetalleFacturaCollectionDetalleFactura.getDetalleFacturaCollection().remove(detalleFacturaCollectionDetalleFactura);
          oldFacturaIdOfDetalleFacturaCollectionDetalleFactura = em.merge(oldFacturaIdOfDetalleFacturaCollectionDetalleFactura);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Factura factura) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Factura persistentFactura = em.find(Factura.class, factura.getId());
      Persona personaIdOld = persistentFactura.getPersonaId();
      Persona personaIdNew = factura.getPersonaId();
      Collection<DetalleFactura> detalleFacturaCollectionOld = persistentFactura.getDetalleFacturaCollection();
      Collection<DetalleFactura> detalleFacturaCollectionNew = factura.getDetalleFacturaCollection();
      if (personaIdNew != null) {
        personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
        factura.setPersonaId(personaIdNew);
      }
      Collection<DetalleFactura> attachedDetalleFacturaCollectionNew = new ArrayList<DetalleFactura>();
      for (DetalleFactura detalleFacturaCollectionNewDetalleFacturaToAttach : detalleFacturaCollectionNew) {
        detalleFacturaCollectionNewDetalleFacturaToAttach = em.getReference(detalleFacturaCollectionNewDetalleFacturaToAttach.getClass(), detalleFacturaCollectionNewDetalleFacturaToAttach.getId());
        attachedDetalleFacturaCollectionNew.add(detalleFacturaCollectionNewDetalleFacturaToAttach);
      }
      detalleFacturaCollectionNew = attachedDetalleFacturaCollectionNew;
      factura.setDetalleFacturaCollection(detalleFacturaCollectionNew);
      factura = em.merge(factura);
      if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
        personaIdOld.getFacturaCollection().remove(factura);
        personaIdOld = em.merge(personaIdOld);
      }
      if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
        personaIdNew.getFacturaCollection().add(factura);
        personaIdNew = em.merge(personaIdNew);
      }
      for (DetalleFactura detalleFacturaCollectionOldDetalleFactura : detalleFacturaCollectionOld) {
        if (!detalleFacturaCollectionNew.contains(detalleFacturaCollectionOldDetalleFactura)) {
          detalleFacturaCollectionOldDetalleFactura.setFacturaId(null);
          detalleFacturaCollectionOldDetalleFactura = em.merge(detalleFacturaCollectionOldDetalleFactura);
        }
      }
      for (DetalleFactura detalleFacturaCollectionNewDetalleFactura : detalleFacturaCollectionNew) {
        if (!detalleFacturaCollectionOld.contains(detalleFacturaCollectionNewDetalleFactura)) {
          Factura oldFacturaIdOfDetalleFacturaCollectionNewDetalleFactura = detalleFacturaCollectionNewDetalleFactura.getFacturaId();
          detalleFacturaCollectionNewDetalleFactura.setFacturaId(factura);
          detalleFacturaCollectionNewDetalleFactura = em.merge(detalleFacturaCollectionNewDetalleFactura);
          if (oldFacturaIdOfDetalleFacturaCollectionNewDetalleFactura != null && !oldFacturaIdOfDetalleFacturaCollectionNewDetalleFactura.equals(factura)) {
            oldFacturaIdOfDetalleFacturaCollectionNewDetalleFactura.getDetalleFacturaCollection().remove(detalleFacturaCollectionNewDetalleFactura);
            oldFacturaIdOfDetalleFacturaCollectionNewDetalleFactura = em.merge(oldFacturaIdOfDetalleFacturaCollectionNewDetalleFactura);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = factura.getId();
        if (findFactura(id) == null) {
          throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
      Factura factura;
      try {
        factura = em.getReference(Factura.class, id);
        factura.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
      }
      Persona personaId = factura.getPersonaId();
      if (personaId != null) {
        personaId.getFacturaCollection().remove(factura);
        personaId = em.merge(personaId);
      }
      Collection<DetalleFactura> detalleFacturaCollection = factura.getDetalleFacturaCollection();
      for (DetalleFactura detalleFacturaCollectionDetalleFactura : detalleFacturaCollection) {
        detalleFacturaCollectionDetalleFactura.setFacturaId(null);
        detalleFacturaCollectionDetalleFactura = em.merge(detalleFacturaCollectionDetalleFactura);
      }
      em.remove(factura);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Factura> findFacturaEntities() {
    return findFacturaEntities(true, -1, -1);
  }

  public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
    return findFacturaEntities(false, maxResults, firstResult);
  }

  private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Factura.class));
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

  public Factura findFactura(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Factura.class, id);
    } finally {
      em.close();
    }
  }

  public int getFacturaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Factura> rt = cq.from(Factura.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
