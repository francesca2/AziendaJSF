package it.alfasoft.francesca.controller;

import it.alfasoft.francesca.bean.FatturaBean;
import it.alfasoft.francesca.bean.Rubrica;
import it.alfasoft.francesca.bean.Voce;
import it.alfasoft.francesca.service.ServiziRubrica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name="rubricaController", eager=true)
@ViewScoped
public class RubricaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Voce> listaVoci;
	
	private ServiziRubrica sr;

	public RubricaController() {
		sr=new ServiziRubrica();
		listaVoci=new ArrayList<Voce>();
	}

	public List<Voce> getListaVoci() {
		return listaVoci;
	}

	public void setListaVoci(List<Voce> listaVoci) {
		this.listaVoci = listaVoci;
	}

	public ServiziRubrica getSr() {
		return sr;
	}

	public void setSr(ServiziRubrica sr) {
		this.sr = sr;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<Voce> findRubrica(long id){
		Rubrica r=sr.trovaRubrica(id);
		this.listaVoci=sr.getVoci(r);
		return listaVoci;
	}
	
	public void onRowEdit(RowEditEvent event) {
		//invocazione.putFattura((FatturaBean) event.getObject()).invoke();
        FacesMessage msg = new FacesMessage("Fattura salvata", ((Voce) event.getObject()).getNomeVoce());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	//invocazione.deleteFattura((FatturaBean) event.getObject()).invoke();
        FacesMessage msg = new FacesMessage("Fattura eliminata", ((Voce) event.getObject()).getNomeVoce());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
