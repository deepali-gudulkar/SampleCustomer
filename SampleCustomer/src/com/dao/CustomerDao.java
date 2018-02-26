package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.config.SessionUtil;
import com.pojo.Customer;

public class CustomerDao {

	public List getCustomers() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Customer");
		List<Customer> customers = query.list();
		session.close();
		return customers;
	}

	public boolean addCustomer(Customer cust) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		Customer customer = new Customer();
		customer.setName(cust.getName());
		customer.setPhone(cust.getPhone());
		customer.setEmail(cust.getEmail());
		customer.setPincode(cust.getPincode());

		// TODO - callback listeners to be added
		customer.setStatus(cust.getStatus());

		session.save(customer);
		tx.commit();
		session.close();
		return true;

	}

	public int updateCustomer(int id, Customer cust) {
		if (id <= 0)
			return 0;
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "update customer set name = :name, email =:email, pincode =:pincode, phone =:phone where id = :id";

		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		query.setString("name", cust.getName());
		query.setString("email", cust.getEmail());
		query.setLong("phone", cust.getPhone());
		int rowCount = query.executeUpdate();

		tx.commit();
		session.close();
		return rowCount;
	}

	public int deleteCustomer(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Customer where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		return rowCount;
	}

}
