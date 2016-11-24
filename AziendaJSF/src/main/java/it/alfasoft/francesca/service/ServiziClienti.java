package it.alfasoft.francesca.service;

import java.util.List;

import it.alfasoft.francesca.bean.ClienteBean;
import it.alfasoft.francesca.bean.Rubrica;
import it.alfasoft.francesca.bean.UtenteBean;
import it.alfasoft.francesca.dao.ClienteDao;
import it.alfasoft.francesca.dao.DipendenteDao;
import it.alfasoft.francesca.dao.RubricaDao;
import it.alfasoft.francesca.dao.UtenteDao;
import it.alfasoft.francesca.dao.VoceDao;
import it.alfasoft.francesca.utility.PasswordCodification;

public class ServiziClienti {
	
	UtenteDao udao=new UtenteDao();
	ClienteDao cdao=new ClienteDao();
	RubricaDao rdao= new RubricaDao();
	
	public boolean registraCliente(ClienteBean c) {
		boolean result=false;
		c.setPassword(convertiPass(c.getPassword()));
		result=cdao.createCliente(c);

		return result;
	}
	
	//metodo per codificare la password
	public String convertiPass(String pass){

		return PasswordCodification.codificatePass(pass);	
	}
	
	//metodi per modificare i dati di un cliente
	public boolean saveCliente(ClienteBean c){
		return cdao.aggiornaCliente(c);
	}
	
	//metodo per avere la lista dei clienti
	public List<ClienteBean> getClienti(){
		return cdao.getTuttiClienti();
	}
	
	//metodo per prendere un cliente con username
	public ClienteBean getCliente(String username) {
		ClienteBean c =cdao.trovaClienteConUsername(username);
		return c;
	}
	
	//metodo per trovare un utente usando il suo id
	public ClienteBean getClienteById(long id) {
		ClienteBean c =cdao.trovaClienteConId(id);
		return c;
	}
	
	
	//metodo per eliminare un cliente
	public boolean eliminaUtente(UtenteBean u) {
		if(u.getRuolo()=='a')
		{
			return false;
		}
		else {
			Rubrica r=rdao.trovaRubricaConNome(u.getUsername());
			if(r!=null) {
			rdao.deleteRubrica(r);
			}
			return udao.deleteUtente(u);
		}
	}

}
