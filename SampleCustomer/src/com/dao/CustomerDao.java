package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.config.SessionUtil;
import com.pojo.Customer;

public class CustomerDao {

	/**
	 * @return List of customers from the database
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			Session session = SessionUtil.getSession();
			Query query = session.createQuery("from Customer");
			customers = query.list();
			session.close();
		} catch (Exception e) {
			System.out.println("Exception occurred in getCustomers() - " + e);
		}
		return customers;
	}

	/**
	 * @param cust
	 * @return id value after customer addition
	 */
	public int addCustomer(Customer cust) {
		int id = 0;
		try {
			Session session = SessionUtil.getSession();
			Transaction tx = session.beginTransaction();

			Customer customer = new Customer();
			customer.setName(cust.getName());
			customer.setPhone(cust.getPhone());
			customer.setEmail(cust.getEmail());
			customer.setPincode(cust.getPincode());

			session.save(customer);
			id = customer.getId();
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println("Exception occurred in addCustomer() - " + e);
		}
		return id;
	}

	/**
	 * @param id
	 * @param cust
	 * @return rowCount value after updation
	 */
	public int updateCustomer(int id, Customer cust) {
		if (id <= 0)
			return 0;
		int rowCount = 0;
		try {
			Session session = SessionUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hql = "update Customer set ";

			if (cust.getName() != null && !(cust.getName().equals("")))
				hql+= "name = '" + cust.getName() + "' ,";
			if (cust.getEmail() != null && !(cust.getEmail().equals("")))
				hql+= "email = '" + cust.getEmail() + "' ,";
			if (cust.getPhone() != null)
				hql+="phone = '" + cust.getPhone() + "',";
			if (cust.getPincode() != null && !(cust.getPincode().equals("")))
				hql+="pincode ='" + cust.getPincode() + "'";
			if(hql.endsWith(","))
				hql = hql.substring(0,hql.length() -1);
			hql+= " where id = " + id;
			
			Query query = session.createQuery(hql);
			rowCount = query.executeUpdate();

			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println("Exception occurred in updateCustomer() - " + e);
		}
		return rowCount;
	}

	/**
	 * @param id
	 * @return rowcount value after deletion
	 */
	public int deleteCustomer(int id) {
		int rowCount = 0;
		try {
			Session session = SessionUtil.getSession();
			Transaction tx = session.beginTransaction();
			String hql = "delete from Customer where id = :id";
			Query query = session.createQuery(hql);
			query.setInteger("id", id);
			rowCount = query.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println("Exception occurred in deleteCustomer() - " + e);
		}
		return rowCount;
	}

}
