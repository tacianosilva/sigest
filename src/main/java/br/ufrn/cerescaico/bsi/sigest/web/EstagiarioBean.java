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
import br.ufrn.cerescaico.bsi.sigest.model.Estagiario;

@ManagedBean(name = "estagiarioBean")
@SessionScoped
public class EstagiarioBean implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private Sigest sigest = Sigest.getInstance();

    private List<Estagiario> estagiarios = null;

    private Estagiario estagiario = null;

    public EstagiarioBean(){
        estagiario = new Estagiario();
    }

    public String manter() {
        return "/estagiario/manter";
    }

    public String incluir() {
     FacesContext context = FacesContext.getCurrentInstance();
        try {
            sigest.cadastrarEstagiario(getEstagiario());
            context.addMessage("Estagiário cadastrado com sucesso!", new FacesMessage(FacesMessage.SEVERITY_INFO, "Estagiário cadastrado com sucesso!",""));
        } catch (NegocioException ex) {
            context.addMessage("Erro ao cadastrar estagiário. Tente novamente.", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar estagiário. Tente novamente.", ex.getMessage()));
        }
        return "";
    }

    public String listar() {
        try {
            estagiarios = sigest.listarEstagiarios();
        } catch (NegocioException ex) {
            estagiarios = new ArrayList<Estagiario>();
            Logger.getLogger(EstagiarioBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("estagiarioBean.listar", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar a lista de estagiarios", ex.getMessage()));
        }
        return "index";
    }

    public List<Estagiario> getEstagiarios() {
        listar();
        return estagiarios;
    }

    public void setEstagiarios(List<Estagiario> estagiarios) {
        this.estagiarios = estagiarios;
    }

    public Estagiario getEstagiario(){
        return estagiario;
    }

    public void setEstagiario(Estagiario estagiario){
        this.estagiario = estagiario;
    }

}
