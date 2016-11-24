package it.alfasoft.francesca.bean;

import it.alfasoft.francesca.utility.IsValid;

import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@ManagedBean(name="impiegato", eager=true)
public class DipendenteBean extends UtenteBean implements IsValid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String posizione;
	private double stipendio;
	
	@OneToMany(mappedBy="dipendente",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<BustaPaga> listaBustePaga = new HashSet<BustaPaga>();
	
	public DipendenteBean() {

	}
	
	
	public DipendenteBean(String nome, String cognome,
			String username, String password, char ruolo,String posizione, double stipendio) {
		super(nome,cognome,username,password,ruolo);
		this.posizione = posizione;
		this.stipendio = stipendio;
	}


	public String getPosizione() {
		return posizione;
	}


	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}


	public double getStipendio() {
		return stipendio;
	}


	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}


	public Set<BustaPaga> getListaBustePaga() {
		return listaBustePaga;
	}


	public void setListaBustePaga(Set<BustaPaga> listaBustePaga) {
		this.listaBustePaga = listaBustePaga;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isValid() {

		boolean result=false;
		
		if(!nome.isEmpty() && nome!=null && 
				!cognome.isEmpty() && cognome!=null &&
				!username.isEmpty() && username!=null &&
				!password.isEmpty() && password!=null &&
				ruolo=='d' &&
				!posizione.isEmpty() && posizione!=null) {
			result=true;
		}
		
		return result;
	}

}
