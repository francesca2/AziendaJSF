package it.alfasoft.francesca.controller;

import java.io.Serializable;
import java.util.List;

import it.alfasoft.francesca.bean.FatturaBean;
import it.alfasoft.francesca.client.Invocazione;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name="fatController", eager=true)
@ViewScoped
public class FatturaController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Invocazione invocazione;
	private List<FatturaBean> fatture;
	private FatturaBean f;

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
	
	public FatturaBean getF() {
		return f;
	}

	public void setF(FatturaBean f) {
		this.f = f;
	}

	public void onRowEdit(RowEditEvent event) {
		//invocazione.putFattura((FatturaBean) event.getObject()).invoke();
        FacesMessage msg = new FacesMessage("Fattura salvata", ((FatturaBean) event.getObject()).getCodiceFattura());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	//invocazione.deleteFattura((FatturaBean) event.getObject()).invoke();
        FacesMessage msg = new FacesMessage("Fattura eliminata", ((FatturaBean) event.getObject()).getCodiceFattura());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
    public String registraFattura(FatturaBean f){
    	Response responsePost= invocazione.sendFattura(f).invoke(); 
    	if(responsePost.getStatus()!=201){
    		FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Fattura non registrata!"));
    	}
    	return "registraFattura?faces-reidrect-true";
    }
	
	public List<FatturaBean> updateLista(){
		Response risposta=invocazione.richiestaFatture().invoke();
		this.setFatture(risposta.readEntity(new GenericType<List<FatturaBean>>(){}));
		return fatture;
	}
	
	public FatturaBean getFatturaByCode(String code){
		Response risposta=invocazione.richiestaFatturaByCode(code).invoke();
		this.setF(risposta.readEntity(FatturaBean.class));
//    	if(risposta.getStatus()!=200){
//    		FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage("Fattura non trovata!"));
//    	}
		return f;
		
	}
	
	public List<FatturaBean> getFattureAnno(String anno){
		Response risposta=invocazione.richiestaFattureAnno(anno).invoke();
		this.setFatture(risposta.readEntity(new GenericType<List<FatturaBean>>(){}));
		return fatture;
	}

}
