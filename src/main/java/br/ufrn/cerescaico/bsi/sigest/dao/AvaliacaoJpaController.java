/*
 * The MIT License
 *
 * Copyright 2012 Mércia Karinny.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import br.ufrn.cerescaico.bsi.sigest.model.Avaliacao;
import br.ufrn.cerescaico.bsi.sigest.model.Estagio;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

/**
 *
 * @author Mercia Karinny
 */
public class AvaliacaoJpaController implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8780293159416728927L;

    public AvaliacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Avaliacao create(Avaliacao avaliacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estagio estagioBean = avaliacao.getEstagioBean();
            if (estagioBean != null) {
                estagioBean = em.getReference(estagioBean.getClass(), estagioBean.getCodigo());
                avaliacao.setEstagioBean(estagioBean);
            }
            Professor professor = avaliacao.getProfessor();
            if (professor != null) {
                professor = em.getReference(professor.getClass(), professor.getCodigo());
                avaliacao.setProfessor(professor);
            }
            em.persist(avaliacao);
            em.getTransaction().commit();
            return avaliacao;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Avaliacao avaliacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estagio estagioBeanNew = avaliacao.getEstagioBean();
            Professor professorNew = avaliacao.getProfessor();
            if (estagioBeanNew != null) {
                estagioBeanNew = em.getReference(estagioBeanNew.getClass(), estagioBeanNew.getCodigo());
                avaliacao.setEstagioBean(estagioBeanNew);
            }
            if (professorNew != null) {
                professorNew = em.getReference(professorNew.getClass(), professorNew.getCodigo());
                avaliacao.setProfessor(professorNew);
            }
            avaliacao = em.merge(avaliacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = avaliacao.getCodigo();
                if (findAvaliacao(id) == null) {
                    throw new NonexistentEntityException("The avaliacao with id " + id + " no longer exists.");
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
            Avaliacao avaliacao;
            try {
                avaliacao = em.getReference(Avaliacao.class, id);
                avaliacao.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(avaliacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Avaliacao> findAvaliacaoEntities() {
        return findAvaliacaoEntities(true, -1, -1);
    }

    public List<Avaliacao> findAvaliacaoEntities(int maxResults, int firstResult) {
        return findAvaliacaoEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
    private List<Avaliacao> findAvaliacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Avaliacao.class));
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

    public List<Avaliacao> buscarPorProf(int codProf){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT * FROM Avaliacao a WHERE a.avaliador = :avaliador");
        query.setParameter("avaliador", codProf);
        @SuppressWarnings("unchecked")
        List<Avaliacao> rsl = query.getResultList();
        return rsl;
    }

    public Avaliacao findAvaliacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Avaliacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvaliacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            Root<Avaliacao> rt = cq.from(Avaliacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
