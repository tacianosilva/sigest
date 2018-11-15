package br.ufrn.cerescaico.bsi.sigest.dao.exceptions;

public class NonexistentEntityException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -7699206043578165485L;

    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonexistentEntityException(String message) {
        super(message);
    }
}
