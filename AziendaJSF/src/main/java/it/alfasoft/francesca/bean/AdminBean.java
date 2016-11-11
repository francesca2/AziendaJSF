package it.alfasoft.francesca.bean;

import it.alfasoft.francesca.utility.IsValid;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

@Entity
@ManagedBean(name="admin", eager=true)
public class AdminBean extends UtenteBean implements IsValid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String livelloAccesso;

	public AdminBean() {
	}
	
	public AdminBean(String nome, String cognome,
			String username, String password, char ruolo, String livelloAccesso) {
		super(nome,cognome,username,password,ruolo);
		this.livelloAccesso = livelloAccesso;
	}

	public String getLivelloAccesso() {
		return livelloAccesso;
	}

	public void setLivelloAccesso(String livelloAccesso) {
		this.livelloAccesso = livelloAccesso;
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
				ruolo=='a' &&
				!livelloAccesso.isEmpty() && livelloAccesso!=null) {
			result=true;
		}
		
		return result;
	}

}
