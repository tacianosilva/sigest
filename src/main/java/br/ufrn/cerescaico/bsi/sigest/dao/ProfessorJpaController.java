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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.IllegalOrphanException;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.NonexistentEntityException;
import br.ufrn.cerescaico.bsi.sigest.model.Avaliacao;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;
import br.ufrn.cerescaico.bsi.sigest.model.Estagio;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

/**
 *
 * @author Mércia Karinny
 */
public class ProfessorJpaController implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7435872895967453723L;

    private EntityManagerFactory emf = null;

    public ProfessorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Professor create(Professor professor) throws Exception {
        if (professor.getAvaliacaos() == null) {
            professor.setAvaliacaos(new ArrayList<Avaliacao>());
        }
        if (professor.getEstagios() == null) {
            professor.setEstagios(new ArrayList<Estagio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso cursoBean = professor.getCurso();
            if (cursoBean != null) {
                cursoBean = em.getReference(cursoBean.getClass(), cursoBean.getCodigo());
                professor.setCurso(cursoBean);
            }
            List<Avaliacao> attachedAvaliacaos = new ArrayList<Avaliacao>();
            for (Avaliacao avaliacaosAvaliacaoToAttach : professor.getAvaliacaos()) {
                avaliacaosAvaliacaoToAttach = em.getReference(avaliacaosAvaliacaoToAttach.getClass(), avaliacaosAvaliacaoToAttach.getCodigo());
                attachedAvaliacaos.add(avaliacaosAvaliacaoToAttach);
            }
            professor.setAvaliacaos(attachedAvaliacaos);
            List<Estagio> attachedEstagios = new ArrayList<Estagio>();
            for (Estagio estagiosEstagioToAttach : professor.getEstagios()) {
                estagiosEstagioToAttach = em.getReference(estagiosEstagioToAttach.getClass(), estagiosEstagioToAttach.getCodigo());
                attachedEstagios.add(estagiosEstagioToAttach);
            }
            professor.setEstagios(attachedEstagios);
            em.persist(professor);

            for (Avaliacao avaliacaosAvaliacao : professor.getAvaliacaos()) {
                Professor oldProfessorOfAvaliacaosAvaliacao = avaliacaosAvaliacao.getProfessor();
                avaliacaosAvaliacao.setProfessor(professor);
                avaliacaosAvaliacao = em.merge(avaliacaosAvaliacao);
                if (oldProfessorOfAvaliacaosAvaliacao != null) {
                    oldProfessorOfAvaliacaosAvaliacao.getAvaliacaos().remove(avaliacaosAvaliacao);
                    oldProfessorOfAvaliacaosAvaliacao = em.merge(oldProfessorOfAvaliacaosAvaliacao);
                }
            }
            for (Estagio estagiosEstagio : professor.getEstagios()) {
                Professor oldProfessorOfEstagiosEstagio = estagiosEstagio.getProfessor();
                estagiosEstagio.setProfessor(professor);
                estagiosEstagio = em.merge(estagiosEstagio);
                if (oldProfessorOfEstagiosEstagio != null) {
                    oldProfessorOfEstagiosEstagio.getEstagios().remove(estagiosEstagio);
                    oldProfessorOfEstagiosEstagio = em.merge(oldProfessorOfEstagiosEstagio);
                }
            }
            em.getTransaction().commit();
            return professor;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Professor professor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Professor persistentProfessor = em.find(Professor.class, professor.getCodigo());
            Curso cursoBeanOld = persistentProfessor.getCurso();
            Curso cursoBeanNew = professor.getCurso();
            List<Avaliacao> avaliacaosOld = persistentProfessor.getAvaliacaos();
            List<Avaliacao> avaliacaosNew = professor.getAvaliacaos();
            List<Estagio> estagiosOld = persistentProfessor.getEstagios();
            List<Estagio> estagiosNew = professor.getEstagios();
            List<String> illegalOrphanMessages = null;
            for (Avaliacao avaliacaosOldAvaliacao : avaliacaosOld) {
                if (!avaliacaosNew.contains(avaliacaosOldAvaliacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Avaliacao " + avaliacaosOldAvaliacao + " since its professor field is not nullable.");
                }
            }
            for (Estagio estagiosOldEstagio : estagiosOld) {
                if (!estagiosNew.contains(estagiosOldEstagio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estagio " + estagiosOldEstagio + " since its professor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cursoBeanNew != null) {
                cursoBeanNew = em.getReference(cursoBeanNew.getClass(), cursoBeanNew.getCodigo());
                professor.setCurso(cursoBeanNew);
            }
            List<Avaliacao> attachedAvaliacaosNew = new ArrayList<Avaliacao>();
            for (Avaliacao avaliacaosNewAvaliacaoToAttach : avaliacaosNew) {
                avaliacaosNewAvaliacaoToAttach = em.getReference(avaliacaosNewAvaliacaoToAttach.getClass(), avaliacaosNewAvaliacaoToAttach.getCodigo());
                attachedAvaliacaosNew.add(avaliacaosNewAvaliacaoToAttach);
            }
            avaliacaosNew = attachedAvaliacaosNew;
            professor.setAvaliacaos(avaliacaosNew);
            List<Estagio> attachedEstagiosNew = new ArrayList<Estagio>();
            for (Estagio estagiosNewEstagioToAttach : estagiosNew) {
                estagiosNewEstagioToAttach = em.getReference(estagiosNewEstagioToAttach.getClass(), estagiosNewEstagioToAttach.getCodigo());
                attachedEstagiosNew.add(estagiosNewEstagioToAttach);
            }
            estagiosNew = attachedEstagiosNew;
            professor.setEstagios(estagiosNew);
            professor = em.merge(professor);
            if (cursoBeanOld != null && !cursoBeanOld.equals(cursoBeanNew)) {
                cursoBeanOld.getProfessors().remove(professor);
                cursoBeanOld = em.merge(cursoBeanOld);
            }
            if (cursoBeanNew != null && !cursoBeanNew.equals(cursoBeanOld)) {
                cursoBeanNew.getProfessors().add(professor);
                cursoBeanNew = em.merge(cursoBeanNew);
            }
            for (Avaliacao avaliacaosNewAvaliacao : avaliacaosNew) {
                if (!avaliacaosOld.contains(avaliacaosNewAvaliacao)) {
                    Professor oldProfessorOfAvaliacaosNewAvaliacao = avaliacaosNewAvaliacao.getProfessor();
                    avaliacaosNewAvaliacao.setProfessor(professor);
                    avaliacaosNewAvaliacao = em.merge(avaliacaosNewAvaliacao);
                    if (oldProfessorOfAvaliacaosNewAvaliacao != null && !oldProfessorOfAvaliacaosNewAvaliacao.equals(professor)) {
                        oldProfessorOfAvaliacaosNewAvaliacao.getAvaliacaos().remove(avaliacaosNewAvaliacao);
                        oldProfessorOfAvaliacaosNewAvaliacao = em.merge(oldProfessorOfAvaliacaosNewAvaliacao);
                    }
                }
            }
            for (Estagio estagiosNewEstagio : estagiosNew) {
                if (!estagiosOld.contains(estagiosNewEstagio)) {
                    Professor oldProfessorOfEstagiosNewEstagio = estagiosNewEstagio.getProfessor();
                    estagiosNewEstagio.setProfessor(professor);
                    estagiosNewEstagio = em.merge(estagiosNewEstagio);
                    if (oldProfessorOfEstagiosNewEstagio != null && !oldProfessorOfEstagiosNewEstagio.equals(professor)) {
                        oldProfessorOfEstagiosNewEstagio.getEstagios().remove(estagiosNewEstagio);
                        oldProfessorOfEstagiosNewEstagio = em.merge(oldProfessorOfEstagiosNewEstagio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = professor.getCodigo();
                if (findProfessor(id) == null) {
                    throw new NonexistentEntityException("The professor with id " + id + " no longer exists.");
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
            Professor professor;
            try {
                professor = em.getReference(Professor.class, id);
                professor.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The professor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Avaliacao> avaliacaosOrphanCheck = professor.getAvaliacaos();
            for (Avaliacao avaliacaosOrphanCheckAvaliacao : avaliacaosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Professor (" + professor + ") cannot be destroyed since the Avaliacao " + avaliacaosOrphanCheckAvaliacao + " in its avaliacaos field has a non-nullable professor field.");
            }
            List<Estagio> estagiosOrphanCheck = professor.getEstagios();
            for (Estagio estagiosOrphanCheckEstagio : estagiosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Professor (" + professor + ") cannot be destroyed since the Estagio " + estagiosOrphanCheckEstagio + " in its estagios field has a non-nullable professor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Curso cursoBean = professor.getCurso();
            if (cursoBean != null) {
                cursoBean.getProfessors().remove(professor);
                cursoBean = em.merge(cursoBean);
            }
            em.remove(professor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Professor> findProfessorEntities() {
        return findProfessorEntities(true, -1, -1);
    }

    public List<Professor> findProfessorEntities(int maxResults, int firstResult) {
        return findProfessorEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
    private List<Professor> findProfessorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Professor.class));
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

    public Professor findProfessor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Professor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfessorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            Root<Professor> rt = cq.from(Professor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Professor buscarPorSiape(Integer siape) {
        EntityManager em = getEntityManager();
        Query query = em
                .createQuery("SELECT p FROM Professor p WHERE p.siape = :siape");
        query.setParameter("siape", siape);
        Professor rsl = (Professor) query.getSingleResult();
        return rsl;
    }
}
