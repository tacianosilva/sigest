package br.ufrn.cerescaico.bsi.sigest.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufrn.cerescaico.bsi.sigest.Sigest;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;

@FacesConverter(value = "cursoConverter")
public class CursoConverter implements Converter {

	// private
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		Sigest sigest = Sigest.getInstance();
		try {
			return sigest.buscarCursoPorNome(arg2);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return sigest.buscarCurso(Integer.parseInt(arg2));
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Curso) {
			Curso curso = (Curso) arg2;
			return String.valueOf(curso.getNome());
		}
		return "";
	}

}
