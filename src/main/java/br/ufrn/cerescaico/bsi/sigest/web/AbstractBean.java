package br.ufrn.cerescaico.bsi.sigest.web;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public abstract class AbstractBean {

    protected FacesContext context;

    public AbstractBean() {
    }

    protected FacesContext getContext() {
        return context;
    }

    protected void setContext(FacesContext context) {
        this.context = context;
    }

    protected String msg(String key) {
        String message = null;
        if (context != null) {
            ResourceBundle bundle = context.getApplication().getResourceBundle(
                    FacesContext.getCurrentInstance(), "msg");
            message = bundle.getString(key);
        }
        return message;
    }

}
