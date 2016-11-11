package it.alfasoft.francesca.dao;

import java.util.ArrayList;
import java.util.List;

import it.alfasoft.francesca.bean.ClienteBean;
import it.alfasoft.francesca.utility.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClienteDao {
	
	public boolean createCliente(ClienteBean c)
	{
		boolean result=false;
		
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.persist(c);
		
		 result=true;
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return result;	
	}
	
	public ClienteBean trovaClienteConUsername(String username) {
		
		ClienteBean cbean=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from UtenteBean where username=:x1");
		query.setString("x1", username);
		
		cbean=(ClienteBean) query.uniqueResult();

		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return cbean;
		
	}

	public ClienteBean trovaClienteConId(long id) {
		
		ClienteBean cbean=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from ClienteBean where id_Utente=:x1");
		query.setLong("x1", id);
		
		cbean=(ClienteBean) query.uniqueResult();

		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return cbean;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ClienteBean> getTuttiClienti() {
		List<ClienteBean> clienti= new ArrayList<ClienteBean>();
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from ClienteBean");
		clienti=query.list();
		
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return clienti;
	}
	
	public boolean aggiornaCliente(ClienteBean c) {
		
		boolean result=false;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.update(c);
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
