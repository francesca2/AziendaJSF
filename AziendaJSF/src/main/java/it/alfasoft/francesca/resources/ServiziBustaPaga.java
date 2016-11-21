package it.alfasoft.francesca.resources;

import java.util.ArrayList;
import java.util.List;

import it.alfasoft.francesca.bean.BustaPagaWS;
import it.alfasoft.francesca.service.ServiziBusta;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("BustePaga")
@Produces(MediaType.APPLICATION_JSON)
public class ServiziBustaPaga {
	private ServiziBusta s=new ServiziBusta();
	
	@GET
	public List<BustaPagaWS> getbuste(){
		
		return new ArrayList<BustaPagaWS>(s.getBustePaga());
}
	

}
