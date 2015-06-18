package br.ufrn.cerescaico.bsi.sigest.bo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrn.cerescaico.bsi.sigest.dao.EstagiarioJpaController;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.util.JPAUtil;
import br.ufrn.cerescaico.bsi.sigest.model.Estagiario;

/**
 * Classe que representa a Entidade de Négocio para Estagiário.
 * @author fladson
 * @author Taciano Morais Silva
 */

public class EstagiarioBO {

     /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(EstagiarioBO.class.getName());

    private EstagiarioJpaController dao;

    public EstagiarioBO() {
        this.dao = new EstagiarioJpaController(JPAUtil.EMF);
    }

    /**
     * Insere um novo Estagiario.
     * @param estagiario O Estagiário a ser inserido.
     * @return O Estagiario inserido com a chave primária preenchida.
     * @throws NegocioException Caso ocorra erro na inserção.
     */
    public Estagiario inserir(Estagiario estagiario) throws NegocioException {
        try {
            return dao.create(estagiario);
        } catch (PreexistingEntityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.Estagiario.bo.inserir.PreexistingEntityException",ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.Estagiario.bo.inserir.exception",ex);
        }
    }

    public List<Estagiario> listar() throws NegocioException {
        try {
            return dao.findEstagiarioEntities();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.Estagiario.bo.listar", ex);
        }
    }
}
