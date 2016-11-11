package it.alfasoft.francesca.dao;


import it.alfasoft.francesca.bean.AdminBean;
import it.alfasoft.francesca.utility.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminDao {
	
	public boolean createAdmin(AdminBean a)
	{
		boolean result=false;
		
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.persist(a);
		
		 result=true;
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return result;	
	}

	public AdminBean trovaAdminConUsername(String username) {
		
		AdminBean abean=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		Query query=session.createQuery("from UtenteBean where username=:x1");
		query.setString("x1", username);
		
		abean=(AdminBean) query.uniqueResult();

		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return abean;
		
	}
	
}
