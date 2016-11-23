package it.alfasoft.francesca.client;

import it.alfasoft.francesca.bean.FatturaBean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

public abstract class Invocazione {

	protected Client cliente= ClientBuilder.newClient();
	protected WebTarget baseTarget= cliente.target("http://localhost:5080/AziendaIBM1/alfasoft/fatture");
	
	abstract Invocation richiestaFatturaByCode(String code);
	
	abstract Invocation sendFattura(FatturaBean f);
	
	abstract Invocation richiestaFatture();
	
	abstract Invocation  richiestaFatturaMese(String anno,String mese);
	
}
