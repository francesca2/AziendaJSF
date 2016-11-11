package it.alfasoft.francesca.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.alfasoft.francesca.bean.UtenteBean;
import it.alfasoft.francesca.utility.HibernateUtil;

public class UtenteDao {
	
	public boolean createUtente(UtenteBean u)
	{
		boolean result=false;
		
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.persist(u);
		
		 result=true;
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return result;	
	}
	
	public UtenteBean trovaUtenteConUsername(String username) {
		
		UtenteBean ubean=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from UtenteBean where username=:x1");
		query.setString("x1", username);
		
		ubean=(UtenteBean) query.uniqueResult();

		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return ubean;
		
	}
	
	public UtenteBean trovaUtenteConId(long id) {
		
		UtenteBean ubean=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from UtenteBean where id_Utente=:x1");
		query.setLong("x1", id);
		
		ubean=(UtenteBean) query.uniqueResult();

		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return ubean;
		
	}
	
	public List<UtenteBean> getTuttiUtenti() {
		List<UtenteBean> utenti= new ArrayList<UtenteBean>();
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from UtenteBean");
		utenti=query.list();
		
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return utenti;
	}
	
	
	public boolean aggiornaUtente(UtenteBean u) {
		
		boolean result=false;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.update(u);
		result=true;

		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return result;
		
	}
	
	public boolean deleteUtente(UtenteBean u)
	{
		boolean result=false;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.delete(u);
		
		result =true;
		
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return result;
	}

}
