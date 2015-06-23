package br.ufrn.cerescaico.bsi.sigest.web.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufrn.cerescaico.bsi.sigest.Sigest;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;

/**
 * Conversor para Curso a partir do seu nome.
 * @author Taciano
 */
@FacesConverter(value = "cursoConverter")
public class CursoConverter implements Converter {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CursoConverter.class.getName());

    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Sigest sigest = Sigest.getInstance();
        try {
            return sigest.buscarCursoPorNome(arg2);
        } catch (NegocioException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 instanceof Curso) {
            Curso curso = (Curso) arg2;
            return String.valueOf(curso.getNome());
        }
        return "";
    }
}
