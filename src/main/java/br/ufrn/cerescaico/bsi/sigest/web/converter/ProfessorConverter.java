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

@FacesConverter(value = "professorConverter")
public class ProfessorConverter implements Converter {

    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(ProfessorConverter.class.getName());

    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Sigest sigest = Sigest.getInstance();
        //try {
            return "Teste PickList Professor"; // TODO sigest.buscarProfessorPorNome(arg2);
        /*} catch (NegocioException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            //ex.printStackTrace();
        }*/
        //return null;
    }

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 instanceof Curso) {
            Curso curso = (Curso) arg2;
            return String.valueOf(curso.getNome());
        }
        return "";
    }
}
