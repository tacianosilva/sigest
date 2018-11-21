package br.ufrn.cerescaico.bsi.sigest;

import java.io.Serializable;
import java.util.List;

import br.ufrn.cerescaico.bsi.sigest.annotation.AnnotationA;
import br.ufrn.cerescaico.bsi.sigest.bo.AvaliacaoBO;
import br.ufrn.cerescaico.bsi.sigest.bo.CursoBO;
import br.ufrn.cerescaico.bsi.sigest.bo.EstagiarioBO;
import br.ufrn.cerescaico.bsi.sigest.bo.EstagioBO;
import br.ufrn.cerescaico.bsi.sigest.bo.EmpresaBO;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.bo.ProfessorBO;
import br.ufrn.cerescaico.bsi.sigest.model.Avaliacao;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;
import br.ufrn.cerescaico.bsi.sigest.model.Estagiario;
import br.ufrn.cerescaico.bsi.sigest.model.Estagio;
import br.ufrn.cerescaico.bsi.sigest.model.Empresa;

/**
 * Fachada do sistema Sigest.
 *
 * @author Taciano de Morais Silva - tacianosilva@gmail.com
 * @version 25/10/2012, Taciano de Morais Silva - tacianosilva@gmail.com
 * @since 25/10/2012
 */
@AnnotationA
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

    public Curso inserirCurso(Curso curso) throws NegocioException {
        CursoBO bo = createCursoBO();
        return bo.inserir(curso);
    }

    public Curso buscarCurso(Integer id) throws NegocioException {
        CursoBO bo = createCursoBO();
        return bo.buscarCurso(id);
    }

    public Curso buscarCursoPorNome(String nome) throws NegocioException {
        CursoBO bo = createCursoBO();
        return bo.buscarCursoPorNome(nome);
    }

    public List<Curso> listarCursos() throws NegocioException {
        CursoBO bo = createCursoBO();
        return bo.listar();
    }

    public void excluirCurso(Integer codigo) throws NegocioException {
        CursoBO bo = createCursoBO();
        bo.excluir(codigo);
    }

    public Estagiario cadastrarEstagiario(Estagiario estagiario) throws NegocioException {
        EstagiarioBO bo = createEstagiarioBO();
        return bo.inserir(estagiario);
    }

    public List<Estagiario> listarEstagiarios() throws NegocioException {
        EstagiarioBO bo = createEstagiarioBO();
        return bo.listar();
    }

    //Métodos para o caso de uso Empresa
    public Empresa cadastrarEmpresa(Empresa empresa) throws NegocioException {
        EmpresaBO bo = createEmpresaBO();
        return bo.inserir(empresa);
    }

    public void editarEmpresa(Empresa empresa) throws NegocioException{
        EmpresaBO bo = createEmpresaBO();
        bo.editar(empresa);
    }

    public void excluirEmpresa(Integer codigo) throws NegocioException{
        EmpresaBO bo = createEmpresaBO();
        bo.excluir(codigo);
    }

    public List<Empresa> listarEmpresas() throws NegocioException {
        EmpresaBO bo = createEmpresaBO();
        return bo.listar();
    }

    public Estagio cadastrarEstagio(Estagio estagio) throws NegocioException{
        EstagioBO bo = createEstagioBO();
        return bo.inserir(estagio);
    }

    public List<Estagio> listarEstagios() throws NegocioException {
        EstagioBO bo = createEstagioBO();
        return bo.listar();
    }

    public void alterarEstagio(Estagio estagio) throws NegocioException {
        // TODO Auto-generated method stub
        EstagioBO bo = createEstagioBO();
        bo.alterar(estagio);
    }

    public void excluirEstagio(Integer id) throws NegocioException {
        // TODO Auto-generated method stub
        EstagioBO bo = createEstagioBO();
        bo.excluir(id);

    }

    /**
     * @param professor O Professor a ser inserido.
     * @return Retorna o professor inserido com a chave primária definida.
     * @throws NegocioException Caso ocorra erro na inserção do Professor.
     */
    public Professor inserirProfessor(Professor professor) throws NegocioException {
        ProfessorBO bo = createProfessorBO();
        return bo.inserir(professor);
    }

    public List<Professor> listarProfessores() throws NegocioException{
        ProfessorBO bo = createProfessorBO();
        return bo.listar();
    }

    public Professor buscarProfessorPorSiape(Integer siape) throws NegocioException {
        ProfessorBO bo = createProfessorBO();
        return bo.buscarProfessorPorSiape(siape);
    }

    public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao) throws NegocioException {
        AvaliacaoBO bo = createAvaliacaoBO();
        return bo.inserir(avaliacao);
    }

    public List<Avaliacao> listarAvaliacoes() throws NegocioException {
        AvaliacaoBO bo = createAvaliacaoBO();
        return bo.listar();
    }

    public List<Avaliacao> listarAvaliacoesProf(int codProf) throws NegocioException {
        AvaliacaoBO bo = createAvaliacaoBO();
        return bo.buscarAvaliacaoPorProf(codProf);
    }
}
