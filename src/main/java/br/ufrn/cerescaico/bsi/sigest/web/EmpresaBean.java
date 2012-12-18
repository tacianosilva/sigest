package br.ufrn.cerescaico.bsi.sigest.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Empresa;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

import br.ufrn.cerescaico.bsi.sigest.Sigest;


@ManagedBean(name = "empresaBean")
@SessionScoped
public class EmpresaBean implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private Sigest sigest = Sigest.getInstance();

	private List<Empresa> empresas = null;
	public Empresa empresa = new Empresa();
	private Empresa empresa1 = new Empresa();
	
	public Empresa inserir(){
		try{
			empresa1 = sigest.cadastrarEmpresa(empresa);
			initComps();
			//JOptionPane.showConfirmDialog(null, "Cliente cadastrou");
		}catch(NegocioException ex){
			Logger.getLogger(EmpresaBean.class.getName()).log(Level.SEVERE,
					ex.getMessage(), ex);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("empresaBean.inserir", new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao cadastrar a empresa", ex.getMessage()));
		}
		
		return empresa1;
	}
	
	public void editar(){
		try{
			sigest.editarEmpresa(empresa);
			initComps();
			//JOptionPane.showConfirmDialog(null, "Cliente cadastrou");
		}catch(NegocioException ex){
			Logger.getLogger(EmpresaBean.class.getName()).log(Level.SEVERE,
					ex.getMessage(), ex);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("empresaBean.editar", new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao editar a empresa", ex.getMessage()));
		}
	}
	
	public void excluir(){
		try{
			sigest.excluirEmpresa(empresa.getCodigo());
			initComps();
			//JOptionPane.showConfirmDialog(null, "Cliente cadastrou");
		}catch(NegocioException ex){
			Logger.getLogger(EmpresaBean.class.getName()).log(Level.SEVERE,
					ex.getMessage(), ex);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("empresaBean.inserir", new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao cadastrar a empresa", ex.getMessage()));
		}
	}

	public String listar() {
		try {
			empresas = sigest.listarEmpresas();
		} catch (NegocioException ex) {
			empresas = new ArrayList<Empresa>();
			Logger.getLogger(EmpresaBean.class.getName()).log(Level.SEVERE,
					ex.getMessage(), ex);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("empresaBean.listar", new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao carregar a lista de empresas", ex.getMessage()));
		}
		return "empresa";
	}

	public List<Empresa> getEmpresas() {
		listar();
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	public Empresa getEmpresa(){
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa){
		this.empresa = empresa;
	}
	
	public void initComps(){
		empresa = new Empresa();
	}
}
