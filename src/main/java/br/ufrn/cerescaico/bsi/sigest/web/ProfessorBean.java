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

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.ufrn.cerescaico.bsi.sigest.Sigest;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

@ManagedBean(name = "professorBean")
@SessionScoped
public class ProfessorBean extends AbstractBean implements Serializable {

    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(ProfessorBean.class.getName());

    private static final long serialVersionUID = 1L;

    private Sigest sigest = Sigest.getInstance();

    private String nome = null;

    private Integer siape = null;

    private List<Professor> professores = null;

    private Professor professor = null;

    private Curso curso = null;

    private Integer codigocurso = null;

    private DualListModel<Curso> cursos;
    
    public ProfessorBean() {
        init(); 
    }

    public void init() {
        //Cursos
        List<Curso> cursosSource = new ArrayList<Curso>();
        List<Curso> cursosTarget = new ArrayList<Curso>();

        try {
            cursosSource.addAll(sigest.listarCursos());
        } catch (NegocioException ex) {
            cursosSource.addAll(new ArrayList<Curso>());
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException("erro.professor.bean.init.exception", ex);
        }

        cursos = new DualListModel<Curso>(cursosSource, cursosTarget);
    }

    public DualListModel<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(DualListModel<Curso> cursos) {
        this.cursos = cursos;
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Curso) item).getNome()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String manter() {
        return "/professor/manter";
    }

    public String incluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        setContext(context);

        try {
            sigest.inserirProfessor(getProfessor());
            context.addMessage("ProfessorBean.incluir", new FacesMessage(
                    msg("info.professor.bean.incluir.sucesso")));
        } catch (NegocioException ex) {
            Logger.getLogger(ProfessorBean.class.getName()).log(Level.SEVERE,
                    ex.getMessage(), ex);
            context.addMessage("professorBean.incluir", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    msg("erro.professor.bean.incluir.exception"),
                    ex.getMessage()));
        }
        return "/professor/manter";
    }

    public String listar() {
        try {
            professores = sigest.listarProfessores();
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
        //TODO Rever este código!
        if (curso == null && getCodigocurso() != null) {
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
