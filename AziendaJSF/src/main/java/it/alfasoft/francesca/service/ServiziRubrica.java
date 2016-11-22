package it.alfasoft.francesca.service;

import java.util.List;

import it.alfasoft.francesca.dao.RubricaDao;
import it.alfasoft.francesca.dao.VoceDao;
import it.alfasoft.francesca.model.Rubrica;
import it.alfasoft.francesca.model.Voce;

public class ServiziRubrica {
	RubricaDao rdao= new RubricaDao();
	VoceDao vdao= new VoceDao();
	
	//metodo per creare una rubrica
	public boolean registraRubrica(String username)
	{
		Rubrica r= new Rubrica(username);
		boolean b= rdao.creaRubrica(r);
		return b;
	}
	
	//metodo per aggiungere una voce in rubrica
	public boolean registraVoce(Rubrica r,Voce v) {
		boolean result= false;
		v.setRubrica(r);
		r.addVoce(v);
		boolean b=vdao.aggiungiVoce(v);
		rdao.aggiornaRubrica(r);

		if(b==true)
		{
			result =true;
		}

		return result;

	}
	
	//Metodo per trovare la rubrica di un utente a seconda del suo username
	public Rubrica trovaRubrica(String username) {

		Rubrica r= rdao.trovaRubricaConNome(username);

		return r;
	}
	
	//metodo per trovare una voce
	public Voce getVoceById(long id){
		return vdao.trovaVoceConId(id);
	}

	//metodo per prendere tutte le voci di rubrica
	public List<Voce> getVoci(Rubrica r) {
		List<Voce> lista = vdao.getVociRubrica(r);

		return lista;
	}


	//metodo per eliminare una voce
	public boolean eliminaVoce(Rubrica r, long id)
	{
		Voce v=vdao.trovaVoceConId(id);
		boolean result=vdao.eliminaVoce(v);
		return result;
	}
	//metodo per aggiornare la rubrica
	public boolean saveRubrica(Rubrica r){
		return rdao.aggiornaRubrica(r);
	}
	
	//metodo per aggiornare una voce
	public boolean saveVoce(Voce v){
		return vdao.aggiornaVoce(v);
	}

}
