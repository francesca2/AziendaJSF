package it.alfasoft.francesca.dao;

import it.alfasoft.francesca.bean.BustaPaga;
import it.alfasoft.francesca.bean.BustaPagaWS;
import it.alfasoft.francesca.utility.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BustaPagaWSDao {
	public boolean aggiungiBustaPaga(BustaPagaWS b)
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
	
	public List<BustaPagaWS> getBustePaga()
	{
		List<BustaPagaWS> lista= new ArrayList<BustaPagaWS>();
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();

		Query query= session.createQuery("from BustaPagaWS");
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

		Query query=session.createQuery("from BustaPagaWS where Id_BustaPagaWS:=x1");
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
	
	public boolean eliminaBustaPaga(BustaPagaWS b)
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
