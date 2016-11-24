package it.alfasoft.francesca.service;

import java.util.List;

import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.bean.BustaPagaWS;
import it.alfasoft.francesca.dao.BustaPagaDao;
import it.alfasoft.francesca.dao.BustaPagaWSDao;

public class ServiziBusta {
	//Buste paga senza dipendente per i Web Service
	BustaPagaWSDao bpwsdao=new BustaPagaWSDao();
	
	public boolean addBustaPagaWS(BustaPagaWS b){
		return bpwsdao.aggiungiBustaPaga(b);
	}

	public List<BustaPagaWS> getBustePagaWS() {
		List<BustaPagaWS> lista = bpwsdao.getBustePaga();

		return lista;
	}
	
	public boolean deleteBustaWS(BustaPagaWS b){
		return bpwsdao.eliminaBustaPaga(b);
	}
	
	//Buste paga con dipendente
	BustaPagaDao bpdao = new BustaPagaDao();
	
	public boolean addBustaPaga(BustaPaga b){
		return bpdao.aggiungiBustaPaga(b);
	}

	public List<BustaPaga> getBustePaga() {
		List<BustaPaga> lista = bpdao.getBustePaga();

		return lista;
	}
	
	public boolean deleteBusta(BustaPaga b){
		return bpdao.eliminaBustaPaga(b);
	}
}
