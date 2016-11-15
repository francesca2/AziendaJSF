package it.alfasoft.francesca.dao;

import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.bean.DipendenteBean;
import it.alfasoft.francesca.utility.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BustaPagaDao {

	public boolean aggiungiBustaPaga(BustaPaga b)
	{
		boolean result=false;
		
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.persist(b);
		
		 result=true;
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return result;

	}
	
	public List<BustaPaga> getBustePagaDipendente(DipendenteBean d)
	{
		List<BustaPaga> lista= new ArrayList<BustaPaga>();
		long id=d.getId_Utente();
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();

		Query query= session.createQuery("from BustaPaga where Dipendente_Id_Utente=:x");
		query.setLong("x", id);
		lista=query.list();
		
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return lista;
	}
	
	public List<BustaPaga> getBustePaga()
	{
		List<BustaPaga> lista= new ArrayList<BustaPaga>();
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();

		Query query= session.createQuery("from BustaPaga");
		lista=query.list();
		
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return lista;
	}
	
	public BustaPaga getBustaPagaById(long id)
	{
		BustaPaga b=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();

		Query query=session.createQuery("from BustaPaga where Id_BustaPaga:=x1");
		query.setLong("x1", id);
		
		b=(BustaPaga) query.uniqueResult();
		 
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return b;
	}
	
	public boolean eliminaBustaPaga(BustaPaga b)
	{
		boolean result=false;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.delete(b);
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
