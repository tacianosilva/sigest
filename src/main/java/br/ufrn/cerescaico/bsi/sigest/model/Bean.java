package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;

/**
 * @author Taciano Morais Silva
 * @version 13/11/2012
 * @since 13/11/2012
 */
public interface Bean extends Serializable {

    /**
     * Retorna o identificador do bean.
     * @return O identificador do bean.
     */
    public Integer getCodigo();

    /**
     * Modifica o identificador do bean.
     * @param id O novo identificador do bean.
     */
    public void setCodigo(Integer id);
}