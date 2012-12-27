/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.bsi.sigest.bo;

import br.ufrn.cerescaico.bsi.sigest.dao.UsuarioDao;
import br.ufrn.cerescaico.bsi.sigest.dao.exceptions.PreexistingEntityException;
import br.ufrn.cerescaico.bsi.sigest.dao.util.JPAUtil;
import br.ufrn.cerescaico.bsi.sigest.model.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de negócio do bean Usuário do sistema SisEventos.
 *
 * @author Taciano Morais Silva
 * @version 23/05/2012, 19h27m
 * @since 23/05/2012, 19h27m
 */
public class UsuarioBO extends AbstractBO {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(UsuarioBO.class.getName());
    private UsuarioDao dao;

    public UsuarioBO() {
        this.dao = new UsuarioDao(null, JPAUtil.EMF);
    }

    /**
     * Autentica o usuários. Se não for possível autenticar o usuário será
     * retornado
     * <code>null</code>.
     *
     * @param username Login do usuário.
     * @param password Senha do usuário.
     * @return O usuário autenticado, ou
     * <code>null</code> caso contrário.
     * @throws SepeException Casp ocorra algum erro de acesso ao banco.
     */
    public Usuario autenticar(String username, String password) throws NegocioException {
        //FIXME
        validarLogin(username);
        try {
            return dao.autenticar(username, password);
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.usuario.bo.autenticar", ex);
        }
    }

    /**
     * Incluir usuário no sistema.
     *
     * @param usuario O usuário a ser incluído.
     * @return O usuário inserido com o código gerado automaticamente.
     */
    public Usuario inserir(Usuario usuario) throws NegocioException {
        verificarNull(usuario);
        verificarLoginExistente(usuario);
        try {
            return dao.create(usuario);
        }
        catch (PreexistingEntityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.usuario.bo.inserir.preexist", ex);
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.usuario.bo.inserir", ex);
        }
    }

    /**
     * Lista todos os usuários cadastrados no sistema.
     *
     * @return A lista de usuários.
     */
    public List<Usuario> listar() throws NegocioException {
        try {
            return dao.findUsuarioEntities();
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.usuario.bo.listar", ex);
        }
    }

    private void validarLogin(String username) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Busca o usuário a partir do seu identificador.
     *
     * @param id O identificador do usuário.
     * @return O usuário localizado.
     */
    public Usuario buscar(Integer id) throws NegocioException {
        try {
            return dao.findUsuario(id);
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.usuario.bo.buscar", ex);
        }
    }

    /**
     * Busca o usuário a partir do seu email (único no sistema).
     *
     * @param email O email do usuário.
     * @return O usuário localizado.
     */
    public Usuario buscarPorEmail(String email) throws NegocioException {
        try {
            return dao.buscarPorEmail(email);
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.usuario.bo.buscarPorEmail", ex);
        }
    }

    /**
     *
     * @param usuario
     * @throws NegocioException
     */
    private void verificarLoginExistente(Usuario usuario) throws NegocioException {
        try {
            Usuario usuarioBD = dao.buscarPorEmail(usuario.getEmail());

            if (usuarioBD != null && usuarioBD.getCodigo().equals(usuarioBD.getCodigo())) {
                throw new NegocioException("erro.usuario.dao.usuario.duplicado");
            }
        }
        catch (NegocioException e) {
            throw new NegocioException("erro.usuario.login.existente");
        }
    }

    /*
     * Alterar os dados do usário. @param usuario O usuário a ser alterado.
     *
     * public void alterar(Usuario usuario) throws NegocioException {
     * verificarNull(usuario); if (isAdminUser(usuario) &&
     * !isAdminUser(getSisEventos().getUsuarioSessao())) { throw new
     * NegocioException("erro.usuario.bo.isAdminUser"); }
     * //verificarLoginExistente(usuario); try { verificarTamMin("login",
     * usuario.getLogin()); verificarTamMin("nome", usuario.getNome());
     *
     * Dao<Usuario> dao = new UsuarioDao(); dao.update(usuario); } catch
     * (DataAccessLayerException ex) { LOGGER.error(ex.getMessage(), ex); throw
     * new SepeException("erro.usuario.bo.alterar", ex); }
    }
     */
    /**
     * Exclui o usuário do sistema.
     *
     * @param usuario O usuário a ser excluído.
     */
    public void excluir(Usuario usuario) throws NegocioException {
        if (isAdminUser(usuario)) {
            throw new NegocioException("erro.usuario.bo.excluir.isAdminUser");
        }
        try {
            excluir(usuario.getCodigo());
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.usuario.bo.excluir", ex);
        }
    }

    /**
     * Exclui o usuário do sistema.
     *
     * @param id O identificador do usuário a ser excluído.
     */
    public void excluir(Integer id) throws NegocioException {
        if (isAdminUser(id)) {
            throw new NegocioException("erro.usuario.bo.excluir.hasAdminUser");
        }
        try {
            dao.destroy(id);
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.usuario.bo.excluir.id", ex);
        }
    }

    /**
     * Pesquisar usuário filtrando por nome.
     *
     * @param nome O nome do usuário.
     */
    public List<Usuario> pesquisar(String nome) throws NegocioException {
        try {
            // verificarTamMin("login", login);
            //verificarTamMin("nome", nome);
            return dao.pesquisar(nome);
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new NegocioException("erro.usuario.bo.pesquisar", ex);
        }
    }

    private boolean isAdminUser(Integer id) throws NegocioException {
        if (id != null && id.equals(ADMIN)) {
            return true;
        }
        return false;
    }

    private boolean isAdminUser(Usuario usuario) throws NegocioException {
        if (usuario != null) {
            return isAdminUser(usuario.getCodigo());
        }
        return false;
    }

    private void verificarNull(Object obj) throws NegocioException {
        if (checkNull(obj)) {
            throw new NegocioException("erro.usuario.bo.null");
        }
    }

    /**
     *
     * @param texto
     * @throws br.cesed.lti.Sepe.bo.SepeException
     */
    private void verificarTamMin(String att, String texto) throws NegocioException {
        if (texto == null || texto.equals(STRING_VAZIO)
                || texto.length() < TAMANHO_MINIMO) {
            String[] parametros = {att, TAMANHO_MINIMO + ""};
            throw new NegocioException("erro.usuario.bo.pesquisar.tam.min", parametros);
        }
    }
}
