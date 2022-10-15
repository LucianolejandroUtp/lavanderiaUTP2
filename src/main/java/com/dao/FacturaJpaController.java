/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.IllegalOrphanException;
import com.dao.exceptions.NonexistentEntityException;
import com.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.dto.Categoria;
import com.dto.ClienteServicio;
import com.dto.Factura;
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
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public FacturaJpaController() {
        emf = Persistence.createEntityManagerFactory("LavanderiaUtpPersistence");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) throws PreexistingEntityException, Exception {
        if (factura.getClienteServicioCollection() == null) {
            factura.setClienteServicioCollection(new ArrayList<ClienteServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria idCategoria = factura.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getIdCategoria());
                factura.setIdCategoria(idCategoria);
            }
            Collection<ClienteServicio> attachedClienteServicioCollection = new ArrayList<ClienteServicio>();
            for (ClienteServicio clienteServicioCollectionClienteServicioToAttach : factura.getClienteServicioCollection()) {
                clienteServicioCollectionClienteServicioToAttach = em.getReference(clienteServicioCollectionClienteServicioToAttach.getClass(), clienteServicioCollectionClienteServicioToAttach.getIdClienteServicio());
                attachedClienteServicioCollection.add(clienteServicioCollectionClienteServicioToAttach);
            }
            factura.setClienteServicioCollection(attachedClienteServicioCollection);
            em.persist(factura);
            if (idCategoria != null) {
                idCategoria.getFacturaCollection().add(factura);
                idCategoria = em.merge(idCategoria);
            }
            for (ClienteServicio clienteServicioCollectionClienteServicio : factura.getClienteServicioCollection()) {
                Factura oldIdFacturaOfClienteServicioCollectionClienteServicio = clienteServicioCollectionClienteServicio.getIdFactura();
                clienteServicioCollectionClienteServicio.setIdFactura(factura);
                clienteServicioCollectionClienteServicio = em.merge(clienteServicioCollectionClienteServicio);
                if (oldIdFacturaOfClienteServicioCollectionClienteServicio != null) {
                    oldIdFacturaOfClienteServicioCollectionClienteServicio.getClienteServicioCollection().remove(clienteServicioCollectionClienteServicio);
                    oldIdFacturaOfClienteServicioCollectionClienteServicio = em.merge(oldIdFacturaOfClienteServicioCollectionClienteServicio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFactura(factura.getIdFactura()) != null) {
                throw new PreexistingEntityException("Factura " + factura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdFactura());
            Categoria idCategoriaOld = persistentFactura.getIdCategoria();
            Categoria idCategoriaNew = factura.getIdCategoria();
            Collection<ClienteServicio> clienteServicioCollectionOld = persistentFactura.getClienteServicioCollection();
            Collection<ClienteServicio> clienteServicioCollectionNew = factura.getClienteServicioCollection();
            List<String> illegalOrphanMessages = null;
            for (ClienteServicio clienteServicioCollectionOldClienteServicio : clienteServicioCollectionOld) {
                if (!clienteServicioCollectionNew.contains(clienteServicioCollectionOldClienteServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClienteServicio " + clienteServicioCollectionOldClienteServicio + " since its idFactura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getIdCategoria());
                factura.setIdCategoria(idCategoriaNew);
            }
            Collection<ClienteServicio> attachedClienteServicioCollectionNew = new ArrayList<ClienteServicio>();
            for (ClienteServicio clienteServicioCollectionNewClienteServicioToAttach : clienteServicioCollectionNew) {
                clienteServicioCollectionNewClienteServicioToAttach = em.getReference(clienteServicioCollectionNewClienteServicioToAttach.getClass(), clienteServicioCollectionNewClienteServicioToAttach.getIdClienteServicio());
                attachedClienteServicioCollectionNew.add(clienteServicioCollectionNewClienteServicioToAttach);
            }
            clienteServicioCollectionNew = attachedClienteServicioCollectionNew;
            factura.setClienteServicioCollection(clienteServicioCollectionNew);
            factura = em.merge(factura);
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getFacturaCollection().remove(factura);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getFacturaCollection().add(factura);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            for (ClienteServicio clienteServicioCollectionNewClienteServicio : clienteServicioCollectionNew) {
                if (!clienteServicioCollectionOld.contains(clienteServicioCollectionNewClienteServicio)) {
                    Factura oldIdFacturaOfClienteServicioCollectionNewClienteServicio = clienteServicioCollectionNewClienteServicio.getIdFactura();
                    clienteServicioCollectionNewClienteServicio.setIdFactura(factura);
                    clienteServicioCollectionNewClienteServicio = em.merge(clienteServicioCollectionNewClienteServicio);
                    if (oldIdFacturaOfClienteServicioCollectionNewClienteServicio != null && !oldIdFacturaOfClienteServicioCollectionNewClienteServicio.equals(factura)) {
                        oldIdFacturaOfClienteServicioCollectionNewClienteServicio.getClienteServicioCollection().remove(clienteServicioCollectionNewClienteServicio);
                        oldIdFacturaOfClienteServicioCollectionNewClienteServicio = em.merge(oldIdFacturaOfClienteServicioCollectionNewClienteServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdFactura();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ClienteServicio> clienteServicioCollectionOrphanCheck = factura.getClienteServicioCollection();
            for (ClienteServicio clienteServicioCollectionOrphanCheckClienteServicio : clienteServicioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the ClienteServicio " + clienteServicioCollectionOrphanCheckClienteServicio + " in its clienteServicioCollection field has a non-nullable idFactura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria idCategoria = factura.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getFacturaCollection().remove(factura);
                idCategoria = em.merge(idCategoria);
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
            Query q = em.createQuery("select object(o) from Factura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Factura findFactura(Integer id) {
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
            Query q = em.createQuery("select count(o) from Factura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
