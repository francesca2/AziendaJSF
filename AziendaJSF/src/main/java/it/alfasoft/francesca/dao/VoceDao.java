package it.alfasoft.francesca.dao;


import it.alfasoft.francesca.bean.Rubrica;
import it.alfasoft.francesca.bean.Voce;
import it.alfasoft.francesca.utility.HibernateUtil;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class VoceDao {
	
	public boolean aggiungiVoce(Voce v)
	{
		boolean b=false;
		
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.persist(v);
		
		 b=true;
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return b;

	}
	
	public Voce trovaVoce(String nome, String cognome, long idRubrica)
	{
		Voce v=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();

		Query query=session.createQuery("from Voce where Rubrica_id_Rubrica=:x1 and nomeVoce=:x2 and cognomeVoce=:x3");
		query.setLong("x1", idRubrica);
		query.setString("x2", nome);
		query.setString("x3", cognome);
		
		v=(Voce) query.uniqueResult();
		 
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return v;
	}
	
	public Voce trovaVoceConId(long id)
	{
		Voce v=null;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();

		Query query=session.createQuery("from Voce where Id_Voce=:x1");
		query.setLong("x1", id);

		v=(Voce) query.uniqueResult();
		 
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return v;
	}
	
	public List<Voce> getVociRubrica(Rubrica r)
	{
		List<Voce> voci= new ArrayList<Voce>();
		long id=r.getId_Rubrica();
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();

		Query query= session.createQuery("from Voce where Rubrica_Id_Rubrica=:x");
		query.setLong("x", id);
		 voci=query.list();
		
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		return voci;
	}
	
	public boolean aggiornaVoce(Voce v)
	{
		boolean result=false;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.update(v);
		result=true;
		 
		 tx.commit();
		}catch(Exception ex){
			tx.rollback();
		}finally{
			session.close();
		}
		
		return result;
	}
	

	public boolean eliminaVoce(Voce v)
	{
		boolean result=false;
		Session session =HibernateUtil.openSession();
		Transaction tx=null;

		try{
		tx=session.getTransaction();
		tx.begin();
		
		session.delete(v);
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
