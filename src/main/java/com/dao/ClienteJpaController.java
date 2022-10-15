/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.dao.exceptions.NonexistentEntityException;
import com.dto.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.dto.Direccion;
import com.dto.Telefono;
import com.dto.ClienteServicio;
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
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ClienteJpaController() {
        emf = Persistence.createEntityManagerFactory("LavanderiaUtpPersistence");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getClienteServicioCollection() == null) {
            cliente.setClienteServicioCollection(new ArrayList<ClienteServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Direccion idDireccion = cliente.getIdDireccion();
            if (idDireccion != null) {
                idDireccion = em.getReference(idDireccion.getClass(), idDireccion.getIdDireccion());
                cliente.setIdDireccion(idDireccion);
            }
            Telefono idTelefono = cliente.getIdTelefono();
            if (idTelefono != null) {
                idTelefono = em.getReference(idTelefono.getClass(), idTelefono.getIdTelefono());
                cliente.setIdTelefono(idTelefono);
            }
            Collection<ClienteServicio> attachedClienteServicioCollection = new ArrayList<ClienteServicio>();
            for (ClienteServicio clienteServicioCollectionClienteServicioToAttach : cliente.getClienteServicioCollection()) {
                clienteServicioCollectionClienteServicioToAttach = em.getReference(clienteServicioCollectionClienteServicioToAttach.getClass(), clienteServicioCollectionClienteServicioToAttach.getIdClienteServicio());
                attachedClienteServicioCollection.add(clienteServicioCollectionClienteServicioToAttach);
            }
            cliente.setClienteServicioCollection(attachedClienteServicioCollection);
            em.persist(cliente);
            if (idDireccion != null) {
                idDireccion.getClienteCollection().add(cliente);
                idDireccion = em.merge(idDireccion);
            }
            if (idTelefono != null) {
                idTelefono.getClienteCollection().add(cliente);
                idTelefono = em.merge(idTelefono);
            }
            for (ClienteServicio clienteServicioCollectionClienteServicio : cliente.getClienteServicioCollection()) {
                Cliente oldIdClienteOfClienteServicioCollectionClienteServicio = clienteServicioCollectionClienteServicio.getIdCliente();
                clienteServicioCollectionClienteServicio.setIdCliente(cliente);
                clienteServicioCollectionClienteServicio = em.merge(clienteServicioCollectionClienteServicio);
                if (oldIdClienteOfClienteServicioCollectionClienteServicio != null) {
                    oldIdClienteOfClienteServicioCollectionClienteServicio.getClienteServicioCollection().remove(clienteServicioCollectionClienteServicio);
                    oldIdClienteOfClienteServicioCollectionClienteServicio = em.merge(oldIdClienteOfClienteServicioCollectionClienteServicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            Direccion idDireccionOld = persistentCliente.getIdDireccion();
            Direccion idDireccionNew = cliente.getIdDireccion();
            Telefono idTelefonoOld = persistentCliente.getIdTelefono();
            Telefono idTelefonoNew = cliente.getIdTelefono();
            Collection<ClienteServicio> clienteServicioCollectionOld = persistentCliente.getClienteServicioCollection();
            Collection<ClienteServicio> clienteServicioCollectionNew = cliente.getClienteServicioCollection();
            if (idDireccionNew != null) {
                idDireccionNew = em.getReference(idDireccionNew.getClass(), idDireccionNew.getIdDireccion());
                cliente.setIdDireccion(idDireccionNew);
            }
            if (idTelefonoNew != null) {
                idTelefonoNew = em.getReference(idTelefonoNew.getClass(), idTelefonoNew.getIdTelefono());
                cliente.setIdTelefono(idTelefonoNew);
            }
            Collection<ClienteServicio> attachedClienteServicioCollectionNew = new ArrayList<ClienteServicio>();
            for (ClienteServicio clienteServicioCollectionNewClienteServicioToAttach : clienteServicioCollectionNew) {
                clienteServicioCollectionNewClienteServicioToAttach = em.getReference(clienteServicioCollectionNewClienteServicioToAttach.getClass(), clienteServicioCollectionNewClienteServicioToAttach.getIdClienteServicio());
                attachedClienteServicioCollectionNew.add(clienteServicioCollectionNewClienteServicioToAttach);
            }
            clienteServicioCollectionNew = attachedClienteServicioCollectionNew;
            cliente.setClienteServicioCollection(clienteServicioCollectionNew);
            cliente = em.merge(cliente);
            if (idDireccionOld != null && !idDireccionOld.equals(idDireccionNew)) {
                idDireccionOld.getClienteCollection().remove(cliente);
                idDireccionOld = em.merge(idDireccionOld);
            }
            if (idDireccionNew != null && !idDireccionNew.equals(idDireccionOld)) {
                idDireccionNew.getClienteCollection().add(cliente);
                idDireccionNew = em.merge(idDireccionNew);
            }
            if (idTelefonoOld != null && !idTelefonoOld.equals(idTelefonoNew)) {
                idTelefonoOld.getClienteCollection().remove(cliente);
                idTelefonoOld = em.merge(idTelefonoOld);
            }
            if (idTelefonoNew != null && !idTelefonoNew.equals(idTelefonoOld)) {
                idTelefonoNew.getClienteCollection().add(cliente);
                idTelefonoNew = em.merge(idTelefonoNew);
            }
            for (ClienteServicio clienteServicioCollectionOldClienteServicio : clienteServicioCollectionOld) {
                if (!clienteServicioCollectionNew.contains(clienteServicioCollectionOldClienteServicio)) {
                    clienteServicioCollectionOldClienteServicio.setIdCliente(null);
                    clienteServicioCollectionOldClienteServicio = em.merge(clienteServicioCollectionOldClienteServicio);
                }
            }
            for (ClienteServicio clienteServicioCollectionNewClienteServicio : clienteServicioCollectionNew) {
                if (!clienteServicioCollectionOld.contains(clienteServicioCollectionNewClienteServicio)) {
                    Cliente oldIdClienteOfClienteServicioCollectionNewClienteServicio = clienteServicioCollectionNewClienteServicio.getIdCliente();
                    clienteServicioCollectionNewClienteServicio.setIdCliente(cliente);
                    clienteServicioCollectionNewClienteServicio = em.merge(clienteServicioCollectionNewClienteServicio);
                    if (oldIdClienteOfClienteServicioCollectionNewClienteServicio != null && !oldIdClienteOfClienteServicioCollectionNewClienteServicio.equals(cliente)) {
                        oldIdClienteOfClienteServicioCollectionNewClienteServicio.getClienteServicioCollection().remove(clienteServicioCollectionNewClienteServicio);
                        oldIdClienteOfClienteServicioCollectionNewClienteServicio = em.merge(oldIdClienteOfClienteServicioCollectionNewClienteServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            Direccion idDireccion = cliente.getIdDireccion();
            if (idDireccion != null) {
                idDireccion.getClienteCollection().remove(cliente);
                idDireccion = em.merge(idDireccion);
            }
            Telefono idTelefono = cliente.getIdTelefono();
            if (idTelefono != null) {
                idTelefono.getClienteCollection().remove(cliente);
                idTelefono = em.merge(idTelefono);
            }
            Collection<ClienteServicio> clienteServicioCollection = cliente.getClienteServicioCollection();
            for (ClienteServicio clienteServicioCollectionClienteServicio : clienteServicioCollection) {
                clienteServicioCollectionClienteServicio.setIdCliente(null);
                clienteServicioCollectionClienteServicio = em.merge(clienteServicioCollectionClienteServicio);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cliente as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cliente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
