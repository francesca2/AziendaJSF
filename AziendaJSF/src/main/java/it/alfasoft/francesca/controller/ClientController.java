package it.alfasoft.francesca.controller;

import it.alfasoft.francesca.bean.ClienteBean;
import it.alfasoft.francesca.bean.UtenteBean;
import it.alfasoft.francesca.service.Servizi;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name="clController", eager=true)
@ViewScoped
public class ClientController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ClienteBean> listaClienti;

	private Servizi s;

	public ClientController(){

	}

	@PostConstruct
	public void init(){
		s=new Servizi();
	}

	public List<ClienteBean> getListaClienti() {
		return listaClienti;
	}

	public void setListaClienti(List<ClienteBean> listaClienti) {
		this.listaClienti = listaClienti;
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
	
	public List<ClienteBean> updateLista(){
		this.setListaClienti(s.getClienti());
		return listaClienti;
	}
	
	public void onRowEdit(RowEditEvent event) {
		s.saveCliente((ClienteBean) event.getObject());
        FacesMessage msg = new FacesMessage("Cliente salvato", ((ClienteBean) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	s.eliminaUtente((ClienteBean) event.getObject());
        FacesMessage msg = new FacesMessage("Cliente eliminato", ((ClienteBean) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String aggiungiCliente(ClienteBean c){
    	String usnm=c.getUsername();  	
    	if(!s.trovaUsername(usnm)){
    		s.registraCliente(c);
    		return "elencoClienti?faces-redirect=true";
    	}
    	
    	else{
    		FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Username già in uso"));
    		return "registraClienti";
    	}
    }

}
