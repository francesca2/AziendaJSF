package it.alfasoft.francesca.service;

import java.util.List;

import it.alfasoft.francesca.bean.AdminBean;
import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.bean.ClienteBean;
import it.alfasoft.francesca.bean.DipendenteBean;
import it.alfasoft.francesca.bean.UtenteBean;
import it.alfasoft.francesca.dao.AdminDao;
import it.alfasoft.francesca.dao.BustaPagaDao;
import it.alfasoft.francesca.dao.ClienteDao;
import it.alfasoft.francesca.dao.DipendenteDao;
import it.alfasoft.francesca.dao.RubricaDao;
import it.alfasoft.francesca.dao.UtenteDao;
import it.alfasoft.francesca.dao.VoceDao;
import it.alfasoft.francesca.model.Rubrica;
import it.alfasoft.francesca.model.Voce;
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

	public boolean registraCliente(ClienteBean c) {
		boolean result=false;
		result=cdao.createCliente(c);

		return result;
	}

	public boolean registraDipendente(DipendenteBean d) {
		boolean result=false;
		result=ddao.createDipendente(d);

		return result;
	}
	//metodi per modificare i dati degli utenti
	public boolean saveCliente(ClienteBean c){
		return cdao.aggiornaCliente(c);
	}
	
	public boolean saveDipendente(DipendenteBean d){
		return ddao.aggiornaDipendente(d);
	}

	//metodo per avere la lista dei clienti
	public List<ClienteBean> getClienti(){
		return cdao.getTuttiClienti();
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
	
	public ClienteBean getCliente(String username) {
		ClienteBean u =cdao.trovaClienteConUsername(username);
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
	
	public ClienteBean getClienteById(long id) {
		ClienteBean c =cdao.trovaClienteConId(id);
		return c;
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
