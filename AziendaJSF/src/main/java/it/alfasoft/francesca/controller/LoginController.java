package it.alfasoft.francesca.controller;

import it.alfasoft.francesca.bean.UtenteBean;
import it.alfasoft.francesca.service.Servizi;
import it.alfasoft.francesca.utility.PasswordCodification;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="loginController")
@SessionScoped
public class LoginController {
	
	private String username;
	private String password;
	private boolean loggato;
	private char ruolo;
	private long id;
	private Servizi s;
	
	public LoginController(){
		loggato = false;
		s=new Servizi();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isLoggato() {
		return loggato;
	}
	
	public void setLoggato(boolean loggato) {
		this.loggato = loggato;
	}

	public char getRuolo() {
		return ruolo;
	}

	public void setRuolo(char ruolo) {
		this.ruolo = ruolo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String doLogin(){
		UtenteBean u=s.getUtente(this.username);
		String page = "login";
		if(u==null || !u.getPassword().equals(PasswordCodification.codificatePass(this.password))){
			
			//se non � verificato visualizzo a video un messaggio di errore e rimando a login page
			FacesMessage msg = new FacesMessage("Username o Password ERRATI!");
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "login";
		}
		
		//se � verificato allora posso mandare ad home page
		this.loggato = true;
		setRuolo(u.getRuolo());
		setId(u.getId_Utente());
		switch(ruolo) {
		case 'a' :
			page= "/portali/amministratore/homeAdmin?faces-redirect=true";
		break;
		case 'c' :
			page= "/portali/cliente/homeClient?faces-redirect=true";
		break;
		
		case 'd' :
			page= "/portali/dipendente/homeDipendente?faces-redirect=true";
		break;
		}
		return page;
	}
	
}
