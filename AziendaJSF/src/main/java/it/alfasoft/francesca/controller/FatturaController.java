package it.alfasoft.francesca.controller;

import java.util.List;

import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.bean.DipendenteBean;
import it.alfasoft.francesca.bean.FatturaBean;
import it.alfasoft.francesca.client.Invocazione;
import it.alfasoft.francesca.service.Servizi;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name="fatController", eager=true)
@ViewScoped
public class FatturaController {
	
	private Invocazione invocazione;
	private List<FatturaBean> fatture;

	public FatturaController() {
	}

	@PostConstruct
	public void init(){
		invocazione= new Invocazione();
	}

	public List<FatturaBean> getFatture() {
		return fatture;
	}

	public void setFatture(List<FatturaBean> fatture) {
		this.fatture = fatture;
	}
	
	public void onRowEdit(RowEditEvent event) {
		//invocazione.sendFattura((FatturaBean) event.getObject()).invoke();
        FacesMessage msg = new FacesMessage("Fattura salvata", ((FatturaBean) event.getObject()).getCodiceFattura());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	//s.eliminaUtente((FatturaBean) event.getObject());
        FacesMessage msg = new FacesMessage("Fattura eliminata", ((FatturaBean) event.getObject()).getCodiceFattura());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
    public String registraFattura(FatturaBean f){
    	Response responsePost= invocazione.sendFattura(f).invoke();   	
    	return "registraFattura?faces-reidrect-true";
    }
	
    

}
