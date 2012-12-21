package br.ufrn.cerescaico.bsi.sigest.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.splitbutton.SplitButton;

import br.ufrn.cerescaico.bsi.sigest.Sigest;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

@ManagedBean(name = "professorBean")
@SessionScoped
public class ProfessorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Sigest sigest = Sigest.getInstance();
    
    private List<Professor> professores = null;
    private Professor professor = new Professor();
    private Curso curso = new Curso();
    private Integer cursoId;
    private String cursoToString;
    
    public String inserir(){
    	try {
    		//cursoId = Integer.parseInt(Split(cursoToString, "-"));
    		//String[] pro = cursoToString.split("\\-");
    		//cursoId = Integer.parseInt(pro[0]);
    		//curso = sigest.buscarCurso(cursoId);
    		//professor.setCurso(curso);
            sigest.cadastrarProfessor(professor);
        } catch (NegocioException ex) {
            Logger.getLogger(ProfessorBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("professorBean.inserir", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar professor", ex.getMessage()));
        }
        return "manterProfessor";
    }
    
    public String listar() {
        try {
            professores = sigest.listarProfessor();
        } catch (NegocioException ex) {
            professores = new ArrayList<Professor>();
            Logger.getLogger(ProfessorBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("professorBean.listar", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar a lista de professores", ex.getMessage()));
        }
        return "manterProfessor";
    }
   
    public List<Professor> getProfessores() {
    	listar();
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
    
    public Professor getProfessor(){
    	return professor;
    }
    
    public void setProfessor(Professor professor){
    	this.professor = professor;
    }
    
    public void setCurso(Curso curso){
    	this.curso = curso;
    }
    
    public Curso getCurso(){
    	return curso;
    }
    
    public void setCursoId(Integer cursoId){
    	this.cursoId = cursoId;
    }
    
    public Integer getCursoId(){
    	return cursoId;
    }
    
    public void setCursoToString(String cursoToString){
    	this.cursoToString = cursoToString;
    }
    
    public String getCursoToString(){
    	return cursoToString;
    }
}
