package br.ufrn.cerescaico.bsi.sigest.web;

import br.ufrn.cerescaico.bsi.sigest.Sigest;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "cursoBean")
@SessionScoped
public class CursoBean implements Serializable {

    /**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private Sigest sigest = Sigest.getInstance();
    
    private List<Curso> cursos = null;
    
    public String listar() {
        try {
            cursos = sigest.listarCursos();
        } catch (NegocioException ex) {
            cursos = new ArrayList<Curso>();
            Logger.getLogger(CursoBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("cursoBean.listar", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar a lista de cursos", ex.getMessage()));
        }
        return "index";
    }

    public List<Curso> getCursos() {
    	listar();
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}