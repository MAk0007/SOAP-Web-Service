package com.itc.ws;

import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.util.HibernateUtil;
import com.itc.beans.Product;

@WebService(endpointInterface = "com.itc.ws.QuantityCheckWS")
public class QuantityCheckWSImpl implements QuantityCheckWS {
	public boolean getQuantity(Product product){
		Product prod=null;
		boolean status=false;
		Session session=HibernateUtil.openSession();
		Transaction tx=null;
		try {
			System.out.println("inside check");
			tx = session.getTransaction();
			tx.begin();
			/*Query query = session.createQuery("from product where id='"+id+"'");

			prod = (Product)query.uniqueResult();*/
			prod = (Product)session.get(Product.class, product.getId());
			
			if(prod.getQuantity()>=product.getQuantity()){
				status=true;
				
			}else{
				status= false;
			}
			
			
//			System.out.println(prod);
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			tx.commit();
			session.close();
			
		}

		return status;
	
	}
}
