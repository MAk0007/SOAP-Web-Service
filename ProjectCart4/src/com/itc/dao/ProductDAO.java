package com.itc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import org.hibernate.Session;

import org.hibernate.Transaction;

import com.hibernate.util.HibernateUtil;
import com.itc.beans.Product;


public class ProductDAO{

	
	public boolean add(Product product) {
		  Session session = HibernateUtil.openSession();
	      Transaction tx = null;
	      boolean status= false;
	      
	      try {
	         tx = session.beginTransaction();
	        session.save(product); 
	         //employeeID = (Integer) session.save(employee); 
	         tx.commit();
	         status=true;

//	      } catch (HibernateException e) {
	      } catch (javax.persistence.PersistenceException e) {
	         if (tx!=null) tx.rollback();
	         if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException)

	         {status=false;}

	         status= false;
	         
	      }
	      finally {
	         session.close(); 
	      }
	      return status;
	}


	public boolean update(Product product) {
		  Session session = HibernateUtil.openSession();
	      Transaction tx = null;
	      boolean status= false;
	      try {
	         tx = session.beginTransaction();
	         Product product1 = (Product)session.get(Product.class, product.getId());
	   
	         product1.setQuantity(product.getQuantity());
	         session.update(product1); 
	         status=true;
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         status=false;
	      } finally {
	         session.close(); 
	      }
		return status;
	}


	public boolean delete(int id) {
		Session session = HibernateUtil.openSession();
	      Transaction tx = null;
	      boolean status= false;
	      try {
	         tx = session.beginTransaction();
	         Product product = (Product)session.get(Product.class, id); 
	         session.delete(product); 
	         tx.commit();
	         status=true;
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         status=false;
	      } finally {
	         session.close(); 
	      }
	      return status;
	}


	public Product get(int id) {
		Product prod=null;
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try {
			tx = session.getTransaction();
			tx.begin();
			/*Query query = session.createQuery("from product where id='"+id+"'");

			prod = (Product)query.uniqueResult();*/
			prod = (Product)session.get(Product.class, id);
			System.out.println(prod);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}


		return prod;
	}


	public List<Product> list() {
		List<Product> list=new ArrayList<Product>();
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = session.createQuery("from Product").list();                        
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	}
	

