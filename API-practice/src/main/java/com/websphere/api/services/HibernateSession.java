package com.websphere.api.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.websphere.api.models.Account;
import com.websphere.api.models.AccountType;
import com.websphere.api.models.Operation;
import com.websphere.api.models.OperationType;
import com.websphere.api.models.User;
import com.websphere.api.models.UserProfile;

public class HibernateSession {
	
	public static SessionFactory factory = configurarSession();
	
	public static SessionFactory configurarSession() {
		SessionFactory fact;
		try {
	         fact = new Configuration().
	                   configure().
	                   addAnnotatedClass(Account.class).
	                   addAnnotatedClass(AccountType.class).
	                   addAnnotatedClass(Operation.class).
	                   addAnnotatedClass(OperationType.class).
	                   addAnnotatedClass(User.class).
	                   addAnnotatedClass(UserProfile.class).
	                   buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		return fact;
	}
}
