package com.config;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author Deepali
 *	Class that manages Hibernate sessions
 */
@SuppressWarnings("deprecation")
public class SessionUtil {

	private static SessionUtil instance = new SessionUtil();
	private SessionFactory sessionFactory;

	public static SessionUtil getInstance() {
		return instance;
	}

	/**
	 * Private construtor that configures hibernate session factory
	 */
	private SessionUtil() {
		Configuration configuration = new Configuration();  
	    configuration.configure();  

	    Properties properties = configuration.getProperties();

	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
	        applySettings(properties).buildServiceRegistry();          
	    sessionFactory = configuration.
	        buildSessionFactory(serviceRegistry);  
		
	}
	
	/**
	 * @return hibernate session from the session factory
	 */
	public static Session getSession() {
		Session session = getInstance().sessionFactory.openSession();
		return session;
	}
}