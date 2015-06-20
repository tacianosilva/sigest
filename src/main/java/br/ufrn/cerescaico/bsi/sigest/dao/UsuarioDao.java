package br.ufrn.cerescaico.bsi.sigest.dao;

import br.ufrn.cerescaico.bsi.sigest.model.Usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author taciano
 */
public class UsuarioDao extends UsuarioJpaController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UsuarioDao(UserTransaction utx, EntityManagerFactory emf) {
        super(utx, emf);
    }

    @SuppressWarnings("unchecked")
    public Usuario autenticar(String username, String password) {
        Usuario usuario = null;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
            Root<Usuario> root = cq.from(Usuario.class);

            if (username != null && !username.equals("") && password != null && !password.equals("")) {
                Predicate byLogin = cb.equal(root.get("username"), username);
                Predicate byGroup = cb.equal(root.get("password"), password);
                cq.where(byLogin, byGroup);
            }
            //
            em.getTransaction().commit();
        } catch (Exception e) {
           handleException(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return usuario;
    }

    private void handleException(Exception e) {
        // TODO Auto-generated method stub

    }

    public Usuario buscarPorEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Usuario.findByEmail");
            q.setParameter("email", email);
            return (Usuario)q.getSingleResult();
        } catch(NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Usuario> pesquisar(String nome) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Usuario.findByNome");
            q.setParameter("nome", nome);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
