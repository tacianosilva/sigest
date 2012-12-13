package br.ufrn.cerescaico.bsi.sigest.bo;

/**
 * Classe de exceção da camada de negócio.
 * @author Taciano Morais Silva
 * @version 15/06/2012
 * @since 15/06/2012
 */
public class NegocioException extends Exception {
    
    /**
     * Parâmetros para as mensagens.
     */
    private String[] parametros;

    /**
     * 
     * @param message
     * @param ex 
     */
    public NegocioException(String message, Exception ex) {
        super(message, ex);
    }

    /**
     * 
     * @param message 
     */
    public NegocioException(String message) {
        super(message);
    }
    
    /**
     * 
     * @param message
     * @param parametros 
     */
    public NegocioException(String message, String[] parametros) {
        super(message);
        this.parametros = parametros;
    }

    /**
     * 
     * @return 
     */
    public String[] getParametros() {
        return parametros;
    }

    /**
     * 
     * @param parametros 
     */
    public void setParametros(String[] parametros) {
        this.parametros = parametros;
    }
}