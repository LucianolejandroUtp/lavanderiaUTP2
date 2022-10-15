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
import com.dto.Cliente;
import com.dto.Telefono;
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
public class TelefonoJpaController implements Serializable {

    public TelefonoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TelefonoJpaController() {
        emf = Persistence.createEntityManagerFactory("LavanderiaUtpPersistence");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Telefono telefono) throws PreexistingEntityException, Exception {
        if (telefono.getClienteCollection() == null) {
            telefono.setClienteCollection(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Cliente> attachedClienteCollection = new ArrayList<Cliente>();
            for (Cliente clienteCollectionClienteToAttach : telefono.getClienteCollection()) {
                clienteCollectionClienteToAttach = em.getReference(clienteCollectionClienteToAttach.getClass(), clienteCollectionClienteToAttach.getIdCliente());
                attachedClienteCollection.add(clienteCollectionClienteToAttach);
            }
            telefono.setClienteCollection(attachedClienteCollection);
            em.persist(telefono);
            for (Cliente clienteCollectionCliente : telefono.getClienteCollection()) {
                Telefono oldIdTelefonoOfClienteCollectionCliente = clienteCollectionCliente.getIdTelefono();
                clienteCollectionCliente.setIdTelefono(telefono);
                clienteCollectionCliente = em.merge(clienteCollectionCliente);
                if (oldIdTelefonoOfClienteCollectionCliente != null) {
                    oldIdTelefonoOfClienteCollectionCliente.getClienteCollection().remove(clienteCollectionCliente);
                    oldIdTelefonoOfClienteCollectionCliente = em.merge(oldIdTelefonoOfClienteCollectionCliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTelefono(telefono.getIdTelefono()) != null) {
                throw new PreexistingEntityException("Telefono " + telefono + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Telefono telefono) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Telefono persistentTelefono = em.find(Telefono.class, telefono.getIdTelefono());
            Collection<Cliente> clienteCollectionOld = persistentTelefono.getClienteCollection();
            Collection<Cliente> clienteCollectionNew = telefono.getClienteCollection();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteCollectionOldCliente : clienteCollectionOld) {
                if (!clienteCollectionNew.contains(clienteCollectionOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteCollectionOldCliente + " since its idTelefono field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Cliente> attachedClienteCollectionNew = new ArrayList<Cliente>();
            for (Cliente clienteCollectionNewClienteToAttach : clienteCollectionNew) {
                clienteCollectionNewClienteToAttach = em.getReference(clienteCollectionNewClienteToAttach.getClass(), clienteCollectionNewClienteToAttach.getIdCliente());
                attachedClienteCollectionNew.add(clienteCollectionNewClienteToAttach);
            }
            clienteCollectionNew = attachedClienteCollectionNew;
            telefono.setClienteCollection(clienteCollectionNew);
            telefono = em.merge(telefono);
            for (Cliente clienteCollectionNewCliente : clienteCollectionNew) {
                if (!clienteCollectionOld.contains(clienteCollectionNewCliente)) {
                    Telefono oldIdTelefonoOfClienteCollectionNewCliente = clienteCollectionNewCliente.getIdTelefono();
                    clienteCollectionNewCliente.setIdTelefono(telefono);
                    clienteCollectionNewCliente = em.merge(clienteCollectionNewCliente);
                    if (oldIdTelefonoOfClienteCollectionNewCliente != null && !oldIdTelefonoOfClienteCollectionNewCliente.equals(telefono)) {
                        oldIdTelefonoOfClienteCollectionNewCliente.getClienteCollection().remove(clienteCollectionNewCliente);
                        oldIdTelefonoOfClienteCollectionNewCliente = em.merge(oldIdTelefonoOfClienteCollectionNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = telefono.getIdTelefono();
                if (findTelefono(id) == null) {
                    throw new NonexistentEntityException("The telefono with id " + id + " no longer exists.");
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
            Telefono telefono;
            try {
                telefono = em.getReference(Telefono.class, id);
                telefono.getIdTelefono();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The telefono with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Cliente> clienteCollectionOrphanCheck = telefono.getClienteCollection();
            for (Cliente clienteCollectionOrphanCheckCliente : clienteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Telefono (" + telefono + ") cannot be destroyed since the Cliente " + clienteCollectionOrphanCheckCliente + " in its clienteCollection field has a non-nullable idTelefono field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(telefono);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Telefono> findTelefonoEntities() {
        return findTelefonoEntities(true, -1, -1);
    }

    public List<Telefono> findTelefonoEntities(int maxResults, int firstResult) {
        return findTelefonoEntities(false, maxResults, firstResult);
    }

    private List<Telefono> findTelefonoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Telefono as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Telefono findTelefono(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Telefono.class, id);
        } finally {
            em.close();
        }
    }

    public int getTelefonoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Telefono as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
