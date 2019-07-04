package com.banking.api.services;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.api.encryption.BCrypt.Encryption;
import com.banking.api.models.User;

@Service
@Transactional
public class UserService {
	
	public static SessionFactory factory = HibernateSession.factory;
	
	public static class UserServices {
		
		// Gets a single user from the database using his ID.
		
		public static User getUser(int userID) {
			
			User user = new User();
			Session session = factory.openSession();
		    Transaction tx = null;
		     
		    try { 
		    	tx = session.beginTransaction();
		         
		    	// Search for the user through his ID.
		    	String hql = "FROM User WHERE ID = :ID";
		    	@SuppressWarnings("unchecked")
		    	Query<User> query = session.createQuery(hql);
		    	query.setParameter("ID", userID);
		         
		    	// Store the result in the object
		    	user = query.getSingleResult();
		    	tx.commit();
		    	
		    } catch (HibernateException e) {
		    	// In case of error during transaction, do a rollback.
		    	  if (tx != null) {
		    		  tx.rollback();
		    	  }
		      e.printStackTrace(); 
		    } finally {
		    	// Finally, shut down the session.
		    	session.close(); 
		    }
		    // If no user has been found, return null.
		    if(user.getUserName().isEmpty()) {
		    	return null;
		    } else {
			    // Return the found user.
			    return user;
		    }
		}
		
		// Gets a single user from the database using his username.
		
				public static User getUser(String userName) {
					
					User user = new User();
					Session session = factory.openSession();
				    Transaction tx = null;
				     
				    try { 
				    	tx = session.beginTransaction();
				         
				    	// Search for the user through his ID.
				    	String hql = "FROM User WHERE UPPER(userName) = :userName";
				    	@SuppressWarnings("unchecked")
				    	Query<User> query = session.createQuery(hql);
				    	query.setParameter("userName", userName.trim().toUpperCase());
				         
				    	// Store the result in the object
				    	user = query.getSingleResult();
				    	tx.commit();
				    	
				    } catch (HibernateException e) {
				    	// In case of error during transaction, do a rollback.
				    	  if (tx != null) {
				    		  tx.rollback();
				    	  }
				      e.printStackTrace(); 
				    } finally {
				    	// Finally, shut down the session.
				    	session.close(); 
				    }
				    // If no user has been found, return null.
				    if(user.getUserName().isEmpty()) {
				    	return null;
				    } else {
					    // Return the found user.
					    return user;
				    }
				}
		
		// Create a new user
		
		public static User createUser(User user) {
			
			Session session = factory.openSession();
		    Transaction tx = null;
		    User foundUser = new User();
		    
		      try {
		    	  tx = session.beginTransaction();
		    	  // Encrypt the user's password
		    	  String encryptedPassword = Encryption.encryptPass(user.getPassword());
		    	  user.setPassword(encryptedPassword);
		    	  // Save the user
		    	  session.save(user); 
		    	  
		    	  // Get the newest user in the database and store it in an object.
		    	  String hql = "FROM User order by ID desc";
		    	  @SuppressWarnings("unchecked")
		    	  Query<User> query = session.createQuery(hql).setMaxResults(1);
		    	  List<User> resultList = query.list();
		    	  
		    	  for (Iterator<User> iterator = resultList.iterator(); iterator.hasNext();){
		    		  foundUser = (User) iterator.next(); 
			      }
		    	  // After all processes are done, commit.
		    	  tx.commit();
		    	  
		      } catch (HibernateException e) {
		    	  // If any error happens during the transaction, do a rollback.
		         if (tx != null) {
		        	 tx.rollback();
		         }
		         e.printStackTrace(); 
		      } finally {
		    	  // Finally, shut down the session
		         session.close(); 
		      }
		      return foundUser;
		}
		
		// Edit an existing user
		
		public static User editUser(int userID, User user) {
			
			Session session = factory.openSession();
		    Transaction tx = null;
		    User userUpdate = new User();
		    
		    // If there's no user with such an ID, return null.
		    if(getUser(userID) == null) {
		    	return null;
		    }
		      
		      try {
		         tx = session.beginTransaction();
		         
		         // Find the existing user through his ID.
		         userUpdate = (User) session.get(User.class, userID); 
		         
		         // Update the old name with a new one.
		         userUpdate.setUserName(user.getUserName().trim());
		          
		         // Update the session and commit to save changes.
				 session.update(userUpdate); 
		         tx.commit();
		         
		      } catch (HibernateException e) {
		    	  // In case of error, do a rollback.
		         if (tx != null) {
		        	 tx.rollback();
		         }
		         e.printStackTrace(); 
		      } finally {
		    	  // Finally, close session.
		         session.close(); 
		      }
		      // Return the updated user.
		      return userUpdate;
		}
		
		// Delete an existing user
		
		public static User deleteUser(int userID) {
			
			Session session = factory.openSession();
		    Transaction tx = null;
		    User deleteUser = new User();
		    
		    // If user is not found, return null.
		    if(getUser(userID) == null) {
		    	return null;
		    }
		      
		      try {
		         tx = session.beginTransaction();
		         
		         // Find user through his ID.
		         User object = (User) session.get(User.class, userID); 
		         // Delete then commit to save changes.
		         session.delete(object); 
		         tx.commit();
		         
		      } catch (HibernateException e) {
		    	  // In case of error, do a rollback.
		         if (tx != null) {
		        	 tx.rollback();
		         }
		         e.printStackTrace(); 
		      } finally {
		    	  // Finally, close session.
		         session.close(); 
		      }
		      return deleteUser;
		}
		
		// Verify an users credentials
		
		public boolean verifyPassword(String username, String rawPassword) {
			
			boolean result = false;
			Session session = factory.openSession();
			Transaction tx = null;
			
		      try {
		    	  
		    	 tx = session.beginTransaction();
		    	 @SuppressWarnings("unchecked")
		    	 Query<User> query = session.createQuery("SELECT c.password FROM User c WHERE UPPER(c.userName) = :userName");
		    	 query.setParameter("userName", username.toUpperCase().trim());
		    	 String pass = query.uniqueResult().toString();
		    	 result = Encryption.verifyPass(rawPassword, pass);
		         tx.commit();
		         
		      } catch (HibernateException e) {
		         if (tx != null) { 
		        	 tx.rollback();
		         }
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		      
			return result;
		}
		
	}

}
