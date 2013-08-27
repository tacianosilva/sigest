package br.ufrn.cerescaico.bsi.sigest.bo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrn.cerescaico.bsi.sigest.dao.ProfessorJpaController;
import br.ufrn.cerescaico.bsi.sigest.dao.util.JPAUtil;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

public class ProfessorBO extends AbstractBO {
	
	private static final Logger logger = Logger.getLogger(ProfessorBO.class.getName());
	
	private ProfessorJpaController daoProf;
	
	public ProfessorBO (){
		this.daoProf = new ProfessorJpaController(JPAUtil.EMF);
	}
	
	public Professor inserir(Professor professor) throws NegocioException {
        try {
            return daoProf.create(professor);
        }
        catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.professor.bo.inserir.exception",ex);
        }
    }
	
	public List<Professor> listar() throws NegocioException {
    	try {
            return daoProf.findProfessorEntities();
        }
        catch (Exception ex) {
        	logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.professor.bo.listar", ex);
        }
    }

}
