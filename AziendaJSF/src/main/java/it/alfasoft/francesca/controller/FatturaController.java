package it.alfasoft.francesca.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import it.alfasoft.francesca.bean.FatturaBean;
import it.alfasoft.francesca.client.Invocazione;
import it.alfasoft.francesca.client.InvocazioneFatture;

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
	
	private InvocazioneFatture invocazione;
	private List<FatturaBean> fatture;
	private FatturaBean f;
	
	 private final static String[] mesi;
	 
	 
	    static {
	    	mesi = new String[13];
	    	mesi[0] = " ";
	    	mesi[1] = "Gennaio";
	    	mesi[2] = "Febbraio";
	    	mesi[3] = "Marzo";
	    	mesi[4] = "Aprile";
	    	mesi[5] = "Maggio";
	    	mesi[6] = "Giugno";
	    	mesi[7] = "Luglio";
	    	mesi[8] = "Agosto";
	    	mesi[9] = "Settembre";
	    	mesi[10] = "Ottobre";
	    	mesi[11] = "Novembre";
	    	mesi[12] = "Dicembre";
	    }
	    
		 private final static String[] anni;
		 
		    static {
		    	anni = new String[4];
		    	anni[0] = "2013";
		    	anni[1] = "2014";
		    	anni[2] = "2015";
		    	anni[3] = "2016";
		    }

	public FatturaController() {
	}

	@PostConstruct
	public void init(){
		invocazione= new InvocazioneFatture();
	}
	
    public List<String> getAnni() {
        return Arrays.asList(anni);
    }
    
    public List<String> getMesi() {
        return Arrays.asList(mesi);
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
		return f;
		
	}
	
	public List<FatturaBean> getFattureAnno(String anno){
		Response risposta=invocazione.richiestaFattureAnno(anno).invoke();
		this.setFatture(risposta.readEntity(new GenericType<List<FatturaBean>>(){}));
		return fatture;
	}

	public List<FatturaBean> getFattureAnnoMese(String anno, String mese){
		if(mese.equals(" "))
		{
			Response risposta=invocazione.richiestaFattureAnno(anno).invoke();
			this.setFatture(risposta.readEntity(new GenericType<List<FatturaBean>>(){}));
		}
		else{
			switch (mese.toLowerCase()) {
			case "gennaio":
				mese = "1";
                break;
            case "febbraio":
            	mese = "2";
                break;
            case "marzo":
            	mese = "3";
                break;
            case "aprile":
            	mese = "4";
                break;
            case "maggio":
            	mese = "5";
                break;
            case "giugno":
            	mese = "6";
                break;
            case "luglio":
            	mese = "7";
                break;
            case "agosto":
            	mese = "8";
                break;
            case "settembre":
            	mese = "9";
                break;
            case "ottobre":
            	mese = "10";
                break;
            case "novembre":
            	mese = "11";
                break;
            case "dicembre":
            	mese = "12";
                break;
			}
		Response risposta=invocazione.richiestaFatturaMese(anno,mese).invoke();
		this.setFatture(risposta.readEntity(new GenericType<List<FatturaBean>>(){}));
		}
		return fatture;
	}
}
