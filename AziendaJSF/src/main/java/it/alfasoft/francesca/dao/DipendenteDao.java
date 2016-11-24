package it.alfasoft.francesca.dao;

import java.util.ArrayList;
import java.util.List;

import it.alfasoft.francesca.bean.DipendenteBean;
import it.alfasoft.francesca.utility.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DipendenteDao {

	public boolean createDipendente(DipendenteBean d)
	{
		boolean result=false;
		
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.persist(d);
		
		 result=true;
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return result;	
	}
	
	public DipendenteBean trovaDipendenteConUsername(String username) {
		
		DipendenteBean dbean=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from UtenteBean where username=:x1");
		query.setString("x1", username);
		
		dbean=(DipendenteBean) query.uniqueResult();

		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return dbean;
		
	}
	
	public DipendenteBean trovaDipendenteConId(long id) {
		
		DipendenteBean dbean=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from DipendenteBean where id_Utente=:x1");
		query.setLong("x1", id);
		
		dbean=(DipendenteBean) query.uniqueResult();

		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return dbean;
		
	}

	
	public List<DipendenteBean> getTuttiDipendenti() {
		List<DipendenteBean> dipendenti= new ArrayList<DipendenteBean>();
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from DipendenteBean");
		dipendenti=query.list();
		
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return dipendenti;
	}
	
	public boolean aggiornaDipendente(DipendenteBean d) {
		
		boolean result=false;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.update(d);
		result=true;

		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return result;
		
	}
	
}
