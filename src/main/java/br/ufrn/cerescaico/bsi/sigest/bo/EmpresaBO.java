package br.ufrn.cerescaico.bsi.sigest.bo;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrn.cerescaico.bsi.sigest.dao.EmpresaJpaController;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.util.JPAUtil;
import br.ufrn.cerescaico.bsi.sigest.model.Empresa;

/**
 * Classe que representa a Entidade de Negócio para Empresa.
 * @author Taciano de Morais Silva
 */
public class EmpresaBO extends AbstractBO {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(EmpresaBO.class.getName());

    private EmpresaJpaController dao;

    public EmpresaBO() {
        this.dao = new EmpresaJpaController(JPAUtil.EMF);
    }

    /**
     * Insere uma nova empresa.
     * @param empresa A nova empresa.
     * @return A empresa inserida com a chave primária preenchida.
     * @throws NegocioException Caso ocorra erro na inserção.
     */
    public Empresa inserir(Empresa empresa) throws NegocioException {
        try {
            return dao.create(empresa);
        } catch (PreexistingEntityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.empresa.bo.inserir.PreexistingEntityException",ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.empresa.bo.inserir.exception",ex);
        }
    }

    public void editar(Empresa empresa) throws NegocioException {
        try {
            dao.edit(empresa);
        } catch (PreexistingEntityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.empresa.bo.editar.PreexistingEntityException",ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.empresa.bo.editar.exception",ex);
        }
    }

    public void excluir(Integer codigo) throws NegocioException {
        try {
            dao.destroy(codigo);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.empresa.bo.editar.exception",ex);
        }
    }

    public List<Empresa> listar() throws NegocioException {
        try {
            return dao.findEmpresaEntities();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.empresa.bo.listar", ex);
        }
    }
}
