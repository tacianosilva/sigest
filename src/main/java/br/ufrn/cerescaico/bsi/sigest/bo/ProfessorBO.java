package br.ufrn.cerescaico.bsi.sigest.bo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrn.cerescaico.bsi.sigest.dao.ProfessorJpaController;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.util.JPAUtil;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

/**
 * Classe que representa a Entidade de Negócio para Professor.
 * @author Taciano de Morais Silva
 */
public class ProfessorBO extends AbstractBO {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ProfessorBO.class.getName());

    private ProfessorJpaController dao;

    public ProfessorBO (){
        this.dao = new ProfessorJpaController(JPAUtil.EMF);
    }

    public Professor inserir(Professor professor) throws NegocioException {
        try {
            return dao.create(professor);
        } catch (PreexistingEntityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.professor.bo.inserir.PreexistingEntityException",ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.professor.bo.inserir.exception",ex);
        }
    }

    public void excluir(Integer codigo) throws NegocioException {
        try {
            dao.destroy(codigo);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.professor.bo.excluir.exception", ex);
        }
    }

    public List<Professor> listar() throws NegocioException {
        try {
            return dao.findProfessorEntities();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.professor.bo.listar", ex);
        }
    }

    public Professor buscarProfessor(Integer id) throws NegocioException {
        try {
            return dao.findProfessor(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.professor.bo.buscarProfessor", ex);
        }
    }

    public Professor buscarProfessorPorSiape(Integer siape) throws NegocioException {
        try {
            return dao.buscarPorSiape(siape);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.professor.bo.buscarProfessorPorSiape", ex);
        }
    }
}
