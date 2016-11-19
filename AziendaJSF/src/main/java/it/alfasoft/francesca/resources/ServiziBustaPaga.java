package it.alfasoft.francesca.resources;

import java.util.ArrayList;
import java.util.List;

import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.dao.BustaPagaDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("BustePaga")
@Produces(MediaType.APPLICATION_JSON)
public class ServiziBustaPaga {
	private BustaPagaDao bpdao= new BustaPagaDao();
	
	@GET
	public List<BustaPaga> getbuste(){
		
		return new ArrayList<BustaPaga>(bpdao.getBustePaga());
}
	

}
