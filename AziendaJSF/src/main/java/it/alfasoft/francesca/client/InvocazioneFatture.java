package it.alfasoft.francesca.client;

import it.alfasoft.francesca.bean.FatturaBean;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;

public class InvocazioneFatture extends Invocazione {
	
	public InvocazioneFatture()  {
	}

	public Invocation richiestaFatturaByCode(String code){

		return baseTarget.path(code).request().buildGet();
	}
	
	public Invocation richiestaFattureAnno(String anno){

		return baseTarget.path(anno).request().buildGet();
	}

	public Invocation sendFattura(FatturaBean f){

		return baseTarget.path("/registrazione").request().buildPost(Entity.json(f));
	}
	
	public Invocation richiestaFatture(){
		return baseTarget.request().buildGet();
	}
	
	public Invocation richiestaFatturaMese(String anno,String mese){

		return baseTarget.path("/filtri").queryParam("mese", mese)
				.queryParam("anno", anno).request().buildGet();
	}

}
