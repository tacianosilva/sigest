package br.ufrn.cerescaico.bsi.sigest.dao;

import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.NonexistentEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.model.Estagiario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author fladson
 */
public class EstagiarioJpaController implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5378480370337728386L;

    private EntityManagerFactory emf = null;

    public EstagiarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Estagiario create(Estagiario estagiario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estagiario);
            em.flush();
            em.getTransaction().commit();

            return estagiario;
        } catch (Exception ex) {
            if (findEstagiario(estagiario.getCodigo()) != null) {
                throw new PreexistingEntityException("Estagiario " + estagiario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estagiario estagiario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estagiario = em.merge(estagiario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estagiario.getCodigo();
                if (findEstagiario(id) == null) {
                    throw new NonexistentEntityException("The Estagiario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estagiario Estagiario;
            try {
                Estagiario = em.getReference(Estagiario.class, id);
                Estagiario.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The Estagiario with id " + id + " no longer exists.", enfe);
            }
            em.remove(Estagiario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estagiario> findEstagiarioEntities() {
        return findEstagiarioEntities(true, -1, -1);
    }

    public List<Estagiario> findEstagiarioEntities(int maxResults, int firstResult) {
        return findEstagiarioEntities(false, maxResults, firstResult);
    }

    private List<Estagiario> findEstagiarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estagiario.class));
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

    public Estagiario findEstagiario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estagiario.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstagiarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estagiario> rt = cq.from(Estagiario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ( (Long) q.getSingleResult() ).intValue();
        } finally {
            em.close();
        }
    }

}
