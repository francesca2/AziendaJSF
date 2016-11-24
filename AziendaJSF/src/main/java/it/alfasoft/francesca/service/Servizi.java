package it.alfasoft.francesca.service;

import java.util.List;

import it.alfasoft.francesca.bean.AdminBean;
import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.bean.ClienteBean;
import it.alfasoft.francesca.bean.DipendenteBean;
import it.alfasoft.francesca.bean.Rubrica;
import it.alfasoft.francesca.bean.UtenteBean;
import it.alfasoft.francesca.bean.Voce;
import it.alfasoft.francesca.dao.AdminDao;
import it.alfasoft.francesca.dao.BustaPagaDao;
import it.alfasoft.francesca.dao.ClienteDao;
import it.alfasoft.francesca.dao.DipendenteDao;
import it.alfasoft.francesca.dao.RubricaDao;
import it.alfasoft.francesca.dao.UtenteDao;
import it.alfasoft.francesca.dao.VoceDao;
import it.alfasoft.francesca.utility.PasswordCodification;

public class Servizi {

	UtenteDao udao=new UtenteDao();
	AdminDao adao= new AdminDao();
	ClienteDao cdao=new ClienteDao();
	DipendenteDao ddao=new DipendenteDao();
	RubricaDao rdao= new RubricaDao();
	VoceDao vdao= new VoceDao();
	BustaPagaDao bdao=new BustaPagaDao();

	//metodi per registrare gli utenti
	public boolean registraUtente(UtenteBean u) {
		boolean result=false;
		result=udao.createUtente(u);

		return result;
	}
	
	public boolean registraAdmin(AdminBean a) {
		boolean result=false;
		a.setPassword(convertiPass(a.getPassword()));
		result=adao.createAdmin(a);

		return result;
	}

	public boolean registraDipendente(DipendenteBean d) {
		boolean result=false;
		d.setPassword(convertiPass(d.getPassword()));
		result=ddao.createDipendente(d);

		return result;
	}
	//metodi per modificare i dati degli utenti
	
	public boolean saveDipendente(DipendenteBean d){
		return ddao.aggiornaDipendente(d);
	}
	
	
	//metodo per avere la lista dei dipendenti
	
	public List<DipendenteBean> getDipendenti(){
		return ddao.getTuttiDipendenti();
	}
	
	//metodo per codificare la password
	public String convertiPass(String pass){

		return PasswordCodification.codificatePass(pass);	
	}

	//metodo per trovare un utente usando il suo username
	public UtenteBean getUtente(String username) {
		UtenteBean u =udao.trovaUtenteConUsername(username);
		return u;
	}
	
	public AdminBean getAdmin(String username) {
		AdminBean u =adao.trovaAdminConUsername(username);
		return u;
	}
	
	public DipendenteBean getDipendente(String username) {
		DipendenteBean u =ddao.trovaDipendenteConUsername(username);
		return u;
	}
	
	public boolean trovaUsername(String username) {
		boolean result =false;
		UtenteBean u =udao.trovaUtenteConUsername(username);
		if(u!=null) 
		{
			result=true;
		}
		return result;
	}
	
	//metodo per trovare un utente usando il suo id
	public UtenteBean getUtenteById(long id) {
		UtenteBean u =udao.trovaUtenteConId(id);
		return u;
	}
	
	public DipendenteBean getDipendenteById(long id) {
		DipendenteBean d =ddao.trovaDipendenteConId(id);
		return d;
	}
	
	
	//metodo per cancellare un utente
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
	
	//metodo per cancellare un utente con id
	public boolean eliminaUtenteById(long id) {
		
		UtenteBean u = getUtenteById(id);
		
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

	//metodo per creare una busta paga
	public boolean salvaBustaPaga(BustaPaga b) {
		return bdao.aggiungiBustaPaga(b);

	}
	
	//metodo per prendere tutte le buste paga di un dipendente
	public List<BustaPaga> getBustePagaDipendente(DipendenteBean d) {
		List<BustaPaga> lista = bdao.getBustePagaDipendente(d);

		return lista;
	}
	//metodo per vedere tutte le buste paga
	public List<BustaPaga> getBustePaga() {
		List<BustaPaga> lista = bdao.getBustePaga();

		return lista;
	}
	//metodo per eliminare una busta paga
	public boolean eliminaBustaPaga(long id)
	{
		BustaPaga b=bdao.getBustaPagaById(id);
		return bdao.eliminaBustaPaga(b);
	}
}
