package com.mycompany.peluqueriacanina.persistencia;

import java.io.Serializable;
import com.mycompany.peluqueriacanina.logica.Mascota;
import com.mycompany.peluqueriacanina.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MascotaJpaController implements Serializable{
     public MascotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

     public MascotaJpaController() {
        emf = Persistence.createEntityManagerFactory("PeluCaninaPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mascota mascotas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mascotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mascota mascotas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mascotas = em.merge(mascotas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = mascotas.getNumCliente();
                if (findMascotas(id) == null) {
                    throw new NonexistentEntityException("The mascotas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascota mascotas;
            try {
                mascotas = em.getReference(Mascota.class, id);
                mascotas.getNumCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mascotas with id " + id + " no longer exists.", enfe);
            }
            em.remove(mascotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mascota> findMascotasEntities() {
        return findMascotasEntities(true, -1, -1);
    }

    public List<Mascota> findMascotasEntities(int maxResults, int firstResult) {
        return findMascotasEntities(false, maxResults, firstResult);
    }

    private List<Mascota> findMascotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mascota.class));
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

    public Mascota findMascotas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }

    public int getMascotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mascota> rt = cq.from(Mascota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
