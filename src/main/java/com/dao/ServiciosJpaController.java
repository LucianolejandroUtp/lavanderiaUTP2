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
import com.dto.Servicios;
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
public class ServiciosJpaController implements Serializable {

    public ServiciosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ServiciosJpaController() {
        emf = Persistence.createEntityManagerFactory("LavanderiaUtpPersistence");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicios servicios) throws PreexistingEntityException, Exception {
        if (servicios.getCategoriaCollection() == null) {
            servicios.setCategoriaCollection(new ArrayList<Categoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Categoria> attachedCategoriaCollection = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionCategoriaToAttach : servicios.getCategoriaCollection()) {
                categoriaCollectionCategoriaToAttach = em.getReference(categoriaCollectionCategoriaToAttach.getClass(), categoriaCollectionCategoriaToAttach.getIdCategoria());
                attachedCategoriaCollection.add(categoriaCollectionCategoriaToAttach);
            }
            servicios.setCategoriaCollection(attachedCategoriaCollection);
            em.persist(servicios);
            for (Categoria categoriaCollectionCategoria : servicios.getCategoriaCollection()) {
                Servicios oldIdServicioOfCategoriaCollectionCategoria = categoriaCollectionCategoria.getIdServicio();
                categoriaCollectionCategoria.setIdServicio(servicios);
                categoriaCollectionCategoria = em.merge(categoriaCollectionCategoria);
                if (oldIdServicioOfCategoriaCollectionCategoria != null) {
                    oldIdServicioOfCategoriaCollectionCategoria.getCategoriaCollection().remove(categoriaCollectionCategoria);
                    oldIdServicioOfCategoriaCollectionCategoria = em.merge(oldIdServicioOfCategoriaCollectionCategoria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicios(servicios.getIdServicio()) != null) {
                throw new PreexistingEntityException("Servicios " + servicios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicios servicios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios persistentServicios = em.find(Servicios.class, servicios.getIdServicio());
            Collection<Categoria> categoriaCollectionOld = persistentServicios.getCategoriaCollection();
            Collection<Categoria> categoriaCollectionNew = servicios.getCategoriaCollection();
            List<String> illegalOrphanMessages = null;
            for (Categoria categoriaCollectionOldCategoria : categoriaCollectionOld) {
                if (!categoriaCollectionNew.contains(categoriaCollectionOldCategoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Categoria " + categoriaCollectionOldCategoria + " since its idServicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Categoria> attachedCategoriaCollectionNew = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionNewCategoriaToAttach : categoriaCollectionNew) {
                categoriaCollectionNewCategoriaToAttach = em.getReference(categoriaCollectionNewCategoriaToAttach.getClass(), categoriaCollectionNewCategoriaToAttach.getIdCategoria());
                attachedCategoriaCollectionNew.add(categoriaCollectionNewCategoriaToAttach);
            }
            categoriaCollectionNew = attachedCategoriaCollectionNew;
            servicios.setCategoriaCollection(categoriaCollectionNew);
            servicios = em.merge(servicios);
            for (Categoria categoriaCollectionNewCategoria : categoriaCollectionNew) {
                if (!categoriaCollectionOld.contains(categoriaCollectionNewCategoria)) {
                    Servicios oldIdServicioOfCategoriaCollectionNewCategoria = categoriaCollectionNewCategoria.getIdServicio();
                    categoriaCollectionNewCategoria.setIdServicio(servicios);
                    categoriaCollectionNewCategoria = em.merge(categoriaCollectionNewCategoria);
                    if (oldIdServicioOfCategoriaCollectionNewCategoria != null && !oldIdServicioOfCategoriaCollectionNewCategoria.equals(servicios)) {
                        oldIdServicioOfCategoriaCollectionNewCategoria.getCategoriaCollection().remove(categoriaCollectionNewCategoria);
                        oldIdServicioOfCategoriaCollectionNewCategoria = em.merge(oldIdServicioOfCategoriaCollectionNewCategoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicios.getIdServicio();
                if (findServicios(id) == null) {
                    throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.");
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
            Servicios servicios;
            try {
                servicios = em.getReference(Servicios.class, id);
                servicios.getIdServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Categoria> categoriaCollectionOrphanCheck = servicios.getCategoriaCollection();
            for (Categoria categoriaCollectionOrphanCheckCategoria : categoriaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicios (" + servicios + ") cannot be destroyed since the Categoria " + categoriaCollectionOrphanCheckCategoria + " in its categoriaCollection field has a non-nullable idServicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(servicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicios> findServiciosEntities() {
        return findServiciosEntities(true, -1, -1);
    }

    public List<Servicios> findServiciosEntities(int maxResults, int firstResult) {
        return findServiciosEntities(false, maxResults, firstResult);
    }

    private List<Servicios> findServiciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Servicios as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Servicios findServicios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicios.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Servicios as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
