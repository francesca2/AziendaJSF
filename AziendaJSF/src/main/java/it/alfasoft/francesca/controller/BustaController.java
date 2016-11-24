package it.alfasoft.francesca.controller;

import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.bean.DipendenteBean;
import it.alfasoft.francesca.service.Servizi;
import it.alfasoft.francesca.service.ServiziBusta;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name="bustaController", eager=true)
@ViewScoped
public class BustaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BustaPaga> listaBuste;
	private Map<String,Long> mappaDipendenti= new HashMap<String,Long>();
	private long idDip;
	
	private ServiziBusta sb;
	private Servizi s;

	public BustaController() {
		sb=new ServiziBusta();
		s=new Servizi();
		listaBuste=sb.getBustePaga();
	}

	public List<BustaPaga> getListaBuste() {
		return listaBuste;
	}

	public void setListaBuste(List<BustaPaga> listaBuste) {
		this.listaBuste = listaBuste;
	}

	public ServiziBusta getSb() {
		return sb;
	}

	public void setSb(ServiziBusta sb) {
		this.sb = sb;
	}

	public long getIdDip() {
		return idDip;
	}

	public void setIdDip(long idDip) {
		this.idDip = idDip;
	}

	public Servizi getS() {
		return s;
	}

	public void setS(Servizi s) {
		this.s = s;
	}
	
	public Map<String, Long> getMappaDipendenti() {
		return mappaDipendenti;
	}

	public void setMappaDipendenti(Map<String, Long> mappaDipendenti) {
		this.mappaDipendenti = mappaDipendenti;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
//	public void onRowEdit(RowEditEvent event) {
//		s.saveDipendente((BustaPaga) event.getObject());
//        FacesMessage msg = new FacesMessage("Dipendente salvato", ((DipendenteBean) event.getObject()).getNome());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
     
    public void onRowCancel(RowEditEvent event) {
    	sb.deleteBusta((BustaPaga) event.getObject());
        FacesMessage msg = new FacesMessage("Busta Paga eliminata", ((BustaPaga) event.getObject()).getData().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public Map<String,Long> updateMappaDipendenti(){
    	for(DipendenteBean d : s.getDipendenti())
    	{
    	mappaDipendenti.put(d.getNome()+" "+d.getCognome(), d.getId_Utente());
    	}
    	return mappaDipendenti;
    }
    
    public String registraBustaPaga(BustaPaga b){
    	DipendenteBean d=(DipendenteBean) s.getUtenteById(idDip);
    	b.setDipendente(d);
    	s.salvaBustaPaga(b);
    	return "registraBustePaga?faces-reidrect-true";
    }

	
}
