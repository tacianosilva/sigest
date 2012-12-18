package br.ufrn.cerescaico.bsi.sigest.web;

import br.ufrn.cerescaico.bsi.sigest.Sigest;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Estagio;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "estagioBean")
@SessionScoped
public class EstagioBean implements Serializable {

    /**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private Sigest sigest = Sigest.getInstance();
    
    private List<Estagio> estagios = null;
    
    private Estagio estagio = null;
    
    

	public String incluir() {
        FacesContext context = FacesContext.getCurrentInstance();
           try {
               sigest.cadastrarEstagio(getEstagio());
               context.addMessage("Estágio cadastrado com sucesso!", new FacesMessage(FacesMessage.SEVERITY_INFO, "Estágio cadastrado com sucesso!",""));
           } catch (NegocioException ex) {
               context.addMessage("Erro ao cadastrar estágio. Tente novamente.", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar estágio. Tente novamente.", ex.getMessage()));
           }
           return "";
       }
    
    public String listar() {
        try {
            estagios = sigest.listarEstagios();
        } catch (NegocioException ex) {
            estagios = new ArrayList<Estagio>();
            Logger.getLogger(EstagioBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("estagioBean.listar", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar a lista de estagios", ex.getMessage()));
        }
        return "estagio";
    }

    public List<Estagio> getEstagios() {
    	listar();
        return estagios;
    }
    
    public String manter(){
    	return "estagio.xhtml";
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }
    
    public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}
}
