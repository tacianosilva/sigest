package br.ufrn.cerescaico.bsi.sigest.dao.exceptions;

public class PreexistingEntityException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -6095493296186872200L;

    public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public PreexistingEntityException(String message) {
        super(message);
    }
}
