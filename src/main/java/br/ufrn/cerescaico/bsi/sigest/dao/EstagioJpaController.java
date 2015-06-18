package br.ufrn.cerescaico.bsi.sigest.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.NonexistentEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.model.Estagio;

public class EstagioJpaController implements Serializable {

    private static final long serialVersionUID = -5378480370337728386L;

    private EntityManagerFactory emf = null;

    public EstagioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Estagio create(Estagio Estagio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(Estagio);
            em.flush();
            em.getTransaction().commit();

            return Estagio;
        } catch (Exception ex) {
            if (findEstagio(Estagio.getCodigo()) != null) {
                throw new PreexistingEntityException("Estagio " + Estagio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estagio Estagio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estagio = em.merge(Estagio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = Estagio.getCodigo();
                if (findEstagio(id) == null) {
                    throw new NonexistentEntityException("The Estagio with id " + id + " no longer exists.");
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
            Estagio Estagio;
            try {
                Estagio = em.getReference(Estagio.class, id);
                Estagio.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The Estagio with id " + id + " no longer exists.", enfe);
            }
            em.remove(Estagio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estagio> findEstagioEntities() {
        return findEstagioEntities(true, -1, -1);
    }

    public List<Estagio> findEstagioEntities(int maxResults, int firstResult) {
        return findEstagioEntities(false, maxResults, firstResult);
    }

    private List<Estagio> findEstagioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estagio.class));
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

    public Estagio findEstagio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estagio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstagioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estagio> rt = cq.from(Estagio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ( (Long) q.getSingleResult() ).intValue();
        } finally {
            em.close();
        }
    }
}
