package it.alfasoft.francesca.controller;

import it.alfasoft.francesca.bean.Voce;
import it.alfasoft.francesca.service.ServiziRubrica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="rubricaController", eager=true)
@ViewScoped
public class RubricaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Voce> listaVoci;
	private long idUt;
	
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

	public long getIdUt() {
		return idUt;
	}

	public void setIdUt(long idUt) {
		this.idUt = idUt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<Voce> findRubrica(long idUt){
	
		return null;
	}
	

}
