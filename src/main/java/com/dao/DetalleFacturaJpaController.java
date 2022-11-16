/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import com.dto.DetalleFactura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.dto.Factura;
import com.dto.Prenda;
import com.dto.Servicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desti
 */
public class DetalleFacturaJpaController implements Serializable {

  public DetalleFacturaJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(DetalleFactura detalleFactura) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Factura facturaId = detalleFactura.getFacturaId();
      if (facturaId != null) {
        facturaId = em.getReference(facturaId.getClass(), facturaId.getId());
        detalleFactura.setFacturaId(facturaId);
      }
      Prenda prendaId = detalleFactura.getPrendaId();
      if (prendaId != null) {
        prendaId = em.getReference(prendaId.getClass(), prendaId.getId());
        detalleFactura.setPrendaId(prendaId);
      }
      Servicio servicioId = detalleFactura.getServicioId();
      if (servicioId != null) {
        servicioId = em.getReference(servicioId.getClass(), servicioId.getId());
        detalleFactura.setServicioId(servicioId);
      }
      em.persist(detalleFactura);
      if (facturaId != null) {
        facturaId.getDetalleFacturaCollection().add(detalleFactura);
        facturaId = em.merge(facturaId);
      }
      if (prendaId != null) {
        prendaId.getDetalleFacturaCollection().add(detalleFactura);
        prendaId = em.merge(prendaId);
      }
      if (servicioId != null) {
        servicioId.getDetalleFacturaCollection().add(detalleFactura);
        servicioId = em.merge(servicioId);
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(DetalleFactura detalleFactura) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      DetalleFactura persistentDetalleFactura = em.find(DetalleFactura.class, detalleFactura.getId());
      Factura facturaIdOld = persistentDetalleFactura.getFacturaId();
      Factura facturaIdNew = detalleFactura.getFacturaId();
      Prenda prendaIdOld = persistentDetalleFactura.getPrendaId();
      Prenda prendaIdNew = detalleFactura.getPrendaId();
      Servicio servicioIdOld = persistentDetalleFactura.getServicioId();
      Servicio servicioIdNew = detalleFactura.getServicioId();
      if (facturaIdNew != null) {
        facturaIdNew = em.getReference(facturaIdNew.getClass(), facturaIdNew.getId());
        detalleFactura.setFacturaId(facturaIdNew);
      }
      if (prendaIdNew != null) {
        prendaIdNew = em.getReference(prendaIdNew.getClass(), prendaIdNew.getId());
        detalleFactura.setPrendaId(prendaIdNew);
      }
      if (servicioIdNew != null) {
        servicioIdNew = em.getReference(servicioIdNew.getClass(), servicioIdNew.getId());
        detalleFactura.setServicioId(servicioIdNew);
      }
      detalleFactura = em.merge(detalleFactura);
      if (facturaIdOld != null && !facturaIdOld.equals(facturaIdNew)) {
        facturaIdOld.getDetalleFacturaCollection().remove(detalleFactura);
        facturaIdOld = em.merge(facturaIdOld);
      }
      if (facturaIdNew != null && !facturaIdNew.equals(facturaIdOld)) {
        facturaIdNew.getDetalleFacturaCollection().add(detalleFactura);
        facturaIdNew = em.merge(facturaIdNew);
      }
      if (prendaIdOld != null && !prendaIdOld.equals(prendaIdNew)) {
        prendaIdOld.getDetalleFacturaCollection().remove(detalleFactura);
        prendaIdOld = em.merge(prendaIdOld);
      }
      if (prendaIdNew != null && !prendaIdNew.equals(prendaIdOld)) {
        prendaIdNew.getDetalleFacturaCollection().add(detalleFactura);
        prendaIdNew = em.merge(prendaIdNew);
      }
      if (servicioIdOld != null && !servicioIdOld.equals(servicioIdNew)) {
        servicioIdOld.getDetalleFacturaCollection().remove(detalleFactura);
        servicioIdOld = em.merge(servicioIdOld);
      }
      if (servicioIdNew != null && !servicioIdNew.equals(servicioIdOld)) {
        servicioIdNew.getDetalleFacturaCollection().add(detalleFactura);
        servicioIdNew = em.merge(servicioIdNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = detalleFactura.getId();
        if (findDetalleFactura(id) == null) {
          throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void softDelete(DetalleFactura detalleFactura) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      DetalleFactura persistentDetalleFactura = em.find(DetalleFactura.class, detalleFactura.getId());
      Factura facturaIdOld = persistentDetalleFactura.getFacturaId();
      Factura facturaIdNew = detalleFactura.getFacturaId();
      Prenda prendaIdOld = persistentDetalleFactura.getPrendaId();
      Prenda prendaIdNew = detalleFactura.getPrendaId();
      Servicio servicioIdOld = persistentDetalleFactura.getServicioId();
      Servicio servicioIdNew = detalleFactura.getServicioId();
      if (facturaIdNew != null) {
        facturaIdNew = em.getReference(facturaIdNew.getClass(), facturaIdNew.getId());
        detalleFactura.setFacturaId(facturaIdNew);
      }
      if (prendaIdNew != null) {
        prendaIdNew = em.getReference(prendaIdNew.getClass(), prendaIdNew.getId());
        detalleFactura.setPrendaId(prendaIdNew);
      }
      if (servicioIdNew != null) {
        servicioIdNew = em.getReference(servicioIdNew.getClass(), servicioIdNew.getId());
        detalleFactura.setServicioId(servicioIdNew);
      }
      detalleFactura = em.merge(detalleFactura);
      if (facturaIdOld != null && !facturaIdOld.equals(facturaIdNew)) {
        facturaIdOld.getDetalleFacturaCollection().remove(detalleFactura);
        facturaIdOld = em.merge(facturaIdOld);
      }
      if (facturaIdNew != null && !facturaIdNew.equals(facturaIdOld)) {
        facturaIdNew.getDetalleFacturaCollection().add(detalleFactura);
        facturaIdNew = em.merge(facturaIdNew);
      }
      if (prendaIdOld != null && !prendaIdOld.equals(prendaIdNew)) {
        prendaIdOld.getDetalleFacturaCollection().remove(detalleFactura);
        prendaIdOld = em.merge(prendaIdOld);
      }
      if (prendaIdNew != null && !prendaIdNew.equals(prendaIdOld)) {
        prendaIdNew.getDetalleFacturaCollection().add(detalleFactura);
        prendaIdNew = em.merge(prendaIdNew);
      }
      if (servicioIdOld != null && !servicioIdOld.equals(servicioIdNew)) {
        servicioIdOld.getDetalleFacturaCollection().remove(detalleFactura);
        servicioIdOld = em.merge(servicioIdOld);
      }
      if (servicioIdNew != null && !servicioIdNew.equals(servicioIdOld)) {
        servicioIdNew.getDetalleFacturaCollection().add(detalleFactura);
        servicioIdNew = em.merge(servicioIdNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Long id = detalleFactura.getId();
        if (findDetalleFactura(id) == null) {
          throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.");
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
      DetalleFactura detalleFactura;
      try {
        detalleFactura = em.getReference(DetalleFactura.class, id);
        detalleFactura.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.", enfe);
      }
      Factura facturaId = detalleFactura.getFacturaId();
      if (facturaId != null) {
        facturaId.getDetalleFacturaCollection().remove(detalleFactura);
        facturaId = em.merge(facturaId);
      }
      Prenda prendaId = detalleFactura.getPrendaId();
      if (prendaId != null) {
        prendaId.getDetalleFacturaCollection().remove(detalleFactura);
        prendaId = em.merge(prendaId);
      }
      Servicio servicioId = detalleFactura.getServicioId();
      if (servicioId != null) {
        servicioId.getDetalleFacturaCollection().remove(detalleFactura);
        servicioId = em.merge(servicioId);
      }
      em.remove(detalleFactura);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<DetalleFactura> findDetalleFacturaEntities() {
    return findDetalleFacturaEntities(true, -1, -1);
  }

  public List<DetalleFactura> findDetalleFacturaEntities(int maxResults, int firstResult) {
    return findDetalleFacturaEntities(false, maxResults, firstResult);
  }

  private List<DetalleFactura> findDetalleFacturaEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(DetalleFactura.class));
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

  public DetalleFactura findDetalleFactura(Long id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(DetalleFactura.class, id);
    } finally {
      em.close();
    }
  }

  public int getDetalleFacturaCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<DetalleFactura> rt = cq.from(DetalleFactura.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
