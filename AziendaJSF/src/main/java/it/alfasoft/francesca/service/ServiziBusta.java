package it.alfasoft.francesca.service;

import java.util.List;

import it.alfasoft.francesca.bean.BustaPagaWS;
import it.alfasoft.francesca.dao.BustaPagaWSDao;

public class ServiziBusta {
	BustaPagaWSDao bpdao=new BustaPagaWSDao();
	
	public boolean addBustaPaga(BustaPagaWS b){
		return bpdao.aggiungiBustaPaga(b);
	}

	public List<BustaPagaWS> getBustePaga() {
		List<BustaPagaWS> lista = bpdao.getBustePaga();

		return lista;
	}
	
	public boolean deleteBusta(BustaPagaWS b){
		return bpdao.eliminaBustaPaga(b);
	}
}
