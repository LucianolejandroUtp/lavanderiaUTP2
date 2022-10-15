/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import com.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.dto.Cliente;
import com.dto.ClienteServicio;
import com.dto.Factura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desti
 */
public class ClienteServicioJpaController implements Serializable {

    public ClienteServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ClienteServicioJpaController() {
        emf = Persistence.createEntityManagerFactory("LavanderiaUtpPersistence");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClienteServicio clienteServicio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = clienteServicio.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                clienteServicio.setIdCliente(idCliente);
            }
            Factura idFactura = clienteServicio.getIdFactura();
            if (idFactura != null) {
                idFactura = em.getReference(idFactura.getClass(), idFactura.getIdFactura());
                clienteServicio.setIdFactura(idFactura);
            }
            em.persist(clienteServicio);
            if (idCliente != null) {
                idCliente.getClienteServicioCollection().add(clienteServicio);
                idCliente = em.merge(idCliente);
            }
            if (idFactura != null) {
                idFactura.getClienteServicioCollection().add(clienteServicio);
                idFactura = em.merge(idFactura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClienteServicio(clienteServicio.getIdClienteServicio()) != null) {
                throw new PreexistingEntityException("ClienteServicio " + clienteServicio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClienteServicio clienteServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClienteServicio persistentClienteServicio = em.find(ClienteServicio.class, clienteServicio.getIdClienteServicio());
            Cliente idClienteOld = persistentClienteServicio.getIdCliente();
            Cliente idClienteNew = clienteServicio.getIdCliente();
            Factura idFacturaOld = persistentClienteServicio.getIdFactura();
            Factura idFacturaNew = clienteServicio.getIdFactura();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                clienteServicio.setIdCliente(idClienteNew);
            }
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getIdFactura());
                clienteServicio.setIdFactura(idFacturaNew);
            }
            clienteServicio = em.merge(clienteServicio);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getClienteServicioCollection().remove(clienteServicio);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getClienteServicioCollection().add(clienteServicio);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idFacturaOld != null && !idFacturaOld.equals(idFacturaNew)) {
                idFacturaOld.getClienteServicioCollection().remove(clienteServicio);
                idFacturaOld = em.merge(idFacturaOld);
            }
            if (idFacturaNew != null && !idFacturaNew.equals(idFacturaOld)) {
                idFacturaNew.getClienteServicioCollection().add(clienteServicio);
                idFacturaNew = em.merge(idFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clienteServicio.getIdClienteServicio();
                if (findClienteServicio(id) == null) {
                    throw new NonexistentEntityException("The clienteServicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClienteServicio clienteServicio;
            try {
                clienteServicio = em.getReference(ClienteServicio.class, id);
                clienteServicio.getIdClienteServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clienteServicio with id " + id + " no longer exists.", enfe);
            }
            Cliente idCliente = clienteServicio.getIdCliente();
            if (idCliente != null) {
                idCliente.getClienteServicioCollection().remove(clienteServicio);
                idCliente = em.merge(idCliente);
            }
            Factura idFactura = clienteServicio.getIdFactura();
            if (idFactura != null) {
                idFactura.getClienteServicioCollection().remove(clienteServicio);
                idFactura = em.merge(idFactura);
            }
            em.remove(clienteServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ClienteServicio> findClienteServicioEntities() {
        return findClienteServicioEntities(true, -1, -1);
    }

    public List<ClienteServicio> findClienteServicioEntities(int maxResults, int firstResult) {
        return findClienteServicioEntities(false, maxResults, firstResult);
    }

    private List<ClienteServicio> findClienteServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ClienteServicio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ClienteServicio findClienteServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClienteServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteServicioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ClienteServicio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
