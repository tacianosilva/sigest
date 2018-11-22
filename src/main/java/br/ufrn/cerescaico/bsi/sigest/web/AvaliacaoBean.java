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
import br.ufrn.cerescaico.bsi.sigest.model.Avaliacao;
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

@ManagedBean(name = "avaliacaoBean")
@SessionScoped
public class AvaliacaoBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Sigest sigest = Sigest.getInstance();

    private List<Avaliacao> avaliacoes = null;
    private List<Avaliacao> avaliacoesProf = null;
    private Avaliacao avaliacao = new Avaliacao();
    private Professor professor = new Professor();
    private int codProf;

    public String inserir(){
        try {
            sigest.cadastrarAvaliacao(avaliacao);
        } catch (NegocioException ex) {
            Logger.getLogger(AvaliacaoBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("avaliacaoBean.inserir", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar avaliacao", ex.getMessage()));
        }
        return "manterAvaliacao";
    }

    public String listar() {
        try {
            avaliacoes = sigest.listarAvaliacoes();
        } catch (NegocioException ex) {
            avaliacoes = new ArrayList<Avaliacao>();
            Logger.getLogger(AvaliacaoBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("avaliacaoBean.listar", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar a lista de avaliacoes", ex.getMessage()));
        }
        return "manterProfessor";
    }

    public String consultarPorProf() {
        try {
            avaliacoesProf = sigest.listarAvaliacoesProf(professor.getCodigo());
        } catch (NegocioException ex) {
            avaliacoesProf = new ArrayList<Avaliacao>();
            Logger.getLogger(AvaliacaoBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("avaliacaoBean.consultarPorProf", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar a lista de avaliacoes", ex.getMessage()));
        }
        return "manterProfessor";
    }

    public List<Avaliacao> getAvaliacoes() {
        listar();
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Avaliacao> getAvaliacoesProf() {
        consultarPorProf();
        return avaliacoesProf;
    }

    public void setAvaliacoesProf(List<Avaliacao> avaliacoesProf) {
        this.avaliacoesProf = avaliacoesProf;
    }

    public void setCodProf(int codProf){
        this.codProf = codProf;
    }

    public int getCodProf(){
        return codProf;
    }

    public Avaliacao getAvaliacao(){
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao){
        this.avaliacao = avaliacao;
    }

    public void setProfessor(Professor professor){
        this.professor = professor;
    }

    public Professor getProfessor(){
        return professor;
    }
}
