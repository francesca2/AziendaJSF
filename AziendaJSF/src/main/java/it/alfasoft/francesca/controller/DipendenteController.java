package it.alfasoft.francesca.controller;

import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.bean.DipendenteBean;
import it.alfasoft.francesca.service.Servizi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name="dpController", eager=true)
@ViewScoped
public class DipendenteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private final static String[] posizioni;
	 
	 
	    static {
	    	posizioni = new String[7];
	    	posizioni[0] = "Segretaria";
	    	posizioni[1] = "Contabile";
	    	posizioni[2] = "Project Manager";
	    	posizioni[3] = "Dipendente livello 1";
	    	posizioni[4] = "Dipendente livello 2";
	    	posizioni[5] = "Dipendente livello 3";
	    	posizioni[6] = "Apprendista";
	    }
	 
	
	private List<DipendenteBean> listaDipendenti;

	private Servizi s;
	
	//attributi che mi servono per le busta paga
	private Map<String,Long> mappaDipendenti= new HashMap<String,Long>();
	private long idDip;

	public DipendenteController(){

	}

	@PostConstruct
	public void init(){
		s=new Servizi();
	}
	
    public List<String> getPosizioni() {
        return Arrays.asList(posizioni);
    }

	public List<DipendenteBean> getListaDipendenti() {
		return listaDipendenti;
	}

	public void setListaDipendenti(List<DipendenteBean> listaDipendenti) {
		this.listaDipendenti = listaDipendenti;
	}

	public Servizi getS() {
		return s;
	}

	public void setS(Servizi s) {
		this.s = s;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Map<String, Long> getMappaDipendenti() {

		return mappaDipendenti;
	}

	public void setMappaDipendenti(Map<String, Long> mappaDipendenti) {
		this.mappaDipendenti = mappaDipendenti;
	}

	public long getIdDip() {
		return idDip;
	}

	public void setIdDip(long idDip) {
		this.idDip = idDip;
	}

	public List<DipendenteBean> updateLista(){
		this.setListaDipendenti(s.getDipendenti());
		return listaDipendenti;
	}
	
	public void onRowEdit(RowEditEvent event) {
		s.saveDipendente((DipendenteBean) event.getObject());
        FacesMessage msg = new FacesMessage("Dipendente salvato", ((DipendenteBean) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	s.eliminaUtente((DipendenteBean) event.getObject());
        FacesMessage msg = new FacesMessage("Dipendente eliminato", ((DipendenteBean) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String aggiungiDipendente(DipendenteBean d){
    	String usnm=d.getUsername();  	
    	if(!s.trovaUsername(usnm)){
    		s.registraDipendente(d);
    		return "elencoDipendenti?faces-redirect=true";
    	}
    	
    	else{
    		FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Username già in uso"));
    		return "registraDipendente";
    	}
    }
    
    public Map<String,Long> updateMappaDipendenti(){
    	for(DipendenteBean d : s.getDipendenti())
    	{
    	mappaDipendenti.put(d.getNome()+" "+d.getCognome(), d.getId_Utente());
    	}
    	return mappaDipendenti;
    }
    
    public String registraBustaPaga(BustaPaga b){
    	DipendenteBean d=s.getDipendenteById(idDip);
    	b.setDipendente(d);
    	s.salvaBustaPaga(b);
    	return "registraBustePaga?faces-reidrect-true";
    }

}

