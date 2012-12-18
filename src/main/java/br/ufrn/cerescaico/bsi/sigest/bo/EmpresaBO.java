package br.ufrn.cerescaico.bsi.sigest.bo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrn.cerescaico.bsi.sigest.dao.EmpresaJpaController;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.util.JPAUtil;
import br.ufrn.cerescaico.bsi.sigest.model.Empresa;

public class EmpresaBO extends AbstractBO{

private static final Logger logger = Logger.getLogger(CursoBO.class.getName());
    
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
        }
        catch (PreexistingEntityException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.empresa.bo.inserir.PreexistingEntityException",ex);
        }
        catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.empresa.bo.inserir.exception",ex);
        }
    }
    
    public List<Empresa> listar() throws NegocioException {
    	try {
            return dao.findEmpresaEntities();
        }
        catch (Exception ex) {
        	logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.empresa.bo.listar", ex);
        }
    }
}
