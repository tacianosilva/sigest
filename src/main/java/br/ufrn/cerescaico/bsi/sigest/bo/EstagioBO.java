package br.ufrn.cerescaico.bsi.sigest.bo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrn.cerescaico.bsi.sigest.dao.EstagioJpaController;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.util.JPAUtil;
import br.ufrn.cerescaico.bsi.sigest.model.Estagio;

/**
 * Classe que representa a Entidade de Negócio para Estágio.
 * @author Taciano de Morais Silva
 */
public class EstagioBO extends AbstractBO {

    private static final Logger LOGGER = Logger.getLogger(EstagioBO.class.getName());

    private EstagioJpaController dao;

    public EstagioBO() {
        this.dao = new EstagioJpaController(JPAUtil.EMF);
    }

    /**
     * Insere um novo Estagio.
     * @param estagio Novos Estágios.
     * @return O Estagio inserido com a chave primária preenchida.
     * @throws NegocioException Caso ocorra erro na inserção.
     */
    public Estagio inserir(Estagio estagio) throws NegocioException {
        try {
            return dao.create(estagio);
        } catch (PreexistingEntityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.Estagio.bo.inserir.PreexistingEntityException",ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.Estagio.bo.inserir.exception",ex);
        }
    }

    public List<Estagio> pesquisar(){
        return null;
    }

    public List<Estagio> listar() throws NegocioException {
        try {
            return dao.findEstagioEntities();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.Estagio.bo.listar", ex);
        }
    }

    public void alterar(Estagio estagio) throws NegocioException {
        try {
            dao.edit(estagio);
        } catch (PreexistingEntityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.Estagio.bo.inserir.PreexistingEntityException",ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.Estagio.bo.inserir.exception",ex);
        }
    }

    public void excluir(Integer id) throws NegocioException {
        try {
            dao.destroy(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.Estagio.bo.inserir.exception",ex);
        }
    }
}
