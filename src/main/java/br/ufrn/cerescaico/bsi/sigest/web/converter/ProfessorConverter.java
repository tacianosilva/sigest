package br.ufrn.cerescaico.bsi.sigest.web.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufrn.cerescaico.bsi.sigest.Sigest;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

@FacesConverter(value = "professorConverter")
public class ProfessorConverter implements Converter {

    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(ProfessorConverter.class.getName());

    public Object getAsObject(FacesContext arg0, UIComponent arg1, String siape) {
        Sigest sigest = Sigest.getInstance();
        if (siape.equals("0")) {
            //return new Professor(0, Integer.parseInt(siape), "Select One");
            return null;
        }
        try {
            return sigest.buscarProfessorPorSiape(Integer.parseInt(siape));
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException("erro.professor.converter.asobject.exception",ex);
        }
    }

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 instanceof Professor) {
            Professor professor = (Professor) arg2;
            return String.valueOf(professor.getSiape());
        }
        return "0";
    }
}
