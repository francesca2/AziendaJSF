package it.alfasoft.francesca.client;

import it.alfasoft.francesca.bean.FatturaBean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

public class Invocazione {

	private Client cliente= ClientBuilder.newClient();
	private WebTarget baseTarget= cliente.target("http://localhost:8085/AziendaIBM");
	public Invocazione() {
	}

	public Invocation richiestaFatturaByCode(String code){

		return baseTarget.path(code).request().buildGet();
	}

	public Invocation sendFattura(FatturaBean f){

		return baseTarget.path("").request().buildPost(Entity.json(f));
	}
	
	public Invocation richiestaFatture(){
		return baseTarget.path("").request().buildGet();
	}
}
