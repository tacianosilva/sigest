package br.ufrn.cerescaico.bsi.sigest;

import java.io.Serializable;
import java.util.List;

import br.ufrn.cerescaico.bsi.sigest.bo.CursoBO;
import br.ufrn.cerescaico.bsi.sigest.bo.EstagiarioBO;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;
import br.ufrn.cerescaico.bsi.sigest.model.Estagiario;

/**
 * Fachada do sistema Sigest.
 * 
 * @author Taciano de Morais Silva - tacianosilva@gmail.com
 * @version 25/10/2012, Taciano de Morais Silva - tacianosilva@gmail.com
 * @since 25/10/2012
 */
public final class Sigest extends AbstractFacade implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instância única da fachada.
	 */
	private static volatile Sigest instance = null;

	/**
	 * Construtor privado da fachada.
	 */
	private Sigest() {
	}

	/**
	 * Retorna a instância única do singleton Sigest (fachada).
	 * 
	 * @return A instância do Sepe.
	 */
	public static Sigest getInstance() {
		if (instance == null) {
			synchronized (Sigest.class) {
				Sigest inst = instance;
				if (inst == null) {
					instance = new Sigest();
				}
			}
		}
		return instance;
	}
	
	public Curso cadastrarCurso(Curso curso) throws NegocioException {
		CursoBO bo = createCursoBO();
        return bo.inserir(curso);
    }
    
    public List<Curso> listarCursos() throws NegocioException {
        CursoBO bo = createCursoBO();
        return bo.listar();
    }
    
    public Estagiario cadastrarEstagiario(Estagiario estagiario) throws NegocioException {
		EstagiarioBO bo = createEstagiarioBO();
        return bo.inserir(estagiario);
    }
    
    public List<Estagiario> listarEstagiarios() throws NegocioException {
    	EstagiarioBO bo = createEstagiarioBO();
        return bo.listar();
    }

}
