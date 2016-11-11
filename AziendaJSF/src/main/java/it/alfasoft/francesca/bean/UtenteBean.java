package it.alfasoft.francesca.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@ManagedBean(name="utente", eager=true)
public class UtenteBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_Utente;
	
	protected String nome;
	protected String cognome;
	protected String username;
	protected String password;
	protected char ruolo;
	
	public UtenteBean() {
	}
	
	public UtenteBean(String nome, String cognome,
			String username, String password, char ruolo) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
	}

	public long getId_Utente() {
		return id_Utente;
	}

	public void setId_Utente(long id_Utente) {
		this.id_Utente = id_Utente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	public char getRuolo() {
		return ruolo;
	}

	public void setRuolo(char ruolo) {
		this.ruolo = ruolo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
