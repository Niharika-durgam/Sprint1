package com.gms.util;

import java.io.IOException;

import java.io.InputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gms.entity.Equipment;
import com.gms.entity.Fees;
import com.gms.entity.Membership;
import com.gms.entity.User;

public class HibernateUtil {
	
	// Singleton SessionFactory object, initialized once
	private final static SessionFactory sessionFactory=buildSessionFactory();
	
	// Method to build and return SessionFactory
	private static SessionFactory buildSessionFactory()
	{

		try {
			return new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(User.class)
					.addAnnotatedClass(Membership.class)
					.addAnnotatedClass(Fees.class)
					.addAnnotatedClass(Equipment.class)
					.buildSessionFactory();
			
		}catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	// Method to provide access to the SessionFactory
	public static SessionFactory getSessionFactory() {
		
		return sessionFactory;
	}

	// Method to open a new session
	public static Session getSession()
	{
	  return getSessionFactory().openSession();
	}


}
