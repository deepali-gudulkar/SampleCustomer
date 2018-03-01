package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

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
	 * @return boolean value after addition
	 */
	public boolean addCustomer(Customer cust) {
		boolean bAdd = false;
		try {
			Session session = SessionUtil.getSession();
			Transaction tx = session.beginTransaction();

			Customer customer = new Customer();
			customer.setName(cust.getName());
			customer.setPhone(cust.getPhone());
			customer.setEmail(cust.getEmail());
			customer.setPincode(cust.getPincode());
			//customer.setStatus(cust.getStatus());

			session.save(customer);
			tx.commit();
			session.close();
			bAdd = true;
		} catch (Exception e) {
			System.out.println("Exception occurred in addCustomer() - " + e);
			if(e instanceof ConstraintViolationException) {
				throw new ConstraintViolationException(((ConstraintViolationException) e).getConstraintViolations());
			}
		}
		return bAdd;
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
			String hql = "update Customer set name = :name, email =:email, pincode =:pincode, phone =:phone where id = :id";

			Query query = session.createQuery(hql);
			query.setInteger("id", id);
			if(cust.getName() != null && !(cust.getName().equals("")))
				query.setString("name", cust.getName());
			if(cust.getEmail() != null && !(cust.getEmail().equals("")))
				query.setString("email", cust.getEmail());
			if(cust.getPhone() != null)
				query.setLong("phone", cust.getPhone());
			if(cust.getPincode() != null && !(cust.getPincode().equals("")))
				query.setString("pincode", cust.getPincode());
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
