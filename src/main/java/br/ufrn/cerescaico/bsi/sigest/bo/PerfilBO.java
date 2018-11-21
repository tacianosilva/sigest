/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.bsi.sigest.bo;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrn.cerescaico.bsi.sigest.dao.PerfilJpaController;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.util.JPAUtil;
import br.ufrn.cerescaico.bsi.sigest.model.Perfil;

/**
 * Classe que representa a Entidade de Négocio para Perfil.
 * @author taciano
 */
public class PerfilBO extends AbstractBO {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PerfilBO.class.getName());

    private PerfilJpaController dao;

    public PerfilBO() {
        this.dao = new PerfilJpaController(null, JPAUtil.EMF);
    }

    public void inserir(Perfil perfil) throws NegocioException {
        try {
            dao.create(perfil);
        } catch (PreexistingEntityException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            throw new NegocioException("MSG_ERRO_001",ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            throw new NegocioException("MSG_ERRO_002",ex);
        }
    }

}
