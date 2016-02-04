/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import br.ufrn.cerescaico.bsi.sigest.model.Curso;

/**
 *
 * @author taciano
 */
public class CursoJpaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5378480370337728386L;

    private EntityManagerFactory emf = null;

    public CursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Curso create(Curso curso) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(curso);
            em.flush();
            em.getTransaction().commit();

            return curso;
        } catch (Exception ex) {
            if (findCurso(curso.getCodigo()) != null) {
                throw new PreexistingEntityException("Curso " + curso
                        + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Curso curso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            curso = em.merge(curso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = curso.getCodigo();
                if (findCurso(id) == null) {
                    throw new NonexistentEntityException("The curso with id "
                            + id + " no longer exists.");
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
            Curso curso;
            try {
                curso = em.getReference(Curso.class, id);
                curso.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The curso with id " + id
                        + " no longer exists.", enfe);
            }
            em.remove(curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Curso> findCursoEntities() {
        return findCursoEntities(true, -1, -1);
    }

    public List<Curso> findCursoEntities(int maxResults, int firstResult) {
        return findCursoEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
    private List<Curso> findCursoEntities(boolean all, int maxResults,
            int firstResult) {
        EntityManager em = getEntityManager();
        try {
            @SuppressWarnings("rawtypes")
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Curso.class));
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

    /**
     * Busca a entidade pela chave primária.
     * @param id Chave primária da entidade.
     * @return A entidade encontrada ou <code>null</code>, caso contrário.
     */
    public Curso findCurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public int getCursoCount() {
        EntityManager em = getEntityManager();
        try {
            @SuppressWarnings("rawtypes")
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curso> rt = cq.from(Curso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Curso buscarPorNome(String nome) {
        EntityManager em = getEntityManager();
        Query query = em
                .createQuery("SELECT c FROM Curso c WHERE c.nome like :nome");
        query.setParameter("nome", nome + "%");
        Curso rsl = (Curso) query.getSingleResult();
        return rsl;
    }
}