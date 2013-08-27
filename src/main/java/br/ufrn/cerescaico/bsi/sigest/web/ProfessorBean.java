package br.ufrn.cerescaico.bsi.sigest.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ufrn.cerescaico.bsi.sigest.Sigest;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

@ManagedBean(name = "professorBean")
@SessionScoped
public class ProfessorBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Sigest sigest = Sigest.getInstance();

    private String nome = null;

    private Integer siape = null;

    private List<Professor> professores = null;

    private Professor professor = null;
    private Curso curso = null;

    private Integer codigocurso = null;

    public String manter() {
        return "/professor/manter";
    }

    public String inserir() {
        try {
            sigest.cadastrarProfessor(getProfessor());
        } catch (NegocioException ex) {
            Logger.getLogger(ProfessorBean.class.getName()).log(Level.SEVERE,
                    ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("professorBean.inserir", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar professor",
                    ex.getMessage()));
        }
        return "manterProfessor";
    }

    public String listar() {
        try {
            professores = sigest.listarProfessor();
        } catch (NegocioException ex) {
            professores = new ArrayList<Professor>();
            Logger.getLogger(ProfessorBean.class.getName()).log(Level.SEVERE,
                    ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("professorBean.listar", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Erro ao carregar a lista de professores", ex.getMessage()));
        }
        return "manterProfessor";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSiape() {
        return siape;
    }

    public void setSiape(Integer siape) {
        this.siape = siape;
    }

    public List<Professor> getProfessores() {
        listar();
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public Professor getProfessor() throws NegocioException {
        professor = new Professor();
        professor.setNome(getNome());
        professor.setSiape(getSiape());
        professor.setCurso(getCurso());
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Curso getCurso() throws NegocioException {
        if (curso == null) {
            curso = sigest.buscarCurso(getCodigocurso());
        }
        return curso;
    }

    public Integer getCodigocurso() {
        return codigocurso;
    }

    public void setCodigocurso(Integer codigocurso) {
        this.codigocurso = codigocurso;
    }
}
