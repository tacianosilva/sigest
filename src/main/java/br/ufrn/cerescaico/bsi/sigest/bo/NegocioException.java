package br.ufrn.cerescaico.bsi.sigest.bo;

/**
 * Classe de exceção da camada de negócio.
 * @author Taciano Morais Silva
 * @version 15/06/2012
 * @since 15/06/2012
 */
public class NegocioException extends Exception {

    private static final long serialVersionUID = 3788893181715411426L;

    /**
     * Parâmetros para as mensagens.
     */
    private String[] parametros;

    public NegocioException(String message, Exception ex) {
        super(message, ex);
    }

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, String[] parametros) {
        super(message);
        this.parametros = parametros;
    }

    public String[] getParametros() {
        return parametros;
    }

    public void setParametros(String[] parametros) {
        this.parametros = parametros;
    }
}