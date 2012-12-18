/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.bsi.sigest.bo;

import br.ufrn.cerescaico.bsi.sigest.dao.PerfilJpaController;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.util.JPAUtil;
import br.ufrn.cerescaico.bsi.sigest.model.Perfil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa a Entidade de Négocio para Perfil.
 * @author taciano
 */
public class PerfilNegocio extends AbstractBO {
    
    /**
     * Logger.
     */
    private Logger logger = Logger.getLogger(UsuarioBO.class.getName());
    
    private PerfilJpaController dao;

    public PerfilNegocio() {
        this.dao = new PerfilJpaController(null, JPAUtil.EMF);
    }
    
    public void inserir(Perfil perfil) throws NegocioException {
        try {
            dao.create(perfil);
        }
        catch (PreexistingEntityException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new NegocioException("MSG_ERRO_001",ex);
        }
        catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new NegocioException("MSG_ERRO_002",ex);
        }
    }
    
}
