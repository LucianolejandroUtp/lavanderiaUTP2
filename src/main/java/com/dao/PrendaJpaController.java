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
import com.dto.TipoDePrenda;
import com.dto.DetalleFactura;
import com.dto.Prenda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class PrendaJpaController implements Serializable {

  public PrendaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Prenda prenda) {
    if (prenda.getDetalleFacturaCollection() == null) {
      prenda.setDetalleFacturaCollection(new ArrayList<DetalleFactura>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Persona personaId = prenda.getPersonaId();
      if (personaId != null) {
        personaId = em.getReference(personaId.getClass(), personaId.getId());
        prenda.setPersonaId(personaId);
      }
      TipoDePrenda tipoDePrendaId = prenda.getTipoDePrendaId();
      if (tipoDePrendaId != null) {
        tipoDePrendaId = em.getReference(tipoDePrendaId.getClass(), tipoDePrendaId.getId());
        prenda.setTipoDePrendaId(tipoDePrendaId);
      }
      Collection<DetalleFactura> attachedDetalleFacturaCollection = new ArrayList<DetalleFactura>();
      for (DetalleFactura detalleFacturaCollectionDetalleFacturaToAttach : prenda.getDetalleFacturaCollection()) {
        detalleFacturaCollectionDetalleFacturaToAttach = em.getReference(detalleFacturaCollectionDetalleFacturaToAttach.getClass(), detalleFacturaCollectionDetalleFacturaToAttach.getId());
        attachedDetalleFacturaCollection.add(detalleFacturaCollectionDetalleFacturaToAttach);
      }
      prenda.setDetalleFacturaCollection(attachedDetalleFacturaCollection);
      em.persist(prenda);
      if (personaId != null) {
        personaId.getPrendaCollection().add(prenda);
        personaId = em.merge(personaId);
      }
      if (tipoDePrendaId != null) {
        tipoDePrendaId.getPrendaCollection().add(prenda);
        tipoDePrendaId = em.merge(tipoDePrendaId);
      }
      for (DetalleFactura detalleFacturaCollectionDetalleFactura : prenda.getDetalleFacturaCollection()) {
        Prenda oldPrendaIdOfDetalleFacturaCollectionDetalleFactura = detalleFacturaCollectionDetalleFactura.getPrendaId();
        detalleFacturaCollectionDetalleFactura.setPrendaId(prenda);
        detalleFacturaCollectionDetalleFactura = em.merge(detalleFacturaCollectionDetalleFactura);
        if (oldPrendaIdOfDetalleFacturaCollectionDetalleFactura != null) {
          oldPrendaIdOfDetalleFacturaCollectionDetalleFactura.getDetalleFacturaCollection().remove(detalleFacturaCollectionDetalleFactura);
          oldPrendaIdOfDetalleFacturaCollectionDetalleFactura = em.merge(oldPrendaIdOfDetalleFacturaCollectionDetalleFactura);
        }
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Prenda prenda) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Prenda persistentPrenda = em.find(Prenda.class, prenda.getId());
      Persona personaIdOld = persistentPrenda.getPersonaId();
      Persona personaIdNew = prenda.getPersonaId();
      TipoDePrenda tipoDePrendaIdOld = persistentPrenda.getTipoDePrendaId();
      TipoDePrenda tipoDePrendaIdNew = prenda.getTipoDePrendaId();
      Collection<DetalleFactura> detalleFacturaCollectionOld = persistentPrenda.getDetalleFacturaCollection();
      Collection<DetalleFactura> detalleFacturaCollectionNew = prenda.getDetalleFacturaCollection();
      if (personaIdNew != null) {
        personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
        prenda.setPersonaId(personaIdNew);
      }
      if (tipoDePrendaIdNew != null) {
        tipoDePrendaIdNew = em.getReference(tipoDePrendaIdNew.getClass(), tipoDePrendaIdNew.getId());
        prenda.setTipoDePrendaId(tipoDePrendaIdNew);
      }
      Collection<DetalleFactura> attachedDetalleFacturaCollectionNew = new ArrayList<DetalleFactura>();
      for (DetalleFactura detalleFacturaCollectionNewDetalleFacturaToAttach : detalleFacturaCollectionNew) {
        detalleFacturaCollectionNewDetalleFacturaToAttach = em.getReference(detalleFacturaCollectionNewDetalleFacturaToAttach.getClass(), detalleFacturaCollectionNewDetalleFacturaToAttach.getId());
        attachedDetalleFacturaCollectionNew.add(detalleFacturaCollectionNewDetalleFacturaToAttach);
      }
      detalleFacturaCollectionNew = attachedDetalleFacturaCollectionNew;
      prenda.setDetalleFacturaCollection(detalleFacturaCollectionNew);
      prenda = em.merge(prenda);
      if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
        personaIdOld.getPrendaCollection().remove(prenda);
        personaIdOld = em.merge(personaIdOld);
      }
      if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
        personaIdNew.getPrendaCollection().add(prenda);
        personaIdNew = em.merge(personaIdNew);
      }
      if (tipoDePrendaIdOld != null && !tipoDePrendaIdOld.equals(tipoDePrendaIdNew)) {
        tipoDePrendaIdOld.getPrendaCollection().remove(prenda);
        tipoDePrendaIdOld = em.merge(tipoDePrendaIdOld);
      }
      if (tipoDePrendaIdNew != null && !tipoDePrendaIdNew.equals(tipoDePrendaIdOld)) {
        tipoDePrendaIdNew.getPrendaCollection().add(prenda);
        tipoDePrendaIdNew = em.merge(tipoDePrendaIdNew);
      }
      for (DetalleFactura detalleFacturaCollectionOldDetalleFactura : detalleFacturaCollectionOld) {
        if (!detalleFacturaCollectionNew.contains(detalleFacturaCollectionOldDetalleFactura)) {
          detalleFacturaCollectionOldDetalleFactura.setPrendaId(null);
          detalleFacturaCollectionOldDetalleFactura = em.merge(detalleFacturaCollectionOldDetalleFactura);
        }
      }
      for (DetalleFactura detalleFacturaCollectionNewDetalleFactura : detalleFacturaCollectionNew) {
        if (!detalleFacturaCollectionOld.contains(detalleFacturaCollectionNewDetalleFactura)) {
          Prenda oldPrendaIdOfDetalleFacturaCollectionNewDetalleFactura = detalleFacturaCollectionNewDetalleFactura.getPrendaId();
          detalleFacturaCollectionNewDetalleFactura.setPrendaId(prenda);
          detalleFacturaCollectionNewDetalleFactura = em.merge(detalleFacturaCollectionNewDetalleFactura);
          if (oldPrendaIdOfDetalleFacturaCollectionNewDetalleFactura != null && !oldPrendaIdOfDetalleFacturaCollectionNewDetalleFactura.equals(prenda)) {
            oldPrendaIdOfDetalleFacturaCollectionNewDetalleFactura.getDetalleFacturaCollection().remove(detalleFacturaCollectionNewDetalleFactura);
            oldPrendaIdOfDetalleFacturaCollectionNewDetalleFactura = em.merge(oldPrendaIdOfDetalleFacturaCollectionNewDetalleFactura);
          }
        }
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = prenda.getId();
        if (findPrenda(id) == null) {
          throw new NonexistentEntityException("The prenda with id " + id + " no longer exists.");
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
      Prenda prenda;
      try {
        prenda = em.getReference(Prenda.class, id);
        prenda.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The prenda with id " + id + " no longer exists.", enfe);
      }
      Persona personaId = prenda.getPersonaId();
      if (personaId != null) {
        personaId.getPrendaCollection().remove(prenda);
        personaId = em.merge(personaId);
      }
      TipoDePrenda tipoDePrendaId = prenda.getTipoDePrendaId();
      if (tipoDePrendaId != null) {
        tipoDePrendaId.getPrendaCollection().remove(prenda);
        tipoDePrendaId = em.merge(tipoDePrendaId);
      }
      Collection<DetalleFactura> detalleFacturaCollection = prenda.getDetalleFacturaCollection();
      for (DetalleFactura detalleFacturaCollectionDetalleFactura : detalleFacturaCollection) {
        detalleFacturaCollectionDetalleFactura.setPrendaId(null);
        detalleFacturaCollectionDetalleFactura = em.merge(detalleFacturaCollectionDetalleFactura);
      }
      em.remove(prenda);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Prenda> findPrendaEntities() {
    return findPrendaEntities(true, -1, -1);
  }

  public List<Prenda> findPrendaEntities(int maxResults, int firstResult) {
    return findPrendaEntities(false, maxResults, firstResult);
  }

  private List<Prenda> findPrendaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Prenda.class));
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

  public Prenda findPrenda(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Prenda.class, id);
    } finally {
      em.close();
    }
  }

  public int getPrendaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Prenda> rt = cq.from(Prenda.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}